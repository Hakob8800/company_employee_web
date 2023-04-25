<%@ page import="com.example.company_employee_web.model.Company" %>
<%@ page import="com.example.company_employee_web.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Companies</title>
</head>
<% Employee employee = (Employee) request.getAttribute("employee"); %>
<% List<Company> companies = (List<Company>) request.getAttribute("companies"); %>
<body>
<h2>
   Update Employee
</h2>
<form action="/updateEmployee" method="post">
    <input type="hidden" name="id" value="<%= employee.getId() %>">
    name:<input type="text" name="name" value="<%= employee.getName()%>"> <br>
    surname:<input type="text" name="surname" value="<%= employee.getSurname()%>"><br>
    email:<input type="text" name="email" value="<%= employee.getEmail()%>"><br>
    <select name="company">
        <%for (Company company : companies) { %>
        <option value="<%=company.getId()%>"> <%=company.getName()%> </option>
        <% } %>
    </select><br>
    <input type="submit" value="update">
</form>
<a href="/employees">back</a>
</body>
</html>