package dev.mvc.coupon;

import java.util.ArrayList;

public interface CouponProcInter {

  /**
   * <xmp>
   * ���
   * <insert id="create" parameterType="CouponVO">
   * </xmp>
   * @param carrentVO
   * @return
   */
  public int create(CouponVO couponVO);
  
  /**
   * <xmp>
   * ��ü ���
   * <select id="list" resultType="CouponVO">
   * </xmp>
   * @param 
   * @return ArrayList<CouponVO>
   */
  public ArrayList<CouponVO> list();
  
  
  /**
   * <xmp>
   * ��ȸ
   * <select id='read' resultType="CouponVO" parameterType="int">
   * </xmp>
   * @param int
   * @return CouponVO
   */
  public CouponVO read(int couponno);
  
  /**
   * <xmp>
   * ����
   * <update id='update' parameterType="CouponVO">
   * </xmp>
   * @param CouponVO
   * @return int
   */
  public int update(CouponVO couponVO);
  

  /**
   * <xmp>
   * ����
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param int
   * @return int
   */
   public int delete(int couponno);
   
   /**
    * <xmp>
    * ��ü ���
    * <select id="list" resultType="CouponVO">
    * </xmp>
    * @param 
    * @return ArrayList<CouponVO>
    */
   public ArrayList<CouponVO> open_list();
}
