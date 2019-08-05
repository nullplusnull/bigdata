package dev.mvc.management;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component("dev.mvc.management.ManagementProc")
public class ManagementProc implements ManagementProcInter {

  @Autowired
  private ManagementDAOInter managementDAO;
  
  public ManagementProc() {
    System.out.println("--> ManagementProc create");
  }
  
  @Override
  public int create(ManagementVO managementVO) {
    int count = 0;
    count = managementDAO.create(managementVO);
    return count;
  }

  @Override
  public ArrayList<ManagementVO> list() {
    ArrayList<ManagementVO> list = managementDAO.list();
    return list;
  }

  @Override
  public ArrayList<ManagementVO> list_paging(HashMap<String, Object> map) {
    /* 
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지: nowPage = 1, (1 - 1) * 10 --> 0 
    2 페이지: nowPage = 2, (2 - 1) * 10 --> 10
    3 페이지: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)map.get("nowPage") - 1) * Management.RECORD_PER_PAGE;
   
    // 시작 rownum, 1 페이지: 1 / 2 페이지: 11 / 3 페이지: 21 
   int startNum = beginOfPage + 1; 
   //  종료 rownum, 1 페이지: 10 / 2 페이지: 20 / 3 페이지: 30
   int endNum = beginOfPage + Management.RECORD_PER_PAGE;   
   /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
   map.put("startNum", startNum);
   map.put("endNum", endNum);
   
    ArrayList<ManagementVO> list = managementDAO.list_paging(map);
  
    
    return list;
  }

  @Override
  public String pagingBox(String listFile, int search_count, int nowPage, String carname) {
    int totalPage = (int)(Math.ceil((double)search_count/Management.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Management.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Management.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Management.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Management.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 

    // 이전 10개 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 20
    int _nowPage = (nowGrp-1) * Management.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./"+listFile+"?&nowPage="+_nowPage +"&carname="+carname  +"'>이전</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='./"+listFile+"?&nowPage="+i +"&carname="+carname +"'>"+i+"</A></span>");   
      } 
    } 

    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 21
    _nowPage = (nowGrp * Management.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./"+listFile+"?&nowPage="+_nowPage   +"&carname="+carname   +"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public int search_count(HashMap map) {
    int count = managementDAO.search_count(map);
    return count;
  }

  @Override
  public int management_state_rent(int managementno) {
    int count = 0;
    count = managementDAO.management_state_rent(managementno);
    return count;
  }

  @Override
  public int management_state_return(int managementno) {
    int count = 0;
    count = managementDAO.management_state_return(managementno);
    return count;
  }

  
  
  @Override
  public ArrayList<ManagementVO> list_paging_search(HashMap<String, Object> map) {
    
    /* 
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지: nowPage = 1, (1 - 1) * 10 --> 0 
    2 페이지: nowPage = 2, (2 - 1) * 10 --> 10
    3 페이지: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)map.get("nowPage") - 1) * Management.RECORD_PER_PAGE;
   
    // 시작 rownum, 1 페이지: 1 / 2 페이지: 11 / 3 페이지: 21 
   int startNum = beginOfPage + 1; 
   //  종료 rownum, 1 페이지: 10 / 2 페이지: 20 / 3 페이지: 30
   int endNum = beginOfPage + Management.RECORD_PER_PAGE;   
   /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
   map.put("startNum", startNum);
   map.put("endNum", endNum);
   
    ArrayList<ManagementVO> list = managementDAO.list_paging_search(map);
    return list;
  }

}






