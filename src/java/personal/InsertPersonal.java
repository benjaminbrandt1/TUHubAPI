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
public class InsertPersonal {
     public static Personal insert(Personal inputData, DbConn dbc) {

        Personal errorMsgs = new Personal();
        errorMsgs = validate(inputData);
        if (!errorMsgs.isEmpty()) {  // at least one field has an error, don't go any further.
            return errorMsgs;

        } else { // all fields passed validation
            try{

          
            // Start preparing SQL statement
            String sql = "INSERT INTO picture VALUES();";
            
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            pStatement.executeUpdate();
                    
                    
            sql = "INSERT INTO personal (user_id_personal, title, ";
            
            boolean hasDescription = false;
            if(inputData.getDescription().length() != 0){
                sql += "description, ";
                hasDescription = true;
            } 
            
            boolean hasLocation = false;
            if(inputData.getLocation().length() != 0){
                sql += "location, ";
                hasLocation = true;
            } 
            
            sql += "is_active, picture_key_personal) \n"
                    + "VALUES (?, ?, ";
            
            if(hasDescription){
                sql += "?, ";
            }
            
            if(hasLocation){
                sql += "?, ";
            }
            
            sql += "?, LAST_INSERT_ID());";           
            
           pStatement = dbc.getConn().prepareStatement(sql);

            // Encode string values into the prepared statement.
           int i = 1;
           
            pStatement.setString(i, inputData.getOwnerId());
            i++;
            
            pStatement.setString(i, inputData.getTitle());
            i++;           
            
            if(hasDescription){
                 pStatement.setString(i, inputData.getDescription());
                 i++;
            }
            
            if(hasLocation){
                 pStatement.setString(i, inputData.getLocation());
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
     
     

        public static Personal validate(Personal personal){
            Personal error = new Personal();
            
            error.setOwnerId(ValidationUtils.stringValidationMsg(personal.getOwnerId(), 45, true));
            
            error.setLocation(ValidationUtils.stringValidationMsg(personal.getLocation(), 45, false));
            
            error.setDescription(ValidationUtils.stringValidationMsg(personal.getDescription(), 2000, false));
            
            error.setTitle(ValidationUtils.stringValidationMsg(personal.getTitle(), 45, true));
            
            error.setIsActive(ValidationUtils.booleanValidation(personal.getIsActive(), true));
            
            return error;
            
        }
}
