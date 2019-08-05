package dev.mvc.rentperiod;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Carrent_RentperiodVO {

  
  /*렌트기간*/
  private int rentno;
  /*렌트기간*/
  private int carrentno;
  /*렌트기간*/
  private String startday;
  /*렌트기간*/
  private String endday;
  /*렌트기간*/
  private String scheduleday;
  /*렌트기간*/
  private String startplace;
  /*렌트기간*/
  private String endplace;
  

  /** 구분 */
  private String carsize;
  /** 차색깔 */
  private String carcolor;
  /** 제조사 */
  private String carproducer;
  /** 차량명 */
  private String carname;
  /** 시간당가격 */
  private int price;
  /** 연료 */
  private String power;
  /** 블랙박스 */
  private String blackbox;
  /** 네비게이션 */
  private String navigation;
  /** 통풍시트 */
  private String airingseat;
  /** 연령제한 */
  private int age;
  /** 차 상태 */
  private String condition;
  /** DBMS 파일명 */
  private String rent_file1;
  /** 파일크기 */
  private String rent_size1;

  /** Preview 소형 이미지 200 X 150, 자동 생성됨 */
  private String rent_thumb1 = "";
  
  /** 여러 이미지중에 첫번째 Preview 이미지 저장, 200 X 150 */
  private String rent_thumbs = "";
  /** 등록일 */
  private String rdate;
  
  private int hour;
  
  private int total_price;
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  하나의 파일 업로드
   */  
  //private List<MultipartFile> filesMF;

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getTotal_price() {
    return total_price;
  }

  public void setTotal_price(int total_price) {
    this.total_price = total_price;
  }
  /** 
Spring Framework에서 자동 주입되는 업로드 파일 객체,
DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
여러개의 파일 업로드
   */  
  private List<MultipartFile> rent_file1MF;

  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
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
    System.out.println("썸네일을 리턴합니다.");
    System.out.println(this.rent_thumb1);
    return rent_thumb1;
  }

  /**
   * @param rent_thumb1 the rent_thumb1 to set
   */
  public void setRent_thumb1(String rent_thumb1) {
    System.out.println("썸네일이 저장되었습니다... : "+rent_thumb1);
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
  
  
  
  
  
  /**
   * @return the rentno
   */
  public int getRentno() {
    return rentno;
  }
  /**
   * @param rentno the rentno to set
   */
  public void setRentno(int rentno) {
    this.rentno = rentno;
  }
  
  /**
   * @return the startday
   */
  public String getStartday() {
    return startday;
  }
  /**
   * @param startday the startday to set
   */
  public void setStartday(String startday) {
    this.startday = startday;
  }
  /**
   * @return the endday
   */
  public String getEndday() {
    return endday;
  }
  /**
   * @param endday the endday to set
   */
  public void setEndday(String endday) {
    this.endday = endday;
  }
  /**
   * @return the scheduleday
   */
  public String getScheduleday() {
    return scheduleday;
  }
  /**
   * @param scheduleday the scheduleday to set
   */
  public void setScheduleday(String scheduleday) {
    this.scheduleday = scheduleday;
  }
  /**
   * @return the startplace
   */
  public String getStartplace() {
    return startplace;
  }
  /**
   * @param startplace the startplace to set
   */
  public void setStartplace(String startplace) {
    this.startplace = startplace;
  }
  /**
   * @return the endplace
   */
  public String getEndplace() {
    return endplace;
  }
  /**
   * @param endplace the endplace to set
   */
  public void setEndplace(String endplace) {
    this.endplace = endplace;
  }
  
  
}

































