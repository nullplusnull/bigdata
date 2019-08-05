package dev.mvc.coupon;

import java.util.ArrayList;

public interface CouponProcInter {

  /**
   * <xmp>
   * 등록
   * <insert id="create" parameterType="CouponVO">
   * </xmp>
   * @param carrentVO
   * @return
   */
  public int create(CouponVO couponVO);
  
  /**
   * <xmp>
   * 전체 목록
   * <select id="list" resultType="CouponVO">
   * </xmp>
   * @param 
   * @return ArrayList<CouponVO>
   */
  public ArrayList<CouponVO> list();
  
  
  /**
   * <xmp>
   * 조회
   * <select id='read' resultType="CouponVO" parameterType="int">
   * </xmp>
   * @param int
   * @return CouponVO
   */
  public CouponVO read(int couponno);
  
  /**
   * <xmp>
   * 수정
   * <update id='update' parameterType="CouponVO">
   * </xmp>
   * @param CouponVO
   * @return int
   */
  public int update(CouponVO couponVO);
  

  /**
   * <xmp>
   * 삭제
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param int
   * @return int
   */
   public int delete(int couponno);
   
   /**
    * <xmp>
    * 전체 목록
    * <select id="list" resultType="CouponVO">
    * </xmp>
    * @param 
    * @return ArrayList<CouponVO>
    */
   public ArrayList<CouponVO> open_list();
}
