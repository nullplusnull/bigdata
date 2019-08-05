/**********************************/
/* Table Name: ��Ʈ �Ⱓ */
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

COMMENT ON TABLE rentperiod is '��Ʈ �Ⱓ';
COMMENT ON COLUMN rentperiod.rentno is '��Ʈ ��ȣ';
COMMENT ON COLUMN rentperiod.carrentno is '��Ʈ ��ǰ ��ȣ';
COMMENT ON COLUMN rentperiod.startday is '��Ʈ ������';
COMMENT ON COLUMN rentperiod.endday is '��Ʈ �ݳ���';
COMMENT ON COLUMN rentperiod.scheduleday is '��Ʈ �����ݳ���';



--2. ���

INSERT INTO RENTPERIOD(rentno, carrentno, startday, endday, scheduleday)
VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
          1, to_date('05-20-2019','mm-dd-yyyy'), to_date('05-25-2019','mm-dd-yyyy'), to_date('05-25-2019','mm-dd-yyyy'));

INSERT INTO RENTPERIOD(rentno, carrentno, startday, endday, scheduleday)
VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
          1, to_date('06-20-2019','mm-dd-yyyy'), to_date('06-25-2019','mm-dd-yyyy'), to_date('05-25-2019','mm-dd-yyyy'));

INSERT INTO RENTPERIOD(rentno, carrentno, startday, endday, scheduleday)
VALUES((SELECT NVL(MAX(rentno), 0)+1 as rentno FROM rentperiod),
          1, to_date('07-20-2019','mm-dd-yyyy'), to_date('07-25-2019','mm-dd-yyyy'), to_date('05-25-2019','mm-dd-yyyy'));


-- 3. ���
SELECT rentno, carrentno, startday, endday, scheduleday
FROM rentperiod
ORDER BY rentno ASC;

SELECT*FROM RENTPERIOD;

-- 4. ��ȸ
SELECT rentno, carrentno, startday, endday, scheduleday
FROM rentperiod
WHERE rentno = 1;

-- 5. ����
UPDATE rentperiod
SET startday=to_date('05-23-2019','mm-dd-yyyy')
WHERE rentno=1;

-- 6.�Ѱ��� ���ڵ� ����
DELETE FROM rentperiod
WHERE rentperiodno=3;

-- 7. ��� ���ڵ� ����
DELETE FROM rentperiod;



