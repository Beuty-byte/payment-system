<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.adminShowUsersDetailsTitle}" /></title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>

     <p>${user.name}</p>
     <p>${user.surname}</p>
     <p>${user.email}</p>
     <hr>
     <c:out value="${lang.adminShowUsersDetailsCreditCardsList}" />

     <c:forEach var="card" items="${user.creditCards}">
                          <p><c:out value="${lang.adminShowUsersDetailsCreditCardNumber}" /> :${card.idForUserView}</p>
                          <c:if test="${card.bankAccount.blocked}">
                                <form method="POST">
                                     <p><c:out value="${lang.adminShowUsersDetailsCreditCardUnlock}" /></p>
                                     <input type="hidden" name="unBlock" value="${card.bankAccount.id}">
                                        <input type="submit"
                                        value="<c:out value="${lang.adminShowUsersDetailsCreditCardUnlockButton}" />">
                                </form>
                          </c:if>
    <hr>
     </c:forEach>


<jsp:include page="footer.jsp"/>

    </body>
</html>