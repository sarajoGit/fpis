<%-- 
    Document   : azuriraj
    Created on : Feb 13, 2020, 3:59:36 PM
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
        <title>Azuriranje stavke narudzbenice</title>
    </head>
    <body>
        <%@include file = "../header.jsp" %>
         <c:if test="${stavka.stavkeNarudzbenicePK.brojNarudzbenice == 0}">
             <h3>Azuriranje stavke (koja nije u bazi)</h3>
             <form:form id="druga" action="/SaraFPIS/narudzbenica/azurirajNovuStavku" method="post" modelAttribute="stavka">
                <div class="form-group">
                    <form:label path="OpisStavke">Opis stavke</form:label>
                    <form:input type="text" class="form-control" path="OpisStavke" id="OpisStavke" />
                </div>
                <div class="form-group">
                    <form:label path="Kolicina">Kolicina</form:label>
                    <form:input type="text" class="form-control" path="Kolicina" id="Kolicina" />
                </div>
                <div class="form-group">
                    <form:label path="SifraProizvoda.SifraProizvoda">Proizvod</form:label>
                    <form:select class="form-control" path="SifraProizvoda.SifraProizvoda" id="SifraProizvoda" >
                        <form:options items="${proizvodi}" itemLabel="NazivProizvoda" itemValue="SifraProizvoda" />
                    </form:select>
                </div>
                <button class="btn btn-outline-dark">Azuriraj</button>
             </form:form>
            <br>
         </c:if>
        <br>
         <c:if test="${stavka.stavkeNarudzbenicePK.brojNarudzbenice != 0}">
             <form:form id="druga" action="/SaraFPIS/narudzbenica/azurirajStavku" method="post" modelAttribute="stavka">
             <form:input type="hidden" path="stavkeNarudzbenicePK.brojNarudzbenice" value="${stavka.stavkeNarudzbenicePK.brojNarudzbenice}" />
             <form:input type="hidden" path="stavkeNarudzbenicePK.rbStavke" value="${stavka.stavkeNarudzbenicePK.rbStavke}" />
                 <div class="form-group">
                    <form:label path="OpisStavke">Opis stavke</form:label>
                    <form:input type="text" class="form-control" path="OpisStavke" id="OpisStavke" />
                </div>
                <div class="form-group">
                    <form:label path="Kolicina">Kolicina</form:label>
                    <form:input type="text" class="form-control" path="Kolicina" id="Kolicina" />
                </div>
                <div class="form-group">
                    <form:label path="SifraProizvoda.SifraProizvoda">Proizvod</form:label>
                    <form:select class="form-control" path="SifraProizvoda.SifraProizvoda" id="SifraProizvoda" >
                        <form:options items="${proizvodi}" itemLabel="nazivProizvoda" itemValue="SifraProizvoda" />
                    </form:select>
                </div>
                <button class="btn btn-outline-dark">Azuriraj</button>
             </form:form>
            <br>
         </c:if>
    </body>
</html>
