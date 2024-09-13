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

@WebServlet(name = "supprimerServlet", value = "/SupprimerServlet")
public class SupprimerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String modal_suppr = "true";
        System.out.println(id);
        request.setAttribute("id", id);
        ProductRepo repo = new ProductRepo();
        Products products = repo.find(id);

        List<Products> liste = repo.liste();
        request.setAttribute("liste", liste);

        request.setAttribute("action_suppr", modal_suppr);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductRepo repo = new ProductRepo();
        if(repo.nombre_valide( request.getParameter("nombre_supprimer"))){
            String nombre_supprimer = request.getParameter("nombre_supprimer");
            int nbre = Integer.parseInt(nombre_supprimer);
            String description_supprimer = request.getParameter("description_supprimer");
            String id_suppr = request.getParameter("id_suppr");

            Products products = repo.find(Integer.parseInt(id_suppr));
            int nbrebd = products.getNumber();
            nbrebd -= nbre;
            if(nbrebd >= 0 ) {
                if (nbrebd == 0){
                    repo.delete(Integer.parseInt(id_suppr));
                }
                else{
                    products.setNumber(nbrebd);
                    repo.update(products);

                    /*++++++++++++++++++++++++++++++++++++++Transaction+++++++++++++++++++++++++++++++++++*/
                    HttpSession session = request.getSession();
                    EmployeeRepo employeeRepo = new EmployeeRepo();
                    Employees employees = employeeRepo.find((Long) session.getAttribute("id_user"));
                    TransactionRepo tr = new TransactionRepo();
                    tr.Transaction_suppr(employees,repo.find(Integer.parseInt(id_suppr)), nbre,description_supprimer );
                    /*++++++++++++++++++++++++++++++++++++++Transaction+++++++++++++++++++++++++++++++++++*/
                }
                request.setAttribute("action_suppr","false");
                response.sendRedirect("Update");
                return;

            }
            else{
                request.setAttribute("erreur_suppr","trop peu de produit");
            }
        }
        else {
            request.setAttribute("erreur_suppr", "nombre de produit non valide ");
        }
        List<Products> liste = repo.liste();
        request.setAttribute("liste", liste);
        request.getRequestDispatcher("/accueil.jsp").forward(request, response);
        }

}
