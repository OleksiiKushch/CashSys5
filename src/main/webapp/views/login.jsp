<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CashSys.login</title>
</head>
<body>
<div>
    <h1>Employee Login Form</h1>
    <form action="login" method="post">
        <table>
            <tr>
                <td>Email: </td>
                <td><label><input type="text" name="email" /></label></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><label><input type="password" name="password" /></label></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
