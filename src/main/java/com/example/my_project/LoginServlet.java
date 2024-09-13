package com.example.my_project;

import Repo.EmployeeRepo;
import Repo.ProductRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Employees;
import models.Products;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/LoginServlet")
public class LoginServlet  extends HttpServlet {
    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getRequestDispatcher("/index.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String gmail = (String)request.getParameter("gmail");
        String password = (String)request.getParameter("password");
        EmployeeRepo employeeRepo = new EmployeeRepo();
        String erreur;
        try {
            Long id = employeeRepo.FindIdUsegmail(gmail);
            if (id >= 0) {
                Employees employee = employeeRepo.find(id);
            if (employeeRepo.ConnectionSuccess(employee.getPassword(), password) == true) {
                    HttpSession session = request.getSession();
                    session.setAttribute("id_user", id);
                    request.setAttribute("erreur", "connection reussie");
                    response.sendRedirect("Update");;
                }
            else {
                    employeeRepo.close();
                    request.setAttribute("erreur", "mot de passe erronne");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }
            else {
                employeeRepo.close();
                request.setAttribute("erreur", "user non trouve");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        }catch (Exception e){
            request.setAttribute("message", "Une erreur s'est produite : " + e.getMessage());
            employeeRepo.close();
            request.setAttribute("erreur", "Utilisateur non existant");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }
    public void destroy() {
    }
}
