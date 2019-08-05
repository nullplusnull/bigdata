package dev.mvc.rentperiod;
 
import java.util.List;




 
public class RentperiodVO {
  /*
  rentno                              NUMBER(10)        NOT NULL,
  carrentno                           NUMBER(10)        NOT NULL,
  startday                            DATE                  NOT NULL,
  endday                              DATE                  NOT NULL,
  scheduleday                       DATE                  NOT NULL,
  startplace                          VARCHAR2(30)    NULL,   
  endplace                           VARCHAR2(30)    NULL,
  PRIMARY KEY (rentno),
  FOREIGN KEY (carrentno) REFERENCES carrent (carrentno)
  */
  

  /*��Ʈ�Ⱓ*/
  private int rentno;
  /*��Ʈ�Ⱓ*/
  private int carrentno;
  /*��Ʈ�Ⱓ*/
  private String startday;
  /*��Ʈ�Ⱓ*/
  private String endday;
  /*��Ʈ�Ⱓ*/
  private String scheduleday;
  /*��Ʈ�Ⱓ*/
  private String startplace;
  /*��Ʈ�Ⱓ*/
  private String endplace;
  /**
   * @return the rentno
   */
  public int getRentno() {
    return rentno;
  }
  /**
   * @param rentno the rentno to set
   */
  public void setRentno(int rentno) {
    this.rentno = rentno;
  }
  /**
   * @return the carrentno
   */
  public int getCarrentno() {
    return carrentno;
  }
  /**
   * @param carrentno the carrentno to set
   */
  public void setCarrentno(int carrentno) {
    this.carrentno = carrentno;
  }
  /**
   * @return the startday
   */
  public String getStartday() {
    return startday;
  }
  /**
   * @param startday the startday to set
   */
  public void setStartday(String startday) {
    this.startday = startday;
  }
  /**
   * @return the endday
   */
  public String getEndday() {
    return endday;
  }
  /**
   * @param endday the endday to set
   */
  public void setEndday(String endday) {
    this.endday = endday;
  }
  /**
   * @return the scheduleday
   */
  public String getScheduleday() {
    return scheduleday;
  }
  /**
   * @param scheduleday the scheduleday to set
   */
  public void setScheduleday(String scheduleday) {
    this.scheduleday = scheduleday;
  }
  /**
   * @return the startplace
   */
  public String getStartplace() {
    return startplace;
  }
  /**
   * @param startplace the startplace to set
   */
  public void setStartplace(String startplace) {
    this.startplace = startplace;
  }
  /**
   * @return the endplace
   */
  public String getEndplace() {
    return endplace;
  }
  /**
   * @param endplace the endplace to set
   */
  public void setEndplace(String endplace) {
    this.endplace = endplace;
  }
 
  
}
 
  