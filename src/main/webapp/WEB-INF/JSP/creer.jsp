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

      <label for="id">id : </label>
      <input type="id" name="id" id="id" />  
          
      <input type="submit" />

   </form>
  </div>
  
  <div class="row">
    <strong> Les membres sont  :</strong> 
    
    <c:forEach items="${membres}" var="membre" >
    
      <c:set var="age" value="${membre.identifiant}" scope="page" />
      
      <p>
        ${empty membre.nom ? " (Le nom est vide pour ce mec)" : membre.nom  }
         ${empty membre.prenom ? " (Le prénom est vide pour ce mec)" : membre.prenom  }            
       <c:out value= "${membre.identifiant}" />      
      </p>

      <p> 
      <c:if test="${ membre.identifiant > 50 }" var="variable">
       Quel grand identifiant.
      </c:if>
      <p/>

      <c:choose>

        <c:when test="${membre.nom == 'Picasso'}">
         <p> (C'est un espagnol) <p/> 
        </c:when>

        <c:when test="${membre.nom == 'De Vinci'}">
        (C'est un rital)
        </c:when>

        <c:otherwise> (Sa nationalité indifférente) 
        </c:otherwise>

      </c:choose>
        
    </c:forEach> 

  </div> 
  
  <br>

  <jsp:include page="footer.jsp" />

</body>
</html>
