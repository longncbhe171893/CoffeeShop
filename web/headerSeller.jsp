
<section id="sidebar">
    <a href="Home" class="brand">
        <i class='bx bxs-coffee'></i>
        <span class="text">Coffee Blend</span>
    </a>
    <ul class="side-menu top">
        <li class="active">
            <a href="SellerDashboard?&user=${sessionScope['account'].getId()}">
                <i class='bx bxs-dashboard' ></i>
                <span class="text">Seller Manage</span>
            </a>
        </li>
        <li class="active">
            <a href="ManageBlog?index=1&user=${sessionScope['account'].getId()}">
               <i class='bx bxs-dashboard' ></i>
                <span class="text">Manage Blog</span>
            </a>
        </li>
        
        <li class="active">
            <a href="ManageOrder?index=1&user=${sessionScope['account'].getId()}">
                <i class='bx bxs-dashboard' ></i>
                <span class="text">Manage Order</span>
            </a>
        </li>
        
       
                <li class="active">
            <a href="ManageFeedback">
                <i class='bx bxs-dashboard' ></i>
                <span class="text">Manage Feedback</span>
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