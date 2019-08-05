package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminDAOInter {
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
  public int login(HashMap<String, Object> map);
  
  
}
