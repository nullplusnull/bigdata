package dev.mvc.carrent;
 
import java.util.List;

 
import org.springframework.web.multipart.MultipartFile;


 
public class CarrentVO {
/*
    carrentno                             NUMBER(10)                            NOT NULL   PRIMARY KEY,
    carsize                               VARCHAR2(20)                          NOT NULL,
    carcolor                              VARCHAR2(30)                        NOT NULL,
    carproducer                         VARCHAR2(30)                        NOT NULL,
    carname                               VARCHAR2(30)                          NOT NULL,
    price                                   NUMBER(10)                            NOT NULL,
    power                                 VARCHAR2(20)                          NOT NULL,
    blackbox                              CHAR(1)            DEFAULT 'Y'      NOT NULL,
    navigation                            CHAR(1)            DEFAULT 'Y'      NOT NULL,
    airingseat                            CHAR(1)            DEFAULT 'Y'      NOT NULL,
    age                                     NUMBER(3)                             NOT NULL,
    condition                            VARCHAR2(30)    DEFAULT '����' NOT NULL,
    rent_file1                                   VARCHAR2(1000),
    rent_size1                                  NUMBER(20),
    rent_thumb1                                  NUMBER(20),
    rdate                                   DATE     NOT NULL
*/
  
  /** ����Ʈ��ǰ��ȣ */
  private int carrentno;
  /** ���� */
  private String carsize;
  /** ������ */
  private String carcolor;
  /** ������ */
  private String carproducer;
  /** ������ */
  private String carname;
  /** �ð��簡�� */
  private int price;
  /** ���� */
  private String power;
  /** ���ڽ� */
  private String blackbox;
  /** �׺���̼� */
  private String navigation;
  /** ��ǳ��Ʈ */
  private String airingseat;
  /** �������� */
  private int age;
  /** �� ���� */
  private String condition;
  /** DBMS ���ϸ� */
  private String rent_file1;
  /** ����ũ�� */
  private String rent_size1;

  /** Preview ���� �̹��� 200 X 150, �ڵ� ������ */
  private String rent_thumb1 = "";
  
  /** ���� �̹����߿� ù��° Preview �̹��� ����, 200 X 150 */
  private String rent_thumbs = "";
  /** ����� */
  private String rdate;
  /** 
Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
�������� ���� ���ε�
   */  
  private List<MultipartFile> rent_file1MF;
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  �ϳ��� ���� ���ε�
   */  
  //private List<MultipartFile> filesMF;



  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String sizesLabel;

  /**
   * @return the carrentno
   */
  public int getCarrentno() {
    return carrentno;
  }

  /**
   * @param carrentno the carrentno to set
   */
  public void setCarrentno(int carrentno) {
    this.carrentno = carrentno;
  }

  /**
   * @return the carsize
   */
  public String getCarsize() {
    return carsize;
  }

  /**
   * @param carsize the carsize to set
   */
  public void setCarsize(String carsize) {
    this.carsize = carsize;
  }

  /**
   * @return the carcolor
   */
  public String getCarcolor() {
    return carcolor;
  }

  /**
   * @param carcolor the carcolor to set
   */
  public void setCarcolor(String carcolor) {
    this.carcolor = carcolor;
  }

  /**
   * @return the carproducer
   */
  public String getCarproducer() {
    return carproducer;
  }

  /**
   * @param carproducer the carproducer to set
   */
  public void setCarproducer(String carproducer) {
    this.carproducer = carproducer;
  }

  /**
   * @return the carname
   */
  public String getCarname() {
    return carname;
  }

  /**
   * @param carname the carname to set
   */
  public void setCarname(String carname) {
    this.carname = carname;
  }

  /**
   * @return the price
   */
  public int getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * @return the power
   */
  public String getPower() {
    return power;
  }

  /**
   * @param power the power to set
   */
  public void setPower(String power) {
    this.power = power;
  }

  /**
   * @return the blackbox
   */
  public String getBlackbox() {
    return blackbox;
  }

  /**
   * @param blackbox the blackbox to set
   */
  public void setBlackbox(String blackbox) {
    this.blackbox = blackbox;
  }

  /**
   * @return the navigation
   */
  public String getNavigation() {
    return navigation;
  }

  /**
   * @param navigation the navigation to set
   */
  public void setNavigation(String navigation) {
    this.navigation = navigation;
  }

  /**
   * @return the airingseat
   */
  public String getAiringseat() {
    return airingseat;
  }

  /**
   * @param airingseat the airingseat to set
   */
  public void setAiringseat(String airingseat) {
    this.airingseat = airingseat;
  }

  /**
   * @return the age
   */
  public int getAge() {
    return age;
  }

  /**
   * @param age the age to set
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * @return the condition
   */
  public String getCondition() {
    return condition;
  }

  /**
   * @param condition the condition to set
   */
  public void setCondition(String condition) {
    this.condition = condition;
  }

  /**
   * @return the rent_file1
   */
  public String getRent_file1() {
    return rent_file1;
  }

  /**
   * @param rent_file1 the rent_file1 to set
   */
  public void setRent_file1(String rent_file1) {
    this.rent_file1 = rent_file1;
  }

  /**
   * @return the rent_size1
   */
  public String getRent_size1() {
    return rent_size1;
  }

  /**
   * @param rent_size1 the rent_size1 to set
   */
  public void setRent_size1(String rent_size1) {
    this.rent_size1 = rent_size1;
  }

  /**
   * @return the rent_thumb1
   */
  public String getRent_thumb1() {
    return rent_thumb1;
  }

  /**
   * @param rent_thumb1 the rent_thumb1 to set
   */
  public void setRent_thumb1(String rent_thumb1) {
    this.rent_thumb1 = rent_thumb1;
  }

  /**
   * @return the rent_thumbs
   */
  public String getRent_thumbs() {
    return rent_thumbs;
  }

  /**
   * @param rent_thumbs the rent_thumbs to set
   */
  public void setRent_thumbs(String rent_thumbs) {
    this.rent_thumbs = rent_thumbs;
  }

  /**
   * @return the rdate
   */
  public String getRdate() {
    return rdate;
  }

  /**
   * @param rdate the rdate to set
   */
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  /**
   * @return the rent_file1MF
   */
  public List<MultipartFile> getRent_file1MF() {
    return rent_file1MF;
  }

  /**
   * @param rent_file1MF the rent_file1MF to set
   */
  public void setRent_file1MF(List<MultipartFile> rent_file1MF) {
    this.rent_file1MF = rent_file1MF;
  }

  /**
   * @return the sizesLabel
   */
  public String getSizesLabel() {
    return sizesLabel;
  }

  /**
   * @param sizesLabel the sizesLabel to set
   */
  public void setSizesLabel(String sizesLabel) {
    this.sizesLabel = sizesLabel;
  }



  
  
  

 
  
}
 
  