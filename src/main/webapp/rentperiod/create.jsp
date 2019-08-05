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

</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 80%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>  
 
  <ASIDE style='float: left;'>
    렌트 기간 > 등록
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
      
          <input type='hidden' name='carrentno' id='carrentno' value='${param.carrentno }'>
          <br>
          <div class="form-group">
            <label for="startydate" class="col-md-5 control-label">렌트 시작 날짜: </label>
            <div >
              <input type='date' class="input_basic" name='startday'
                id='startday' value='2019-06-18' required="required"
                style='width: 25%;'> 
                <select  class="input_basic" name='starttime'  id='starttime' required="required">
                <option value='08:00:00'>08:00</option>
                <option value='09:00:00'>09:00</option>
                <option value='10:00:00'>10:00</option>
                <option value='11:00:00'>11:00</option>
                <option value='12:00:00'>12:00</option>
                <option value='13:00:00'>13:00</option>
                <option value='14:00:00'>14:00</option>
                <option value='15:00:00'>15:00</option>
                <option value='16:00:00'>16:00</option>
                <option value='17:00:00'>17:00</option>
                <option value='18:00:00'>18:00</option>
                <option value='19:00:00'>19:00</option>
                <option value='20:00:00'>20:00</option>

              </select>
            </div>
          </div>
    
          <div class="form-group">
            <label for="enddate" class="col-md-5 control-label">렌트 반납 날짜:</label>
          
            <div >
              <input type='date' class="input_basic" name='endday'
                id='endday' value='2019-07-08' required="required"
                style='width: 25%;'>
                  <select  class="input_basic" name='endtime'  id='endtime' required="required">
                <option value='08:00:00'>08:00</option>
                <option value='09:00:00'>09:00</option>
                <option value='10:00:00'>10:00</option>
                <option value='11:00:00'>11:00</option>
                <option value='12:00:00'>12:00</option>
                <option value='13:00:00'>13:00</option>
                <option value='14:00:00'>14:00</option>
                <option value='15:00:00'>15:00</option>
                <option value='16:00:00'>16:00</option>
                <option value='17:00:00'>17:00</option>
                <option value='18:00:00'>18:00</option>
                <option value='19:00:00'>19:00</option>
                <option value='20:00:00'>20:00</option>

              </select>
            </div>
          </div>
              
              <div class="form-group">
                <label for="startwhere" class="col-md-5 control-label">렌트 장소:</label>
                <div >
                  <input type='text' class="form-control input-lg"
                    name='startplace' id='startplace' value='솔데스크'
                    required="required" style='width: 15%;'>
                </div>
              </div>
              
              
                    <div class="form-group">
                <label for="endwhere" class="col-md-5 control-label">반납 장소:</label>
                <div >
                  <input type='text' class="form-control input-lg"
                    name='endplace' id='endplace' value='솔데스크'
                    required="required" style='width: 15%;'>
                </div>
              </div>          

              <DIV style='text-align: right;'>
                <button type="submit">등록</button>
                <%-- <button type="button"
                  onclick="location.href='./list.do?rentno=${rentperiodVO.rentno}'">취소[목록]</button> --%>
              </DIV>
        </FORM>
  </DIV>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 