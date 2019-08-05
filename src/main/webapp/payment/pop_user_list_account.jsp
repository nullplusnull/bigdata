<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>${param.userid }님의 계좌목록</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
 
<script type="text/javascript">

$(function()  {   // 자동실행
  var total_price = $("#total_price", opener.document).val();
  $('#total_price').val(total_price);
  var reservationno = $("#reservationno", opener.document).val();
  $('#reservationno').val(reservationno);
  
  var carname = $("#carname", opener.document).val();
  $('#carname').val(carname);
  var power = $("#power", opener.document).val();
  $('#power').val(power);
  var price = $("#price", opener.document).val();
  $('#price').val(price);
  var hour = $("#hour", opener.document).val();
  $('#hour').val(hour);
  var startday = $("#startday", opener.document).val();
  $('#startday').val(startday);
  var endday = $("#endday", opener.document).val();
  $('#endday').val(endday);

});



$(function(){
  $('#confirm_id').click(function(){
    var accountno = $(":input:radio[name=accountno]:checked").val();
    //$('#accountno').val(accountno);
    alert(accountno);
    var total_price = $('#total_price').val();
    var reservationno = $('#reservationno').val();
    
    var carname = $('#carname').val(); 
    var price = $('#price').val();
    var power = $('#power').val();
    var hour = $('#hour').val();
    var startday = $('#startday').val();
    var endday = $('#endday').val();

    
    var form = {
        accountno : accountno,
        total_price : total_price,
        reservationno : reservationno
    }
    
    // payment/create.do
    $.ajax({
      url : 'createA.do',
      type : 'POST',
      data : form,
      dataType: "json",
      success : function(data) {
        alert("테스트합니다.");
        $('#panel').text(data.count);
        alert(data.resultno);
        if(data.resultno == 1) {
          alert("예약이 이미 끝난 상태입니다.");
          window.location.href = 'http://www.naver.com/';
        } else if(data.resultno == 2) {
          alert("잔액이 모자랍니다.");
          window.location.href = 'http://www.daum.co.kr/';
        } else if(data.resultno == 3) {
          alert("결제가 완료되었습니다.");
          opener.location.href='./orderComplete.do?reservationno='+reservationno+"&carname="+carname
              +"&power="+power+"&price="+price+"&hour="+hour+"&startday="+startday+"&endday="+endday+"&total_price="+total_price;
          window.close();
        } else { 
          alert(data.resultno);
        }
      },
      error: function() {
        alert("ERROR")
      }
    });
    

     //self.close();
  });
 });


</script>
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<DIV class='content' style='width: 90%;'> 
 
  <ASIDE style='float: left;'>
      <A href='./list.do'>회원 목록</A>  
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
  
<FORM name='frm' method='GET' action='../reservation/create.do'
               enctype="multipart/form-data" onsubmit="return send();" class="form-horizontal">
  
  <input type="hidden" name='accountno' id='accountno' value="">
  <input type="hidden" name='reservationno' id='reservationno' value="">
  <input type="hidden" name='total_price' id='total_price' value="">
  
  <input type="hidden" name='carname' id='carname' value="">
    <input type="hidden" name='power' id='power' value="">
    <input type="hidden" name='price' id='price' value="">
    <input type="hidden" name='hour' id='hour' value="">
    <input type="hidden" name='startday' id='startday' value="">
    <input type="hidden" name='endday' id='endday' value="">
  
 
  <table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  <TR>
    <TH class='th'>계좌번호</TH>
    <TH class='th'>은행</TH>
    <TH class='th'>현금</TH>
    <TH class='th'>유저번호</TH>
    <TH class='th'>선택</TH>
  </TR>
 
 
 
 
<tbody>
        <c:forEach var="accountVO" items="${list }">
  <TR>
    <TD class='td'>${accountVO.accountno } </TD>
    <TD class='td'>${accountVO.bank }</TD>
    <TD class='td'>${accountVO.cash } </TD>
    <TD class='td'>${accountVO.userno }</TD> <!-- 년월일 -->
    <TD class='td'>
      <input type="radio" name="accountno"   checked="checked" value="${accountVO.accountno }"  />
      <%-- <A href= "./read.do?accountno=${accountVO.accountno }"><IMG src='./images/account.jpg' title='선택'></A> --%>
    </TD>
    
  </TR>
</c:forEach>
    
</TABLE>

<DIV class='bottom_menu'>
  <button type='button' id='confirm_id'>결제확인</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
<div class='bottom_menu'>
    <input type='text' name='data1' id='data1' value='' >
  <input type='text' name='data2' id='data2' value='' >
  </div>

  </FORM>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
 
</html>
  