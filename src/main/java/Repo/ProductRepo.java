package Repo;

import jakarta.persistence.*;
import models.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepo {
    private EntityManager entityManager;
    private EntityManagerFactory emf;
    public ProductRepo() {
        this.emf = Persistence.createEntityManagerFactory("stock");
        this.entityManager = this.emf.createEntityManager();
    }

    public Products create(String name, int number, String description){
        Products products = new Products();
        products.setName(name);
        products.setNumber(number);
        products.setDescription(description);
        return products;
    }
    public Products add(Products product) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return product;
    }
    public Products find(int id) {
        return entityManager.find(Products.class, id);
    }

    public Products update(Products product) {
        Products productModif = find(product.getId());
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        productModif.setNumber(product.getNumber());
        productModif.setName(product.getName());
        productModif.setDescription(product.getDescription());
        entityManager.getTransaction().commit();
        return  productModif;
    }

    public void delete(int id) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        // Suppression des transactions liées au produit
        Query query = entityManager.createQuery("DELETE FROM Transaction t WHERE t.produit.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        // Suppression du produit
        entityManager.remove(find(id));
        entityManager.getTransaction().commit();
    }

    public boolean nom_valide(String name){
        return name != null && !name.isEmpty();
    }


    public boolean format_validate(String name, String number){
        return nom_valide(name) && nombre_valide(number);
    }
    public boolean nombre_valide(String number) {
        return number != null && !number.isEmpty() && number.matches("\\d+");
    }

    public boolean exist(String name) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        try {
            Query query = entityManager.createQuery("select p.id from Products p where p.name = :name");
            query.setParameter("name", name);
            query.getSingleResult(); // Si cette ligne ne déclenche pas d'exception, l'employé existe
            return true;
        } catch (NoResultException e) {
            return false; // Si aucune résultat n'est trouvé, retourne false
        }
    }

    public List<Products> liste(){
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        Query query = entityManager.createQuery("select p from Products p");
        return query.getResultList();
    }
    public int getLastP() {
        String jpql = "SELECT MAX(p.id) FROM Transaction p";
        Query query =  entityManager.createQuery(jpql);
        Integer lastId = (Integer) query.getSingleResult();
        return lastId != null ? lastId : 0;  // Retourne 0 si aucun produit n'existe
    }

    public String compare(Products p1, Products p2) {
        StringBuilder differences = new StringBuilder();
            if (!p1.getName().equals(p2.getName())) {
                differences.append(" du nom de ").append(p1.getName())
                        .append(" en ").append(p2.getName()).append(", ");
            }
            if (p1.getNumber() != p2.getNumber()) {
                differences.append(" du nombre ").append(p1.getNumber())
                        .append(" en ").append(p2.getNumber()).append(", ");
            }
            // Retirer la dernière virgule et l'espace, si nécessaire
            if (differences.length() > 0) {
                differences.setLength(differences.length() - 2);
            } else {
                return "Aucun changements";
            }
            return differences.toString();
        }
    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}