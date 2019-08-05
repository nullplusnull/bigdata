package dev.mvc.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface AdminProcInter {
  /**
   * <xmp>
   * ���̵� �̿��� ȸ������ ��ȸ
   * <select id="readById" resultType="adminVO" parameterType="String">
   * </xmp>
   * @param adminid
   * @return ȸ�����̵� ���� ���ڵ�
   */
  public AdminVO readById(String adminid);
  
  /**
   * <xmp>
   * �α���
   * <select id="login"  resultType="int" parameterType="Hashmap">
   * </xmp>
   * @param map
   * @return �α����� ���ڵ� ����
   */
  public int login(String adminid, String adminpasswd);
  
  /**
   * <xmp>
   * �α��ε� ������ �������� �˻��մϴ�.
   * </xmp>
   * @param request
   * @return true: ������
   */
  public boolean isAdmin(HttpSession session); 
}
