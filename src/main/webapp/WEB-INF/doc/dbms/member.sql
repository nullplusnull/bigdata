
/**********************************/
/* Table Name: 회원 */
/**********************************/
drop table member2;
CREATE TABLE member2(
		member2no                      		NUMBER(10)		 NOT NULL,
		PRIMARY KEY (member2no)
);

COMMENT ON TABLE member2 is '회원';
COMMENT ON COLUMN member2.member2no is '회원번호';

DROP SEQUENCE member2_seq;

CREATE SEQUENCE member2_seq
START WITH   1            --시작번호
INCREMENT BY 1          --증가값
MAXVALUE   99999999  --최대값, NUMBER(7) 대응
CACHE        2               --시쿼스 변경시 자주 update되는 것을 방지하기위한 캐시값
NOCYCLE;  
drop table rentperiod;
drop table rentproduct;
drop table member CASCADE CONSTRAINTS PURGE;

select * from member2;

insert into member2(member2no)
values(member2_seq.nextval);
