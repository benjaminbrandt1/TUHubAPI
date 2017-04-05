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
public class UpdateJob {
    public static Job update(DbConn dbc, Job inputData){
        Job errorMsgs = validate(inputData);
        
        if(errorMsgs.isEmpty()){
            try{
          
            // Start preparing SQL statement
            StringBuilder sql = new StringBuilder("UPDATE job SET ");
            
            boolean commaNeeded = false;
            boolean newTitle = false;
            if (inputData.getTitle().length() != 0){
                sql.append("title = ?");
                newTitle = true;
                commaNeeded = true;
            }
            
            boolean newDescription = false;
            if(inputData.getDescription().length() != 0){
                if(commaNeeded){
                    sql.append(",");
                }
                sql.append(" description = ?");
                newDescription = true;
                commaNeeded = true;
            }
            
            boolean newPay = false;
            if(inputData.getPay().length() != 0){
                if(commaNeeded){
                    sql.append(",");
                }
                sql.append(" pay = ?");
                newPay = true;
                commaNeeded = true;
            }           
            
            boolean newIsActive = false;
            if(inputData.getIsActive().length() != 0){
                if(commaNeeded){
                    sql.append(",");
                }
                sql.append(" is_active = ?");
                newIsActive = true;
                commaNeeded = true;
            }
            
            boolean newStart = false;
            if(inputData.getStartDate().length() != 0){
                if(commaNeeded){
                    sql.append(",");
                }
                sql.append(" start_date = ?");
                newStart = true;
                commaNeeded = true;
            } 
            
            boolean newLocation = false;
            if(inputData.getLocation().length() != 0){
                if(commaNeeded){
                    sql.append(",");
                }
                sql.append(" location = ?");
                newLocation = true;
                commaNeeded = true;
            } 
            
            boolean newHours = false;
            if(inputData.getHoursPerWeek()!= -1){
                if(commaNeeded){
                    sql.append(",");
                }
                sql.append(" hours_per_week = ?");
                newHours = true;
                commaNeeded = true;
            } 
            
            sql.append(" WHERE job_id = ?");       
            
            
           PreparedStatement pStatement = dbc.getConn().prepareStatement(sql.toString());

           int i = 1;
           if(newTitle){
               pStatement.setString(i, inputData.getTitle());
               i++;
           }
           if(newDescription){
               pStatement.setString(i, inputData.getDescription());
               i++;
           }
           if(newPay){
               pStatement.setDouble(i, FormatUtils.stringToDollar(inputData.getPay()));
               i++;
           }
           if(newIsActive){
               if(inputData.getIsActive().equalsIgnoreCase("true")){
                   pStatement.setBoolean(i, true);
               } else {
                   pStatement.setBoolean(i, false);
               }
               i++;
           }
           if(newStart){
               pStatement.setDate(i, FormatUtils.stringToDate(inputData.getStartDate()));
               i++;
           }
           if(newLocation){
               pStatement.setString(i, inputData.getLocation());
               i++;
           }
           if(newHours){
               pStatement.setInt(i, inputData.getHoursPerWeek());
               i++;
           }
            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(i, inputData.getJobId());
            // here the SQL statement is actually executed
            int numRows = pStatement.executeUpdate();
            
            if(numRows == 1){
                return inputData;
            } else {
                errorMsgs.setError("Error: expected to alter 1 row, " + String.valueOf(numRows) + " rows altered.");
            }

            // This will return empty string if all went well, else all error messages.
           
            } catch(SQLException e){
               errorMsgs.setError(e.toString());
            }
        } else {
            errorMsgs.setError("Product not updated");
        }
            return errorMsgs;
        
    }
    
     public static Job validate(Job job){
            Job error = new Job();
            
            error.setJobId(ValidationUtils.integerValidationMsg(job.getJobId(), true));
            
            error.setLocation(ValidationUtils.stringValidationMsg(job.getLocation(), 45, false));
            
            error.setError(ValidationUtils.hoursPerWeekValidation(job.getHoursPerWeek(), false));
            
            error.setDescription(ValidationUtils.stringValidationMsg(job.getDescription(), 2000, false));
            
            error.setTitle(ValidationUtils.stringValidationMsg(job.getTitle(), 45, false));
            
            error.setPay(ValidationUtils.dollarValidation(job.getPay(), false));
            
            error.setStartDate(ValidationUtils.dateValidationMsg(job.getStartDate(), false));
            
            error.setIsActive(ValidationUtils.booleanValidation(job.getIsActive(), false));
            
            return error;
            
        }
}
