/**********************************/
/* Table Name: 렌트 기간 */
/**********************************/
drop table rentperiod;

CREATE TABLE rentperiod(
		rentno                        		NUMBER(10)		 NOT NULL,
		carrentno                 		NUMBER(10)		 NOT NULL,
		startday                      		DATE		 NOT NULL,
		endday                        		DATE		 NOT NULL,
		scheduleday                   		DATE		 NOT NULL,
		PRIMARY KEY (rentno),
		FOREIGN KEY (carrentno) REFERENCES carrent (carrentno)

);

COMMENT ON TABLE rentperiod is '렌트 기간';
COMMENT ON COLUMN rentperiod.rentno is '렌트 번호';
COMMENT ON COLUMN rentperiod.carrentno is '렌트 상품 번호';
COMMENT ON COLUMN rentperiod.startday is '렌트 시작일';
COMMENT ON COLUMN rentperiod.endday is '렌트 반납일';
COMMENT ON COLUMN rentperiod.scheduleday is '렌트 예정반납일';



--2. 등록

INSERT INTO RENTPERIOD(rentno, carrentno, startday, endday, scheduleday)
VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
          1, to_date('05-20-2019','mm-dd-yyyy'), to_date('05-25-2019','mm-dd-yyyy'), to_date('05-25-2019','mm-dd-yyyy'));

INSERT INTO RENTPERIOD(rentno, carrentno, startday, endday, scheduleday)
VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
          1, to_date('06-20-2019','mm-dd-yyyy'), to_date('06-25-2019','mm-dd-yyyy'), to_date('05-25-2019','mm-dd-yyyy'));

INSERT INTO RENTPERIOD(rentno, carrentno, startday, endday, scheduleday)
VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
          1, to_date('07-20-2019','mm-dd-yyyy'), to_date('07-25-2019','mm-dd-yyyy'), to_date('05-25-2019','mm-dd-yyyy'));


-- 3. 목록
SELECT rentno, carrentno, startday, endday, scheduleday
FROM rentperiod
ORDER BY rentno ASC;

SELECT*FROM RENTPERIOD;

-- 4. 조회
SELECT rentno, carrentno, startday, endday, scheduleday
FROM rentperiod
WHERE rentno = 1;

-- 5. 수정
UPDATE rentperiod
SET startday=to_date('05-23-2019','mm-dd-yyyy')
WHERE rentno=1;

-- 6.한건의 레코드 삭제
DELETE FROM rentperiod
WHERE rentperiodno=3;

-- 7. 모든 레코드 삭제
DELETE FROM rentperiod;



