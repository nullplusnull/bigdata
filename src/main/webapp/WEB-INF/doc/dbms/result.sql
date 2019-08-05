/**********************************/
/* Table Name: ȸ�� */
/**********************************/
DROP TABLE rent_user cascade CONSTRAINTS PURGE;
DELETE * FROM rent_user;

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
    rdate                             DATE     NOT NULL,
    licensefile                        VARCHAR2(300)  NULL,
    userthumb                            VARCHAR2(300)   NULL,
    usersize                                VARCHAR2(300)   NULL
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
COMMENT ON COLUMN rent_user.licensefile is '�������';
COMMENT ON COLUMN rent_user.userthumb is '������������';
COMMENT ON COLUMN rent_user.usersize is '�������ũ��';
COMMENT ON COLUMN rent_user.rdate is '���Գ�¥';



select * from rent_user;