package dev.mvc.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface AdminProcInter {
  /**
   * <xmp>
   * 아이디를 이용한 회원정보 조회
   * <select id="readById" resultType="adminVO" parameterType="String">
   * </xmp>
   * @param adminid
   * @return 회원아이디에 의한 레코드
   */
  public AdminVO readById(String adminid);
  
  /**
   * <xmp>
   * 로그인
   * <select id="login"  resultType="int" parameterType="Hashmap">
   * </xmp>
   * @param map
   * @return 로그인한 레코드 갯수
   */
  public int login(String adminid, String adminpasswd);
  
  /**
   * <xmp>
   * 로그인된 관리자 계정인지 검사합니다.
   * </xmp>
   * @param request
   * @return true: 관리자
   */
  public boolean isAdmin(HttpSession session); 
}
