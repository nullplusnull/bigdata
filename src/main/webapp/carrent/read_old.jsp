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
    
  </ASIDE> 
 
  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
      
      <br>
          <DIV>
            <IMG src='./storage/${carrentVO.rent_thumb1 }'>
          </DIV>
          <div class="form-group">
            <label for="car" class="col-md-1 control-label">차량명</label>
            <div class="col-md-11">
              <input type='text' class="form-control input-lg"
                name='carname' id='carname' value='${carrentVO.carname }'
                required="required" style='width: 15%;'>  
                </div>
              </div>
              <div class="form-group">
                <label for="producer" class="col-md-1 control-label">제조사</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='carproducer' id='carproducer' value='${carrentVO.carproducer }''
                    required="required" style='width: 15%;'>
                </div>
              </div>
              
              <div class="form-group">
                <label for="oil" class="col-md-1 control-label">연료</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='power' id='power' value='${carrentVO.power }'
                    required="required" style='width: 15%;'>
                </div>
              </div>                      
              
              <div class="form-group">
                <label for="hourprice" class="col-md-1 control-label">시간당가격</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='price' id='price' value='${carrentVO.price }'
                    required="required" style='width: 15%;'>
                </div>
              </div>
              <div class="form-group">
                <label for="limage" class="col-md-1 control-label">연령제한</label>
                <div class="col-md-11">
                  <input type='text' class="form-control input-lg"
                    name='age' id='age' value='${carrentVO.age }' required="required"
                    style='width: 10%;'>
                </div>
                <button type="button"
                  onclick="location.href='../rentperiod/create.do?carrentno=${carrentVO.carrentno}'">예약</button>

             

      
  </DIV>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 