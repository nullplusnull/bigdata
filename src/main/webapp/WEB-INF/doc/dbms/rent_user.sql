-- ���¹�ȣ �����ؾ��Ѵ�

/**********************************/
/* Table Name: ȸ�� */
/**********************************/
DROP TABLE rent_user;
drop table account;

CREATE TABLE rent_user(
    userno                            NUMBER(10)     NOT NULL    PRIMARY KEY,
    userid                            VARCHAR2(50)     NOT NULL UNIQUE,
    username                          VARCHAR2(20)     NOT NULL,
    userpasswd                        VARCHAR2(20)     NOT NULL,
    userzipcode                          VARCHAR2(5)        NULL, -- �����ȣ, 12345
    useraddress1                         VARCHAR2(300)       NULL, -- �ּ� 1
    useraddress2                        VARCHAR2(300)       NULL, -- �ּ� 2
    usertel                           VARCHAR2(20)     NOT NULL,
    userage                           NUMBER(7)     NOT NULL,
    usersex                            CHAR(1)    NOT NULL,
    userlicense                       VARCHAR2(20)     NOT NULL,
    userstatus                        CHAR(1)    NOT NULL,
    rdate                             DATE     NOT NULL
);

COMMENT ON TABLE rent_user is 'ȸ��';
COMMENT ON COLUMN rent_user.userno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN rent_user.userid is 'ȸ��ID';
COMMENT ON COLUMN rent_user.username is 'ȸ���̸�';
COMMENT ON COLUMN rent_user.userpasswd is '��й�ȣ';
COMMENT ON COLUMN rent_user.userzipcode is '�����ȣ';
COMMENT ON COLUMN rent_user.useraddress1 is '�ּ�1';
COMMENT ON COLUMN rent_user.useraddress2 is '�ּ�2';
COMMENT ON COLUMN rent_user.usertel is '��ȭ��ȣ';
COMMENT ON COLUMN rent_user.userage is '����';
COMMENT ON COLUMN rent_user.usersex is '����';
COMMENT ON COLUMN rent_user.userlicense is '����';
COMMENT ON COLUMN rent_user.userstatus is '��������';
COMMENT ON COLUMN rent_user.rdate is '���Գ�¥';


select * from rent_user;

1. ���
 
1) id �ߺ� Ȯ��
SELECT COUNT(userid) as cnt
FROM rent_user
WHERE userid='user1';
 
 cnt
 ---
   0   �� �ߺ� ���� ����.
   
2) ���
- �Ϸù�ȣ ���� ��������
- MAX(mno): mno �÷��� ���߿� ���� ū ���� ����
- ���ڵ尡 ������ MAX �Լ��� ����� NULL�̵˴ϴ�.
SELECT MAX(mno) as mno FROM member;
 MNO
 ------
 NULL
 
 - NULL ���� ��Ģ������ ����� NULL�̵˴ϴ�. �ǹ� ����.
SELECT MAX(mno)+1 as mno FROM member;
 MNO
 ------
 NULL
 
 - NVL(��, ��ȯ�� ��): ���� NULL�̸� ��ȯ�� ������ ����, 
   NULL�� 0���� ����
SELECT NVL(MAX(mno), 0) as mno FROM member;
 MNO
 ------
   0
   
- NULL�� ó���ϸ鼭 ���ο� �ִ밪 ���� SQL
SELECT NVL(MAX(mno), 0)+1 as mno FROM member;
 MNO
 ------
   1
 
-- ������ ȸ�� ����

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'manager01', 'QNA������', 'manager01', '12345', '���� Ư���� ���α�', '��ö��', '010-0000-0000', '20��~30��', 'M', '1�� ����', 'M', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'manager02', 'ȸ��������', 'manager02', '12345', '���� Ư���� ���α�', '��ö��', '010-1111-1111', '20��~30��', 'W', '1�� ����', 'M', sysdate);


-- ���� ȸ�� ����
INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user01', 'ȸ��01', 'user01', '12345', '���� Ư���� ���α�', '��ö��', '010-0000-0000', '20��~30��', 'M', '1�� ����', 'Y', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user02', 'ȸ��02', 'user02', '12345', '���� Ư���� ���α�', '��ö��', '010-1111-1111', '20��~30��', 'W', '1�� ����', 'N', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user03', 'ȸ��03', 'user03', '12345', '���� Ư���� ���α�', '��ö��', '010-2222-2222', '30��~40��', 'W', '2�� �ڵ�', 'Y', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user04', 'ȸ��04', 'user04', '12345', '���� Ư���� ���α�', '��ö��', '010-3333-3333', '30��~40��', 'M', '2�� �ڵ�', 'H', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user05', 'ȸ��05', 'user05', '12345', '���� Ư���� ���α�', '��ö��', '010-4444-4444', '40��~50��', 'M', '����', 'Y', sysdate);
 
