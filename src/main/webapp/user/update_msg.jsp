<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>회원변경처리</title>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
</head> 
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 90%;'>
 
<DIV class='title_line' style="width: 40%;">회원 [${param.username }] 변경됨</DIV>
 
<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.count == 0}">
          <li class='li_none'>회원 정보 수정에 실패했습니다.</li>
          <li class='li_none'>다시한번 시도해주세요.</li>
          <li class='li_none'>
            <br>
            <button type='button' onclick='history.back()'>다시 시도</button>
            <button type='button' onclick="location.href='./list.do'">목록</button>
          </li>
                     
        </c:when>
        
        <c:when test="${param.count == 1 }">
          <LI class='li_none'>회원 정보를 변경했습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>회원 정보 변경에 실패했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <button type='button' onclick="location.href='./read.do?userno=${param.userno}'">변경된 회원 정보 확인</button>
        <button type='button' onclick="location.href='./list_by_paging.do'">목록</button>
      </LI>
     </UL>
  </fieldset>
 
</DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 