
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <link href="CSSsimple/SearchBox.css" rel="stylesheet">
        <title>Coffee</title>     
        <script src="ckeditor/ckeditor.js"></script> 
        <script src="ckfinder/ckfinder.js"></script>
        <title>Seller Dashboard</title>   
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
            .filter{
                display: flex;
                margin: 10px;
                margin-left: 250px;

            }
            .filter_date{
                margin-left: 15px;
                margin-top: -15px;
            }
            .button {
                background-color: #029ef3;
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
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
            #toastBox{
                position: absolute;
                bottom: 30px;
                right: 30px;
                display: flex;
                align-items: flex-start;
                flex-direction: column;
                overflow: hidden;
                padding: 20px;


            }
        </style>
    </head>

    <body>

        <!-- SIDEBAR -->
        <jsp:include page="headerSeller.jsp"/>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <b><input type="text" hidden class="form-control" required  value="${sessionScope['account'].getId()}" name="user"></b>
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <div class="head-title">
                    <div class="left">
                        <h1>Manage Blogs</h1>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div>
                    <div id="search">
                        <svg viewBox="0 0 420 60" xmlns="http://www.w3.org/2000/svg">
                        <rect class="bar"/>

                        <g class="magnifier">
                        <circle class="glass"/>
                        <line class="handle" x1="32" y1="32" x2="44" y2="44"></line>
                        </g>

                        <g class="sparks">
                        <circle class="spark"/>
                        <circle class="spark"/>
                        <circle class="spark"/>
                        </g>

                        <g class="burst pattern-one">
                        <circle class="particle circle"/>
                        <path class="particle triangle"/>
                        <circle class="particle circle"/>
                        <path class="particle plus"/>
                        <rect class="particle rect"/>
                        <path class="particle triangle"/>
                        </g>
                        <g class="burst pattern-two">
                        <path class="particle plus"/>
                        <circle class="particle circle"/>
                        <path class="particle triangle"/>
                        <rect class="particle rect"/>
                        <circle class="particle circle"/>
                        <path class="particle plus"/>
                        </g>
                        <g class="burst pattern-three">
                        <circle class="particle circle"/>
                        <rect class="particle rect"/>
                        <path class="particle plus"/>
                        <path class="particle triangle"/>
                        <rect class="particle rect"/>
                        <path class="particle plus"/>
                        </g>
                        </svg>
                        <input type="search" id="myInput" onkeyup="myFunction()" name=search placeholder="Search blog by tittle ... " aria-label="Search for inspiration"/>
                    </div> <br>
                </div>
                <div class="filter">
                    <div ${hidden}>
                        <label for="Filter">Creator :</label>
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
                        <form action="ManageBlog" method="post" onsubmit="return checkDate();">
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

                <div style="margin-top: 3rem;" class="col-md-12">
                    <button class="button" onclick="window.location.href = 'AddBlog?index=${index}&user=${sessionScope['account'].getId()}';">Add Blog</button>



                    <table class="table" id="myTable" style="margin-top: 20px; margin-bottom: 20px;">

                        <thead >
                            <tr style="font-size: 20px;">
                                <th scope="col">ID</th>
                                <th scope="col">Title</th>
                                <th scope="col">Image</th>
                                <th scope="col">Short Description</th>
                                <th scope="col">Creator</th>
                                <th scope="col">Category</th>
                                <th scope="col">Content</th>
                                <th scope="col" ${sessionScope['account'].getSetting_id()==1?'hidden':''}>Status</th>


                                <th scope="col" colspan="3" style="text-align: center">Action</th>
                            </tr>

                        </thead>

                        <c:forEach var="bl" items="${bl}">
                            <tr>
                                <th scope="row">${bl.getBlog_id()}</th>
                                <td>${bl.getBlog_title()}</td>
                                <td><img style="width:150px;height:150px;"src="${bl.getBlog_image()}"></td>
                                <td>${bl.getDescription()}</td>   
                                <td>${bl.getUser().getName()}</td>   
                                <td>${bl.getSetting().getName()}</td>   

                                <td>
                                    <details>
                                        <summary style="color:blue">Show</summary>
                                        <p>${bl.getContent()}</p>
                                    </details>
                                </td>
                                <td><b ${sessionScope['account'].getSetting_id()==1?'hidden':''} style="display: block;color: ${bl.getBlog_status()==2?'red':'green'}; " >${bl.getBlog_status()==1?"Public":"Private"}</b></td>
                                <td>
                                    <a href="ChangeStatusBlog?bid=${bl.getBlog_id()}&index=${index}&user=${sessionScope['account'].getId()}" ${hidden} id="${bl.getBlog_id()}" class="btn- btn-danger  btn-lg" style="display: block; background-color: ${bl.getBlog_status()==2?'red':'green'}; " >${bl.getBlog_status()==1?"Public":"Private"}</a>
                                </td>

                                <td> <button type="button"  class="btn btn-success btn-lg" onclick="window.location.href = 'EditBlog?blogId=${bl.getBlog_id()}&user=${sessionScope['account'].getId()}&BlogDetail=false&index=${index}&imgSrc=${bl.getBlog_image()}';"">Edit Blog</button></td>

                                <td>
                                    <button class="viewButton" onclick="window.location.href = 'EditBlog?blogId=${bl.getBlog_id()}&BlogDetail=true&index=${index}';"">
                                        <span class="eye-icon"></span>
                                    </button>
                                </td>
                            </tr>

                        </c:forEach>
                    </table>
                </div>
                <div class="pagination">

                    <a href="ManageBlog?index=${backPage}&user=${sessionScope['account'].getId()}">&laquo;</a>
                    <c:forEach begin="1" end="${ePage}" var="i">                      
                        <a href="ManageBlog?index=${i}&user=${sessionScope['account'].getId()}">${i}</a>
                    </c:forEach>
                    <a href="ManageBlog?index=${nextPage}&user=${sessionScope['account'].getId()}">&raquo;</a>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script>
            const elems = document.getElementsByClassName('confirmation');
            const confirmIt = function (e) {
                if (!confirm('Are you sure?')) {
                    e.preventDefault(); // Prevent link from loading
                }
            };

            for (let i = 0; i < elems.length; i++) {
                elems[i].addEventListener('click', confirmIt, false);
            }
        </script>
        <script type="text/javascript">
            function myFunction() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("myInput");
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
            function doFilter(txtValue) {
                var input, filter, table, tr, td, i;
                input = document.getElementById("selectBox");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[3];
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
