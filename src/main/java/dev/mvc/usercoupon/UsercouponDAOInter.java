package dev.mvc.usercoupon;

import java.util.ArrayList;
import java.util.HashMap;

public interface UsercouponDAOInter {

  /**
   * <xmp>
   * 등록
   * <insert id="create" parameterType="UsercouponVO">
   * </xmp>
   * @param UsercouponVO
   * @return
   */
  public int create(UsercouponVO usercouponVO);
  
  /**
   * <xmp>
   * 전체 목록
   * <select id="list" resultType="UsercouponVO">
   * </xmp>
   * @param 
   * @return ArrayList<UsercouponVO>
   */
  public ArrayList<UsercouponVO> list();
  
  
  /**
   * <xmp>
   * 조회
   * <select id='read' resultType="CouponVO" parameterType="int">
   * </xmp>
   * @param int
   * @return CouponVO
   */
  public UsercouponVO read(int user_couponno);
  
  /**
   * <xmp>
   * 사용
   * <update id='update' parameterType="CouponVO">
   * </xmp>
   * @param CouponVO
   * @return int
   */
  public int update_use(int user_couponno);
  
  /**
   * <xmp>
   * 제한날짜 변경
   * <update id='update' parameterType="CouponVO">
   * </xmp>
   * @param CouponVO
   * @return int
   */
  public int update_date(UsercouponVO usercouponVO);
  

  /**
   * <xmp>
   * 삭제
   * <delete id="delete" parameterType="int">
   * </xmp>
   * @param int
   * @return int
   */
   public int delete(int user_couponno);
   
   /**
    * <xmp>
    * 확인
    * <select id='confirm' resultType="int" parameterType="HashMap">
    * </xmp>
    * @param int
    * @return int
    */
   public int confirm(HashMap map);
}
