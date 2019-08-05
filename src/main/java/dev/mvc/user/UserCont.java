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
   * 중복 ID 검사, JSON http://localhost:9090/ojt/user/checkId.do?id=user01
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
   * 회원 가입 폼 http://localhost:9090/rent/user/create.do
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
  // categrpVO는 form 태그의 값으로 자동 저장됨
  @RequestMapping(value = "/user/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, UserVO userVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/user/storage");
    // Spring이 FIle 객체를 저장해둠, 개발자는 이용만 함
    List<MultipartFile> filesMF = userVO.getFilesMF(); // Spring이 File 객체를
                                                       // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String licensefile = ""; // 컬럼에 저장할 파일명
    String licensefile_item = ""; // 하나의 파일명
    String usersize = "";
    long usersize_item = 0; // 하나의 파일 사이즈
    String userthumb = ""; // Thumb 파일들
    String userthumb_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
    System.out.println("--> 업로드된 파일 갯수 count: " + filesMF.size());

    if (count > 0) { // 전송 파일이 존재한다면
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0:첫번째 파일 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          licensefile_item = Upload.saveFileSpring(multipartFile, upDir); // 서버에
                                                                          // 파일
                                                                          // 저장
          usersize_item = multipartFile.getSize();

          if (Tool.isImage(licensefile_item)) { // 이미지인지 검사
            userthumb_item = Tool.preview(upDir, licensefile_item, 120, 80); // Thumb
                                                                             // 이미지
            // 생성
          }
          // 1개 이상의 다중 파일 처리
          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            licensefile = licensefile + "/" + licensefile_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            usersize = usersize + "/" + usersize_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            userthumb = userthumb + "/" + userthumb_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
            // 파일이 없어도 파일 객체가 1개 생성되서 크기 체크
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
    // 파일 전송 코드 종료
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
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/user/storage");
    List<MultipartFile> filesMF = userVO.getFilesMF(); // Spring이 File 객체를
                                                       // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String licensefile = ""; // 컬럼에 저장할 파일명
    String licensefile_item = ""; // 하나의 파일명
    String usersize = "";
    long usersize_item = 0; // 하나의 파일 사이즈
    String userthumb = ""; // Thumb 파일들
    String userthumb_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // 기존의 등록 정보 조회
    UserVO userVO_old = userProc.read(userVO.getUserno());
    if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String userthumb_old = userVO_old.getUserthumb();
      StringTokenizer userthumb_st = new StringTokenizer(userthumb_old, "/");
      while (userthumb_st.hasMoreTokens()) {
        String fname = upDir + userthumb_st.nextToken();
        Tool.deleteFile(fname); // 기존에 등록된 썸네일 파일 삭제
      }

      // 원본 파일 삭제
      String licensefile_old = userVO_old.getLicensefile();
      StringTokenizer licensefile_st = new StringTokenizer(licensefile_old, "/");
      while (licensefile_st.hasMoreTokens()) {
        String fname = upDir + licensefile_st.nextToken();
        Tool.deleteFile(fname); // 기존에 등록된 원본 파일 삭제
      }

      // --------------------------------------------
      // 새로운 파일의 등록 시작
      // --------------------------------------------
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          licensefile_item = Upload.saveFileSpring(multipartFile, upDir);
          usersize_item = multipartFile.getSize();

          if (Tool.isImage(licensefile_item)) {
            userthumb_item = Tool.preview(upDir, licensefile_item, 120, 80); // Thumb
                                                                             // 이미지
            // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            licensefile = licensefile + "/" + licensefile_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            usersize = usersize + "/" + usersize_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            userthumb = userthumb + "/" + userthumb_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            licensefile = licensefile_item; // file1.jpg
            usersize = "" + usersize_item; // 123456
            userthumb = userthumb_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // 새로운 파일의 등록 종료
      // --------------------------------------------

    } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
      licensefile = userVO_old.getLicensefile();
      usersize = userVO_old.getUsersize();
      userthumb = userVO_old.getUserthumb();
    }
    userVO.setLicensefile(licensefile);
    userVO.setUsersize(usersize);
    userVO.setUserthumb(userthumb);

    count = userProc.update(userVO); // 수정

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("userno", userVO.getUserno()); // 회원 번호
    redirectAttributes.addAttribute("username", userVO.getUsername());

    mav.setViewName("redirect:/user/update_msg.jsp");

    return mav;
  }

  /**
   * 패스워드 변경폼
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
   * 패스워드를 변경합니다.
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

    // 로그인 관련 추가 영역
    // int count = memberProc.login(id, passwd); // 현재 패스워드 검사
    int count = 1;
    System.out.println("--> count: " + count);
    if (count == 1) { // 로그인한 회원의 패스워드 검사
      int count_update = userProc.passwd_update(userno, new_passwd);
      // System.out.println("--> count_update: " + count_update);
      mav.setViewName("redirect:/user/passwd_update_msg.jsp?count=" + count_update + "&userno=" + userno);
    } else {
      mav.setViewName("redirect:/user/login.do");
    }

    return mav;
  }

  // 회원 삭제 폼
  @RequestMapping(value = "/user/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int userno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/delete");

    UserVO userVO = userProc.read(userno);
    mav.addObject("userVO", userVO);

    return mav;
  }

  // 회원 삭제 처리
  @RequestMapping(value = "/user/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(RedirectAttributes redirectAttributes, int userno, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();

    String upDir = Tool.getRealPath(request, "/user/storage");

    // 기존의 등록 정보 조회
    UserVO userVO_old = userProc.read(userno);
    String userthumb_old = userVO_old.getUserthumb();
    StringTokenizer userthumb_st = new StringTokenizer(userthumb_old, "/");
    while (userthumb_st.hasMoreTokens()) {
      String fname = upDir + userthumb_st.nextToken();
      Tool.deleteFile(fname); // 기존에 등록된 썸네일 파일 삭제
    }

    // 원본 파일 삭제
    String licensefile_old = userVO_old.getLicensefile();
    StringTokenizer licensefile_st = new StringTokenizer(licensefile_old, "/");
    while (licensefile_st.hasMoreTokens()) {
      String fname = upDir + licensefile_st.nextToken();
      Tool.deleteFile(fname); // 기존에 등록된 원본 파일 삭제
    }

    UserVO userVO = userProc.read(userno);
    int count = userProc.delete(userno);

    redirectAttributes.addAttribute("count", count);

    mav.setViewName("redirect:/user/delete_msg.jsp");
    return mav;

  }

  /**
   * 로그인 폼
   * 
   * @return
   */
  @RequestMapping(value = "/user/login.do", method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/login_ck_form"); // /webapp/user/login_ck_form.jsp

    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String ck_userid = ""; // id 저장 변수
    String ck_id_save = ""; // id 저장 여부를 체크하는 변수
    String ck_userpasswd = ""; // passwd 저장 변수
    String ck_passwd_save = ""; // passwd 저장 여부를 체크하는 변수

    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i]; // 쿠키 객체 추출

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
  @RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
  public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
      String userid, @RequestParam(value = "id_save", defaultValue = "") String id_save, String userpasswd,
      @RequestParam(value = "passwd_save", defaultValue = "") String passwd_save) {
    // System.out.println("--> login() POST called.");
    ModelAndView mav = new ModelAndView();

    if (userProc.login(userid, userpasswd) != 1) { // 로그인 실패시
      mav.setViewName("redirect:/user/login_msg.jsp");

    } else { // 패스워드 일치하는 경우
      UserVO userVO = userProc.readById(userid);

      session.setAttribute("userno", userVO.getUserno()); // session 내부 객체
      session.setAttribute("userid", userid);
      session.setAttribute("userpasswd", userpasswd);
      session.setAttribute("username", userVO.getUsername());
      session.setAttribute("usertel", userVO.getUsertel());

      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id를 저장할 경우
        Cookie ck_userid = new Cookie("ck_userid", userid);
        ck_userid.setMaxAge(60 * 60 * 72 * 10); // 30 day, 초단위
        response.addCookie(ck_userid);
      } else { // N, id를 저장하지 않는 경우
        Cookie ck_userid = new Cookie("ck_userid", "");
        ck_userid.setMaxAge(0);
        response.addCookie(ck_userid);
      }
      // id를 저장할지 선택하는 CheckBox 체크 여부
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_userpasswd = new Cookie("ck_userpasswd", userpasswd);
        ck_userpasswd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_userpasswd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_userpasswd = new Cookie("ck_userpasswd", "");
        ck_userpasswd.setMaxAge(0);
        response.addCookie(ck_userpasswd);
      }
      // passwd를 저장할지 선택하는 CheckBox 체크 여부
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------

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
  @RequestMapping(value = "/user/logout.do", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, HttpSession session) {
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();

    session.invalidate(); // session 내부 객체의 등록된 모든 session 변수 삭제

    mav.setViewName("redirect:/user/logout_msg.jsp");

    return mav;
  }

  /**
   * 회원ID찾기 폼 http://localhost:9090/rent/user/find_id.do
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
   * 회원ID조회
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
   * 회원PW찾기 폼 http://localhost:9090/rent/user/find_pw.do
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
   * 회원ID조회
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
   * 목록 + 검색 + 페이징 지원
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
    // 검색 기능 추가, /webapp/contents/list_by_cateno_search_paging.jsp
    mav.setViewName("/user/list_by_paging");

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("nowPage", nowPage);
    map.put("adminno", adminno);
    // map.put("userno", userno);
    int search_count = userProc.search_count(map);
    mav.addObject("search_count", search_count);

    // 검색 목록
    List<UserVO> list = userProc.list_by_paging(map);
    mav.addObject("list", list);

    // mav.addObject("word", word);
    System.out.println("--> search_count: " + search_count);
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16
     * 17 18 19 20 [다음]
     * 
     * @param categoryno 리스트 파일
     * 
     * @param categoryno 카테고리번호
     * 
     * @param search_count 검색(전체) 레코드수
     * 
     * @param nowPage 현재 페이지
     * 
     * @param word 검색어
     * 
     * @return 페이징 생성 문자열
     */
    String paging = userProc.pagingBox("list_by_paging.do", search_count, nowPage);
    System.out.println("----> search_count: " + search_count);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);

    return mav;
  }

}
