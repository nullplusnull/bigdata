CREATE TABLE coupon(
  couponno         NUMBER(10) NOT NULL PRIMARY KEY,                -- ������ȣ PK
  couponname      VARCHAR2(100)     NOT NULL,                           -- �����̸�
  use_condition     NUMBER(10)     NOT NULL,                                -- ���� �ݾ� �̻���� ��밡��
  expiry_date        DATE              NOT NULL,                                -- ��������
  discount_cost     NUMBER(10)     NOT NULL,                                -- ���αݾ�
  coupon_file        VARCHAR2(300)   NULL ,                                    -- ���� �̹���
  file_size             NUMBER(10)     NULL,                                       -- ���� ������
  rdate                DATE              NOT NULL,                                -- ���� ��¥
  thumb              VARCHAR2(300) NULL,                                       -- �����
  visible               CHAR(1)          DEFAULT 'N'    NOT NULL           -- ���� Ȱ��ȭ ����           
); 

select *
from coupon c, user_coupon u
where c.couponno = 

select couponno
from user_coupon


select * from COUPON;
drop table coupon cascade CONSTRAINTS PURGE;

select coupon_use
from user_coupon
where couponno = 1 and userno = 2



insert into COUPON(couponno, couponname, use_condition, expiry_date, discount_cost,coupon_file,file_size, rdate)
VALUES((SELECT NVL(MAX(couponno), 0)+1 as couponno FROM coupon), '����Ư������', 10000, sysdate , 3000, './asfas.jpg', 3000 ,sysdate );

          
INSERT INTO rentperiod(rentno, carrentno, startday, endday, startplace, endplace)
     VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
              1, sysdate, sysdate, '����', '�Ѷ�');   

select * from coupon;


CREATE TABLE user_coupon(
  user_couponno      NUMBER(10)    NOT NULL PRIMARY KEY,                 -- �߱޵� ������ȣ
  couponno            NUMBER(10)      NOT NULL,                                   -- ������ȣ (FK)
  userno                 NUMBER(10)     NOT NULL,                                    -- �߱޹��� ������ȣ(FK)
  coupon_use          CHAR(1)          DEFAULT 'N'    NOT NULL  ,              -- ���� ��� ����
  limit_date          DATE              NOT NULL,                                      -- ���� ��� ������
  rdate                DATE              NOT NULL,                                       -- ���� �߱���
  FOREIGN KEY (userno) REFERENCES rent_user(userno),
  FOREIGN KEY (couponno) REFERENCES coupon(couponno)
);

insert into USER_COUPON(user_couponno, couponno, userno, coupon_use, limit_date, rdate )
values((SELECT NVL(MAX(user_couponno), 0)+1 as user_couponno FROM user_coupon), 
2, 1, 'N' , sysdate, sysdate);

drop table user_coupon;
select * from USER_COUPON;


CREATE TABLE management(
  managementno NUMBER(10) NOT NULL PRIMARY KEY,
  paymentno      NUMBER(10)     NOT NULL,
  management_state VARCHAR2(50)    NOT NULL,
  FOREIGN KEY (paymentno) REFERENCES payment (paymentno)
);

drop table management;
select * from management;


select m.managementno, p.payment_price, p.userno, r.reservation_state,
r.rdate, t.startday, t.endday, t.scheduleday, t.startplace, t.endplace, c.carname, c.condition
from MANAGEMENT m, PAYMENT p, reservation r, RENTPERIOD t, carrent c
where m.paymentno = p.paymentno and p.reservationno = r.reservationno
and r.rentno = t.rentno and p.payment_cancel = 'N' and t.carrentno = c.carrentno




/* Table Name: �� ��Ʈ */
/**********************************/
CREATE TABLE carrent(
		carrentno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		carsize                       		VARCHAR2(20)		 NOT NULL,
		carcolor                      		VARCHAR2(30)		 NOT NULL,
		carproducer                   		VARCHAR2(30)		 NOT NULL,
		carname                       		VARCHAR2(30)		 NOT NULL,
		price                         		NUMBER(10)		 NOT NULL,
		power                         		VARCHAR2(20)		 NOT NULL,
		blackbox                      		CHAR(1)		 NOT NULL,
		navigation                    		CHAR(1)		 NOT NULL,
		airingseat                    		CHAR(1)		 NOT NULL,
		age                           		NUMBER(3)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		condition                     		VARCHAR2(30)		 DEFAULT '����'		 NOT NULL,
		rent_file1                    		VARCHAR2(30)		 NULL ,
		rent_size1                    		NUMBER(10)		 NULL 
);

