package dev.mvc.payment;

import java.util.ArrayList;

public interface PaymentProcInter {

  public int read_paymentno(int reservationno);
  public int create(PaymentVO PaymentVO);
  public ArrayList<Reservation_PaymentVO> reservation_payment_list(int userno);
  public ArrayList<TotalVO> my_reservation_pay_list(int userno);
  public TotalVO my_reservation_pay_read(int paymentno);
  public PaymentVO pay_method(int paymentno);
  public int pay_cancel(int paymentno);
  
}
