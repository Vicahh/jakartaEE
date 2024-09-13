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
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addServlet", value = "/AddServlet")
public class AddServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String modal_add = "true";
        ProductRepo repo = new ProductRepo();
        List<Products> liste = repo.liste();
        request.setAttribute("liste", liste);
        request.setAttribute("action_add", modal_add);
        repo.close();
        request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductRepo productRepo = new ProductRepo();
        if (productRepo.format_validate((String)request.getParameter("NameAdd"),(String)request.getParameter("NumberAdd"))) {
            EmployeeRepo employeeRepo = new EmployeeRepo();
            String name = request.getParameter("NameAdd");
            int number = Integer.parseInt(request.getParameter("NumberAdd"));
            String description = request.getParameter("DescriptionAdd");
            if(! productRepo.exist(name)){
                    Products product = productRepo.create(name, number,description);
                    productRepo.add(product);
                    TransactionRepo t = new TransactionRepo();
                    HttpSession session = request.getSession();
                    Employees employees = employeeRepo.find((Long)session.getAttribute("id_user"));
                    t.Transaction_add(employees,product, name, number, description);
                    List<Products> liste = productRepo.liste();
                    request.setAttribute("liste", liste);
                    request.getRequestDispatcher("/accueil.jsp").forward(request, response);
                }
            else{
                    request.setAttribute("erreur_add", "produit existe deja");
                    doGet(request, response);
                }
            }
        else {
                request.setAttribute("erreur_add", "nom ou le nombre de produit non valide ");
                doGet(request, response);
            }
        }
}




