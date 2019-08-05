/**********************************/
/* Table Name: 회원 */
/**********************************/
DROP TABLE rent_user cascade CONSTRAINTS PURGE;
DELETE * FROM rent_user;

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
    rdate                             DATE     NOT NULL,
    licensefile                        VARCHAR2(300)  NULL,
    userthumb                            VARCHAR2(300)   NULL,
    usersize                                VARCHAR2(300)   NULL
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
COMMENT ON COLUMN rent_user.licensefile is '면허사진';
COMMENT ON COLUMN rent_user.userthumb is '면허사진썸네일';
COMMENT ON COLUMN rent_user.usersize is '면허사진크기';
COMMENT ON COLUMN rent_user.rdate is '가입날짜';



select * from rent_user;