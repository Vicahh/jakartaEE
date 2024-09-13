package models;


import jakarta.persistence.*;

    @Entity
    @Table(name = "produit")
    public class Products {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "name", nullable = false,length = 100, unique = true)
        private String name;

        @Column(name = "description", nullable = true,length = 300)
        private String description;

        @Column(name = "quantite", nullable = false)
        private int number;

        public int getId() {return id;}

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }



