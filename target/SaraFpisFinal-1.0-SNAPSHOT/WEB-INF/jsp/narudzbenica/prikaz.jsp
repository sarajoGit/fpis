<%-- 
    Document   : prikaz
    Created on : Feb 12, 2020, 4:02:15 PM
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
        <title>Narudzbenice</title>
    </head>
    <script>
        
        function dodaj()
        {
            window.location = "/SaraFPIS/narudzbenica/dodaj";
        }
        
        function azuriraj(id)
        {
            window.location = "/SaraFPIS/narudzbenica/azuriraj/" + id;
        }
        
         function obrisi(id)
        {
            $.get("/SaraFPIS/narudzbenica/obrisi/" + id, (res) =>
            {
                if (res == true)
                    location.reload();
                else
                    alert("Doslo je do greske pri brisanju!");
            });
        }
        
        $(() =>
        {
            $("#pretraga").keyup(() =>
            {
                var kljuc = $("#pretraga").val().toUpperCase();
                var redovi = $("tbody tr");
                var kolicine = $("tbody td:nth-child(2)");

                for (var i = 0; i < redovi.length; i++)
                {
                    var red = redovi[i];
                    var broj = $(kolicine[i]).html().toUpperCase();


                    if (broj.indexOf(kljuc) > -1)
                        $(red).css("display", "table-row");
                    else
                        $(red).css("display", "none");
                }
            });
        });
        
    </script>
    
    <body>
        <%@include file = "../header.jsp" %>
        <div class="container-fluid mt-2">
            <button type="button" class="btn btn-outline-primary" onclick="dodaj()">Dodaj</button>
            <input id="pretraga" type="text" class="w-100 mt-2 mb-2 form-control" placeholder="Unesite ukupnu kolicinu narucenih proizvoda..."/>
            <table class="table table-responsive w-100">
                <thead>
                    <tr>
                        <th>Broj narudzbenice</th>
                        <th>Ukupno naruceno</th>
                        <th>Zaposleni</th>
                        <th>Rok isporuke</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${narudzbenice}" var="nar" >
                        <tr>
                            <td>${nar.brojNarudzbenice}</td>
                            <td>${nar.ukupnoNaruceno}</td>
                            <td>${nar.zaposleni.imePrezime}</td>
                            <td>${nar.rokIsporuke.brojDana}</td>
                            <td><button class="btn btn-outline-warning" onclick="azuriraj(${nar.brojNarudzbenice})">Azuriraj</button></td>
                            <td><button class="btn btn-outline-danger" onclick="obrisi(${nar.brojNarudzbenice})">Obrisi</button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
