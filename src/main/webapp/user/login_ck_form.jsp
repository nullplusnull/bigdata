<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>로그인</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
 
 <script type="text/javascript">
 	function loadDefault() {
 	  $('#userid').val('new1');
 	  $('#userpasswd').val('1234');
 	  
 	}
 </script>
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 90%;'> 
 
<DIV class='title_line' style='width: 20%;'>로그인</DIV>
 
<DIV style='width: 80%; margin: 0px auto;'>
  <FORM name='frm' method='POST' action='./login.do'>
  
    <div class="form-group">
      <label for="id" class="col-md-2 control-label">아이디</label>    
      <div class="col-md-10">
        <input type='text' class="form-control input-md" name='userid' id='userid' value='${ck_userid }' required="required" style='width: 30%;' placeholder="아이디" autofocus="autofocus">
        <Label>   
          <input type='checkbox' name='id_save' value='Y' 
                    ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 저장
        </Label>
      </div>
 
    </div>   
 
    <div class="form-group">
      <label for="userpasswd" class="col-md-2 control-label">패스워드</label>    
      <div class="col-md-10">
        <input type='password' class="form-control input-md" name='userpasswd' id='userpasswd' value='${ck_userpasswd }' required="required" style='width: 30%;' placeholder="패스워드">
        <Label>
          <input type='checkbox' name='passwd_save' value='Y' 
                    ${ck_passwd_save == 'Y' ? "checked='checked'" : "" }> 저장
        </Label>
      </div>
    </div>   
 
    <div class="form-group">
      <div class="col-md-offset-2 col-md-10">
        <button type="submit" class="btn btn-primary btn-md">로그인</button>
        <button type="button" onclick="history.back()" class="btn btn-primary btn-md">취소</button>
        <button type='button' onclick="location.href='./find_id.do'" class="btn btn-primary btn-md">ID 찾기</button>
        <button type='button' onclick="location.href='./find_pw.do'" class="btn btn-primary btn-md">비밀번호 찾기</button>
        <button type='button' onclick="location.href='./create.do'" class="btn btn-primary btn-md">회원가입</button>
        <button type='button' onclick="loadDefault();" class="btn btn-primary btn-md">테스트 계정</button>
      </div>
    </div>   
    
  </FORM>
</DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 