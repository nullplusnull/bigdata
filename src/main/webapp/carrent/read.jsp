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

 
<script type="text/javascript">
var ajax = new XMLHttpRequest();

$(function()  {   // 자동실행

});



function change () {
  
  
  var startday = $('#startday').val();
  //2019-02-11
  alert(startday);
  var year_month = startday.substr(0, 8);
  var day = startday.substr(8, 10);

  // 0일에서 ~ 9일이면 Number변환시 한자리 수로 변하므로 String형 0을 추가
  day = Number(day);
  var day = day + 1;
  if(day < 9) {
    day = String(day);
    day = "0"+day;
  }
  day = String(day);

  var endday = year_month + day;
  alert(endday);
  $('#endday').val(endday);
  $('#endday').attr('min', startday);
}  


$(function(){
  $('#btn_send2').click(function(){
    var price = $('#price').val();
    alert(price);

    // 년, 월, 일 계산
    var startday = $('#startday').val();
    var endday = $('#endday').val();

    var start_arr = startday.split('-');
    var end_arr = endday.split('-');
    
    var start_day = new Date(start_arr[0], start_arr[1], start_arr[2]); //1992,11,08
    var end_day = new Date(end_arr[0], end_arr[1], end_arr[2]);     //2019-11-08
    

    
    var diff =   end_day - start_day;
    var currhour = 60 * 60 * 1000;
    
    diff_hour = diff/currhour;
    
    // 시간 계산
    var starttime = $('#starttime').val();
    var endtime = $('#endtime').val();
    
    //08:00:00 20:00:00
    var st = starttime.substring(0, 2);  // 08
    var et = endtime.substring(0, 2);   // 20
    
    var dh = et - st
    diff_hour = diff_hour + dh
    var total_price = diff_hour * price
    //$('#cash').val(total_price);
    $('#total_price').val(total_price);
    $('#hour').val(diff_hour);
    
    $('#_start_day').val(startday+" "+starttime);
    $('#_end_day').val(endday+" "+endtime);
    
    /////////////
    
    var startday = $('#startday').val();
    var endday = $('#endday').val();
    var carrentno = $('#carrentno').val();
    
    var starttime = $('#starttime').val();
    var endtime = $('#endtime').val();
    
    var f_startday = startday +" " + starttime;
    var f_endday = endday + " " + endtime;
    alert(f_startday);
    alert(f_endday);

    var form = {
        startday : f_startday,
        endday : f_endday,
        carrentno : carrentno
    }
       
    $.ajax({
      url : 'check_rentperiod_proc',
      type : 'POST',
      data : form,
      dataType: "json",
      success : function(data) {
        alert(data.count)
        $('#panel').text(data.count)
        //$('#panel').val(data)
        if(data.count >= 1) {
          $('#panel').text('해당일은 예약이 마감되었습니다.');
          $('#trans').html('예약마감');
          $('#trans').prop("disabled", true);
        } else {
          //$('#cash').val(total_price);
          if(total_price < 0) {
            $('#panel').text('시간 선택이 잘못되었습니다..');
            $('#trans').html('예약마감');
            $('#trans').prop("disabled", true);
          } else {
          $('#panel').text(total_price+'원')
          $('#trans').html('바로 구매');
          $('#trans').prop("disabled", false);
          }
        }
      },
      error: function() {
        alert("ERROR")
      }
    });
    
  
});
  
  $('#btn_send').click(function(){
    var price = $('#price').val();

    // 년, 월, 일 계산
    var startday = $('#startday').val();
    var endday = $('#endday').val();

    var start_arr = startday.split('-');
    var end_arr = endday.split('-');
    
    var start_day = new Date(start_arr[0], start_arr[1], start_arr[2]); //1992,11,08
    var end_day = new Date(end_arr[0], end_arr[1], end_arr[2]);     //2019-11-08
    
    var diff =   end_day - start_day;
    var currhour = 60 * 60 * 1000;
    
    diff_hour = diff/currhour;
    
    // 시간 계산
    var starttime = $('#starttime').val();
    var endtime = $('#endtime').val();
    
    
    //08:00:00 20:00:00
    var st = starttime.substring(0, 2);  // 08
    var et = endtime.substring(0, 2);   // 20
    
    var dh = et - st
    diff_hour = diff_hour + dh
    var total_price = diff_hour * price
    alert("test1");
    if(total_price < 0) {
      alert(total_price);
      total_price = "예약 날짜가 잘못 입력되었습니다.";
    }
    
    $('#cash').val(total_price);
    $('#total_price').val(total_price);
    $('#hour').val(diff_hour);
    
    $('#_start_day').val(startday+" "+starttime);
    $('#_end_day').val(endday+" "+endtime);
    
    $('#trans').show();
  });
});





