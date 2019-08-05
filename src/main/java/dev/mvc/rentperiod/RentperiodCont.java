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
   * ��� �� http://localhost:9090/ojt/rentperiod/create.do
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
   * ��� �� http://localhost:9090/ojt/rentperiod/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/rentperiod/create.do", method = RequestMethod.POST)
  public ModelAndView create(RentperiodVO rentperiodVO, int carrentno, String starttime, String endtime) {
    ModelAndView mav = new ModelAndView();
    System.out.println("carrentno ��ȣ: " +carrentno);
    
    System.out.println("������ �� : "+rentperiodVO.getStartday());
    System.out.println("������ �� : "+rentperiodVO.getEndday());
    System.out.println("���� �ð� : "+starttime);
    System.out.println("������ �ð�: "+endtime);
    
    String sd = rentperiodVO.getStartday();
    String st = starttime;
    
    String ed = rentperiodVO.getEndday();
    String et = endtime;
    
    String startday = sd+" "+st;
    String endday = ed+" "+et;
    
    System.out.println("������ �����ǳ� ����� ��� : "+startday);
    rentperiodVO.setStartday(startday);
    
    System.out.println("������ �����ǳ� ����� ��� : "+endday);
    rentperiodVO.setEndday(endday);
   
   /* //String day = "2016-11-22 11:22:30.0"; // ������ ���Ѿ� ��
    java.sql.Timestamp t = java.sql.Timestamp.valueOf(day);


    System.out.println("���� �����:"+t);
    rentperiodVO.setStartday(t);
*/
  
      
      
    int count = rentperiodProc.create(rentperiodVO);
    System.out.println(rentperiodVO.getStartday());
    
    
    System.out.println("��Ʈ ��ȣ" +rentperiodVO.getRentno());
    Carrent_RentperiodVO carrent_RentperiodVO = rentperiodProc.read_by_join(rentperiodVO.getRentno());
    
    mav.addObject("carrent_RentperiodVO", carrent_RentperiodVO);
    mav.setViewName("redirect:/reservation/create.jsp?count=" + count);
    return mav;
  }
  
  
  
  
 
  

}