select * from CARRENT;



/**********************************/
/* Table Name: ��Ʈ�Ⱓ */
/**********************************/
drop table rentperiod CASCADE CONSTRAINT;
CREATE TABLE rentperiod(
		rentno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		carrentno                     		NUMBER(10)		 NOT NULL,
		startday                      		DATE		 NOT NULL,
		endday                        		DATE		 NOT NULL,
		scheduleday                   		DATE		 NULL,
		startplace                        VARCHAR2(100) NOT NULL,
		endplace                        VARCHAR2(100) NOT NULL,
  FOREIGN KEY (carrentno) REFERENCES carrent (carrentno)
);

delete from rentperiod;
select * from rentperiod;


SELECT count(rentno) as cnt
FROM rentperiod
where startday >= to_date(#{startday},'YYYY-MM-DD') AND endday <= to_date(#{endday},'YYYY-MM-DD') 

AND carrentno=#{carrentno}


delete from PAYMENT;

delete from RESERVATION;
select * from reservation;
select * from payment;

INSERT INTO rentperiod(rentno, carrentno, startday, endday, startplace, endplace)
     VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
              1, sysdate, sysdate, '����', '�Ѷ�');       

UPDATE reservation
SET        reservation_state='���� �Ϸ�'
WHERE   reservationno=1


UPDATE carrent
    SET price= #{price}, condition= #{condition}, 
      rent_file1=#{rent_file1}, rent_size1=#{rent_size1}, rent_thumb1=#{rent_thumb1}, age=#{age}
    WHERE carrentno = #{carrentno}
              
INSERT INTO reservation(reservationno, deposit, reservation_state, rentno, userno)
VALUES((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 1000, '���� ���', 1, 1 );



delete  from rentperiod;
select * from rentperiod;
select * from reservation;
delete from reservation;


select count(*) 
from RENTPERIOD
where startday >= to_date('2019-07-06','YYYY-MM-DD') 
AND endday <= to_date('2019-07-07','YYYY-MM-DD') AND carrentno=5;





select count(*)
from rentperiod
where carrentno=5 AND startday >= to_date( '2019-07-06 08:00:00' ,'YYYY-MM-DD HH24:MI:SS') AND
             endday <= to_date('2019-07-07 08:00:00','YYYY-MM-DD HH24:MI:SS')

INSERT INTO rentperiod
VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod), 1, to_date('2019-01-10 23:11:11','YYYY-MM-DD HH24:MI:SS') , sysdate, sysdate, '����', '�λ�');


-- ����
select r.startday, r.endday, c.power, c.carname, c.price, (r.endday - r.startday) as hour, (r.endday - r.startday) * c.price as total_price
from CARRENT c, RENTPERIOD r
where c.carrentno = r.carrentno and rentno=3;


SELECT c.categrpno, c.name,
               t.cateno, t.categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt, t.rdate
     FROM categrp c, cate t  
     WHERE c.categrpno = t.categrpno
     ORDER BY c.categrpno ASC, t.seqno ASC;


INSERT INTO CARRENT(carrentno,
                                   carsize, carname, carcolor, carproducer, price,
                                   power, blackbox, navigation, airingseat,
                                   age, condition, rent_file1, rent_size1, rent_thumb1, rdate)
    
    VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent),
                #{carsize}, #{carname}, #{carcolor}, #{carproducer}, #{price},
                #{power}, #{blackbox}, #{navigation}, #{airingseat},
                #{age}, #{condition}, #{rent_file1}, #{rent_size1}, #{rent_thumb1}, sysdate)
/**********************************/
/* Table Name: ���� */
/**********************************/

drop table reservation;

CREATE TABLE reservation(
		reservationno                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		reservation_state            		VARCHAR(100)		 DEFAULT '�������'	 NOT NULL,
		rentno                        		NUMBER(10)		 NOT NULL ,
		userno                            NUMBER(10)     NOT NULL ,
		rdate                              DATE              NOT NULL,
  FOREIGN KEY (rentno) REFERENCES rentperiod(rentno),
  FOREIGN KEY (userno) REFERENCES rent_user(userno)
);



select * from rent_user;


select p.startday, p.endday, p.carrentno
from rentperiod p, reservation r
where p.rentno = r.rentno and reservationno=6;

select * from reservation;
select * from rentperiod;

