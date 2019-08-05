package dev.mvc.rentperiod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.account.AccountVO;

@Controller
public class RentperiodCont {
  
  
  //@Qualifier("dev.mvc.rentperiod.RentperiodContProc")
  @Autowired
  @Qualifier("dev.mvc.rentperiod.RentperiodProc")
  private RentperiodProcInter rentperiodProc;
  
  public RentperiodCont() {
    System.out.println("--> RentperiodContCont created.");
  }
  /**
   * 등록 폼 http://localhost:9090/ojt/rentperiod/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/rentperiod/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/rentperiod/create"); // /webapp/rentperiod/create.jsp
 
    return mav;
  }
  
  
  /**
   * 등록 폼 http://localhost:9090/ojt/rentperiod/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/rentperiod/create.do", method = RequestMethod.POST)
  public ModelAndView create(RentperiodVO rentperiodVO, int carrentno, String starttime, String endtime) {
    ModelAndView mav = new ModelAndView();
    System.out.println("carrentno 번호: " +carrentno);
    
    System.out.println("시작의 날 : "+rentperiodVO.getStartday());
    System.out.println("끝나는 날 : "+rentperiodVO.getEndday());
    System.out.println("시작 시간 : "+starttime);
    System.out.println("끝나는 시간: "+endtime);
    
    String sd = rentperiodVO.getStartday();
    String st = starttime;
    
    String ed = rentperiodVO.getEndday();
    String et = endtime;
    
    String startday = sd+" "+st;
    String endday = ed+" "+et;
    
    System.out.println("더해진 시작의날 결과값 출력 : "+startday);
    rentperiodVO.setStartday(startday);
    
    System.out.println("더해진 종말의날 결과값 출력 : "+endday);
    rentperiodVO.setEndday(endday);
   
   /* //String day = "2016-11-22 11:22:30.0"; // 형식을 지켜야 함
    java.sql.Timestamp t = java.sql.Timestamp.valueOf(day);


    System.out.println("전부 출력해:"+t);
    rentperiodVO.setStartday(t);
*/
  
      
      
    int count = rentperiodProc.create(rentperiodVO);
    System.out.println(rentperiodVO.getStartday());
    
    
    System.out.println("렌트 번호" +rentperiodVO.getRentno());
    Carrent_RentperiodVO carrent_RentperiodVO = rentperiodProc.read_by_join(rentperiodVO.getRentno());
    
    mav.addObject("carrent_RentperiodVO", carrent_RentperiodVO);
    mav.setViewName("redirect:/reservation/create.jsp?count=" + count);
    return mav;
  }
  
  
  
  
 
  

}







