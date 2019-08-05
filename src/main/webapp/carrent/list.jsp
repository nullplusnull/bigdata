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
<DIV class='container' style='width: 80%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>     
 
 <DIV class='title_line'></DIV>
 
  <ASIDE style='float: left;'>
    전체 보기 <span style='font-size:0.8em; color:#CCCCCC;'> </span>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href='./create.do'>차량 등록</A> <span class='menu_divide'> |</span>
    <A href="javascript:location.reload();">새로고침</A>
    <!--  <span class='menu_divide' >│</span> -->
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 25%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>사진</th>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>차이름</th>
          <th style='text-align: center;'>구분</th>
          <th style='text-align: center;'>시간당가격</th>
          <th style='text-align: center;'>상태</th>
          <th style='text-align: center;'>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="carrentVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle;'>
            <c:choose>
              <c:when test="${carrentVO.rent_thumbs != ''}">
                <IMG id='thumb' src='./storage/${carrentVO.rent_thumbs}' > <!-- 이미지 파일명 출력 -->
              </c:when>
              <c:otherwise>
                <IMG id='thumb' src='./images/none1.png' style='width: 120px; height: 80px;'> <!-- 파일이 존재하지 않는 경우 -->
              </c:otherwise>
            </c:choose>
            </td>          
            <td style='text-align: center; vertical-align: middle;''>${carrentVO.rdate.substring(0, 10)}</td>
            <td style='text-align: center; vertical-align: middle;'> 
            <a href="./read.do?carrentno=${carrentVO.carrentno}">${carrentVO.carname}</a></td>
            <td style='text-align: center; vertical-align: middle;'>${carrentVO.carsize}</td>
            <td style='text-align: center; vertical-align: middle;'>${carrentVO.price}</td>
            <td style='text-align: center; vertical-align: middle;'>${carrentVO.condition}</td>
                         
            <td style='text-align: center; vertical-align: middle;'>
              <a href="./update.do?carrentno=${carrentVO.carrentno}"><img src="./images/update.png" title="수정" border='0'/></a>
              <a href="./delete.do?carrentno=${carrentVO.carrentno}"><img src="./images/delete.png" title="삭제"  border='0'/></a>
              
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
    
    ${carrentVO.rent_thumb1}
      ${carrentVO.rent_thumbs}
  </div>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html>
    