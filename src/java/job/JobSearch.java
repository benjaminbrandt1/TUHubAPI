/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package job;

import dbUtils.DbConn;
import dbUtils.FormatUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class JobSearch {
        //Returns a list of every job in the job table
    public static JobList getAllJobList(DbConn dbc, boolean activeOnly, String start, String numRows){
        JobList jobList = new JobList();
         try {

            // prepare (compiles) the SQL statement
            String sql = "SELECT job_id, user_id_job, date_posted, location, hours_per_week, description, title, pay, start_date, is_active, picture_key_job"
                    + " FROM job";
            if(activeOnly){
                sql += " WHERE is_active = 1;";
            } 
            
            sql += " LIMIT ?,? ORDER BY date_posted DESC;";
            

            
                     
           
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);

            pStatement.setInt(1,Integer.parseInt(numRows));
            pStatement.setInt(2, Integer.parseInt(start));
            
            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                String jobId = FormatUtils.formatInteger(results.getObject("job_id"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_job"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String location = FormatUtils.formatString(results.getObject("location"));              
                int hoursPerWeek = FormatUtils.formatIntegerReturnInt(results.getObject("hours_per_week"));
                String description = FormatUtils.formatString(results.getObject("description"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String pay = FormatUtils.formatDollar(results.getObject("pay"));
                String startDate = FormatUtils.formatDateYearFirst(results.getObject("start_date"));
                String isActive = FormatUtils.formatBoolean(results.getObject("is_active"));
                String pictureKey = FormatUtils.formatInteger(results.getObject("picture_key_job"));
                jobList.addOption(new Job(jobId, ownerId, datePosted, location, hoursPerWeek, description, title, pay, startDate, isActive, pictureKey));
            } 
            return jobList;
            
        } catch (SQLException e) {
            jobList.dbError = (e.toString());
            return jobList;
        }
    }
    
    //Returns a list of every user in the user_t table
    public static Job getJobById(DbConn dbc, int jobId){
        Job job = new Job();
         try {

            // prepare (compiles) the SQL statement
           String sql = "SELECT job_id, user_id_job, date_posted, location, hours_per_week, description, title, pay, start_date, is_active, picture_key_job"
                    + " FROM job WHERE job_id = ?";
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
            pStatement.setInt(1, jobId);
            
            ResultSet results = pStatement.executeQuery();
       
            if (results.next()) {
                job.setJobId(FormatUtils.formatInteger(results.getObject("job_id")));
                job.setOwnerId(FormatUtils.formatString(results.getObject("user_id_job")));
                job.setDatePosted(FormatUtils.formatDateYearFirst(results.getObject("date_posted")));
                job.setLocation(FormatUtils.formatString(results.getObject("location")));              
                job.setHoursPerWeek(FormatUtils.formatIntegerReturnInt(results.getObject("hours_per_week")));
                job.setDescription(FormatUtils.formatString(results.getObject("description")));
                job.setTitle(FormatUtils.formatString(results.getObject("title")));
                job.setPay(FormatUtils.formatDollar(results.getObject("pay")));
                job.setStartDate(FormatUtils.formatDateYearFirst(results.getObject("start_date")));
                job.setIsActive(FormatUtils.formatBoolean(results.getObject("is_active")));
                job.setPicFolder(FormatUtils.formatInteger(results.getObject("picture_key_job")));
                
            } else {
                job.setError("No job listing with that ID found.");
            }
            return job;
            
        } catch (SQLException e) {
            job.setError(e.toString());
            return job;
        }
    }
    
    public static JobList getJobsByUserId(DbConn dbc, String userId, String start, String numRows){
        JobList jobList = new JobList();
         try {

            // prepare (compiles) the SQL statement
           StringBuilder sql = new StringBuilder("SELECT job_id, user_id_job, date_posted, location, hours_per_week, description, title, pay, start_date, is_active, picture_key_job"
                    + " FROM job WHERE user_id_job = ? LIMIT ?,? ORDER BY date_posted DESC;");
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql.toString());
            
            pStatement.setString(1, userId);
            
         
            pStatement.setInt(2,Integer.parseInt(numRows));
            pStatement.setInt(3, Integer.parseInt(start));
            
            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                String jobId = FormatUtils.formatInteger(results.getObject("job_id"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_job"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String location = FormatUtils.formatString(results.getObject("location"));              
                int hoursPerWeek = FormatUtils.formatIntegerReturnInt(results.getObject("hours_per_week"));
                String description = FormatUtils.formatString(results.getObject("description"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String pay = FormatUtils.formatDollar(results.getObject("pay"));
                String startDate = FormatUtils.formatDateYearFirst(results.getObject("start_date"));
                String isActive = FormatUtils.formatBoolean(results.getObject("is_active"));
                String pictureKey = FormatUtils.formatInteger(results.getObject("picture_key_job"));
                jobList.addOption(new Job(jobId, ownerId, datePosted, location, hoursPerWeek, description, title, pay, startDate, isActive, pictureKey));
            } 
            return jobList;
            
        } catch (SQLException e) {
            jobList.dbError = (e.toString());
            return jobList;
        }
    }
    
    public static JobList searchActiveJobTitles(DbConn dbc, String titleKeyWord, String start, String numRows){
        JobList jobList = new JobList();
         try {

            String sql = "SELECT job_id, user_id_job, date_posted, location, hours_per_week, description, title, pay, start_date, is_active, picture_key_job"
                    + " FROM job WHERE is_active = 1"
                    + " AND title LIKE ? LIMIT ?,? ORDER BY date_posted DESC;";
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
            pStatement.setString(1, "%" + titleKeyWord + "%");
            pStatement.setInt(2,Integer.parseInt(numRows));
            pStatement.setInt(3, Integer.parseInt(start));
            
            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                String jobId = FormatUtils.formatInteger(results.getObject("job_id"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_job"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String location = FormatUtils.formatString(results.getObject("location"));              
                int hoursPerWeek = FormatUtils.formatIntegerReturnInt(results.getObject("hours_per_week"));
                String description = FormatUtils.formatString(results.getObject("description"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String pay = FormatUtils.formatDollar(results.getObject("pay"));
                String startDate = FormatUtils.formatDateYearFirst(results.getObject("start_date"));
                String isActive = FormatUtils.formatBoolean(results.getObject("is_active"));
                String pictureKey = FormatUtils.formatInteger(results.getObject("picture_key_job"));
                jobList.addOption(new Job(jobId, ownerId, datePosted, location, hoursPerWeek, description, title, pay, startDate, isActive, pictureKey));
            } 
            return jobList;
            
        } catch (SQLException e) {
            jobList.dbError = (e.toString());
            return jobList;
        }
    }
}
