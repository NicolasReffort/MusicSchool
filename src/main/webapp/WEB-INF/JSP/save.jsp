<!DOCTYPE html>
<html lang="fr">
<head>
  <jsp:include page="head.jsp" />
</head>
<body>

  <jsp:include page="navbar.jsp" />
  <jsp:include page="imports.jsp" />

  <div class="row">
    <%@ page import="java.util.Date" %>
    <strong> Nous sommes le :  </strong>: <%=new Date() %>
  </div>

  <div class="row">
  

  <c:choose>

    <c:when test="${unePersonneChoisie}">
      <H1>
        PAGE MODIFICATION DE ... <c:out value="${membreToModifier.nom}"  />
      </H1>

      <div class="row">
        
        <form method="post" action="?action=creer">

          <label for="nom">Nom</label>
          <input type="text" value="${membreToModifier.nom}" name="nom" id="nom" />       

          <label for="prenom"> Prénom : </label>
          <input type="text" value="${membreToModifier.prenom}" name="prenom" id="prenom" />    

          <input type="submit" />

        </form>

      </div>
    </c:when>

    <c:when test="${creation == 'asked'}">
      <H1>
        PAGE CREATION
      </H1>
      
        

      <c:if test="${erreurDetectee == 'true'}">
        <div class="alert alert-warning" role="alert">
          Saisie invalide : 
          <c:forEach items="${erreurs}" var="erreur" >             
            <c:out value="${erreur}" />
            <br>
          </c:forEach>
        </div>

      </c:if>

      <div class="row">
        
        <form method="post" action="?action=creer">

          <label for="nom">Nom : </label>
          <input type="text" name="nomToCreate" id="nom" />       

          <label for="prenom"> Prénom : </label>
          <input type="text" name="prenomToCreate" id="prenom" />       

          <input type="submit" />

        </form>

      </div>
    </c:when>

    <c:when test="${creation == 'done'}">
      <H1>
        PAGE CREATION : 
      </H1>  
      <div class="bg-success p-2 text-dark bg-opacity-50">
       ☻ Création réussie. 
      </div>    
     
 
    </c:when>

    <c:otherwise>      
      <H1>
        QUI VOULEZ-VOUS MODIFIER ?
      </H1>

        <div class="row">    
          <form name="formModificationListe" action="?action=modifier" method="post">

            <select name="idFromSelect" class="col-8 form-select" aria-label="Default select example">
              <option selected> 
                <strong> Les membres sont  :</strong> 
              </option>

              <c:forEach items="${membres}" var="membre" >      
              
                <option value="${membre.identifiant}" >  
                  ${empty membre.identifiant ? " (L'identifiant est vide)" : membre.identifiant  }
                  ${empty membre.nom ? " (Le nom est vide)" : membre.nom  }
                  ${empty membre.prenom ? " (Le prénom est vide)" : membre.prenom }
                </option>   
                  
              </c:forEach>  
              
              <input type="submit" value="Modifier ce crétin" />              
            
            </select>
          </form>
          
        </div> 

    </c:otherwise>

  </c:choose> 
  </div>

  <jsp:include page="footer.jsp" />

</body>
</html>
