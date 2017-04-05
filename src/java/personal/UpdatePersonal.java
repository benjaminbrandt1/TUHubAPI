/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personal;

import dbUtils.DbConn;
import dbUtils.ValidationUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class UpdatePersonal {
    public static Personal update(DbConn dbc, Personal inputData){
        Personal errorMsgs = validate(inputData);
        
        if(errorMsgs.isEmpty()){
            try{
          
            // Start preparing SQL statement
            StringBuilder sql = new StringBuilder("UPDATE personal SET ");
            
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
            
            boolean newLocation = false;
            if(inputData.getLocation().length() != 0){
                if(commaNeeded){
                    sql.append(",");
                }
                sql.append(" location = ?");
                newLocation = true;
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
            
            sql.append(" WHERE personal_id = ?");       
            
                System.out.println(sql.toString());
            
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
           if(newLocation){
               pStatement.setString(i, inputData.getLocation());
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
            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(i, inputData.getPersonalId());
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
            errorMsgs.setError("Personal not updated");
        }
            return errorMsgs;
        
    }
    
    public static Personal validate(Personal personal){
            Personal error = new Personal();
            
            error.setPersonalId(ValidationUtils.integerValidationMsg(personal.getPersonalId(), true));
            
            error.setLocation(ValidationUtils.stringValidationMsg(personal.getLocation(), 45, false));
            
            error.setDescription(ValidationUtils.stringValidationMsg(personal.getDescription(), 2000, false));
            
            error.setTitle(ValidationUtils.stringValidationMsg(personal.getTitle(), 45, false));
            
            error.setIsActive(ValidationUtils.booleanValidation(personal.getIsActive(), false));
            
            return error;
            
        }
}
