<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>회원PW찾기</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
 
 <script type="text/javascript">
 </script>
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 90%;'> 
 
<DIV class='title_line' style='width: 20%;'>회원PW찾기</DIV> 
<DIV style='width: 80%; margin: 0px auto;'>
  <FORM name='frm' method='POST' action='./find_pw.do'> 
  
        
    <div class="form-group">
      <label for="id" class="col-md-2 control-label">회원ID</label>    
      <div class="col-md-10">
        <input type='text' class="form-control input-md" name='userid' id='userid' value='' required="required" style='width: 30%;' placeholder="ID">
      </div>
    </div>  
  
    <div class="form-group">
      <label for="name" class="col-md-2 control-label">이름</label>    
      <div class="col-md-10">
        <input type='text' class="form-control input-md" name='username' id='username' value='' required="required" style='width: 30%;' placeholder="이름" autofocus="autofocus">
      </div>
 
    </div>   
 
    <div class="form-group">
      <label for="usertel" class="col-md-2 control-label">핸드폰번호</label>    
      <div class="col-md-10">
        <input type='text' class="form-control input-md" name='usertel' id='usertel' value='' required="required" style='width: 30%;' placeholder="핸드폰번호">
      </div>
    </div>   
 
    <div class="form-group">
      <div class="col-md-offset-2 col-md-10">
        <button type="submit" class="btn btn-primary btn-md">PW찾기</button>
        <button type="button" onclick="history.back()" class="btn btn-primary btn-md">취소</button>
        <!-- <button type='button' onclick="location.href='./create.do'" class="btn btn-primary btn-md">회원가입</button> -->
      </div>
    </div>   
    
  </FORM>
</DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 