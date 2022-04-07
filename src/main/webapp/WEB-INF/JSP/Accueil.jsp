<!DOCTYPE html>
<html lang="fr">
<head>

 <title> De bonnes notes</title>
 <jsp:include page="head.jsp" />
 <jsp:include page="imports.jsp" />


</head>
<body>

 <jsp:include page="navbar.jsp"/>

 <div class="alert alert-primary" role="alert">
 BONJOUR
 </div>

  <div class="text-center mt-5">
    <c:if test="${messageAccueil}">
      ${messageAccueil}
    </c:if>

  </div>



 <div class="row">
  J'ai dit :
  Bonjour !
 </div>
 <br>
   <p>
      <c:out value="${messageAccueil}" />
    </p>

 <jsp:include page="footer.jsp" />

</body>
</html>
