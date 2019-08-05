package dev.mvc.buyhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.buyhistory.BuyhistoryProc")
public class BuyhistoryProc implements BuyhistoryProcInter {

  @Autowired
  private BuyhistoryDAOInter buyhistoryDAOInter;
  
  public BuyhistoryProc() {
    System.out.println("-->BuyhistoryProc create");
  }

  @Override
  public int create(BuyhistoryVO buyhistoryVO) {
    int count = 0;
    count = buyhistoryDAOInter.create(buyhistoryVO);
    return count;
  }
}
