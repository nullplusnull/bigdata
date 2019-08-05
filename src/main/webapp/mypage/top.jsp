<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
  <!-- 화면 상단 메뉴 -->
  <DIV id='top_menu'
          style="background-image: url('${pageContext.request.contextPath}/menu/images/top_image3.jpg')" >
    <DIV class='top_menu_label'>Resort 0.1</DIV>
    <NAV class='top_menu_list'>
      <span style='padding-left: 0.5%;'></span>
      <A class='menu_link' href='${pageContext.request.contextPath}/carrent/list.do'>전체 카테고리 목록</A><span class='top_menu1'> | </span>
      <A class='menu_link' href='${pageContext.request.contextPath}/mypage/buy_list.do'>마이 페이지</A><span class='top_menu1'> | </span>
         
    </NAV>
  </DIV>
  
  <!-- 화면을 2개로 분할하여 좌측은 메뉴, 우측은 내용으로 구성 -->  
  <DIV class="row" style='margin-top: 2px;'>
    <DIV class="col-md-2"> <!-- 메뉴 출력 컬럼 -->
      <img src='${pageContext.request.contextPath}/menu/images/myimage.png' style='width: 100%;'>
      <div style='margin-top: 5px;'>
        <img src='${pageContext.request.contextPath}/menu/images/myface.png'>왕눈이
      </div>
      
      <div>
        <ul>
          <li>나의 예약/구매 내역</li>
          <li>찜한상품</li>
          <li>나의 혜택정보</li>
          <li>나의 게시글 모음
            <ol>
              <li>1:1 문의내역</li>
              <li>이용후기 내역</li>
            </ol>
            </li>
          <li>나의 정보
          <ol>
            <li>개인 정보 수정</li>
            <li><a href="./do">계좌관리</a></li>
            <li>비밀번호 변경</li>
            <li>회원탈퇴</li>
          </ol>
          </li>
          <li>나의 혜택정보
           <ol>   
           <li><a href="../coupon/coupon_list.do">쿠폰내역</a></li>  
           </ol>
          </li>
        </ul>
      </div>
       
 
    </div>
      
    <DIV class="col-md-10 cont">  <!-- 내용 출력 컬럼 -->  
    
    
    
    
    
    