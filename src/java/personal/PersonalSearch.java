/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personal;

import dbUtils.DbConn;
import dbUtils.FormatUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class PersonalSearch {
        //Returns a list of every personal in the personal table
    public static PersonalList getAllPersonalList(DbConn dbc, boolean activeOnly, String start, String numRows){
        PersonalList personalList = new PersonalList();
         try {

            // prepare (compiles) the SQL statement
            String sql = "SELECT personal_id, user_id_personal, title, description, location, date_posted, is_active, picture_key_personal"
                    + " FROM personal";
            if(activeOnly){
                sql += " WHERE is_active = 1";
            } 
            
            sql += " LIMIT ? OFFSET ?;";
            
                     
           
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
             pStatement.setInt(1,Integer.parseInt(numRows));
            pStatement.setInt(2, Integer.parseInt(start));

            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                String personalId = FormatUtils.formatInteger(results.getObject("personal_id"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_personal"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String description = FormatUtils.formatString(results.getObject("description"));                
                String location = FormatUtils.formatString(results.getObject("location"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String isActive = FormatUtils.formatBoolean(results.getObject("is_active"));
                String pictureKey = FormatUtils.formatInteger(results.getObject("picture_key_personal"));
                personalList.addOption(new Personal(personalId, ownerId, title, description, location, datePosted, isActive, pictureKey));
            } 
            return personalList;
            
        } catch (SQLException e) {
            personalList.dbError = (e.toString());
            return personalList;
        }
    }
    
    //Returns a list of every user in the user_t table
    public static Personal getPersonalById(DbConn dbc, int personalId){
        Personal personal = new Personal();
         try {

            // prepare (compiles) the SQL statement
           String sql = "SELECT personal_id, user_id_personal, title, description, location, date_posted, is_active, picture_key_personal"
                    + " FROM personal WHERE personal_id=?;";
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
            pStatement.setInt(1, personalId);
            
            ResultSet results = pStatement.executeQuery();
       
            if (results.next()) {
                personal.setPersonalId(FormatUtils.formatInteger(results.getObject("personal_id")));
                personal.setOwnerId(FormatUtils.formatString(results.getObject("user_id_personal")));
                personal.setTitle(FormatUtils.formatString(results.getObject("title")));
                personal.setDescription(FormatUtils.formatString(results.getObject("description")));
                personal.setLocation(FormatUtils.formatString(results.getObject("location"))); 
                personal.setDatePosted(FormatUtils.formatDateYearFirst(results.getObject("date_posted")));
                personal.setIsActive(FormatUtils.formatBoolean(results.getObject("is_active")));
                personal.setPicFolder(FormatUtils.formatInteger(results.getObject("picture_key_personal")));
            } else {
                personal.setError("No personal listing with that ID found.");
            }
            return personal;
            
        } catch (SQLException e) {
            personal.setError(e.toString());
            return personal;
        }
    }
    
    public static PersonalList getPersonalsByUserId(DbConn dbc, String userId, String start, String numRows){
        PersonalList personalList = new PersonalList();
         try {

            // prepare (compiles) the SQL statement
          String sql = "SELECT personal_id, user_id_personal, title, description, location, date_posted, is_active, picture_key_personal"
                    + " FROM personal WHERE user_id_personal=? LIMIT ? OFFSET ?;";
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
            pStatement.setString(1, userId);
            pStatement.setInt(2,Integer.parseInt(numRows));
            pStatement.setInt(3, Integer.parseInt(start));
            
            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                String personalId = FormatUtils.formatInteger(results.getObject("personal_id"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_personal"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String description = FormatUtils.formatString(results.getObject("description"));                
                String location = FormatUtils.formatString(results.getObject("location"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String isActive = FormatUtils.formatBoolean(results.getObject("is_active"));
                String pictureKey = FormatUtils.formatInteger(results.getObject("picture_key_personal"));
                personalList.addOption(new Personal(personalId, ownerId, title, description, location, datePosted, isActive, pictureKey));
            } 
            return personalList;
            
        } catch (SQLException e) {
            personalList.dbError = (e.toString());
            return personalList;
        }
    }
    
    public static PersonalList searchActivePersonalTitles(DbConn dbc, String titleKeyWord, String start, String numRows){
        PersonalList personalList = new PersonalList();
         try {

            String sql = "SELECT personal_id, user_id_personal, title, description, location, date_posted, is_active, picture_key_personal"
                    + " FROM personal WHERE is_active = 1"
                    + " AND title LIKE ? LIMIT ? OFFSET ?;";
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
            pStatement.setString(1, "%" + titleKeyWord + "%");
            pStatement.setInt(2,Integer.parseInt(numRows));
            pStatement.setInt(3, Integer.parseInt(start));
            
            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                 String personalId = FormatUtils.formatInteger(results.getObject("personal_id"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_personal"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String description = FormatUtils.formatString(results.getObject("description"));                
                String location = FormatUtils.formatString(results.getObject("location"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String isActive = FormatUtils.formatBoolean(results.getObject("is_active"));
                String pictureKey = FormatUtils.formatInteger(results.getObject("picture_key_personal"));
                personalList.addOption(new Personal(personalId, ownerId, title, description, location, datePosted, isActive, pictureKey));
            }  
            return personalList;
            
        } catch (SQLException e) {
            personalList.dbError = (e.toString());
            return personalList;
        }
    }
}
