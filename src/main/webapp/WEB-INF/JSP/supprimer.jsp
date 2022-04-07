<!DOCTYPE html>
<html lang="fr">
<head>
 <jsp:include page="head.jsp" />
 <title>Document</title>
 <jsp:include page="imports.jsp" />

</head>
<body>

<nav>
 <jsp:include page="navbar.jsp" />
</nav>

  <!-- Page content-->

  <div class="container">

    <div class="text-center mt-5">

        <H1>
          PAGE SUPPRESSION
        </H1>
        DEBUG :
        DeleteStatus : <c:out value="${deleteStatus}" /> <br>
        Erreur détecté : <c:out value="${erreurDetectee}" /> <br>
        membreToDelete.identifiant : <c:out value="${membreToDelete.identifiant}" /> <br>
        idToDelete : <c:out value="${idToDelete}" /> <br>
        <p>
          <c:out value="${messageAccueil}" />
        </p>


        <c:if test="${erreurDetectee == 'true'}">
          <div class="alert alert-warning" role="alert">
            Saisie invalide :
            <c:forEach items="${erreurs}" var="erreur" >
              <c:out value="${erreur}" />
              <br>
            </c:forEach>
          </div>
        </c:if>


        <c:choose>

          <c:when test="${deleteStatus == 'deleteConfirmed'}" >
            <c:set var="avancement" value="100"/>

            <div class="display-4" >
              SMILE.☻
            </div>

            <div class="display-5" >
              YOU'VE JUST
            </div>

            <div class="display-5 text-primary" style="--bs-text-opacity: .15">
              BEEN ERASED
            </div>
            <img alt="Le film l'EFFACEUR" src="assets/image/erasingOk.jpg" >
            <br>

          </c:when>

          <c:when test="${deleteStatus == 'waitingOk'}" >

            <c:set var="avancement" value="75"/>

            <form action="?action=supprimer" method="post">

              Confirmer la suppression de ${membreToDelete.nom} ${membreToDelete.prenom}
              ( identifiant ${membreToDelete.identifiant} ) ?
              <br>

              <input type="hidden" name="identifiant" value="${membreToDelete.identifiant}">
              <input type="submit" class="btn btn-danger" value="Oui">

            </form>

            <br>

            <a class="btn btn-secondary" href="?action=supprimer">
              Non c'était une erreur
              </a>

            <br>

          </c:when>

          <c:otherwise>
            <c:set var="avancement" value="25"/>

              <form method="post" action="?action=supprimer">
                <select name="idFromSelect" class="col-8 form-select" aria-label="Default select example">
                  <option selected>
                  <strong> Les membres sont :</strong>
                  </option>

                  <c:forEach items="${membres}" var="membre" >
                    <option value="${membre.identifiant}" >
                      ${empty membre.nom ? " (Le nom est vide)" : membre.nom }
                      ${empty membre.prenom ? " (Le prénom est vide)" : membre.prenom }
                      ${empty membre.identifiant ? " (L'id est vide)" : membre.identifiant }
                    </option>
                  </c:forEach>
                  <input class="btn btn-outline-danger" type="submit" value="Supprimer" />

                </select>
              </form>

          </c:otherwise>

        </c:choose>
      </div>

      <br>

        <div class="progress">
          <div class="progress-bar" role="progressbar"
          aria-valuemin="0" aria-valuemax="100"
          aria-valuenow="${avancement}"
          style="width: ${avancement}%">
          </div>
        </div>

    </div>

  </div>
  <jsp:include page="footer.jsp" />

</body>
</html>
