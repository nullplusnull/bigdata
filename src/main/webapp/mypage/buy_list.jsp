<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
 <style type="text/css">
  .cursors span{
    display: inline-block;
    margin: 5px;
    padding: 5px 10px;
    background-color: #d2f4ff;
    border: 2px solid #09c;
  }
</style>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">



function fn_paymentno_set(paymentno) {
  $("#paymentno").val(paymentno);
  document.frm.action = "/rent/mypage/buy_read.do";
  document.frm.submit();
}

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
 

  <c:forEach var="totalVO" items="${list }">

  <TR>
    <TD class='td cursors'  style='curser: pointer;' onclick="fn_paymentno_set('${totalVO.paymentno}')">${totalVO.paymentno}</TD>
    <TD class='td'  onclick="fn_paymentno_set('${totalVO.paymentno}')">${totalVO.carname} /${totalVO.power}<br> ${totalVO.startday}부터 ~ ${totalVO.endday}까지 ${totalVO.hour}시간</TD>
    <TD class='td'>${totalVO.reservation_state}</TD>
    <TD class='td'  onclick="fn_paymentno_set('${totalVO.paymentno}')">${totalVO.payment_price}</TD>    
  </TR>

  </c:forEach>
  
</TABLE>
    
    
    
              <DIV style='text-align: right;'>
                <button type="submit">예약</button>
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