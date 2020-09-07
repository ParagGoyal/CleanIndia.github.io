<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,daos.*,beans.*,java.util.ArrayList,javax.servlet.http.*"%>
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

    <!-- Sidebar Toggle (Topbar) -->
    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>


    <!-- Topbar Navbar -->
    <ul class="navbar-nav ml-auto">
       


        <!-- Nav Item - Alerts -->
       <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <span class="badge badge-danger badge-counter">1+</span>
              </a>
              <!-- Dropdown - Alerts -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  Alerts Center
                </h6>
                   <div class="mr-3">
                    <div class="icon-circle bg-primary">
                      <i class="fas fa-file-alt text-white"></i>
                    </div>
                  </div>
               <%--   <div>
                      <%
                      AdminDao ado = new AdminDao(); 
                        ArrayList<reqDetails> completeRequest = ado.getTotalCompleteRequest();
                      for(reqDetails req : completeRequest){%>
                      <a href="completedRequest.jsp">
                   <span class="font-weight-bold">req id <%=req.getSreqId()%> is <%=req.getReqStatus()%></span>
                      </a> 
                  </div>
                
                 <%}%>
 --%>                     
                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
              </div>
            </li>

        <!-- Nav Item - Messages -->


        <div class="topbar-divider d-none d-sm-block"></div>

        <!-- Nav Item - User Information -->
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                <span class="mr-2 d-none d-lg-inline text-gray-600 small">cleanIndia.pvt.ltd</span>

                <img class="img-profile rounded-circle" src="#">
            </a>
            <!-- Dropdown - User Information -->
                    </li>

    </ul>

</nav>
