<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Operation</title>
</head>
<body>

<h2>Enter your answer</h2>
<form:form method="post" action="result">
    <table>
        <tr>

            <td>Question = ${question1};     </td>
            <td>Answer = </td>
            <td><form:input path="answer" /></td>
        </tr>

        <td colspan="2">
            <input type="submit" value="Next question"/>
        </td>

        </tr>
    </table>
</form:form>
<tr>
    <li><a href="/stopTest">Stop Test</a></li>
    <li><a href="/allResult">All result</a></li>
</tr>
</body>
</html>