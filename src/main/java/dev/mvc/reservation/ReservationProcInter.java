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
   * ��¥���� ����ȣ�� ���� Ȯ��
   * �������� 20���� ������ �ʾҰų� ���� �Ϸ�Ǿ����� 0����
   * <select id="check_rentperiod" resultType="int" parameterType="HashMap">
   * </xmp>
   * @param map
   * @return RentperiodVO
   */
   public int check_rentperiod(HashMap map);
}
