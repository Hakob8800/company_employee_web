package com.example.company_employee_web.servlet;

import com.example.company_employee_web.meneger.CompanyManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deleteCompany")
public class DeleteCompanyServlet extends HttpServlet {
    private final CompanyManager companyManager = new CompanyManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        companyManager.removeById(id);
        resp.sendRedirect("/companies");
    }
}
