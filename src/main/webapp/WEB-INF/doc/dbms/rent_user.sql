-- 계좌번호 생성해야한다

/**********************************/
/* Table Name: 회원 */
/**********************************/
DROP TABLE rent_user;
drop table account;

CREATE TABLE rent_user(
    userno                            NUMBER(10)     NOT NULL    PRIMARY KEY,
    userid                            VARCHAR2(50)     NOT NULL UNIQUE,
    username                          VARCHAR2(20)     NOT NULL,
    userpasswd                        VARCHAR2(20)     NOT NULL,
    userzipcode                          VARCHAR2(5)        NULL, -- 우편번호, 12345
    useraddress1                         VARCHAR2(300)       NULL, -- 주소 1
    useraddress2                        VARCHAR2(300)       NULL, -- 주소 2
    usertel                           VARCHAR2(20)     NOT NULL,
    userage                           NUMBER(7)     NOT NULL,
    usersex                            CHAR(1)    NOT NULL,
    userlicense                       VARCHAR2(20)     NOT NULL,
    userstatus                        CHAR(1)    NOT NULL,
    rdate                             DATE     NOT NULL
);

COMMENT ON TABLE rent_user is '회원';
COMMENT ON COLUMN rent_user.userno is '회원 번호';
COMMENT ON COLUMN rent_user.userid is '회원ID';
COMMENT ON COLUMN rent_user.username is '회원이름';
COMMENT ON COLUMN rent_user.userpasswd is '비밀번호';
COMMENT ON COLUMN rent_user.userzipcode is '우편번호';
COMMENT ON COLUMN rent_user.useraddress1 is '주소1';
COMMENT ON COLUMN rent_user.useraddress2 is '주소2';
COMMENT ON COLUMN rent_user.usertel is '전화번호';
COMMENT ON COLUMN rent_user.userage is '연령';
COMMENT ON COLUMN rent_user.usersex is '성별';
COMMENT ON COLUMN rent_user.userlicense is '면허';
COMMENT ON COLUMN rent_user.userstatus is '계정상태';
COMMENT ON COLUMN rent_user.rdate is '가입날짜';


select * from rent_user;

1. 등록
 
1) id 중복 확인
SELECT COUNT(userid) as cnt
FROM rent_user
WHERE userid='user1';
 
 cnt
 ---
   0   ← 중복 되지 않음.
   
2) 등록
- 일련번호 산출 서브쿼리
- MAX(mno): mno 컬럼의 값중에 가장 큰 값을 산출
- 레코드가 없으면 MAX 함수의 결과는 NULL이됩니다.
SELECT MAX(mno) as mno FROM member;
 MNO
 ------
 NULL
 
 - NULL 값은 사칙연산의 결과도 NULL이됩니다. 의미 없음.
SELECT MAX(mno)+1 as mno FROM member;
 MNO
 ------
 NULL
 
 - NVL(값, 변환할 값): 값이 NULL이면 변환할 값으로 변경, 
   NULL을 0으로 변경
SELECT NVL(MAX(mno), 0) as mno FROM member;
 MNO
 ------
   0
   
- NULL을 처리하면서 새로운 최대값 산출 SQL
SELECT NVL(MAX(mno), 0)+1 as mno FROM member;
 MNO
 ------
   1
 
-- 관리용 회원 기준

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'manager01', 'QNA관리자', 'manager01', '12345', '서울 특별시 종로구', '관철동', '010-0000-0000', '20대~30대', 'M', '1종 보통', 'M', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'manager02', '회원관리자', 'manager02', '12345', '서울 특별시 종로구', '관철동', '010-1111-1111', '20대~30대', 'W', '1종 보통', 'M', sysdate);


-- 개인 회원 기준
INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user01', '회원01', 'user01', '12345', '서울 특별시 종로구', '관철동', '010-0000-0000', '20대~30대', 'M', '1종 보통', 'Y', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user02', '회원02', 'user02', '12345', '서울 특별시 종로구', '관철동', '010-1111-1111', '20대~30대', 'W', '1종 보통', 'N', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user03', '회원03', 'user03', '12345', '서울 특별시 종로구', '관철동', '010-2222-2222', '30대~40대', 'W', '2종 자동', 'Y', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user04', '회원04', 'user04', '12345', '서울 특별시 종로구', '관철동', '010-3333-3333', '30대~40대', 'M', '2종 자동', 'H', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'user05', '회원05', 'user05', '12345', '서울 특별시 종로구', '관철동', '010-4444-4444', '40대~50대', 'M', '대형', 'Y', sysdate);
 
-- 그룹별 공유회원 기준

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'team01', '개발팀', 'team01', '12345', '서울 특별시 종로구', '관철동', '010-0000-0000', '20대~30대', 'M', '1종 보통', 'Y', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'team02', '디자인팀', 'team02', '12345', '서울 특별시 종로구', '관철동', '010-1111-1111', '20대~30대', 'W', '1종 보통', 'N', sysdate);

