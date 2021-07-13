<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.accountTitle}"/></title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>

        <p>It's account</p>
        <a href="/account/credit-cards"><c:out value="${lang.accountShowCreditCards}"/></a>
        <br>

        <a href="/account/payments"><c:out value="${lang.accountShowPayments}"/></a>
        <br>


    <jsp:include page="footer.jsp"/>
    </body>
</html>