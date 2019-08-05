<%@ page contentType="text/html; charset=UTF-8" %>
 
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

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
  window.onload = function() {
    CKEDITOR.replace('carrent'); // <TEXTAREA>태그 id 값
  };
</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 80%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>  
 
  <ASIDE style='float: left;'>
   <A href='./list.do'> 차량 목록</A>> 수정
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>
    <FORM name='frm' method='POST' action='./update.do'
               enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='carrentno' id='carrentno' value='${carrentVO.carrentno }'>
      <br>
          <div class="form-group">

             <div class="form-group">
                <label for="hourprice" class="col-md-1 control-label">시간당가격</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='price' id='price' value='80000'
                    required="required" style='width: 15%;'>
                </div>
              </div>
              
              <div class="form-group">
                <label for="limage" class="col-md-1 control-label">연령제한</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='age' id='age' value='24' required="required"
                    style='width: 10%;'>
                </div>

              </div>
           
           

              <div class="form-group">
                <label for="title" class="col-md-1 control-label">상태</label>
                <div class="col-md-11">
                  <label> <input type='radio' name='condition'
                    value='렌트 가능' checked="checked"> 렌트 가능
                  </label> <label> <input type='radio' name='condition'
                    value='사용중''> 사용중
                  </label> <label> <input type='radio' name='condition'
                    value='수리중 '>수리중
                  </label>
                </div>
              </div>

              
              <div class="form-group">
                <label for="filesMF" class="col-md-1 control-label">업로드
                  파일</label>
                <div class="col-md-11">
                  <input type="file" class="form-control input-lg"
                    name='rent_file1MF' id='rent_file1MF' size='40'
                    multiple="multiple"> <br> Preview(미리보기)
                  이미지는 자동 생성됩니다.
                </div>
              </div>

              <DIV style='text-align: right;'>
                <button type="submit">수정</button>
                <button type="button"
                  onclick="location.href='./list.do?carrentno=${carrentVO.carrentno}'">취소[목록]</button>
              </DIV>
        </FORM>
  </DIV>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 