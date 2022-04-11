<!DOCTYPE html>
<html lang="fr">
<head>

 <title> De bonnes notes</title>
 <jsp:include page="head.jsp" />
 <jsp:include page="imports.jsp" />
 <jsp:include page="compteurPage.jsp" />

</head>
<body>

 <jsp:include page="navbar.jsp"/>


  <div class="text-center mt-5">

    <div class="alert alert-primary" role="alert">

      <c:forEach var="cookies" items="${cookie}">

        <c:if test="${cookies.key == 'prenomUser'}">
          <c:choose>
            <c:when test="${cookies.value.value == 'vide'}">
              <c:set var="prenom" value="Utilisateur"/>
            </c:when>

            <c:otherwise>
              <c:set var="prenom" value="${cookies.value.value}"/>
            </c:otherwise>
          </c:choose>
        </c:if>

        <c:if test="${cookies.key == 'nomUser'}">
          <c:choose>
            <c:when test="${cookies.value.value == 'vide'}">
              <c:set var="nom" value="Inconnu"/>
            </c:when>

            <c:otherwise>
              <c:set var="nom" value="${cookies.value.value}"/>
            </c:otherwise>
          </c:choose>
        </c:if>


      </c:forEach>

      <h3>
      Bienvenue,
      ${prenom}
      ${nom}
      </h3>
    </div>

  </div>



 <jsp:include page="footer.jsp" />

</body>
</html>
