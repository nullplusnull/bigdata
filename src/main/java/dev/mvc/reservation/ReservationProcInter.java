package dev.mvc.reservation;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReservationProcInter {

  
  public int create(ReservationVO reservationVO); 
  
  public ArrayList<ReservationVO> list();
  
  public ReservationVO read_by_rentno(int rentno);
  
  public int update_state(ReservationVO reservationVO);
  
  public Reservation_RentperiodVO read_day(int reservationno);
  
  /**
   * <xmp>
   * 날짜값과 차번호로 예약 확인
   * 예약한지 20분이 지나지 않았거나 예약 완료되었으면 0리턴
   * <select id="check_rentperiod" resultType="int" parameterType="HashMap">
   * </xmp>
   * @param map
   * @return RentperiodVO
   */
   public int check_rentperiod(HashMap map);
}
