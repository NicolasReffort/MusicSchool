    <c:if test="${not empty compteurPage}">
        Compteur :
        <c:forEach var = "i" begin = "1" end ="${compteurPage}" >
              <img width="40" height="120" class="img-thumbnail" alt="k" src="assets/image/cat.png">
        </c:forEach>

    </c:if>

    <br>
  Le dernier nom créé est :  <
    <h2>
    <c:out value="${nouveauNom}"/>
    </h2>

    <h3>
    <c:out value="${nouveauPrenom}"/>
    </h3>

  >







