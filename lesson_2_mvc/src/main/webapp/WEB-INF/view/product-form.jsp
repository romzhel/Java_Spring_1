<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<body>
<form:form action="processForm" modelAttribute="product">
    Title: <form:input path="title"/>
    <br>
    Cost: <form:input path="cost"/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>