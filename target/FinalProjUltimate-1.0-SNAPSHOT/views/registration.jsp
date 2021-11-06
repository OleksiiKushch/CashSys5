<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <div>
        <h1>Employee Register Form</h1>
        <form action="/registration" method="post">
            <table>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" /></td>
                </tr>
                <tr>
                    <td>Middle Name</td>
                    <td><input type="text" name="middleName" /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td><input type="radio" name="role" value="cashier" /></td>
                    <td><input type="radio" name="role" value="senior cashier" /></td>
                    <td><input type="radio" name="role" value="commodity expert" /></td>
                </tr>
            </table>
            <input type="submit" value="Submit" />
        </form>
    </div>
</body>
</html>
