<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,daos.UserDao,beans.users"%>


<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Register User</title>
        <jsp:include page="base.jsp"></jsp:include>
            <script type="text/javascript">
                function cmpPassword(x, y)
                {
                    if (x === y)
                        return true;
                    else {
                        alert('password not matched..!');
                        return false;
                    }
                }

                function checkEmailId(x, y) {

                    ajax = new XMLHttpRequest();
                    ajax.open("GET", "UserController?op=check_email&emailId=" + x, true);
                    ajax.send();

                    ajax.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                            y.innerHTML = this.responseText;
                        }
                    }
                }


            </script>


            <!-- Custom fonts for this template-->
            <link href="assets2/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="assets2/css/sb-admin-2.min.css" rel="stylesheet">

        </head>

        <body class="bg-gradient-primary">

        <jsp:useBean class="beans.users" id="user" scope="session" ></jsp:useBean>

        <%
            if (request.getParameter("submit") != null) {
        %> 
        <jsp:setProperty name="user" property="*"></jsp:setProperty>
        <%
                UserDao udao = new UserDao();
                if (udao.addUser(user)) {
                    System.out.println("successfully inserted");
                    response.sendRedirect("Login.jsp");
                } else {
                    System.out.println("failure occurs");
                }
            }
        %>


        <div class="container">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                        <div class="col-lg-7">
                            <div class="p-5">

                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                                </div>


                                <form class="user"  onsubmit="return cmpPassword(password.value, repassword.value);">

                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label for="name" style="color: black">Name </label>
                                            <input type="text" name="name" class="form-control form-control-user" id="name" required="" placeholder="Enter Your Name">
                                        </div>
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label for="mob" style="color: black">Mobile No</label>
                                            <input type="text" name="mobile" class="form-control form-control-user" id="mobile" required="" placeholder="Mobile Number">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" style="color: black">Email Id </label>
                                        <input type="email" class="form-control form-control-user" id="emailId" name="emailId" required="" placeholder="Email Address" onblur="checkEmailId(this.value, sp1);">
                                        <span id ="sp1"> </span></td>

                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label for="password" style="color: black">Password</label>
                                            <input type="password" name="password" id="password" class="form-control form-control-user" required="" placeholder="Password" pattern = "(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$">
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="repassword" style="color: black">Confirm Password</label>
                                            <input type="password" name="repassword" id="repassword" class="form-control form-control-user" required="" placeholder="Retype Password">
                                        </div>
                                    </div>
                                    <p style="font-size: x-small">The password must contains an upper case,lower case,digit an special symbol and minimum 8 character long.</p>
                                    <div class="form-submit">
                                        <input type="submit" value="SignUp" class="btn btn-primary btn-user btn-block" name="submit" id="submit">
                                    </div>
                                    <hr>

                                </form>







                                <hr>
                                
                                
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="forgot-password.html">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="Login.jsp">Already have an account? Login!</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="home.jsp">or go to HOME</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="assets2/vendor/jquery/jquery.min.js"></script>
        <script src="assets2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="assets2/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="assets2/js/sb-admin-2.min.js"></script>

    </body>

</html>
