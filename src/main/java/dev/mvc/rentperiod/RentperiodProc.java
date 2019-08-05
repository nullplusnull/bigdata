package dev.mvc.rentperiod;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component("dev.mvc.rentperiod.RentperiodProc")
public class RentperiodProc implements RentperiodProcInter {
  @Autowired
 private RentperiodDAOInter rentperiodDAO;
 
 public RentperiodProc() {
   System.out.println("--> RentperiodProc created.");
 }

 @Override
 public int create(RentperiodVO rentperiodVO) {
   int count = rentperiodDAO.create(rentperiodVO);
   return count ;
 }

@Override
public Carrent_RentperiodVO read_by_join(int rentno) {
  Carrent_RentperiodVO carrent_RentperiodVO = rentperiodDAO.read_by_join(rentno);
  return carrent_RentperiodVO;
  }

@Override
public int check_rentperiod(HashMap map) {
  int count = 0;
  count = rentperiodDAO.check_rentperiod(map);
  return count;
}

@Override
public RentperiodVO day_read(HashMap map) {
  RentperiodVO rentperiodVO = rentperiodDAO.day_read(map);
  return rentperiodVO;
}

@Override
public int schedule_update(int rentno) {
  int count= 0;
  count = rentperiodDAO.schedule_update(rentno);
  return count;
}


}

 




