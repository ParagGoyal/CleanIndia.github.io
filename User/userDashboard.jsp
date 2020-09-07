<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,daos.*,beans.*"%>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>User Dashboard</title>
        <jsp:include page="base.jsp"></jsp:include>

            <!-- Custom fonts for this template-->
            <link href="admin/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="admin/assets/css/sb-admin-2.min.css" rel="stylesheet">

        </head>

        <body id="page-top">


        <%
            users user = (users) (session.getAttribute("user"));

            UserDao udao = new UserDao();
            int totalRequests = udao.getTotalRequestCountByUser(user.getId());


        %>



        <div id="wrapper">
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div id="content-wrapper" class="d-flex flex-column">
                    <div id="content">
                    <jsp:include page="navbar.jsp"></jsp:include>
                        <div class="container-fluid">
                            <!-- Page Heading -->
                            <h1 class="h3 mb-4 text-gray-800">Welcome <%=user.getName()%></h1>
                        <table class="table">

                            <tr>
                                <td><h4 class="h3 mb-0 text-gray-800">Total Number Of Requests :  <%=totalRequests%></h4></td>
                                <td><a href="allRequest.jsp" class="btn btn-success">View</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <!-- /.container-fluid -->
                </div>
                <!-- End of Main Content -->
                <!-- Footer -->
                <jsp:include page="footer.jsp"></jsp:include>
                    <!-- End of Footer -->
                </div>
                <!-- End of Content Wrapper -->
            </div>
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
