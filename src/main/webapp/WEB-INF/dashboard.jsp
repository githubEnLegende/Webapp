<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Newro Factory</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Newro Factory </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                Stagiaires Trouvés
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addStagiaire" href="addStagiaire">Ajout stagiaire</a>
                    <a class="btn btn-default" id="editStagiaire" href="#" onclick="$.fn.toggleEditMode();">Modifier</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                         <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Stagiaire
                        </th>
                        <th>
                            Date d'arrivée
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Date de fin de formation
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Promotion
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                <c:forEach var="stagiaire" items="${requestScope.stagiaires}">
                    <tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${stagiaire.id}">
                        </td>
                        <td><a href="editStagiaire?id=${stagiaire.id}" onclick="">${stagiaire.firstName} ${stagiaire.lastName}</a></td>
                        <td>${stagiaire.arrival}</td>
                        <td>${stagiaire.formationOver}</td>
                        <td>${stagiaire.promotion.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href="dashboard?page=1&size=${size}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach var="pageNumber" begin="${Math.max(1, page - 2)}" end="${Math.max(5, page + 2)}">
                    <li class="${page == pageNumber ? "active" : ""}">
                        <a href="dashboard?page=${pageNumber}&size=${size}">
                            <c:out value="${pageNumber}" />
                        </a>
                    </li>
                </c:forEach>
                <li>
                    <a href="dashboard?page=${totalPages}&size=${size}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>


            <div class="btn-group btn-group-sm pull-right" role="group">
            <button type="button" class="btn btn-default" >
                <a href="dashboard?page=1&size=10">10</a>
            </button>
            <button type="button" class="btn btn-default">
                <a href="dashboard?page=1&size=50">50</a>
            </button>
            <button type="button" class="btn btn-default">
                <a href="dashboard?page=1&size=100">100</a>
            </button>
        </div>
	</div>
    </footer>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/dashboard.js"></script>

</body>
</html>
