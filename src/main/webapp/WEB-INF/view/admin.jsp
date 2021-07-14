<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.adminTitle}" /></title>


    <jsp:include page="header.jsp"/>
    <div class="container">
        <div class="admin_main_page">
       <a href="/admin/users"><c:out value="${lang.adminShowAllUsers}" /></a>
       <br>
    </div>
        </div>
<jsp:include page="footer.jsp"/>

    </body>
</html>