package dev.mvc.payment;

public class PaymentVO {

  
 /* CREATE TABLE payment(
      paymentno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
      payment_price                   NUMBER(10)     NOT NULL, 
      payment_method                    VARCHAR2(50)     NOT NULL,
      payment_cancel                    CHAR(1)    DEFAULT 'N'     NOT NULL,
      reservationno                     NUMBER(10)     NULL ,
      userno                            NUMBER(10)     NULL ,
    FOREIGN KEY (userno) REFERENCES rent_user (userno),
    FOREIGN KEY (reservationno) REFERENCES reservation (reservationno)
  );*/
  private int paymentno;
  private int payment_price;
  private String payment_method;
  private int payment_method_num;
  public int getPayment_method_num() {
    return payment_method_num;
  }
  public void setPayment_method_num(int payment_method_num) {
    this.payment_method_num = payment_method_num;
  }
  private String payment_cancel;
  private int reservationno;
  private int userno;

  public String getPayment_method() {
    return payment_method;
  }
  public void setPayment_method(String payment_method) {
    this.payment_method = payment_method;
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
  public String getPayment_cancel() {
    return payment_cancel;
  }
  public void setPayment_cancel(String payment_cancel) {
    this.payment_cancel = payment_cancel;
  }
  public int getReservationno() {
    return reservationno;
  }
  public void setReservationno(int reservationno) {
    this.reservationno = reservationno;
  }
  public int getUserno() {
    return userno;
  }
  public void setUserno(int userno) {
    this.userno = userno;
  }
  
  
}
