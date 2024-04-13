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
    <div class="container">
        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="Home" class="brand">
                <i class='bx bxs-coffee'></i>
                <span class="text">Coffee Blend</span>
            </a>
            <ul class="side-menu top">
                <li class="active">
                    <a href="SellerDashboard">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Seller Manage</span>
                    </a>
                </li>
                <li class="active">
                    <a href="ManageBlog">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Manage Blog</span>
                    </a>
                </li>
                <li class="active">
                    <a href="ManageOrder">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Manage Order</span>
                    </a>
                </li>
                <li>
                    <a href="LogOut" class="logout">
                        <i class='bx bxs-log-out-circle' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
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
                                <th scope="row">${setting.settingId}</th>
                                <td>${setting.settingName}</td>
                                <td>${setting.description}</td>   
                                <td>${setting.type}</td>
                                
                        </c:forEach>
                    <!-- Use JSTL to iterate over settings -->
                </table>
            </main>
            <!-- MAIN -->
        </section>
    </div>
</body>

        <!-- CONTENT -->
        <script>
            // Lắng nghe sự kiện khi nút "View" được bấm
            var viewButtons = document.getElementsByClassName("viewButton");
            for (var i = 0; i < viewButtons.length; i++) {
                viewButtons[i].addEventListener("click", function () {
                    // Lấy giá trị blog_id từ thuộc tính data-blog-id
                    var blogId = this.dataset.blogId;

                    // Mở một cửa sổ mới với URL BlogController và tham số blogId
                    window.open("BlogController?blogId=" + blogId, "_blank");
                });
            }
        </script>
        <script>
            function checkDate() {
                var firstDate = document.getElementsByName("firstDate")[0].value;
                var secondDate = document.getElementsByName("secondDate")[0].value;

                if (firstDate && secondDate && new Date(secondDate) < new Date(firstDate)) {
                    alert("Second date must be after the first date.");
                    return false;
                }
                return true;
            }
        </script>
        <script >

            CKEDITOR.replace('edit', {
                filebrowserBrowseUrl: 'ckfinder/ckfinder.html',
                filebrowserUploadUrl: 'ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files'
            });
        </script>
        <script src="js/adminDashbord.js"></script>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>

</html>