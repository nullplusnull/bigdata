package dev.mvc.refund;

public class RefundVO {
  private int refundno;
  private int paymentno;
  private String refund_reason;
  private String rdate;
  
  
  public int getRefundno() {
    return refundno;
  }
  public void setRefundno(int refundno) {
    this.refundno = refundno;
  }
  

  public String getRefund_reason() {
    return refund_reason;
  }
  public void setRefund_reason(String refund_reason) {
    this.refund_reason = refund_reason;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  public int getPaymentno() {
    return paymentno;
  }
  public void setPaymentno(int paymentno) {
    this.paymentno = paymentno;
  }
}
