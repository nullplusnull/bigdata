package dev.mvc.refund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.refund.RefundProc")
public class RefundProc implements RefundProcInter {

   @Autowired
   private RefundDAOInter refundDAO;

  @Override
  public int create(RefundVO refundVO) {
    int count = 0;
    count = refundDAO.create(refundVO);
    return count;
  }
  
}
