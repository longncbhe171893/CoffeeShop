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
.add-user-button {
    background-color: #28a745; /* Green color */
    color: #fff; /* White text color */
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.add-user-button:hover {
    background-color: #218838; /* Darker green on hover */
}
.btn-container {
    display: flex;
    justify-content: space-between;
    align-items: center; /* Add this line to vertically center the buttons */
}

.btn-container button {
    flex-grow: 0; /* Prevent buttons from growing */
    width: auto; /* Auto width based on content */
    padding: 5px 10px; /* Adjust padding as needed */
    font-size: 14px; /* Adjust font size as needed */
}

/* Adjust button size for small screens */
@media screen and (max-width: 768px) {
    .btn-container button {
        padding: 5px 5px; /* Adjust padding for small screens */
        font-size: 12px; /* Adjust font size for small screens */
    }
}
        </style>
    </head>
    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerAdmin.jsp"/>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
       <section id="content"> 
    <main>
        <div class="head-title">
            <div class="left">
                <h1>Manage User</h1>
            </div>
            <div class="right">
                <button class="add-user-button" onclick="window.location.href = 'AddUser';">Add User</button>
            </div>
        </div>

<form action="ManagerUser" method="post" class="search-form">
    <input type="hidden" name="index" value="1">
    <div class="form-input">
        <input type="search" name="search" placeholder="Search..." class="search-input">
    </div>
    <div class="select-buttons">
        <b style="margin-right: 10px;">Role:</b>
        <select class="form-control" name="role">
             <option value="0" ${user.getSetting_id() == 0 ? "selected" : ""}>All</option>
            <c:forEach var="r" items="${rlist}">
                <option value="${r.getId()}" ${user.getSetting_id()==r.getId()?"selected":""}>${r.getName()}</option>
            </c:forEach>
        </select>
            <b style="margin-right: 10px;">Status:</b>
        <select class="form-control" name="userstatus">
            <option value="0" ${user.getUserSatus() == 0 ? "selected" : ""}>All</option>
            <option value="1" ${user.getUserSatus() == 1 ? "selected" : ""}>Active</option>
            <option value="2" ${user.getUserStatus() == 2 ? "selected" : ""}>Inactive</option>
        </select>
        <button type="submit" class="btn btn-success" value="submit">Submit</button>
    </div>  
</form>

                <div style="margin-top: 3rem;" class="col-md-12">
                    <table class="table" id="myTable">
                        <thead >
                            <tr style="font-size: 20px;">
                                <th scope="col" >ID</th>
                                <th scope="col" onclick="sortTable(0)">Name</th>
                                <th scope="col" onclick="sortTable(1)">Email</th>
                                <th scope="col" onclick="sortTable(2)">Sex</th>  
                                <th scope="col" onclick="sortTable(3)">Role</th>
                                <th scope="col" onclick="sortTable(4)">Status</th>
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
                                             <c:when test="${p.getSetting_id()==1}">
                                                Admin
                                            </c:when>
                                            <c:when test="${p.getSetting_id()==2}">
                                                Seller
                                            </c:when>
                                            <c:when test="${p.getSetting_id()==3}">
                                                Customer
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.getUserStatus() == 1}">
                                                Active
                                            </c:when>
                                            <c:when test="${p.getUserStatus() == 2}">
                                                Inactive
                                            </c:when>
                                        </c:choose>      
                                   <td>
    <div class="btn-container">
        <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModalStatusActivate${p.getId()}">Change status</button>
        <button type="button" class="btn btn-success btn-lg" onclick="window.location.href = 'EditUser?userId=${p.getId()}&UserDetail=false';"">Edit User</button>
    </div>
</td>
                                </tr>
                            <div class="modal fade" id="myModalStatusActivate${p.getId()}" role="dialog">
                                <!-- Modal content -->
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Change Status:</h4>
                                        </div>
                                        <form action="UpdateStatusUser" method="get">
                                            <div class="modal-body">
                                                <p>Do you want to change status this user</p>
                                                <input type="hidden" name="uid" value="${p.getId()}">
                                                <input type="hidden" name="sid" value="${p.getUserStatus()}">
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
        <script>
            function sortTable(n) {
                var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                table = document.getElementById("myTable");
                switching = true;
                //Set the sorting direction to ascending:
                dir = "asc";
                /*Make a loop that will continue until
                 no switching has been done:*/
                while (switching) {
                    //start by saying: no switching is done:
                    switching = false;
                    rows = table.rows;
                    /*Loop through all table rows (except the
                     first, which contains table headers):*/
                    for (i = 1; i < (rows.length - 1); i++) {
                        //start by saying there should be no switching:
                        shouldSwitch = false;
                        /*Get the two elements you want to compare,
                         one from current row and one from the next:*/
                        x = rows[i].getElementsByTagName("TD")[n];
                        y = rows[i + 1].getElementsByTagName("TD")[n];
                        /*check if the two rows should switch place,
                         based on the direction, asc or desc:*/
                        if (dir == "asc") {
                            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                //if so, mark as a switch and break the loop:
                                shouldSwitch = true;
                                break;
                            }
                        } else if (dir == "desc") {
                            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                //if so, mark as a switch and break the loop:
                                shouldSwitch = true;
                                break;
                            }
                        }
                    }
                    if (shouldSwitch) {
                        /*If a switch has been marked, make the switch
                         and mark that a switch has been done:*/
                        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                        switching = true;
                        //Each time a switch is done, increase this count by 1:
                        switchcount++;
                    } else {
                        /*If no switching has been done AND the direction is "asc",
                         set the direction to "desc" and run the while loop again.*/
                        if (switchcount == 0 && dir == "asc") {
                            dir = "desc";
                            switching = true;
                        }
                    }
                }
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