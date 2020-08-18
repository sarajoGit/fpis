<%-- 
    Document   : dodaj
    Created on : Feb 11, 2020, 9:07:23 PM
    Author     : Sara Jovanovic
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "../bootstrap.jsp" %>
        <title>Dodavanje zahteva za katalog</title>
    </head>
    <body>
         <%@include file = "../header.jsp" %>
         
         <div class="container-fluid">
            <form:form action="/SaraFPIS/zahtev/dodaj" method="post" modelAttribute="zahtev">
                <div class="form-group">
                    <form:label path="BrojKataloga">Broj kataloga</form:label>
                    <form:input class="form-control" path="BrojKataloga" id="BrojKataloga" />
                </div>
                <div class="form-group">
                    <form:label path="ImeKataloga">Ime kataloga</form:label>
                    <form:input type="text" class="form-control" path="ImeKataloga" id="ImeKataloga" />
                </div>
                <div class="form-group">
                    <form:label path="JMBGZaposlenog.JMBGZaposlenog">Zaposleni</form:label>
                    <form:select class="form-control" path="JMBGZaposlenog.JMBGZaposlenog" id="JMBGZaposlenog" >
                        <form:options items="${zaposleni}" itemLabel="ImePrezime" itemValue="JMBGZaposlenog" />
                    </form:select>
                </div>
                <div class="form-group">
                    <form:label path="PIBDobavljaca.PIBDobavljaca">Dobavljac</form:label>
                    <form:select class="form-control" path="PIBDobavljaca.PIBDobavljaca" id="PIBDobavljaca" >
                        <form:options items="${dobavljaci}" itemLabel="NazivDobavljaca" itemValue="PIBDobavljaca" />
                    </form:select>
                </div>
                <button class="btn btn-outline-dark">Dodaj</button>
            </form:form>
        </div>
    </body>
</html>

<style> 
    .container-fluid {
        padding-top: 10px;
        outline-color: #eb10a6;
        outline-width: medium;
    }
</style>
