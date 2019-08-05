package dev.mvc.payment;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.account.AccountProcInter;
import dev.mvc.account.AccountVO;
import dev.mvc.buyhistory.BuyhistoryProcInter;
import dev.mvc.buyhistory.BuyhistoryVO;
import dev.mvc.carrent.CarrentProcInter;
import dev.mvc.management.ManagementDAOInter;
import dev.mvc.management.ManagementProc;
import dev.mvc.management.ManagementProcInter;
import dev.mvc.management.ManagementVO;
import dev.mvc.rentperiod.RentperiodProcInter;
import dev.mvc.reservation.ReservationProcInter;
import dev.mvc.reservation.ReservationVO;
import dev.mvc.reservation.Reservation_RentperiodVO;
import dev.mvc.user.UserProcInter;
import dev.mvc.user.UserVO;

@Controller
public class PaymentCont {

  @Autowired
  @Qualifier("dev.mvc.reservation.ReservationProc")
  private ReservationProcInter reservationProc;
  
  @Autowired
  @Qualifier("dev.mvc.rentperiod.RentperiodProc")
  private RentperiodProcInter rentperiodProc;
  
  @Autowired
  @Qualifier("dev.mvc.carrent.CarrentProc")
  private CarrentProcInter carrentProc;
  
  @Autowired
  @Qualifier("dev.mvc.account.AccountProc")
  private AccountProcInter accountProc;
  
  @Autowired
  @Qualifier("dev.mvc.payment.PaymentProc")
  private PaymentProcInter paymentProc;

  
  @Autowired
  @Qualifier("dev.mvc.buyhistory.BuyhistoryProc")
  private BuyhistoryProcInter buyhistoryProc;


  @Autowired
  @Qualifier("dev.mvc.user.UserProc")
  private UserProcInter userProc;
  
  @Autowired
  @Qualifier("dev.mvc.management.ManagementProc")
  private ManagementProcInter managementProc;
  
