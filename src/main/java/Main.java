import Repo.EmployeeRepo;
import models.Employees;

public class Main {
    public static void main(String[] args) {
        EmployeeRepo repo = new EmployeeRepo();
        Employees employees = new Employees();
        employees.setFirstName("Vicah");
        employees.setLastName("TOJONIRINA");
        employees.setEmail("vicah@gmail.com");
        employees.setPassword("vicah");
        employees.setRole("Manager");
        repo.add(employees);
        repo.close();
    }
}
