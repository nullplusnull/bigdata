package dev.mvc.carrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.rentperiod.RentperiodProcInter;
import dev.mvc.reservation.ReservationProcInter;
import nation.web.tool.Tool;
import nation.web.tool.Upload;





@Controller
public class CarrentCont {
  
  @Autowired
  @Qualifier("dev.mvc.carrent.CarrentProc")
  private CarrentProcInter carrentProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.reservation.ReservationProc")
  private ReservationProcInter reservationProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.rentperiod.RentperiodProc")
  private RentperiodProcInter rentperiodProc = null;
  
  public CarrentCont() {
    System.out.println("--> CarrentCont created.");
  }
  /**
   * 등록 폼 http://localhost:9090/ojt/carrent/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/carrent/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/carrent/create"); // /webapp/carrent/create.jsp
 
    
    return mav;
  }
  
  /**
   * 등록 처리
   * 파일 태그의 연결
   * <input type="file" name='filesMF' id='filesMF' size='40' multiple="multiple">
   * 
   * private List<MultipartFile> filesMF;
   * @param request
   * @param carrentVO
   * @return
   */
  @RequestMapping(value = "/carrent/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, CarrentVO carrentVO) {
    // System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    
    // 옵션 NULL이면 N으로 바꿔줌
    if(carrentVO.getBlackbox() == null) {
      carrentVO.setBlackbox("N");
    }
    if(carrentVO.getNavigation() == null) {
      carrentVO.setNavigation("N");
    }
    if(carrentVO.getAiringseat() == null) {
      carrentVO.setAiringseat("N");
    }


 
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/carrent/storage");
    // Spring이 FIle 객체를 저장해둠, 개발자는 이용만 함
    List<MultipartFile> filesMF = carrentVO.getRent_file1MF(); // Spring이 File 객체를
                                                           // 저장해둠.
 
    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());
 
    String rent_file1 = ""; // 컬럼에 저장할 파일명
    String rent_file1_item = ""; // 하나의 파일명
    String rent_size1 = "";
    long rent_size1_item = 0; // 하나의 파일 사이즈
    String rent_thumb1 = ""; // Thumb 파일들
    String rent_thumb1_item = ""; // 하나의 Thumb 파일명
 
    int count = filesMF.size(); // 업로드된 파일 갯수
 
    // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
    // System.out.println("--> 업로드된 파일 갯수 count: " + count);
 
    if (count > 0) { // 전송 파일이 존재한다면
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0:첫번째 파일 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());
 
        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          rent_file1_item = Upload.saveFileSpring(multipartFile, upDir); // 서버에 파일 저장
          rent_size1_item = multipartFile.getSize();
 
          if (Tool.isImage(rent_file1_item)) { // 이미지인지 검사
            rent_thumb1_item = Tool.preview(upDir, rent_file1_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }
          // 1개 이상의 다중 파일 처리
          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            rent_file1 = rent_file1 + "/" + rent_file1_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            rent_size1 = rent_size1 + "/" + rent_size1_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            rent_thumb1 = rent_thumb1 + "/" + rent_thumb1_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
          // 파일이 없어도 파일 객체가 1개 생성되서 크기 체크
            rent_file1 = rent_file1_item; // file1.jpg
            rent_size1 = "" + rent_size1_item; // 123456
            rent_thumb1 = rent_thumb1_item; // file1_t.jpg
            System.out.println("rent_thumb1 : " +rent_thumb1);
          }
        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    carrentVO.setRent_file1(rent_file1);
    carrentVO.setRent_size1(rent_size1);
    carrentVO.setRent_thumb1(rent_thumb1);
  
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    count = carrentProc.create(carrentVO);
    
 
    mav.setViewName(
        "redirect:/carrent/create_msg.jsp?count=" + count + "&carrentno=" + carrentVO.getCarrentno()); // /webapp/contents/create_msg.jsp
 
    // mav.setViewName("redirect:/contents/list_by_cateno_search_paging.do?cateno=" + contentsVO.getCateno());
    // mav.setViewName("redirect:/contents/list_all_cateno.do");
 