INSERT INTO rent_user(userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate)
VALUES((SELECT NVL(MAX(userno), 0) +1 AS userno FROM rent_user), 'team03', '웹퍼블리셔팀', 'team03', '12345', '서울 특별시 종로구', '관철동', '010-2222-2222', '30대~40대', 'W', '2종 자동', 'Y', sysdate);

 
 
2. 목록
- 검색을 하지 않는 경우, 전체 목록 출력

SELECT userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate
FROM rent_user
ORDER BY userno ASC;
 
 USERNO USERID    USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ --------- -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      1 manager01 QNA관리자   manager01  12345       서울 특별시 종로구   관철동          010-0000-0000 20대~30대 M       1종 보통       M          2019-05-30 17:29:52.0
      2 manager02 회원관리자    manager02  12345       서울 특별시 종로구   관철동          010-1111-1111 20대~30대 W       1종 보통       M          2019-05-30 17:31:04.0
      3 user01    회원01     user01     12345       서울 특별시 종로구   관철동          010-0000-0000 20대~30대 M       1종 보통       Y          2019-05-30 17:31:13.0
      4 user02    회원02     user02     12345       서울 특별시 종로구   관철동          010-1111-1111 20대~30대 W       1종 보통       N          2019-05-30 17:31:14.0
      5 user03    회원03     user03     12345       서울 특별시 종로구   관철동          010-2222-2222 30대~40대 W       2종 자동       Y          2019-05-30 17:31:15.0
      6 user04    회원04     user04     12345       서울 특별시 종로구   관철동          010-3333-3333 30대~40대 M       2종 자동       H          2019-05-30 17:31:16.0
      7 user05    회원05     user05     12345       서울 특별시 종로구   관철동          010-4444-4444 40대~50대 M       대형          Y          2019-05-30 17:31:17.0
      8 team01    개발팀      team01     12345       서울 특별시 종로구   관철동          010-0000-0000 20대~30대 M       1종 보통       Y          2019-05-30 17:31:26.0
      9 team02    디자인팀     team02     12345       서울 특별시 종로구   관철동          010-1111-1111 20대~30대 W       1종 보통       N          2019-05-30 17:31:27.0
     10 team03    웹퍼블리셔팀   team03     12345       서울 특별시 종로구   관철동          010-2222-2222 30대~40대 W       2종 자동       Y          2019-05-30 17:31:28.0
   
3. 조회
 
1) userno1 사원 정보 보기

SELECT userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate
FROM rent_user
WHERE userno = 1;

 USERNO USERID    USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ --------- -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      1 manager01 QNA관리자   manager01  12345       서울 특별시 종로구   관철동          010-0000-0000 20대~30대 M       1종 보통       M          2019-05-30 17:29:52.0

      
SELECT userno, userid, username, userpasswd, userzipcode, useraddress1, useraddress2, usertel, userage, usersex, userlicense, userstatus, rdate
FROM rent_user
WHERE userid = 'user01';
 
 USERNO USERID USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ ------ -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      3 user01 회원01     user01     12345       서울 특별시 종로구   관철동          010-0000-0000 20대~30대 M       1종 보통       Y          2019-05-30 17:31:13.0

   
4. 수정

UPDATE rent_user
SET userid='user12', username='회원12', userpasswd='user12', userzipcode='54321', useraddress1='서울 특별시 서대문구', useraddress2='홍은동', usertel='010-1212-1212', userage='30대~40대', usersex='M', userlicense='2종', userstatus='N'
WHERE userno = 4;

USERNO USERID USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ ------ -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      4 user12 회원12     user12     54321       서울 특별시 서대문구  홍은동          010-1212-1212 30대~40대 M       2종          N          2019-05-30 17:31:14.0
 

 
5. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(userno) as cnt
FROM rent_user
WHERE userno=3 AND userpasswd='user01';

CNT
 ---
   1

 
2) 패스워드 수정
UPDATE rent_user
SET userpasswd='user11'
WHERE userno=3;

USERNO USERID USERNAME USERPASSWD USERZIPCODE USERADDRESS1 USERADDRESS2 USERTEL       USERAGE USERSEX USERLICENSE USERSTATUS RDATE
 ------ ------ -------- ---------- ----------- ------------ ------------ ------------- ------- ------- ----------- ---------- ---------------------
      3 user01 회원01     user11     12345       서울 특별시 종로구   관철동          010-0000-0000 20대~30대 M       1종 보통       Y          2019-05-30 17:31:13.0

 
6. 삭제
1) 모두 삭제
DELETE FROM user_rent;
 
2) 특정 회원 삭제
DELETE FROM user_rent
WHERE userno=4;
 
 
7. 로그인
SELECT COUNT(userno) as cnt
FROM rent_user
WHERE userid='user01' AND userpasswd='user11';

 CNT
 ---
   1

   
   
   SELECT userid, username, usertel
  FROM rent_user
  WHERE username = '강태풍' AND usertel = '010-0000-0000';
   
