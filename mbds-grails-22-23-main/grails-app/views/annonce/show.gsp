<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <asset:stylesheet src="button.css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <asset:stylesheet src="index.css"/>
    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="main.css"/>
</head>
<body class="home">
<div class="container-fluid display-table">
    <div class="row display-table-row">
        <div class="col-md-2 col-sm-1 hidden-xs display-table-cell v-align box" id="navigation">
            <div class="logo">
                <a hef="home.html"><img src="https://st2.depositphotos.com/1967477/8596/v/950/depositphotos_85967834-stock-illustration-cartoon-mallard-duck-waving-isolated.jpg" alt="merkery_logo" class="hidden-xs hidden-sm">
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
                                    <span class="sr-only">Toggle navigation</span>
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
                                        <span class="label label-primary">3</span>
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

            <table class="table">

                <tr>
                    <td>
                        <ul class="btn btn-lg btn-default"><li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li></ul>
                        <ul class="btn btn-lg btn-warning"><li><g:link class="edit" action="edit">Créer une nouvelle annonce</g:link></li></ul>
                    </td>
                </tr>
            </table>

            <a href="#show-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            <div id="show-annonce" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <f:display bean="annonce" />
                <g:form resource="${this.annonce}" method="DELETE">
                    <fieldset class="buttons">
                        <g:link class="edit" action="edit" resource="${this.annonce}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>

        </div>
        <asset:stylesheet src="index.js"/>
        <asset:javascript src="application.js"/>

</body>
</html>
