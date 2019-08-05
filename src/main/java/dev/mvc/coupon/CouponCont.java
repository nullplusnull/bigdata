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
  
  
  // �Է�
 @RequestMapping(value="/coupon/create.do", method=RequestMethod.POST)
 public ModelAndView create(HttpServletRequest request, CouponVO couponVO) {
   ModelAndView mav = new ModelAndView();
   


// -------------------------------------------------------------------
   // ���� ���� �ڵ� ����
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/coupon/storage");
// Spring�� FIle ��ü�� �����ص�, �����ڴ� �̿븸 ��
   List<MultipartFile> filesMF = couponVO.getCoupon_fileMF(); // Spring�� File ��ü��
                                                          // �����ص�.
   String coupon_file = "";    // �÷��� ������ ���ϸ�
   String coupon_file_item = "";      // �ϳ��� ���ϸ�
   
   String file_size = "";
   long file_size_item = 0;        // �ϳ��� ���� ������
   
   String thumbs = "";    // Thumb ���ϵ�
   String thumbs_item = "";   // �ϳ��� Thumb ���ϸ�
   
   int count = filesMF.size();      // ���ε�� ���� ����
   // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
   System.out.println("���ε�� ���� ����: " +count);
   
   if(count > 0) {      // ���� ������ �����Ѵٸ�
     for(int i =0; i<count; i++) {
       MultipartFile multipartFile = filesMF.get(i); // 0: ù��° ���� ~
       System.out.println("multipartFile.getName(): " + multipartFile.getName());
       
       if(multipartFile.getSize() > 0) {   // ���� ������ �ִ��� üũ filesMF
      // System.out.println("���� ������ �ֽ��ϴ�.");
         coupon_file_item = Upload.saveFileSpring(multipartFile, upDir); // ������ ���� ����
         file_size_item = multipartFile.getSize();
         
         if(Tool.isImage(coupon_file_item)) {       // �̹������� �˻�
           thumbs_item = Tool.preview(upDir, coupon_file_item, 120, 80); // Thumb�̹��� ����
         }
         
         // 1�� �̻��� ���� ���� ó��
         if(i != 0 && i < count) {  // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
           // �ϳ��� Į���� �������� ���ϸ��� �����Ͽ� ����, 
           coupon_file = coupon_file + "/" +coupon_file_item;
           // �ϳ��� Į���� �������� ���� ����� �����Ͽ� ����
           file_size = file_size +"/" +file_size_item;
           // ����� �̹����� �����Ͽ� �ϳ��� �÷��� ����
           thumbs = thumbs +"/" +thumbs_item;
         } else if(multipartFile.getSize() > 0) {
           // ������ ��� ���� ��ü�� 1�� �����Ǽ� ũ�� üũ
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
   // ���� ���� �ڵ� ����
   // -------------------------------------------------------------------

   count = couponProc.create(couponVO);

   
   mav.setViewName("redirect:/coupon/list.do"); 
   return mav;
 }
  
  
 
 
 
  // ����Ʈ
  @RequestMapping(value="/coupon/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    ArrayList<CouponVO> list = couponProc.list();
    mav.addObject("list", list);
    mav.setViewName("/coupon/list"); // 
    return mav;
  }
  
 // ���� ����Ʈ
 @RequestMapping(value="/coupon/coupon_list.do", method=RequestMethod.GET)
 public ModelAndView coupon_list() {
   ModelAndView mav = new ModelAndView();
   ArrayList<CouponVO> list = couponProc.open_list();
   
   /*for(int i=0; i<list.size(); i++) {
     list.get(i).getCouponno();         // ������ȣ �ϳ� ���
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
    System.out.println("AJAX coupon ����");
   
    CouponVO couponVO = couponProc.read(couponno);
    System.out.println("getExpiry_date : "+couponVO.getExpiry_date());
    JSONObject json = new JSONObject();
    json.put("couponname", couponVO.getCouponname());
    json.put("use_condition", couponVO.getUse_condition());
    json.put("discount_cost", couponVO.getDiscount_cost());
    json.put("expiry_date", couponVO.getExpiry_date());
    
    return json.toString();
  }
  
  
   // ������Ʈ
   @RequestMapping(value="/coupon/update.do", method=RequestMethod.POST)
   public ModelAndView update(HttpServletRequest request, CouponVO couponVO) {
     ModelAndView mav = new ModelAndView();

     String upDir = Tool.getRealPath(request, "/coupon/storage");
     List<MultipartFile> filesMF = couponVO.getCoupon_fileMF(); // Spring�� File ��ü�� �����ص�.
     
     String coupon_file = "";    // �÷��� ������ ���ϸ�
     String coupon_file_item = "";      // �ϳ��� ���ϸ�
     
     String file_size = "";
     long file_size_item = 0;        // �ϳ��� ���� ������
     
     String thumbs = "";    // Thumb ���ϵ�
     String thumbs_item = "";   // �ϳ��� Thumb ���ϸ�
     
     int count = filesMF.size();      // ���ε�� ���� ����
     // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
     System.out.println("���ε�� ���� ����: " +count);
     
     
     CouponVO couponVO_old = couponProc.read(couponVO.getCouponno());
     
     if(filesMF.get(0).getSize() > 0) {      // ���� ������ �����Ѵٸ�
       
       // ����� ����
       String thumbs_old = couponVO_old.getThumb();
       StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
       
       while (thumbs_st.hasMoreTokens()) {
         String fname = upDir + thumbs_st.nextToken();
         Tool.deleteFile(fname); // ������ ��ϵ� thumbs ���� ����
       }
       
       // ���� ���� ����
       String files_old = couponVO_old.getCoupon_file();
       if(files_old == null) {
         files_old = "";
       }
       StringTokenizer files_st = new StringTokenizer(files_old, "/");
       while (files_st.hasMoreTokens()) {
         String fname = upDir + files_st.nextToken();
         Tool.deleteFile(fname);  // ������ ��ϵ� ���� ���� ����
       }
     
       
       for(int i =0; i<count; i++) {
         MultipartFile multipartFile = filesMF.get(i); // 0: ù��° ���� ~
         //System.out.println("multipartFile.getName(): " + multipartFile.getName());
         
         if(multipartFile.getSize() > 0) {   // ���� ������ �ִ��� üũ filesMF
        // System.out.println("���� ������ �ֽ��ϴ�.");
           coupon_file_item = Upload.saveFileSpring(multipartFile, upDir); // ������ ���� ����
           file_size_item = multipartFile.getSize();
           
           if(Tool.isImage(coupon_file_item)) {       // �̹������� �˻�
             thumbs_item = Tool.preview(upDir, coupon_file_item, 120, 80); // Thumb�̹��� ����

           }
           
           // 1�� �̻��� ���� ���� ó��
           if(i != 0 && i < count) {  // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
             // �ϳ��� Į���� �������� ���ϸ��� �����Ͽ� ����, 
             coupon_file = coupon_file + "/" +coupon_file_item;
             // �ϳ��� Į���� �������� ���� ����� �����Ͽ� ����
             file_size = file_size +"/" +file_size_item;
             // ����� �̹����� �����Ͽ� �ϳ��� �÷��� ����
             thumbs = thumbs +"/" +thumbs_item;
           } else if(multipartFile.getSize() > 0) {
             // ������ ��� ���� ��ü�� 1�� �����Ǽ� ũ�� üũ
             coupon_file = coupon_file_item;
             file_size = ""+file_size_item;
             thumbs = thumbs_item;
           }
         }
         
       }
     } else {   // �۸� �����ϴ� ��� ������ ���� ���� ����
       //System.out.println("���� ���� ����");
       coupon_file = couponVO_old.getCoupon_file();
       file_size = couponVO_old.getFile_size();
       thumbs = couponVO_old.getThumb();
     }
     couponVO.setCoupon_file(coupon_file);
     couponVO.setFile_size(file_size);
     couponVO.setThumb(thumbs);
     count = couponProc.update(couponVO);
     // -------------------------------------------------------------------
     // ���� ���� �ڵ� ����
     // -------------------------------------------------------------------
     //System.out.println("??"+count);
     mav.setViewName("redirect:/coupon/list.do"); 
     return mav;
   }
   
   
   // ����
   @RequestMapping(value="/coupon/delete.do", 
       produces = "application/json; charset=utf8", method=RequestMethod.POST)
   @ResponseBody
   public String delete(HttpServletRequest request, int couponno) {
     String upDir = Tool.getRealPath(request, "/coupon/storage");
     
     // ������ ���� ��ȸ
     CouponVO couponVO_old = couponProc.read(couponno);
     
     // ����� ����
     String thumbs_old = couponVO_old.getThumb();
     StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
     
     while (thumbs_st.hasMoreTokens()) {
       String fname = upDir + thumbs_st.nextToken();
       Tool.deleteFile(fname); // ������ ��ϵ� thumbs ���� ����
     }
     
     // ���� ���� ����
     String files_old = couponVO_old.getCoupon_file();
     if(files_old == null) {
       files_old = "";
     }
     StringTokenizer files_st = new StringTokenizer(files_old, "/");
     while (files_st.hasMoreTokens()) {
       String fname = upDir + files_st.nextToken();
       Tool.deleteFile(fname);  // ������ ��ϵ� ���� ���� ����
     }
     
     
     int count = couponProc.delete(couponno);
     JSONObject json = new JSONObject();
     json.put("count", count);
     return json.toString();
   }
  
  
}








