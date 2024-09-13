package Repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import models.Employees;

public class EmployeeRepo {
    private EntityManager entityManager;
    private EntityManagerFactory emf;
    private String acte = "Ajout";
    public EmployeeRepo() {
        this.emf = Persistence.createEntityManagerFactory("stock");
        this.entityManager = this.emf.createEntityManager();
    }
    public Employees add(Employees employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        return employee;
    }
    public Employees find(Long id) {
        return entityManager.find(Employees.class, id);
    }
    public Employees update(Employees employee) {
        Employees employeeModif = find(employee.getId());
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        employeeModif.setDescription(employee.getDescription());
        employeeModif.setFirstName(employee.getFirstName());
        employeeModif.setLastName(employee.getLastName());
        employeeModif.setEmail(employee.getEmail());
        employeeModif.setRole(employee.getRole());
        entityManager.getTransaction().commit();
        return employeeModif;
    }
    public Long getLastP() {
        String jpql = "SELECT MAX(p.id) FROM Transaction p";
        Query query =  entityManager.createQuery(jpql);
        Long lastId = (Long) query.getSingleResult();
        return lastId != null ? lastId : 0;  // Retourne 0 si aucun produit n'existe
    }
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(find(id));
        entityManager.getTransaction().commit();
    }
    public Long FindUseName(String name) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select p.id from Employees p where p.FirstName = :name");
        query.setParameter("name", name);
        Long result = (Long) query.getSingleResult();
        return result != null ? result : -1L;
    }
    public boolean ConnectionSuccess(String psswd, String passwd){
        return psswd.equals(passwd);
    }
    public Long FindIdUsegmail(String gmail) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select p.id from Employees p where p.Email = :name");
        query.setParameter("name", gmail);
        Long result = (Long) query.getSingleResult();
        return result != null ? result : -1L;
    }
    public boolean UserExist(String name) {
        Long trouve = FindUseName(name);
        if (trouve != -1L) {
            return true;
        }
        return false;
    }
    public String passwd(String name) {
        Long id = FindUseName(name);
        return find(id).getPassword();
    }
    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}