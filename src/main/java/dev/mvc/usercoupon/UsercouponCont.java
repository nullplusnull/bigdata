package dev.mvc.usercoupon;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.coupon.CouponProcInter;
import dev.mvc.coupon.CouponVO;
import nation.web.tool.Tool;

@Controller
public class UsercouponCont {

  @Autowired
  @Qualifier("dev.mvc.usercoupon.UsercouponProc")
  private UsercouponProcInter usercouponProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.coupon.CouponProc")
  private CouponProcInter couponProc = null;
  
  public UsercouponCont() {
    System.out.println("--> Usercoupon created.");
  }
  
  
  @RequestMapping(value="/usercoupon/create.do", method=RequestMethod.POST)
  @ResponseBody
  public String create(HttpServletRequest request, int couponno) {
    CouponVO couponVO = couponProc.read(couponno);
    UsercouponVO usercouponVO = new UsercouponVO();
    String date = couponVO.getExpiry_date();
    System.out.println(date);
    
    usercouponVO.setLimit_date(date);
    usercouponVO.setUserno(1);
    usercouponVO.setCouponno(couponno);
    System.out.println("쿠폰번호"+couponno);
    System.out.println("할당은 완료됨");
    int count = usercouponProc.create(usercouponVO);
    System.out.println("count1 : "+count);
    JSONObject json = new JSONObject();
    json.put("count", count);
    System.out.println("count2"+count);
    return json.toString();
  }
    
}





















