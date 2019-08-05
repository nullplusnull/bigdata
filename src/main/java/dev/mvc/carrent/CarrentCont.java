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
   * ��� �� http://localhost:9090/ojt/carrent/create.do
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
   * ��� ó��
   * ���� �±��� ����
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
    
    // �ɼ� NULL�̸� N���� �ٲ���
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
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/carrent/storage");
    // Spring�� FIle ��ü�� �����ص�, �����ڴ� �̿븸 ��
    List<MultipartFile> filesMF = carrentVO.getRent_file1MF(); // Spring�� File ��ü��
                                                           // �����ص�.
 
    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());
 
    String rent_file1 = ""; // �÷��� ������ ���ϸ�
    String rent_file1_item = ""; // �ϳ��� ���ϸ�
    String rent_size1 = "";
    long rent_size1_item = 0; // �ϳ��� ���� ������
    String rent_thumb1 = ""; // Thumb ���ϵ�
    String rent_thumb1_item = ""; // �ϳ��� Thumb ���ϸ�
 
    int count = filesMF.size(); // ���ε�� ���� ����
 
    // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
    // System.out.println("--> ���ε�� ���� ���� count: " + count);
 
    if (count > 0) { // ���� ������ �����Ѵٸ�
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0:ù��° ���� ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());
 
        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          rent_file1_item = Upload.saveFileSpring(multipartFile, upDir); // ������ ���� ����
          rent_size1_item = multipartFile.getSize();
 
          if (Tool.isImage(rent_file1_item)) { // �̹������� �˻�
            rent_thumb1_item = Tool.preview(upDir, rent_file1_item, 120, 80); // Thumb �̹���
                                                                    // ����
          }
          // 1�� �̻��� ���� ���� ó��
          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            rent_file1 = rent_file1 + "/" + rent_file1_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            rent_size1 = rent_size1 + "/" + rent_size1_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            rent_thumb1 = rent_thumb1 + "/" + rent_thumb1_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
          // ������ ��� ���� ��ü�� 1�� �����Ǽ� ũ�� üũ
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
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    count = carrentProc.create(carrentVO);
    
 
    mav.setViewName(
        "redirect:/carrent/create_msg.jsp?count=" + count + "&carrentno=" + carrentVO.getCarrentno()); // /webapp/contents/create_msg.jsp
 
    // mav.setViewName("redirect:/contents/list_by_cateno_search_paging.do?cateno=" + contentsVO.getCateno());
    // mav.setViewName("redirect:/contents/list_all_cateno.do");
 
    return mav;
  }
  
  
  
  
  
  
  
  /**
   * ��ü ���
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
  * ���� �� http://localhost:9090/ojt/carrent/update.do
  * 
  * @return
  */
 @RequestMapping(value = "/carrent/update.do", method = RequestMethod.GET)
 public ModelAndView update(int carrentno) {
   ModelAndView mav = new ModelAndView();  
   
   
   CarrentVO carrentVO = carrentProc.read(carrentno); // ī�װ� 
   System.out.println(carrentVO.getCarrentno()+" ��ȣ Ȯ����.." );
   mav.addObject("carrentVO", carrentVO);  
   
   mav.setViewName("/carrent/update"); // /webapp/carrent/update.jsp

   return mav;
 }
 
 /**
  * - �۸� �����ϴ� ����� ���� 
  * - ���ϸ� �����ϴ� ����� ���� 
  * - �۰� ������ ���ÿ� �����ϴ� ����� ����
  * @param request
  * @param carrentVO
  * @return
  */
 @RequestMapping(value = "/carrent/update.do", method = RequestMethod.POST)
 public ModelAndView update(RedirectAttributes redirectAttributes, CarrentVO carrentVO, HttpServletRequest request) {

   
   ModelAndView mav = new ModelAndView();  
   String upDir = Tool.getRealPath(request, "/carrent/storage");
    List<MultipartFile> filesMF = carrentVO.getRent_file1MF(); // Spring�� File ��ü�� �����ص�.


    String rent_file1 = ""; // �÷��� ������ ���ϸ�
    String rent_file1_item = ""; // �ϳ��� ���ϸ�
    String rent_size1 = "";
    long rent_size1_item = 0; // �ϳ��� ���� ������
    String rent_thumb1 = ""; // Thumb ���ϵ�
    String rent_thumb1_item = ""; // �ϳ��� Thumb ���ϸ�
    int count_f = filesMF.size(); // ���ε�� ���� ����
    
    // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
    // System.out.println("--> ���ε�� ���� ���� count: " + count);
    

    CarrentVO carrentVO_old = carrentProc.read(carrentVO.getCarrentno()); // ī�װ�   
    

    if (filesMF.get(0).getSize() > 0) { // ���� ������ �����Ѵٸ�

      String thumbs_old = carrentVO_old.getRent_thumb1();  
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");

      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname); // ������ ��ϵ� thumbs ���� ����
      }

      // ���� ���� ����
      String files_old = carrentVO_old.getRent_file1();

      if(files_old == null) {
        files_old = "";
      }
      System.out.println("���� ��� : " +files_old);
      StringTokenizer files_st = new StringTokenizer(files_old, "/");
      while (files_st.hasMoreTokens()) {
        String fname = upDir + files_st.nextToken();
        Tool.deleteFile(fname);  // ������ ��ϵ� ���� ���� ����
      }
      
      for (int i = 0; i < count_f; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0:ù��° ���� ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());
 
        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          rent_file1_item = Upload.saveFileSpring(multipartFile, upDir); // ������ ���� ����
          rent_size1_item = multipartFile.getSize();
 
          if (Tool.isImage(rent_file1_item)) { // �̹������� �˻�
            rent_thumb1_item = Tool.preview(upDir, rent_file1_item, 120, 80); // Thumb �̹���
                                                                    // ����
          }
          // 1�� �̻��� ���� ���� ó��
          if (i != 0 && i < count_f) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            rent_file1 = rent_file1 + "/" + rent_file1_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            rent_size1 = rent_size1 + "/" + rent_size1_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            rent_thumb1 = rent_thumb1 + "/" + rent_thumb1_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
          // ������ ��� ���� ��ü�� 1�� �����Ǽ� ũ�� üũ
            rent_file1 = rent_file1_item; // file1.jpg
            rent_size1 = "" + rent_size1_item; // 123456
            rent_thumb1 = rent_thumb1_item; // file1_t.jpg
            System.out.println("rent_thumb1 : " +rent_thumb1);
          }
        } // if (multipartFile.getSize() > 0) {  END
        
      }
      
      
    } else { // �۸� �����ϴ� ���, ������ ���� ���� ����
      rent_file1 = carrentVO_old.getRent_file1();
      rent_size1 = carrentVO_old.getRent_size1();
      rent_thumb1 = carrentVO_old.getRent_thumb1();
    }

   carrentVO.setRent_file1(rent_file1);
   carrentVO.setRent_size1(rent_size1);
   carrentVO.setRent_thumb1(rent_thumb1);
   int count = carrentProc.update(carrentVO);

   redirectAttributes.addAttribute("count", count); // redirect parameter ����
   // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
   redirectAttributes.addAttribute("price", carrentVO.getPrice());
   redirectAttributes.addAttribute("conditon", carrentVO.getCondition());   
   redirectAttributes.addAttribute("age", carrentVO.getAge());


   mav.setViewName("redirect:/carrent/update_msg.jsp");
   return mav;
 }
 
 /**
  * ���� �� http://localhost:9090/ojt/carrent/delete.do
  * @param cateno, carrentno
  * @return
  */
 @RequestMapping(value = "/carrent/delete.do", method = RequestMethod.GET)
 public ModelAndView delete(int carrentno) {
   ModelAndView mav = new ModelAndView();
   

   CarrentVO carrentVO = carrentProc.read(carrentno); // ī�װ�                                                                          // ����                                                                             // ����
   mav.addObject("carrentVO", carrentVO);
   
//   ArrayList<FileVO> file_list = carrentProc.getThumbs(carrentVO);
//   mav.addObject("file_list", file_list);
   
   mav.setViewName("/carrent/delete"); // /webapp/carrent/delete.jsp

   return mav;
 }
 
 /**
  * ����
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

   // ������ ��� ���� ��ȸ
   CarrentVO carrentVO_old = carrentProc.read(carrentno);
   
   String thumbs_old = carrentVO_old.getRent_thumb1();  
   StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");

   while (thumbs_st.hasMoreTokens()) {
     String fname = upDir + thumbs_st.nextToken();
     Tool.deleteFile(fname); // ������ ��ϵ� thumbs ���� ����
   }

   // ���� ���� ����
   String files_old = carrentVO_old.getRent_file1();

   if(files_old == null) {
     files_old = "";
   }
   
   StringTokenizer files_st = new StringTokenizer(files_old, "/");
   while (files_st.hasMoreTokens()) {
     String fname = upDir + files_st.nextToken();
     Tool.deleteFile(fname);  // ������ ��ϵ� ���� ���� ����
   }


   int count = carrentProc.delete(carrentno);


   // mav.setViewName("redirect:/contents/update_msg.jsp?count=" + count + "&...);
   //redirectAttributes.addAttribute("count", count); // redirect parameter ����
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
  * ��ȸ
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
   System.out.println("AJAX 1�� ����");
   System.out.println("startday : "+startday);
   System.out.println("endday : "+endday);
   System.out.println("carrentno : "+carrentno);

   
   System.out.println(startday.length());
   System.out.println(endday.length());
   
   HashMap<String, Object> map = new HashMap<String, Object>();
   System.out.println("�ؽø� ����");
   map.put("startday", startday);
   map.put("endday", endday);
   map.put("carrentno", carrentno);
   System.out.println("�ؽø� �ڷ� �Է�");
   //int count = rentperiodProc.check_rentperiod(map);
   int count = reservationProc.check_rentperiod(map);
   System.out.println("count : �� ��� : "+count);
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