package dev.mvc.reservation;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.carrent.CarrentProcInter;
import dev.mvc.carrent.CarrentVO;
import dev.mvc.rentperiod.Carrent_RentperiodVO;
import dev.mvc.rentperiod.RentperiodProcInter;
import dev.mvc.rentperiod.RentperiodVO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;






@Controller
public class ReservationCont {

  @Autowired
  @Qualifier("dev.mvc.reservation.ReservationProc")
  private ReservationProcInter reservationProc;
  
  @Autowired
  @Qualifier("dev.mvc.rentperiod.RentperiodProc")
  private RentperiodProcInter rentperiodProc;
  
  @Autowired
  @Qualifier("dev.mvc.carrent.CarrentProc")
  private CarrentProcInter carrentProc;


 @RequestMapping(value="/reservation/create.do", method=RequestMethod.GET)
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
 }
 
  
  // http://localhost:9090/rent/reservation/list.do
  @RequestMapping(value="/reservation/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reservation/list"); // /webapp/cate/list.jsp
    
    ArrayList<ReservationVO> list = reservationProc.list();
    System.out.println("test1");
    mav.addObject("list", list);
    
    return mav;
  }  
  
  
//http://localhost:9090/ojt/categrp/create.do
 @RequestMapping(value="/reservation/create.do", method=RequestMethod.POST)
 public ModelAndView create(RedirectAttributes redirectAttributes, int carrentno, String startday, String endday, String startplace, 
     String endplace, HttpSession session) {
   ModelAndView mav = new ModelAndView();
   //int userno = 1;   // 로그인 되어있다 가정합시다
   int userno = (Integer)session.getAttribute("userno"); // session
   System.out.println("예약 POST접속 완료");
   
   RentperiodVO rentperiodVO = new RentperiodVO();
   ReservationVO reservationVO = new ReservationVO();
   
   rentperiodVO.setCarrentno(carrentno);
   rentperiodVO.setStartday(startday);
   rentperiodVO.setEndday(endday);
   rentperiodVO.setStartplace(startplace);
   rentperiodVO.setEndplace(endplace);
   System.out.println("기간 테이블 할당 완료");

   // 예약 테이블 생성
   int count = rentperiodProc.create(rentperiodVO);
   System.out.println("1 level");
   System.out.println("count"+count);
   if(count != 0) {
     HashMap<String, Object> map = new HashMap<String, Object>();
     map.put("startday", startday);
     map.put("endday", endday);
     map.put("carrentno", carrentno);
     
     System.out.println("test a");
     System.out.println("startday"+startday);
     System.out.println("endday" +endday);
     System.out.println("carrentno" +carrentno);
     RentperiodVO rentperiodVO_new = rentperiodProc.day_read(map);
     
     System.out.println("test b");
     int rentno = rentperiodVO_new.getRentno();
     
     System.out.println(" rentno : "+rentno);

     reservationVO.setReservation_state("예약 대기");
     reservationVO.setRentno(rentno);
     reservationVO.setUserno(userno);
  
      
     count = reservationProc.create(reservationVO);
     System.out.println("2 level : "+count);
     
     
     Carrent_RentperiodVO carrent_RentperiodVO = rentperiodProc.read_by_join(rentno);
     
     redirectAttributes.addAttribute("startday", carrent_RentperiodVO.getStartday());
     redirectAttributes.addAttribute("endday", carrent_RentperiodVO.getEndday());
     redirectAttributes.addAttribute("power", carrent_RentperiodVO.getPower());
     redirectAttributes.addAttribute("carname", carrent_RentperiodVO.getCarname());
     redirectAttributes.addAttribute("price", carrent_RentperiodVO.getPrice());
     redirectAttributes.addAttribute("hour", carrent_RentperiodVO.getHour());
     redirectAttributes.addAttribute("total_price", carrent_RentperiodVO.getTotal_price());

     
     ReservationVO reservationVO_ = reservationProc.read_by_rentno(rentno);
     System.out.println("3 level");
     System.out.println("reservationVO_.getReservationno() : "+reservationVO_.getReservationno());
     redirectAttributes.addAttribute("reservationno", reservationVO_.getReservationno());
     
     mav.addObject("count", count);
   }
   mav.setViewName("redirect:/payment/create.jsp?count=" + count);
   
   return mav;
 }
 

}








