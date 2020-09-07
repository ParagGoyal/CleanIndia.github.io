<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,daos.*,beans.*,java.util.ArrayList"%>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Employee Dashboard</title>
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
                                    <td><b> </b></td><td><b>Req Id</b></td><td><b>Location</b></td><td><b>Location Detail</b></td><td><b>Req Date</b></td>
                                    <td><b>Type</b></td><td><b>Dispatch Location</b></td><td><b>View Images</b></td><td><b>Change Status</b></td>
                                </tr>
                            <%  EmployeeDao edao = new EmployeeDao();
                                employee emp=(employee)session.getAttribute("emp");
                                ArrayList<servicereq> reqList = new ArrayList();
                                reqList = edao.getAllPendingRequestById(emp.getEmpId());
                                String dispatchLocation=edao.getDispatchLocation(emp.getEmpId());
                                for (servicereq serv : reqList) {%>
                            <tr>
                                <td><b> </b></td>
                                <td><%=serv.getId()%></td>
                                <td><%=serv.getLoc_id()%></td>
                                <td><%=serv.getLocDetail()%></td>
                                <td><%=serv.getDate()%></td> 
                                <td><%=serv.getType()%></td>
                                <td><%=dispatchLocation%></td>
                                <td><a href="viewGarbageImage.jsp" class="btn btn-primary">View Images</a></td>
                                <td><a href="../EmployeeController?op=complete&sreqId=<%=serv.getId()%>&empId=<%=emp.getEmpId()%>" class="btn btn-primary">Completed</a></td>
                              <%}
                            %>
                            </tr>
                            

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