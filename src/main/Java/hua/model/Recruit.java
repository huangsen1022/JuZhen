package hua.model;

public class Recruit {

  private long no;
  private String name;
  private String addr;
  private String responsibility;
  private int state;
  private int type1;
  private int type2;
  private java.sql.Timestamp date;

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public long getNo() {
    return no;
  }

  public void setNo(long no) {
    this.no = no;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }


  public String getResponsibility() {
    return responsibility;
  }

  public void setResponsibility(String responsibility) {
    this.responsibility = responsibility;
  }


  public int getType1() {
    return type1;
  }

  public void setType1(int type1) {
    this.type1 = type1;
  }


  public int getType2() {
    return type2;
  }

  public void setType2(int type2) {
    this.type2 = type2;
  }


  public java.sql.Timestamp getDate() {
    return date;
  }

  public void setDate(java.sql.Timestamp date) {
    this.date = date;
  }


}
