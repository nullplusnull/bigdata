
/**********************************/
/* Table Name: ȸ�� */
/**********************************/
drop table member2;
CREATE TABLE member2(
		member2no                      		NUMBER(10)		 NOT NULL,
		PRIMARY KEY (member2no)
);

COMMENT ON TABLE member2 is 'ȸ��';
COMMENT ON COLUMN member2.member2no is 'ȸ����ȣ';

DROP SEQUENCE member2_seq;

CREATE SEQUENCE member2_seq
START WITH   1            --���۹�ȣ
INCREMENT BY 1          --������
MAXVALUE   99999999  --�ִ밪, NUMBER(7) ����
CACHE        2               --������ ����� ���� update�Ǵ� ���� �����ϱ����� ĳ�ð�
NOCYCLE;  
drop table rentperiod;
drop table rentproduct;
drop table member CASCADE CONSTRAINTS PURGE;

select * from member2;

insert into member2(member2no)
values(member2_seq.nextval);
