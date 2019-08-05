package dev.mvc.coupon;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component("dev.mvc.coupon.CouponProc")
public class CouponProc implements CouponProcInter {
  
  @Autowired
  private CouponDAOInter couponDAO;
  
  public CouponProc() {
    System.out.println("--> CouponProc created");
  }

  @Override
  public int create(CouponVO couponVO) {
    int count = 0;
    count = couponDAO.create(couponVO);
    return count;
  }

  @Override
  public ArrayList<CouponVO> list() {
    ArrayList<CouponVO> list = null;
    list = couponDAO.list();
    return list;
  }

  @Override
  public CouponVO read(int couponno) {
    CouponVO couponVO = couponDAO.read(couponno);
    return couponVO;
  }

  @Override
  public int update(CouponVO couponVO) {
    int count = 0;
    count = couponDAO.update(couponVO);
    return count;
  }

  @Override
  public int delete(int couponno) {
    int count = 0;
    count = couponDAO.delete(couponno);
    return count;
  }

  @Override
  public ArrayList<CouponVO> open_list() {
    ArrayList<CouponVO> list = couponDAO.open_list();
    return list;
  }
  
  
}
