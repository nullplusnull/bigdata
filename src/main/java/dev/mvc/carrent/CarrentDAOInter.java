package dev.mvc.carrent;

import java.util.ArrayList;
import java.util.HashMap;



public interface CarrentDAOInter {
  /**
   * <xmp>
   * 등록
   * <insert id="create" parameterType="CarrentVO">
   * </xmp>
   * @param carrentVO
   * @return
   */
  public int create(CarrentVO carrentVO);
 
  /**
   * <xmp>
   * 전체 목록
   * <select id="list" resultType="CarrentVO">
   * </xmp>
   * @param carrentVO
   * @return
   */
  public ArrayList<CarrentVO> list();
  
  /**
   * 조회
   * <xmp>
   *   <select id="read" resultType="CarrentVO" parameterType="int">
   * </xmp>  
   * @param carrentno
   * @return
   */
  public CarrentVO read(int carrentno);
 
  /**
   * 수정 처리
   * <xmp>
   *   <update id="update" parameterType="CarrentVO"> 
   * </xmp>
   * @param carrentVO
   * @return
   */
  public int update(CarrentVO carrentVO);
  
  /**
   * 삭제 처리
   * <xmp>
   *   <delete id="update" parameterType="CarrentVO"> 
   * </xmp>
   * @param carrentVO
   * @return
   */
  public int delete(int carrentno);

  public int update_rent(int carrentno);
  public int update_return(int carrentno);


}

