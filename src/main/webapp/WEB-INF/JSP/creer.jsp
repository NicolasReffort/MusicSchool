<!DOCTYPE html>
<html lang="fr">
<head>
  <jsp:include page="head.jsp" />
</head>
<body>

  <jsp:include page="navbar.jsp" />
  <jsp:include page="imports.jsp" />

  <H1>
    PAGE CREATION  
  </H1>

  <div class="row">
    <%@ page import="java.util.Date" %>
    <strong> Nous sommes le :  </strong>: <%=new Date() %>
  </div>

  <div class="row">
   <form method="post" action="?action=creer">

       <label for="nom">Nom : </label>
       <input type="text" name="nom" id="nom" />       

       <label for="prenom"> Prénom : </label>
       <input type="prenom" name="prenom" id="prenom" />       

       <label for="oeuvre">oeuvre : </label>
       <input type="text" name="oeuvre" id="oeuvre" />  
           
       <input type="submit" />

   </form>
  </div>

    <c:out value= "${ nom }" />  est le retour du formulaire  !

  
  
  <div class="row">
    <strong> Les tableaux sont  :</strong> 
    
    <c:forEach items="${tableaux}" var="tableau" >
    
      <c:set var="age" value="${tableau[0].identifiant}" scope="page" />
      
      <p>
        ${ empty tableau[0].nom ? " (Le nom est vide pour ce mec)" : tableau[0].nom }       
        a peint 
       <c:out value= "${tableau[1]}" />  , il est  mort à l'âge de    
       <c:out value= "${tableau[0].identifiant}" />  ans. 
       <c:out value= "${age}" />  ans  !
      
      </p>

      <p> 
      <c:if test="${ age > 50 }" var="variable">
      Quel crôuton.
      </c:if>
      <p/>

      <c:choose>

        <c:when test="${ tableau[0].nom == 'Picasso' }">
         <p> (C'est un espagnol) <p/> 
        </c:when>

        <c:when test="${ tableau[0].nom == 'De Vinci'}">
        (C'est un rital)
        </c:when>

        <c:otherwise> (nationalité indifférente) 
        </c:otherwise>

      </c:choose>


        

    </c:forEach>



    

  </div> 
  
  <br>

  <jsp:include page="footer.jsp" />

</body>
</html>
