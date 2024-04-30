
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
            <a class="navbar-brand" href="WEB-INF/dashboard"> Newro Factory </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Stagiaire</h1>
                    <form action="addStagiaire" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <c:if test="${requestScope.stagiaireValidator.valide == false}">
                                    <c:forEach var="error" items="${requestScope.stagiaireValidator.stagiaireValidator[(0).intValue()]}">
                                        <span style="color:red"> ${error} </span><br>
                                    </c:forEach>
                                </c:if>
                                <label for="lastName">Nom</label>
                                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Nom du stagiaire" value="" required>

                            </div>
                            <div class="form-group">
                                <c:if test="${requestScope.stagiaireValidator.valide == false}">
                                    <c:forEach var="error" items="${requestScope.stagiaireValidator.stagiaireValidator[(1).intValue()]}">
                                        <span style="color:red"> ${error} </span><br>
                                    </c:forEach>
                                </c:if>
                                <label for="firstName">Prénom</label>
                                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Prénom du stagiaire" value="" required>
                            </div>
                            <div class="form-group">
                                <c:if test="${requestScope.stagiaireValidator.valide == false}">
                                    <c:forEach var="error" items="${requestScope.stagiaireValidator.stagiaireValidator[(2).intValue()]}">
                                        <span style="color:red"> ${error} </span><br>
                                    </c:forEach>
                                </c:if>
                                <label for="arrival">Date d'arrivée</label>
                                <input type="date" class="form-control" name="arrival" id="arrival" placeholder="Date d'arrivée" value="" required>
                            </div>
                            <div class="form-group">
                                <label for="finFormation">Date de fin de formation</label>
                                <input type="date" class="form-control" name="finFormation" id="finFormation" placeholder="Date de fin de formation" value="">
                            </div>
                            <div class="form-group">
                                <c:if test="${requestScope.stagiaireValidator.valide == false}">
                                    <c:forEach var="error" items="${requestScope.stagiaireValidator.stagiaireValidator[(3).intValue()]}">
                                        <span style="color:red"> ${error} </span> <br>
                                    </c:forEach>
                                </c:if>
                                <label for="promotionId">Promotion</label>
                                <input type="text" class="form-control" name="promotionId" id="promotionId" placeholder="Entrez l'id de la promotion" value="" required>
<%--                                <select class="form-control" name="promotionId" id="promotionId" >--%>
<%--                                    <option value="0">--</option>--%>
<%--                                </select>--%>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>