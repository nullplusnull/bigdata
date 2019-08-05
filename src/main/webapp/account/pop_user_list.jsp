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

});


$(function(){
  $('#confirm_id').click(function(){
    var st = $(":input:radio[name=accountno]:checked").val();
    alert(st);
    //부모창의 id가 id인 input 태그에 'dragon' 이라는 문자열 저장하기
     $('#accountno',opener.document).val(st);
    
    //아래와 같이 명시하는 것도 가능함
   //$(opener.document).find('#id').val('dragon');

    self.close();
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
  
  

 
  <table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  <TR>
    <TH class='th'>계좌번호</TH>
    <TH class='th'>은행</TH>
    <TH class='th'>패스워드</TH>
    <TH class='th'>현금</TH>
    <TH class='th'>유저번호</TH>
    <TH class='th'>선택</TH>
  </TR>
 
 
 
 
<tbody>
        <c:forEach var="accountVO" items="${list }">
  <TR>
    <TD class='td'>${accountVO.accountno } </TD>
    <TD class='td'>${accountVO.bank }</TD>
    <TD class='td'>${accountVO.account_passwd }</TD>
    <TD class='td'>${accountVO.cash } </TD>
    <TD class='td'>${accountVO.userno }</TD> <!-- 년월일 -->
    <TD class='td'>
      <input type="radio" name="accountno" value="${accountVO.accountno }" />
      <%-- <A href= "./read.do?accountno=${accountVO.accountno }"><IMG src='./images/account.jpg' title='선택'></A> --%>
    </TD>
    
  </TR>
</c:forEach>
    
</TABLE>
 
<DIV class='bottom_menu'>
  <button type='button' id='confirm_id'>등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
  

</DIV>
 
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
 
</html>
  