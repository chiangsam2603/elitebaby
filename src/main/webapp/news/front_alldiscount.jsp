<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.web.service.*"%>
<%@ page import="com.tibame.web.vo.*"%>

<%
LatestNewsService newsSvc = new LatestNewsService();
List<LatestNewsVO> list = newsSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- bootstrap引用cdn -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous" />
    <!-- 下載bootstrap引用 -->
    <!-- <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css"> -->

    <!-- css連結 -->
     <link rel="stylesheet" href="/elitebaby/css/official.css"/>
     <link rel="stylesheet" href="/elitebaby/css/front_navbar.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/news/css/front_all.css"/>
   
    <!-- FontAwesom 連結 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">

    <title>菁英產後護理之家</title>
</head>

<body>
     <div id="nav">
        <nav class="navbar navbar-expand-lg navbar-light c1 fw-bold  ">
            <div class="container-fluid" >
                <img src="../images/logo.jpg">
                <a class="navbar-brand" href="/elitebaby/member/homepage.html" id="home">菁英產後護理之家</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" style=" justify-content: space-between;" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">最新消息 </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="/elitebaby/news/front_allnews.jsp">綜合</a></li>
                                <li><a class="dropdown-item" href="/elitebaby/news/front_alldiscount.jsp">優惠</a></li>
                                <li><a class="dropdown-item" href="/elitebaby/news/front_allnormal.jsp">一般</a></li>
                               
                            </ul>
                        <li class="nav-item"><a class="nav-link" href="/elitebaby/about.html">關於我們</a></li>
                       
                        <li class="nav-item"><a class="nav-link" href="/elitebaby/room/introduce.html">房型介紹</a></li>
                        <li class="nav-item"><a class="nav-link" href="/elitebaby/mea/meal_front.html">月子膳食</a></li>
                        <li class="nav-item"><a class="nav-link" href="../forum/home">討論區</a></li>
                        <!-- ====================================會員中心================================================= -->
                        <li class="nav-item dropdown member">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">
                                會員中心
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <button type="button" class=" dropdown-item btn_edit"data-toggle="modal" data-target="#editModal">
                                    編輯會員資料
                                  </button>
                                  <li><a class="dropdown-item" href="/elitebaby/room/order.html">房型訂單</a></li>
                                  <li><a class="dropdown-item" href="/elitebaby/meal/user_order.html">商品訂單</a></li>
                                  <li> <a class="dropdown-item" href="/elitebaby/visit/VisitRoomFrontGetAll.html">預約訂單</a> </li>
                                  <li> <a class="dropdown-item" href="/elitebaby/visit/ReportEmailFrontGetAll.html">寄信匣</a> </li>
                                  <li> <a class="dropdown-item" href="/elitebaby/visit/ReportEmailFrontRSMail.html">收信匣</a> </li>
                                  <li> <a class="dropdown-item" href="/elitebaby/visit/ReportEmailFrontInsert.html">問題回報</a> </li>
                            </ul>
                        </li>
                </div>
                <div id="login">
                    <ul class="navbar-nav navbar-right">
                        <li class="nav-item"><a class="nav-link " id="login_btn">登入</a></li>
                        <li class="nav-item"><a class="nav-link " href="/elitebaby/member/register.html">註冊</a></li>
                    </ul>
                </div>
<!-- 會員資料編輯彈跳視窗 -->
  <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editModalLabel">編輯會員資料</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="name">姓名</label>
              <input type="text" class="form-control" id="name">
            </div>
            <div class="form-group">
              <label for="address">居住地址</label>
              <input type="text" class="form-control" id="address">
            </div>
            <div class="form-group">
              <label for="phoneNumber">手機</label>
              <input type="text" class="form-control" id="phoneNumber">
            </div>
            <div class="form-group">
              <label for="password">密碼</label>
              <input type="password" class="form-control" id="password">
            </div>
            <div class="form-group">
              <label for="confirmpassword">確認密碼</label>
              <input type="password" class="form-control" id="confirmpassword">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary btn_save ">儲存</button>
        </div>
      </div>
    </div>
  </div>
                <!-- ==============================================按鈕觸發============================================ -->
                <!-- ===============================================購物車============================================= -->
                <div id="logout">
                    <ul class="navbar-nav navbar-right">
                        <button id="cart_btn"
                            style="border: 0px; margin-right: 10px;margin-top: 5px; " class="c1"><svg
                                xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                                class="bi bi-cart2" viewBox="0 0 16 16">
                                <path
                                    d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
                            </svg>
                            <span class="badge badge-danger navbar-badge ;"></span>
                        </button>
                       <!-- ===================================鈴鐺==================================== -->
                       <button id="cart_btn"
                            style="border: 0px; margin-right: 10px; margin-top: 5px;" class="c1"><svg
                                xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                                class="bi bi-bell" viewBox="0 0 16 16">
                                <path
                                    d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
                            </svg>
                            <span class="badge badge-danger navbar-badge emailBill;"></span>
                        </button>
                        <!-- Button trigger modal -->
                        <button type="button" id="logoutButton" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                            登出
                        </button>
                        <button id="registerButton" class="register_btn">註冊</button>
                        <!-- 登入按鈕 -->
                        <button id="loginButton" class="login_btn">登入</button>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div id="blank_area">
        <!-- 此處留空 -->
    </div>
    <ul class="container-filter categories-filter">
        <li>
            <a class="categories active" href="/elitebaby/news/front_allnews.jsp">綜合</a>
        </li>
        <li>
            <a class="categories" href="/elitebaby/news/front_allnormal.jsp">一般</a>
        </li>
        <li>
            <a class="categories" href="/elitebaby/news/front_alldiscount.jsp">優惠</a>
        </li>
    </ul>
    <!-- 搜尋欄位 -->
    <div class="container">
    <form method="post" action="<%=request.getContextPath()%>/Latestnews.do">
            <input class="bar" id="tags" placeholder="搜尋..." name=select>
            <input type="hidden" name="action" value="search">
            <button id="search" type="submit">
                <i class="fas fa-search"></i>
            </button>
        </form>
    </div>
    
    <!-- 圖片內容 -->
    <c:forEach var="latestNewsVO" items="${list}" >
<c:if test="${latestNewsVO.sortId == 3}">
  <section class="sec1 -type1">
        <div class="flex_container">
            <div class="flex_items -left">
            <img class="figure-img img-fluid rounded" src="<%=request.getContextPath()%>/LatestNewsServlet?action=get_photo&&newsId=${latestNewsVO.newsId}">
            </div>
            <div class="flex_items -right">
            <a href="/elitebaby/LatestNewsServlet?action=FindBynewsId&&newsId=${latestNewsVO.newsId}">
            <h1 class="title1">${latestNewsVO.postTitle}</h1>
            <p class="para">${latestNewsVO.newsIntro}</p>
           </a>
            </div>
        </div>
    </section>
    </c:if>
	 </c:forEach>
		
    <div>
        <p class="text-center">
            10488 台北市中山區南京東路三段219號5樓｜TEL : 02-2712-0589｜FAX :
            02-2794-0123
        </p>
    </div>
 <script src="<%=request.getContextPath()%>/news/vendors/jquery/jquery-3.6.3.min.js"></script>
  <!-- jquery ui連結-->
    <script src="<%=request.getContextPath()%>/news/js/jquery-ui.js"></script>
    <!-- bootstrap引用cdn -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
        </script>
    <!-- 下載bootstrap引用 -->
    <!-- <script src="./vendors/bootstrap/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"
    ></script> -->
</body>
</html>