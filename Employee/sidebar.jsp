<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,daos.*,beans.*"%>
<!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="employeeDashboard.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
           <% 
                    employee emp = (employee) (session.getAttribute("emp"));
                    %>
        <div class="sidebar-brand-text mx-3"><%=emp.getName()%></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item">
          <a class="nav-link" href="employeeDashboard.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Manage Requests
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      
      

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
          <a class="nav-link" href="processingRequest.jsp">
            <i class="fas fa-fw fa-wrench"></i>
          <span>Pending Requests</span></a>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="completedRequest.jsp">
            <i class="fas fa-fw fa-wrench"></i>
          <span>Completed Requests</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">
      <li class="nav-item">
          <a class="nav-link" href="editProfile.jsp">
            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
          <span>Profile</span></a>
      </li>
 

      <!-- Heading -->
      
    </ul>
    <!-- End of Sidebar -->
