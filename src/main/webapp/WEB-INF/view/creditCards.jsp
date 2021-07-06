<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Index page</title>
    </head>
    <body>
        <p>Total balance : ${totalBalance}</p>
       <c:forEach var="creditCard" items="${creditCards}">
                  <p>Your card Number : ${creditCard.idForUserView}</p>
                  <p>Your balance : ${creditCard.bankAccount.balance}$</p>
                  <p>${creditCard.cardName}</p>
                  <a href="/account/credit-cards/${creditCard.id}">Show details</a>
                  <hr>
        </c:forEach>

    </body>
</html>