<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.SigInTitle}"/></title>


    <jsp:include page="header.jsp"/>

    <div class="container">

        <div class="form_wrapper">
            <div class="in_form">
            

        <form method="POST">
            <input class="form_input" type="email" placeholder="<c:out value="${lang.SigInPlaceholderForEmail}"/>" name="email">
            <br>
            <input class="form_input" type="password" placeholder="<c:out value="${lang.SigInPlaceholderForPassword}"/>" name="password">
            <br>
            <input class="form_button" type="submit" value="<c:out value="${lang.SigInButtonForLogin}"/>">
        </form>


        <p>${error}</p>


            </div>
            
        </div>


    </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>