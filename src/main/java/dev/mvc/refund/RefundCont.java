package dev.mvc.refund;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.account.AccountProcInter;
import dev.mvc.account.AccountVO;
import dev.mvc.payment.PaymentProc;
import dev.mvc.payment.PaymentProcInter;
import dev.mvc.payment.PaymentVO;
import dev.mvc.reservation.ReservationProcInter;
import dev.mvc.reservation.ReservationVO;

@Controller
public class RefundCont {
  
  @Autowired
  @Qualifier("dev.mvc.refund.RefundProc")
  private RefundProcInter refundProc = null; 
  
  @Autowired
  @Qualifier("dev.mvc.payment.PaymentProc")
  private PaymentProcInter paymentProc = null; 
  
  @Autowired
  @Qualifier("dev.mvc.account.AccountProc")
  private AccountProcInter accountProc = null; 
  
  @Autowired
  @Qualifier("dev.mvc.reservation.ReservationProc")
  private ReservationProcInter reservationProc = null; 
  
  public RefundCont() {
    System.out.println("--> RefundCont created.");
  }
  
  @RequestMapping(value = "/refund/pop_refund.do", method = RequestMethod.GET)
  public ModelAndView pop_user_list() {
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/refund/pop_refund");
    return mav;
  }

  
  @ResponseBody
  @RequestMapping(value = "/refund/create.do", method = RequestMethod.POST)
  public String create(String refund, int paymentno) {
    ModelAndView mav = new ModelAndView();
    int resultno = 0;
    PaymentVO paymentVO = paymentProc.pay_method(paymentno);
    JSONObject json = new JSONObject();
    
    // 결제 레코드의 정보 로딩
    paymentVO.getPayment_method();
    int refund_price = paymentVO.getPayment_price();
    int accountno = paymentVO.getPayment_method_num();
    int reservationno = paymentVO.getReservationno();
    
    System.out.println("reservationno : "+reservationno);
    
    // 결제 레코드 업데이트
    int count = paymentProc.pay_cancel(paymentno);
    if(count != 1) {
      resultno = 1;
      json.put("resultno", resultno);
      return json.toString();
    }
    
    // 예약 레코드 업데이트
    ReservationVO reservationVO = new ReservationVO();
    reservationVO.setReservation_state("결제 취소");
    reservationVO.setReservationno(reservationno);
    count = reservationProc.update_state(reservationVO);
    if(count != 1) {
      resultno = 2;
      json.put("resultno", resultno);
      return json.toString();
    }
    
    // 계좌 정보 리딩후 환불
    AccountVO accountVO = accountProc.read(accountno);
    int now_cash = accountVO.getCash();
    int return_cash = now_cash + refund_price;
    
    AccountVO accountVO_c = new AccountVO();
    accountVO_c.setCash(return_cash);
    accountVO_c.setAccountno(accountno);
    accountProc.update_cash(accountVO_c);
    
    // 환불이 완료되고 환불 레코드를 생성합니다.
    RefundVO refundVO = new RefundVO();
    System.out.println(refund+" Pre: refund!");
    refundVO.setRefund_reason(refund);
    refundVO.setPaymentno(paymentno);
    count = refundProc.create(refundVO);
    if(count != 1) {
      resultno = 3;
      json.put("resultno", resultno);
      return json.toString();
    }
    
    json.put("resultno", resultno);
    return json.toString();

  }
}










