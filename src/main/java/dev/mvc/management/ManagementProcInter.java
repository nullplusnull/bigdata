package dev.mvc.management;

import java.util.ArrayList;
import java.util.HashMap;

public interface ManagementProcInter {

  public int create(ManagementVO managementVO);
  
  public ArrayList<ManagementVO> list();
  
  
  /**
   * <xmp>
   * 검색 + 페이징 목록
   * <select id="list_paging" resultType="ManagementVO" parameterType="HashMap">
   * </xmp>
   * @param map
   * @return
   */
  public ArrayList<ManagementVO> list_paging(HashMap<String, Object> map);
  public ArrayList<ManagementVO> list_paging_search(HashMap<String, Object> map);
  
  /**
   * 페이지 목록 문자열 생성
   * @param listFile 목록 파일명 
   * @param cateno 커테고리번호
   * @param search_count 검색 갯수
   * @param nowPage 현재 페이지, nowPage는 1부터 시작
   * @param word 검색어
   * @return
   */
  public String pagingBox(String listFile, int search_count, int nowPage, String carname);
  
  public int search_count(HashMap map);


  public int management_state_rent(int managementno);
  public int management_state_return(int managementno);
}
