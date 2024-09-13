package com.example.my_project;


import Repo.TransactionRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Transaction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "historique", value = "/Historique")
public class Historique extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TransactionRepo repot = new TransactionRepo();
        List<Transaction> l = repot.liste();
        request.setAttribute("historique", l);
        request.getRequestDispatcher("/historique.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nom = request.getParameter("nom");
        TransactionRepo tr = new TransactionRepo();
        List<Transaction> l = tr.Recherchernom(nom);
        request.setAttribute("historique", l);
        request.setAttribute("value", nom);
        request.getRequestDispatcher("/historique.jsp").forward(request, response);
    }

}
