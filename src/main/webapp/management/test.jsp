<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 

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


<script type="text/javascript">

$(function(){
});

</script>


<script type="text/javascript">

function page_replace(prodcateno, viewno, nowPage, prod_word) {

  if(nowPage == undefined){
    nowPage = 1;
  }

  if(prod_word == undefined){
    prod_word = "";
  }


  location.href="./list_by_prodcateno_search_paging.do?prodcateno="+prodcateno
                    +"&viewno="+viewno+"&nowPage="+nowPage+"&prod_word="+prod_word;
}

function prodest(productno, customerno, prodcateno, viewno, nowPage, prod_word){
  var frm_prodest = $('#frm_prodest');
  frm_prodest.attr('action', './prodest_create.do');
  $('#productno', frm_prodest).val(productno);
  $('#customerno', frm_prodest).val(customerno);
  frm_prodest.submit();
}

 

function estimate_add(prod_price){
  var count = $('#count').val()
  $('#count_panel').html(prod_price*count)
}

 
function estimate(productno){
  $('#panel_estimate').show();
  var params = 'productno=' + productno;
  $.ajax({
    url: "./readp.do",
    type: "get", // get
    cache: false,
    async: true,  // true: 비동기
    dataType: "json", // 응답 형식: json, xml, html...
    data: params,
    success: function(rdata) {
      var count = 0
      if(count == 0){
        count = 1;
      }else if(count > 0){
        count = $('#count').val();
      }

      $('#estimate_td').append('<tr>');
      $('#estimate_td').append('<td><span class="category">'+rdata.prodcate_title+'</span></td>');
      $('#estimate_td').append('<td><span class="category">'+rdata.prod_title+'</span></td>');
      $('#estimate_td').append('<td><span class="category">'+rdata.prod_price+'</span></td>');
      $('#estimate_td').append('<td><input type="number" onchange="javascript:estimate_add('+rdata.prod_price+');" name="period" id="count" size="10" value="1" min="1" max="60" step="1"></td>');
      $('#estimate_td').append('<td><span id="count_panel" name="" class="category" ></span></td></tr>');
      $('#estimate_td').append('</tr>');
      $('#main_panel').hide(); 
    },

    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 

    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
 

  // 처리중 출력
  $('#main_panel').html("<IMG src='./images/ani01.gif' style='width: 5%;'>");
  $('#main_panel').show();
}

</script>
</head>

<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>

<!-- 출력 폼-->
  <DIV id='panel_replace' style='display: none; padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_replace' id='frm_replace' method='GET' 
                action='./list_by_prodcateno_search_paging.do'> 
      <input type='hidden' name='prodcateno' id='prodcateno' value=''>
      <input type='hidden' name='viewno' id='viewno' value='2'>
      <button type="submit" id='submit'></button>
    </FORM>
  </DIV>

  <form name='frm' id='frm' method="get" action="./list_by_prodcateno_search_paging.do">
  <ASIDE style='float: left;'>
    <A href='../prodcategrp/list.do'>카테고리 그룹</A> >
    <A href='../prodcate/list_by_prodcategrpno.do?prodcategrpno=${prodcategrp_ProdcateVO.prodcategrpno }'>${prodcategrp_ProdcateVO.prodgrp_title }</A> > 
    <A href='./list_by_prodcateno_search.do?prodcateno=${prodcategrp_ProdcateVO.prodcateno }'>${prodcategrp_ProdcateVO.prodcate_title }</A>  

    <c:if test="${param.word.length() > 0}"> 
      >
      [${param.word}] 검색 목록(${search_count } 건)

    </c:if>

  </ASIDE>

  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>

    <span class='menu_divide' >│</span> 
    <A href='./create.do?prodcateno=${prodcategrp_ProdcateVO.prodcateno }'>등록</A>

    <input type='hidden' name='prodcateno' id='prodcateno' value='${prodcategrp_ProdcateVO.prodcateno }'>
    <span class='menu_divide' >│</span> 

    <c:choose>
      <c:when test="${param.prod_word != '' }">
        <input type='text' name='prod_word' id='prod_word' value='${param.prod_word }' style='width: 35%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='prod_word' id='prod_word' value='' style='width: 35%;'>
      </c:otherwise>
    </c:choose>

    <button type='submit'>검색</button>
    <button type='button' 
                 onclick="location.href='./list_by_prodcateno.do?prodcateno=${prodcategrp_ProdcateVO.prodcateno }'">전체 보기</button>
  </ASIDE>
  </form>

  
  <FORM name='frm_prodest' id='frm_prodest' method='post' action=''>
    <input type='hidden' name='productno' id='productno' value=''>
    <input type='hidden' name='customerno' id='customerno' value='1'>
  </FORM>

  

  <DIV id='panel_estimate' style='display: none; padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
  <table>
    <thead>
      <tr>
      <th>분류</th>
      <th>상품명</th>
      <th>판매가격</th>
      <th>수량</th>
      <th>견적금액</th>
    </thead>

    <tbody id='estimate_td'>
    </tbody>

 

  </table>
  <DIV>
  <h3 class="cart-body_compare_sum">총 금액 : </h3> 
  </DIV>
  </DIV>

  

  <table width="980" border="0" align="center" cellpadding="0" cellspacing="0" style="border-top:2px solid #6cad2c">

    <tbody id= 'replace_product'>

    <tr> 

      <td id="page_replace_1"  onclick="javascript:page_replace(${prodcategrp_ProdcateVO.prodcateno }, 1, ${nowPage }, '${param.prod_word }'); page_replace_add(this.id);" class="table_product${param.viewno == 1 ? '_cursor' : '' }">인기순</td>
      <td id="page_replace_2"  onclick="javascript:page_replace(${prodcategrp_ProdcateVO.prodcateno }, 2, ${nowPage }, '${param.prod_word }'); page_replace_add(this.id);" class="table_product${param.viewno == 2 ? '_cursor' : '' }">신제품순</td>
      <td id="page_replace_3"  onclick="javascript:page_replace(${prodcategrp_ProdcateVO.prodcateno }, 3, ${nowPage }, '${param.prod_word }'); page_replace_add(this.id);" class="table_product${param.viewno == 3 ? '_cursor' : '' }">낮은가격순</td>
      <td id="page_replace_4"  onclick="javascript:page_replace(${prodcategrp_ProdcateVO.prodcateno }, 4, ${nowPage }, '${param.prod_word }'); page_replace_add(this.id);" class="table_product${param.viewno == 4 ? '_cursor' : '' }">높은가격순</td>
      <td id="page_replace_5"  onclick="javascript:page_replace(${prodcategrp_ProdcateVO.prodcateno }, 5, ${nowPage }, '${param.prod_word }'); page_replace_add(this.id);" class="table_product${param.viewno == 5 ? '_cursor' : '' }">상품명순</td>
      <td id="page_replace_6"  onclick="javascript:page_replace(${prodcategrp_ProdcateVO.prodcateno }, 6, ${nowPage }, '${param.prod_word }'); page_replace_add(this.id);" class="table_product${param.viewno == 6 ? '_cursor' : '' }">제조사순</td>
    </tr>
    </tbody>
  </table>

  <div class='menu_line' style='clear: both;'></div>       
  <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 50%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>

      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>파일</th>
          <th>제목</th>
          <th>등록일</th>
          <th>추천</th>
          <th>기타</th>
        </tr>
      </thead>

      

      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="productVO" items="${list }">
          <tr>
            <td style='vertical-align: middle;'>
            <c:choose>
              <c:when test="${productVO.thumb != ''}">
                <IMG id='thumb' src='./storage/${productVO.thumb}' > <!-- 이미지 파일명 출력 -->
              </c:when>
              <c:otherwise>
                <IMG id='thumb' src='./images/none1.png' style='width: 120px; height: 80px;'> <!-- 파일이 존재하지 않는 경우 -->
              </c:otherwise>
            </c:choose>
            </td>          
            <td style='vertical-align: middle;'>
              <c:choose>
                <c:when test="${productVO.prod_ansnum == 0 }"> <!-- 부모글인 경우 -->
                  <img src='./images/ting1.png'>
                </c:when>
                <c:when test="${productVO.prod_ansnum > 0}">    <!-- 답변글인 경우 -->
                  <img src='./images/white.png' style='width: ${productVO.prod_indent * 20}px; opacity: 0.0;'>
                  <img src='./images/reply3.png'>
                </c:when>
              </c:choose>
              <a href="./read.do?productno=${productVO.productno}&prodcateno=${productVO.prodcateno}&prod_word=${param.prod_word}&nowPage=${param.nowPage}">${productVO.prod_title}</a> 
            </td> 

 

            <td style='vertical-align: middle;'>${productVO.prod_rdate.substring(0, 16)}</td>

            <td style='vertical-align: middle;'>${productVO.prod_good}</td>

            <td style='vertical-align: middle;'>

              <a href="./update.do?productno=${productVO.productno}&prodcateno=${productVO.prodcateno}&nowPage=${param.nowPage}&prod_word=${param.prod_word}"><img src="./images/update.png" title="수정" border='0'/></a>

              <a href="./delete.do?productno=${productVO.productno}&prodcateno=${productVO.prodcateno}&nowPage=${param.nowPage}&prod_word=${param.prod_word}"><img src="./images/delete.png" title="삭제"  border='0'/></a>

              G${productVO.prod_grpno}/A${productVO.prod_ansnum}

              <a name='${productVO.productno}' href="javascript:prodest(${productVO.productno}, 1, ${prodcategrp_ProdcateVO.prodcateno }, 1, ${nowPage }, '${param.prod_word });"><img src="./images/youtube.png" title="견적" border='0'/></a>

              <!-- <a name='${productVO.productno}' href="javascript:estimate(${productVO.productno });"><img src="./images/youtube.png" title="견적" border='0'/></a> -->
            </td>
          </tr>
        </c:forEach>

      </tbody>
  </table>
  <DIV class='bottom_menu'>${paging }</DIV>
  <br><br>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

 

</html>