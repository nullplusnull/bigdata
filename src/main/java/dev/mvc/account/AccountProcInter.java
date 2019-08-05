package dev.mvc.account;

import java.util.ArrayList;

public interface AccountProcInter {

  
  public int create(AccountVO accountVO);
  
  
  /**
   * <xmp>
   * 계좌 리스트 조회
   * <select id="list" resultType="AccountVO">
   * </xmp>
   * @param AccountVO
   * @return ArrayList
   */
  public ArrayList<AccountVO> list();
  
  
  /**
   * <xmp>
   * 유저 계좌 리스트 조회
   *  <select id="user_list" resultType="AccountVO">
   * </xmp>
   * @param userno
   * @return ArrayList
   */
  public ArrayList<AccountVO> user_list(int userno);
  
 
  /**
   * <xmp>
   * 단일 계좌 조회
   * <select id="read" resultType="AccountVO">
   * </xmp>
   * @param accountno
   * @return AccountVO
   */
  public AccountVO read(int accountno);
  
  
 
  /**
   * <xmp>
   * 삭제
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param int
   * @return count
   */
  public int delete(int accountno);


  /**
   * <xmp>
   * <!-- 업데이트 비밀번호 변경 -->
   * <update id="update_passwd" parameterType="AccountVO">
   * </xmp>
   * @param accountVO
   * @return count
   */
  public int update_passwd(AccountVO accountVO);
 

  /**
   * <xmp>
   * <!-- 구매 환불로 인한 금액 변경 -->
   * <update id="update_cash" parameterType="AccountVO">
   * </xmp>
   * @param accountVO
   * @return count
   */
  public int update_cash(AccountVO accountVO);
 
  
  
}




