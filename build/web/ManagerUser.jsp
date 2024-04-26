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
        </style>
    </head>
    <body>
        <!-- SIDEBAR -->
        <jsp:include page="headerAdmin.jsp"/>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <form action="ManagerUser" method="post">
                    <div class="form-input">
                        <input type="search" name="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                    </div>
                </form>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Manage User</h1> 
                    </div>
                </div>
                <!-- Modal -->

                <div style="margin-top: 3rem;" class="col-md-12">
                    <button class="button" onclick="window.location.href = 'AddUser';">Add User</button>
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
                                        <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModalStatusActivate${p.getId()}">Change status</button>
                                        <button type="button" class="btn btn-success btn-lg" onclick="window.location.href = 'EditUser?userId=${p.getId()}&UserDetail=false';"">Edit User</button></td>
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