package com.springbackend.cbs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

 
    @ManyToOne
    @JoinColumn(name = "chef_id", referencedColumnName = "id") 
    private Chef chef;

   
    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id") 
    private Menu menu;

    public Admin() {}

    public Admin(Long id, String name, String email, String password, Chef chef, Menu menu) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.chef = chef;
        this.menu = menu;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Chef getChef() { return chef; }
    public void setChef(Chef chef) { this.chef = chef; }

    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }
}
