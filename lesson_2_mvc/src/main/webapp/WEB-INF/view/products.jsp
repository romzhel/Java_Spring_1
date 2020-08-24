<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />--%>
</head>

<body>
<h1>Product list</h1>

<ul>
    <c:forEach var="product" items="${products}">
        <table>
            <tr>
                <td>${product.id}</td>
                <td>${product.title}</td>
                <td>${product.cost}</td>
            </tr>
        </table>


        <%--<li>${product.id}</li>
        <li>${product.title}</li>
        <li>${product.cost}</li>
        <br>--%>
    </c:forEach>
</ul>

</body>
</html>