package dev.mvc.user;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/*CREATE TABLE rent_user(
    userno                            NUMBER(10)     NOT NULL    PRIMARY KEY,
    userid                            VARCHAR2(50)     NOT NULL UNIQUE,
    username                          VARCHAR2(20)     NOT NULL,
    userpasswd                        VARCHAR2(20)     NOT NULL,
    userzipcode                          VARCHAR2(5)        NULL, -- 快祈锅龋, 12345
    useraddress1                         VARCHAR2(300)       NULL, -- 林家 1
    useraddress2                        VARCHAR2(300)       NULL, -- 林家 2
    usertel                           VARCHAR2(20)     NOT NULL,
    userage                           NUMBER(7)     NOT NULL,
    usersex                            CHAR(1)    NOT NULL,
    userlicense                       VARCHAR2(20)     NOT NULL,
    userstatus                        CHAR(1)    NOT NULL,
    rdate                             DATE     NOT NULL,
    licensefile                        VARCHAR2(300)  NULL,
    userthumb                            VARCHAR2(300)   NULL,
    usersize                                VARCHAR2(300)   NULL
);*/

public class UserVO {
  private List<MultipartFile> filesMF;
  private int userno;
  private String userid;
  private String username;
  private String userpasswd;
  private String userzipcode;
  private String useraddress1;
  private String useraddress2;
  private String usertel;
  private int userage;
  private String usersex;
  private String userlicense;
  private String userstatus;
  private String licensefile;
  private String userthumb;
  private String usersize;
  private String rdate;
  private String sizesLabel;
  
  public int getUserno() {
    return userno;
  }
  public void setUserno(int userno) {
    this.userno = userno;
  }
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getUserpasswd() {
    return userpasswd;
  }
  public void setUserpasswd(String userpasswd) {
    this.userpasswd = userpasswd;
  }
  public String getUserzipcode() {
    return userzipcode;
  }
  public void setUserzipcode(String userzipcode) {
    this.userzipcode = userzipcode;
  }
  public String getUseraddress1() {
    return useraddress1;
  }
  public void setUseraddress1(String useraddress1) {
    this.useraddress1 = useraddress1;
  }
  public String getUseraddress2() {
    return useraddress2;
  }
  public void setUseraddress2(String useraddress2) {
    this.useraddress2 = useraddress2;
  }
  public String getUsertel() {
    return usertel;
  }
  public void setUsertel(String usertel) {
    this.usertel = usertel;
  }
  public int getUserage() {
    return userage;
  }
  public void setUserage(int userage) {
    this.userage = userage;
  }
  public String getUsersex() {
    return usersex;
  }
  public void setUsersex(String usersex) {
    this.usersex = usersex;
  }
  public String getUserlicense() {
    return userlicense;
  }
  public void setUserlicense(String userlicense) {
    this.userlicense = userlicense;
  }
  public String getUserstatus() {
    return userstatus;
  }
  public void setUserstatus(String userstatus) {
    this.userstatus = userstatus;
  }
  public String getLicensefile() {
    return licensefile;
  }
  public void setLicensefile(String licensefile) {
    this.licensefile = licensefile;
  }
  public String getUserthumb() {
    return userthumb;
  }
  public void setUserthumb(String userthumb) {
    this.userthumb = userthumb;
  }
  public String getUsersize() {
    return usersize;
  }
  public void setUsersize(String usersize) {
    this.usersize = usersize;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getSizesLabel() {
    return sizesLabel;
  }
  public void setSizesLabel(String sizesLabel) {
    this.sizesLabel = sizesLabel;
  }
  public List<MultipartFile> getFilesMF() {
    return filesMF;
  }
  public void setFilesMF(List<MultipartFile> filesMF) {
    this.filesMF = filesMF;
  }
  
  
  
  
}
