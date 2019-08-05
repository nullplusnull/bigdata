package dev.mvc.rentperiod;

import java.util.ArrayList;
import java.util.HashMap;




public interface RentperiodDAOInter {
  /**
   * <xmp>
   * ���
   * <insert id="create" parameterType="RentperiodVO">
   * </xmp>
   * @param rentperiodVO
   * @return
   */
  public int create(RentperiodVO rentperiodVO);
 
  
  
  /**
   * <xmp>
   * ���
   * <select id="read_by_join" resultType="Carrent_RentperiodVO" parameterType="int">
   * </xmp>
   * @param int
   * @return Carrent_RentperiodVO
   */
  public Carrent_RentperiodVO read_by_join(int rentno);
 

  /**
   * <xmp>
   * ���೯¥ üũ
   * <select id="check_rentperiod" resultType="int" parameterType="HashMap">
   * </xmp>
   * @param map
   * @return int
   */
  public int check_rentperiod(HashMap map);
  
  /**
   * <xmp>
   * ��¥������ ��Ʈ��ȣ �о����
   * <select id="day_read" resultType="RentperiodVO" parameterType="HashMap">
   * </xmp>
   * @param map
   * @return RentperiodVO
   */
   public RentperiodVO day_read(HashMap map);
  
   
   /**
    * e
    * @param rentno
    * @return
    */
   public int schedule_update(int rentno);
   
}













