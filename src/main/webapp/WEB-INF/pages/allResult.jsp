<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Result list</title>
</head>
<body>

<h2>result list</h2>
<table>
    <c:forEach var="list1" items="${list}">
    <tr>
        <td>User name = ${list1.name}</td>
        <td>User sum points = ${list1.total_points}</td>
    </tr>
    </c:forEach>

</body>
</html>