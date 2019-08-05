package dev.mvc.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nation.web.tool.Tool;
import nation.web.tool.Upload;
import dev.mvc.carrent.FileVO;

@Controller
public class UserCont {

  @Autowired
  @Qualifier("dev.mvc.user.UserProc")
  private UserProcInter userProc = null;

  public UserCont() {
    System.out.println("--> UserCont created.");
  }

  /**
   * �ߺ� ID �˻�, JSON http://localhost:9090/ojt/user/checkId.do?id=user01
   * 
   * @param categrpno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/checkId.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String checkId(String userid) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    int count = userProc.checkId(userid);
    JSONObject json = new JSONObject();
    json.put("count", count);

    return json.toString();
  }

  @ResponseBody
  @RequestMapping(value = "/user/checkTel.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String checkTel(String usertel) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    int count = userProc.checkTel(usertel);
    JSONObject json = new JSONObject();
    json.put("count", count);

    return json.toString();
  }

  /**
   * ȸ�� ���� �� http://localhost:9090/rent/user/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/user/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/create");
    return mav;
  }

  // http://localhost:9090/ojt/user/create.do
  // categrpVO�� form �±��� ������ �ڵ� �����
  @RequestMapping(value = "/user/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, UserVO userVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/user/storage");
    // Spring�� FIle ��ü�� �����ص�, �����ڴ� �̿븸 ��
    List<MultipartFile> filesMF = userVO.getFilesMF(); // Spring�� File ��ü��
                                                       // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String licensefile = ""; // �÷��� ������ ���ϸ�
    String licensefile_item = ""; // �ϳ��� ���ϸ�
    String usersize = "";
    long usersize_item = 0; // �ϳ��� ���� ������
    String userthumb = ""; // Thumb ���ϵ�
    String userthumb_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = filesMF.size(); // ���ε�� ���� ����

    // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
    System.out.println("--> ���ε�� ���� ���� count: " + filesMF.size());

    if (count > 0) { // ���� ������ �����Ѵٸ�
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0:ù��° ���� ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          licensefile_item = Upload.saveFileSpring(multipartFile, upDir); // ������
                                                                          // ����
                                                                          // ����
          usersize_item = multipartFile.getSize();

          if (Tool.isImage(licensefile_item)) { // �̹������� �˻�
            userthumb_item = Tool.preview(upDir, licensefile_item, 120, 80); // Thumb
                                                                             // �̹���
            // ����
          }
          // 1�� �̻��� ���� ���� ó��
          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            licensefile = licensefile + "/" + licensefile_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            usersize = usersize + "/" + usersize_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            userthumb = userthumb + "/" + userthumb_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
            // ������ ��� ���� ��ü�� 1�� �����Ǽ� ũ�� üũ
            licensefile = licensefile_item; // file1.jpg
            usersize = "" + usersize_item; // 123456
            userthumb = userthumb_item; // file1_t.jpg
          }
        } // if (multipartFile.getSize() > 0) { END

      }
    }

    System.out.println("licensefile:" + licensefile);
    System.out.println("usersize:" + usersize);
    System.out.println("userthumb:" + userthumb);
    userVO.setLicensefile(licensefile);
    userVO.setUsersize(usersize);
    userVO.setUserthumb(userthumb);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    count = userProc.create(userVO);
    // mav.addObject("count", count);
    mav.setViewName("redirect:/user/create_msg.jsp?count=" + count);
    return mav;
  }

  @RequestMapping(value = "/user/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/list"); // webapp/user/list.jsp

    /*
     * if (userProc.isMember(session) == false) {
     * mav.setViewName("redirect:/user/login_need.jsp"); } else {
     * mav.setViewName("/user/list"); // webapp/user/list.jsp
     * 
     * ArrayList<UserVO> list = userProc.list(); mav.addObject("list", list); }
     */

    ArrayList<UserVO> list = userProc.list();
    mav.addObject("list", list);

