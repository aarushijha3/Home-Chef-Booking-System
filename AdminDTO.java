package com.springbackend.cbs.dto;

public class AdminDTO {
    private Long id;
    private String name;
    private String email;
    private Long chefId;
    private Long menuId;
    private String password;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getChefId() {
        return chefId;
    }
    public void setChefId(Long chefId) {
        this.chefId = chefId;
    }

    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
