<style>
            <%@include file="/WEB-INF/css/style.css"%>
</style>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;1,300&display=swap" rel="stylesheet">
</head>
<body>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<header class="header">
<div class="container">
    <div class="header__inner">
        <div class="header__main_reference">
            <a class="payment_system" href="/">Payment System</a>
        </div>
        
        <nav class="nav">
            <c:if test="${empty sessionScope.isActive}">
            <a class="nav_link" href="/sign-in"><c:out value="${lang.headerSignIn}"/></a>
            </c:if>
            <c:if test="${not empty sessionScope.isActive}">
            <a class="nav_link" href="/log-out"><c:out value="${lang.headerLogOut}"/></a>
            </c:if>

            <a class="nav_link" href="/register"><c:out value="${lang.headerRegisterInSystem}"/></a>
        </nav>
        
    </div>

    <div class="lang-switch">
        <c:if test="${empty cookie.lang.value}">
            <a href="/ru"><b>сменить язык на русский</b></a>
        </c:if>
        <c:if test="${not empty cookie.lang.value}">
            <a href="/en">switch language to english</a>
        </c:if>
    </div>

</div>
</header>