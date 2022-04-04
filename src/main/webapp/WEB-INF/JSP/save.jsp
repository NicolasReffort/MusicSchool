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
  <strong> Nous sommes le : </strong>: <%=new Date() %>
 </div>

 DEBUG

 <div class="row">
unePersonneChoisieDansLeSelect
 <c:out value="${unePersonneChoisieDansLeSelect}"/>  ; 
  <br>

 <div class="row">
unePersonneModifiee
 <c:out value="${unePersonneModifiee}"/>  ; 
  <br>
 
 creation : 
 <c:out value="${creation}"/>  ; 
 <br>

 succesModification :
 <c:out value="${succesModification}"/>  ; 
 <br>
 erreurDetectee : 
 <c:out value="${erreurDetectee}"/>  ; 

 <br>
 membreToModifier.identifiant : 
 <c:out value="${membreToModifier.identifiant}"/>  ; 

 <br>
 personneACreer.identifiant : 
 <c:out value="${personneACreer.nom}"/>  ; 

les erreurs sont : 
<c:out value="${erreurs}"/>  ;
<br> 

  <c:choose>

  <%-- DEBUT AFFICHAGE DES SUCCES DES MODIF CREATION --%>
  <c:when test="${creation == 'done' || succesModification}">
 
   <div class="bg-success p-2 text-dark bg-opacity-50">
    ☻ Opération réussie. 
   </div>  
    
  </c:when>
  <%-- FIN AFFICHAGE DES SUCCES DES MODIF CREATION --%>


  <%-- DEBUT AFFICHAGE FORMULAIRES DE CREATION, ET MODIFICATION DE PERSONNE DETERMINEE,
   unePersonneChoisieDansLeSelect = première étape 
   unePersonneModifie = seconde étape --%>
  <c:when test="${creation == 'asked' || unePersonneChoisieDansLeSelect || unePersonneModifiee  }" >
  
    <%-- EN FONCTION DE L ETAPE ON AFFECTE LES VARIABLES EN CONSEQUENCE --%>
    <c:if test="${creation == 'asked'}">
      <c:set var="h1" value="PAGE CREATION"/>  
      <c:set var="action" value="?action=creer"/>    

      <c:if test="${erreurDetectee}"> 
        <c:set var="inputNom" value="${personneACreer.nom}"/>  
        <c:set var="inputPrenom" value="${personneACreer.prenom}"/>  
      </c:if>
    </c:if>   
      
    <c:if test="${unePersonneChoisieDansLeSelect}">
      <c:set var="h1" value="PAGE MODIFICATION"/>  
      <c:set var="action" value="?action=modifier"/>  
      <c:set var="action" value="?action=modifier"/>  
      <c:set var="inputNom" value="${membreToModifier.nom}"/>  
      <c:set var="inputPrenom" value="${membreToModifier.prenom}"/>  
      <c:set var="inputId" value="${membreToModifier.identifiant}"/> 
    </c:if>  


    <c:if test="${unePersonneModifiee}">

        <c:if test="${erreurDetectee}"> 
          <c:set var="inputNom" value="${membreModifie.nom}"/>  
          <c:set var="inputPrenom" value="${membreModifie.prenom}"/>  
          <c:set var="inputId" value="${membreModifie.identifiant}"/> 
        </c:if>

        <c:if test="${erreurDetectee == 'false'}"> 
          <c:set var="inputNom" value="${membreToModifier.nom}"/>  
          <c:set var="inputPrenom" value="${membreToModifier.prenom}"/>  
          <c:set var="inputId" value="${membreToModifier.identifiant}"/> 
        </c:if>  

    </c:if>


    <H1>
      <c:out value="${h1}" />
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

    <div class="col-5">    
      <form method="post" action="${action}">

        <label for="nom">
          Nom
        </label>
        <input value="${inputNom}" type="text" name="nom" id="nom" /> 

        <label for="prenom">
          Prénom     
          </label>
        <input value="${inputPrenom}" type="text" name="prenom" id="prenom" />   

        <input type="hidden" value="${inputId}" name="identifiant" id="identifiant" />  

        <input type="submit" />

      </form>
    <div>

   </div>
  </c:when>
  <%-- FIN AFFICHAGE FORMULAIRES DE CREATION, ET MODIFICATION DE PERSONNE DETERMINEE,


  <%-- (PAR DEFAUT) AFFICHAGE DES MEMBRES A SELECTIONNER --%>
  <c:otherwise>   
   <H1>
    QUI VOULEZ-VOUS MODIFIER ?
   </H1>

    <div class="row">  
    
      <div class="col-5">
        <form name="formModificationListe" action="?action=modifier" method="post">

          <select name="idFromSelect" class="col-8 form-select" aria-label="Default select example">
            <option selected> 
              <strong> Les membres sont :</strong> 
            </option>

            <c:forEach items="${membres}" var="membre" >   
            
              <option value="${membre.identifiant}" > 
              ${empty membre.identifiant ? " (L'identifiant est vide)" : membre.identifiant }
              ${empty membre.nom ? " (Le nom est vide)" : membre.nom }
              ${empty membre.prenom ? " (Le prénom est vide)" : membre.prenom }
              </option>  
              
            </c:forEach> 
            
            <input type="submit" value="Modifier" />       
            
          </select>
        </form>
      <div>
     
    </div> 

  </c:otherwise>
    <%-- FIN AFFICHAGE DES MEMBRES A SELECTIONNER --%>


 </c:choose> 

  <br>
 le formulaire va se diriger vers : 
  <c:out value="${action}"/>  ; 
  <br>

          <c:forEach items="${membres}" var="membre" >   
            
              <option value="${membre.identifiant}" > 
              ${empty membre.identifiant ? " (L'identifiant est vide)" : membre.identifiant }
              ${empty membre.nom ? " (Le nom est vide)" : membre.nom }
              ${empty membre.prenom ? " (Le prénom est vide)" : membre.prenom }
              </option>  
              
            </c:forEach> 
 


 </div>

 <jsp:include page="footer.jsp" />

</body>
</html>
