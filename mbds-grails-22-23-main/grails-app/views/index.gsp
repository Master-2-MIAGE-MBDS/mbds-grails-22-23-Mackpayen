<!doctype html>
<html>
<head>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <title>Welcome to Lecoincoin</title>
    <asset:stylesheet src="index.css"/>
    <asset:stylesheet src="carousel.css"/>
</head>
<body class="home">
<div class="container-fluid display-table">
    <div class="row display-table-row">
        <div class="col-md-2 col-sm-1 hidden-xs display-table-cell v-align box" id="navigation">
            <div class="logo">
                <a hef="home.html"><img src="https://st2.depositphotos.com/1967477/8596/v/950/depositphotos_85967834-stock-illustration-cartoon-mallard-duck-waving-isolated.jpg" alt="merkery_logo" class="hidden-xs hidden-sm">
                    <img src="http://jskrishna.com/work/merkury/images/circle-logo.png" alt="merkery_logo" class="visible-xs visible-sm circle-logo">
                </a>
            </div>
            <div class="navi">
                <ul>
                    <li><g:link controller="annonce" action="index">Consulter les annonces</g:link></li>
                    <li><g:link controller="illustration" action="index">Consulter les illustrations</g:link></li>
                    <li><g:link controller="user" action="index">Consulter les utilisateurs</g:link></li>
                </ul>
            </div>
        </div>
        <div class="col-md-10 col-sm-11 display-table-cell v-align">
            <!--<button type="button" class="slide-toggle">Slide Toggle</button> -->
            <div class="row">
                <header>
                    <div class="col-md-7">
                        <nav class="navbar-default pull-left">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="offcanvas" data-target="#side-menu" aria-expanded="false">
                                    <span class="sr-only"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                        </nav>
                    </div>
                    <div class="col-md-5">
                        <div class="header-rightside">
                            <ul class="list-inline header-top pull-right">
                                <li><a href="#"><i class="fa fa-envelope" aria-hidden="true"></i></a></li>
                                <li>
                                    <a href="#" class="icon-info">
                                        <i class="fa fa-bell" aria-hidden="true"></i>
                                        <span class="label label-primary"></span>
                                    </a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="http://jskrishna.com/work/merkury/images/user-pic.jpg" alt="user">
                                        <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <div class="navbar-content">
                                                <g:link controller="logout" action="index" class="add-project">Logout</g:link>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </header>
            </div>
            <div class="user-dashboard">
                <div class="row">

                    <!-- Carousel -->
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>
                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="https://img-3.journaldesfemmes.fr/id3jfn4IcJMIccqqI0LdMQaRO8Q=/1500x/smart/676b698f910f4c0f98a8cc28aa78b6b4/ccmcms-jdf/14684085.jpg" alt="First slide">
                                <!-- Static Header -->
                                <div class="header-text hidden-xs">
                                    <div class="col-md-12 text-center">
                                        <h2>
                                            <span>Bienvenue sur <strong>Lecoincoin</strong></span>
                                        </h2>
                                        <br>
                                        <h3>
                                            <span>Afin de bénéficier de nos services, une connexion vous est indispensable.</span>
                                        </h3>
                                        <br>
                                        <div class="">
                                            <a class="btn btn-theme btn-sm btn-min-block" href="#">Login</a></div>
                                    </div>
                                </div><!-- /header-text -->
                            </div>
                            <div class="item">
                                <img src="https://www.cdiscount.com/other/image-pc-sms-393x261-05-p19-56978_210414020402.png?bf6b86b5-aa24-49f3-9b82-7409cb75825d" alt="Second slide">
                                <!-- Static Header -->
                                <div class="header-text hidden-xs">
                                    <div class="col-md-12 text-center">
                                        <h2>
                                            <span>Votre satisfaction, notre priorité</span>
                                        </h2>
                                        <br>
                                        <h3>
                                            <span>Pour vous satisfaire, nous vous mettons à disposition une large gamme de produits.</span>
                                        </h3>
                                        <br>
                                        <div class="">
                                            <a class="btn btn-theme btn-sm btn-min-block" href="#">Login</a></div>
                                    </div>
                                </div><!-- /header-text -->
                            </div>
                            <div class="item">
                                <img src="https://images.frandroid.com/wp-content/uploads/2020/11/vpavic_4291_20201113_0366-0-1200x800.jpg" alt="Third slide">
                                <!-- Static Header -->
                                <div class="header-text hidden-xs">
                                    <div class="col-md-12 text-center">
                                        <h2>
                                            <span>Commerce de proximité</span>
                                        </h2>
                                        <br>
                                        <h3>
                                            <span>Des produits neufs et d'occasions à votre disposition.</span>
                                        </h3>
                                        <br>
                                        <div class="">
                                            <a class="btn btn-theme btn-sm btn-min-block" href="#">Login</a></div>
                                    </div>
                                </div><!-- /header-text -->
                            </div>
                        </div>
                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </a>
                    </div><!-- /carousel -->

                </div>
            </div>
        </div>


    </div>



</div>



<!-- Modal -->
<div id="add_project" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <g:form resource="${this.commande}" method="POST" class="modal-content">
            <div class="modal-header login-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4 class="modal-title">Passer une commande</h4>
            </div>
            <div class="modal-body">
                <input type="text" placeholder="Désignation" name="title">
                <input type="text" placeholder="Quantité" name="quantity">
                <input type="text" placeholder="Email" name="mail">
                <input type="text" placeholder="Contact" name="contact">
                <textarea placeholder="Description" name="description"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="cancel" data-dismiss="modal">Close</button>
                <g:submitButton class="add-project" data-dismiss="modal" name="create"  value="${message(code: 'default.button.create.label', default: 'Create')}" />
            </div>
        </g:form>

    </div>
</div>
<asset:stylesheet src="index.js"/>
<asset:javascript src="application.js"/>
</body>
</html>
