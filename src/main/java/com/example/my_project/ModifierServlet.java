package com.example.my_project;

import Repo.EmployeeRepo;
import Repo.ProductRepo;
import Repo.TransactionRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Employees;
import models.Products;
import models.Transaction;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "modifierServlet", value = "/ModifierServlet")
public class ModifierServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String modal_modif = "true";
        request.setAttribute("id", id);
        ProductRepo repo = new ProductRepo();
        Products products = repo.find(id);
        List<Products> liste = repo.liste();
        request.setAttribute("liste", liste);
        request.setAttribute("action_modif", modal_modif);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductRepo repo = new ProductRepo();
        if (repo.format_validate((String)request.getParameter("nom_modif"),(String)request.getParameter("nombre_modif"))){
            String name = request.getParameter("nom_modif");
            String nombre_modif = request.getParameter("nombre_modif");
            int nbre = Integer.parseInt(nombre_modif);
            String description_modif = request.getParameter("description_modif");
            String id = request.getParameter("id_modif");
            Products products = repo.create(name,nbre, description_modif);
            products.setId(Integer.parseInt(id));
            repo.update(products);
            request.setAttribute("action_modif","false");

            /*++++++++++++++++++++++++++++++++++++++Transaction+++++++++++++++++++++++++++++++++++*/
            HttpSession session = request.getSession();
            EmployeeRepo employeeRepo = new EmployeeRepo();
            Employees employees = employeeRepo.find((Long) session.getAttribute("id_user"));
            TransactionRepo tr = new TransactionRepo();
            tr.Transaction_modif(employees,repo.find(Integer.parseInt(id)), repo.compare(repo.find(Integer.parseInt(id)),products),description_modif );
            /*++++++++++++++++++++++++++++++++++++++Transaction+++++++++++++++++++++++++++++++++++*/
            response.sendRedirect("Update");
        }
        else{
            request.setAttribute("erreur_modif", "nom ou le nombre de produit non valide ");
            List<Products> liste = repo.liste();
            request.setAttribute("liste", liste);
            request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }
        }

}
