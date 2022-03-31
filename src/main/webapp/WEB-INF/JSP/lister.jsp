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
  PAGE LISTE
 </H1>

 <div class="row">  

  <select class="col-8 form-select" aria-label="Default select example">
   <option selected> 
    <strong> Les membres sont :</strong> 
   </option>

   <c:forEach items="${membres}" var="membre" >   
   
    <c:set var="age" value="${membre.identifiant}" scope="page" />

    <option> 
     ${empty membre.nom ? " (Le nom est vide pour ce mec)" : membre.nom }
     ${empty membre.prenom ? " (Le prénom est vide pour ce mec)" : membre.prenom }
    </option>      
     
   </c:forEach> 
  </select>
  
 </div> 
 
 <br>

 <jsp:include page="footer.jsp" />

</body>
</html>
