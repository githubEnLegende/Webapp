<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
        <a class="navbar-brand" href="question"> Questions </a>
        <a class="navbar-brand" href="chapitre"> <spring:message code="HEADER.chapitre"/> </a>
        <div class="pull-right">
            <form action="#" method="get" class="form-inline">
                <select class="form-control" style="margin-top: 8px" name="lang" onchange="this.form.submit()">
                    <option value="fr" ${requestScope.lang.equals("fr") ? "selected" : ""}><spring:message
                            code="HEADER.LANG.fr"/></option>
                    <option value="en" ${requestScope.lang.equals("en") ? "selected" : ""}><spring:message
                            code="HEADER.LANG.en"/></option>
                </select>
            </form>
        </div>
    </div>
</header>

<section id="main">
    <div class="container">
        <h1 id="homeTitle">
            <spring:message code="QUESTION.titre"/>
        </h1>
        <%--            <div id="actions" class="form-horizontal">--%>
        <%--                <div class="pull-left">--%>
        <%--                    <form id="searchForm" action="#" method="GET" class="form-inline">--%>

        <%--                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />--%>
        <%--                        <input type="submit" id="searchsubmit" value="Filter by name"--%>
        <%--                        class="btn btn-primary" />--%>
        <%--                    </form>--%>
        <%--                </div>--%>
        <%--                <div class="pull-right">--%>
        <%--                    <a class="btn btn-success" id="addStagiaire" href="addStagiaire">Ajout stagiaire</a>--%>
        <%--                    <a class="btn btn-default" id="editStagiaire" href="#" onclick="$.fn.toggleEditMode();">Modifier</a>--%>
        <%--                </div>--%>
        <%--            </div>--%>
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
                    <input type="checkbox" id="selectall"/>
                    <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                </th>
                <!-- Table header for Discontinued Date -->
                <th>
                    <%--                            <a href="dashboard?page=${requestScope.page}&size=${size}&order=formation_over&search=${search}">title</a>--%>
                    <a><spring:message code="QUESTION.ennonce"/></a>
                </th>
                <!-- Table header for Company -->
                <th>
                    <%--                            <a href="dashboard?page=${requestScope.page}&size=${size}&order=promotion_id&search=${search}">statement</a>--%>
                    <a><spring:message code="QUESTION.corps"/></a>
                </th>
                <th>
                    <%--                            <a href="dashboard?page=${requestScope.page}&size=${size}&order=promotion_id&search=${search}">chapter_id</a>--%>
                    <a><spring:message code="QUESTION.chapitre"/></a>
                </th>

            </tr>
            </thead>
            <!-- Browse attribute computers -->
            <tbody id="results">
            <c:forEach var="question" items="${requestScope.questions}">
                <tr>
                    <td class="editMode">
                        <input type="checkbox" name="cb" class="cb" value="${question.id}">
                    </td>
                    <td><a href="question/${question.id}" onclick="">${question.title}</a></td>
                    <td>${question.statement}</td>
                    <td>${question.chapitreId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<%--    <footer class="navbar-fixed-bottom">--%>
<%--        <div class="container text-center">--%>
<%--            <ul class="pagination">--%>
<%--                <c:set var="startPage" value="${Math.min(Math.max(1, totalPages-4),Math.max(1, page - 2))}"/>--%>
<%--                <c:if test="${totalPages>4}">--%>
<%--                    <c:set var="endPage" value="${Math.max(5,Math.min(totalPages+0, page + 2))}"/>--%>
<%--                </c:if>--%>
<%--                <c:if test="${totalPages<=4}">--%>
<%--                    <c:set var="endPage" value="${Math.max(1,Math.min(totalPages+0, page + 2))}"/>--%>
<%--                </c:if>--%>
<%--                <li>--%>
<%--                    <a href="dashboard?page=1&size=${size}&order=${requestScope.order}&search=${search}" aria-label="Previous">--%>
<%--                        <span aria-hidden="true">&laquo;</span>--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">--%>
<%--                    <li class="${page == pageNumber ? "active" : ""}">--%>
<%--                        <a href="dashboard?page=${pageNumber}&size=${size}&order=${requestScope.order}&search=${search}">--%>
<%--                            <c:out value="${pageNumber}" />--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </c:forEach>--%>
<%--                <li>--%>
<%--                    <a href="dashboard?page=${totalPages}&size=${size}&order=${requestScope.order}&search=${search}" aria-label="Next">--%>
<%--                        <span aria-hidden="true">&raquo;</span>--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--            </ul>--%>


<%--            <div class="btn-group btn-group-sm pull-right" role="group">--%>
<%--                <form action="dashboard?page=1&order=${requestScope.order}&search=${search}" method="GET">--%>
<%--                    <input type="submit" name="size" value="10" class="btn btn-default">--%>
<%--                    <input type="submit" name="size" value="50" class="btn btn-default">--%>
<%--                    <input type="submit" name="size" value="100" class="btn btn-default">--%>
<%--                </form>--%>

<%--            </div>--%>
<%--	</div>--%>
<%--    </footer>--%>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/dashboard.js"></script>

</body>
</html>
