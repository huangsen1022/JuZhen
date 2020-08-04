package hua.model;

public class Resume {

  private long no;
  private String name;
  private String tel;
  private String email;
  private String intro;
  private String code;
  private String addr;
  private String title;
  private java.sql.Timestamp time;

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getNo() {
    return no;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "Resume{" +
            "no=" + no +
            ", name='" + name + '\'' +
            ", tel='" + tel + '\'' +
            ", email='" + email + '\'' +
            ", intro='" + intro + '\'' +
            ", code='" + code + '\'' +
            ", addr='" + addr + '\'' +
            ", title='" + title + '\'' +
            ", time=" + time +
            '}';
  }
}
