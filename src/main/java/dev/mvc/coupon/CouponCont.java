package dev.mvc.coupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.usercoupon.UsercouponProcInter;
import dev.mvc.usercoupon.UsercouponVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class CouponCont {
  
  @Autowired
  @Qualifier("dev.mvc.usercoupon.UsercouponProc")
  private UsercouponProcInter usercouponProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.coupon.CouponProc")
  private CouponProcInter couponProc = null;
  
  public CouponCont() {
    System.out.println("--> CouponCont created.");
  }
  
  
  // 입력
 @RequestMapping(value="/coupon/create.do", method=RequestMethod.POST)
 public ModelAndView create(HttpServletRequest request, CouponVO couponVO) {
   ModelAndView mav = new ModelAndView();
   


// -------------------------------------------------------------------
   // 파일 전송 코드 시작
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/coupon/storage");
// Spring이 FIle 객체를 저장해둠, 개발자는 이용만 함
   List<MultipartFile> filesMF = couponVO.getCoupon_fileMF(); // Spring이 File 객체를
                                                          // 저장해둠.
   String coupon_file = "";    // 컬럼에 저장할 파일명
   String coupon_file_item = "";      // 하나의 파일명
   
   String file_size = "";
   long file_size_item = 0;        // 하나의 파일 사이즈
   
   String thumbs = "";    // Thumb 파일들
   String thumbs_item = "";   // 하나의 Thumb 파일명
   
   int count = filesMF.size();      // 업로드된 파일 갯수
   // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
   System.out.println("업로드된 파일 갯수: " +count);
   
   if(count > 0) {      // 전송 파일이 존재한다면
     for(int i =0; i<count; i++) {
       MultipartFile multipartFile = filesMF.get(i); // 0: 첫번째 파일 ~
       System.out.println("multipartFile.getName(): " + multipartFile.getName());
       
       if(multipartFile.getSize() > 0) {   // 전송 파일이 있는지 체크 filesMF
      // System.out.println("전송 파일이 있습니다.");
         coupon_file_item = Upload.saveFileSpring(multipartFile, upDir); // 서버에 파일 저장
         file_size_item = multipartFile.getSize();
         
         if(Tool.isImage(coupon_file_item)) {       // 이미지인지 검사
           thumbs_item = Tool.preview(upDir, coupon_file_item, 120, 80); // Thumb이미지 생성
         }
         
         // 1개 이상의 다중 파일 처리
         if(i != 0 && i < count) {  // index가 1 이상이면(두번째 파일 이상이면)
           // 하나의 칼럼에 여러개의 파일명을 조합하여 저장, 
           coupon_file = coupon_file + "/" +coupon_file_item;
           // 하나의 칼럼에 여러개의 파일 사이즈를 조합하여 저장
           file_size = file_size +"/" +file_size_item;
           // 썸네일 이미지를 조합하여 하나의 컬럼에 저장
           thumbs = thumbs +"/" +thumbs_item;
         } else if(multipartFile.getSize() > 0) {
           // 파일이 없어도 파일 객체가 1개 생성되서 크기 체크
           coupon_file = coupon_file_item;
           file_size = ""+file_size_item;
           thumbs = thumbs_item;
         }
       }
       
     }
   }
   couponVO.setCoupon_file(coupon_file);
   couponVO.setFile_size(file_size);
   couponVO.setThumb(thumbs);
   // -------------------------------------------------------------------
   // 파일 전송 코드 종료
   // -------------------------------------------------------------------

   count = couponProc.create(couponVO);

   
   mav.setViewName("redirect:/coupon/list.do"); 
   return mav;
 }
  
  
 
 
 
  // 리스트
  @RequestMapping(value="/coupon/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    ArrayList<CouponVO> list = couponProc.list();
    mav.addObject("list", list);
    mav.setViewName("/coupon/list"); // 
    return mav;
  }
  
 // 쿠폰 리스트
 @RequestMapping(value="/coupon/coupon_list.do", method=RequestMethod.GET)
 public ModelAndView coupon_list() {
   ModelAndView mav = new ModelAndView();
   ArrayList<CouponVO> list = couponProc.open_list();
   
   /*for(int i=0; i<list.size(); i++) {
     list.get(i).getCouponno();         // 쿠폰번호 하나 출력
     usercouponProc.read(user_couponno)
   }*/
   
   mav.addObject("list", list);
   mav.setViewName("/mypage/coupon_list"); // 
   return mav;
 }
  
  
  
  
  @RequestMapping(value="/coupon/coupon_update", 
      produces = "application/json; charset=utf8", method=RequestMethod.POST)
  @ResponseBody
  public String coupon_update(int couponno) {
    System.out.println("AJAX coupon 연결");
   
    CouponVO couponVO = couponProc.read(couponno);
    System.out.println("getExpiry_date : "+couponVO.getExpiry_date());
    JSONObject json = new JSONObject();
    json.put("couponname", couponVO.getCouponname());
    json.put("use_condition", couponVO.getUse_condition());
    json.put("discount_cost", couponVO.getDiscount_cost());
    json.put("expiry_date", couponVO.getExpiry_date());
    
    return json.toString();
  }
  
  
   // 업데이트
   @RequestMapping(value="/coupon/update.do", method=RequestMethod.POST)
   public ModelAndView update(HttpServletRequest request, CouponVO couponVO) {
     ModelAndView mav = new ModelAndView();

     String upDir = Tool.getRealPath(request, "/coupon/storage");
     List<MultipartFile> filesMF = couponVO.getCoupon_fileMF(); // Spring이 File 객체를 저장해둠.
     
     String coupon_file = "";    // 컬럼에 저장할 파일명
     String coupon_file_item = "";      // 하나의 파일명
     
     String file_size = "";
     long file_size_item = 0;        // 하나의 파일 사이즈
     
     String thumbs = "";    // Thumb 파일들
     String thumbs_item = "";   // 하나의 Thumb 파일명
     
     int count = filesMF.size();      // 업로드된 파일 갯수
     // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
     System.out.println("업로드된 파일 갯수: " +count);
     
     
     CouponVO couponVO_old = couponProc.read(couponVO.getCouponno());
     
     if(filesMF.get(0).getSize() > 0) {      // 전송 파일이 존재한다면
       
       // 썸네일 삭제
       String thumbs_old = couponVO_old.getThumb();
       StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
       
       while (thumbs_st.hasMoreTokens()) {
         String fname = upDir + thumbs_st.nextToken();
         Tool.deleteFile(fname); // 기존에 등록된 thumbs 파일 삭제
       }
       
       // 원본 파일 삭제
       String files_old = couponVO_old.getCoupon_file();
       if(files_old == null) {
         files_old = "";
       }
       StringTokenizer files_st = new StringTokenizer(files_old, "/");
       while (files_st.hasMoreTokens()) {
         String fname = upDir + files_st.nextToken();
         Tool.deleteFile(fname);  // 기존에 등록된 원본 파일 삭제
       }
     
       
       for(int i =0; i<count; i++) {
         MultipartFile multipartFile = filesMF.get(i); // 0: 첫번째 파일 ~
         //System.out.println("multipartFile.getName(): " + multipartFile.getName());
         
         if(multipartFile.getSize() > 0) {   // 전송 파일이 있는지 체크 filesMF
        // System.out.println("전송 파일이 있습니다.");
           coupon_file_item = Upload.saveFileSpring(multipartFile, upDir); // 서버에 파일 저장
           file_size_item = multipartFile.getSize();
           
           if(Tool.isImage(coupon_file_item)) {       // 이미지인지 검사
             thumbs_item = Tool.preview(upDir, coupon_file_item, 120, 80); // Thumb이미지 생성

           }
           
           // 1개 이상의 다중 파일 처리
           if(i != 0 && i < count) {  // index가 1 이상이면(두번째 파일 이상이면)
             // 하나의 칼럼에 여러개의 파일명을 조합하여 저장, 
             coupon_file = coupon_file + "/" +coupon_file_item;
             // 하나의 칼럼에 여러개의 파일 사이즈를 조합하여 저장
             file_size = file_size +"/" +file_size_item;
             // 썸네일 이미지를 조합하여 하나의 컬럼에 저장
             thumbs = thumbs +"/" +thumbs_item;
           } else if(multipartFile.getSize() > 0) {
             // 파일이 없어도 파일 객체가 1개 생성되서 크기 체크
             coupon_file = coupon_file_item;
             file_size = ""+file_size_item;
             thumbs = thumbs_item;
           }
         }
         
       }
     } else {   // 글만 수정하는 경우 기존의 파일 정보 재사용
       //System.out.println("기존 정보 재사용");
       coupon_file = couponVO_old.getCoupon_file();
       file_size = couponVO_old.getFile_size();
       thumbs = couponVO_old.getThumb();
     }
     couponVO.setCoupon_file(coupon_file);
     couponVO.setFile_size(file_size);
     couponVO.setThumb(thumbs);
     count = couponProc.update(couponVO);
     // -------------------------------------------------------------------
     // 파일 전송 코드 종료
     // -------------------------------------------------------------------
     //System.out.println("??"+count);
     mav.setViewName("redirect:/coupon/list.do"); 
     return mav;
   }
   
   
   // 삭제
   @RequestMapping(value="/coupon/delete.do", 
       produces = "application/json; charset=utf8", method=RequestMethod.POST)
   @ResponseBody
   public String delete(HttpServletRequest request, int couponno) {
     String upDir = Tool.getRealPath(request, "/coupon/storage");
     
     // 기존의 정보 조회
     CouponVO couponVO_old = couponProc.read(couponno);
     
     // 썸네일 삭제
     String thumbs_old = couponVO_old.getThumb();
     StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
     
     while (thumbs_st.hasMoreTokens()) {
       String fname = upDir + thumbs_st.nextToken();
       Tool.deleteFile(fname); // 기존에 등록된 thumbs 파일 삭제
     }
     
     // 원본 파일 삭제
     String files_old = couponVO_old.getCoupon_file();
     if(files_old == null) {
       files_old = "";
     }
     StringTokenizer files_st = new StringTokenizer(files_old, "/");
     while (files_st.hasMoreTokens()) {
       String fname = upDir + files_st.nextToken();
       Tool.deleteFile(fname);  // 기존에 등록된 원본 파일 삭제
     }
     
     
     int count = couponProc.delete(couponno);
     JSONObject json = new JSONObject();
     json.put("count", count);
     return json.toString();
   }
  
  
}








