<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="header">

<div>
<a href="/">To main page</a>




<c:if test="${empty sessionScope.isActive}">
<a href="/sign-in"><c:out value="${lang.headerSignIn}"/></a>
</c:if>
<c:if test="${not empty sessionScope.isActive}">
<a href="/log-out"><c:out value="${lang.headerLogOut}"/></a>
</c:if>



<a href="/register"><c:out value="${lang.headerRegisterInSystem}"/></a>



<div class="lang-switch">
        <c:if test="${empty cookie.lang.value}">
            <a href="/ru"><b>switch language to russian</b></a>
        </c:if>
        <c:if test="${not empty cookie.lang.value}">
            <a href="/en">switch language to english</a>
        </c:if>
</div>


</div>


</header>