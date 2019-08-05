package dev.mvc.usercoupon;

import java.util.ArrayList;
import java.util.HashMap;

public interface UsercouponDAOInter {

  /**
   * <xmp>
   * ���
   * <insert id="create" parameterType="UsercouponVO">
   * </xmp>
   * @param UsercouponVO
   * @return
   */
  public int create(UsercouponVO usercouponVO);
  
  /**
   * <xmp>
   * ��ü ���
   * <select id="list" resultType="UsercouponVO">
   * </xmp>
   * @param 
   * @return ArrayList<UsercouponVO>
   */
  public ArrayList<UsercouponVO> list();
  
  
  /**
   * <xmp>
   * ��ȸ
   * <select id='read' resultType="CouponVO" parameterType="int">
   * </xmp>
   * @param int
   * @return CouponVO
   */
  public UsercouponVO read(int user_couponno);
  
  /**
   * <xmp>
   * ���
   * <update id='update' parameterType="CouponVO">
   * </xmp>
   * @param CouponVO
   * @return int
   */
  public int update_use(int user_couponno);
  
  /**
   * <xmp>
   * ���ѳ�¥ ����
   * <update id='update' parameterType="CouponVO">
   * </xmp>
   * @param CouponVO
   * @return int
   */
  public int update_date(UsercouponVO usercouponVO);
  

  /**
   * <xmp>
   * ����
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param int
   * @return int
   */
   public int delete(int user_couponno);
   
   /**
    * <xmp>
    * Ȯ��
    * <select id='confirm' resultType="int" parameterType="HashMap">
    * </xmp>
    * @param int
    * @return int
    */
   public int confirm(HashMap map);
}
