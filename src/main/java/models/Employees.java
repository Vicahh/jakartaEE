package models;


import jakarta.persistence.*;

import java.util.Set;

import static jakarta.persistence.CascadeType.*;

    @Entity
    @Table(name = "employers")
    public class Employees {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "first_name", nullable = false, length = 150)
        private String FirstName;
        @Column(name = "last_name", nullable = false, length = 150)
        private String LastName;
        @Column(name = "email", nullable = false, length = 150, unique = true)
        private String Email;
        @Column(name = "password", nullable = false, length = 150)
        private String Password;
        @Column(name = "role", nullable = false, length = 150)
        private String Role;
        @Column(name = "description", nullable = true, length = 150)
        private String Description;
        @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
        private Set<Transaction> transactions;;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getFirstName() {
            return FirstName;
        }
        public void setFirstName(String firstName) {
            FirstName = firstName;
        }
        public String getLastName() {
            return LastName;
        }
        public void setLastName(String lastName) {
            LastName = lastName;
        }
        public String getEmail() {
            return Email;
        }
        public void setEmail(String email) {
            Email = email;
        }
        public String getPassword() {
            return Password;
        }
        public void setPassword(String password) {
            Password = password;
        }
        public String getRole() {
            return Role;
        }
        public void setRole(String role) {
            Role = role;
        }
        public Set<Transaction> getTransactions() {
            return transactions;
        }
        public void setTransactions(Set<Transaction> transactions) {
            this.transactions = transactions;
        }
        public String getDescription() {
            return Description;
        }
        public void setDescription(String description) {
            Description = description;
        }
    }

