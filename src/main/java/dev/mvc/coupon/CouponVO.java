package dev.mvc.coupon;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CouponVO {
    private int couponno;             // ������ȣ PK
    private String couponname;     // �����̸�
    private int use_condition;         // ���� �ݾ� �̻���� ��밡��
    private String expiry_date;      // ��������
    private int discount_cost;      // ���αݾ�
    private String coupon_file="";    // ���� �̹���
    private String file_size="";            // ���� ������
    private String rdate="";          // ���� ��¥
    private String thumb="";
    private String thumbs="";
    private List<MultipartFile> coupon_fileMF;
    private String visible;
    
    public String getVisible() {
      return visible;
    }
    public void setVisible(String visible) {
      this.visible = visible;
    }
    public String getThumb() {
      return thumb;
    }
    public void setThumb(String thumb) {
      this.thumb = thumb;
    }
    public String getThumbs() {
      return thumbs;
    }
    public void setThumbs(String thumbs) {
      this.thumbs = thumbs;
    }
    
    
    public List<MultipartFile> getCoupon_fileMF() {
      return coupon_fileMF;
    }
    public void setCoupon_fileMF(List<MultipartFile> coupon_fileMF) {
      this.coupon_fileMF = coupon_fileMF;
    }
    public int getCouponno() {
      return couponno;
    }
    public void setCouponno(int couponno) {
      this.couponno = couponno;
    }
    public String getCouponname() {
      return couponname;
    }
    public void setCouponname(String couponname) {
      this.couponname = couponname;
    }
    public int getUse_condition() {
      return use_condition;
    }
    public void setUse_condition(int use_condition) {
      this.use_condition = use_condition;
    }
    public String getExpiry_date() {
      return expiry_date;
    }
    public void setExpiry_date(String expiry_date) {
      this.expiry_date = expiry_date;
    }
    public int getDiscount_cost() {
      return discount_cost;
    }
    public void setDiscount_cost(int discount_cost) {
      this.discount_cost = discount_cost;
    }
    public String getCoupon_file() {
      return coupon_file;
    }
    public void setCoupon_file(String coupon_file) {
      this.coupon_file = coupon_file;
    }
    public String getFile_size() {
      return file_size;
    }
    public void setFile_size(String file_size) {
      this.file_size = file_size;
    }
    public String getRdate() {
      return rdate;
    }
    public void setRdate(String rdate) {
      this.rdate = rdate;
    }
  
 
}
