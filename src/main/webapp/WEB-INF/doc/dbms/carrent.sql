/**********************************/
/* Table Name: 차 렌트 */
/**********************************/
--1 테이블
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
		condition                            VARCHAR2(100)    DEFAULT '정상' NOT NULL,
    rent_file1                             VARCHAR2(1000)                               NULL,
    rent_size1                            VARCHAR2(1000)                                  NULL,
    rent_thumb1                        VARCHAR2(1000)                               NULL,
		rdate                         		      DATE		                                    NOT NULL
);

COMMENT ON TABLE carrent is '차 렌트';
COMMENT ON COLUMN carrent.carrentno is '차렌트상품번호';
COMMENT ON COLUMN carrent.carsize is '구분';
COMMENT ON COLUMN carrent.carcolor is '차색깔';
COMMENT ON COLUMN carrent.carproducer is '제조사';
COMMENT ON COLUMN carrent.carname is '차량명';
COMMENT ON COLUMN carrent.price is '시간당 요금';
COMMENT ON COLUMN carrent.power is '연료';
COMMENT ON COLUMN carrent.blackbox is '블랙박스';
COMMENT ON COLUMN carrent.navigation is '네비게이션';
COMMENT ON COLUMN carrent.airingseat is '통풍시트';
COMMENT ON COLUMN carrent.age is '연령제한';
COMMENT ON COLUMN carrent.condition is '차 상태';
COMMENT ON COLUMN carrent.rent_file1 is '첨부파일명';
COMMENT ON COLUMN carrent.rent_size1 is '첨부파일크기';
COMMENT ON COLUMN carrent.rent_thumb1 is '썸네일';
COMMENT ON COLUMN carrent.rdate is '등록일';

SELECT NVL(MAX(caterentno), 0)+1 as carrentno FROM carrent


--2. 등록

INSERT INTO CARRENT(carrentno, carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rdate)
VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         '소형', '스파크', 'white', 'KIA', 200000,
         '가솔린', 'Y', 'Y', 'Y',
         24, '정상', sysdate);
         
INSERT INTO CARRENT(carrentno, carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rdate)
VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         '대형', '카니발', 'white', 'KIA', 250000,
         '가솔린', 'Y', 'Y', 'Y',
         24, '정상', sysdate);
         
INSERT INTO CARRENT(carrentno, carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rdate)
VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         '중형', '니로', 'white', 'KIA', 250000,
         '전기', 'Y', 'Y', 'Y',
         24, '정상', sysdate);         
         
         
INSERT INTO CARRENT(carrentno,
                               carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rent_file1, rent_size1, rent_thumb1, rdate)
VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         #{carsize}, #{carname}, #{carcolor}, #{carproducer}, #{price},
         #{power}, #{blackbox}, #{navigation}, #{airingseat},
         #{age}, #{condition}, #{rent_file1}, #{rent_size1}, #{rent_thumb1}, sysdate)


select*from carrent
-- 3. 목록
SELECT carrentno,carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rdate
FROM carrent
ORDER BY carrentno ASC;


-- 4. 조회
SELECT carrentno, carsize, carname, carcolor, carproducer, price, 
                               power, blackbox, navigation, airingseat, 
                               age, condition, rent_file1, rent_size1, rdate
FROM carrent
WHERE carrentno = 1;

-- 5. 수정
UPDATE carrent
SET carname='스마트', price=70000, power='전기'
WHERE carrentno=1;

-- 6.한건의 레코드 삭제
DELETE FROM carrent
WHERE carrentno=3;

-- 7. 모든 레코드 삭제
DELETE FROM carrent;





    INSERT INTO CARRENT(carrentno,
                                   carsize, carname, carcolor, carproducer, price,
                                   power, blackbox, navigation, airingseat,
                                   age, condition, rent_file1, rent_size1, rent_thumb1, rdate)
    
    VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent),
               '중형', '니로', 'white','KIA', 250000,
               '전기', 'Y', 'Y', 'Y',
                 24, '정상', 'abc', 'abcd', 'abcsd', sysdate)
                
                
                
   VALUES((SELECT NVL(MAX(carrentno), 0)+1 as carrentno FROM carrent), 
         '중형', '니로', 'white', 'KIA', 250000,
         '전기', 'Y', 'Y', 'Y',
         24, '정상', sysdate);                   
                

                
                
select rent_thumb1 from carrent;


select * from carrent;



