package dev.mvc.usercoupon;

public class UsercouponVO {
  private int user_couponno;
  private int couponno;
  private int userno;
  private String coupon_use;
  private String limit_date;
  private String rdate;
  public int getUser_couponno() {
    return user_couponno;
  }
  public void setUser_couponno(int user_couponno) {
    this.user_couponno = user_couponno;
  }
  public int getCouponno() {
    return couponno;
  }
  public void setCouponno(int couponno) {
    this.couponno = couponno;
  }
  public int getUserno() {
    return userno;
  }
  public void setUserno(int userno) {
    this.userno = userno;
  }
  public String getCoupon_use() {
    return coupon_use;
  }
  public void setCoupon_use(String coupon_use) {
    this.coupon_use = coupon_use;
  }
  public String getLimit_date() {
    return limit_date;
  }
  public void setLimit_date(String limit_date) {
    this.limit_date = limit_date;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
  /*user_couponno      NUMBER(10)    NOT NULL PRIMARY KEY,                 -- 발급된 쿠폰번호
  couponno            NUMBER(10)      NOT NULL,                                   -- 쿠폰번호 (FK)
  userno                 NUMBER(10)     NOT NULL,                                    -- 발급받은 유저번호(FK)
  coupon_use          CHAR(1)          DEFAULT 'N'    NOT NULL  ,              -- 쿠폰 사용 유무
  limit_date          DATE              NOT NULL,                                      -- 쿠폰 사용 제한일
  rdate                DATE              NOT NULL,   */                
}
