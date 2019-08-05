package dev.mvc.management;

import java.util.ArrayList;
import java.util.HashMap;

public interface ManagementProcInter {

  public int create(ManagementVO managementVO);
  
  public ArrayList<ManagementVO> list();
  
  
  /**
   * <xmp>
   * �˻� + ����¡ ���
   * <select id="list_paging" resultType="ManagementVO" parameterType="HashMap">
   * </xmp>
   * @param map
   * @return
   */
  public ArrayList<ManagementVO> list_paging(HashMap<String, Object> map);
  public ArrayList<ManagementVO> list_paging_search(HashMap<String, Object> map);
  
  /**
   * ������ ��� ���ڿ� ����
   * @param listFile ��� ���ϸ� 
   * @param cateno Ŀ�װ���ȣ
   * @param search_count �˻� ����
   * @param nowPage ���� ������, nowPage�� 1���� ����
   * @param word �˻���
   * @return
   */
  public String pagingBox(String listFile, int search_count, int nowPage, String carname);
  
  public int search_count(HashMap map);


  public int management_state_rent(int managementno);
  public int management_state_return(int managementno);
}
