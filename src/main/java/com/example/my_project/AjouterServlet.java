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


@WebServlet(name = "ajouterServlet", value = "/AjouterServlet")
public class AjouterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String modal_ajout = "true";
        System.out.println(id);
        request.setAttribute("id", id);
        ProductRepo repo = new ProductRepo();
        Products products = repo.find(id);
        List<Products> liste = repo.liste();
        request.setAttribute("liste", liste);
        request.setAttribute("action_ajout", modal_ajout);
        request.setAttribute("products", products);
        repo.close();
        request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductRepo repo = new ProductRepo();
        if(repo.nombre_valide( request.getParameter("nombre_ajouter"))){
            String nombre_ajouter = (String) request.getParameter("nombre_ajouter");
            int nbre = Integer.parseInt(nombre_ajouter);
            String description_ajouter = request.getParameter("description_ajouter");
            String id_ajout = request.getParameter("id_ajouter");
            int id = Integer.parseInt(id_ajout);
            Products products = repo.find(Integer.parseInt(id_ajout));
            nbre += products.getNumber();
            products.setNumber(nbre);
            repo.update(products);

            /*++++++++++++++++++++++++++++++++++++++Transaction+++++++++++++++++++++++++++++++++++*/
            HttpSession session = request.getSession();
            EmployeeRepo employeeRepo = new EmployeeRepo();
            Employees employees = employeeRepo.find((Long) session.getAttribute("id_user"));
            TransactionRepo tr = new TransactionRepo();
            tr.Transaction_ajout(employees,repo.find(id),nbre, description_ajouter);
            /*++++++++++++++++++++++++++++++++++++++/Transaction+++++++++++++++++++++++++++++++++++*/


            request.setAttribute("action_ajout","false");
            repo.close();
            employeeRepo.close();
            tr.close();
            response.sendRedirect("Update");
        }
        else {
            List<Products> liste = repo.liste();
            request.setAttribute("liste", liste);
            repo.close();
            request.setAttribute("erreur_ajout", "nombre de produit non valide ");
            request.getRequestDispatcher("/accueil.jsp").forward(request, response);
        }

    }
}


