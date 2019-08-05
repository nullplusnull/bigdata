<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">


$(function()  {   // 자동실행
  var payment_cancel = "${totalVO.payment_cancel}";
  if(payment_cancel == 'Y') {
    $('#btn_send').hide();
  }
});


$(function(){
  $('#btn_send').click(function(){
    window.open("../refund/pop_refund.do?userno=1&paymentno="+"${totalVO.paymentno}", 'test', 'width=600, height=600');
  });
});



</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 80%;'>
<jsp:include page="/mypage/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>  
 
  <ASIDE style='float: left;'>
    렌트 기간 > 등록
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
  </ASIDE> 

  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
    <input type="hidden" name='paymentno' id='paymentno'>
 
    
    <table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 15%;'/>
    <col style='width: 50%;'/>
    <col style='width: 15%;'/>
    <col style='width: 20%;'/>
  </colgroup>
  <TR>
    <TH class='th'>예약/구매번호</TH>
    <TH class='th'>예약/구매정보</TH>
    <TH class='th'>진행상태</TH>
    <TH class='th'>결제 금액</TH>
  </TR>
  <TR>
    <TD class='td' style='curser:pointer;' onclick="fn_paymentno_set('${totalVO.paymentno}')">${totalVO.paymentno}</TD>
    <TD class='td'>${totalVO.carname} /${totalVO.power}<br> ${totalVO.startday}부터 ~ ${totalVO.endday}까지 ${totalVO.hour}시간</TD>
    <TD class='td'>${totalVO.reservation_state}</TD>
    <TD class='td'>${totalVO.payment_price} </TD>    
  </TR>
</TABLE>

<br><br>
<table class="table table-striped" style='width: 50%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 35%;'/>
    <col style='width: 65%;'/>
  </colgroup>
  <TR>
     <TH class='th'>이름</TH>
     <TD class='td'>${userVO.username}</TD>
   </TR>
   <TR>
     <TH class='th'>전화번호</TH>
     <TD class='td'>${userVO.usertel}</TD>
   </TR>
   <TR>
     <TH class='th'>이메일</TH>
     <TD class='td'>${userVO.useraddress1}</TD>
   </TR>
 
</TABLE>


              <DIV style='text-align: right;'>
                <button type="submit" id='test111'>예약</button>
                <button type='button' id='btn_send'>환불</button>
                <%-- <button type="button"
                  onclick="location.href='./list.do?rentno=${rentperiodVO.rentno}'">취소[목록]</button> --%>
              </DIV>
        </FORM>
  </DIV>
 
 
</DIV> <!-- content END -->
<jsp:include page="/mypage/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 