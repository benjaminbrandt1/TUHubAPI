/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personal;

/**
 *
 * @author Ben
 */
public class Personal {
    private String personalId = "";
    private String ownerId = "";
    private String title = "";
    private String description = "";
    private String location = "";
    private String datePosted = "";
    private String isActive = "";
    private String picFolder = "";
    private String error = "";

    public Personal() {
    }
    
    public Personal (String personalId, String ownerId, String title, String description, String location, String datePosted
    , String isActive, String picFolder){
        this.personalId = personalId;
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.datePosted = datePosted;
        this.isActive = isActive;
        this.picFolder = picFolder;
    }
    
    public boolean isEmpty(){
        String allFields = personalId + ownerId + title + description + location + datePosted + isActive + picFolder + error;
        return allFields.length() == 0;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPicFolder() {
        return picFolder;
    }

    public void setPicFolder(String picFolder) {
        this.picFolder = picFolder;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
