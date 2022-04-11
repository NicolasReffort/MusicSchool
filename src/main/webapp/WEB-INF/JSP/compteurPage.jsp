    <c:if test="${not empty compteurPage}">
        <p id="motCompteur"> Compteur :
        </p>
        <c:forEach var = "i" begin = "1" end ="${compteurPage}" >
              <img width="40" height="120" id="compteurImg" class="img-thumbnail" alt="k" src="assets/image/cat.png">
        </c:forEach>

    </c:if>










