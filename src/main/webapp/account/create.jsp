<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" />
<title>회원등록</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/jquery.cookie.js"></script>



</head>


<body>
  <DIV class='container' style='width: 100%;'>
    <jsp:include page="/menu/top.jsp" flush='false' />
    <DIV class='content' style='width: 90%;'>

      <DIV id='main_panel'></DIV>

      <!-- Modal -->
      <div class="modal fade" id="modal_panel" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">×</button>
              <h4 class="modal-title" id='modal_title'></h4>
            </div>
            <div class="modal-body">
              <p id='modal_content'></p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default"
                data-dismiss="modal">Close</button>
            </div>
          </div>

        </div>
      </div>
      <!-- Modal END -->


      <ASIDE style='float: left;'>
        <A href='./list.do'>회원 목록</A>
      </ASIDE>
      <ASIDE style='float: right;'>
        <A href="javascript:location.reload();">새로고침</A> <span
          class='menu_divide'>│</span> <A href='./create.do'>회원 가입</A>
        <span class='menu_divide'>│</span> <A href='./list.do'>목록</A>
      </ASIDE>

      <div class='menu_line'></div>


      <DIV class='title_line'>>회원가입</DIV>

      <FORM name='frm' id='frm' method='POST' action='./create.do' class="form-horizontal">

        <div class="form-group">
          <label for="id" class="col-md-2 control-label">은행명</label>
          <div class="col-md-10">
            <input type='text' class="form-control input-md"
              name='bank' id='bank' value='new1' required="required"
              style='width: 30%;' placeholder="아이디"
              autofocus="autofocus">
            <button type='button' onclick="checkId()"
              class="btn btn-info btn-md">중복확인</button>
            <SPAN id='id_span'></SPAN>
            <!-- ID 중복 관련 메시지 -->
          </div>
        </div>

        <div class="form-group">
          <label for="passwd" class="col-md-2 control-label">패스워드</label>
          <div class="col-md-10">
            <input type='password' class="form-control input-md"
              name='account_passwd' id='account_passwd' value='1234'
              required="required" style='width: 30%;' placeholder="패스워드">
          </div>
        </div>
<!-- 
        <div class="form-group">
          <label for="passwd2" class="col-md-2 control-label">패스워드
            확인</label>
          <div class="col-md-10">
            <input type='password' class="form-control input-md"
              name='userpasswd2' id='userpasswd2' value='1234'
              required="required" style='width: 30%;' placeholder="패스워드">
          </div>
        </div> -->

        
     
    
 
    

        <div class="form-group">
          <div class="col-md-offset-2 col-md-10">
            <button type="submit" class="btn btn-primary btn-md">가입</button>
            <button type="button" onclick="location.href='../index.jsp'"
              class="btn btn-primary btn-md">취소</button>

          </div>
        </div>
      </FORM>

    </DIV>
    <!-- content END -->
    <jsp:include page="/menu/bottom.jsp" flush='false' />
  </DIV>
  <!-- container END -->
</body>

</html>
