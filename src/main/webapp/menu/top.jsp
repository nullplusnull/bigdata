<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
  <!-- 화면 상단 메뉴 -->
  <DIV id='top_menu'
          style="background-image: url('${pageContext.request.contextPath}/menu/images/top_image3.jpg'); height:120px;" >
    <DIV class='top_menu_label'>Resort 0.1</DIV>
    <NAV class='top_menu_list'>
      <span style='padding-left: 0.5%;'></span>
      
      <A class='menu_link' href='${pageContext.request.contextPath}/carrent/list.do'>전체 카테고리 목록</A><span class='top_menu1'> | </span>
      <A class='menu_link' href='${pageContext.request.contextPath}/mypage/buy_list.do'>마이 페이지</A><span class='top_menu1'> | </span>
      <A class='menu_link' href='${pageContext.request.contextPath}/management/list_paging_search.do'>관리</A><span class='top_menu1'> | </span>
      <A class='menu_link' href='${pageContext.request.contextPath}/coupon/list.do'>쿠폰 관리</A><span class='top_menu1'> | </span>
      
      <c:choose>
        <c:when test="${sessionScope.userid == null}">
          <A class='menu_link'  href='${pageContext.request.contextPath}/user/login.do' >회원 Login</A> <span class='top_menu1'> | </span>
        </c:when>
        <c:otherwise>
          ${sessionScope.userid } <A class='menu_link'  href='${pageContext.request.contextPath}/user/logout.do' >Logout</A> <span class='top_menu1'> | </span>
        </c:otherwise>
      </c:choose>
      <c:choose>
        <c:when test="${sessionScope.adminid == null}">
          <A class='menu_link'  href='${pageContext.request.contextPath}/admin/login.do' >관리자 Login</A> <span class='top_menu1'> | </span>
        </c:when>
        <c:otherwise>
          ${sessionScope.adminid } <A class='menu_link'  href='${pageContext.request.contextPath}/admin/logout.do' >Logout</A> <span class='top_menu1'> | </span>
        </c:otherwise>
      </c:choose>
      <c:if test="${sessionScope.adminid ne null}"> 
        <A class='menu_link'  href='${pageContext.request.contextPath}/user/list_by_paging.do?nowPage=1'>회원목록</A> <span class='top_menu1'> | </span>  
      </c:if> 
      <c:if test="${sessionScope.userid ne null}"> 
        <A class='menu_link'  href='${pageContext.request.contextPath}/user/read.do?userno=${userno}'>마이페이지</A> <span class='top_menu1'> | </span>
        <A class='menu_link'  href='${pageContext.request.contextPath}/account/create.do'>계좌등록</A> <span class='top_menu1'> | </span>
      </c:if>
    </NAV>
  </DIV>

  
  <%-- <!-- 화면을 2개로 분할하여 좌측은 메뉴, 우측은 내용으로 구성 -->  
  <DIV class="row" style='margin-top: 2px;'>
    <DIV class="col-md-2"> <!-- 메뉴 출력 컬럼 -->
      <img src='${pageContext.request.contextPath}/menu/images/myimage.png' style='width: 100%;'>
      <div style='margin-top: 5px;'>
        <img src='${pageContext.request.contextPath}/menu/images/myface.png'>왕눈이
      </div>
       ▷ 블로그 소개
      <c:import url="/cate/list_index_left.do"/>
    </div> --%>
      
    <DIV class="col-md-10 cont">  <!-- 내용 출력 컬럼 -->  
    
      