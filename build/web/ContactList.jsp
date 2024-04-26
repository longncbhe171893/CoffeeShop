<%-- 
    Document   : ContactList
    Created on : Apr 24, 2024, 2:30:56 PM
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
        <script>
    function showToast(message) {
        var toastElement = document.querySelector('.toast');

        if (!toastElement) {
            return;
        }

        var toastBody = toastElement.querySelector('.toast-body');
        toastBody.textContent = message;

        var toast = new bootstrap.Toast(toastElement);
        toast.show();

        // Thiết lập thời gian tồn tại của toast là 5 giây (5000 miligiây)
        setTimeout(function() {
            toast.hide(); // Ẩn toast sau khi đã đến thời gian quy định
        }, 5000);
    }
</script>

    </head>
    <body>
        <   <jsp:include page="headerAdmin.jsp"/>
        <!-- CONTENT -->
        <section id="content">
            <nav>
                
                <i class='bx bx-menu' ></i>
                
                <div class="head-title">
                    <div class="left">
                        <h1>Manage Contact</h1>
                    </div>
                </div>
                
                
                <form action="ContactList" method="get">
                    <div class="form-input">
                        <input type="search" name="search" placeholder="Search by subject...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                    </div>
                </form>
                
            </nav>
            <!-- NAVBAR -->
            
            <!-- MAIN -->
            <main style="
                  margin-top: 53px; ">
                <li>
                    <div>
                        <form action="ContactList"  method="post" style="
                          margin-top: -94px;
                          margin-bottom: -41px;">
                        <select style="
                                background-color: #007BFF;
                                color: white;
                                height: 40px;
                                border-radius: 10px;
                                margin-left: 47px;
                                margin-top: 60px;" name="filter" class="form-select"  onchange="this.form.submit()">
                            <option value="" ${param['filter']==null?"selected":""}>All Type</option>
                            <option value="1" ${param['filter']==1?"selected":""}>Question</option>
                            <option value="2" ${param['filter']==2?"selected":""}>Feedback</option>
                            <option value="3" ${param['filter']==3?"selected":""}>Support</option>
                            
                            
                            
                        </select>
                    </form>
                    </div>
                
                
                            
                </li>
                

                <table class="table" style="margin-top: 60px; margin-bottom: 20px;">
                    <thead>
                        <tr style="font-size: 20px;">
                            <th scope="col">ID</th>
                            <th scope="col">Subject</th>
                            <th scope="col">Type</th>
                            <th scope="col">Name</th>
                            <th scope="col">Action</th>
                            
                        </tr>
                    </thead>
                    <tbody> <!-- Đặt các phần tử HTML trong một phần tử tbody -->
                        <c:forEach var="contact" items="${contacts}">
                            <tr>
                                <td>${contact.getContact_id()}</td>
                                <td>${contact.getSubject()}</td>
                                <td>${contact.getSetting().getName()}</td>
                                <td>${contact.getName()}</td>
                                
                                
                                <td>
                                    <form action="ContactDetails" method="get">
                                    <button type="submit" class="btn btn-success btn-lg">Detail</button>
                                    <input type="hidden"  name="contact_id" value="${contact.getContact_id()}" readonly=""><br>
                                    
                                    </form>
                                </td>
                            </tr>
                        
                        
                        
                    </c:forEach>
                    </tbody>
                </table>
                                    

            </main>
            <!-- MAIN -->
        </section>





        <script src="js/adminDashbord.js"></script>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>

</html>