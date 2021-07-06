<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Page on register in system</title>
    </head>
    <body>
        ${serverError}
        <c:forEach var="error" items="${errors}">
            <p>${error}</p>
        </c:forEach>

        <form method="POST">
            <input type="text" placeholder="enter your name" name="name">
            <br>
            <input type="text" placeholder="enter your surname" name="surname">
            <br>
            <input type="password" placeholder="enter your password" name="password1">
            <br>
            <input type="password" placeholder="enter your password" name="password2">
            <br>
            <input type="email" placeholder="enter your email" name="email">
            <br>
            <input type="submit" value="Register">
        </form>
    </body>
</html>