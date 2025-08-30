package com.springbackend.cbs.dto;

public class MenuDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Long chefId;

    // Constructors
    public MenuDTO() {}

    public MenuDTO(Long id, String name, String description, double price, String imageUrl, Long chefId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.chefId = chefId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Long getChefId() { return chefId; }
    public void setChefId(Long chefId) { this.chefId = chefId; }
}
