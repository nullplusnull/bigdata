<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
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
 
<script type="text/javascript">
  $(function(){
 
  });
</script>
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 50%;'>
 
<DIV class='title_line' style='width: 40%;'>
『${carrentVO.carname }』 수정
</DIV>
 
<FORM name='frm' method='POST' action='./update.do'>
  <!-- 개발시 임시 값 사용: 1 -->
  <input type='hidden' name='carrentno' id='carrentno' value='${carrentVO.carrentno }'>

  
  <fieldset class='fieldset_basic'>
    <ul>
      <li class='li_none'>
        <label>차량정보를 수정합니다.</label>
      </li>
    
      <li class='li_none'>
        <label for='price'>가격</label>
        <input type='text' name='price' id='price' value='${carrentVO.price }' required="required" autofocus="autofocus">
      </li>     
      <li class='li_none'>
        <label for='condition'>상태</label>
        <select name='condition'>
          <option value='렌트가능' ${carrentVO.condition == '렌트가능' ? "selected='selected'" : "" }>렌트가능</option>
          <option value='사용중' ${carrentVO.condition == '사용중' ? "selected='selected'" : "" }>사용중</option>
          <option value='수리중' ${carrentVO.condition == '수리중' ? "selected='selected'" : "" }>수리중</option>
        </select>
      </li>
      
 
      <li class='li_right'>
        <button type="submit">수정</button>
        <button type="button" onclick="location.href='./list.do'">취소(목록)</button>
      </li>
    </ul>
  </fieldset>
</FORM>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 