<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />




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

function rent_click(carrentno, managementno) {
  alert(managementno);
  
  var form = {
      carrentno : carrentno,
      managementno : managementno
  }
     
  $.ajax({
    url : 'update_rent.do',
    type : 'GET',
    data : form,
    dataType: "json",
    success : function(data) {
      alert(data.resultno)

      //$('#panel').val(data)
      if(data.resultno == 0) {
        alert('에러');
      } else if(data.resultno == 1) {
        alert('차량 인수 확인하였습니다.');
        location.reload();
      }
    },
    error: function() {
      alert("ERROR")
    }
  });
  
}



function return_click(carrentno, managementno, rentno) {
  alert(managementno);
  
  var form = {
      carrentno : carrentno,
      managementno : managementno, 
      rentno : rentno
  }
     
  $.ajax({
    url : 'return_rent.do',
    type : 'GET',
    data : form,
    dataType: "json",
    success : function(data) {
      alert(data.resultno)

      //$('#panel').val(data)
      if(data.resultno == 0) {
        alert('에러');
      } else if(data.resultno == 1) {
        alert('차량 반납 확인하였습니다.');
        location.reload();
      }
    },
    error: function() {
      alert("ERROR")
    }
  });
  
}

$(function(){
  $('#test1').click(function(){
      alert('1');
      var bb = $("#box option:selected").val();
      $("#word").val(bb);
      alert(bb);
  });       
});
     

</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 120%;'>  
 
  <ASIDE style='float: left;'>
    렌트 기간 > 등록2             
  </ASIDE>

  
   <ASIDE style='float: right;'>
      <FORM name='frm' method='GET' action='./list_paging_search.do'>  
        <!-- <input type='hidden' name='categoryno' value=''> -->
 
      <%-- <SELECT name='col'>
          <OPTION value='rname' <%=(col.equals("rname"))?"selected='selected'":"" %>>이름</OPTION>
          <OPTION value='title' <%=(col.equals("title"))?"selected='selected'":"" %>>제목</OPTION>
          <OPTION value='content' <%=(col.equals("content"))?"selected='selected'":"" %>>내용</OPTION>
          <OPTION value='title_content' <%=(col.equals("title_content"))?"selected='selected'":"" %>>제목+내용</OPTION>
        </SELECT> --%>
 
 
        <SELECT name='col'  id='col'>   
          <OPTION value='carname'  
             <c:if test="${param.col eq 'carname'} ">selected</c:if>>차량명
           </OPTION>
          <OPTION value='startplace' 
          <c:if test="${param.col eq 'startplace' }">selected</c:if>>대여장소
          </OPTION>
          <OPTION value='endplace' 
          <c:if test="${param.col eq 'endplace' }">selected</c:if>>반납장소
          </OPTION>
          <OPTION value='userno' 
          <c:if test="${param.col eq 'userno' }">selected</c:if>>고객번호 
          </OPTION>

        </SELECT>  
        
        <INPUT type='text' name='word' id='word' value='${param.word}' placeholder="검색어">
        <BUTTON type='submit'>검색</BUTTON> 
        <button type='button'  id='test1'>테스트6</button>       
      </FORM>   
    </ASIDE>   
<br><br>

  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>

 
    
    <table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 3%;'/>
    <col style='width: 5%;'/>
    <col style='width: 6%;'/> 
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>     
    <col style='width: 10%;'/>
    <col style='width: 8%;'/> 
    <col style='width: 8%;'/>
    <col style='width: 8%;'/>
    <col style='width: 8%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  <TR>
    <TH class='th'>관리번호</TH>            <!-- 1 -->
    <TH class='th'>결제금액</TH>
    <TH class='th'>고객번호</TH>
    <TH class='th'>예약시간</TH>
    <TH class='th'>렌트시작시간</TH>
    <TH class='th'>렌트종료시간</TH>
    <TH class='th'>렌트반납시간</TH>
    <TH class='th'>대여장소</TH>
    <TH class='th'>반납장소</TH>
    <TH class='th'>차량명</TH>
    <TH class='th'>차량상태</TH>           <!-- 12 -->
    <TH class='th'>처리</TH>
  </TR>
 

  <c:forEach var="ManagementVO" items="${list }">

  <TR>
    <TD class='td'>${ManagementVO.managementno}</TD>
    <TD class='td'>${ManagementVO.payment_price}</TD>
    <TD class='td'>${ManagementVO.userno}</TD>      
    <TD class='td'>${ManagementVO.rdate}</TD> 
    <TD class='td'>${ManagementVO.startday}</TD> 
    
    <fmt:parseDate value="${ManagementVO.endday}" pattern="yyyy-MM-dd HH:mm:ss" var="endDate" />
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="nowDate" />             <%-- 오늘날짜 --%>
    <fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss" var="enddate"/>       <%-- 마감날짜 --%>
   <c:choose>
      <c:when  test="${enddate < nowDate}">
        <TD class='td' style='color: #FF0000;'>${ManagementVO.endday}</TD>
      </c:when> 
      <c:otherwise>
        <TD class='td' >${ManagementVO.endday}</TD>
      </c:otherwise>
    </c:choose>   

    <TD class='td'>${ManagementVO.scheduleday}</TD> 
    <TD class='td'>${ManagementVO.startplace}</TD> 
    <TD class='td'>${ManagementVO.endplace}</TD> 
    <TD class='td'>${ManagementVO.carname}</TD> 
    <c:choose>
      <c:when  test="${ManagementVO.management_state eq '대여중' }">
        <TD class='td' style='color: #0000FF;'>${ManagementVO.management_state}</TD>
      </c:when> 
      <c:otherwise>
        <TD class='td'>${ManagementVO.management_state}</TD> 
      </c:otherwise>
    </c:choose>    
<%--     <TD class='td'>${ManagementVO.carrentno}</TD>  --%>
    
    <%-- <TD class='td'>${ManagementVO.condition}</TD>  --%>
    <TD class='td'>
    <button type='button'  onclick="rent_click(${ManagementVO.carrentno}, ${ManagementVO.managementno});">인수확인</button>
    <button type='button'  onclick="return_click(${ManagementVO.carrentno}, ${ManagementVO.managementno}, ${ManagementVO.rentno});">반납</button>
    </TD> 
  </TR>
 
  </c:forEach>
  
</TABLE>
    <DIV class='bottom_menu'>${paging }</DIV>
    
    

  </DIV>
 
 
</DIV> <!-- content END -->
<jsp:include page="/mypage/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 