<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Index page</title>
    </head>
    <body>

       <c:forEach var="payment" items="${payments}">
                  <p>${payment.sum}</p>
                  <p>${payment.invoicedPaymentDate}</p>
                  <a href="/account/payments/${payment.id}">Show payment details</a>
                  <hr>
        </c:forEach>

    </body>
</html>