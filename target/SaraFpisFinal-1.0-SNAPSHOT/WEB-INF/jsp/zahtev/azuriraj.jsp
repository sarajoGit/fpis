<%-- 
    Document   : azuriraj
    Created on : Feb 11, 2020, 9:12:58 PM
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
        <title>Azuriranje zahteva za katalog</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
        
         <div class="container-fluid">
           <form:form action="/SaraFPIS/zahtev/azuriraj" method="post" modelAttribute="zahtev">
                <form:input type="hidden" path="BrojKataloga" id="BrojKataloga" />
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
                <button class="btn btn-outline-dark">Azuriraj</button>
            </form:form>
         </div>
    </body>
</html>
