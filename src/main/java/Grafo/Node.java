/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package Grafo;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-11-25")
public class Node implements org.apache.thrift.TBase<Node, Node._Fields>, java.io.Serializable, Cloneable, Comparable<Node> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Node");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField FT_FIELD_DESC = new org.apache.thrift.protocol.TField("ft", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField PRED_FIELD_DESC = new org.apache.thrift.protocol.TField("pred", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField IP_FIELD_DESC = new org.apache.thrift.protocol.TField("ip", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("port", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new NodeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new NodeTupleSchemeFactory();

  public int id; // required
  public java.util.List<Finger> ft; // required
  public Finger pred; // required
  public java.lang.String ip; // required
  public int port; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    FT((short)2, "ft"),
    PRED((short)3, "pred"),
    IP((short)4, "ip"),
    PORT((short)5, "port");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // FT
          return FT;
        case 3: // PRED
          return PRED;
        case 4: // IP
          return IP;
        case 5: // PORT
          return PORT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __PORT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.FT, new org.apache.thrift.meta_data.FieldMetaData("ft", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "Finger"))));
    tmpMap.put(_Fields.PRED, new org.apache.thrift.meta_data.FieldMetaData("pred", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT        , "Finger")));
    tmpMap.put(_Fields.IP, new org.apache.thrift.meta_data.FieldMetaData("ip", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PORT, new org.apache.thrift.meta_data.FieldMetaData("port", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Node.class, metaDataMap);
  }

  public Node() {
  }

  public Node(
    int id,
    java.util.List<Finger> ft,
    Finger pred,
    java.lang.String ip,
    int port)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.ft = ft;
    this.pred = pred;
    this.ip = ip;
    this.port = port;
    setPortIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Node(Node other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetFt()) {
      java.util.List<Finger> __this__ft = new java.util.ArrayList<Finger>(other.ft.size());
      for (Finger other_element : other.ft) {
        __this__ft.add(other_element);
      }
      this.ft = __this__ft;
    }
    if (other.isSetPred()) {
      this.pred = new Finger(other.pred);
    }
    if (other.isSetIp()) {
      this.ip = other.ip;
    }
    this.port = other.port;
  }

  public Node deepCopy() {
    return new Node(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.ft = null;
    this.pred = null;
    this.ip = null;
    setPortIsSet(false);
    this.port = 0;
  }

  public int getId() {
    return this.id;
  }

  public Node setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getFtSize() {
    return (this.ft == null) ? 0 : this.ft.size();
  }

  public java.util.Iterator<Finger> getFtIterator() {
    return (this.ft == null) ? null : this.ft.iterator();
  }

  public void addToFt(Finger elem) {
    if (this.ft == null) {
      this.ft = new java.util.ArrayList<Finger>();
    }
    this.ft.add(elem);
  }

  public java.util.List<Finger> getFt() {
    return this.ft;
  }

  public Node setFt(java.util.List<Finger> ft) {
    this.ft = ft;
    return this;
  }

  public void unsetFt() {
    this.ft = null;
  }

  /** Returns true if field ft is set (has been assigned a value) and false otherwise */
  public boolean isSetFt() {
    return this.ft != null;
  }

  public void setFtIsSet(boolean value) {
    if (!value) {
      this.ft = null;
    }
  }

  public Finger getPred() {
    return this.pred;
  }

  public Node setPred(Finger pred) {
    this.pred = pred;
    return this;
  }

  public void unsetPred() {
    this.pred = null;
  }

  /** Returns true if field pred is set (has been assigned a value) and false otherwise */
  public boolean isSetPred() {
    return this.pred != null;
  }

  public void setPredIsSet(boolean value) {
    if (!value) {
      this.pred = null;
    }
  }

  public java.lang.String getIp() {
    return this.ip;
  }

  public Node setIp(java.lang.String ip) {
    this.ip = ip;
    return this;
  }

  public void unsetIp() {
    this.ip = null;
  }

  /** Returns true if field ip is set (has been assigned a value) and false otherwise */
  public boolean isSetIp() {
    return this.ip != null;
  }

  public void setIpIsSet(boolean value) {
    if (!value) {
      this.ip = null;
    }
  }

  public int getPort() {
    return this.port;
  }

  public Node setPort(int port) {
    this.port = port;
    setPortIsSet(true);
    return this;
  }

  public void unsetPort() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  /** Returns true if field port is set (has been assigned a value) and false otherwise */
  public boolean isSetPort() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  public void setPortIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PORT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((java.lang.Integer)value);
      }
      break;

    case FT:
      if (value == null) {
        unsetFt();
      } else {
        setFt((java.util.List<Finger>)value);
      }
      break;

    case PRED:
      if (value == null) {
        unsetPred();
      } else {
        setPred((Finger)value);
      }
      break;

    case IP:
      if (value == null) {
        unsetIp();
      } else {
        setIp((java.lang.String)value);
      }
      break;

    case PORT:
      if (value == null) {
        unsetPort();
      } else {
        setPort((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case FT:
      return getFt();

    case PRED:
      return getPred();

    case IP:
      return getIp();

    case PORT:
      return getPort();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case FT:
      return isSetFt();
    case PRED:
      return isSetPred();
    case IP:
      return isSetIp();
    case PORT:
      return isSetPort();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Node)
      return this.equals((Node)that);
    return false;
  }

  public boolean equals(Node that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_ft = true && this.isSetFt();
    boolean that_present_ft = true && that.isSetFt();
    if (this_present_ft || that_present_ft) {
      if (!(this_present_ft && that_present_ft))
        return false;
      if (!this.ft.equals(that.ft))
        return false;
    }

    boolean this_present_pred = true && this.isSetPred();
    boolean that_present_pred = true && that.isSetPred();
    if (this_present_pred || that_present_pred) {
      if (!(this_present_pred && that_present_pred))
        return false;
      if (!this.pred.equals(that.pred))
        return false;
    }

    boolean this_present_ip = true && this.isSetIp();
    boolean that_present_ip = true && that.isSetIp();
    if (this_present_ip || that_present_ip) {
      if (!(this_present_ip && that_present_ip))
        return false;
      if (!this.ip.equals(that.ip))
        return false;
    }

    boolean this_present_port = true;
    boolean that_present_port = true;
    if (this_present_port || that_present_port) {
      if (!(this_present_port && that_present_port))
        return false;
      if (this.port != that.port)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + id;

    hashCode = hashCode * 8191 + ((isSetFt()) ? 131071 : 524287);
    if (isSetFt())
      hashCode = hashCode * 8191 + ft.hashCode();

    hashCode = hashCode * 8191 + ((isSetPred()) ? 131071 : 524287);
    if (isSetPred())
      hashCode = hashCode * 8191 + pred.hashCode();

    hashCode = hashCode * 8191 + ((isSetIp()) ? 131071 : 524287);
    if (isSetIp())
      hashCode = hashCode * 8191 + ip.hashCode();

    hashCode = hashCode * 8191 + port;

    return hashCode;
  }

  @Override
  public int compareTo(Node other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFt()).compareTo(other.isSetFt());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFt()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ft, other.ft);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPred()).compareTo(other.isSetPred());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPred()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pred, other.pred);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIp()).compareTo(other.isSetIp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ip, other.ip);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPort()).compareTo(other.isSetPort());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPort()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.port, other.port);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Node(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("ft:");
    if (this.ft == null) {
      sb.append("null");
    } else {
      sb.append(this.ft);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("pred:");
    if (this.pred == null) {
      sb.append("null");
    } else {
      sb.append(this.pred);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ip:");
    if (this.ip == null) {
      sb.append("null");
    } else {
      sb.append(this.ip);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("port:");
    sb.append(this.port);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class NodeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NodeStandardScheme getScheme() {
      return new NodeStandardScheme();
    }
  }

  private static class NodeStandardScheme extends org.apache.thrift.scheme.StandardScheme<Node> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Node struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // FT
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list10 = iprot.readListBegin();
                struct.ft = new java.util.ArrayList<Finger>(_list10.size);
                Finger _elem11;
                for (int _i12 = 0; _i12 < _list10.size; ++_i12)
                {
                  _elem11 = new Finger();
                  _elem11.read(iprot);
                  struct.ft.add(_elem11);
                }
                iprot.readListEnd();
              }
              struct.setFtIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PRED
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.pred = new Finger();
              struct.pred.read(iprot);
              struct.setPredIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // IP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ip = iprot.readString();
              struct.setIpIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.port = iprot.readI32();
              struct.setPortIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Node struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      if (struct.ft != null) {
        oprot.writeFieldBegin(FT_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.ft.size()));
          for (Finger _iter13 : struct.ft)
          {
            _iter13.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.pred != null) {
        oprot.writeFieldBegin(PRED_FIELD_DESC);
        struct.pred.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.ip != null) {
        oprot.writeFieldBegin(IP_FIELD_DESC);
        oprot.writeString(struct.ip);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PORT_FIELD_DESC);
      oprot.writeI32(struct.port);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class NodeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NodeTupleScheme getScheme() {
      return new NodeTupleScheme();
    }
  }

  private static class NodeTupleScheme extends org.apache.thrift.scheme.TupleScheme<Node> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Node struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetFt()) {
        optionals.set(1);
      }
      if (struct.isSetPred()) {
        optionals.set(2);
      }
      if (struct.isSetIp()) {
        optionals.set(3);
      }
      if (struct.isSetPort()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetFt()) {
        {
          oprot.writeI32(struct.ft.size());
          for (Finger _iter14 : struct.ft)
          {
            _iter14.write(oprot);
          }
        }
      }
      if (struct.isSetPred()) {
        struct.pred.write(oprot);
      }
      if (struct.isSetIp()) {
        oprot.writeString(struct.ip);
      }
      if (struct.isSetPort()) {
        oprot.writeI32(struct.port);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Node struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list15 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.ft = new java.util.ArrayList<Finger>(_list15.size);
          Finger _elem16;
          for (int _i17 = 0; _i17 < _list15.size; ++_i17)
          {
            _elem16 = new Finger();
            _elem16.read(iprot);
            struct.ft.add(_elem16);
          }
        }
        struct.setFtIsSet(true);
      }
      if (incoming.get(2)) {
        struct.pred = new Finger();
        struct.pred.read(iprot);
        struct.setPredIsSet(true);
      }
      if (incoming.get(3)) {
        struct.ip = iprot.readString();
        struct.setIpIsSet(true);
      }
      if (incoming.get(4)) {
        struct.port = iprot.readI32();
        struct.setPortIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

