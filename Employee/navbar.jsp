<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,daos.*,beans.*,java.util.ArrayList,javax.servlet.http.*"%>
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

    <!-- Sidebar Toggle (Topbar) -->
    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>


    <!-- Topbar Navbar -->
    <ul class="navbar-nav ml-auto">
        <%
            employee emp = (employee) session.getAttribute("emp");
            EmployeeDao edao = new EmployeeDao();
            ArrayList<reqDetails> newNotifivation = edao.getEmpNotification(emp.getEmpId());
            int totalNotification=edao.getCountNotify(emp.getEmpId());
      %>


        <!-- Nav Item - Alerts -->
        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                Notification <span class="badge badge-danger badge-counter"><%=totalNotification %></span>
            </a>
            <!-- Dropdown - Alerts --><% if(totalNotification !=0) { %>
            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                    Alerts Center
                </h6>

                <div class="mr-3">
                    <div class="icon-circle bg-primary">
                        <i class="fas fa-file-alt text-white"></i>
                    </div>
                </div>
                <div>
                  <% for (reqDetails req : newNotifivation) { %>
                    <a href="processingRequest.jsp">
                        <span class="font-weight-bold">You have new Request</span>
                    </a> 
                </div>
                <%}%>
                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
            </div> <% } %>

        </li>

        <!-- Nav Item - Messages -->


        <div class="topbar-divider d-none d-sm-block"></div>

        <!-- Nav Item - User Information -->
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=emp.getName()%></span>

                <img class="img-profile rounded-circle" src="../<%=emp.getPic()%>">
            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="loginEmployee.jsp" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Logout
                </a>
            </div>
        </li>

    </ul>

</nav>
