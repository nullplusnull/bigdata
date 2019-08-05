package dev.mvc.user;

import java.util.ArrayList;
import java.util.HashMap;
import dev.mvc.carrent.FileVO;

import javax.servlet.http.HttpSession;

public interface UserProcInter {
  
  /**
   * <xmp>
   * ���̵� �ߺ� �˻�
   * <select id="checkId" resultType="int" parameterType="String">
   * </xmp>
   * @param userid
   * @return �ߺ��� ���ڵ� ����
   */
  public int checkId(String userid);
  
  public int checkTel(String usertel);
  
  /**
   * <xmp>
   * ȸ������
   * <insert id="create" parameterType="userVO">
   * </xmp>
   * @param userVO
   * @return ��ϵ� ���ڵ� ����
   */
  public int create(UserVO userVO);
  
  /**
   * <xmp>
   * ��ü ȸ�� ���
   * <select id="list" resultType="userVO">
   * </xmp>
   * @return �˻��� ���ڵ��
   */
  public ArrayList<UserVO> list();
  
  /**
   * <xmp>
   * ȸ������ ��ȸ
   * <select id="read" resultType="userVO" parameterType="int">
   * </xmp>
   * @param userno
   * @return ȸ����ȣ�� ���� ���ڵ�
   */
  public UserVO read(int userno);
  
  /**
   * ���ϸ��� ������ŭ FileVO ��ü�� ����� ����
   * @param contentsVO
   * @return 
   */
  public ArrayList<FileVO> getThumbs(UserVO userVO); 
  /**
   * <xmp>
   * ���̵� �̿��� ȸ������ ��ȸ
   * <select id="readById" resultType="userVO" parameterType="String">
   * </xmp>
   * @param userid
   * @return ȸ�����̵� ���� ���ڵ�
   */
  public UserVO readById(String userid);
  
  /**
   * <xmp>
   * ȸ��ID ��ȸ
   * <select id="findId" resultType="userVO" parameterType="String">
   * </xmp>
   * @param username, usertel
   * @return 
   */
  public UserVO findId(HashMap<String, Object> map);
  
  /**
   * <xmp>
   * ȸ��PW ��ȸ
   * <select id="findId" resultType="userVO" parameterType="String">
   * </xmp>
   * @param username, usertel, userid
   * @return 
   */
  public UserVO findPw(HashMap<String, Object> map);
  
  /**
   * <xmp>
   * ȸ������ ����
   * <update id="update" parameterType="userVO">
   * </xmp>
   * @param userVO
   * @return ������ ���ڵ� ����
   */
  public int update(UserVO userVO);
  
  /**
   * <xmp>
   * ȸ�� ��й�ȣ ����
   * <update id="passwd_update" parameterType="Hashmap" >
   * </xmp>
   * @param map
   * @return ������ ���ڵ� ����
   */
  public int passwd_update(int userno, String new_passwd);
    
  /**
   * <xmp>
   * ȸ�� ����
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param userno
   * @return ������ ���ڵ� ����
   */
  public int delete(int userno);
  
  /**
   * <xmp>
   * �α���
   * <select id="login"  resultType="int" parameterType="Hashmap">
   * </xmp>
   * @param map
   * @return �α����� ���ڵ� ����
   */
  public int login(String userid, String userpasswd);
  
  /**
   * <xmp>
   * �α��ε� ȸ�� �������� �˻��մϴ�.
   * </xmp>
   * @param request
   * @return true: ������
   */
  public boolean isMember(HttpSession session); 
 
  /**
   * ����¡ ��� ���ڿ� ���� 
   * @param listFile ��� ���ϸ�
   * @param cateno
   * @param search_count �˻� ����
   * @param nowPage ���� ������
   * @param word �˻���
   * @return
   */
  public String pagingBox(String listFile, int search_count, int nowPage);
  /**
   * <xmp>
   * ī�װ��� �˻��� ��� + ����¡
   * <select id="list_by_cateno_search_paging" resultType="ContentsVO" parameterType="HashMap">
   * </xmp> 
   * @param 
   * @return ArrayList<ContentsVO>
   */
  public ArrayList<UserVO> list_by_paging(HashMap<String, Object> map);
  
  /**
   * cate�� �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @param map
   * @return
   */
  public int search_count(HashMap map);
}
