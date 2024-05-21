<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Newro Factory</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <a class="navbar-brand" href="chapitre"> Chapitres </a>
    </div>
</header>
<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <div class="label label-default pull-right">
                    id: <%= request.getParameter("id") %>
                </div>
                <h1>Modification stagiaire</h1>

                <form action="editStagiaire" method="POST" onsubmit="return validateForm()">
                    <input type="hidden" name="id" value="<%= request.getParameter("id") %>" id="id"/>
                    <!-- TODO: Change this value with the computer id -->
                    <fieldset>
                        <c:set var="validator" value="${requestScope.stagiaireValidator}"/>
                        <div class="form-group">
                            <c:if test="${not empty validator && not empty validator[(0).intValue()]}">
                                <span style="color:red"> ${validator[(0).intValue()]} </span><br>
                            </c:if>
                            <label for="lastName">Nom</label>
                            <input type="text" class="form-control" name="lastName" id="lastName"
                                   placeholder="Nom du stagiaire" value="${requestScope.stagiaire.lastName}" required>

                        </div>
                        <div class="form-group">
                            <c:if test="${not empty validator && not empty validator[(1).intValue()]}">
                                <span style="color:red"> ${validator[(1).intValue()]} </span><br>
                            </c:if>
                            <label for="firstName">Prénom</label>
                            <input type="text" class="form-control" name="firstName" id="firstName"
                                   placeholder="Prénom du stagiaire" value="${requestScope.stagiaire.firstName}"
                                   required>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty validator && not empty validator[(2).intValue()]}">
                                <span style="color:red"> ${validator[(2).intValue()]} </span><br>
                            </c:if>
                            <label for="arrival">Date d'arrivée</label>
                            <input type="date" class="form-control" name="arrival" id="arrival"
                                   placeholder="Date d'arrivée" value="${requestScope.stagiaire.arrival}" required>
                        </div>
                        <div class="form-group">
                            <label for="finFormation">Date de fin de formation</label>
                            <input type="date" class="form-control" name="finFormation" id="finFormation"
                                   placeholder="Date de fin de formation"
                                   value="${requestScope.stagiaire.formationOver}">
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty validator && not empty validator[(3).intValue()]}">
                                <span style="color:red"> ${validator[(3).intValue()]} </span> <br>
                            </c:if>
                            <label for="promotion">Promotion</label>
                            <select class="form-control" name="promotion" id="promotion">
                                <option value="${[requestScope.stagiaire.promotion.id, requestScope.stagiaire.promotion.name]}">${requestScope.stagiaire.promotion.name} </option>
                                <c:forEach items="${requestScope.listPromo}" var="promotion">
                                    <option value="${promotion.id}">${promotion.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Edit" class="btn btn-primary">
                        or
                        <a href="dashboard" class="btn btn-default">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>

<script>
    function validateForm() {
        // Validate first name
        var firstName = document.getElementById("firstName").value;
        if (!/^[a-zA-ZÀ-ÿ\s'-]+$/.test(firstName)) {
            alert("Please enter a valid first name (only letters and spaces allowed).");
            return false;
        }

        // Validate last name
        var lastName = document.getElementById("lastName").value;
        if (!/^[a-zA-ZÀ-ÿ\s'-]+$/.test(lastName)) {
            alert("Please enter a valid last name (only letters and spaces allowed).");
            return false;
        }

        // Validate formation over date if present
        var formationOver = document.getElementById("finFormation").value;
        var arrival = document.getElementById("arrival").value;
        if (formationOver && formationOver < arrival) {
            alert("Formation Over Date must be after Arrival Date.");
            return false;
        }

        return true;
    }
</script>

</html>