<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Index page</title>




    <jsp:include page="header.jsp"/>
    <div class="container">
       <h1 class="greeting"><c:out value="${lang.greetingAtIndexPage}" /></h1>

       <c:if test="${not empty sessionScope.isActive}">
                   <a href="${referenceToAccount}">
                   <c:out value="${lang.referenceAtIndexPage}" />
                   </a>
       </c:if>

       <div class="container">

    <jsp:include page="footer.jsp"/>
    </body>
</html>