function send() {
  var sd = $('#_start_day').val();
  var ed = $('#_end_day').val();

  
  
  if (sd == "") {
    alert('예약을 원하는 날짜를 선택해주세요.\n선택한 날짜에 따라 결제금액이 변경될 수 있습니다.');
    $('#startday').focus();
    return false; // submit 중지
  }
  
  if (ed == "") {
    alert('예약을 원하는 날짜를 선택해주세요.\n선택한 날짜에 따라 결제금액이 변경될 수 있습니다.');
    $('#endday').focus();
    return false; // submit 중지
  }
  
  return true; // submit 진행
}

</script>
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
    <FORM name='frm' method='GET' action='../reservation/create.do'
               enctype="multipart/form-data" onsubmit="return send();" class="form-horizontal">
               <input type="hidden" name='price' id='price' value="${carrentVO.price }">
               <input type="hidden" name='total_price' id='total_price' value="">
               <input type="hidden" name='hour' id='hour' value="">
               <input type="hidden" name='_start_day' id='_start_day' value="">
               <input type="hidden" name='_end_day' id='_end_day' value="">
               <input type="hidden" name='carrentno' id='carrentno' value="${carrentVO.carrentno}">

      <br> 
          <DIV>
            <IMG src='./storage/${carrentVO.rent_file1 }'>
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
              </div>

              <div class="form-group">
                <label for="startwhere" class="col-md-1 control-label">렌트 장소:</label>
                <div >
                  <input type='text' class="form-control input-lg"
                    name='startplace' id='startplace' value='솔데스크'
                    required="required" style='width: 15%;'>
                </div>
              </div>
              
              
                    <div class="form-group">
                <label for="endwhere" class="col-md-1 control-label">반납 장소:</label>
                <div >
                  <input type='text' class="form-control input-lg"
                    name='endplace' id='endplace' value='솔데스크'
                    required="required" style='width: 15%;'>
                </div>
              </div>

   <fieldset class='fieldset_basic'>
     <legend class='legend_basic'>시간 * 금액</legend>
     <ul>
       <li class='li_none'>
        <div class="form-group">
            <label for="startydate" class="col-md-1 control-label">렌트 시작 날짜: </label>
            <div>
              <input type='date' class="input_basic" name='startday', min='${es_day}'' ,
                id='startday' value='${es_day}' required="required", onchange="change();"
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
    
       </li>
       <li class='li_none'>
       <div class="form-group">
            <label for="enddate" class="col-md-1 control-label">렌트 반납 날짜:</label>
          
            <div >
              <input type='date' class="input_basic" name='endday'
                id='endday' value='${ee_day}' required="required", max='2020-01-01'
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
            <br>
          <button type='button'  id='btn_send2'>검색</button>
          </div>
          
       </li>
       <li class='li_none'>
        
         
    <DIV id='panel' class='message' style='text-align: center; background-color: #FFFFFF; '></DIV>
    <!-- <input type='text' name='panel' id='panel' size='100' value='' > -->
       </li>
     </ul>   
     <DIV style='text-align: right;'>
                <button id='trans' type="submit">바로 구매</button>
              
              </DIV> 
     
   </fieldset>
 </form>  
                     
                



      

 
 

<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 