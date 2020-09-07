<%@page contentType="text/html" pageEncoding="UTF-8" import="beans.employee,beans.city,daos.*,java.util.ArrayList,java.sql.*"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <jsp:include page="base.jsp"></jsp:include>
        
        <script>
                function readURL(input) {
                    // alert('hello');
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            preview.src = e.target.result;
                        };

                        reader.readAsDataURL(input.files[0]);
                    }
                }
            </script>
        </head>

        <body id="page-top">

            <!-- Page Wrapper -->
            <div id="wrapper">

                <!-- Sidebar -->
            <jsp:include page="sidebar.jsp"></jsp:include>
                <!-- End of Sidebar -->

                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                        <!-- Topbar -->
                    <jsp:include page="navbar.jsp"></jsp:include>
                        <!-- End of Topbar -->

                        <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                                <h1 class="h3 mb-0 text-gray-800">Edit the Employee Details Here </h1>
                            </div>

                            <!-- Content Row -->
                            <div class="row">

                            <jsp:useBean id="emp" class="beans.employee" scope="session"></jsp:useBean>
                             <%
                                        emp = (employee) session.getAttribute("emp");
                                        EmployeeDao edao = new EmployeeDao();
                                        emp = edao.getById(emp.getEmpId());
                                        session.setAttribute("emp", emp);
                                    %>
                                <div class="container">
                                    <div class="col col-md-3" style="position: fixed;right: 0px;">
                                    <% if (request.getParameter("submit") != null) {%>
                                    <jsp:setProperty name="emp" property="*"></jsp:setProperty>
                                        <form action="../EmployeeController?op=update" method="post" enctype="multipart/form-data" class="form-control">
                                            <input type="file" name="pic"   onchange="readURL(this);" class="btn btn-primary"/><br/><br>
                                            <img src="../${emp.pic}" class="img img-thumbnail" width="50%" height="50%" id="preview"/> <br/>
                                    
                                            <input type="submit" value="Update" class="btn btn-primary"/>
                                        </form>
                                    <%
                                        }%>
                                </div>
                                <div class="row">
                                    <div class="col col-md-5">
                                        <form class="form">
                                            <table class="table">

                                                <tr>
                                                    <td>Enter Employee Name </td>
                                                    <td><input type="text" id="name" name="name" required="required" value="${emp.name}" class="form-control" /></td>
                                                </tr> 


                                                <tr>
                                                    <td>Enter Employee's Contact </td>
                                                    <td><input type="text" name="mobile" value="${emp.mobile}" maxlength="13" id="mobile" class="form-control"  />

                                                    </td>
                                                </tr> 

                                                <tr>
                                                    <td>Enter Employee's Email </td>
                                                    <td><input type="email" name="emailId"id="emailId" value="${emp.emailId}" class="form-control"  />

                                                    </td>
                                                </tr> 
                                            </table>
                                            <input type="submit" value="Click to check Profile Photo" name="submit" id ="submit" class="btn btn-primary"/>
                                        </form>   
                                    </div>


                                </div>

                            </div>
                        </div>

                        <a class="scroll-to-top rounded" href="#page-top">
                            <i class="fas fa-angle-up"></i>
                        </a>

                        <!-- Logout Modal-->
                        <jsp:include page="LogoutModal.jsp"></jsp:include>

                        <jsp:include page="footer.jsp"></jsp:include>

    </body>

</html>
