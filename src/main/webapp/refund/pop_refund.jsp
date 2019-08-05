<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>${param.userid }님의 계좌목록</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
 
<script type="text/javascript">

$(function()  {   // 자동실행
  var total_price = $("#total_price", opener.document).val();
  $('#total_price').val(total_price);
  var reservationno = $("#reservationno", opener.document).val();
  $('#reservationno').val(reservationno);


});


var paramsList = location.search.substring(1).split("&"); //파라미터가 담긴 배열


//파라미터명으로 파라미터값 추출하기. (편하게 호출하기위해 작성)
function paramsFunc(paramsNm) {
 var nullChk = "";
 
 for(var i=0; i<paramsList.length; i++) {
  if(paramsNm == paramsList[i].split("=")[0]) {
   return paramsList[i].split("=")[1]; 
  }else {
   if(i == paramsList.length-1) nullChk = true;
  }
 }
 if(nullChk) {
  alert("파라미터가 존재하지 않습니다.");
  //return location.href="#";
 }
}




$(function(){
  $('#confirm_id').click(function(){
    var refund = $('input:radio[name="refund"]:checked').val();
    var paymentno = paramsFunc("paymentno");
    alert(paymentno);
    alert(refund);
    
    var form = {
        refund : refund,
        paymentno : paymentno
    }
    
    // payment/create.do
    $.ajax({
      url : 'create.do',
      type : 'POST',
      data : form,
      dataType: "json",
      success : function(data) {
        alert("테스트합니다.");
        $('#panel').text(data.count);
        alert(data.resultno);
        if(data.resultno == 1) {
          alert("결제정보에 오류가 있습니다.");
          window.location.href = 'http://www.naver.com/';
        } else if(data.resultno == 2) {
          alert("예약정보에 오류가 있습니다.");
          window.location.href = 'http://www.daum.co.kr/';
        } else if(data.resultno == 3) {
          alert("환불정보에 오류가 있습니다.");       
        } else if(data.resultno == 0) {
          alert("정상적으로 환불이 완료되었습니다.");
          $(opener.document).find("#btn_send").hide(); //find를 이용한 jquery
          window.close();
        } else { 
          alert(data.resultno);
        }
      },
      error: function() {
        alert("ERROR")
      }
    });
    

     //self.close();
  });
 });


</script>
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<DIV class='content' style='width: 90%;'> 

  <div class='menu_line'></div>
  
<FORM name='frm' method='GET' action='../reservation/create.do'
               enctype="multipart/form-data" onsubmit="return send();" class="form-horizontal">
  
  <input type="hidden" name='accountno' id='accountno' value="">

   <h2>환불 사유를 선택해주세요</h2>
  <ul>
    <li>
      <label><input type="radio" name='refund'  value='일정취소' /> 일정이 취소되거나 변경됨</label>
    </li>
    <li>
       <label><input type="radio"  name='refund'  value='단순변심'  /> 단순 변심</label>
    </li>
    <li>
       <label><input type="radio"   name='refund' value='차량변경'  /> 다른 차량으로 변경</label>
    </li>
    <li>
       <label><input type="radio"  name='refund'  value='불만족'  /> 현재 조건의 불만족</label>
    </li>
    <li>
       <label><input type="radio"  name='refund'  value='타사제품구매'  /> 타사에서 다 나은 조건을 찾음</label>
    </li>
    <li>
       <label><input type="radio"   name='refund'  value='기타'> 기타</label>
    </li>
  </ul>


  <DIV class='bottom_menu'>
    <button type='button' id='confirm_id'>확인</button>
    <button type='button' onclick="location.reload();">새로 고침</button>
  </DIV>

  </FORM>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
 
</html>
  