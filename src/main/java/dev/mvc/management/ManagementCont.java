package dev.mvc.management;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.carrent.CarrentProcInter;
import dev.mvc.carrent.CarrentVO;
import dev.mvc.rentperiod.RentperiodProcInter;
import dev.mvc.rentperiod.RentperiodVO;
import dev.mvc.reservation.ReservationProcInter;
import dev.mvc.reservation.ReservationVO;

@Controller
public class ManagementCont {
  
  @Autowired
  @Qualifier("dev.mvc.management.ManagementProc")
  private ManagementProcInter managementProc;
  
  @Autowired
  @Qualifier("dev.mvc.carrent.CarrentProc")
  private CarrentProcInter carrentProc;
  
  @Autowired
  @Qualifier("dev.mvc.rentperiod.RentperiodProc")
  private RentperiodProcInter rentperiodProc;
  
  
  @RequestMapping(value="/management/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<ManagementVO> list = managementProc.list();
    mav.addObject("list", list);
    mav.setViewName("/management/list");
    return mav;
  }
  
//  
//  @RequestMapping(value = "/management/list_paging.do", 
//      method = RequestMethod.GET)
//public ModelAndView list_paging(
//        @RequestParam(value="nowPage", defaultValue="1") int nowPage) { 
//    ModelAndView mav = new ModelAndView();
//    
//    
// // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
//    HashMap<String, Object> map = new HashMap<String, Object>();
//    map.put("nowPage", nowPage);  
//    
//    List<ManagementVO> list = managementProc.list_paging(map);
//    mav.addObject("list", list);
//    
//    // 검색된 레코드 개수
//    int search_count = managementProc.search_count(map);
//    mav.addObject("search_count", search_count);
//    
//    String paging = managementProc.pagingBox("list_paging.do", search_count, nowPage);
//    mav.addObject("paging", paging);
//    mav.addObject("nowPage", nowPage);
//    
//    mav.setViewName("/management/list");
//    return mav;
//  }
//  
  
  @RequestMapping(value="/management/update_rent.do", method=RequestMethod.GET)
  @ResponseBody
  public String update_rent(int carrentno, int managementno) {
    JSONObject json = new JSONObject();
    int resultno = 1; 
    int count1 = carrentProc.update_rent(carrentno);
    int count2 = managementProc.management_state_rent(managementno);
    if(count1 == 0 || count2 == 0) {
      // 업데이트 에러
      resultno = 0;
      json.put("resultno", resultno);
      return json.toString();
    }
    json.put("resultno", resultno);
    return json.toString();
  }
  
  
  @RequestMapping(value="/management/return_rent.do", method=RequestMethod.GET)
  @ResponseBody
  public String return_rent(int carrentno, int managementno, int rentno) {
    JSONObject json = new JSONObject();
    int resultno = 1;
    int count1 = carrentProc.update_return(carrentno);
    int count2 = managementProc.management_state_return(managementno);
    int count3 = rentperiodProc.schedule_update(rentno);

    if(count1 == 0 || count2 == 0 || count3 == 0) {
      // 업데이트 에러
      resultno = 0;
      json.put("resultno", resultno);
      return json.toString();
    }
    json.put("resultno", resultno);
    return json.toString();
  }
  
  
  
  
  @RequestMapping(value = "/management/list_paging_search.do", 
      method = RequestMethod.GET)
public ModelAndView list_paging_search(
        @RequestParam(value="nowPage", defaultValue="1") int nowPage, 
        @RequestParam(value="carname", defaultValue="") String carname,
        @RequestParam(value="startplace", defaultValue="") String startplace,
        @RequestParam(value="endplace", defaultValue="") String endplace,
        @RequestParam(value="userno", defaultValue="") String userno,
        @RequestParam(value="word", defaultValue="") String word,
        @RequestParam(value="col", defaultValue="carname") String col) { 
    ModelAndView mav = new ModelAndView();
    
    

    System.out.println("nowPage"+nowPage);
    System.out.println("carname"+carname);
    System.out.println("startplace"+startplace);
    System.out.println("endplace"+endplace);
    System.out.println("userno"+userno);
    System.out.println("word"+word);
    System.out.println("col : " +col);
    
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("nowPage", nowPage);
    map.put("carname", carname);
    map.put("startplace", startplace);
    map.put("endplace", endplace);
    map.put("userno", userno);
    map.put("word", word);
    map.put("col", col);
    
    
    List<ManagementVO> list = managementProc.list_paging_search(map);
    mav.addObject("list", list);
    
    // 검색된 레코드 개수
    int search_count = managementProc.search_count(map);
    mav.addObject("search_count", search_count);
    
    String paging = managementProc.pagingBox("list_paging_search.do", search_count, nowPage, carname);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    
    mav.addObject(carname);
    mav.addObject(startplace);
    mav.addObject(endplace);
    mav.addObject(userno);
    mav.addObject(word);
    mav.addObject(col);
    
    mav.setViewName("/management/list");
    return mav;
  }
  
  
  
}














