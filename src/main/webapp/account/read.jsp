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
  $(function(){
 
  });
</script>
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
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
    <TH class='th'>기타</TH>
  </TR>
 
 
 
 
<tbody>
      
  <TR>
    <TD class='td'>${accountVO.accountno } </TD>
    <TD class='td'>${accountVO.bank }</TD>
    <TD class='td'>${accountVO.account_passwd }</TD>
    <TD class='td'>${accountVO.cash } </TD>
    <TD class='td'>${accountVO.userno }</TD> <!-- 년월일 -->
    <TD class='td'>
      <%-- <A href="./passwd_update.do?userno=${userno}"><IMG src='./images/passwd.png' title='패스워드 변경'></A> --%>
      <IMG src='./images/account.jpg' title='선택'>
    </TD>
    
  </TR>

    
</TABLE>
 
<DIV class='bottom_menu'>
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html>
  