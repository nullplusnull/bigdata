package dev.mvc.account;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.carrent.CarrentVO;


public interface AccountDAOInter {
  /**
   * <xmp>
   * ���� ����
   * <insert id="create" parameterType="RentperiodVO">
   * </xmp>
   * @param account
   * @return ó���Ǹ� 1����
   */
  public int create(AccountVO accountVO);
  
  
  /**
   * <xmp>
   * ���� ����Ʈ ��ȸ
   * <select id="list" resultType="AccountVO">
   * </xmp>
   * @param 
   * @return ArrayList
   */
  public ArrayList<AccountVO> list();
  
  /**
   * <xmp>
   * ���� ���� ����Ʈ ��ȸ
   *  <select id="user_list" resultType="AccountVO">
   * </xmp>
   * @param userno
   * @return ArrayList
   */
  public ArrayList<AccountVO> user_list(int userno);
  
 
  /**
   * <xmp>
   * ���� ���� ��ȸ
   * <select id="read" resultType="AccountVO">
   * </xmp>
   * @param accountno
   * @return AccountVO
   */
  public AccountVO read(int accountno);
  

  /**
   * <xmp>
   * ����
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param int
   * @return count
   */
  public int delete(int accountno);


  /**
   * <xmp>
   * <!-- ������Ʈ ��й�ȣ ���� -->
   * <update id="update_passwd" parameterType="AccountVO">
   * </xmp>
   * @param accountVO
   * @return count
   */
  public int update_passwd(AccountVO accountVO);
 

  /**
   * <xmp>
   * <!-- ���� ȯ�ҷ� ���� �ݾ� ���� -->
   * <update id="update_cash" parameterType="AccountVO">
   * </xmp>
   * @param accountVO
   * @return count
   */
  public int update_cash(AccountVO accountVO);
 

  
}






















