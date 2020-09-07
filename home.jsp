<!DOCTYPE html>
<html class="no-js">

    <head>
        <title>CleanIndia</title>
        <meta charset="utf-8">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/animations.css">
        <link rel="stylesheet" href="assets/css/fonts.css">
        <link rel="stylesheet" href="assets/css/main.css" class="color-switcher-link">
        <script src="assets/js/modernizr-2.6.2.min.js"></script>


    </head>

    <body>
        <!--[if lt IE 9]>
                <div class="bg-danger text-center">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/" class="highlight">upgrade your browser</a> to improve your experience.</div>
        <![endif]-->

        <div class="preloader">
            <div class="preloader_image"></div>
        </div>

        <!-- search modal -->
     

       <!-- Unyson messages modal -->
      
        <!-- eof .modal -->

        <!-- wrappers for visual page editor and boxed version of template -->
        <div id="canvas">
            <div id="box_wrapper">

                <!-- template sections -->
                 
                <jsp:include page="navbar.jsp"></jsp:include>
                <jsp:include page="slider.jsp"></jsp:include>
                

                    <!-- eof flexslider -->

                <jsp:include page="about.jsp"></jsp:include>
                


                 <jsp:include page="testimonials.jsp"></jsp:include>
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

        <script src="assets/js/compressed.js"></script>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/switcher.js"></script>

        <!-- Google Map Script -->
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTwYSMRGuTsmfl2z_zZDStYqMlKtrybxo"></script>
    </body>

</html>