<%@ page import="com.example.company_employee_web.model.Company" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.company_employee_web.model.Employee" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eployees</title>
</head>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employeeList");%>
<body>
<h2>
    Employees
</h2>
<a href="/createEmployee">Create employee</a><br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Company</th>
        <th>Action</th>
    </tr>
    <% if(employees!=null) { %>
    <% for (Employee employee : employees) { %>

    <tr>
        <td> <%= employee.getId() %> </td>
        <td> <%= employee.getName() %> </td>
        <td> <%= employee.getSurname() %> </td>
        <td> <%= employee.getEmail() %> </td>
        <td> <%= employee.getCompany().getName() %> </td>
        <td> <a href="/deleteEmployee?id=<%=employee.getId()%>">Delete</a>/
            <a href="/updateEmployee?id=<%=employee.getId()%>">Update</a>
        </td>

    </tr>

    <% } %>
    <% } %>

</table><br>
<a href="index.jsp">back</a>
</body>
</html>