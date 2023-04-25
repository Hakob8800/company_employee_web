<%@ page import="com.example.company_employee_web.model.Company" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Companies</title>
</head>
<% Company company = (Company) request.getAttribute("company"); %>
<body>
<h2>
   Update Company
</h2>
<form action="/updateCompany" method="post">
    <input type="hidden" name="id" value="<%= company.getId() %>">
    name:<input type="text" name="name" value="<%= company.getName()%>"> <br>
    country:<input type="text" name="country" value="<%= company.getCountry()%>"><br>
    <input type="submit" value="update">
</form>
<a href="/companies">back</a>
</body>
</html>