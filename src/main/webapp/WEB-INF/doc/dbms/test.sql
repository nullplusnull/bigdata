

select *
from rentperiod rp, RESERVATION r
where r.rentno = rp.rentno and round((sysdate - r.rdate) * 24*60, 0) > 20 and reservation_state != '예약 완료'



update rentperiod
set reservation_state = '결제시간 초과로인한 예약취소'
where (
    select * from rentperiod rp, RESERVATION r
        where r.rentno = rp.rentno and round((sysdate - r.rdate) * 24*60, 0) > 20 and reservation_state != '예약 완료');

-- 2
update (
        select * 
        from rentperiod rp, RESERVATION r
        where r.rentno = rp.rentno
        )
set reservation_state = '결제시간 초과로인한 예약취소'
where round((sysdate - rdate) * 24*60, 0) > 20 and reservation_state != '예약 완료'


select count(*)
      from rentperiod rp, reservation r
      where r.rentno = rp.rentno and carrentno=10 and
        rp.startday >= to_date('2019-08-04 08:00:00' ,'YYYY-MM-DD HH24:MI:SS') and
       rp.endday <= to_date('2019-08-05 08:00:00' ,'YYYY-MM-DD HH24:MI:SS') and (r.reservation_state = '예약 대기' or r.reservation_state = '예약 완료' );

       

select *
from rentperiod rp, RESERVATION r, payment p
where p.reservationno = r.reservationno and r.rentno = rp.rentno and rp.carrentno=10 and
rp.startday >= to_date( '2019-07-16' ,'YYYY-MM-DD') and
             rp.endday <= to_date('2019-07-18','YYYY-MM-DD')

             
             
rp.startday >= to_date( '2019-07-16 08:00:00' ,'YYYY-MM-DD HH24:MI:SS') and
             rp.endday <= to_date('2019-07-17 08:00:00','YYYY-MM-DD HH24:MI:SS')

             
             rp.startday >= to_date( '2019-07-16' ,'YYYY-MM-DD') and
             rp.endday <= to_date('2019-07-17','YYYY-MM-DD')

rp.carrentno=5 AND rp.startday >= to_date( '2019-07-16 08:00:00' ,'YYYY-MM-DD HH24:MI:SS') AND
             rp.endday <= to_date('2019-07-17 08:00:00','YYYY-MM-DD HH24:MI:SS')

             scheduleday
select * from rentperiod;
select * from reservation;
select * from payment;
select * from management;

select * from contents;
             
select p.startday, p.endday, p.carrentno
from rentperiod p, reservation r
where p.rentno = r.rentno and reservationno=15
             
             
<!-- count 조회 -->
   <select id="check_rentperiod" resultType="int" parameterType="HashMap">
       select count(*)
      from rentperiod rp, RESERVATION r
      where r.rentno = rp.rentno and carrentno=#{carrentno} and
        rp.startday &gt;= to_date( #{startday} ,'YYYY-MM-DD HH24:MI:SS') and
       rp.endday &lt;= to_date(#{endday},'YYYY-MM-DD HH24:MI:SS') and (r.reservation_state = '예약 대기' or r.reservation_state = '예약 완료' )
   </select>
   

select * from rentperiod;

select * from rent_user;
select * from reservation;
select * from payment;
select * from refund;

select * from management;
















  SELECT *
  FROM payment
  WHERE paymentno = 25

  
  
  
  
  
  
  
-- step 3         
SELECT contentsno, cateno, title, content, good, thumbs, files, sizes, cnt,
          replycnt, rdate, word, r
FROM(
         SELECT contentsno, cateno, title, content, good, thumbs, files, sizes, cnt,
                   replycnt, rdate, word, rownum as r
         FROM(
                  SELECT contentsno, cateno, title, content, good, thumbs, files, sizes, cnt,
                            replycnt, rdate, word
                  FROM contents
                  WHERE cateno=1
                  ORDER BY contentsno DESC
         )
)
WHERE r >=1 AND r <= 3;
  
  

select managementno, payment_price, userno, reservation_state,
            rdate, startday, endday, scheduleday, startplace, endplace, carname, condition, r
  from(
	select m.managementno, p.payment_price, p.userno, r.reservation_state,
	          r.rdate, t.startday, t.endday, t.scheduleday, t.startplace, t.endplace, c.carname, c.condition, rownum as r
	from MANAGEMENT m, PAYMENT p, reservation r, RENTPERIOD t, carrent c
	where m.paymentno = p.paymentno and p.reservationno = r.reservationno and r.rentno = t.rentno
	          and p.payment_cancel = 'N' and t.carrentno = c.carrentno
) where r >= 1 and r <= 3;

select count(*)
from management



update
set 



select *
  from MANAGEMENT m, PAYMENT p, reservation r, RENTPERIOD t, carrent c
  where m.paymentno = p.paymentno and p.reservationno = r.reservationno and r.rentno = t.rentno
            and p.payment_cancel = 'N' and t.carrentno = c.carrentno and m.managementno = 34

            
            select m.managementno, p.payment_price, p.userno, r.reservation_state,
            r.rdate, t.startday, t.endday, t.scheduleday, t.startplace, t.endplace, c.carname, c.condition
       from MANAGEMENT m, PAYMENT p, reservation r, RENTPERIOD t, carrent c
        where m.paymentno = p.paymentno and p.reservationno = r.reservationno and r.rentno = t.rentno and t.carrentno = c.carrentno 


update (
      select m.managementno, p.payment_price, p.userno, r.reservation_state,
            r.rdate, t.startday, t.endday, t.scheduleday, t.startplace, t.endplace, c.carname, c.condition
       from MANAGEMENT m, PAYMENT p, reservation r, RENTPERIOD t, carrent c
        where m.paymentno = p.paymentno and p.reservationno = r.reservationno and r.rentno = t.rentno and t.carrentno = c.carrentno 
        and managementno = 34
)
set condition = '대여중';
where managementno = 34;

update rentperiod
set reservation_state = '결제시간 초과로인한 예약취소'
where (
    select * from rentperiod rp, RESERVATION r
        where r.rentno = rp.rentno and round((sysdate - r.rdate) * 24*60, 0) > 20 and reservation_state != '예약 완료');

select * from rentperiod;
        
update (
        select * 
        from rentperiod rp, RESERVATION r
        where r.rentno = rp.rentno
        )
set reservation_state = '결제시간 초과로인한 예약취소'
where round((sysdate - rdate) * 24*60, 0) > 20 and reservation_state != '예약 완료'









select managementno, payment_price, userno, reservation_state,
            rdate, startday, endday, scheduleday, startplace, endplace, carname, condition, r
  from(
  select m.managementno, p.payment_price, p.userno, r.reservation_state,
            r.rdate, t.startday, t.endday, t.scheduleday, t.startplace, t.endplace, c.carname, c.condition, rownum as r
  from MANAGEMENT m, PAYMENT p, reservation r, RENTPERIOD t, carrent c
  where m.paymentno = p.paymentno and p.reservationno = r.reservationno and r.rentno = t.rentno
            and p.payment_cancel = 'N' and t.carrentno = c.carrentno
            and c.carname LIKE '%' || '범' || '%'
) where r >= 1 and r <= 3;

  