drop table reservation;
SELECT reservationno, deposit, reservation_cancel, reservation_cancel, rentno
FROM reservation
ORDER BY reservationno ASC


INSERT INTO reservation(reservationno, deposit, reservation_state, rentno, userno)
VALUES((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 1000, '���� ���', 1, 1 );


 reservationno deposit reservation_cancel rentperiodno


/**********************************/
/* Table Name: ȸ�� */
/**********************************/
drop table rent_user cascade constraint;

CREATE TABLE rent_user(
		userno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		userid                        		VARCHAR2(50)		 NOT NULL,
		username                      		VARCHAR2(20)		 NOT NULL,
		userpasswd                    		VARCHAR2(20)		 NOT NULL,
		useraddress                   		VARCHAR2(300)		 NOT NULL,
		userrefundaccount             NUMBER(10)   NULL,
		usertel                       		VARCHAR2(20)		 NOT NULL,
		userage                       		NUMBER(10)		 NOT NULL,
		usersex                        		CHAR(1)		 NOT NULL,
		userlicense                   		VARCHAR2(20)		 NOT NULL,
		userstatus                    		CHAR(1)		 NOT NULL,
		rdate                         		DATE		 NOT NULL
);

select * from rent_user;
drop table rent_user;
drop table account;


INSERT INTO rent_user(userno, userid, username, userpasswd, useraddress, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user01', 'ȸ��01', '1234', 
                                                  '���� Ư���� ���α�', '010-0000-0000', 26, 'M', '1�� ����', 'Y', sysdate);


/**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE payment(
		paymentno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		payment_price                   NUMBER(10)     NOT NULL, 
		payment_method                		VARCHAR2(50)		 NOT NULL,
		payment_method_num       NUMBER(10)     NOT NULL, 
		payment_cancel                		CHAR(1)		 DEFAULT 'N'		 NOT NULL,
		reservationno                 		NUMBER(10)		 NULL ,
		userno                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (userno) REFERENCES rent_user (userno),
  FOREIGN KEY (reservationno) REFERENCES reservation (reservationno)
);




drop table payment;
select * from payment;

INSERT INTO payment(paymentno, payment_price, payment_method, payment_cancel, reservationno, userno)
VALUES((SELECT NVL(MAX(paymentno), 0) +1 AS paymentno FROM payment), 200000, '������ü', 'N', 1, 1);


select p.startday, p.endday, p.carrentno
from rentperiod p, reservation r, payment pay
where p.rentno = r.rentno and r.reservationno= pay.reservationno

SELECT count(r.rentno) as cnt
FROM rentperiod r, reservation re, payment p
where re.rentno = r.rentno and re.reservationno= p.reservationno AND r.carrentno=5;

 and r.startday &gt;= to_date('2019-07-19','YYYY-MM-DD') AND 
r.endday &lt;= to_date('2019-07-20','YYYY-MM-DD') 

select * from rentperiod;
select * from reservation;
select * from payment;

/**********************************/
/* Table Name: ȯ�� */
/**********************************/

select * from refund;

CREATE TABLE refund(
		refundno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		paymentno                     		NUMBER(10)		 NULL ,
		refund_reason                  VARCHAR2(300)  NOT NULL,           
		rdate                              DATE              NOT NULL,
  FOREIGN KEY (paymentno) REFERENCES payment (paymentno)
);
select * from payment;
select * from refund;
INSERT INTO refund(refundno, paymentno, refund_reason, rdate)
VALUES((SELECT NVL(MAX(refundno), 0) +1 AS refundno FROM refund), 23, '�ܼ� ����', sysdate);

drop table refund;

COMMENT ON TABLE refund is 'ȯ��';
COMMENT ON COLUMN refund.refundno is 'ȯ�ҹ�ȣ';
COMMENT ON COLUMN refund.paymentno is '������ȣ';


/**********************************/
/* Table Name: ���ű�� */
/**********************************/
CREATE TABLE buyhistory(
		buyhistoryno                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		paymentno                     		NUMBER(10)		 NULL ,
  FOREIGN KEY (paymentno) REFERENCES payment (paymentno)
);

select * from buyhistory;
drop table buyhistory;

INSERT INTO buyhistory(buhistoryno, paymentno)
VALUES((SELECT NVL(MAX(buhistoryno), 0) +1 AS buhistoryno FROM buyhistory), 1);

COMMENT ON TABLE buyhistory is '���ű��';
COMMENT ON COLUMN buyhistory.buyhistoryno is '���ű�Ϲ�ȣ';
COMMENT ON COLUMN buyhistory.paymentno is '������ȣ';


/**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE account(
		accountno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		bank                          		VARCHAR2(10)		 NOT NULL,
		account_passwd                		NUMBER(10)		 NOT NULL,
		cash                          		NUMBER(12)		 NOT NULL,
		userno                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (userno) REFERENCES rent_user (userno)
);

drop table account;
select * from account;


INSERT INTO account(accountno, bank, account_passwd, cash, userno)
VALUES ((SELECT NVL(MAX(accountno), 0) +1 AS accountno FROM account), '����', 1234, 0, 1);

INSERT INTO account(accountno, bank, account_passwd, cash, userno)
VALUES ((SELECT NVL(MAX(accountno), 0) +1 AS accountno FROM account), '����', 1234, 1000000, 1);

INSERT INTO account(accountno, bank, cash, account_passwd)
VALUES ((SELECT NVL(MAX(accountno), 0) +1 AS accountno FROM account), 100000, 1234);

select * from account;

delete 

SELECT accountno, bank, account_passwd, cash, userno
FROM account
ORDER BY accountno ASC;

SELECT accountno, bank, account_passwd, cash, userno
FROM account
WHERE userno = 1
ORDER BY accountno ASC;

/**********************************/
/* Table Name: ��Ʈ�ı� */
/**********************************/
CREATE TABLE rentreview(
		reviewno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(50)		 NOT NULL,
		contents                      		VARCHAR2(300)		 NULL ,
		good                          		NUMBER(10)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		userno                        		NUMBER(10)		 NULL ,
		carrentno                     		NUMBER(10)		 NULL ,
  FOREIGN KEY (userno) REFERENCES rent_user (userno),
  FOREIGN KEY (carrentno) REFERENCES carrent (carrentno)
);


/**********************************/
/* Table Name: ������ */
/**********************************/
CREATE TABLE RENT_ADMIN(
		ADMINNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ADMINID                       		VARCHAR2(30)		 NOT NULL,
		ADMINNAME                     		VARCHAR2(20)		 NOT NULL,
		ADMINPASSWD                   		VARCHAR2(100)		 NOT NULL,
		ADMINTEL                      		VARCHAR2(20)		 NOT NULL,
		ADMINEMAIL                    		VARCHAR2(50)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE RENT_ADMIN is '������';
COMMENT ON COLUMN RENT_ADMIN.ADMINNO is '������ ��ȣ';
COMMENT ON COLUMN RENT_ADMIN.ADMINID is '������ID';
COMMENT ON COLUMN RENT_ADMIN.ADMINNAME is '������ �̸�';
COMMENT ON COLUMN RENT_ADMIN.ADMINPASSWD is '������ ��й�ȣ';
COMMENT ON COLUMN RENT_ADMIN.ADMINTEL is '������ ��ȭ��ȣ';
COMMENT ON COLUMN RENT_ADMIN.ADMINEMAIL is '������ ����';
COMMENT ON COLUMN RENT_ADMIN.RDATE is '������ �����';


/**********************************/
/* Table Name: ��ٱ��� */
/**********************************/
CREATE TABLE cart(
		cartno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		rdate                         		DATE		 NOT NULL,
		userno                        		NUMBER(10)		 NULL ,
		rentno                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (userno) REFERENCES rent_user (userno),
  FOREIGN KEY (rentno) REFERENCES rentperiod (rentno)
);

COMMENT ON TABLE cart is '��ٱ���';
COMMENT ON COLUMN cart.cartno is '��ٱ��Ϲ�ȣ';
COMMENT ON COLUMN cart.rdate is '��ٱ��ϳ�¥';
COMMENT ON COLUMN cart.userno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN cart.rentno is '��Ʈ ��ȣ';


/**********************************/
/* Table Name: ���ɻ�ǰ */
/**********************************/
CREATE TABLE interest(
		interestno                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		userno                        		NUMBER(10)		 NULL ,
		carrentno                     		NUMBER(10)		 NULL ,
  FOREIGN KEY (userno) REFERENCES rent_user (userno),
  FOREIGN KEY (carrentno) REFERENCES carrent (carrentno)
);

COMMENT ON TABLE interest is '���ɻ�ǰ';
COMMENT ON COLUMN interest.interestno is '���ɻ�ǰ��ȣ';
COMMENT ON COLUMN interest.userno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN interest.carrentno is '����Ʈ��ǰ��ȣ';


/**********************************/
/* Table Name: RTQ */
/**********************************/
CREATE TABLE RTQ(
		RTQNO                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		RTQ_CONTENTS                  		CLOB(4000)		 NOT NULL,
		RTQ_FILE1                     		VARCHAR2(30)		 NULL ,
		RTQ_SIZE1                     		NUMBER(10)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		userno                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (userno) REFERENCES rent_user (userno)
);

COMMENT ON TABLE RTQ is 'RTQ';
COMMENT ON COLUMN RTQ.RTQNO is '�ǽð� 1:1 ���� ��ȣ';
COMMENT ON COLUMN RTQ.RTQ_CONTENTS is '1:1 ���� ����';
COMMENT ON COLUMN RTQ.RTQ_FILE1 is '÷�����ϸ�';
COMMENT ON COLUMN RTQ.RTQ_SIZE1 is '÷������ũ��';
COMMENT ON COLUMN RTQ.RDATE is '���� �ð�';
COMMENT ON COLUMN RTQ.userno is 'ȸ�� ��ȣ';


/**********************************/
/* Table Name: �������� */
/**********************************/
CREATE TABLE NOTICE(
		NOTICENO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NOTICE_TITLE                  		VARCHAR2(200)		 NOT NULL,
		NOTICE_CONTENTS               		CLOB(4000)		 NOT NULL,
		NOTICE_COUNT                  		NUMBER(10)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		NOTICE_FILE1                  		VARCHAR2(30)		 NULL ,
		NOTICE_SIZE1                  		NUMBER(10)		 NULL ,
		RECOMEN                       		NUMBER(10)		 NULL ,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (ADMINNO) REFERENCES RENT_ADMIN (ADMINNO)
);

COMMENT ON TABLE NOTICE is '��������';
COMMENT ON COLUMN NOTICE.NOTICENO is '�������׹�ȣ';
COMMENT ON COLUMN NOTICE.NOTICE_TITLE is '�������� ����';
COMMENT ON COLUMN NOTICE.NOTICE_CONTENTS is '�������� ����';
COMMENT ON COLUMN NOTICE.NOTICE_COUNT is '�������� ��ȸ��';
COMMENT ON COLUMN NOTICE.RDATE is '�������� �����';
COMMENT ON COLUMN NOTICE.NOTICE_FILE1 is '÷�����ϸ�';
COMMENT ON COLUMN NOTICE.NOTICE_SIZE1 is '÷������ ũ��';
COMMENT ON COLUMN NOTICE.RECOMEN is '��õ ��';
COMMENT ON COLUMN NOTICE.ADMINNO is '������ ��ȣ';


/**********************************/
/* Table Name: �������� ��� */
/**********************************/
CREATE TABLE NOTICE_REPLY(
		NOTICE_REPLYNO                		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NREPLY_CONTENTS               		CLOB(4000)		 NOT NULL,
		NREPLY_PASSWD                 		VARCHAR2(30)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		NREPLY_FILE1                  		VARCHAR2(30)		 NULL ,
		NREPLY_SIZE1                  		NUMBER(10)		 NULL ,
		NOTICENO                      		NUMBER(10)		 NULL ,
		userno                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (NOTICENO) REFERENCES NOTICE (NOTICENO),
  FOREIGN KEY (userno) REFERENCES rent_user (userno)
);

COMMENT ON TABLE NOTICE_REPLY is '�������� ���';
COMMENT ON COLUMN NOTICE_REPLY.NOTICE_REPLYNO is '��۹�ȣ';
COMMENT ON COLUMN NOTICE_REPLY.NREPLY_CONTENTS is '��� ����';
COMMENT ON COLUMN NOTICE_REPLY.NREPLY_PASSWD is '��� ��й�ȣ';
COMMENT ON COLUMN NOTICE_REPLY.RDATE is '��� �����';
COMMENT ON COLUMN NOTICE_REPLY.NREPLY_FILE1 is '÷�����ϸ�';
COMMENT ON COLUMN NOTICE_REPLY.NREPLY_SIZE1 is '÷������ũ��';
COMMENT ON COLUMN NOTICE_REPLY.NOTICENO is '�������׹�ȣ';
COMMENT ON COLUMN NOTICE_REPLY.userno is 'ȸ�� ��ȣ';


/**********************************/
/* Table Name: Q&A */
/**********************************/
CREATE TABLE QNA(
		QNANO                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		QNA_TITLE                     		VARCHAR2(200)		 NOT NULL,
		QNA_CONTENTS                  		CLOB(4000)		 NOT NULL,
		QNA_PASSWD                    		VARCHAR2(30)		 NOT NULL,
		QNA_FILE1                     		VARCHAR2(30)		 NULL ,
		QNA_SIZE1                     		NUMBER(10)		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		userno                        		NUMBER(10)		 NULL ,
  FOREIGN KEY (userno) REFERENCES rent_user (userno)
);

COMMENT ON TABLE QNA is 'Q&A';
COMMENT ON COLUMN QNA.QNANO is 'Q&A��ȣ';
COMMENT ON COLUMN QNA.QNA_TITLE is 'Q&A ����';
COMMENT ON COLUMN QNA.QNA_CONTENTS is 'Q&A ����';
COMMENT ON COLUMN QNA.QNA_PASSWD is 'Q&A �� ��й�ȣ';
COMMENT ON COLUMN QNA.QNA_FILE1 is '÷�����ϸ�';
COMMENT ON COLUMN QNA.QNA_SIZE1 is '÷������ ũ��';
COMMENT ON COLUMN QNA.RDATE is 'Q&A �����';
COMMENT ON COLUMN QNA.userno is 'ȸ�� ��ȣ';


/**********************************/
/* Table Name: Q&A ��� */
/**********************************/
CREATE TABLE QNA_REPLY(
		QNA_REPLYNO                   		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		QREPLY_TITLE                  		VARCHAR2(200)		 NOT NULL,
		QREPLY_CONTENTS               		CLOB(4000)		 NOT NULL,
		QREPLY_PASSWD                 		VARCHAR2(30)		 NOT NULL,
		QREPLY_FILE1                  		VARCHAR2(30)		 NULL ,
		QREPLY_SIZE1                  		NUMBER(10)		 NULL ,
		QNANO                         		NUMBER(10)		 NULL ,
  FOREIGN KEY (QNANO) REFERENCES QNA (QNANO)
);

COMMENT ON TABLE QNA_REPLY is 'Q&A ���';
COMMENT ON COLUMN QNA_REPLY.QNA_REPLYNO is 'Q&A ��� ��ȣ';
COMMENT ON COLUMN QNA_REPLY.QREPLY_TITLE is 'Q&A �������';
COMMENT ON COLUMN QNA_REPLY.QREPLY_CONTENTS is 'Q&A ��� ����';
COMMENT ON COLUMN QNA_REPLY.QREPLY_PASSWD is 'Q&A ��� �� ��й�ȣ';
COMMENT ON COLUMN QNA_REPLY.QREPLY_FILE1 is '÷�����ϸ�';
COMMENT ON COLUMN QNA_REPLY.QREPLY_SIZE1 is '÷������ ũ��';
COMMENT ON COLUMN QNA_REPLY.QNANO is 'Q&A��ȣ';





create table tb_members(
  num number(3),
  name varchar2(50),
  phone varchar2(50),
  point number(3)
);

insert into tb_members(num, name, phone, point) values(1, '�����', '010-1111-2222', '40');
insert into tb_members(num, name, phone, point) values(2, '������', '010-1123-4522', '70');

declare
 v_num number(3);
 v_phone varchar2(50);
 
begin
	select num, phone into v_num, v_phone from tb_members where name='�����';
	dbms_output.put_line('��ȣ        ����ó');
	dbms_output.put_line(v_num || ' ' || v_phone);
end;
 





CREATE OR REPLACE 

DECLARE
 v_name varchar2(20);
BEGIN
	v_name := 'ȫ�浿';
	DBMS_OUTPUT.ENABLE;
	DBMS_OUTPUT.PUT_LINE('v_name' || v_name);
END;



CREATE OR REPLACE PROCEDURE update_sal 
     /* IN  Parameter */
     (v_empno    IN    NUMBER) 
         
     IS 

     BEGIN 

       UPDATE emp 
       SET sal = sal  * 1.1 
       WHERE empno = v_empno; 

       COMMIT; 

     END update_sal; 
























SELECT accountno, bank, account_passwd, cash, userno
  FROM account
  WHERE userno = 1
  ORDER BY accountno ASC;



INSERT INTO rentperiod(rentno, carrentno, startday, endday, startplace, endplace)
     VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
              1, sysdate, sysdate, '����', '�Ѷ�');       



              
              
              
              
              
              
              
              
              
              
              
              
              



