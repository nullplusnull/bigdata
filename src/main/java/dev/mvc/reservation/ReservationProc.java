package dev.mvc.reservation;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.payment.Reservation_PaymentVO;


@Component("dev.mvc.reservation.ReservationProc")
public class ReservationProc implements ReservationProcInter {
  
  @Autowired
  private ReservationDAOInter reservationDAO;

  @Override
  public int create(ReservationVO reservationVO) {
    int count = 0;
    count = reservationDAO.create(reservationVO);
    return count;
  }

  @Override
  public ArrayList<ReservationVO> list() {
    ArrayList<ReservationVO> list = reservationDAO.list();
    return list;
  }

  @Override
  public ReservationVO read_by_rentno(int rentno) {
    ReservationVO reservationVO = reservationDAO.read_by_rentno(rentno);
    return reservationVO;
  }

  @Override
  public int update_state(ReservationVO reservationVO) {
    int count = 0;
    count = reservationDAO.update_state(reservationVO);
    return count;
  }

  @Override
  public Reservation_RentperiodVO read_day(int reservationno) {
    Reservation_RentperiodVO reservation_RentperiodVO = reservationDAO.read_day(reservationno);
    return reservation_RentperiodVO;
  }

  @Override
  public int check_rentperiod(HashMap map) {
    int count = 0;
    count = reservationDAO.check_rentperiod(map);
    return count;
  }
  

  
 
  
}
