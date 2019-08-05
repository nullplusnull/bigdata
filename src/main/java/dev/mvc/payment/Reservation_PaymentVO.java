package dev.mvc.payment;

public class Reservation_PaymentVO {
  
  
  private int paymentno;
  private int payment_price;
  private String payment_method;
  private String payment_cancel;
  private int reservationno;
  private int deposit;
  private String reservation_state;
  private int userno;
  
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
  
  
  
  
}
