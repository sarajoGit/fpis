<%-- 
    Document   : azuriraj
    Created on : Feb 12, 2020, 11:19:57 PM
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
        <title>Dodavanje Narudzbenice</title>
         <script>
            function azurirajStavku(rbStavke, brojNarudzbenice)
            {
                window.location = "/SaraFPIS/narudzbenica/azurirajStavku/" + rbStavke + "&" + brojNarudzbenice;
            }
            
            
             function obrisiStavku(rbStavke, brojNarudzbenice)
            {
                $.get("/SaraFPIS/narudzbenica/obrisiStavku/" + rbStavke + "&" + brojNarudzbenice, (res) =>
                {
                    if (res == true)
                        location.reload();
                    else
                        alert("Ne mozete obrisati poslednju stavku!");
                });
            }
            
             function potvrdiTransakciju()
            {
                $.get("/SaraFPIS/narudzbenica/potvrdiTransakciju", (res) =>
                {
                    if (res == true)
                        window.location = "/SaraFPIS/narudzbenica/prikaz";
                    else
                        alert("Niste popunili sve podatke ispravno.");
                });
            }
            
        </script>
        
    </head>
    <body>
         <%@include file = "../header.jsp" %>
         <br>
         <c:if test="${narudzbenica.brojNarudzbenice == null}">
            <h3>Dodavanje narudzbenice</h3>
        </c:if>
        <c:if test="${narudzbenica.brojNarudzbenice != null}">
            <h3>Azuriranje narudzbenice</h3>
        </c:if>
        <br>
        
        <div class="container-fluid">
            <form:form id="prva" action="/SaraFPIS/narudzbenica/azuriraj" method="post" modelAttribute="narudzbenica">
                <form:input type="hidden" path="brojNarudzbenice" value="${narudzbenica.brojNarudzbenice}" />
                <div class="form-group">
                    <form:label path="UkupnoNaruceno">Ukupno naruceno</form:label>
                    <form:input type="text" class="form-control" path="UkupnoNaruceno" id="UkupnoNaruceno" />
                </div>
                <div class="form-group">
                    <form:label path="Zaposleni.JMBGZaposlenog">Zaposleni</form:label>
                    <form:select class="form-control" path="Zaposleni.JMBGZaposlenog" id="JMBGZaposlenog" >
                        <form:options items="${zaposleni}" itemLabel="imePrezime" itemValue="JMBGZaposlenog" />
                    </form:select>
                </div>
                <div class="form-group">
                    <form:label path="RokIsporuke.RokIsporukeID">Rok isporuke</form:label>
                    <form:select class="form-control" path="RokIsporuke.RokIsporukeID" id="RokIsporukeID" >
                        <form:options items="${rokovi}" itemLabel="brojDana" itemValue="RokIsporukeID" />
                    </form:select>
                </div>
                <c:if test="${narudzbenica.brojNarudzbenice == null}">
                    <button class="btn btn-outline-dark">Sacuvaj podatke</button>
                </c:if>
                <c:if test="${narudzbenica.brojNarudzbenice != null}">
                    <button class="btn btn-outline-dark">Azuriraj</button>
                </c:if>
            </form:form>
            <br>
            <h3>Unos stavke</h3>
             <form:form id="druga" action="/SaraFPIS/narudzbenica/dodajStavku" method="post" modelAttribute="stavka">
                <form:input type="hidden" path="stavkeNarudzbenicePK.brojNarudzbenice" value="${stavka.stavkeNarudzbenicePK.brojNarudzbenice}" />
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
                <button class="btn btn-outline-dark">Dodaj stavku</button>
            </form:form>
            <br>
            <h3>Stavke</h3>
            <table class="table table-responsive">
                <thead>
                <th>Redni br stavke</th>
                <th>Broj narudzbenice</th>
                <th>Opis stavke</th>
                <th>Proizvod</th>
                <th>Kolicina</th>
                <th></th>
                <th></th>
                </thead>
                <tbody>
                    <c:forEach items="${stavke}" var="s" >
                        <tr>
                            <td>${s.stavkeNarudzbenicePK.rbStavke}</td>
                            <td>${s.stavkeNarudzbenicePK.brojNarudzbenice}</td>
                            <td>${s.opisStavke}</td>
                            <td>${s.sifraProizvoda}</td>
                            <td>${s.kolicina}</td>
                        <c:if test="${s.stavkeNarudzbenicePK.rbStavke == 0}">
                            <td>
                                <form method="post" action="/SaraFPIS/narudzbenica/azurirajNovuStavkuStrana" >
                                    <input type="hidden" name="OpisStavke" value="${s.opisStavke}" />
                                    <input type="hidden" name="Kolicina" value="${s.kolicina}" />
                                    <input type="hidden" name="SifraProizvoda" value="${s.sifraProizvoda.sifraProizvoda}" />
                                    <button class="btn btn-outline-warning" >Azuriraj</button>
                                </form>
                            </td>
                        </c:if>
                        <c:if test="${s.stavkeNarudzbenicePK.rbStavke > 0}">
                            <td><button class="btn btn-outline-warning" onclick="azurirajStavku(${s.stavkeNarudzbenicePK.rbStavke}, ${s.stavkeNarudzbenicePK.brojNarudzbenice})">Azuriraj</button></td>
                        </c:if>
                        <c:if test="${s.stavkeNarudzbenicePK.rbStavke == 0}">
                                <td>
                                    <form method="post" action="/SaraFPIS/narudzbenica/obrisiNovuStavku" >
                                        <input type="hidden" name="OpisStavke" value="${s.opisStavke}" />
                                        <input type="hidden" name="Kolicina" value="${s.kolicina}" />
                                        <input type="hidden" name="SifraProizvoda" value="${s.sifraProizvoda.sifraProizvoda}" />
                                        <button class="btn btn-outline-danger" >Obrisi</button>
                                    </form>
                                </td>

                        </c:if>
                        <c:if test="${s.stavkeNarudzbenicePK.rbStavke > 0}">
                            <td><button class="btn btn-outline-danger" onclick="obrisiStavku(${s.stavkeNarudzbenicePK.rbStavke}, ${s.stavkeNarudzbenicePK.brojNarudzbenice})">Obrisi</button></td>
                        </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <button type="button" class="btn btn-outline-success" onclick="potvrdiTransakciju()">Potvrdi transakciju</button>
            <br>
            <br>
        </div>
    </body>
</html>
