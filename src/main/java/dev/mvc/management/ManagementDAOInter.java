package dev.mvc.management;

import java.util.ArrayList;
import java.util.HashMap;



public interface ManagementDAOInter {

  
  
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

  public int search_count(HashMap map);
  

  public int management_state_rent(int managementno);
  public int management_state_return(int managementno);
}








