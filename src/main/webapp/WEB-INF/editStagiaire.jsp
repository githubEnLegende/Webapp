<!DOCTYPE html>

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
            <a class="navbar-brand" href="dashboard"> Newro Factory </a>
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

                    <form action="editStagiaire" method="POST">
                        <input type="hidden" name="id" value="<%= request.getParameter("id") %>" id="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="lastName">Nom</label>
                                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Nom du stagiaire" value="">
                            </div>
                            <div class="form-group">
                                <label for="firstName">Prénom</label>
                                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Prénom du stagiaire" value="">
                            </div>
                            <div class="form-group">
                                <label for="arrival">Date d'arrivée</label>
                                <input type="date" class="form-control" name="arrival" id="arrival" placeholder="Date d'arrivée" value="">
                            </div>
                            <div class="form-group">
                                <label for="finFormation">Date de fin de formation</label>
                                <input type="date" class="form-control" name="finFormation" id="finFormation" placeholder="Date de fin de formation" value="">
                            </div>
                            <div class="form-group">
                                <label for="promotionId">Promotion</label>
                                <input type="text" class="form-control" name="promotionId" id="promotionId" placeholder="Entrez l'id de la promotion" value="">
                                <%--                                <select class="form-control" name="promotionId" id="promotionId" >--%>
                                <%--                                    <option value="0">--</option>--%>
                                <%--                                </select>--%>
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
</html>