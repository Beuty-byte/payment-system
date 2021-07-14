<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.adminShowUsersTitle}" /></title>


    <jsp:include page="header.jsp"/>
    <div class="container">
    <form>
    <p><c:out value="${lang.adminShowUsersMessageBeforeSort}" /></p>
    <p><input name="sort" type="radio" value="name"> <c:out value="${lang.adminShowUsersSortByName}" /></p>
     <p><input name="sort" type="radio" value="name-desc"> <c:out value="${lang.adminShowUsersSortByNameDesc}" /></p>
     <p><input name="sort" type="radio" value="surname" checked> <c:out value="${lang.adminShowUsersSortBySurname}" /></p>
     <p><input name="sort" type="radio" value="surname-desc" checked> <c:out value="${lang.adminShowUsersSortBySurnameDesc}" /></p>
     <p><input name="sort" type="radio" value="email" checked> <c:out value="${lang.adminShowUsersSortByEmail}" /></p>
     <p><input name="sort" type="radio" value="email-desc" checked> <c:out value="${lang.adminShowUsersSortByEmailDesc}" /></p>
     <p><input type="submit" value="<c:out value="${lang.adminShowUsersSortButtonValue}" />"></p>
    </form>

     <c:forEach var="user" items="${users}">
                     <p>${user.name}</p>
                     <p>${user.surname}</p>
                     <p>${user.email}</p>
                     <a href="/admin/users/${user.id}"><c:out value="${lang.adminShowUsersReferenceToDetails}" /></a>
                     <hr>
     </c:forEach>

   <div class="pagination">${pagination}</div>
    </div>


   <jsp:include page="footer.jsp"/>
    </body>
</html>