 /*@RequestMapping(value="/payment/create.do", method=RequestMethod.GET)
 public ModelAndView create(RedirectAttributes redirectAttributes, RentperiodVO rentperiodVO, CarrentVO carretVO,
     int total_price, int hour, String startwhere, String endwhere, String _start_day, String _end_day) {
   ModelAndView mav = new ModelAndView();

   mav.addObject("hour", hour);
   mav.addObject("total_price", total_price);

   System.out.println(carretVO.getCarrentno());
   
   mav.addObject("startday", _start_day);
   mav.addObject("endday", _end_day);
   
   redirectAttributes.addAttribute("carrentno", carretVO.getCarrentno());
   redirectAttributes.addAttribute("carname", carretVO.getCarname());
   redirectAttributes.addAttribute("power", carretVO.getPower());
   redirectAttributes.addAttribute("price", carretVO.getPrice());
   
   redirectAttributes.addAttribute("startwhere", startwhere);
   redirectAttributes.addAttribute("endwhere", endwhere);
   redirectAttributes.addAttribute("startplace", rentperiodVO.getStartplace());
   redirectAttributes.addAttribute("endplace", rentperiodVO.getEndplace());
   
   mav.setViewName("redirect:/reservation/create.jsp");
   
   return mav;
 }*/
 
  
  /*// http://localhost:9090/rent/reservation/list.do
  @RequestMapping(value="/reservation/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reservation/list"); // /webapp/cate/list.jsp
    
    ArrayList<ReservationVO> list = reservationProc.list();
    System.out.println("test1");
    mav.addObject("list", list);
    
    return mav;
  }  
  */
  
//http://localhost:9090/ojt/categrp/create.do
 @RequestMapping(value="/payment/create.do", method=RequestMethod.POST)
 public ModelAndView create(int accountno, int total_price, int reservationno, HttpSession session) {
   ModelAndView mav = new ModelAndView();
   int userno = (Integer)session.getAttribute("userno"); // session
   int count = 2;
   System.out.println("결제 POST 접속 완료");
   
   System.out.println("accountno : 0"+accountno);
   System.out.println("total_price0"+total_price);
   System.out.println("reservationno0" +reservationno);
   
   // 계좌 정보 리딩
   AccountVO accountVO = accountProc.read(accountno);
   int now_cash = accountVO.getCash();
   int return_cash = now_cash - total_price;
   

     AccountVO accountVO_c = new AccountVO();
     accountVO_c.setCash(return_cash);
     accountVO_c.setAccountno(accountno);
     accountProc.update_cash(accountVO_c);
     
     // Payment 값 할당
     PaymentVO paymentVO = new PaymentVO();
     paymentVO.setPayment_price(total_price);
     paymentVO.setPayment_cancel("N");
     paymentVO.setPayment_method("계좌");
     paymentVO.setReservationno(reservationno);
     paymentVO.setUserno(userno);
     
     // paymentProc.create 생성
     count = paymentProc.create(paymentVO);
     System.out.println("count payment: "+count);
     
     ReservationVO reservationVO = new ReservationVO();
     reservationVO.setReservationno(reservationno);
     reservationVO.setReservation_state("예약 완료");
     count =  reservationProc.update_state(reservationVO);
     System.out.println("update reservation: "+count);
     
     int paymentno = paymentProc.read_paymentno(reservationno);
     
     System.out.println("paymentno : "+paymentno);
     /*BuyhistoryVO buyhistoryVO = new BuyhistoryVO();
     buyhistoryVO.setPaymentno(paymentno);
     buyhistoryProc.create(buyhistoryVO);
*/
     
     // 관리자 create
     ManagementVO managementVO = new ManagementVO();
     managementVO.setPaymentno(paymentno);
     managementProc.create(managementVO);
   
   mav.setViewName("redirect:/payment/create_msg.jsp?count="+count);
   
   return mav;
 }
 
 
 
 
 @RequestMapping(value="/mypage/buy_list.do", method=RequestMethod.GET)
 public ModelAndView buy_list(HttpSession session) {
   ModelAndView mav = new ModelAndView();

   System.out.println("1");
   // userno = 1 유저 로그인 가정
   int userno = (Integer)session.getAttribute("userno"); // session
   ArrayList<TotalVO> list = new ArrayList<>();
   list = paymentProc.my_reservation_pay_list(userno);
   
   
   mav.addObject("list", list);
  
   mav.setViewName("/mypage/buy_list");
   
   return mav;
 }
 
