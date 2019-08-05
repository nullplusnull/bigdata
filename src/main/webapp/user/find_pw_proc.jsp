<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>회원PW조회처리</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
<!-- Bootstrap -->
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
  
<DIV class='title_line'>알림</DIV>
  <DIV class='message'>
    <fieldset class='fieldset_basic'>
      <ul>
        
        <%-- <a href='${pageContext.request.contextPath}/user/login.do'>로그인 하러 가기</a> --%>
        <c:choose>
        <c:when test="${userVO != null }">
          <li class='li_none'>[${userVO.userid}] 회원의 패스워드는 [${userVO.userpasswd}] 입니다.</li>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>일치하는 정보가 없습니다.</LI>
        </c:otherwise>
      </c:choose>
      </ul>
    </fieldset>    
  </DIV>
 
  <div class="form-group">
      <div class="col-md-offset-5 col-md-7">
        <button type='button' onclick="location.href='./create.do'" class="btn btn-primary btn-md">회원가입</button>
        <button type='button' onclick="location.href='./login.do'" class="btn btn-primary btn-md">로그인</button>
      </div>
    </div>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 