<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.registerTitle}"/></title>


    <jsp:include page="header.jsp"/>

    <div class="container">
        <div class="form_wrapper">
            <div class="in_form">
        
        

        <form method="POST">
            <input class="form_input" type="text" placeholder="<c:out value="${lang.registerPlaceholderForName}"/>" name="name">
            <br>
            <input class="form_input" type="text" placeholder="<c:out value="${lang.registerPlaceholderForSurname}"/>" name="surname">
            <br>
            <input class="form_input" type="password" placeholder="<c:out value="${lang.registerPlaceholderForPassword}"/>" name="password1">
            <br>
            <input class="form_input" type="password" placeholder="<c:out value="${lang.registerPlaceholderForPassword}"/>" name="password2">
            <br>
            <input class="form_input" type="email" placeholder="<c:out value="${lang.registerPlaceholderForEmail}"/>" name="email">
            <br>
            <input class="form_button" type="submit" value="<c:out value="${lang.registerButtonForRegister}"/>">
        </form>

        
            </div>
            
        </div>
        <div class="errors">
            ${serverError}
            <c:forEach var="error" items="${errors}">
             <p>${error}</p>
            </c:forEach>
            </div>
    </div>


        <jsp:include page="footer.jsp"/>
    </body>
</html>