<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />--%>
</head>

<body>
<h1>Product list</h1>

<ul>
    <c:forEach var="product" items="${productsPage.content}">
        <table>
            <tr>
                <td>${product.id}</td>
                <td>${product.title}</td>
                <td>${product.cost}</td>
            </tr>
        </table>
    </c:forEach>
</ul>
<div c:if="${productsPage.totalPages > 0}" class="pagination">
    <c:forEach var="pageNumber" items="${pageNumbers}">
        <a href="${pathAndCondition}&page=${pageNumber}">
                ${pageNumber}
                <%--c:class="${pageNumber==productPage.number + 1} ? active"--%>>
        </a>
    </c:forEach>
</div>

</body>
</html>