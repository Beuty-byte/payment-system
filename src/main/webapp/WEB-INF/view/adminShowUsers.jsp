<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Index page</title>
    </head>
    <body>

    <form>
    <p>Choose sort</p>
    <p><input name="sort" type="radio" value="name"> sort by name</p>
     <p><input name="sort" type="radio" value="name-desc"> sort by name desc</p>
     <p><input name="sort" type="radio" value="surname" checked> sort by surname</p>
     <p><input name="sort" type="radio" value="surname-desc" checked> sort by surname desc</p>
     <p><input name="sort" type="radio" value="email" checked> sort by email</p>
     <p><input name="sort" type="radio" value="email-desc" checked> sort by email desc</p>
     <p><input type="submit" value="sort"></p>
    </form>

     <c:forEach var="user" items="${users}">
                     <p>${user.name}</p>
                     <p>${user.surname}</p>
                     <p>${user.email}</p>
                     <a href="/admin/users/${user.id}">show details</a>
                     <hr>
     </c:forEach>

   <div>${pagination}</div>
    </body>
</html>