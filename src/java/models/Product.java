/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rogeliotorres
 */
public class Product {
    private int id;
    private int departmentId;
    private String departmentName;
    private String name;
    private String description;
    private float price;
    private int quantity;
    private boolean active;
    private String imageUrl;
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product(){};

    public Product(int id, int departmentId, String departmentName, String name, String description, float price, int quantity, boolean active, String imageUrl, int cantidad) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.active = active;
        this.imageUrl = imageUrl;
        this.cantidad = cantidad;
    }

    
    
    public Product(int id, int departmentId, String departmentName, String name, String description, float price, int quantity, boolean active, String imageUrl) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.active = active;
        this.imageUrl = imageUrl;
    }
    
    public Product(int id, int departmentId, String departmentName, String name, String description, float price, int quantity, boolean active) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity ( int quantity )
    {
        this.quantity = quantity;
    }

    public boolean isActive ()
    {
        return active;
    }

    public void setActive ( boolean active )
    {
        this.active = active;
    }
    
}