 @RequestMapping(value="/mypage/buy_read.do", method=RequestMethod.POST)
 public ModelAndView buy_read(int paymentno) {
   ModelAndView mav = new ModelAndView();

   System.out.println("buy_read");
   System.out.println("paymentno : " +paymentno);
   TotalVO totalVO = paymentProc.my_reservation_pay_read(paymentno);
   

   UserVO userVO = userProc.read(totalVO.getUserno());
  
   mav.addObject("totalVO", totalVO);
   mav.addObject("userVO", userVO);
   mav.setViewName("/mypage/buy_read");
   
   return mav;
 }
    
 
 
 
 @RequestMapping(value = "/payment/pop_user_list.do", method = RequestMethod.GET)
 public ModelAndView pop_user_list(int userno) {
   ModelAndView mav = new ModelAndView();

   
   ArrayList<AccountVO> list = accountProc.user_list(userno);
   mav.addObject("list", list);
   mav.setViewName("/payment/pop_user_list_account");
   return mav;
 }
 
 
 @RequestMapping(value="/payment/createA.do", method=RequestMethod.POST)
 @ResponseBody
 public String check_rentperiod_proc(int accountno, int total_price, int reservationno, HttpSession session) {
   System.out.println("AJAX 결제");
   int resultno = 0;
   int userno = (Integer)session.getAttribute("userno"); // session
   
   // 예약 테이블 기본키로 차종, 렌트시작일, 렌트반납일을 읽어옴
   Reservation_RentperiodVO reservation_RentperiodVO = reservationProc.read_day(reservationno);
   int carrentno = reservation_RentperiodVO.getCarrentno();
   String startday = reservation_RentperiodVO.getStartday();
   String endday = reservation_RentperiodVO.getEndday();
   
   System.out.println("startday : "+startday);
   System.out.println("endday : "+endday);
   System.out.println("carrentno : "+carrentno);
   
   // 해당 일자에 예약이 있는지 체크합니다
   HashMap<String, Object> map = new HashMap<String, Object>();
   System.out.println("해시맵 생성");
   map.put("startday", startday);
   map.put("endday", endday);
   map.put("carrentno", carrentno);
   System.out.println("해시맵 자료 입력");
   
   int count =  reservationProc.check_rentperiod(map);
   // int count = rentperiodProc.check_rentperiod(map);
   System.out.println("count : 값 출력 : "+count);
   JSONObject json = new JSONObject();
   
   // 현재 결제하려는 예약 레코드 값은 무조건 1개가 되어야 하므로
   if(count != 1) {
     resultno = 1;
     json.put("resultno", resultno);
     return json.toString();
   }
   
   // 계좌 정보 리딩후에 돈이 없으면 손님을 퇴장시킵시다
   AccountVO accountVO = accountProc.read(accountno);
   int now_cash = accountVO.getCash();
   int return_cash = now_cash - total_price;
   
   if(return_cash < 0) {
     resultno = 2;
     json.put("resultno", resultno);
     return json.toString();
   }
   
   AccountVO accountVO_c = new AccountVO();
   accountVO_c.setCash(return_cash);
   accountVO_c.setAccountno(accountno);
   accountProc.update_cash(accountVO_c);
   
   
   // Payment Table 생성
   // Payment 값 할당
   PaymentVO paymentVO = new PaymentVO();
   paymentVO.setPayment_price(total_price);
   paymentVO.setPayment_cancel("N");
   paymentVO.setPayment_method("계좌");
   paymentVO.setReservationno(reservationno);
   paymentVO.setUserno(userno);
   paymentVO.setPayment_method_num(accountno);
   
   // paymentProc.create 생성
   count = paymentProc.create(paymentVO);
   System.out.println("count payment: "+count);
   
   ReservationVO reservationVO = new ReservationVO();
   reservationVO.setReservationno(reservationno);
   reservationVO.setReservation_state("예약 완료");
   count =  reservationProc.update_state(reservationVO);
   System.out.println("update reservation: "+count);
   
   int paymentno = paymentProc.read_paymentno(reservationno);
   
   System.out.println("paymentno : "+paymentno);
   
   // 관리자 create
   ManagementVO managementVO = new ManagementVO();
   managementVO.setPaymentno(paymentno);
   managementProc.create(managementVO);

   
   resultno = 3;
   json.put("resultno", resultno);
   return json.toString();
 }

 
 
 
 @RequestMapping(value = "/payment/orderComplete.do", method = RequestMethod.GET)
 public ModelAndView orderComplete(String startday, String endday, String power, String carname, String price, String hour, String total_price,
     RedirectAttributes redirectAttributes, int reservationno) {
   ModelAndView mav = new ModelAndView();
   System.out.println("테스트가 성공했습니다.~~");
   System.out.println("테스트2" +reservationno);
   
   System.out.println(startday);
   System.out.println(endday);
   System.out.println(power);
   System.out.println(carname);
   System.out.println(price);
   System.out.println(hour);
   System.out.println(total_price);
   
   redirectAttributes.addAttribute("startday", startday);
   redirectAttributes.addAttribute("endday", endday);
   redirectAttributes.addAttribute("power", power);
   redirectAttributes.addAttribute("carname", carname);
   redirectAttributes.addAttribute("price", price);
   redirectAttributes.addAttribute("hour", hour);
   redirectAttributes.addAttribute("total_price", total_price);
   
   /*ArrayList<AccountVO> list = accountProc.user_list(userno);
   mav.addObject("list", list);*/
   mav.setViewName("redirect:/payment/orderComplete.jsp");
   return mav;
 }
 
 
 

}








