package com.springbackend.cbs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chef_id", nullable = false)
    @JsonBackReference
    private Chef chef;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonBackReference
    private Menu menu;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Billing billing;

    // Constructors
    public Booking() {}

    public Booking(Long id, String date, User user, Chef chef, Menu menu) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.chef = chef;
        this.menu = menu;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    // toString
    @Override
    public String toString() {
        return "Booking [id=" + id + ", date=" + date + ", user=" + user.getId() +
               ", chef=" + chef.getId() + ", menu=" + menu.getId() + "]";
    }
}
