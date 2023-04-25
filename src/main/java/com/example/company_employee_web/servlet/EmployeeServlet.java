package com.example.company_employee_web.servlet;

import com.example.company_employee_web.meneger.EmployeeManager;
import com.example.company_employee_web.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private final EmployeeManager employeeManager = new EmployeeManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = employeeManager.getAll();
        req.setAttribute("employeeList",employeeList);
        req.getRequestDispatcher("WEB-INF/employee.jsp").forward(req,resp);
    }
}
