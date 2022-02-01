<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Input user</title>
</head>
<body>

<h2>Input user</h2>

<form:form method="post" action="findUser">
    <table>
        <tr>
            <td>Input user name</td>
            <td><form:input path="name" /></td>
        </tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
        </tr>
    </table>
</form:form>

<li><a href="/allResult">All result</a></li>

</body>
</html>