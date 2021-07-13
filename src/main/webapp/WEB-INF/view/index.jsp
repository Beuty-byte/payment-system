<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Index page</title>
    </head>
    <body>

    <jsp:include page="header.jsp"/>

       <h2><c:out value="${lang.greetingAtIndexPage}" /></h2>


    <jsp:include page="footer.jsp"/>
    </body>
</html>