-- �׷캰 ����ȸ�� ����

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'team01', '������', 'team01', '12345', '���� Ư���� ���α�', '��ö��', '010-0000-0000', '20��~30��', 'M', '1�� ����', 'Y', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'team02', '��������', 'team02', '12345', '���� Ư���� ���α�', '��ö��', '010-1111-1111', '20��~30��', 'W', '1�� ����', 'N', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'team03', '���ۺ�����', 'team03', '12345', '���� Ư���� ���α�', '��ö��', '010-2222-2222', '30��~40��', 'W', '2�� �ڵ�', 'Y', sysdate);

 
 
2. ���
- �˻��� ���� �ʴ� ���, ��ü ��� ���

SELECT userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate
FROM rent_user
ORDER BY userno ASC;
 
 USERNO USERID    USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ --------- -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      1 manager01 QNA������   manager01  12345       ���� Ư���� ���α�   ��ö��          010-0000-0000 20��~30�� M       1�� ����       M          2019-05-30 17:29:52.0
      2 manager02 ȸ��������    manager02  12345       ���� Ư���� ���α�   ��ö��          010-1111-1111 20��~30�� W       1�� ����       M          2019-05-30 17:31:04.0
      3 user01    ȸ��01     user01     12345       ���� Ư���� ���α�   ��ö��          010-0000-0000 20��~30�� M       1�� ����       Y          2019-05-30 17:31:13.0
      4 user02    ȸ��02     user02     12345       ���� Ư���� ���α�   ��ö��          010-1111-1111 20��~30�� W       1�� ����       N          2019-05-30 17:31:14.0
      5 user03    ȸ��03     user03     12345       ���� Ư���� ���α�   ��ö��          010-2222-2222 30��~40�� W       2�� �ڵ�       Y          2019-05-30 17:31:15.0
      6 user04    ȸ��04     user04     12345       ���� Ư���� ���α�   ��ö��          010-3333-3333 30��~40�� M       2�� �ڵ�       H          2019-05-30 17:31:16.0
      7 user05    ȸ��05     user05     12345       ���� Ư���� ���α�   ��ö��          010-4444-4444 40��~50�� M       ����          Y          2019-05-30 17:31:17.0
      8 team01    ������      team01     12345       ���� Ư���� ���α�   ��ö��          010-0000-0000 20��~30�� M       1�� ����       Y          2019-05-30 17:31:26.0
      9 team02    ��������     team02     12345       ���� Ư���� ���α�   ��ö��          010-1111-1111 20��~30�� W       1�� ����       N          2019-05-30 17:31:27.0
     10 team03    ���ۺ�����   team03     12345       ���� Ư���� ���α�   ��ö��          010-2222-2222 30��~40�� W       2�� �ڵ�       Y          2019-05-30 17:31:28.0
   
3. ��ȸ
 
1) userno1 ��� ���� ����

SELECT userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate
FROM rent_user
WHERE userno = 1;

 USERNO USERID    USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ --------- -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      1 manager01 QNA������   manager01  12345       ���� Ư���� ���α�   ��ö��          010-0000-0000 20��~30�� M       1�� ����       M          2019-05-30 17:29:52.0

      
SELECT userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate
FROM rent_user
WHERE userid = 'user01';
 
 USERNO USERID USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ ------ -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      3 user01 ȸ��01     user01     12345       ���� Ư���� ���α�   ��ö��          010-0000-0000 20��~30�� M       1�� ����       Y          2019-05-30 17:31:13.0

   
4. ����

UPDATE rent_user
SET userid='user12', username='ȸ��12', userpasswd='user12', userzipcode='54321', useraddress1='���� Ư���� ���빮��', useraddress2='ȫ����', usertel='010-1212-1212', userage='30��~40��', usersex='M', userlicense='2��', userstatus='N'
WHERE userno = 4;

USERNO USERID USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ ------ -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      4 user12 ȸ��12     user12     54321       ���� Ư���� ���빮��  ȫ����          010-1212-1212 30��~40�� M       2��          N          2019-05-30 17:31:14.0
 

 
5. �н����� ����
1) �н����� �˻�
SELECT COUNT(userno) as cnt
FROM rent_user
WHERE userno=3 AND userpasswd='user01';

CNT
 ---
   1

 
2) �н����� ����
UPDATE rent_user
SET userpasswd='user11'
WHERE userno=3;

USERNO USERID USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ ------ -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      3 user01 ȸ��01     user11     12345       ���� Ư���� ���α�   ��ö��          010-0000-0000 20��~30�� M       1�� ����       Y          2019-05-30 17:31:13.0

 
6. ����
1) ��� ����
DELETE FROM user_rent;
 
2) Ư�� ȸ�� ����
DELETE FROM user_rent
WHERE userno=4;
 
 
7. �α���
SELECT COUNT(userno) as cnt
FROM rent_user
WHERE userid='user01' AND userpasswd='user11';

 CNT
 ---
   1

   
   
   SELECT userid, username, usertel
  FROM rent_user
  WHERE username = '����ǳ' AND usertel = '010-0000-0000';
   
