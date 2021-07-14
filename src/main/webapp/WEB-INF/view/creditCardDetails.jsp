<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.creditCardDetailsTitle}" /></title>


    <jsp:include page="header.jsp"/>
    <div class="container">
       <c:choose>
           <c:when test="${creditCardInfo != null}">
               <p><c:out value="${lang.creditCardDetailsCardName}" /> : ${creditCardInfo.cardName}</p>
               <p><c:out value="${lang.creditCardDetailsCardId}" /> : ${creditCardInfo.idForUserView}</p>
               <p><c:out value="${lang.creditCardDetailsCardBalance}" /> : ${creditCardInfo.bankAccount.balance}</p>
               <c:set var="refreshSent" value="${creditCardInfo.bankAccount.blocked}"/>
               <p><c:out value="${lang.creditCardDetailsCardIsBlocked}" /> : ${refreshSent}</p>
               <br />
<hr>

            <form method="POST">
            <p><c:out value="${lang.creditCardDetailsBlockedCard}" /></p>
            <input type="hidden" name="block" value="block">
            <input type="submit" value="<c:out value="${lang.creditCardDetailsBlockCard}" />">
            </form>

<hr>
           <form method="POST">
           <input type="text" name="amount" placeholder="<c:out value="${lang.creditCardDetailsInputFormat}" /> 1419.01">
           <input type="submit" value="<c:out value="${lang.creditCardDetailsTopUpAnAccount}" />">
           </form>

           <c:forEach var="error" items="${errors}">
                <p>${error}</p>
           </c:forEach>

           </c:when>
           <c:otherwise>
               <p><c:out value="${lang.creditCardDetailsNotAccess}" /></p>
               <br />
           </c:otherwise>
       </c:choose>

       </div>
<jsp:include page="footer.jsp"/>
    </body>
</html>