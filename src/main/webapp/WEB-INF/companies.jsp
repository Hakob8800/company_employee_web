<%@ page import="com.example.company_employee_web.model.Company" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Companies</title>
</head>
<% List<Company> companies = (List<Company>) request.getAttribute("companies");%>
<body>
<h2>
    Companies
</h2>
<a href="/createCompany">Create company</a><br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Country</th>
        <th>Action</th>
    </tr>
    <% if(companies!=null) { %>
    <% for (Company company : companies) { %>

    <tr>
        <td> <%= company.getId() %> </td>
        <td> <%= company.getName() %> </td>
        <td> <%= company.getCountry() %> </td>
        <td> <a href="/deleteCompany?id=<%=company.getId()%>">Delete</a>/
            <a href="/updateCompany?id=<%=company.getId()%>">Update</a>
        </td>

    </tr>

    <% } %>
    <% } %>

</table><br>
<a href="index.jsp">back</a>
</body>
</html>