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
        <title></title>   
        
        

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
                  margin-top: 0px; ">
                
                <ul style="display: flex; list-style-type: none; margin-left: 10px">
                    <li style="margin-right: 20px;">
                        <form action="ContactList" method="post">
                            <select style="
                                    background-color: #007BFF;
                                    color: white;
                                    height: 40px;
                                    border-radius: 10px;
                                    " name="filtertype" class="form-select" onchange="this.form.submit()">
                                <option value="" ${param['filtertype']==null?"selected":""}>All Type</option>
                                <option value="1" ${param['filtertype']==1?"selected":""}>Question</option>
                                <option value="2" ${param['filtertype']==2?"selected":""}>Feedback</option>
                                <option value="3" ${param['filtertype']==3?"selected":""}>Support</option>
                                <option value="4" ${param['filtertype']==4?"selected":""}>Other</option>
                            </select>
                        </form>
                    </li>
                    <li>
                        <form action="ContactList" method="post">
                            <select style="
                                    background-color: #007BFF;
                                    color: white;
                                    height: 40px;
                                    border-radius: 10px;
                                    " name="filterstatus" class="form-select" onchange="this.form.submit()">
                                <option value="" ${param['filterstatus']==null?"selected":""}>All Status</option>
                                <option value="1" ${param['filterstatus']==1?"selected":""}>New</option>
                                <option value="2" ${param['filterstatus']==2?"selected":""}>Solved</option>
                            </select>
                        </form>
                    </li>
                </ul>
                    
                
                
                            
                       

                <table class="table" style="margin-top: 60px; margin-bottom: 20px;">
                    <thead>
                        <tr style="font-size: 20px;">
                            <th scope="col">ID</th>
                            <th scope="col">Subject</th>
                            <th scope="col">Type</th>
                            <th scope="col">Name</th>
                            <th scope="col">Status</th>
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
                                <td>${contact.getStatus()==1?"New":"Solved"}</td>
                                
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
