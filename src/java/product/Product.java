/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package product;

/**
 *
 * @author Ben
 */
public class Product {
    private String productId = "";
    private String title="";
    private String description="";
    private String price = "";
    private String isActive = "";
    private String ownerId = "";
    private String datePosted = "";
    private String picFolder = "";
    private String error = "";

    public Product(){
        
    }
    
    public Product(String productId, String title, String description, String price, String isActive, String ownerId, String datePosted, String picFolder) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.ownerId = ownerId;
        this.datePosted = datePosted;
        this.picFolder = picFolder;
    }

    public boolean isEmpty(){
        String allFields = productId + title + description + price + isActive + ownerId + datePosted + picFolder + error;
        return (allFields.length() == 0);
    }
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicFolder() {
        return picFolder;
    }

    public void setPicFolder(String picFolder) {
        this.picFolder = picFolder;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
