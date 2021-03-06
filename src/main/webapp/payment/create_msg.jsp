<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <input type="hidden" name='total_price' id='total_price' value="${param.total_price}">
    <input type="hidden" name='reservationno' id='reservationno' value="${param.reservationno}">
    
    <!-- redirectAttributes.addAttribute("startday", carrent_RentperiodVO.getStartday());
     redirectAttributes.addAttribute("endday", carrent_RentperiodVO.getEndday());
     redirectAttributes.addAttribute("power", carrent_RentperiodVO.getPower());
     redirectAttributes.addAttribute("carname", carrent_RentperiodVO.getCarname());
     redirectAttributes.addAttribute("price", carrent_RentperiodVO.getPrice());
     redirectAttributes.addAttribute("hour", carrent_RentperiodVO.getHour());
     redirectAttributes.addAttribute("total_price", carrent_RentperiodVO.getTotal_price());
     ReservationVO reservationVO_ = reservationProc.read_by_rentno(rentno); -->
    
    <table class="table table-striped" style='width: 100%;'>
  <%-- <caption>관리자만 접근가능합니다.</caption> --%>
  <colgroup>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 35%;'/>
  </colgroup>
  <TR>
    <TH class='th'>차이름</TH>
    <TH class='th'>연료</TH>
    <TH class='th'>시간당 가격</TH>
    <TH class='th'>대여 시간</TH>
    <TH class='th'>총 금액</TH>
    <TH class='th'>시작일~종료일</TH>
  </TR>
 
  <TR>
    <TD class='td'>${param.carname}</TD>
    <TD class='td'>${param.power}</TD>
    <TD class='td'>${param.price}</TD>
    <TD class='td'>${param.hour}</TD>    
    <TD class='td'>${param.total_price}</TD>
    <TD class='td'>${param.startday} ~ ${param.endday}</TD> 
  </TR>
  
</TABLE>
    <c:choose>
    <c:when test="${param.count eq 1}">
        결제가 완료되었습니다.
    </c:when>

    <c:otherwise>
      결제중 문제가 발생했습니다.
    </c:otherwise>
</c:choose>

    
              <DIV style='text-align: right;'>
                <button type="submit">결제</button>
                
                <%-- <button type="button"
                  onclick="location.href='./list.do?rentno=${rentperiodVO.rentno}'">취소[목록]</button> --%>
              </DIV>
              선택된 계좌 <input type="text" name="accountno" id="accountno" value=''>
        </FORM>
  </DIV>
  <input type='text' name='data1' id='data1' required="required" value='' style='width: 35%;'>
 <button id='opt01'>계좌선택</button>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 