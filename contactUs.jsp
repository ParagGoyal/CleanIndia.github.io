<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->

    <head>
        <title>cleanIndia</title>
        <meta charset="utf-8">
        <!--[if IE]>
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <![endif]-->
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

        <link rel="stylesheet" href="assets\css\bootstrap.min.css">
        <link rel="stylesheet" href="assets\css\animations.css">
        <link rel="stylesheet" href="assets\css\fonts.css">
        <link rel="stylesheet" href="assets\css\main.css" class="color-switcher-link">
        <script src="assets\js\vendor\modernizr-2.6.2.min.js"></script>

        <!--[if lt IE 9]>
                <script src="js/vendor/html5shiv.min.js"></script>
                <script src="js/vendor/respond.min.js"></script>
                <script src="js/vendor/jquery-1.12.4.min.js"></script>
        <![endif]-->

    </head>

    <body>
        <!--[if lt IE 9]>
                <div class="bg-danger text-center">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/" class="highlight">upgrade your browser</a> to improve your experience.</div>
        <![endif]-->

        <!-- search modal -->

        <!-- Unyson messages modal -->

        <!-- eof .modal -->

        <!-- wrappers for visual page editor and boxed version of template -->
        <div id="canvas">
            <div id="box_wrapper">

                <!-- template sections -->
                <jsp:include page="navbar.jsp"></jsp:include>




                <section class="page_breadcrumbs ds parallax section_padding_top_65 section_padding_bottom_65">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12 text-center">
                                <h2 class="highlight">Contacts</h2>
                                <ol class="breadcrumb darklinks">
                                    <li>
                                        <a href="contact.html">
                                            Home
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">Pages</a>
                                    </li>
                                    <li class="active">Contacts</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </section>

                <section class="ls section_padding_100 background_cover page_contact">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 text-center">
                                <div class="with_background transp_black_bg with_padding">

                                    <form class="contact-form row" method="post" action="./">

                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="name">Full Name
                                                    <span class="required">*</span>
                                                </label>
                                                <i class="fa fa-user highlight2" aria-hidden="true"></i>
                                                <input type="text" aria-required="true" size="30" value="" name="name" id="name" class="form-control" placeholder="Full Name">
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="phone">Phone Number
                                                    <span class="required">*</span>
                                                </label>
                                                <i class="fa fa-phone highlight2" aria-hidden="true"></i>
                                                <input type="text" aria-required="true" size="30" value="" name="phone" id="phone" class="form-control" placeholder="Phone Number">
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="email">Email address
                                                    <span class="required">*</span>
                                                </label>
                                                <i class="fa fa-envelope highlight2" aria-hidden="true"></i>
                                                <input type="email" aria-required="true" size="30" value="" name="email" id="email" class="form-control" placeholder="Email Address">
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="subject">Subject
                                                    <span class="required">*</span>
                                                </label>
                                                <i class="fa fa-flag highlight2" aria-hidden="true"></i>
                                                <input type="text" aria-required="true" size="30" value="" name="subject" id="subject" class="form-control" placeholder="Subject">
                                            </div>
                                        </div>
                                        <div class="col-sm-12">

                                            <div class="contact-form-message form-group">
                                                <label for="message">Message</label>
                                                <i class="fa fa-comment-o highlight2" aria-hidden="true"></i>
                                                <textarea aria-required="true" rows="3" cols="45" name="message" id="message" class="form-control" placeholder="Message"></textarea>
                                            </div>
                                        </div>

                                        <div class="col-sm-12 bottommargin_0">

                                            <div class="contact-form-submit topmargin_10">
                                                <button type="submit" id="contact_form_submit" name="contact_submit" class="theme_button color2 wide_button margin_0">Send message</button>
                                            </div>
                                        </div>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <jsp:include page="footer.jsp"></jsp:include>
                
                <section class="ls page_copyright section_padding_15">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12 text-center">
                                <p class="small-text small-spacing grey">&copy; Copyright 2020 All Rights Reserved</p>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
            <!-- eof #box_wrapper -->
        </div>
        <!-- eof #canvas -->

        <script src="js\compressed.js"></script>
        <script src="js\main.js"></script>
        <script src="js\switcher.js"></script>

        <!-- Google Map Script -->
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTwYSMRGuTsmfl2z_zZDStYqMlKtrybxo"></script>
    </body>

</html>