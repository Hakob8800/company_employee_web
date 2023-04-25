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

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
 private final CompanyManager companyManager = new CompanyManager();
 private final EmployeeManager employeeManager = new EmployeeManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeManager.getById(id);
        List<Company> companies = companyManager.getAll();

        req.setAttribute("employee",employee);
        req.setAttribute("companies",companies);
        req.getRequestDispatcher("WEB-INF/updateEmployee.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        Company company = companyManager.getById(Integer.parseInt(req.getParameter("company")));

        Employee employee = new Employee(id,name,surname,email,company);

        employeeManager.update(employee);
        resp.sendRedirect("/employees");
    }
}
