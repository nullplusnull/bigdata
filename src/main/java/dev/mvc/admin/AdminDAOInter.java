package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminDAOInter {
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
  public int login(HashMap<String, Object> map);
  
  
}
