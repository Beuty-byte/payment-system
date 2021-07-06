<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Index page</title>
    </head>
    <body>

     <p>${user.name}</p>
     <p>${user.surname}</p>
     <p>${user.email}</p>
     <hr>
     List credit cards

     <c:forEach var="card" items="${user.creditCards}">
                          <p>Number credit card :${card.idForUserView}</p>
                          <c:if test="${card.bankAccount.blocked}">
                                <form method="POST">
                                     <p>Unblock card</p>
                                     <input type="hidden" name="unBlock" value="${card.bankAccount.id}">
                                        <input type="submit" value="UnBlockCard">
                                </form>
                          </c:if>
    <hr>
     </c:forEach>


    </body>
</html>