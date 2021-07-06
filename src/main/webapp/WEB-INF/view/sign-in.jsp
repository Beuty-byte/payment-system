<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Page on register in system</title>
    </head>
    <body>


            <p>${error}</p>

        <form method="POST">
            <input type="email" placeholder="enter your email" name="email">
            <br>
            <input type="password" placeholder="enter your password" name="password">
            <br>
            <input type="submit" value="sign in">
        </form>
    </body>
</html>