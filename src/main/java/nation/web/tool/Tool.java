package nation.web.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

public class Tool {
  /**
   * 수를 전달받아 자료의 단위를 적용합니다.
   * @param size
   * @return 1000 > 1000 Byte
   */
  public static synchronized String unit(long size){
    String str = "";
    
    if (size < 1024){ // 1 KB 이하
      str = size + " Byte";
    }else if (size < 1024 * 1024){ // 1 MB 이하
      str = (int)(Math.ceil(size/1024.0)) + " KB"; // 1048576 보다 작으면 KB
    }else if (size < 1024 * 1024 * 1024){ // 1 GB 이하
      str = (int)(Math.ceil(size/1024.0/1024.0)) + " MB";
    }else if (size < 1024L * 1024 * 1024 * 1024){ // 1 TB 이하
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0)) + " GB";
    }else if (size < 1024L * 1024 * 1024 * 1024 * 1024){ // 1 PT 이하
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0/1024.0)) + " TB";
    }else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024){ // 1 EX 이하
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0/1024.0/1024.0)) + " PT";
    }
    
    return str;
  }
  
  
  /**
   * 이미지 사이즈를 변경하여 새로운 Preview 이미지를 생성합니다.
   <pre>
   사용예): Too.preview(folder 명, 원본 파일명, 200, 150)
   </pre>
   * @param upDir 원본 이미지 폴더
   * @param _src 원본 파일명
   * @param width 생성될 이미지 너비
   * @param height  생성될 이미지 높이, ImageUtil.RATIO는 자동 비례 비율
   * @return src.jpg 파일을 이용하여 src_t.jpg 파일을 생성하여 파일명 리턴
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;
    int SAME = -1;
 
    File src = new File(upDir + "/" + _src); // 원본 파일 객체 생성
    String srcname = src.getName(); // 원본 파일명 추출
 
    // 순수 파일명 추출, mt.jpg -> mt 만 추출
    String _dest = srcname.substring(0, srcname.indexOf("."));
 
    // 축소 이미지 조합 /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");
 
    Image srcImg = null;
 
    String name = src.getName().toLowerCase(); // 파일명을 추출하여 소문자로 변경
    // 이미지 파일인지 검사
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // 메모리에 원본 이미지 생성
        int srcWidth = srcImg.getWidth(null); // 원본 이미지 너비 추출
        int srcHeight = srcImg.getHeight(null); // 원본 이미지 높이 추출
        int destWidth = -1, destHeight = -1; // 대상 이미지 크기 초기화
 
        if (width == SAME) { // width가 같은 경우
          destWidth = srcWidth;
        } else if (width > 0) {
          destWidth = width; // 새로운 width를 할당
        }
 
        if (height == SAME) { // 높이가 같은 경우
          destHeight = srcHeight;
        } else if (height > 0) {
          destHeight = height; // 새로운 높이로 할당
        }
 
        // 비율에 따른 크기 계산
        if (width == RATIO && height == RATIO) {
          destWidth = srcWidth;
          destHeight = srcHeight;
        } else if (width == RATIO) {
          double ratio = ((double) destHeight) / ((double) srcHeight);
          destWidth = (int) ((double) srcWidth * ratio);
        } else if (height == RATIO) {
          double ratio = ((double) destWidth) / ((double) srcWidth);
          destHeight = (int) ((double) srcHeight * ratio);
        }
 
        // 메모리에 대상 이미지 생성
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);
 
        pg.grabPixels();
 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
 
        // 파일에 기록
        ImageIO.write(destImg, "jpg", dest);
 
        System.out.println(dest.getName() + " 이미지를 생성했습니다.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
 
    return dest.getName();
  }
  
  
  /**
   * 이미지인지 검사
   * @param file
   * @return true: 이미지, false: 일반파일
   */
  public static synchronized boolean isImage(String file) {
    boolean sw = false;
 
    if (file != null) { // 문자열이 있다면
      file = file.toLowerCase(); // 소문자로 변환
      if (file.endsWith(".jpg") || file.endsWith(".jpeg") || file.endsWith(".png") || file.endsWith(".gif")) {
        sw = true;
      }
    }
    return sw;
  }
  
  
  /**
   * 전송가능한 파일인지 검사
   * @param file
   * @return true: 전송 가능, false: 전송 불가능
   */
  /**
   * 전송 가능한 파일인지 검사
   * @param file
   * @return true: 전송 가능, false: 전송 불가능
   */
  public static synchronized boolean isAvailable(String file) {
    boolean sw = false;
 
    if (file != null) { // 문자열이 있다면
      file = file.toLowerCase(); // 소문자로 변환, jsp, java, php, asp, aspx등 프로그래밍 확장자 금지
      if (file.endsWith(".jpg") || file.endsWith(".jpeg") || file.endsWith(".png")
          || file.endsWith(".gif") || file.endsWith(".zip") || file.endsWith(".pdf")
          || file.endsWith(".hwp") || file.endsWith(".txt") || file.endsWith(".ppt") || file.endsWith(".pptx")
          || file.endsWith(".mp3") || file.endsWith(".mp4")      ) {
        sw = true;
      } else if (file.trim().length() == 0) { // 글만 등록하는 경우
        sw = true;
      }
    }
    return sw;
  }
  
  
  
  
 
  /**
   * MP3 인지 검사
   * @param file
   * @return true: 전송 가능, false: 전송 불가능
   */
  public static synchronized boolean isAvailableMP3(String file) {
    boolean sw = false;
 
    if (file != null) { // 문자열이 있다면
      file = file.toLowerCase(); // 소문자로 변환, jsp, java, php, asp, aspx등 프로그래밍 확장자 금지
      if (file.endsWith(".mp3")  ) {
        sw = true;
      } 
    }
    return sw;
  }


  /**
   * MP4 인지 검사
   * @param file
   * @return true: 전송 가능, false: 전송 불가능
   */
  public static synchronized boolean isAvailableMP4(String file) {
    boolean sw = false;
 
    if (file != null) { // 문자열이 있다면
      file = file.toLowerCase(); // 소문자로 변환, jsp, java, php, asp, aspx등 프로그래밍 확장자 금지
      if (file.endsWith(".mp4")  ) {
        sw = true;
      } 
    }
    return sw;
  }

  /**
   * 최대 파일 크기 초과 여부 검사
   * @param file   원본 파일 크기
   * @param limit 최대 파일 크기
   * @return true: 전송 가능 용량, false: 전송 불가능 용량
   */
  public static synchronized boolean isBeforeMax(long size, long limit) {
    boolean sw = false;
 
    if (size <= limit) { // 문자열이 있다면
      sw = true;                                         // 전송 가능
    }
    return sw;
  }

  
  /**
   * 문자열의 길이가 length 보다 크면 "..."을 표시하는 메소드
   * @param str 원본 문자열
   * @param length 선별할 문자열 길이
   * @return 특정 길이의 문자열
   */
  public static synchronized String textLength(String str, int length) {
    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0,  length) + "..."; // 범위: 0 ~ length - 1
      }
    } else {
      str = "";
    }
    
    return str;
  }
  
  
  
  /**
   * HTML 특수 문자의 변경
   * @param str
   * @return
   */
  public static synchronized String convertChar(String str) {
    str = str.replaceAll("<", "&lt;");
    str = str.replaceAll(">", "&gt;");
    str = str.replaceAll("'", "&apos");
    str = str.replaceAll("\"", "&quot;");
    str = str.replaceAll("\r\n", "<BR>");
    
    return str;
  }

  
  
  /**
   * 파일을 삭제합니다.
   */
  public static synchronized boolean deleteFile(String folder, String fileName) {
    boolean ret = false;
 
    try {
      if (fileName != null) { // 기존에 파일이 존재하는 경우 삭제
        File file = new File(folder + "/" + fileName);
        if (file.exists() && file.isFile()) { // 존재하는 파일인지 검사
          ret = file.delete();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
 
    return ret;
  }
  
  
  /**
   * 문자열이 null이면 ""으로 변경
   * @param str
   * @return
   */
  public static synchronized String checkNull(String str) {
    if (str == null) {
      str = "";
    }
    
    return str;
  }
  
  /**
   * spring에서 URL 한글 변환
   * Spring controller > JSP View EL
   * @param str
   * @return
   */
  public static synchronized String spring_param_encoding(String str) {
    if(str != null) {
      try {
        str =URLEncoder.encode(str, "UTF-8");
        System.out.println("변환된 한글 str: " +str);
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    } else {
      str = "";
    }
    
    return str;
  }
  
  /**
   * 폴더를 입력받아 절대 경로를 산출합니다. 
   * 예) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir 절대 경로를 구할 폴더명
   * @return 절대 경로 리턴
   * @throws IOException
   */
  public static synchronized String getRealPath(HttpServletRequest request, String dir) {
    String path = "";
    
    try{
      path = request.getRealPath(dir) + "/";  
      // System.out.println("Upload path: " + path);
    }catch(Exception e){
      System.out.println(e.toString());
    }
 
    return path;
  }
 
  /**
   * 파일 삭제
   * @param fname
   * @return
   */
  public static synchronized boolean deleteFile(String fname) {
    File file = new File(fname);
    boolean ret = false;
    
      if (file.exists()){ //파일이 존재하는지 확인
        ret = file.delete();
      }
    
    return ret;
  }
  
  /**
   * 20190522101401 + 정수형 난수
   * @param hhmm 시분을 전달받아 설정
   * @return
   */
  public static synchronized String getDateRand(){
    SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddhhmmss ");
    String date = sd.format(new Date());
 
    date = date + (int)(Math.random() * 1000); // 0~999, 중복확률 0.001초
    // System.out.println(date);        
    return date;
  }
 
  public static void main(String[] args){
    System.out.println(getDateRand());
  }
  
}























