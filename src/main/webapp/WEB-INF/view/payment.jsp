<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.paymentTitle}" /></title>


    <jsp:include page="header.jsp"/>
    <div class="container">
       <c:forEach var="payment" items="${payments}">
                  <p><c:out value="${lang.paymentSum}" /> :${payment.sum}</p>
                  <p><c:out value="${lang.paymentDate}" /> :${payment.invoicedPaymentDate}</p>
                  <a href="/account/payments/${payment.id}"><c:out value="${lang.paymentShowDetails}" /></a>
                  <hr>
        </c:forEach>
    <div class="container">   

<jsp:include page="footer.jsp"/>
    </body>
</html>