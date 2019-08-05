package dev.mvc.payment;

public class TotalVO {
  
  /** 차렌트상품번호 */
  private int carrentno;
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
  
  private int hour;

  public int getHour() {
    return hour;
  }
  public void setHour(int hour) {
    this.hour = hour;
  }
  /** Preview 소형 이미지 200 X 150, 자동 생성됨 */
  private String rent_thumb1 = "";
  
  /** 여러 이미지중에 첫번째 Preview 이미지 저장, 200 X 150 */
  private String rent_thumbs = "";
  /** 등록일 */
  private String rdate;
  

  private int rentno;


  private String startday;

  private String endday;

  private String scheduleday;

  private String startplace;

  private String endplace;
  
  private int reservationno;
  private int deposit;
  private String reservation_state;
  private int userno;
  
  private int paymentno;
  private int payment_price;
  private String payment_method;
  private String payment_cancel;
  public int getCarrentno() {
    return carrentno;
  }
  public void setCarrentno(int carrentno) {
    this.carrentno = carrentno;
  }
  public String getCarsize() {
    return carsize;
  }
  public void setCarsize(String carsize) {
    this.carsize = carsize;
  }
  public String getCarcolor() {
    return carcolor;
  }
  public void setCarcolor(String carcolor) {
    this.carcolor = carcolor;
  }
  public String getCarproducer() {
    return carproducer;
  }
  public void setCarproducer(String carproducer) {
    this.carproducer = carproducer;
  }
  public String getCarname() {
    return carname;
  }
  public void setCarname(String carname) {
    this.carname = carname;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public String getPower() {
    return power;
  }
  public void setPower(String power) {
    this.power = power;
  }
  public String getBlackbox() {
    return blackbox;
  }
  public void setBlackbox(String blackbox) {
    this.blackbox = blackbox;
  }
  public String getNavigation() {
    return navigation;
  }
  public void setNavigation(String navigation) {
    this.navigation = navigation;
  }
  public String getAiringseat() {
    return airingseat;
  }
  public void setAiringseat(String airingseat) {
    this.airingseat = airingseat;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public String getCondition() {
    return condition;
  }
  public void setCondition(String condition) {
    this.condition = condition;
  }
  public String getRent_file1() {
    return rent_file1;
  }
  public void setRent_file1(String rent_file1) {
    this.rent_file1 = rent_file1;
  }
  public String getRent_size1() {
    return rent_size1;
  }
  public void setRent_size1(String rent_size1) {
    this.rent_size1 = rent_size1;
  }
  public String getRent_thumb1() {
    return rent_thumb1;
  }
  public void setRent_thumb1(String rent_thumb1) {
    this.rent_thumb1 = rent_thumb1;
  }
  public String getRent_thumbs() {
    return rent_thumbs;
  }
  public void setRent_thumbs(String rent_thumbs) {
    this.rent_thumbs = rent_thumbs;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getRentno() {
    return rentno;
  }
  public void setRentno(int rentno) {
    this.rentno = rentno;
  }
  public String getStartday() {
    return startday;
  }
  public void setStartday(String startday) {
    this.startday = startday;
  }
  public String getEndday() {
    return endday;
  }
  public void setEndday(String endday) {
    this.endday = endday;
  }
  public String getScheduleday() {
    return scheduleday;
  }
  public void setScheduleday(String scheduleday) {
    this.scheduleday = scheduleday;
  }
  public String getStartplace() {
    return startplace;
  }
  public void setStartplace(String startplace) {
    this.startplace = startplace;
  }
  public String getEndplace() {
    return endplace;
  }
  public void setEndplace(String endplace) {
    this.endplace = endplace;
  }
  public int getReservationno() {
    return reservationno;
  }
  public void setReservationno(int reservationno) {
    this.reservationno = reservationno;
  }
  public int getDeposit() {
    return deposit;
  }
  public void setDeposit(int deposit) {
    this.deposit = deposit;
  }
  public String getReservation_state() {
    return reservation_state;
  }
  public void setReservation_state(String reservation_state) {
    this.reservation_state = reservation_state;
  }
  public int getUserno() {
    return userno;
  }
  public void setUserno(int userno) {
    this.userno = userno;
  }
  public int getPaymentno() {
    return paymentno;
  }
  public void setPaymentno(int paymentno) {
    this.paymentno = paymentno;
  }
  public int getPayment_price() {
    return payment_price;
  }
  public void setPayment_price(int payment_price) {
    this.payment_price = payment_price;
  }
  public String getPayment_method() {
    return payment_method;
  }
  public void setPayment_method(String payment_method) {
    this.payment_method = payment_method;
  }
  public String getPayment_cancel() {
    return payment_cancel;
  }
  public void setPayment_cancel(String payment_cancel) {
    this.payment_cancel = payment_cancel;
  }



  
  
  
}











