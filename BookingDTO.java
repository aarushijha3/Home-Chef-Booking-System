package com.springbackend.cbs.dto;

public class BookingDTO {

    private Long id;
    private Long userId;       
    private Long chefId;       
    private Long menuId;
    private String date;

    public BookingDTO(Long id, Long userId, Long chefId, Long menuId, String date) {
		super();
		this.id = id;
		this.userId = userId;
		this.chefId = chefId;
		this.menuId = menuId;
		this.date = date;
	}

	public BookingDTO() {
		super();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
