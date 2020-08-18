<%-- 
    Document   : header
    Created on : Feb 11, 2020, 6:44:34 PM
    Author     : Sara Jovanovic
--%>
<style>
    .navbar-brand img {
        width: 100px;
    }
    .nav-item {
        font-weight: bold;
        text-transform: lowercase;
        color: #f520a3; 
    }
    .nav-link {
        color: #f520a3;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
  <div class="container">
    <a class="navbar-brand" href="/SaraFPIS">
          <img src="https://static.framar.bg/snimki/proizvoditeli/esensa.png"/>
        </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="/SaraFPIS">Home
                <span class="sr-only">(current)</span>
              </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/SaraFPIS/zahtev/prikaz">Zahtev za katalog</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/SaraFPIS/narudzbenica/prikaz">Narudzbenice</a>
        </li>
      </ul>
    </div>
  </div>
</nav>