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
    <input type="hidden" name='carrentno' id='carrentno' value="${param.carrentno }">
    <input type="hidden" name='startday' id='startday' value="${param.startday}">
    <input type="hidden" name='endday' id='endday' value="${param.endday}">
    <input type="hidden" name='startplace' id='startplace' value="${param.startplace}">
    <input type="hidden" name='endplace' id='endplace' value="${param.endplace}">
   

    
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
    <%-- <TD class='td'>
      <A href="../account/user_list.do?userno=${userno}"><IMG src='./images/KRW.png' title='계좌조회'></A>
      <A href="./passwd_update.do?userno=${userno}"><IMG src='./images/passwd.png' title='패스워드 변경'></A>
      <A href="./read.do?userno=${userno}"><IMG src='./images/update.png' title='수정'></A>
      <A href="./delete.do?userno=${userno}"><IMG src='./images/delete.png' title='삭제'></A>
    </TD> --%>
  </TR>
  
</TABLE>
    
    
    
              <DIV style='text-align: right;'>
                <button type="submit">예약</button>
                <%-- <button type="button"
                  onclick="location.href='./list.do?rentno=${rentperiodVO.rentno}'">취소[목록]</button> --%>
              </DIV>
        </FORM>
  </DIV>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 