package dev.mvc.account;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.carrent.CarrentVO;


@Controller
public class AccountCont {

  @Autowired
  @Qualifier("dev.mvc.account.AccountProc")
  private AccountProcInter accountProc = null;
  
  public AccountCont() {
    System.out.println("--> AccountCont created.");
  }
  
  @RequestMapping(value = "/account/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/account/create"); // /webapp/carrent/create.jsp
 
    
    return mav;
  }
  
  @RequestMapping(value = "/account/create.do", method = RequestMethod.POST)
  public ModelAndView create(AccountVO accountVO) {
    ModelAndView mav = new ModelAndView();

    int count = accountProc.create(accountVO);
    // mav.addObject("count", count);
    mav.setViewName("redirect:/account/create_msg.jsp?count=" + count);
    return mav;
  }
  

  /**
   * 전체 목록
   * 
   * @return
   */
  // http://localhost:9090/ojt/carrent/list.do
  @RequestMapping(value = "/account/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    ArrayList<AccountVO> list = accountProc.list();
    mav.addObject("list", list);
    mav.setViewName("/account/list"); // /webapp/carrent/list.jsp
    return mav;
  }
  
    
  @RequestMapping(value = "/account/user_list.do", method = RequestMethod.GET)
  public ModelAndView user_list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    int userno = (Integer)session.getAttribute("userno"); // session
    ArrayList<AccountVO> list = accountProc.user_list(userno);
    mav.addObject("list", list);
    mav.setViewName("/account/user_list");
    return mav;
  }
  
  @RequestMapping(value = "/account/pop_user_list.do", method = RequestMethod.GET)
  public ModelAndView pop_user_list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    int userno = (Integer)session.getAttribute("userno"); // session
    
    ArrayList<AccountVO> list = accountProc.user_list(userno);
    mav.addObject("list", list);
    mav.setViewName("/account/pop_user_list");
    return mav;
  }
  
  
  @RequestMapping(value = "/account/read.do", method = RequestMethod.GET)
  public ModelAndView read(int accountno) {
    ModelAndView mav = new ModelAndView();

    
    AccountVO accountVO = accountProc.read(accountno);
    mav.addObject("accountVO", accountVO);
    mav.setViewName("/account/read");
    return mav;
  }
  
  
  
  
}






















