<%-- 
    Document   : dodaj
    Created on : Feb 12, 2020, 11:52:05 PM
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
        <title>Nova narudzbenica</title>
        <script>

            function potvrdiTransakciju()
            {
                $.get("/SaraFPIS/narudzbenica/potvrdiTransakciju", (res) =>
                {
                    if (res == true)
                        window.location = "/SaraFPIS/narudzbenica/prikaz";
                    else
                        alert("Niste dodali narudzbenicu");
                });
            }
        </script>
        
    </head>
    
    <body>
         <%@include file = "../header.jsp" %>
         
         <div class="container-fluid">
            <form:form action="/SaraFPIS/narudzbenica/dodaj" method="post" modelAttribute="narudzbenicaSaStavkom">
                <div class="form-group">
                    <form:label path="narudzbenica.UkupnoNaruceno">Ukupno naruceno</form:label>
                    <form:input type="text" class="form-control" path="narudzbenica.UkupnoNaruceno" id="UkupnoNaruceno" readonly />
                    
                </div>
                
                <div class="form-group">
                    <form:label path="narudzbenica.Zaposleni.JMBGZaposlenog">Zaposleni</form:label>
                    <form:select class="form-control" path="narudzbenica.Zaposleni.JMBGZaposlenog" id="JMBGZaposlenog" >
                        <form:options items="${zaposleni}" itemLabel="imePrezime" itemValue="JMBGZaposlenog" />
                    </form:select>
                </div>
                <div class="form-group">
                    <form:label path="narudzbenica.RokIsporuke.RokIsporukeID">Rok isporuke</form:label>
                    <form:select class="form-control" path="narudzbenica.RokIsporuke.RokIsporukeID" id="RokIsporukeID" >
                        <form:options items="${rokovi}" itemLabel="brojDana" itemValue="RokIsporukeID" />
                    </form:select>
                </div>
                <h3>Unos stavke</h3>
                <div class="form-group">
                    <form:label path="stavka.OpisStavke">Opis stavke</form:label>
                    <form:input type="text" class="form-control" path="stavka.OpisStavke" id="OpisStavke" />
                </div>
                <div class="form-group">
                    <form:label path="stavka.Kolicina">Kolicina</form:label>
                    <form:input type="text" class="form-control" path="stavka.Kolicina" id="Kolicina" />
                </div>
                <div class="form-group">
                    <form:label path="stavka.SifraProizvoda.SifraProizvoda">Proizvod</form:label>
                    <form:select class="form-control" path="stavka.SifraProizvoda.SifraProizvoda" id="SifraProizvoda" >
                        <form:options items="${proizvodi}" itemLabel="nazivProizvoda" itemValue="SifraProizvoda" />
                    </form:select>
                </div>
                <button class="btn btn-outline-dark">Dodaj</button>
                
            </form:form>
            <button type="button" class="btn btn-outline-success" onclick="potvrdiTransakciju()">Potvrdi transakciju</button>
        </div>
    </body>
</html>
