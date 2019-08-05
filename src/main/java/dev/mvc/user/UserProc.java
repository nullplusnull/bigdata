package dev.mvc.user;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;
import dev.mvc.carrent.FileVO;

@Component("dev.mvc.user.UserProc")
public class UserProc implements UserProcInter{

  @Autowired
  private UserDAOInter UserDAOInter;
  
  public UserProc(){
    System.out.println("--> UserProc created.");
  }
  
  /**
   * �̹��� ����߿� ù��° �̹��� ���ϸ��� �����Ͽ� ����
   * @param contentsVO
   * @return
   */
  public String getUserthumb(UserVO userVO) {
    String userthumbs = userVO.getUserthumb();
    String userthumb = "";
    
    if (userthumbs != null) {
      String[] userthumbs_array = userthumbs.split("/");
      int count = userthumbs_array.length;
      
      if (count > 0) {
        userthumb = userthumbs_array[0];    
      }
    }
    // System.out.println("thumb: " + thumb);
    return userthumb;
  }
  
  /**
   * ���ϸ��� ������ŭ FileVO ��ü�� ����� ����
   * @param contentsVO
   * @return 
   */
  public ArrayList<FileVO> getThumbs(UserVO userVO) {
    ArrayList<FileVO> file_list = new ArrayList<FileVO>();
    
    String userthumb = userVO.getUserthumb(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String licensefile = userVO.getLicensefile();          // xmas01_2.jpg/xmas02_2.jpg...
    String usersize = userVO.getUsersize();        // 272558/404087... 
    
    String[] userthumb_array = userthumb.split("/");  // Thumbs
    String[] licensefile_array = licensefile.split("/");   // ���ϸ� ����
    String[] usersize_array = usersize.split("/"); // ���� ������
 
    int count = usersize_array.length;
    // System.out.println("sizes_array.length: " + sizes_array.length);
    // System.out.println("sizes: " + sizes);
    // System.out.println("files: " + files);
 
    if (licensefile.length() > 0) {
      for (int index = 0; index < count; index++) {
        usersize_array[index] = Tool.unit(new Integer(usersize_array[index]));  // 1024 -> 1KB
      
        FileVO fileVO = new FileVO(userthumb_array[index], licensefile_array[index], usersize_array[index]);
        file_list.add(fileVO);
      }
    } 
 
    return file_list;
  }
  
  @Override
  public int checkId(String userid) {
    int count = UserDAOInter.checkId(userid);
    return count;
  }
  
  @Override
  public int checkTel(String usertel) {
    int count = UserDAOInter.checkTel(usertel);
    return count;
  }

  @Override
  public int create(UserVO userVO) {
    int count = UserDAOInter.create(userVO);
    return count;
  }
  
  @Override
  public ArrayList<UserVO> list() {
    ArrayList<UserVO> list = UserDAOInter.list();
    return list;
  }

  @Override
  public UserVO read(int userno) {
    UserVO userVO = UserDAOInter.read(userno);
    return userVO;
  }

  @Override
  public UserVO readById(String userid) {
    UserVO userVO = UserDAOInter.readById(userid);
    return userVO;
  }
  


  @Override
  public int update(UserVO userVO) {
    int count = UserDAOInter.update(userVO);
    return count;
  }

  @Override
  public int passwd_update(int userno, String new_passwd) {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("userno", userno);
    map.put("userpasswd", new_passwd);
    int count = UserDAOInter.passwd_update(map);
    return count;
  }

  @Override
  public int delete(int userno) {
    int count = UserDAOInter.delete(userno);
    return count;
  }

  @Override
  public int login(String userid, String userpasswd) {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("userid", userid);
    map.put("userpasswd", userpasswd);
    int count = UserDAOInter.login(map);
    return count;
  }

  public boolean isMember(HttpSession session){
    boolean sw = false;
    
    String userid = (String)session.getAttribute("userid");
    
    if (userid != null){
      sw = true;
    }
    return sw;
  }

  @Override
  public UserVO findId(HashMap map) {
    UserVO userVO = UserDAOInter.findId(map);
    return userVO;
  }

  @Override
  public UserVO findPw(HashMap map) {
    UserVO userVO = UserDAOInter.findPw(map);
    return userVO;
  }

  @Override
  public int search_count(HashMap map) {
    int count = UserDAOInter.search_count(map);
    return count;
  }

  @Override
  public ArrayList<UserVO> list_by_paging(HashMap<String, Object> map) {
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)map.get("nowPage") - 1) * UserPaging.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + UserPaging.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   map.put("startNum", startNum);
   map.put("endNum", endNum);
    
    ArrayList<UserVO> list = UserDAOInter.list_by_paging(map);
    
    int count = list.size();
    for (int i=0; i < count; i++) {
      UserVO userVO = list.get(i);
      String thumb = getUserthumb(userVO);
      userVO.setUserthumb(thumb);
    }
    
    return list;
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   * @param listFile ��� ���ϸ�
   * @param cateno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String pagingBox(String listFile, int search_count, int nowPage){ 
    int totalPage = (int)(Math.ceil((double)search_count/UserPaging.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/UserPaging.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/UserPaging.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * UserPaging.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * UserPaging.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
    System.out.println("search_count:" + search_count);
    System.out.println("totalPage:" + totalPage);
    System.out.println("totalGrp:" + totalGrp);
    System.out.println("nowGrp:" + nowGrp);
    System.out.println("startPage:" + startPage);
    System.out.println("endPage:" + endPage);
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    // ���� 10�� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 2�׷��� ���: (2 - 1) * 10 = 1�׷��� 10
    // ���� 3�׷��� ���: (3 - 1) * 10 = 2�׷��� 20
    int _nowPage = (nowGrp-1) * UserPaging.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./" + listFile +"?nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./" + listFile +"?nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp * UserPaging.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./" + listFile + "?nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  
}
