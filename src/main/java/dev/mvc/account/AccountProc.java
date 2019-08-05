package dev.mvc.account;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.account.AccountProc")
public class AccountProc implements AccountProcInter {
  @Autowired
  private AccountDAOInter accountDAO;
  
  public AccountProc() {
    System.out.println("--> AccountProc created.");
  }
  
  @Override
  public int create(AccountVO accountVO) {
    int count = accountDAO.create(accountVO);
    return count;
  }

  @Override
  public ArrayList<AccountVO> list() {
    ArrayList<AccountVO> list = accountDAO.list();
    return list;
  }

 

  @Override
  public int delete(int accountno) {
    int count = 0;
    count = accountDAO.delete(accountno);
    return count;
  }

  @Override
  public int update_passwd(AccountVO accountVO) {
    int count = 0;
    count = accountDAO.update_passwd(accountVO);
    return count;
  }

  @Override
  public int update_cash(AccountVO accountVO) {
    int count = 0;
    count = accountDAO.update_cash(accountVO);
    return count;
  }

  @Override
  public ArrayList<AccountVO> user_list(int userno) {
    ArrayList<AccountVO> list = accountDAO.user_list(userno);
    return list;
  }

  @Override
  public AccountVO read(int accountno) {
    AccountVO accountVO = accountDAO.read(accountno);
    return accountVO;
  }


  

}





