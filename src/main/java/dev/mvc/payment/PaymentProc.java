package dev.mvc.payment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component("dev.mvc.payment.PaymentProc")
public class PaymentProc implements PaymentProcInter {

  @Autowired
  private PaymentDAOInter paymentDAO;
  
  @Override
  public int create(PaymentVO paymentVO) {
    int count = 0;
    count = paymentDAO.create(paymentVO);
    return count;
  }

  @Override
  public int read_paymentno(int reservationno) {
    int paymentno = paymentDAO.read_paymentno(reservationno);
    return paymentno;
  }

  @Override
  public ArrayList<Reservation_PaymentVO> reservation_payment_list(int userno) {
    ArrayList<Reservation_PaymentVO> list = new ArrayList<>();
    list = paymentDAO.reservation_payment_list(userno);
    return list;
  }

  @Override
  public ArrayList<TotalVO> my_reservation_pay_list(int userno) {
    ArrayList<TotalVO> list = new ArrayList<>();
    list = paymentDAO.my_reservation_pay_list(userno);
    return list;
  }

  @Override
  public TotalVO my_reservation_pay_read(int paymentno) {
    TotalVO totalVO = paymentDAO.my_reservation_pay_read(paymentno);
    return totalVO;
  }

  @Override
  public PaymentVO pay_method(int paymentno) {
    PaymentVO paymentVO = paymentDAO.pay_method(paymentno);
    return paymentVO;
  }

  @Override
  public int pay_cancel(int paymentno) {
    int count = 0;
    count = paymentDAO.pay_cancel(paymentno);
    return count;
  }

}






