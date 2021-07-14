<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.accountTitle}"/></title>


    <jsp:include page="header.jsp"/>
    <div class="container">

        <div class="account_reference">        
        <a href="/account/credit-cards"><c:out value="${lang.accountShowCreditCards}"/></a>
        <br>

        <a href="/account/payments"><c:out value="${lang.accountShowPayments}"/></a>
        <br>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
    </body>
</html>