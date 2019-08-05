package dev.mvc.reservation;

public class ReservationVO {
  private int reservationno;
  private String reservation_state;
  private int userno;
  private int rentno;
  private String rdate;
  
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getUserno() {
    return userno;
  }
  public void setUserno(int userno) {
    this.userno = userno;
  }
  public String getReservation_state() {
    return reservation_state;
  }
  public void setReservation_state(String reservation_state) {
    this.reservation_state = reservation_state;
  }
  
  
  public int getReservationno() {
    return reservationno;
  }
  public void setReservationno(int reservationno) {
    this.reservationno = reservationno;
  }

  
  public int getRentno() {
    return rentno;
  }
  public void setRentno(int rentno) {
    this.rentno = rentno;
  }

  
  
  /*COMMENT ON TABLE reservation is '����';
  COMMENT ON COLUMN reservation.reservationno is '�����ȣ';
  COMMENT ON COLUMN reservation.reservation_state is '�������';
  COMMENT ON COLUMN reservation.rentno is '��Ʈ�Ⱓ��ȣ';*/
}
