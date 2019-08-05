package dev.mvc.admin;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminCont {

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc = null;

  public AdminCont() {
    System.out.println("--> AdminCont created.");
  }

  /**
   * 로그인 폼
   * 
   * @return
   */
  @RequestMapping(value = "/admin/login.do", method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/login_admin_form"); // /webapp/admin/login_admin_form.jsp

    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String adminid = ""; // id 저장 변수
    String adminpasswd = ""; // passwd 저장 변수

    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i]; // 쿠키 객체 추출

        if (cookie.getName().equals("adminid")) {
          adminid = cookie.getValue();
        } else if (cookie.getName().equals("adminpasswd")) {
          adminpasswd = cookie.getValue(); // 1234
        }
      }
    }

    mav.addObject("adminid", adminid);
    mav.addObject("adminpasswd", adminpasswd);

    return mav;
  }

  /**
   * 로그인 처리
   * 
   * @param request
   * @param response
   * @param session
   * @param id
   * @param id_save
   * @param passwd
   * @param passwd_save
   * @return
   */
  @RequestMapping(value = "/admin/login.do", method = RequestMethod.POST)
  public ModelAndView login(HttpServletRequest request, HttpServletResponse response, 
                                       HttpSession session, String adminid, String adminpasswd){
    // System.out.println("--> login() POST called.");
    ModelAndView mav = new ModelAndView();

    if (adminProc.login(adminid, adminpasswd) != 1) { // 로그인 실패시
      mav.setViewName("redirect:/admin/login_msg.jsp");

    } else { // 패스워드 일치하는 경우
      AdminVO adminVO = adminProc.readById(adminid);

      session.setAttribute("adminno", adminVO.getAdminno()); // session 내부 객체
      session.setAttribute("adminid", adminid);
      session.setAttribute("adminpasswd", adminpasswd);
      session.setAttribute("adminname", adminVO.getAdminname());

      // 로그인 내역 추가
      // VO
      // LoginProc.java create method execute
      // LoginVO loginVO = new LoginVO();
      // loginProc.create(loginVO);
      mav.setViewName("redirect:/index.jsp"); // 확장자 명시
    }
    return mav;
  }

  /**
   * 로그아웃 처리
   * 
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value = "/admin/logout.do", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, HttpSession session) {
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();

    session.invalidate(); // session 내부 객체의 등록된 모든 session 변수 삭제

    // webapp/member/message_logout.jsp
    mav.setViewName("redirect:/admin/logout_msg.jsp");

    return mav;
  }

}
