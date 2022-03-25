<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title> De bonnes notes</title>

</head>
<body>

  <jsp:include page="navbar.jsp"/>

  <div class="alert alert-primary" role="alert">
  BONJOUR
  </div>

  <div class="row">
    J'ai dit : 
    Bonjour ! 
  </div>
  <br>

  <form action="creer" method="post">
    <strong>User ID</strong>:<input type="text" name="id"><br>
    <strong>Password</strong>:<input type="text" name="password"><br>
    <input type="submit" value="Login">
  </form>
  
  <jsp:include page="footer.jsp" />

</body>
</html>
