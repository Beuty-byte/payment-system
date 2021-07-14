<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><c:out value="${lang.paymentsDetailsTitle}"/></title>


    <jsp:include page="header.jsp"/>

    <div class="container">
             <c:choose>
                 <c:when test="${paymentInfo != null}">
                     <p><c:out value="${lang.paymentDetailsSum}"/> : ${paymentInfo.sum}</p>
                     <p><c:out value="${lang.paymentDetailsDate}"/> : ${paymentInfo.invoicedPaymentDate}</p>
                     <br />

                <c:forEach var="error" items="${paymentError}">
                        <p>${error}</p>
                    </c:forEach>




      <hr>

            <form method="POST">
                <select name="creditCardId">
                    <c:forEach var="creditCard" items="${userCreditCards}">
                        <option value="${creditCard.id}">
                            <c:out value="${lang.paymentDetailsCreditCardNumber}"/> : ${creditCard.idForUserView}
                            <c:out value="${lang.paymentDetailsCreditCardName}"/> : ${creditCard.cardName}
                            <c:out value="${lang.paymentDetailsCreditCardBalance}"/> : ${creditCard.bankAccount.balance}
                            <c:out value="${lang.paymentDetailsCreditCardIsBlocked}"/> : ${creditCard.bankAccount.blocked}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" value="<c:out value="${lang.paymentDetailsButtonForPay}"/>">
            </form>

                 </c:when>
                 <c:otherwise>
                     <p><c:out value="${lang.paymentDetailsAccessToInfo}"/></p>
                     <br />
                 </c:otherwise>
             </c:choose>
    </div>

<jsp:include page="footer.jsp"/>
    </body>
</html>