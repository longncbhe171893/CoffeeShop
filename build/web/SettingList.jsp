<%-- 
    Document   : SettingList
    Created on : Apr 12, 2024, 8:23:13 AM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="DAO.*" %>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="CSSsimple/adminDashbord.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/nice-select.css" rel="stylesheet">
        <title>Coffee</title>     
           <script src="ckeditor/ckeditor.js"></script> 
        <script src="ckfinder/ckfinder.js"></script>
        <title>Seller Dashbord</title>   
        <style>
            /* Định dạng nút View */
            .viewButton {
                margin-top: 10px;
                background: none;
                border: none;
                padding: 0;
                cursor: pointer;
            }

            /* Định dạng biểu tượng mắt */
            .eye-icon {
                width: 16px;
                height: 16px;
                background-image: url("image/eye.jpg");
                /* Đường dẫn tới ảnh biểu tượng mắt */
                background-repeat: no-repeat;
                background-size: cover;
                display: inline-block;
                transform: scale(2);
                border: 2px solid transparent;
                /* Viền mặc định là trong suốt */
                border-radius: 50%;
                /* Bo tròn viền */
                transition: box-shadow 0.3s ease;
                /* Hiệu ứng chuyển động mượt mà cho box-shadow */
            }
            .viewButton:hover .eye-icon {
                border: 2px solid limegreen;
                /* Viền sáng màu xanh lá cây */
            }
            .container {
        display: flex;
    }
    #sidebar {
        flex: 1; /* tỉ lệ 1 */
        margin-right: 20px; /* khoảng cách giữa sidebar và content */
    }
    #content {
        flex: 3; /* tỉ lệ 3 */
    }
        </style>
    </head>
    <body>
    <   <jsp:include page="headerAdmin.jsp"/>
        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Manage Setting</h1>
                    </div>
                </div>
                <table class="table" style="margin-top: 20px; margin-bottom: 20px;">
                    <thead >
                        <tr style="font-size: 20px;">
                            <th scope="col">ID</th>
                            <th scope="col">Setting Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Type</th>                             
                            <th scope="col" colspan="2" style="text-align: center">Action</th>
                        </tr>
                    </thead>
                    <c:forEach var="setting" items="${settings}">
                            <tr>
                                <td>${setting.getSetting_id()}</td>
                                <td>${setting.getName()}</td>
                                <td>${setting.getDescription()}</td>   
                                <td>${setting.getType()}</td>
                                <td><button>Details</button></td>
                                
                        </c:forEach>
                    <!-- Use JSTL to iterate over settings -->
                </table>
            </main>
            <!-- MAIN -->
        </section>
    
</body>

        
        <script src="js/adminDashbord.js"></script>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>

</html>