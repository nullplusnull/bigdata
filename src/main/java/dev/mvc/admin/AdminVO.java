package dev.mvc.admin;

//CREATE TABLE RENT_ADMIN(
//    ADMINNO                           NUMBER(10)     NOT NULL    PRIMARY KEY,
//    ADMINID                           VARCHAR2(30)     NOT NULL,
//    ADMINNAME                         VARCHAR2(20)     NOT NULL,
//    ADMINPASSWD                       VARCHAR2(100)    NOT NULL,
//    ADMINTEL                          VARCHAR2(20)     NOT NULL,
//    ADMINEMAIL                        VARCHAR2(50)     NOT NULL,
//    RDATE                             DATE     NOT NULL
//);
//
//COMMENT ON TABLE RENT_ADMIN is '������';
//COMMENT ON COLUMN RENT_ADMIN.ADMINNO is '������ ��ȣ';
//COMMENT ON COLUMN RENT_ADMIN.ADMINID is '������ID';
//COMMENT ON COLUMN RENT_ADMIN.ADMINNAME is '������ �̸�';
//COMMENT ON COLUMN RENT_ADMIN.ADMINPASSWD is '������ ��й�ȣ';
//COMMENT ON COLUMN RENT_ADMIN.ADMINTEL is '������ ��ȭ��ȣ';
//COMMENT ON COLUMN RENT_ADMIN.ADMINEMAIL is '������ ����';
//COMMENT ON COLUMN RENT_ADMIN.RDATE is '������ �����';

public class AdminVO {
  private int adminno;
  private String adminid;
  private String adminname;
  private String adminpasswd;
  private String admintel;
  private String adminmail;
  private String adminrdate;
  
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public String getAdminid() {
    return adminid;
  }
  public void setAdminid(String adminid) {
    this.adminid = adminid;
  }
  public String getAdminname() {
    return adminname;
  }
  public void setAdminname(String adminname) {
    this.adminname = adminname;
  }
  public String getAdminpasswd() {
    return adminpasswd;
  }
  public void setAdminpasswd(String adminpasswd) {
    this.adminpasswd = adminpasswd;
  }
  public String getAdmintel() {
    return admintel;
  }
  public void setAdmintel(String admintel) {
    this.admintel = admintel;
  }
  public String getAdminmail() {
    return adminmail;
  }
  public void setAdminmail(String adminmail) {
    this.adminmail = adminmail;
  }
  public String getAdminrdate() {
    return adminrdate;
  }
  public void setAdminrdate(String adminrdate) {
    this.adminrdate = adminrdate;
  }
  
  
  
}
