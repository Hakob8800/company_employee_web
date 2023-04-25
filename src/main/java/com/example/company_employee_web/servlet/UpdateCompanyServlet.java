package com.example.company_employee_web.servlet;

import com.example.company_employee_web.meneger.CompanyManager;
import com.example.company_employee_web.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCompany")
public class UpdateCompanyServlet extends HttpServlet {
 private final CompanyManager companyManager = new CompanyManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Company company = companyManager.getById(id);
        req.setAttribute("company",company);
        req.getRequestDispatcher("WEB-INF/updateCompany.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        int id = Integer.parseInt(req.getParameter("id"));
        Company company = new Company(id,name,country);
        companyManager.update(company);
        resp.sendRedirect("/companies");
    }
}
