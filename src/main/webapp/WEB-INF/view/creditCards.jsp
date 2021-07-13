<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.creditCardsTitle}"/></title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>

        <p><c:out value="${lang.creditCardsTotalBalance}"/> : ${totalBalance}</p>
       <c:forEach var="creditCard" items="${creditCards}">
                  <p><c:out value="${lang.creditCardsCardNumber}"/> : ${creditCard.idForUserView}</p>
                  <p><c:out value="${lang.creditCardsCardBalance}"/> : ${creditCard.bankAccount.balance}$</p>
                  <p>${creditCard.cardName}</p>
                  <a href="/account/credit-cards/${creditCard.id}"><c:out value="${lang.creditCardsCardShowDetailsReference}"/></a>
                  <hr>
        </c:forEach>


<jsp:include page="footer.jsp"/>
    </body>
</html>