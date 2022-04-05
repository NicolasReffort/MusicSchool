<!DOCTYPE html>
<html lang="fr">
<head>
 <jsp:include page="head.jsp" />
 <title>Document</title>
 <jsp:include page="imports.jsp" />

</head>
<body>

 <jsp:include page="navbar.jsp" />

 <H1>
  PAGE SUPPRESSION
 </H1>

  <div class="row">

    <div class="col-5">
      <form method="post" action="?action=supprimer">

      <c:if test="${erreurDetectee == 'true'}">
        <div class="alert alert-warning" role="alert">
          Saisie invalide :
          <c:forEach items="${erreurs}" var="erreur" >
            <c:out value="${erreur}" />
            <br>
          </c:forEach>
        </div>
     </c:if>

        <select name="idFromSelect" class="col-8 form-select" aria-label="Default select example">
          <option selected>
          <strong> Les membres sont :</strong>
          </option>

          <c:forEach items="${membres}" var="membre" >
            <option value="${membre.identifiant}" >
              ${empty membre.nom ? " (Le nom est vide)" : membre.nom }
              ${empty membre.prenom ? " (Le prénom est vide)" : membre.prenom }
            </option>
          </c:forEach>

          <input type="submit" value="Supprimer" />

        </select>

      </form>
    </div>

  </div>

 </div>
 <br>

 <jsp:include page="footer.jsp" />

</body>
</html>
