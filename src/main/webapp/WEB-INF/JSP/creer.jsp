<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
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
    <strong> Les membres sont :</strong> 
    ${ requestScope.mapStars}
    <br><br>
  </div>
  
  <%-- <div class="row">
    <strong>No scope:</strong> ${personne.value}
    <br><br>
  </div> --%>



  <!-- <%
  String user = request.getParameter("id");
  String pwd = request.getParameter("password");
  %> -->
  
  <br>

  <jsp:include page="footer.jsp" />

</body>
</html>
