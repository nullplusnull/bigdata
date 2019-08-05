package dev.mvc.carrent;

import java.util.ArrayList;
import java.util.HashMap;



public interface CarrentDAOInter {
  /**
   * <xmp>
   * ���
   * <insert id="create" parameterType="CarrentVO">
   * </xmp>
   * @param carrentVO
   * @return
   */
  public int create(CarrentVO carrentVO);
 
  /**
   * <xmp>
   * ��ü ���
   * <select id="list" resultType="CarrentVO">
   * </xmp>
   * @param carrentVO
   * @return
   */
  public ArrayList<CarrentVO> list();
  
  /**
   * ��ȸ
   * <xmp>
   *   <select id="read" resultType="CarrentVO" parameterType="int">
   * </xmp>  
   * @param carrentno
   * @return
   */
  public CarrentVO read(int carrentno);
 
  /**
   * ���� ó��
   * <xmp>
   *   <update id="update" parameterType="CarrentVO"> 
   * </xmp>
   * @param carrentVO
   * @return
   */
  public int update(CarrentVO carrentVO);
  
  /**
   * ���� ó��
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

