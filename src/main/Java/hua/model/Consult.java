package hua.model;

public class Consult {

  private long no;
  private String title;
  private String content;
  private String img;
  private int state;
  private java.sql.Timestamp time;

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


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "Consult{" +
            "no=" + no +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", img='" + img + '\'' +
            ", state=" + state +
            ", time=" + time +
            '}';
  }
}
