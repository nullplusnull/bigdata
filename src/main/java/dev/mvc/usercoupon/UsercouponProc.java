package dev.mvc.usercoupon;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.usercoupon.UsercouponProc")
public class UsercouponProc implements UsercouponProcInter {

  @Autowired
  private UsercouponDAOInter usercouponDAO;
  
  public UsercouponProc() {
    System.out.println("--> Usercoupon created.");
  }

  @Override
  public int create(UsercouponVO usercouponVO) {
    int count = 0;
    count = usercouponDAO.create(usercouponVO);
    return count;
  }

  @Override
  public ArrayList<UsercouponVO> list() {
    ArrayList<UsercouponVO> list = usercouponDAO.list();
    return list;
  }

  @Override
  public UsercouponVO read(int user_couponno) {
    UsercouponVO usercouponVO = usercouponDAO.read(user_couponno);
    return usercouponVO;
  }

  @Override
  public int update_use(int user_couponno) {
    int count = 0;
    count = usercouponDAO.update_use(user_couponno);
    return count;
  }

  @Override
  public int update_date(UsercouponVO usercouponVO) {
    int count = 0;
    count = usercouponDAO.update_date(usercouponVO);
    return count;
  }

  @Override
  public int delete(int user_couponno) {
    int count = 0;
    count = usercouponDAO.delete(user_couponno);
    return count;
  }
  
  
  
}
