
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
       <link rel="stylesheet" href="CSSsimple/adminDashbord.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/nice-select.css" rel="stylesheet">
        <script src="ckeditor/ckeditor.js"></script> 
        <script src="ckfinder/ckfinder.js"></script>
        <title>Admin Dashboard</title> 
        <style>   
        .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border-radius: 5px;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
                border-radius: 5px;
            }

      .search-form {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form-input {
    display: flex;
    align-items: center;
}

.search-input {
    width: 200px;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.search-btn {
    padding: 8px 12px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-left: 8px;
}

.select-buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
}

.select-buttons select {
    margin-right: 10px;
}

.select-buttons button {
    padding: 8px 16px;
}
            </style>
    </head>

    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerDashbord.jsp"/>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content"> 
            <!-- MAIN -->
            <main>
                <div class="head-title">
    <div class="left">
        <h1>Manage User</h1>
    </div>
</div>

<form action="ManagerUser" method="post" class="search-form">
    <input type="hidden" name="index" value="1">
    <div class="form-input">
        <input type="search" name="search" placeholder="Search..." class="search-input">
    </div>
    <div class="select-buttons">
            <b>Status:</b>
            <br>
        <select class="form-control" name="userstatus">
            <option value="0" ${user.getUserSatus() == 0 ? "selected" : ""}>All</option>
            <option value="1" ${user.getUserSatus() == 1 ? "selected" : ""}>Active</option>
            <option value="2" ${user.getUserStatus() == 2 ? "selected" : ""}>Inactive</option>
        </select>
         <b>Sex:</b>
         <br>
        <select class="form-control" name="sex">
            <option value="0" ${user.getSex() == 0 ? "selected" : ""}>All</option>
            <option value="1" ${user.getSex() == 1 ? "selected" : ""}>Nam</option>
            <option value="2" ${user.getSex() == 2 ? "selected" : ""}>Nữ</option>
        </select>
        <button type="submit" class="btn btn-success" value="submit">Submit</button>
    </div>  
</form>
 <!-- Modal -->
                                    
                <div style="margin-top: 3rem;" class="col-md-12">
                    <button class="button" onclick="window.location.href = 'AddUser';">Add User</button>
                    <table class="table" id="myTable">
                        <thead >
                            <tr style="font-size: 20px;">
                                <th scope="col" >ID</th>
                                <th scope="col" >Name</th>
                                <th scope="col">Email</th>
                                 <th scope="col" >Sex</th>  
                                <th scope="col" >Role</th>
                                <th scope="col" >Status</th>
                                <th scope="col" >Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${userlist}">
                                <tr>
                                    <th scope="row">${p.getId()}</th>
                                    <td>${p.getName()}</td>
                                    <td>${p.getEmail()}</td> 
                                    <td>
                           <c:choose>
                                 <c:when test="${p.getSex() == 1}">
                                           Nam
                                 </c:when>
                                 <c:when test="${p.getSex() == 2}">
                                            Nữ
                                 </c:when>
                            </c:choose>
                            </td>
                             <td>
                           <c:choose>
                                 <c:when test="${p.getSetting_id()==2}">
                                           Seller
                                 </c:when>
                                 <c:when test="${p.getSetting_id()==3}">
                                            Customer
                                 </c:when>
                            </c:choose>
                            </td>
                                    <td><a onclick="return confirm('Do you want to change your account status?')" href="UpdateStatusUser?uid=${p.getId()}&sid=${p.getUserStatus()}">
                                            ${p.getUserStatus()==1?"Active":"Inactive"}</a></td>    
                                   <td> <button type="button" class="btn btn-success btn-lg" onclick="window.location.href = 'EditUser?userId=${p.getId()}&UserDetail=false';"">Edit User</button></td>
                                </tr>   
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
 <div class="pagination">
                    <a href="ManagerUser?index=${backPage}">&laquo;</a>
                    <c:forEach begin="1" end="${ePage}" var="i">
                        <a href="ManagerUser?index=${i}">${i}</a>
                    </c:forEach>
                    <a href="ManagerUser?index=${nextPage}">&raquo;</a>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
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
