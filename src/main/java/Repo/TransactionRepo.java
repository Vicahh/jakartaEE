package Repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import models.Employees;
import models.Products;
import models.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TransactionRepo {
    private EntityManager Emg;
    private EntityManagerFactory Emf;

    public TransactionRepo() {
        this.Emf = Persistence.createEntityManagerFactory("stock");
        this.Emg = this.Emf.createEntityManager();
    }
    public Transaction add_TransP(Transaction transaction) {
        Emg.getTransaction().begin();
        Emg.persist(transaction);
        Emg.getTransaction().commit();
        return transaction;
    }
    public Long getLastP() {
        String jpql = "SELECT MAX(p.id) FROM Transaction p";
        Query query = Emg.createQuery(jpql);
        Long lastId = (Long) query.getSingleResult();
        return lastId != null ? lastId : 0;  // Retourne 0 si aucun produit n'existe
    }
    public List<Transaction> Recherchernom(String prefix) {
        String jpql = "SELECT p FROM Transaction p WHERE p.produit.name" +
                " LIKE :prefix  order by p.id desc";
        Query query = Emg.createQuery(jpql);
        query.setParameter("prefix", prefix);
        return query.getResultList();
    }
    public Transaction finds(Long id) {
        return Emg.find(Transaction.class, id);
    }

    public void Transaction_modif(Employees employee, Products p,  String texte, String description){
        String txt = texte;
        if (!Objects.equals(texte, "Aucun changements")){
            txt = ("Changements "+ texte  + " par "+ employee.getFirstName() );
        }
        Transaction t =  create(employee,p,txt, "Modifiction" ,description);
        add_TransP(t);
    }

    public void Transaction_suppr(Employees employee, Products p, int number, String description){
        String texte = "Suppression de " + number+ ' ' + p.getName() + " par "+ employee.getFirstName();
        Transaction t =  create(employee,p,texte, "creation" ,description);
        add_TransP(t);
    }

    public void Transaction_ajout(Employees employee, Products p,  int number, String description){
        String texte = "Ajout de " + number+ ' ' + p.getName() + " par "+ employee.getFirstName();
        Transaction t =  create(employee,p,texte, "ajout" ,description);
        add_TransP(t);
    }

    public void Transaction_add(Employees employee, Products p, String name, int number, String description){
        String texte = "Creation de " + number+ ' ' + name + " par "+ employee.getFirstName();
        Transaction t =  create(employee,p,texte, "creation" ,description);
        add_TransP(t);
    }

    public Transaction create(Employees employee, Products p, String texte , String action,String description){
        Transaction t = new Transaction();
        t.setDate(GetDate());
        t.setHeure(GetHeure());
        t.setAction(action);
        t.setDescription(description);
        t.setProduit(p);
        t.setEmployer(employee);
        t.setTexte(texte) ;
        return t;
    }
    public String GetDate(){
        LocalDateTime maintenant = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/u");
        return maintenant.format(format);
    }
    public List<Transaction> liste(){
        Emg.getTransaction().begin();
        Query query = Emg.createQuery("select p from Transaction p order by p.id desc ");
        return query.getResultList();
    }

    public String GetHeure(){
        LocalDateTime maintenant = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("H:m:s");
        return maintenant.format(format);
    }
    public void close(){
        this.Emg.close();
        this.Emf.close();
    }
}
