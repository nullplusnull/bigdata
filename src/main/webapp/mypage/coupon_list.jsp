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
 
}); 


function issue_coupon(couponno) {
  var couponno = couponno; 
  var form = {
      couponno : couponno,
  }
    
  $.ajax({  
    url : '../usercoupon/create.do', 
    type : 'POST',
    data : form,
    dataType: "json",
    success : function(data) { 
      alert('쿠폰이 발급되었습니다.')
    },  
    error: function() {
      alert("ERROR")
    }
  });
}

</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 120%;'>  
 
  <ASIDE style='float: left;'>
    렌트 기간 > 등록
  </ASIDE>   

  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>

    <table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 13%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/> 
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
  </colgroup>  
  <TR>
    <TH class='th'>쿠폰 이미지</TH>
    <TH class='th'>쿠폰이름</TH>
    <TH class='th'>사용조건</TH>
    <TH class='th'>할인금액</TH>
    <TH class='th'>유효기간</TH>
    <TH class='th'>상태</TH>
  </TR>

  <c:forEach var="CouponVO" items="${list }">
  <TR>
    <c:if test="${CouponVO.thumb != null}">
      <TD class='td'><img src="./storage/${CouponVO.thumb}"  style='width: 50px; height: 50px;' />   
      </TD>      
    </c:if>      
    <TD class='td'>${CouponVO.couponname}</TD>
    <TD class='td'>${CouponVO.use_condition}</TD>      
    <TD class='td'>${CouponVO.discount_cost}</TD>
    <TD class='td'>${CouponVO.expiry_date}</TD>  
    <TD class='td'>
        <button type='button'  onclick="issue_coupon(${CouponVO.couponno});">쿠폰받기</button>
    </TD> 
  </TR>
  </c:forEach>
</TABLE>




<table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 13%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/> 
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
  </colgroup>  
  <TR>
    <TH class='th'>쿠폰 이미지</TH>
    <TH class='th'>쿠폰이름</TH>
    <TH class='th'>사용조건</TH>
    <TH class='th'>할인금액</TH>
    <TH class='th'>유효기간</TH>
    <TH class='th'>상태</TH>
  </TR>

  <c:forEach var="CouponVO" items="${list }">
  <TR>
    <c:if test="${CouponVO.thumb != null}">
      <TD class='td'><img src="./storage/${CouponVO.thumb}"  style='width: 50px; height: 50px;' />   
      </TD>      
    </c:if>      
    <TD class='td'>${CouponVO.couponname}</TD>
    <TD class='td'>${CouponVO.use_condition}</TD>      
    <TD class='td'>${CouponVO.discount_cost}</TD>
    <TD class='td'>${CouponVO.expiry_date}</TD>  
    <TD class='td'>
        <button type='button'  onclick="issue_coupon(${CouponVO.couponno});">쿠폰받기</button>
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