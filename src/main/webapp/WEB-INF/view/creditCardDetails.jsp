<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Index page</title>
    </head>
    <body>

       <c:choose>
           <c:when test="${creditCardInfo != null}">
               <p>Card name : ${creditCardInfo.cardName}</p>
               <p>Card id : ${creditCardInfo.idForUserView}</p>
               <p>Card balance : ${creditCardInfo.bankAccount.balance}</p>
               <c:set var="refreshSent" value="${creditCardInfo.bankAccount.blocked}"/>
               <p>Card is blocked : ${refreshSent}</p>
               <br />
<hr>

            <form method="POST">
            <p>You can block your credit card</p>
            <input type="hidden" name="block" value="block">
            <input type="submit" value="blockCard">
            </form>

<hr>
           <form method="POST">
           <input type="text" name="amount" placeholder="format 1419.01">
           <input type="submit" value="top up an account">
           </form>

           <c:forEach var="error" items="${errors}">
                <p>${error}</p>
           </c:forEach>

           </c:when>
           <c:otherwise>
               <p>You can not have access to credit card info</p>
               <br />
           </c:otherwise>
       </c:choose>

    </body>
</html>