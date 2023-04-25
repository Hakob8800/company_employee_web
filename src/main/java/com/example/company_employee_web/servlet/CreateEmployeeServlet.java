package com.example.company_employee_web.servlet;

import com.example.company_employee_web.meneger.CompanyManager;
import com.example.company_employee_web.meneger.EmployeeManager;
import com.example.company_employee_web.model.Company;
import com.example.company_employee_web.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createEmployee")
public class CreateEmployeeServlet extends HttpServlet {
    private final EmployeeManager employeeManager = new EmployeeManager();
    private final CompanyManager companyManager = new CompanyManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = companyManager.getAll();
        req.setAttribute("companies",companies);
        req.getRequestDispatcher("/WEB-INF/createEmployee.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int companyId = Integer.parseInt(req.getParameter("company"));

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setEmail(email);
        employee.setCompany(companyManager.getById(companyId));
        employeeManager.save(employee);
        resp.sendRedirect("/employees");
    }
}
