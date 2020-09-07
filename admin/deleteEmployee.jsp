<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,daos.*,beans.*,java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Remove Employee</title>

        <jsp:include page="base.jsp"></jsp:include>
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
                            <div class="row">
                            <%
                                String op = request.getParameter("op");
                                System.out.println("op for deleteEmployee:" + op);
                                EmployeeDao edao = new EmployeeDao();
                                employee emp = null;
                                if (op != null && op.equalsIgnoreCase("deleteEmployee")) {
                                       int empId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
                                       emp = edao.getById(empId);
                            %>
                            <table class="table">
                                <th><h5 style="color: red">  Are You Really Wants To Remove Employee <%=emp.getName()%> ?</h5></th>
                                <tr>
                                    <td><a href="viewEmployee.jsp" class="btn btn-success">Back</a>
                                    <a href="../EmployeeController?id=<%=emp.getEmpId()%>&op=deleteEmployee" class="btn btn-danger">Remove</a></td>
                                </tr>  
                            </table>
                            
                           
                    <% } %>

                  </div>


                        <!-- /.container-fluid -->

                    </div>
                    <!-- End of Main Content -->

                    <!-- Footer -->
                    <!-- End of Footer -->

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>



            <!-- Bootstrap core JavaScript-->
      


    </body>
</html>