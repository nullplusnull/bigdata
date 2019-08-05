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
   * �α��� ��
   * 
   * @return
   */
  @RequestMapping(value = "/admin/login.do", method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/login_admin_form"); // /webapp/admin/login_admin_form.jsp

    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String adminid = ""; // id ���� ����
    String adminpasswd = ""; // passwd ���� ����

    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i]; // ��Ű ��ü ����

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
   * �α��� ó��
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

    if (adminProc.login(adminid, adminpasswd) != 1) { // �α��� ���н�
      mav.setViewName("redirect:/admin/login_msg.jsp");

    } else { // �н����� ��ġ�ϴ� ���
      AdminVO adminVO = adminProc.readById(adminid);

      session.setAttribute("adminno", adminVO.getAdminno()); // session ���� ��ü
      session.setAttribute("adminid", adminid);
      session.setAttribute("adminpasswd", adminpasswd);
      session.setAttribute("adminname", adminVO.getAdminname());

      // �α��� ���� �߰�
      // VO
      // LoginProc.java create method execute
      // LoginVO loginVO = new LoginVO();
      // loginProc.create(loginVO);
      mav.setViewName("redirect:/index.jsp"); // Ȯ���� ���
    }
    return mav;
  }

  /**
   * �α׾ƿ� ó��
   * 
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value = "/admin/logout.do", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, HttpSession session) {
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();

    session.invalidate(); // session ���� ��ü�� ��ϵ� ��� session ���� ����

    // webapp/member/message_logout.jsp
    mav.setViewName("redirect:/admin/logout_msg.jsp");

    return mav;
  }

}
