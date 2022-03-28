<!DOCTYPE html>
<html lang="fr">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>

  <jsp:include page="navbar.jsp" />

  <H1>
    PAGE CREATION  
  </H1>

  <div class="row">
    <%@ page import="java.util.Date" %>
    <strong> Nous sommes le :  </strong>: <%=new Date() %>
  </div>
  
  <div class="row">
    <strong> Les tableaux sont  :</strong> 
    
    <c:forEach items="${tableaux}" var="tableau" >
    
      <c:set var="age" value="${tableau[0].identifiant}" scope="page" />
      

      <p>
       
       <c:out value= "${tableau[0].nom }" />   a   
       <c:out value= "${tableau[0].identifiant}" />  ans. 
       <c:out value= "${age}" />  ans. 
      
      </p>

      <c:if test="${ age > 50 }" var="variable">
      Quel crôuton
      </c:if>

    </c:forEach>



    

  </div> 
  
  <br>

  <jsp:include page="footer.jsp" />

</body>
</html>
