<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.SigInTitle}"/></title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>


            <p>${error}</p>

        <form method="POST">
            <input type="email" placeholder="<c:out value="${lang.SigInPlaceholderForEmail}"/>" name="email">
            <br>
            <input type="password" placeholder="<c:out value="${lang.SigInPlaceholderForPassword}"/>" name="password">
            <br>
            <input type="submit" value="<c:out value="${lang.SigInButtonForLogin}"/>">
        </form>


        <jsp:include page="footer.jsp"/>
    </body>
</html>