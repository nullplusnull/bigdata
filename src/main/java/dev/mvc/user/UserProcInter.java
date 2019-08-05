package dev.mvc.user;

import java.util.ArrayList;
import java.util.HashMap;
import dev.mvc.carrent.FileVO;

import javax.servlet.http.HttpSession;

public interface UserProcInter {
  
  /**
   * <xmp>
   * 아이디 중복 검사
   * <select id="checkId" resultType="int" parameterType="String">
   * </xmp>
   * @param userid
   * @return 중복된 레코드 갯수
   */
  public int checkId(String userid);
  
  public int checkTel(String usertel);
  
  /**
   * <xmp>
   * 회원가입
   * <insert id="create" parameterType="userVO">
   * </xmp>
   * @param userVO
   * @return 등록된 레코드 갯수
   */
  public int create(UserVO userVO);
  
  /**
   * <xmp>
   * 전체 회원 목록
   * <select id="list" resultType="userVO">
   * </xmp>
   * @return 검색된 레코드들
   */
  public ArrayList<UserVO> list();
  
  /**
   * <xmp>
   * 회원정보 조회
   * <select id="read" resultType="userVO" parameterType="int">
   * </xmp>
   * @param userno
   * @return 회원번호에 의한 레코드
   */
  public UserVO read(int userno);
  
  /**
   * 파일명의 갯수만큼 FileVO 객체를 만들어 리턴
   * @param contentsVO
   * @return 
   */
  public ArrayList<FileVO> getThumbs(UserVO userVO); 
  /**
   * <xmp>
   * 아이디를 이용한 회원정보 조회
   * <select id="readById" resultType="userVO" parameterType="String">
   * </xmp>
   * @param userid
   * @return 회원아이디에 의한 레코드
   */
  public UserVO readById(String userid);
  
  /**
   * <xmp>
   * 회원ID 조회
   * <select id="findId" resultType="userVO" parameterType="String">
   * </xmp>
   * @param username, usertel
   * @return 
   */
  public UserVO findId(HashMap<String, Object> map);
  
  /**
   * <xmp>
   * 회원PW 조회
   * <select id="findId" resultType="userVO" parameterType="String">
   * </xmp>
   * @param username, usertel, userid
   * @return 
   */
  public UserVO findPw(HashMap<String, Object> map);
  
  /**
   * <xmp>
   * 회원정보 수정
   * <update id="update" parameterType="userVO">
   * </xmp>
   * @param userVO
   * @return 수정된 레코드 갯수
   */
  public int update(UserVO userVO);
  
  /**
   * <xmp>
   * 회원 비밀번호 수정
   * <update id="passwd_update" parameterType="Hashmap" >
   * </xmp>
   * @param map
   * @return 수정된 레코드 갯수
   */
  public int passwd_update(int userno, String new_passwd);
    
  /**
   * <xmp>
   * 회원 삭제
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param userno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int userno);
  
  /**
   * <xmp>
   * 로그인
   * <select id="login"  resultType="int" parameterType="Hashmap">
   * </xmp>
   * @param map
   * @return 로그인한 레코드 갯수
   */
  public int login(String userid, String userpasswd);
  
  /**
   * <xmp>
   * 로그인된 회원 계정인지 검사합니다.
   * </xmp>
   * @param request
   * @return true: 관리자
   */
  public boolean isMember(HttpSession session); 
 
  /**
   * 페이징 목록 문자열 생성 
   * @param listFile 목록 파일명
   * @param cateno
   * @param search_count 검색 갯수
   * @param nowPage 현재 페이지
   * @param word 검색어
   * @return
   */
  public String pagingBox(String listFile, int search_count, int nowPage);
  /**
   * <xmp>
   * 카테고리별 검색된 목록 + 페이징
   * <select id="list_by_cateno_search_paging" resultType="ContentsVO" parameterType="HashMap">
   * </xmp> 
   * @param 
   * @return ArrayList<ContentsVO>
   */
  public ArrayList<UserVO> list_by_paging(HashMap<String, Object> map);
  
  /**
   * cate별 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @param map
   * @return
   */
  public int search_count(HashMap map);
}
