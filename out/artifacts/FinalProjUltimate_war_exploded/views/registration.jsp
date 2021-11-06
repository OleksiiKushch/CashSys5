<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CashSys.registration</title>
</head>
<body>
    <div>
        <h1>Employee Register Form</h1>
        <form action="registration" method="post">
            <table>
                <tr>
                    <td>Email: </td>
                    <td><label><input type="text" name="email" /></label></td>
                </tr>
                <tr>
                    <td>First Name: </td>
                    <td><label><input type="text" name="firstName" /></label></td>
                </tr>
                <tr>
                    <td>Middle Name: </td>
                    <td><label><input type="text" name="middleName" /></label></td>
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><label><input type="text" name="lastName" /></label></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><label><input type="password" name="password" /></label></td>
                </tr>
                <tr>
                    <td>Confirm password: </td>
                    <td><label><input type="password" name="confirmationPassword" /></label></td>
                </tr>
                <tr>
                    <td>Role: </td>
                    <td><div>
                            <input type="radio" id="roleChoice1" name="role" value="cashier">
                            <label for="roleChoice1">Cashier</label>
                            <input type="radio" id="roleChoice2" name="role" value="senior cashier">
                            <label for="roleChoice2">Senior cashier</label>
                            <input type="radio" id="roleChoice3" name="role" value="commodity expert">
                            <label for="roleChoice3">Commodity expert</label>
                    </div></td>
                </tr>
            </table>
            <input type="submit" value="Submit" />
        </form>
    </div>
</body>
</html>
