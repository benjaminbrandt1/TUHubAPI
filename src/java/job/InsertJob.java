/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package job;

import dbUtils.DbConn;
import dbUtils.FormatUtils;
import dbUtils.ValidationUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class InsertJob {
    public static Job insert(Job inputData, DbConn dbc) {

        Job errorMsgs = new Job();
        errorMsgs = validate(inputData);
        if (!errorMsgs.isEmpty()) {  // at least one field has an error, don't go any further.
            return errorMsgs;

        } else { // all fields passed validation
            try{

          
            // Start preparing SQL statement
            String sql = "INSERT INTO picture VALUES();";
            
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            pStatement.executeUpdate();
                    
                    
            sql = "INSERT INTO job (user_id_job, location, ";
            
            boolean hasHours = false;
            if(inputData.getHoursPerWeek() != -1){
                sql += "hours_per_week, ";
                hasHours = true;
            } 
            
            boolean hasDescription = false;
            if(inputData.getDescription().length() != 0){
                sql += "description, ";
                hasDescription = true;
            } 
            
            sql += " title, pay, ";
            
            boolean hasStartDate = false;
            if(inputData.getStartDate().length() != 0){
                sql += "start_date, ";
                hasStartDate = true;
            } 
            
            sql += "is_active, picture_key_job) \n"
                    + "VALUES (?, ?, ";
            
            if(hasHours){
                sql += "?, ";
            }
            
            if(hasDescription){
                sql += "?, ";
            }
            
            sql += "?, ?, ";
            
            if(hasStartDate){
                sql += "?, ";
            }
            
            sql += "?, LAST_INSERT_ID());";           
            
           pStatement = dbc.getConn().prepareStatement(sql);

            // Encode string values into the prepared statement.
           int i = 1;
           
            pStatement.setString(i, inputData.getOwnerId());
            i++;
            
            pStatement.setString(i, inputData.getLocation());
            i++;
            
            if(hasHours){
                try{
                pStatement.setInt(i, inputData.getHoursPerWeek());
                i++;
                } catch (NumberFormatException e){
                    inputData.setError(e.toString());
                    return inputData;
                }
            }
            
            if(hasDescription){
                 pStatement.setString(i, inputData.getDescription());
                 i++;
            }
            
             pStatement.setString(i, inputData.getTitle());
            i++;
            
            pStatement.setDouble(i, FormatUtils.stringToDollar(inputData.getPay()));
            i++;
            
            if(hasStartDate){
                 pStatement.setDate(i, FormatUtils.stringToDate(inputData.getStartDate()));
                 i++;
            }
            
            if(inputData.getIsActive().equalsIgnoreCase("true")){
                pStatement.setBoolean(i, true);
            } else {
                pStatement.setBoolean(i, false);
            }
            i++;

            // here the SQL statement is actually executed
            pStatement.executeUpdate();

            // This will return empty string if all went well, else all error messages.
           
            } catch(SQLException e){
               inputData.setError(e.toString());
            }
            return inputData;
        }
        
    } // insert

        public static Job validate(Job job){
            Job error = new Job();
            
            error.setOwnerId(ValidationUtils.stringValidationMsg(job.getOwnerId(), 45, true));
            
            error.setLocation(ValidationUtils.stringValidationMsg(job.getLocation(), 45, true));
            
            error.setError(ValidationUtils.hoursPerWeekValidation(job.getHoursPerWeek(), false));
            
            error.setDescription(ValidationUtils.stringValidationMsg(job.getDescription(), 2000, false));
            
            error.setTitle(ValidationUtils.stringValidationMsg(job.getTitle(), 45, true));
            
            error.setPay(ValidationUtils.dollarValidation(job.getPay(), true));
            
            error.setStartDate(ValidationUtils.dateValidationMsg(job.getStartDate(), false));
            
            error.setIsActive(ValidationUtils.booleanValidation(job.getIsActive(), true));
            
            return error;
            
        }
}
