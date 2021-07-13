<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.registerTitle}"/></title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>


        ${serverError}
        <c:forEach var="error" items="${errors}">
         <p>${error}</p>
        </c:forEach>

        <form method="POST">
            <input type="text" placeholder="<c:out value="${lang.registerPlaceholderForName}"/>" name="name">
            <br>
            <input type="text" placeholder="<c:out value="${lang.registerPlaceholderForSurname}"/>" name="surname">
            <br>
            <input type="password" placeholder="<c:out value="${lang.registerPlaceholderForPassword}"/>" name="password1">
            <br>
            <input type="password" placeholder="<c:out value="${lang.registerPlaceholderForPassword}"/>" name="password2">
            <br>
            <input type="email" placeholder="<c:out value="${lang.registerPlaceholderForEmail}"/>" name="email">
            <br>
            <input type="submit" value="<c:out value="${lang.registerButtonForRegister}"/>">
        </form>



        <jsp:include page="footer.jsp"/>
    </body>
</html>