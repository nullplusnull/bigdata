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

<script type="text/javascript">
  $(function() { // 자동 생성
    $.cookie('checkID', 'FALSE'); // Cookie 생성
    $('#popup').hide();
  });
  
  $(function() { // 자동 생성
    $.cookie('checkTel', 'FALSE'); // Cookie 생성
    $('#popup').hide();
  });

  function checkId() {
    var frm = $('#frm');
    var params = 'userid=' + $('#userid', frm).val(); // #: id
    // alert('params: ' + params);

    $.ajax({
      url : "./checkId.do",
      type : "GET",
      cache : false,
      dataType : "json", // or html, text
      data : params,
      success : function(data) {
        var msg = "";

        if (data.count > 0) {
          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap
          msg = "『Danger!』 이미 사용중인 ID 입니다.";
        } else {
          $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap
          msg = "『Success!』 사용 가능한 ID 입니다.";

          $.cookie('checkID', 'TRUE'); // Cookie 값 변경
        }

        $('#modal_title').html('ID 중복 확인');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error : function(request, status, error) {
        var msg = "에러가 발생했습니다. <br><br>";
        msg += "다시 시도해주세요.<br><br>";
        msg += "request.status: " + request.status + "<br>";
        msg += "request.responseText: " + request.responseText + "<br>";
        msg += "status: " + status + "<br>";
        msg += "error: " + error;

        // var panel = "";
        // panel += "<DIV id='panel' class='popup1' style='height: 350px;'>";
        // panel += msg;
        // panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\">CLOSE</A>]";
        // panel += "</DIV>";

        // $('#main_panel').html(panel);      
        // $('#main_panel').show();
        // id_span.html(msg);
        $('#modal_title').html('ID 중복 확인');
        $('#modal_content').attr('class', 'alert alert-danger');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      }
    });

  }
  
  function checkTel() {
    var frm = $('#frm');
    var params = 'usertel=' + $('#usertel', frm).val(); // #: id
    // alert('params: ' + params);

    $.ajax({
      url : "./checkTel.do",
      type : "GET",
      cache : false,
      dataType : "json", // or html, text
      data : params,
      success : function(data) {
        var msg = "";

        if (data.count > 0) {
          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap
          msg = "『Danger!』 이미 사용중인 Tel 입니다.";
        } else {
          $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap
          msg = "『Success!』 사용 가능한 Tel 입니다.";

          $.cookie('checkTel', 'TRUE'); // Cookie 값 변경
        }

        $('#modal_title').html('번호 중복 확인');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error : function(request, status, error) {
        var msg = "에러가 발생했습니다. <br><br>";
        msg += "다시 시도해주세요.<br><br>";
        msg += "request.status: " + request.status + "<br>";
        msg += "request.responseText: " + request.responseText + "<br>";
        msg += "status: " + status + "<br>";
        msg += "error: " + error;

        // var panel = "";
        // panel += "<DIV id='panel' class='popup1' style='height: 350px;'>";
        // panel += msg;
        // panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\">CLOSE</A>]";
        // panel += "</DIV>";

        // $('#main_panel').html(panel);      
        // $('#main_panel').show();
        // id_span.html(msg);
        $('#modal_title').html('번호 중복 확인');
        $('#modal_content').attr('class', 'alert alert-danger');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      }
    });

  }

  function send() {
    var check1 = $.cookie('checkID'); // 쿠키값
    var check2 = $.cookie('checkTel'); // 쿠키값
    
    if (check1 != 'TRUE') {
      var msg = "ID 중복확인이 되지 않았습니다.<br>";
      msg += "ID [중복확인] 버튼을 클릭하세요.<br>";

      $('#modal_title').html('ID 체크 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   

      return false; // submit 중지
    }
    
    if (check2 != 'TRUE') {
      var msg = "Tel 중복확인이 되지 않았습니다.<br>";
      msg += "Tel [중복확인] 버튼을 클릭하세요.<br>";

      $('#modal_title').html('Tel 체크 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   

      return false; // submit 중지
    }

    if ($('#userpasswd').val() != $('#userpasswd2').val()) {
      var msg = "입력된 패스워드가 일치하지 않습니다.<br>";
      msg += "패스워드를 다시 입력해주세요.<br>";

      $('#modal_title').html('패스워드 일치여부 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   

      return false; // submit 중지
    }

    return true; // submit 진행
  }
</script>


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

      <FORM name='frm' id='frm' method='POST' action='./create.do'
        onsubmit="return send();" class="form-horizontal" enctype="multipart/form-data">

        <div class="form-group">
          <label for="id" class="col-md-2 control-label">아이디</label>
          <div class="col-md-10">
            <input type='text' class="form-control input-md"
              name='userid' id='userid' value='new1' required="required"
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
              name='userpasswd' id='userpasswd' value='1234'
              required="required" style='width: 30%;' placeholder="패스워드">
          </div>
        </div>

        <div class="form-group">
          <label for="passwd2" class="col-md-2 control-label">패스워드
            확인</label>
          <div class="col-md-10">
            <input type='password' class="form-control input-md"
              name='userpasswd2' id='userpasswd2' value='1234'
              required="required" style='width: 30%;' placeholder="패스워드">
          </div>
        </div>

        <div class="form-group">
          <label for="mname" class="col-md-2 control-label">성명</label>
          <div class="col-md-10">
            <input type='text' class="form-control input-md"
              name='username' id='username' value='개발자'
              required="required" style='width: 30%;' placeholder="성명">
          </div>
        </div>

        <div class="form-group">
          <label for="tel" class="col-md-2 control-label">전화번호</label>
          <div class="col-md-10">
            <input type='text' class="form-control input-md"
              name='usertel' id='usertel' value='010-0000-0000'
              required="required" style='width: 30%;' placeholder="전화번호">
              <button type='button' onclick="checkTel()"
              class="btn btn-info btn-md">중복확인</button>
            <SPAN id='id_span'></SPAN>
            예) 010-0000-0000
          </div>
        </div>

        <div class="form-group">
          <label for="zipcode" class="col-md-2 control-label">우편번호</label>
          <div class="col-md-10">
            <input type='text' class="form-control input-md"
              name='userzipcode' id='userzipcode' value='12345'
              required="required" style='width: 30%;' placeholder="우편번호">
            <input type="button" onclick="DaumPostcode()"
              value="우편번호 찾기" class="btn btn-info btn-md">
          </div>
        </div>

        <div class="form-group">
          <label for="address1" class="col-md-2 control-label">주소</label>
          <div class="col-md-10">
            <input type='text' class="form-control input-md"
              name='useraddress1' id='useraddress1' value=''
              required="required" style='width: 80%;' placeholder="주소">
          </div>
        </div>

        <div class="form-group">
          <label for="address2" class="col-md-2 control-label">상세
            주소</label>
          <div class="col-md-10">
            <input type='text' class="form-control input-md"
              name='useraddress2' id='useraddress2' value=''
              required="required" style='width: 80%;'
              placeholder="상세 주소">
          </div>
        </div>

        <div class="form-group">
          <div class="col-md-12">

            <!-- ----- DAUM 우편번호 API 시작 ----- -->
            <div id="wrap"
              style="display: none; border: 1px solid; width: 500px; height: 300px; margin: 5px 110px; position: relative">
              <img
                src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png"
                id="btnFoldWrap"
                style="cursor: pointer; position: absolute; right: 0px; top: -1px; z-index: 1"
                onclick="foldDaumPostcode()" alt="접기 버튼">
            </div>

            <script
              src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
            <script>
                          // 우편번호 찾기 화면을 넣을 element
                          var element_wrap = document.getElementById('wrap');

                          function foldDaumPostcode() {
                            // iframe을 넣은 element를 안보이게 한다.
                            element_wrap.style.display = 'none';
                          }

                          function DaumPostcode() {
                            // 현재 scroll 위치를 저장해놓는다.
                            var currentScroll = Math.max(
                                document.body.scrollTop,
                                document.documentElement.scrollTop);
                            new daum.Postcode(
                                {
                                  oncomplete : function(data) {
                                    // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                                    var fullAddr = data.address; // 최종 주소 변수
                                    var extraAddr = ''; // 조합형 주소 변수

                                    // 기본 주소가 도로명 타입일때 조합한다.
                                    if (data.addressType === 'R') {
                                      //법정동명이 있을 경우 추가한다.
                                      if (data.bname !== '') {
                                        extraAddr += data.bname;
                                      }
                                      // 건물명이 있을 경우 추가한다.
                                      if (data.buildingName !== '') {
                                        extraAddr += (extraAddr !== '' ? ', '
                                            + data.buildingName
                                            : data.buildingName);
                                      }
                                      // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                                      fullAddr += (extraAddr !== '' ? ' ('
                                          + extraAddr + ')' : '');
                                    }

                                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                                    document.getElementById('userzipcode').value = data.zonecode; //5자리 새우편번호 사용
                                    document.getElementById('useraddress1').value = fullAddr;

                                    // iframe을 넣은 element를 안보이게 한다.
                                    // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                                    element_wrap.style.display = 'none';

                                    // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                                    document.body.scrollTop = currentScroll;

                                    $('#useraddress2').focus();
                                  },
                                  // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
                                  onresize : function(size) {
                                    element_wrap.style.height = size.height
                                        + 'px';
                                  },
                                  width : '100%',
                                  height : '100%'
                                }).embed(element_wrap);

                            // iframe을 넣은 element를 보이게 한다.
                            element_wrap.style.display = 'block';
                          }
                        </script>
            <!-- ----- DAUM 우편번호 API 종료----- -->

          </div>
        </div>
 
        <div class="form-group">
          <label for="mname" class="col-md-2 control-label">나이</label>
          <div class="col-md-10">
            <input type='number' class="form-control input-md"
              name='userage' id='userage' value='20'
              required="required" style='width: 30%;' placeholder="나이">
          </div>
        </div>
        
        <div class="form-group">
          <label for="sex" class="col-md-2 control-label">성별 구분</label>
          <div class="col-md-10">
            <label> <input type='radio' name='usersex'
              value='M' checked='checked'> 남성
            </label> <label> <input type='radio' name='usersex'
              value='W'> 여성
            </label>
          </div>
        </div>
        
        <div class="form-group">
          <label for="license" class="col-md-2 control-label">면허 구분</label>
          <div class="col-md-10">
            <label> <input type='radio' name='userlicense'
              value='1종 대형'> 1종 대형
            </label>
            <label> <input type='radio' name='userlicense'
              value='1종 보통' checked='checked'> 1종 보통
            </label> 
            <label> <input type='radio' name='userlicense'
              value='2종 자동'> 2종 자동
            </label>
          </div>
        </div>
        
        <div class="form-group">   
        <label for="filesMF" class="col-md-1 control-label">면허 사진</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple" required="required">
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>   

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
