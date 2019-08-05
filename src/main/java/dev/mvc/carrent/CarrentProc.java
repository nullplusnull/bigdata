package dev.mvc.carrent;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.carrent.CarrentProc")
public class CarrentProc implements CarrentProcInter {
  @Autowired
 private CarrentDAOInter carrentDAO;
 
 
 public CarrentProc() {
   System.out.println("--> CarrentProc created.");
 }

  @Override
  public int create(CarrentVO carrentVO) {
   int count = carrentDAO.create(carrentVO);
    return count;
  }

  @Override
  public ArrayList<CarrentVO> list() {
    ArrayList<CarrentVO> list = carrentDAO.list();
    
    int count = list.size();
    for (int i=0; i < count; i++) {
      CarrentVO carrentVO = list.get(i);
      String thumb = getThumb(carrentVO); // 대표 이미지 추출
      carrentVO.setRent_thumbs(thumb);
    }
        
    return list;
  }

  @Override
  public CarrentVO read(int carrentno) {
    CarrentVO carrentVO = carrentDAO.read(carrentno);
    return carrentVO;
  }

  @Override
  public int update(CarrentVO carrentVO) {
    int count = carrentDAO.update(carrentVO);
    return count;
  }

  @Override
  public int delete(int carrentno) {
    int count = carrentDAO.delete(carrentno);
    return count;
  }
  
  /**
   * 이미지 목록중에 첫번째 이미지 파일명을 추출하여 리턴
   * @param contentsVO
   * @return
   */
  public String getThumb(CarrentVO carrentVO) {
    String thumbs = carrentVO.getRent_thumb1();
    String thumb = "";
    
    if (thumbs != null) {
      String[] thumbs_array = thumbs.split("/");
      int count = thumbs_array.length;
      
      if (count > 0) {
        thumb = thumbs_array[0];    
      }
    }
    // System.out.println("thumb: " + thumb);
    return thumb;
  }

  @Override
  public int update_rent(int carrentno) {
    int count = 0;
    count = carrentDAO.update_rent(carrentno);
    return count;
  }

  @Override
  public int update_return(int carrentno) {
    int count = 0;
    count = carrentDAO.update_return(carrentno);
    return count;
  }
  

}










