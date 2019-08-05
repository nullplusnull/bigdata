<%@ page contentType="text/html; charset=UTF-8" %>
 
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

  
</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 80%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>  
 
 
 </DIV>


  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>

  <h5>주문완료</h5>
 <div style='text-align: center;'>
 <h4>"감사합니다 주문이 완료되었습니다. "</h4>
 <span>님의 주문이 정상적으로 완료되었습니다. </span>
 </div>
 
 <ul>
  <li>주문상세내역은 사이트 상단의 마이페이지>나의 예약/구매 내역에서 확인하실 수 있습니다.
  </li>
  <li>주문과 관련된 문의사항이 있으신경우 고객센터를 문의해 주시기 바랍니다.
  </li>
 </ul>
 
  <H6>결제 내역</H6>
  <span>
  결제금액 : 
  </span>

    <H6>주문하신 상품</H6>
    <table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 35%;'/>
  </colgroup>
  <TR>
    <TH class='th'>차이름</TH>
    <TH class='th'>연료</TH>
    <TH class='th'>시간당 가격</TH>
    <TH class='th'>대여 시간</TH>
    <TH class='th'>총 금액</TH>
    <TH class='th'>시작일~종료일</TH>
  </TR>
 
  <TR>
    <TD class='td'>${param.carname}</TD>
    <TD class='td'>${param.power}</TD>
    <TD class='td'>${param.price}</TD>
    <TD class='td'>${param.hour}</TD>    
    <TD class='td'>${param.total_price}</TD>
    <TD class='td'>${param.startday} ~ ${param.endday}</TD> 
  </TR>
  
</TABLE>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 