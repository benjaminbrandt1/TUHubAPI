/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package job;

/**
 *
 * @author Ben
 */
public class Job {
    
    private String jobId = "";
    private String ownerId = "";
    private String datePosted = "";
    private String location = "";
    private int hoursPerWeek = -1;
    private String description = "";
    private String title = "";
    private String pay = "";
    private String startDate = "";
    private String isActive = "";
    private String picFolder = "";
    private String error = "";

    public Job() {
    }
    
    public Job(String jobId, String ownerId, String datePosted, String location, int hoursPerWeek, String description, String title, 
            String pay, String startDate, String isActive, String picFolder){
        this.jobId = jobId;
        this.ownerId = ownerId;
        this.datePosted = datePosted; 
        this.location = location;
        this.hoursPerWeek = hoursPerWeek;
        this.description = description;
        this.title = title;
        this.pay = pay;
        this.startDate = startDate;
        this.isActive = isActive;
        this.picFolder = picFolder;
    }
    
    public boolean isEmpty(){
        String allFields = jobId + ownerId + datePosted + location + description + title + pay + startDate + isActive + picFolder;
        return (allFields.length() == 0);
    }
    
    @Override
    public String toString(){
        return "Id: " + jobId
                + " Owner: " + ownerId
                + " Posted: " + datePosted
                + " Location: " + location
                + " Hours: " + String.valueOf(hoursPerWeek)
                + " description: " + description
                + " title: " + title
                + " pay: " + pay
                + " start: " + startDate
                + " active: " + isActive
                + " picFolder: " + picFolder
                + " error: " + error;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
}
