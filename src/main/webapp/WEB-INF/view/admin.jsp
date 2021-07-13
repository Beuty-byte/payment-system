<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.adminTitle}" /></title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>

       <a href="/admin/users"><c:out value="${lang.adminShowAllUsers}" /></a>
       <br>

<jsp:include page="footer.jsp"/>

    </body>
</html>