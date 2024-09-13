package com.example.my_project;


import Repo.ProductRepo;
import Repo.TransactionRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Products;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "update", value = "/Update")
public class Update extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TransactionRepo repo = new TransactionRepo();
        ProductRepo productRepo = new ProductRepo();
        request.setAttribute("message", "Login de passe incorrecte");
        List<Products> liste = productRepo.liste();
        request.setAttribute("liste", liste);
        productRepo.close();
        request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }
}
