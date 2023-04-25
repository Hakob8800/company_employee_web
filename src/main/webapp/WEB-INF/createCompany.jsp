
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Company</title>
</head>
<body>
<h2>
   Create Company
</h2>
<form action="/createCompany" method="post">

    name:<input type="text" name="name"><br>
    country:<input type="text" name="country"><br>
    <input type="submit" value="create">
</form>
<a href="/companies">back</a>
</body>
</html>