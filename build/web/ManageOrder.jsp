

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="CSSsimple/sellerDashbord.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <title>Manage Order</title>
        <style>
            .viewButton {
                margin-top: 10px;
                background: none;
                border: none;
                padding: 0;
                cursor: pointer;
            }

            /* Định dạng biểu tượng mắt */
            .eye-icon {
                width: 20px;
                height: 20px;
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
            .filter{
                display: flex;
                margin: 10px;

            }
            .filter_date{
                margin-left: 15px;
                margin-top: -15px;
            }
            .wrapper {
                position: relative;
                width: 20 px;
                min-height: 20 px ;
                border-radius: 20px;
                background: hsl(20, 100%, 64%);

                display: flex;
                justify-content: center;
                align-items: center;

                .arrow {
                    width: 40px;
                    height: 40px;
                    border-radius: 20px;
                    background: hsl(40, 100%, 30%);
                    background-color: #ff413c;
                    transition: 0.2s ease-in-out;

                    .line {
                        position: absolute;
                        top: 50%;
                        left: 50%;
                        width: 20px;
                        height: 4px;
                        border-radius: 2px;
                        background: hsl(30, 100%, 80%);
                        transform-origin: center;
                        transform: translate(-50%, -50%) rotate(45deg);

                        &:nth-child(2) {
                            transform: translate(-50%, -50%) rotate(-45deg);
                        }
                    }

                    &:hover {
                        transform: rotate(25deg);
                    }
                }

            }
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
        <jsp:include page="headerSeller.jsp" />
        <!-- SIDEBAR -->
        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu'></i>
                <div class="left">
                    <h1>Manage Order</h1>
                </div>

            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div>
                    <nav>  
                        <form action="ManageOrder" method="post">
                            <div class="form-input">
                                <input type="search" id="myInput" onkeyup="myFunction()" name="search" placeholder="Search by ID or Phone number">
                                <input type="text" hidden  name="index" value="${index}">
                                <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>

                            </div>
                            <p style="color: red">${mess}</p>
                        </form>
                    </nav>
                </div>
                <div class="filter">
                    <div>
                        <label for="Filter">Order Person :</label>
                        <select id="selectBox" name="Filter" onchange="doFilter(value);"> 
                            <option value="">All</option>
                            <c:forEach var="creator" items="${creator}">
                                <option value="${creator.getName()}">${creator.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>

                        <label for="Filter">Category :</label>
                        <select id="selectCategory"  name="Category" onchange="doFilterCategory(value);"> 

                            <option value="">All</option>
                            <c:forEach var="categoryBlog" items="${categoryBlog}">
                                <option value="${categoryBlog.getName()}">${categoryBlog.getName()}</option>
                            </c:forEach>

                        </select>

                    </div>
                    <div class="filter_date">
                        <form action="ManageOrder" method="post" onsubmit="return checkDate();">
                            <label>Start Date</label>
                            <input required type="date" name="firstDate">
                            <label>End Date</label>
                            <input style="margin: 14px" required type="date" name="secondDate">


                            <input style="background: var(--blue);
                                   color: white;
                                   border: solid var(--blue);
                                   font-size: 17px;
                                   border-radius: 15px;" type="submit" value="Search">
                        </form>
                    </div>
                </div>
                <p style="color: red">${messEdit}</p>        
                <div style="margin-top: 3rem;" class="col-md-12">
                    <table class="table" id="myTable">
                        <thead>
                            <tr style="font-size: 17px;">
                                <th scope="col">Order ID</th>
                                <th scope="col">Order Name</th>
                                <th scope="col">Order Date</th>
                                <th scope="col">Note</th>
                                <th scope="col">Address</th>
                                <th scope="col">Phone</th>
                                <th scope="col" colspan="3" style="text-align: center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="o" items="${olist}">

                                <tr>

                                    <th scope="row">
                                        <a style="color: white" class="btn btn-primary" data-toggle="modal" data-target="#myDialog"  onclick="getOrderDetails(this)" " data-orderid="${o.getId()}" >${o.getId()}</a>
                                    </th>
                                    <td >${o.getOrderName()} </td>
                                    <td>${o.formatDate()}</td>
                                    <td><textarea class="note" readonly>${o.getNotes()}</textarea></td>
                                    <td><textarea class="address" readonly>${o.getAddress()}</textarea></td>
                                    <td>${o.getPhone()}</td>
                                    <td >
                                        <a href="ChangeOrderStatus?orderId=${o.getId()}&ost=${o.getStatus()}&index=${index}" class="btn- btn-danger  btn-lg"  style="pointer-events: ${o.getStatus()==3?'none':''};display: block; background-color: ${o.getStatus()==1?'#f89f3c':o.getStatus()==2?'green':o.getStatus()==4?'blue':'red'};" >${o.getStatus()==1?"Pending":o.getStatus()==2?"Approve":o.getStatus()==4?"Paid":"Cancel"}</a>
                                    </td>

                                    <td> <button type="button" ${o.getStatus()==3?'hidden':o.getStatus()==4?'hidden':''} class="btn btn-success btn-lg" onclick="window.location.href = 'EditOrder?orderId=${o.getId()}&edit=true&index=${index}';"">Edit Order</button></td>

                                    <td>
                                        <button type="button"  ${o.getStatus()==3?'hidden':o.getStatus()==4?'hidden':''} onclick="window.location.href = 'ChangeOrderStatus?index=${index}&orderId=${o.getId()}&ost=3'"style="border-radius: 100%;">
                                            <div class="wrapper">
                                                <div class="arrow">
                                                    <div class="line"></div>
                                                    <div class="line"></div>
                                                </div>
                                            </div>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="pagination">
                    <a href="ManageOrder?index=${backPage}">&laquo;</a>
                    <c:forEach begin="1" end="${ePage}" var="i">
                        <a href="ManageOrder?index=${i}">${i}</a>
                    </c:forEach>
                    <a href="ManageOrder?index=${nextPage}">&raquo;</a>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->

        <div class="modal fade" id="myDialog" tabindex="-1" role="dialog" aria-labelledby="myDialogLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="myDialogLabel">Chi tiết đơn hàng</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Thông tin đơn hàng -->
                        <div class="bill-section">
                            <div class="bill-info" id="order-info">
                                <!-- Dữ liệu đơn hàng sẽ được thêm vào đây -->
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/adminDashbord.js"></script>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js">

        </script>
        <script type="text/javascript">
            function myFunction() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("myInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[4];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
            function doFilter(txtValue) {
                var input, filter, table, tr, td, i;
                input = document.getElementById("selectBox");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[0];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }

            }
            function doFilterCategory(txtValue) {
                var input, filter, table, tr, td, i;
                input = document.getElementById("selectCategory");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[4];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }

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
        <script>
            function getOrderDetails(element) {
                var orderId = element.getAttribute("data-orderid");
                $.ajax({
                    url: 'GetOrderDetail?orderId=' + orderId,
                    type: 'GET',
                    success: function (response) {
                        // Cập nhật thông tin đơn hàng và chi tiết sản phẩm
                        $('#myDialogLabel').text('Order details ' + orderId);
                        $('#order-info').html(response);
                        $('#myDialog').modal('show');
                    },
                    error: function () {
                        console.error('Failed to get order details');
                    }
                });
            }
        </script>
    </body>
</html>
