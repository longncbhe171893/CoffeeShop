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
                        <h1>Manage Setting</h1>
                    </div>
                </div>
                
                
                <form action="SettingLists" method="get">
                    <div class="form-input">
                        <input type="search" name="search" placeholder="Search...">
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
                        <form action="SettingLists"  method="post" style="
                          margin-top: -94px;
                          margin-bottom: -41px;">
                        <select style="
                                background-color: #007BFF;
                                color: white;
                                height: 40px;
                                border-radius: 10px;
                                margin-left: 47px;
                                margin-top: 53px;" name="sort" class="form-select"  onchange="this.form.submit()">
                            <option value="" ${param['sort']==null?"selected":""}>Sort Default</option>
                            
                            <option value="1" ${param['sort']==1?"selected":""}>Setting Name</option>
                            <option value="2" ${param['sort']==2?"selected":""}>Type</option>
                            <option value="3" ${param['sort']==3?"selected":""}>Status</option>
                            
                        </select>
                    </form>
                    </div>
                
                
                            <div>            
                <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#myModalAddNew">Add Setting</button>
                            </div>
                <!-- Modal -->
                <div class="modal fade" id="myModalAddNew" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Add Setting</h4>
                            </div>
                            <form action="AddSetting" method="post" >
                                <div class="modal-body">
                                    <b>Setting Name* </b><input type="text" class="form-control" value="${sname}" required name="name"><br>  
                                    <b>Description </b>
                                    <div class="form-control">
                                        <textarea id="edit" rows="5" name="description" class="form-control" placeholder="Write some thing..."  value="${sdescription}"></textarea>
                                    </div>
                                    <b>Type* </b>
                                    <select name="type" class="form-control" required>
                                        <option value="User">User</option>
                                        <option value="Category">Category</option>
                                        <option value="Product">Product</option>
                                        <option value="Blog">Blog</option>
                                        <option value="Contact">Contact</option>
                                    </select><br> 
                                    
                                    <b>Status* </b>
                                    <input type="radio" id="status_enable" name="status" value="1" checked>
                                    <label for="status_enable">Enable</label>
                                    <input type="radio" id="status_disable" name="status" value="0">
                                    <label for="status_disable">Disable</label><br>

                                </div>
                                <div class="modal-footer">
                                    <p style="color: red">${mess}</p>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-success" value="submit">Submit</button>
                                    
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                </li>
                

                <table class="table" style="margin-top: 20px; margin-bottom: 20px;">
                    <thead>
                        <tr style="font-size: 20px;">
                            <th scope="col">ID</th>
                            <th scope="col">Setting Name</th>
                            <th scope="col">Type</th>     
                            <th scope="col">Status</th> 
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody> <!-- Đặt các phần tử HTML trong một phần tử tbody -->
                        <c:forEach var="setting" items="${settings}">
                            <tr>
                                <td>${setting.getId()}</td>
                                <td>${setting.getName()}</td>
                                <td>${setting.getType()}</td>
                                <td>${setting.getStatus()==1?"Active":"Deactive"}</td> 
                                <td> 
                                    <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModalEditSetting${setting.getId()}">Detail</button>
                                    <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModalStatusActivate${setting.getId()}">Activate</button>
                                    <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModalStatusDeactivate${setting.getId()}">Deactivate</button>
                                </td>
                            </tr>
                        <div class="modal fade" id="myModalEditSetting${setting.getId()}" role="dialog">
                            <!-- Modal content -->
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Edit Setting:</h4>
                                    </div>
                                    <form action="EditSetting" method="post">
                                        <div class="modal-body">
                                            
                                            <b>ID: </b><input type="text" class="form-control" name="id" value="${setting.getId()}" readonly=""><br>
                                            <b>Name: </b><input type="text" class="form-control" value="${setting.getName()}"  name="name"><br>
                                            <b>Description: </b><textarea class="form-control"name="description">${setting.getDescription()}</textarea><br>
                                            <b>Type: </b><input type="text" class="form-control" value="${setting.getType()}" name="type" ><br>    
                                            <b>Status: </b>
                                            <c:choose>
                                                <c:when test="${setting.getStatus() eq 1}">
                                                    <input type="radio" id="status_enable" name="status" value="1" checked>
                                                    <label for="status_enable">Active</label>

                                                    <input type="radio" id="status_disable" name="status" value="0">
                                                    <label for="status_disable">Deactive</label><br>
                                                </c:when>
                                                <c:when test="${setting.getStatus() eq 0}">
                                                    <input type="radio" id="status_enable" name="status" value="1">
                                                    <label for="status_enable">Active</label>

                                                    <input type="radio" id="status_disable" name="status" value="0" checked>
                                                    <label for="status_disable">Deactive</label><br>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-success" value="submit">Submit</button>
                                        </div>
                                    </form>                                 
                                </div>
                            </div>
                        </div>
                        
                        <div class="modal fade" id="myModalStatusActivate${setting.getId()}" role="dialog">
                            <!-- Modal content -->
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Change Status:</h4>
                                    </div>
                                    <form action="UpdateStatusSetting" method="get">
                                        <div class="modal-body">
                                            <p>Do you want to activate this setting</p>
                                            <input type="hidden" name="setting_id" value="${setting.getId()}">
                                            <input type="hidden" name="status" value="${setting.getStatus()}">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-success" value="submit">Submit</button>
                                        </div>
                                    </form>                                 
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="myModalStatusDeactivate${setting.getId()}" role="dialog">
                            <!-- Modal content -->
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Change Status:</h4>
                                    </div>
                                    <form action="UpdateStatusSetting" method="post">
                                        <div class="modal-body">
                                            <p>Do you want to deactivate this setting</p>
                                            <input type="hidden" name="setting_id" value="${setting.getId()}">
                                            <input type="hidden" name="status" value="${setting.getStatus()}">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-success" value="submit">Submit</button>
                                        </div>
                                    </form>                                 
                                </div>
                            </div>
                        </div>
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