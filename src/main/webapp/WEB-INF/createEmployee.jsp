<%@ page import="com.example.company_employee_web.model.Company" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Employee</title>
</head>
<% List<Company> companies = (List<Company>) request.getAttribute("companies");%>
<body>
<h2>
   Create Employee
</h2>
<form action="/createEmployee" method="post">

    name:<input type="text" name="name"><br>
    surname:<input type="text" name="surname"><br>
    email:<input type="text" name="email"><br>
    <select name="company">
        <%for (Company company : companies) { %>
        <option value="<%=company.getId()%>"> <%=company.getName()%> </option>
          <% } %>
    </select><br>
    <input type="submit" value="create">
</form>
<a href="/employees">back</a>
</body>
</html>