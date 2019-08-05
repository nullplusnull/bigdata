package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.admin.AdminProc")
public class AdminProc implements AdminProcInter{

  @Autowired
  private AdminDAOInter AdminDAOInter;
  
  public AdminProc(){
    System.out.println("--> AdminProc created.");
  }
  
  @Override
  public AdminVO readById(String adminid) {
    AdminVO adminVO = AdminDAOInter.readById(adminid);
    return adminVO;
  }

  @Override
  public int login(String adminid, String adminpasswd) {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("adminid", adminid);
    map.put("adminpasswd", adminpasswd);
    int count = AdminDAOInter.login(map);
    return count;
  }

  public boolean isAdmin(HttpSession session){
    boolean sw = false;
    
    String adminid = (String)session.getAttribute("adminid");
    
    if (adminid != null){
      sw = true;
    }
    return sw;
  }

}
