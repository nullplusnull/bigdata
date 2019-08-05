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
   <A href='./list.do'> 차량 목록</A>> 등록
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
      
      <br>
          <div class="form-group">
            <label for="car" class="col-md-1 control-label">차</label>
            <div class="col-md-11">
              <input type='text' class="form-control input-lg"
                name='carname' id='carname' value='카니발'
                required="required" style='width: 15%;'>

              <div class="form-group">
                <label for="producer" class="col-md-1 control-label">제조사</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='carproducer' id='carproducer' value='KIA'
                    required="required" style='width: 15%;'>
                </div>
              </div>
              
              <div class="form-group">
                <label for="oil" class="col-md-1 control-label">기름</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='power' id='power' value='가솔린'
                    required="required" style='width: 15%;'>
                </div>
              </div>
              
              
              <div class="form-group">
                <label for="color" class="col-md-1 control-label">색깔</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='carcolor' id='carcolor' value='silver'
                    required="required" style='width: 15%;'>
                </div>
              </div>
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
                <label for="title" class="col-md-1 control-label">구분</label>
                <div class="col-md-11">
                  <label> <input type='radio' name='carsize'
                    value='소형'> 소형
                  </label> <label> <input type='radio' name='carsize'
                    value='중형' checked="checked"> 중형
                  </label> <label> <input type='radio' name='carsize'
                    value='승합'> 승합
                  </label> <label> <input type='radio' name='carsize'
                    value='캠핑카'> 캠핑카
                  </label>
                </div>
              </div>
              <div class="form-group">
                <label for="title" class="col-md-1 control-label">옵션</label>
                <div class="col-md-11">
                  <label> <input type="checkbox" name='blackbox'
                    value='Y' checked="checked"> 블랙박스
                  </label> <label> <input type="checkbox"
                    name='navigation' value='Y' checked="checked">네비게이션
                  </label> <label> <input type="checkbox"
                    name='airingseat' value='Y' checked="checked">통풍시트
                  </label>
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

              <!-- <div class="form-group">   
        <label for="content" class="col-md-1 control-label">구분</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  rows='10'>즐거운 여행이었다</textarea>
        </div> -->

              <div class="form-group">
                <label for="content" class="col-md-1 control-label">검색어</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='word' id='word' value='#승합차, #9인승, #1박2일'>
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
                <button type="submit">등록</button>
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