<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />




<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
 <style type="text/css">
  .cursors span{
    display: inline-block;
    margin: 5px;
    padding: 5px 10px;
    background-color: #d2f4ff;
    border: 2px solid #09c;
  }
</style>  
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">



$(function(){
  var day_ = new Date().toISOString().substring(0, 10);
  $('#expiry_date').val(day_);
  $('#expiry_date').attr('min', day_);
}); 

function coupon_update(couponno) {
  var couponno = couponno; 
  
  var form = {
      couponno : couponno,
  }
     
  $.ajax({
    url : 'coupon_update.do',
    type : 'POST',
    data : form,
    dataType: "json",
    success : function(data) { 
      $('#couponname').val(data.couponname);
      $('#use_condition').val(data.use_condition);
      $('#discount_cost').val(data.discount_cost);
      $('#expiry_date').val(data.expiry_date.substring(0, 10));
      $('#send_btn').html('수정');
      //$('#couponno').val(couponno);
      $('#frm_create').attr("action", "./update.do?couponno="+couponno );
      
    },  
    error: function() {
      alert("ERROR")
    }
  });
  
}

function coupon_delete(couponno) {
  $("#dialog1").attr("data-val", couponno);
  $("#dialog1").modal();
}

function yes(){
  var couponno = $("#dialog1").attr("data-val");
  
  var form = {
      couponno : couponno,
  }
     
  $.ajax({
    url : 'delete.do',
    type : 'POST',
    data : form,
    dataType: "json",
    success : function(data) {
      alert("정상적으로 삭제되었습니다.");
      location.reload();

    },
    error: function() {
      alert("ERROR")
    }
  });
  
}
 
function no(){
 //alert($("#dialog1").attr("data-val") + '번 No clicked.');
 
}


</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 120%;'>  
 
  <ASIDE style='float: left;'>
    렌트 기간 > 등록0
  </ASIDE>   
  <br><br>  
<input type='text' name='test11' id='test11' value='' required="required" style='width: 8%;'>
 <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do' enctype="multipart/form-data" > 
      <!-- <input type="hidden" name="couponno" id="couponno" value="" /> -->
          
      <label for='couponname'>쿠폰 이름</label> 
      <input type='text' name='couponname' id='couponname' value='' required="required" style='width: 8%;'>

      <label for='use_condition'>사용 조건</label>
      <input type='number' name='use_condition' id='use_condition' value='0' required="required" 
                min='0' max='100000' step='500' style='width: 5%;'>
      
      <label for='discount_cost'>할인 금액</label>  
      <input type='number' name='discount_cost' id='discount_cost' value='0' required="required" 
                min='0' max='100000' step='500' style='width: 5%;'>
    
      <label for='expiry_date'>유효기간</label>
      <input type='date' id='expiry_date' name='expiry_date' min=''  max=''>
      View    
     <label>
       <input type='radio' name='visible' id='visibley' value='Y' checked="checked"> Y
     </label>
     <label>
       <input type='radio' name='visible' id='visiblen' value='N'> N
     </label>
      
      <div class="form-group">
                <label for="coupon_fileMF" class="col-md-1 control-label">업로드
                  파일</label>
   
                  <input type="file" class="form-control input-lg"
                    name=coupon_fileMF id='coupon_fileMF' size='30'
                    multiple="multiple"> 
              </div>
 
       
      <button type="submit" id='send_btn'>등록</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>

<br><br> 



<div class="modal fade" id="dialog1" data-val="" tabindex="-1" role="dialog" aria-labelledby="dialog1Label" aria-hidden="true" > 
  <div class="modal-dialog"> 
    <div class="modal-content"> 
      <div class="modal-header"> 
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">삭제</h4> 
      </div> 
      <div class="modal-body">정말로 삭제 하시겠습니까? </div> 
      <div class="modal-footer"> 
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="yes('Yes')">삭제</button> 
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="no()">취소</button>
      </div> 
    </div> 
  </div>
</div>          
           



  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>

    
    
    <table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 13%;'/>
    <col style='width: 7%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/> 
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>       
    <col style='width: 10%;'/>

  </colgroup>  
  <TR>
    <TH class='th'>쿠폰 이미지</TH>
    <TH class='th'>쿠폰번호</TH>            <!-- 1 -->
    <TH class='th'>공개여부</TH>
    <TH class='th'>쿠폰이름</TH>
    <TH class='th'>사용조건</TH>
    <TH class='th'>할인금액</TH>
    <TH class='th'>유효기간</TH>
    <TH class='th'>등록일</TH>
    <TH class='th'>처리</TH>
  </TR>

  <c:forEach var="CouponVO" items="${list }">
  <TR>
    <c:if test="${CouponVO.thumb != null}">
      <TD class='td'><img src="./storage/${CouponVO.thumb}"  style='width: 50px; height: 50px;' />   
      </TD>      
    </c:if>   
    <TD class='td'>${CouponVO.couponno}</TD>
    <TD class='td'>${CouponVO.visible}</TD>
    <TD class='td'>${CouponVO.couponname}</TD>
    <TD class='td'>${CouponVO.use_condition}</TD>      
    <TD class='td'>${CouponVO.discount_cost}</TD>
    <TD class='td'>${CouponVO.expiry_date}</TD>  
    <TD class='td'>${CouponVO.rdate}</TD> 

    <TD class='td'>
    <button type='button'  onclick="coupon_update(${CouponVO.couponno});">수정</button>
    <button type='button'  onclick="coupon_delete(${CouponVO.couponno});">삭제</button>
    </TD> 
  </TR>
 
  </c:forEach>
  
</TABLE>
    <DIV class='bottom_menu'>${paging }</DIV>
    
    

  </DIV>  
 
 
</DIV> <!-- content END -->
<jsp:include page="/mypage/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 