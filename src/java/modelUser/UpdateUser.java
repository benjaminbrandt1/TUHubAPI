/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelUser;

import dbUtils.DbConn;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class UpdateUser {
    
    public static String updatePhoneNumber(DbConn dbc, String userId, String phoneNumber){
        String errorMsgs = "";
        
            try{
          
            // Start preparing SQL statement
            String sql = "UPDATE user_t SET phone_number = ? WHERE user_id = ?";          
            
           PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);

            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, phoneNumber);
            pStatement.setString(2, userId);
            // here the SQL statement is actually executed
            int numRows = pStatement.executeUpdate();
            
            if(numRows == 1){
                return "Success!";
            } else {
                return "Error: expected to alter 1 row, " + String.valueOf(numRows) + " rows altered.";
            }

            // This will return empty string if all went well, else all error messages.
           
            } catch(SQLException e){
               errorMsgs = (e.toString());
            }
            return errorMsgs;
        
    }
    
}
