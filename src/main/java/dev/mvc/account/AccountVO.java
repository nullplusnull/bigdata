package dev.mvc.account;

public class AccountVO {

  
//  CREATE TABLE account(
//      accountno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
//      bank                              VARCHAR2(10)     NOT NULL,
//      account_passwd                    NUMBER(10)     NOT NULL,
//      cash                              NUMBER(12)     NOT NULL,
//      userno                            NUMBER(10)     NULL ,
//    FOREIGN KEY (userno) REFERENCES rent_user (userno)
//  );
  
  private int accountno;
  private String bank;
  private int account_passwd;
  
  
  public int getAccount_passwd() {
    return account_passwd;
  }
  public void setAccount_passwd(int account_passwd) {
    this.account_passwd = account_passwd;
  }
  private int cash;
  private int userno;
  
  
  public int getAccountno() {
    return accountno;
  }
  public void setAccountno(int accountno) {
    this.accountno = accountno;
  }
  public String getBank() {
    return bank;
  }
  public void setBank(String bank) {
    this.bank = bank;
  }
  public int getCash() {
    return cash;
  }
  public void setCash(int cash) {
    this.cash = cash;
  }
  public int getUserno() {
    return userno;
  }
  public void setUserno(int userno) {
    this.userno = userno;
  }
  
  

  
}