    return mav;
  }
  
  
  
  
  
  
  
  /**
   * 전체 목록
   * 
   * @return
   */
  // http://localhost:9090/ojt/carrent/list.do
  @RequestMapping(value = "/carrent/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<CarrentVO> list = carrentProc.list();
    mav.addObject("list", list);
 
    mav.setViewName("/carrent/list"); // /webapp/carrent/list.jsp
 
    return mav;
  }
  
  
  
  
  


 /**
  * 수정 폼 http://localhost:9090/ojt/carrent/update.do
  * 
  * @return
  */
 @RequestMapping(value = "/carrent/update.do", method = RequestMethod.GET)
 public ModelAndView update(int carrentno) {
   ModelAndView mav = new ModelAndView();  
   
   
   CarrentVO carrentVO = carrentProc.read(carrentno); // 카테고리 
   System.out.println(carrentVO.getCarrentno()+" 번호 확인중.." );
   mav.addObject("carrentVO", carrentVO);  
   
   mav.setViewName("/carrent/update"); // /webapp/carrent/update.jsp

   return mav;
 }
 
 /**
  * - 글만 수정하는 경우의 구현 
  * - 파일만 수정하는 경우의 구현 
  * - 글과 파일을 동시에 수정하는 경우의 구현
  * @param request
  * @param carrentVO
  * @return
  */
 @RequestMapping(value = "/carrent/update.do", method = RequestMethod.POST)
 public ModelAndView update(RedirectAttributes redirectAttributes, CarrentVO carrentVO, HttpServletRequest request) {

   
   ModelAndView mav = new ModelAndView();  
   String upDir = Tool.getRealPath(request, "/carrent/storage");
    List<MultipartFile> filesMF = carrentVO.getRent_file1MF(); // Spring이 File 객체를 저장해둠.


    String rent_file1 = ""; // 컬럼에 저장할 파일명
    String rent_file1_item = ""; // 하나의 파일명
    String rent_size1 = "";
    long rent_size1_item = 0; // 하나의 파일 사이즈
    String rent_thumb1 = ""; // Thumb 파일들
    String rent_thumb1_item = ""; // 하나의 Thumb 파일명
    int count_f = filesMF.size(); // 업로드된 파일 갯수
    
    // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
    // System.out.println("--> 업로드된 파일 갯수 count: " + count);
    

    CarrentVO carrentVO_old = carrentProc.read(carrentVO.getCarrentno()); // 카테고리   
    

    if (filesMF.get(0).getSize() > 0) { // 전송 파일이 존재한다면

      String thumbs_old = carrentVO_old.getRent_thumb1();  
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");

      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname); // 기존에 등록된 thumbs 파일 삭제
      }

      // 원본 파일 삭제
      String files_old = carrentVO_old.getRent_file1();

      if(files_old == null) {
        files_old = "";
      }
      System.out.println("파일 출력 : " +files_old);
      StringTokenizer files_st = new StringTokenizer(files_old, "/");
      while (files_st.hasMoreTokens()) {
        String fname = upDir + files_st.nextToken();
        Tool.deleteFile(fname);  // 기존에 등록된 원본 파일 삭제
      }
      
      for (int i = 0; i < count_f; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0:첫번째 파일 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());
 
        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          rent_file1_item = Upload.saveFileSpring(multipartFile, upDir); // 서버에 파일 저장
          rent_size1_item = multipartFile.getSize();
 
          if (Tool.isImage(rent_file1_item)) { // 이미지인지 검사
            rent_thumb1_item = Tool.preview(upDir, rent_file1_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }
          // 1개 이상의 다중 파일 처리
          if (i != 0 && i < count_f) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            rent_file1 = rent_file1 + "/" + rent_file1_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            rent_size1 = rent_size1 + "/" + rent_size1_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            rent_thumb1 = rent_thumb1 + "/" + rent_thumb1_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
          // 파일이 없어도 파일 객체가 1개 생성되서 크기 체크
            rent_file1 = rent_file1_item; // file1.jpg
            rent_size1 = "" + rent_size1_item; // 123456
            rent_thumb1 = rent_thumb1_item; // file1_t.jpg
            System.out.println("rent_thumb1 : " +rent_thumb1);
          }
        } // if (multipartFile.getSize() > 0) {  END
        
      }
      
      
    } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
      rent_file1 = carrentVO_old.getRent_file1();
      rent_size1 = carrentVO_old.getRent_size1();
      rent_thumb1 = carrentVO_old.getRent_thumb1();
    }

   carrentVO.setRent_file1(rent_file1);
   carrentVO.setRent_size1(rent_size1);
   carrentVO.setRent_thumb1(rent_thumb1);
   int count = carrentProc.update(carrentVO);

   redirectAttributes.addAttribute("count", count); // redirect parameter 적용
   // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
   redirectAttributes.addAttribute("price", carrentVO.getPrice());
   redirectAttributes.addAttribute("conditon", carrentVO.getCondition());   
   redirectAttributes.addAttribute("age", carrentVO.getAge());


   mav.setViewName("redirect:/carrent/update_msg.jsp");
   return mav;
 }
 
 /**
  * 삭제 폼 http://localhost:9090/ojt/carrent/delete.do
  * @param cateno, carrentno
  * @return
  */
 @RequestMapping(value = "/carrent/delete.do", method = RequestMethod.GET)
 public ModelAndView delete(int carrentno) {
   ModelAndView mav = new ModelAndView();
   

   CarrentVO carrentVO = carrentProc.read(carrentno); // 카테고리                                                                          // 정보                                                                             // 추출
   mav.addObject("carrentVO", carrentVO);
   
//   ArrayList<FileVO> file_list = carrentProc.getThumbs(carrentVO);
//   mav.addObject("file_list", file_list);
   
   mav.setViewName("/carrent/delete"); // /webapp/carrent/delete.jsp

   return mav;
 }
 
 /**
  * 삭제
  * @param request
  * @param carrentVO
  * @return
  */
 @RequestMapping(value = "/carrent/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request, 
                                        int carrentno) {
   
   ModelAndView mav = new ModelAndView();
   String upDir = Tool.getRealPath(request, "/carrent/storage");

   // 기존의 등록 정보 조회
   CarrentVO carrentVO_old = carrentProc.read(carrentno);
   
   String thumbs_old = carrentVO_old.getRent_thumb1();  
   StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");

   while (thumbs_st.hasMoreTokens()) {
     String fname = upDir + thumbs_st.nextToken();
     Tool.deleteFile(fname); // 기존에 등록된 thumbs 파일 삭제
   }

   // 원본 파일 삭제
   String files_old = carrentVO_old.getRent_file1();

   if(files_old == null) {
     files_old = "";
   }
   
   StringTokenizer files_st = new StringTokenizer(files_old, "/");
   while (files_st.hasMoreTokens()) {
     String fname = upDir + files_st.nextToken();
     Tool.deleteFile(fname);  // 기존에 등록된 원본 파일 삭제
   }


   int count = carrentProc.delete(carrentno);


   // mav.setViewName("redirect:/contents/update_msg.jsp?count=" + count + "&...);
   //redirectAttributes.addAttribute("count", count); // redirect parameter 적용
   redirectAttributes.addAttribute("carrentno", carrentno);
   redirectAttributes.addAttribute("count", count);
   /*
   redirectAttributes.addAttribute("cateno", cateno);
   redirectAttributes.addAttribute("title", contentsVO_old.getTitle());
   redirectAttributes.addAttribute("nowPage", nowPage);*/
   
   mav.setViewName("redirect:/carrent/delete_msg.jsp");

   return mav;

 }
 
 
 
 /**
  * 조회
  * 
  * @param carrentno
  * @return
  */
 @RequestMapping(value = "/carrent/read.do", method = RequestMethod.GET)
 public ModelAndView read(int carrentno,  HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   
   Calendar cal = Calendar.getInstance();
   cal.add(Calendar.DATE, 1);
   
   String es_day = sdf.format(cal.getTime());

   cal.add(Calendar.DATE, 1);
   String ee_day = sdf.format(cal.getTime());


   System.out.println(es_day);
   System.out.println(ee_day);
   
   CarrentVO carrentVO = carrentProc.read(carrentno);
   mav.setViewName("/carrent/read"); // /webapp/carrent/read.jsp

   String upDir = Tool.getRealPath(request, "/carrent/storage");

   StringTokenizer rent_file1_st = new StringTokenizer(carrentVO.getRent_file1(), "/");
   ArrayList<String> list = new ArrayList<String>();
   

   while (rent_file1_st.hasMoreTokens()) {
     String fname = upDir + rent_file1_st.nextToken();
     list.add(fname);
     System.out.println("fname:"  +fname);
   }
   
   
   mav.addObject("ee_day", ee_day);
   mav.addObject("es_day", es_day);
   mav.addObject("list", list);
   mav.addObject("carrentVO", carrentVO);
   return mav;
 }
 
 
 

 
 @RequestMapping(value="/carrent/check_rentperiod_proc", method=RequestMethod.POST)
 @ResponseBody
 public String check_rentperiod_proc(String startday, String endday, int carrentno) {
   System.out.println("AJAX 1차 연결");
   System.out.println("startday : "+startday);
   System.out.println("endday : "+endday);
   System.out.println("carrentno : "+carrentno);

   
   System.out.println(startday.length());
   System.out.println(endday.length());
   
   HashMap<String, Object> map = new HashMap<String, Object>();
   System.out.println("해시맵 생성");
   map.put("startday", startday);
   map.put("endday", endday);
   map.put("carrentno", carrentno);
   System.out.println("해시맵 자료 입력");
   //int count = rentperiodProc.check_rentperiod(map);
   int count = reservationProc.check_rentperiod(map);
   System.out.println("count : 값 출력 : "+count);
   JSONObject json = new JSONObject();
   json.put("count", count);
   
   return json.toString();
 }

 @Scheduled(fixedDelay = 1000)
 public void wow() {
   System.out.println("hahahahahahahahah");
 }
 
 @Scheduled(fixedDelay = 1000)
 public void scheduleRun() {
     System.out.println("sdffds");
 
 }
}