    return mav;
  }

  @RequestMapping(value = "/user/read.do", method = RequestMethod.GET)
  public ModelAndView read(int userno) {
    // System.out.println("--> read(int mno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/read"); // webapp/user/read.jsp

    UserVO userVO = userProc.read(userno);
    mav.addObject("userVO", userVO);

    ArrayList<FileVO> file_list = userProc.getThumbs(userVO);
    mav.addObject("file_list", file_list);

    return mav;
  }

  @RequestMapping(value = "/user/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, UserVO userVO) {
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/user/storage");
    List<MultipartFile> filesMF = userVO.getFilesMF(); // Spring�� File ��ü��
                                                       // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String licensefile = ""; // �÷��� ������ ���ϸ�
    String licensefile_item = ""; // �ϳ��� ���ϸ�
    String usersize = "";
    long usersize_item = 0; // �ϳ��� ���� ������
    String userthumb = ""; // Thumb ���ϵ�
    String userthumb_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = filesMF.size(); // ���ε�� ���� ����

    // ������ ��� ���� ��ȸ
    UserVO userVO_old = userProc.read(userVO.getUserno());
    if (filesMF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
      // thumbs ���� ����
      String userthumb_old = userVO_old.getUserthumb();
      StringTokenizer userthumb_st = new StringTokenizer(userthumb_old, "/");
      while (userthumb_st.hasMoreTokens()) {
        String fname = upDir + userthumb_st.nextToken();
        Tool.deleteFile(fname); // ������ ��ϵ� ����� ���� ����
      }

      // ���� ���� ����
      String licensefile_old = userVO_old.getLicensefile();
      StringTokenizer licensefile_st = new StringTokenizer(licensefile_old, "/");
      while (licensefile_st.hasMoreTokens()) {
        String fname = upDir + licensefile_st.nextToken();
        Tool.deleteFile(fname); // ������ ��ϵ� ���� ���� ����
      }

      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          licensefile_item = Upload.saveFileSpring(multipartFile, upDir);
          usersize_item = multipartFile.getSize();

          if (Tool.isImage(licensefile_item)) {
            userthumb_item = Tool.preview(upDir, licensefile_item, 120, 80); // Thumb
                                                                             // �̹���
            // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            licensefile = licensefile + "/" + licensefile_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            usersize = usersize + "/" + usersize_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            userthumb = userthumb + "/" + userthumb_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            licensefile = licensefile_item; // file1.jpg
            usersize = "" + usersize_item; // 123456
            userthumb = userthumb_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------

    } else { // �۸� �����ϴ� ���, ������ ���� ���� ����
      licensefile = userVO_old.getLicensefile();
      usersize = userVO_old.getUsersize();
      userthumb = userVO_old.getUserthumb();
    }
    userVO.setLicensefile(licensefile);
    userVO.setUsersize(usersize);
    userVO.setUserthumb(userthumb);

    count = userProc.update(userVO); // ����

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("userno", userVO.getUserno()); // ȸ�� ��ȣ
    redirectAttributes.addAttribute("username", userVO.getUsername());

    mav.setViewName("redirect:/user/update_msg.jsp");

    return mav;
  }

  /**
   * �н����� ������
   * 
   * @return
   */
  @RequestMapping(value = "/user/passwd_update.do", method = RequestMethod.GET)
  public ModelAndView passwd_update() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/passwd_update"); // webapp/user/passwd_update.jsp

    // mav.addObject("mno", mno);

    return mav;
  }

  /**
   * �н����带 �����մϴ�.
   * 
   * @param request
   * @param passwd
   * @param new_passwd
   * @return
   */
  @RequestMapping(value = "/user/passwd_update.do", method = RequestMethod.POST)
  public ModelAndView passwd_update(HttpServletRequest request, HttpSession session, String userpasswd,
      String new_passwd) {
    // System.out.println("--> passwd_update() POST called.");
    ModelAndView mav = new ModelAndView();

    //String userid = "user01";
     String userid = (String)session.getAttribute("userid"); // session
    //int userno = 1;
     int userno = (Integer)session.getAttribute("userno"); // session

    // �α��� ���� �߰� ����
    // int count = memberProc.login(id, passwd); // ���� �н����� �˻�
    int count = 1;
    System.out.println("--> count: " + count);
    if (count == 1) { // �α����� ȸ���� �н����� �˻�
      int count_update = userProc.passwd_update(userno, new_passwd);
      // System.out.println("--> count_update: " + count_update);
      mav.setViewName("redirect:/user/passwd_update_msg.jsp?count=" + count_update + "&userno=" + userno);
    } else {
      mav.setViewName("redirect:/user/login.do");
    }

    return mav;
  }

  // ȸ�� ���� ��
  @RequestMapping(value = "/user/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int userno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/delete");

    UserVO userVO = userProc.read(userno);
    mav.addObject("userVO", userVO);

    return mav;
  }

  // ȸ�� ���� ó��
  @RequestMapping(value = "/user/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(RedirectAttributes redirectAttributes, int userno, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();

    String upDir = Tool.getRealPath(request, "/user/storage");

    // ������ ��� ���� ��ȸ
    UserVO userVO_old = userProc.read(userno);
    String userthumb_old = userVO_old.getUserthumb();
    StringTokenizer userthumb_st = new StringTokenizer(userthumb_old, "/");
    while (userthumb_st.hasMoreTokens()) {
      String fname = upDir + userthumb_st.nextToken();
      Tool.deleteFile(fname); // ������ ��ϵ� ����� ���� ����
    }

    // ���� ���� ����
    String licensefile_old = userVO_old.getLicensefile();
    StringTokenizer licensefile_st = new StringTokenizer(licensefile_old, "/");
    while (licensefile_st.hasMoreTokens()) {
      String fname = upDir + licensefile_st.nextToken();
      Tool.deleteFile(fname); // ������ ��ϵ� ���� ���� ����
    }

    UserVO userVO = userProc.read(userno);
    int count = userProc.delete(userno);

    redirectAttributes.addAttribute("count", count);

    mav.setViewName("redirect:/user/delete_msg.jsp");
    return mav;

  }

  /**
   * �α��� ��
   * 
   * @return
   */
  @RequestMapping(value = "/user/login.do", method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/login_ck_form"); // /webapp/user/login_ck_form.jsp

    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String ck_userid = ""; // id ���� ����
    String ck_id_save = ""; // id ���� ���θ� üũ�ϴ� ����
    String ck_userpasswd = ""; // passwd ���� ����
    String ck_passwd_save = ""; // passwd ���� ���θ� üũ�ϴ� ����

    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i]; // ��Ű ��ü ����

        if (cookie.getName().equals("ck_userid")) {
          ck_userid = cookie.getValue();
        } else if (cookie.getName().equals("ck_id_save")) {
          ck_id_save = cookie.getValue(); // Y, N
        } else if (cookie.getName().equals("ck_userpasswd")) {
          ck_userpasswd = cookie.getValue(); // 1234
        } else if (cookie.getName().equals("ck_passwd_save")) {
          ck_passwd_save = cookie.getValue(); // Y, N
        }
      }
    }

    mav.addObject("ck_userid", ck_userid);
    mav.addObject("ck_id_save", ck_id_save);
    mav.addObject("ck_userpasswd", ck_userpasswd);
    mav.addObject("ck_passwd_save", ck_passwd_save);

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
  @RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
  public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
      String userid, @RequestParam(value = "id_save", defaultValue = "") String id_save, String userpasswd,
      @RequestParam(value = "passwd_save", defaultValue = "") String passwd_save) {
    // System.out.println("--> login() POST called.");
    ModelAndView mav = new ModelAndView();

    if (userProc.login(userid, userpasswd) != 1) { // �α��� ���н�
      mav.setViewName("redirect:/user/login_msg.jsp");

    } else { // �н����� ��ġ�ϴ� ���
      UserVO userVO = userProc.readById(userid);

      session.setAttribute("userno", userVO.getUserno()); // session ���� ��ü
      session.setAttribute("userid", userid);
      session.setAttribute("userpasswd", userpasswd);
      session.setAttribute("username", userVO.getUsername());
      session.setAttribute("usertel", userVO.getUsertel());

      // -------------------------------------------------------------------
      // id ���� ��� ����
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id�� ������ ���
        Cookie ck_userid = new Cookie("ck_userid", userid);
        ck_userid.setMaxAge(60 * 60 * 72 * 10); // 30 day, �ʴ���
        response.addCookie(ck_userid);
      } else { // N, id�� �������� �ʴ� ���
        Cookie ck_userid = new Cookie("ck_userid", "");
        ck_userid.setMaxAge(0);
        response.addCookie(ck_userid);
      }
      // id�� �������� �����ϴ� CheckBox üũ ����
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password ���� ��� ����
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // �н����� ������ ���
        Cookie ck_userpasswd = new Cookie("ck_userpasswd", userpasswd);
        ck_userpasswd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_userpasswd);
      } else { // N, �н����带 �������� ���� ���
        Cookie ck_userpasswd = new Cookie("ck_userpasswd", "");
        ck_userpasswd.setMaxAge(0);
        response.addCookie(ck_userpasswd);
      }
      // passwd�� �������� �����ϴ� CheckBox üũ ����
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------

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
  @RequestMapping(value = "/user/logout.do", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, HttpSession session) {
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();

    session.invalidate(); // session ���� ��ü�� ��ϵ� ��� session ���� ����

    mav.setViewName("redirect:/user/logout_msg.jsp");

    return mav;
  }

  /**
   * ȸ��IDã�� �� http://localhost:9090/rent/user/find_id.do
   * 
   * @return
   */
  @RequestMapping(value = "/user/find_id.do", method = RequestMethod.GET)
  public ModelAndView findId() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/find_id_form");
    return mav;
  }

  /**
   * ȸ��ID��ȸ
   * 
   * @param username,
   *          usertel
   * @return
   */
  @RequestMapping(value = "/user/find_id.do", method = RequestMethod.POST)
  public ModelAndView findId(String username, String usertel) {
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/find_id_proc");

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("username", username);
    map.put("usertel", usertel);
    UserVO userVO = userProc.findId(map);
    mav.addObject("userVO", userVO);
    System.out.println("userVO:" + userVO);
    return mav;
  }

  /**
   * ȸ��PWã�� �� http://localhost:9090/rent/user/find_pw.do
   * 
   * @return
   */
  @RequestMapping(value = "/user/find_pw.do", method = RequestMethod.GET)
  public ModelAndView findPw() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/find_pw_form");
    return mav;
  }

  /**
   * ȸ��ID��ȸ
   * 
   * @param username,
   *          usertel
   * @return
   */
  @RequestMapping(value = "/user/find_pw.do", method = RequestMethod.POST)
  public ModelAndView findId(String userid, String username, String usertel) {
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/find_pw_proc");

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("username", username);
    map.put("usertel", usertel);
    map.put("userid", userid);
    UserVO userVO = userProc.findPw(map);
    mav.addObject("userVO", userVO);

    return mav;
  }

  /**
   * ��� + �˻� + ����¡ ����
   * http://localhost:9090/ojt/contents/list_by_cateno_search.do?
   * 
   * @param cateno
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/user/list_by_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_paging(@RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
      HttpSession session) {
    // System.out.println("--> list_by_category() GET called.");
    System.out.println("--> nowPage: " + nowPage);

    ModelAndView mav = new ModelAndView();
    // int userno = (int)session.getAttribute("userno");
    int adminno = (int)session.getAttribute("adminno");
    // �˻� ��� �߰�, /webapp/contents/list_by_cateno_search_paging.jsp
    mav.setViewName("/user/list_by_paging");

    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("nowPage", nowPage);
    map.put("adminno", adminno);
    // map.put("userno", userno);
    int search_count = userProc.search_count(map);
    mav.addObject("search_count", search_count);

    // �˻� ���
    List<UserVO> list = userProc.list_by_paging(map);
    mav.addObject("list", list);

    // mav.addObject("word", word);
    System.out.println("--> search_count: " + search_count);
    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� ���� ������: 11 / 22 [����] 11 12 13 14 15 16
     * 17 18 19 20 [����]
     * 
     * @param categoryno ����Ʈ ����
     * 
     * @param categoryno ī�װ���ȣ
     * 
     * @param search_count �˻�(��ü) ���ڵ��
     * 
     * @param nowPage ���� ������
     * 
     * @param word �˻���
     * 
     * @return ����¡ ���� ���ڿ�
     */
    String paging = userProc.pagingBox("list_by_paging.do", search_count, nowPage);
    System.out.println("----> search_count: " + search_count);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);

    return mav;
  }

}
