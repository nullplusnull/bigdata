<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function(){

});
</script>

<script type="text/javascript">
</script>
</head>

<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 90%;'>     

  <DIV class='title_line'>전체 보기</DIV>

  <ASIDE style='float: left;'>
    전체 보기 <span style='font-size: 0.8em; color: #CCCCCC;'>(${total_count })</span> 
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <!--  <span class='menu_divide' >│</span> -->
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 30%;"></col>
        <col style="width: 30%;"></col>
        <col style="width: 25%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>


        
          <th style='text-align: center;'>예약번호</th>
          <th style='text-align: center;'>예약금</th>
          <th style='text-align: center;'>예약취소 여부</th>
          <th style='text-align: center;'>렌트 번호</th>
        </tr>
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="reservationVO" items="${list }">
          <tr> 
            <td style='text-align: center;'>${reservationVO.reservationno }</td>
            <td style='text-align: center;'>${reservationVO.deposit }</td>
            <td style='text-align: center;'>${reservationVO.reservation_cancel}</td>
            <td style='text-align: center;'>${reservationVO.rentno}</td>
            <%-- <td style='text-align: center;'>
              <a href="./update.do?contentsno=${carrentVO.contentsno}&cateno=${carrentVO.cateno}"><img src="./images/update.png" title="수정" border='0'/></a>
              <a href="./delete.do?contentsno=${carrentVO.contentsno}&cateno=${carrentVO.cateno}"><img src="./images/delete.png" title="삭제"  border='0'/></a>
              ${carrentVO.cateno} / ${carrentVO.mno }
            </td> --%>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html>
    