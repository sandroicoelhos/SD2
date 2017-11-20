package Handlers;

import Grafo.Aresta;
import Grafo.Chord;
import Grafo.Finger;
import Grafo.KeyNotFound;
import Grafo.Node;
import Grafo.Thrift;
import Grafo.Vertice;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Handler implements Thrift.Iface {

    private ConcurrentHashMap<Integer, Vertice> HashVertice;
    private int id;
    private static Node node, nodeRaiz;
    private static int numBits = 5;

    public Handler(String args[]) throws TException {
        /* Ex. da sequência de argumentos:
            IP Local | Porta Local | IP Raíz | Porta Raíz
            1ª Vez: localhost 4000 localhost 4000 (IP/Porta Iguais para upar o nó raíz)
            2ª+ Vez: localhost 4001 localhost 4000 (Nó que quer entrar tem que ter porta diferente)
         */
        this.HashVertice = new ConcurrentHashMap<Integer, Vertice>(); // Instancia hash para funções de vértice e aresta

        int port = Integer.parseInt(args[1]); //Porta do nó local (Que quer entrar no chord)
        int nodeRaizPort = Integer.parseInt(args[3]); //Porta do nó nodeRaiz

        // Variável de thread usada nas funções fixFingers e Stabilize pra permitir que os nós entrem a qualquer momento ao chord. [Artigo]
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(numBits);

        node = new Node();
        node.setFt(new ArrayList<Finger>());
        node.setIp(args[0]); // IP Local
        node.setPort(port); // Porta Local

        // Se o IP/Porta "Raiz" for igual ao IP e porta Local estabelece o nó raíz
        if (args[2].equals(node.getIp()) && (port == nodeRaizPort)) {
            randomID(node);
            join(node);
            System.out.println("# Nó RAIZ estabelecido: \n"
                    + "*ID: " + node.getId()
                    + "\n*IP: " + node.getIp()
                    + "\n*Port: " + node.getPort() + "\n");
        } else {
            /* Quer dizer que já existe um nó no chord então irá iniciar uma comunicação com
            o nó raíz e vai atribuir esse nó raíz à variável nodeRaiz. Então o nó local, 
            ao setar seu ID primeiro deve verificar que não existe nenhum nó pertencente ao 
            chord com o mesmo ID, por meio da função VerifyID.
             */
            TTransport transport = new TSocket(args[2], Integer.parseInt(args[3]));
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Chord.Client client = new Chord.Client(protocol);
            nodeRaiz = client.sendSelf();
            transport.close();
            node.setId(verifyID(nodeRaiz));
            join(nodeRaiz);
            System.out.println("# Nó local estabelecido no Chord: \n"
                    + "*ID: " + node.getId()
                    + "\n*IP: " + node.getIp()
                    + "\n*Port: " + node.getPort() + "\n");
        }
    }

    @Override
    public boolean addVertice(Vertice v) throws TException {

        if (this.HashVertice.putIfAbsent(v.nome, v) == null) {
            return true;
        }

        return false;
    }

    @Override
    public Vertice readVertice(int nome) throws TException, KeyNotFound {

        Vertice v = HashVertice.computeIfPresent(nome, (a, b) -> {
            return b;
        });

        if (v != null) {
            return v;
        }

        throw new KeyNotFound();

    }

    @Override
    public boolean updateVertice(Vertice v) throws KeyNotFound, TException {
        try {
            Vertice vertice = readVertice(v.getNome());

            synchronized (vertice) {
                vertice.setCor(v.getCor());
                vertice.setDescricao(v.getDescricao());
                vertice.setPeso(v.getPeso());
                return true;
            }

        } catch (KeyNotFound e) {
            return false;
        }
    }

    @Override
    public boolean deleteVertice(Vertice v) throws KeyNotFound, TException {
        Vertice vertice;
        Aresta a;
        synchronized (v) {
            for (Integer key : v.HashAresta.keySet()) {
                a = this.readAresta(v.HashAresta.get(key).getV1(), v.HashAresta.get(key).getV2());
                this.deleteAresta(a);
            }
            if (HashVertice.remove(v.getNome()) != null) {
                return true;
            }
            return false;
        }
    }

    @Override
    public List<Vertice> readAllVertice() throws TException {
        ArrayList<Vertice> Vertices = new ArrayList<>();

        for (Integer key : HashVertice.keySet()) {
            Vertices.add(this.readVertice(key));
        }

        return Vertices;
    }

    @Override
    public List<Vertice> readVerticeNeighboors(Vertice v) throws TException {
        ArrayList<Vertice> Vertices = new ArrayList<>();

        for (Integer key : v.HashAresta.keySet()) {
            Vertices.add(this.readVertice(v.HashAresta.get(key).v2));
        }

        return Vertices;
    }

    @Override
    public boolean addAresta(Aresta a) throws TException {
        Vertice v;
        v = this.readVertice(a.getV1());

        if (v.HashAresta.putIfAbsent(a.getV2(), a) == null) {
            return true;
        }

        return false;
    }

    @Override
    public Aresta readAresta(int nomeV1, int nomeV2) throws TException {
        Vertice vertice;
        vertice = this.readVertice(nomeV1);

        Aresta aresta;
        aresta = vertice.HashAresta.computeIfPresent(nomeV2, (a, b) -> {
            return b;
        });

        if (aresta != null) {
            return aresta;
        }

        throw new KeyNotFound();

    }

    @Override
    public List<Aresta> readAllAresta() throws TException { // Tratar concorrência
        ArrayList<Aresta> Arestas = new ArrayList<>();

        for (Integer keyVertice : HashVertice.keySet()) {
            synchronized (keyVertice) {
                for (Integer keyAresta : HashVertice.get(keyVertice).HashAresta.keySet()) {
                    Arestas.add(HashVertice.get(keyVertice).HashAresta.get(keyAresta));
                }
            }
        }
        return Arestas;
    }

    @Override
    public List<Aresta> readAllArestaOfVertice(Vertice v) throws TException { // Tratar concorrência
        ArrayList<Aresta> Arestas = new ArrayList<>();
        Vertice vertice;

        for (Integer key : v.HashAresta.keySet()) {
            vertice = this.readVertice(v.HashAresta.get(key).getV2());
            Arestas.add(this.readAresta(v.getNome(), vertice.getNome()));
        }

        return Arestas;
    }

    @Override
    public boolean updateAresta(Aresta a) throws KeyNotFound, TException {
        try {
            Aresta aresta = this.readAresta(a.v1, a.v2);

            synchronized (aresta) {
                aresta.setDescricao(a.descricao);
                aresta.setDirect(a.isDirect());
                aresta.setPeso(a.getPeso());
                return true;
            }

        } catch (KeyNotFound e) {
            return false;
        }
    }

    @Override
    public boolean deleteAresta(Aresta a) throws KeyNotFound, TException {
        synchronized (a) {
            Vertice v1 = this.readVertice(a.getV1());
            Vertice v2 = this.readVertice(a.getV2());

            v1.HashAresta.remove(v2.getNome());
            v2.HashAresta.remove(v1.getNome());

            return true;
        }
    }

    @Override
    public void join(Node n) throws TException {
        node.setPred(null);

        for (int i = 1; i <= numBits; i++) {
            Finger aux = new Finger();
            aux.setId(node.getId());
            aux.setIp(node.getIp());
            aux.setPort(node.getPort());
            node.getFt().add(aux);
        }

        if (node.getId() != n.getId()) {
            TTransport transport = new TSocket(n.getIp(), n.getPort());
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Chord.Client client = new Chord.Client(protocol);
            Node naux = client.getSucessor(node.getId());
            transport.close();

            synchronized (node.getFt().get(0)) {
                node.getFt().get(0).setId(naux.getId());
                node.getFt().get(0).setIp(naux.getIp());
                node.getFt().get(0).setPort(naux.getPort());
            }
        }
    }

    @Override
    public Node getSucessor(int id) throws TException {
        Node node = getPredecessor(id);

        if (node.getFt().get(0).getId() == node.getId()) {
            System.out.println("Sucessor ID: " + node.getId() + " encontrado para ID:" + id);
            return node;
        } else {
            TTransport transport = new TSocket(node.getFt().get(0).getIp(), node.getFt().get(0).getPort());
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Chord.Client client = new Chord.Client(protocol);
            node = client.sendSelf();
            transport.close();
            System.out.println("Else = Sucessor ID: " + node.getId() + " encontrado para ID:" + id);
            return node;
        }
    }

    @Override
    public Node getPredecessor(int id) throws TException {
        System.out.println("Procurando Predecessor para ID: " + id);
        Node aux = node;
        while (!interval(id, aux.getId(), true, aux.getFt().get(0).getId(), false)) {
            if (aux != node) {
                TTransport transport = new TSocket(aux.getIp(), aux.getPort());
                transport.open();
                TProtocol protocol = new TBinaryProtocol(transport);
                Chord.Client client = new Chord.Client(protocol);
                aux = client.closestPrecedingFinger(id);
                transport.close();
            } else {
                aux = closestPrecedingFinger(id);
            }
        }
        System.out.println("Predecessor ID: " + node.getId() + " achado para ID: " + id);
        return aux;
    }

    @Override
    public Node closestPrecedingFinger(int id) throws TException {

        for (int i = numBits - 1; i >= 0; i--) {
            System.out.println("Procurando Finger Predecessor mais próximo para ID: " + id + " na tabela de ID:" + node.getId() + " Entrada(" + i + ")->" + node.getFt().get(i).getId());
            if (interval(node.getFt().get(i).getId(), node.getId(), true, id, true)) {
                if (node.getId() != node.getFt().get(i).getId()) {
                    Finger finger = node.getFt().get(i);
                    TTransport transport = new TSocket(finger.getIp(), finger.getPort());
                    transport.open();
                    TProtocol protocol = new TBinaryProtocol(transport);
                    Chord.Client client = new Chord.Client(protocol);
                    Node aux = client.sendSelf();
                    transport.close();
                    return aux;
                } else {
                    return node;
                }
            }
        }
        return node;
    }

    @Override
    public void transferKeys(Node n) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stabilize() throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notify(Node n) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fixFingers() throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node sendSelf() throws TException {
        return node;
    }

    @Override
    public void setPredecessor(Node n) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int verifyID(Node node) throws TException {
        int trueID = -1;

        System.out.println("\nGerando ID... \n");
        
        while (1 == 1) {
            trueID = randomID(node).getId();
            // Abre a conexão com os dados locais do nó e verifica no sucessor se o ID pode ser usado.
            TTransport transport = new TSocket(node.getIp(), node.getPort());
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Chord.Client client = new Chord.Client(protocol);
            
            Node aux = client.getSucessor(trueID); // Pergunta qual é o ID do sucessor
            
            int idAux = aux.getId(); // Variável de verificação de ID
            
            /* Se retornar um ID do sucessor diferente do perguntado 
            ao chord quer dizer que esse ID perguntado pode ser atribuído. 
            Senão continua o loop até encontrar um ID factível. 
            Ex.: Gerou o ID 5 e ao perguntar o sucessor, retornou o 9, 
            logo o 5 pode ser atribuído ao nó local. Caso retornasse o ID 5, 
            teria que gerar outro ID sem ser o 5.*/
            
            if (idAux != trueID) {
                System.out.println("ID gerado!");
                break;
            } else {
                System.out.println("ID já atribuído.");
            }
        }

        return trueID;

    }

    public Node randomID(Node node) {
        // Função destinada a geração de um número aleatório dentro do intervalo pre-definido
        int a = (int) (Math.random() * Math.pow(2, numBits));
        node.setId(a);

        return node;
    }

    public static boolean interval(int x, int a, boolean flagOpenA, int b, boolean flagOpenB) {
        //Verifica se x está no intervalo de valores "a" e "b".
        //As flags informam se o intervalo é aberto ou não. 

        if (a == b) {
            return !((flagOpenA && flagOpenB) && x == a);
        } else {
            if ((!flagOpenA && x == a) || (!flagOpenB && x == b)) {
                return true;
            }

            if (a < b) {
                return ((x > a) && (x < b));
            } else {
                return ((x > a) || (x >= 0 && x < b));
            }
        }

    }

    public static int getID() {
        return node.getId();
    }
}
