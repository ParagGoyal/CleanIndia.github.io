<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,daos.*,beans.*,java.util.ArrayList,javax.servlet.http.HttpSession"%>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Admin Dashboard</title>
        <jsp:include page="base.jsp"></jsp:include>

            <!-- Custom fonts for this template-->
            <link href="admin/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="admin/assets/css/sb-admin-2.min.css" rel="stylesheet">

        </head>

        <body id="page-top">
            <div id="wrapper">
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div id="content-wrapper" class="d-flex flex-column">
                    <div id="content">
                    <jsp:include page="navbar.jsp"></jsp:include>
                        <div class="row table-responsive">

                            <table class="table">
                                <tr>
                                    <td><b> </b></td><td><b>Employee Id</b></td><td><b>Employee Name</b></td><td><b>Photo</b></td><td><b>workStatus</b></td>
                                    <td><b>Action</b></td>
                                </tr>
                            <%  EmployeeDao edao = new EmployeeDao();
                                ArrayList<employee> empList = new ArrayList();
                                String locid=request.getParameter("loc_id");
                                int locId = Integer.parseInt(locid);
                               session.setAttribute("loc_id",locid);
                                int sreqId = Integer.parseInt(request.getParameter("sreqId"));
                                empList = edao.getFreeEmployee(locId);
                                for (employee emp : empList) {%>
                            <tr>
                                <td><b> </b></td>
                                <td><%=emp.getEmpId()%></td>
                                <td><%=emp.getName()%></td> 
                                <td><img src="../<%=emp.getPic()%>" width="50px" height="50px"/></td>
                                <td><%=emp.getWorkStatus()%></td>
                                <td><a href="../EmployeeController?sreqId=<%=sreqId%>&empId=<%=emp.getEmpId()%>&op=updateStatus" class="btn btn-primary">Assign?</a></td>  
                            </tr>
                            <%}
                            %>

                        </table>

                    </div>

                    <!-- /.container-fluid -->
                </div>

            </div>
            <!-- End of Content Wrapper -->
        </div>



        <!-- Content Row -->

        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <jsp:include page="LogoutModal.jsp"></jsp:include>
        <!-- Bootstrap core JavaScript-->
        <script src="admin/assets/vendor/jquery/jquery.min.js"></script>
        <script src="admin/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="admin/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="admin/assets/js/sb-admin-2.min.js"></script>

    </body>

</html>
