

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSSsimple/blogList.css"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    </head>
    <body style="background-color: #101012;">
        <jsp:include page="header.jsp"/>
        <div style="margin-top: 100px"></div>
        <h1 style="text-align: center; color: #FF8C00">Blog List</h1>
        <div style="margin-top: 60px"></div>


        <div class="container">


            <div class="row">
                <div class="col-md-8">
                    <c:forEach items="${data}" var="i"> 
                        <div class="col-md-12 column-style">
                            <div class="wrap-item">
                                <a href="BlogController?blogId=${i.getBlog_id()}">
                                    <img class="item-image" style=" width: 650px!important;" src="${i.getBlog_image()}" alt="image">
                                    <div class="item-detail-infor">
                                        <span class="icon-calendar">${i.getPost_date()}</span>&ensp;
                                        <span class="icon-person">${i.getUser().getName()}</span>&ensp;
                                        <span class="icon-type">${i.getSetting().getName()}</span>&ensp;
                                    </div>
                                    <div class="item-title" style="color: gray;">
                                        <h5 style="color: white">${i.getBlog_title()} </h5>
                                        <span style="color: gray">${i.getDescription()} </span>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach> 

                    <!--<div class="wrap-pagination">
                </div>
            </div>-->

                    <div class="wrap-pagination">
                        <center>
                            <c:set var="page" value="${requestScope.page}" />
                            <div>
                                <c:forEach begin="1" end="${requestScope.numberOfPage}" var="i">
                                    <a href="Blog?page=${i}">${i}</a>
                                </c:forEach>
                            </div>
                        </center>
                    </div>
                </div>
                <div class="col-md-4">
                    <%@include file="SidebarBlog.jsp" %>    
                </div>        
            </div>
        </div>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
