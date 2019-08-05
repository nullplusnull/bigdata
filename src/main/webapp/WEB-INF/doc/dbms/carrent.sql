/**********************************/
/* Table Name: �� ��Ʈ */
/**********************************/
--1 ���̺�
DROP TABLE carrent;
CREATE TABLE carrent(
		carrentno                     		    NUMBER(10)		                        NOT NULL   PRIMARY KEY,
		carsize                          		  VARCHAR2(100)		                      NOT NULL,
		carcolor                              VARCHAR2(100)                        NOT NULL,
		carproducer                         VARCHAR2(100)                        NOT NULL,
		carname                       		    VARCHAR2(100)		                      NOT NULL,
		price                         		      NUMBER(10)		                        NOT NULL,
		power                         		    VARCHAR2(100)		                      NOT NULL,
		blackbox                      		    CHAR(1)		         DEFAULT 'N'		 NOT NULL,
		navigation                    		    CHAR(1)		         DEFAULT 'N'		 NOT NULL,
		airingseat                    		    CHAR(1)		         DEFAULT 'N'		 NOT NULL,
		age                           		      NUMBER(3)		                          NOT NULL,
		condition                            VARCHAR2(100)    DEFAULT '����' NOT NULL,
    rent_file1                             VARCHAR2(1000)                               NULL,
    rent_size1                            VARCHAR2(1000)                                  NULL,
    rent_thumb1                        VARCHAR2(1000)                               NULL,
		rdate                         		      DATE		                                    NOT NULL
);

COMMENT ON TABLE carrent is '�� ��Ʈ';
COMMENT ON COLUMN carrent.carrentno is '����Ʈ��ǰ��ȣ';
COMMENT ON COLUMN carrent.carsize is '����';
COMMENT ON COLUMN carrent.carcolor is '������';
COMMENT ON COLUMN carrent.carproducer is '������';
COMMENT ON COLUMN carrent.carname is '������';
COMMENT ON COLUMN carrent.price is '�ð��� ���';
COMMENT ON COLUMN carrent.power is '����';
COMMENT ON COLUMN carrent.blackbox is '���ڽ�';
COMMENT ON COLUMN carrent.navigation is '�׺���̼�';
COMMENT ON COLUMN carrent.airingseat is '��ǳ��Ʈ';
COMMENT ON COLUMN carrent.age is '��������';
COMMENT ON COLUMN carrent.condition is '�� ����';
COMMENT ON COLUMN carrent.rent_file1 is '÷�����ϸ�';
COMMENT ON COLUMN carrent.rent_size1 is '÷������ũ��';
COMMENT ON COLUMN carrent.rent_thumb1 is '�����';
COMMENT ON COLUMN carrent.rdate is '�����';

SELECT NVL(MAX(caterentno), 0)+1 as carrentno FROM carrent


--2. ���

INSERT INTO CARRENT(carrentno, carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rdate)
VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         '����', '����ũ', 'white', 'KIA', 200000,
         '���ָ�', 'Y', 'Y', 'Y',
         24, '����', sysdate);
         
INSERT INTO CARRENT(carrentno, carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rdate)
VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         '����', 'ī�Ϲ�', 'white', 'KIA', 250000,
         '���ָ�', 'Y', 'Y', 'Y',
         24, '����', sysdate);
         
INSERT INTO CARRENT(carrentno, carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rdate)
VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         '����', '�Ϸ�', 'white', 'KIA', 250000,
         '����', 'Y', 'Y', 'Y',
         24, '����', sysdate);         
         
         
INSERT INTO CARRENT(carrentno,
                               carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rent_file1, rent_size1, rent_thumb1, rdate)
VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         #{carsize}, #{carname}, #{carcolor}, #{carproducer}, #{price},
         #{power}, #{blackbox}, #{navigation}, #{airingseat},
         #{age}, #{condition}, #{rent_file1}, #{rent_size1}, #{rent_thumb1}, sysdate)


select*from carrent
-- 3. ���
SELECT carrentno,carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rdate
FROM carrent
ORDER BY carrentno ASC;


-- 4. ��ȸ
SELECT carrentno, carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rent_file1, rent_size1, rdate
FROM carrent
WHERE carrentno = 1;

-- 5. ����
UPDATE carrent
SET carname='����Ʈ', price=70000, power='����'
WHERE carrentno=1;

-- 6.�Ѱ��� ���ڵ� ����
DELETE FROM carrent
WHERE carrentno=3;

-- 7. ��� ���ڵ� ����
DELETE FROM carrent;





    INSERT INTO CARRENT(carrentno,
                                   carsize, carname, carcolor, carproducer, price,
                                   power, blackbox, navigation, airingseat,
                                   age, condition, rent_file1, rent_size1, rent_thumb1, rdate)
    
    VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent),
               '����', '�Ϸ�', 'white','KIA', 250000,
               '����', 'Y', 'Y', 'Y',
                 24, '����', 'abc', 'abcd', 'abcsd', sysdate)
                
                
                
   VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         '����', '�Ϸ�', 'white', 'KIA', 250000,
         '����', 'Y', 'Y', 'Y',
         24, '����', sysdate);                   
                

                
                
select rent_thumb1 from carrent;


select * from carrent;



