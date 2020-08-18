<%-- 
    Document   : prikaz
    Created on : Feb 11, 2020, 8:56:33 PM
    Author     : Sara Jovanovic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "../bootstrap.jsp" %>
        <title>Zahtev za katalog</title>
    </head>
    <script>
        function dodaj()
        {
            window.location = "/SaraFPIS/zahtev/dodaj";
        }
        function azuriraj(id)
        {
            window.location = "/SaraFPIS/zahtev/azuriraj/" + id;
        }
        
        function obrisi(id)
        {
            $.get("/SaraFPIS/zahtev/obrisi/" + id, (res) =>
            {
                if (res == true)
                    location.reload();
                else
                    alert("Zahtev nije obrisan!");
            });
        }
        
        $(() =>
        {
            $("#pretraga").keyup(() =>
            {
                var kljuc = $("#pretraga").val().toUpperCase();
                var redovi = $("tbody tr");
                var nazivi = $("tbody td:nth-child(2)");

                for (var i = 0; i < redovi.length; i++)
                {
                    var red = redovi[i];
                    var naziv = $(nazivi[i]).html().toUpperCase();


                    if (naziv.indexOf(kljuc) > -1)
                        $(red).css("display", "table-row");
                    else
                        $(red).css("display", "none");
                }
            });
        });
      
        
    </script>
    <body>
         <%@include file = "../header.jsp" %>
        
        <div class="container-fluid mt-2" id="prvi">
           <button type="button" class="btn btn-outline-primary" onclick="dodaj()">Dodaj</button>
            <input id="pretraga" type="text" class="w-100 mt-2 mb-2 form-control" placeholder="Unesite naziv kataloga..." style="max-width:750px"/>
            <table class="table table-responsive w-100">
                <thead>
                    <tr>
                        <th>Broj</th>
                        <th>Naziv</th>
                        <th>Dobavljac</th>
                        <th>Potpisnik</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${zahtevi}" var="zahtev" >
                        <tr>
                            <td>${zahtev.brojKataloga}</td>
                            <td>${zahtev.imeKataloga}</td>
                            <td>${zahtev.PIBDobavljaca}</td>
                            <td>${zahtev.JMBGZaposlenog}</td>
                            <td><button class="btn btn-outline-warning" onclick="azuriraj(${zahtev.brojKataloga})">Azuriraj</button></td>
                            <td><button class="btn btn-outline-danger" onclick="obrisi(${zahtev.brojKataloga})">Obrisi</button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
