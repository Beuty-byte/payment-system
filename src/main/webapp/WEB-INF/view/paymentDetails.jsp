<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Index page</title>
    </head>
    <body>


             <c:choose>
                 <c:when test="${paymentInfo != null}">
                     <p>Sum for payment : ${paymentInfo.sum}</p>
                     <p>Payment date : ${paymentInfo.invoicedPaymentDate}</p>
                     <br />

                <c:forEach var="error" items="${paymentError}">
                        <p>${error}</p>
                    </c:forEach>




      <hr>

            <form method="POST">
                <select name="creditCardId">
                    <c:forEach var="creditCard" items="${userCreditCards}">
                        <option value="${creditCard.id}">
                            Credit card number : ${creditCard.idForUserView}
                            Credit card name : ${creditCard.cardName}
                            Credit  card balance : ${creditCard.bankAccount.balance}
                            Credit  card is blocked : ${creditCard.bankAccount.blocked}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" value="pay">
            </form>

                 </c:when>
                 <c:otherwise>
                     <p>You can not have access to payment info</p>
                     <br />
                 </c:otherwise>
             </c:choose>

    </body>
</html>