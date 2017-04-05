/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelUser;

import dbUtils.DbConn;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.validator.EmailValidator;

/**
 *
 * @author Ben
 */
public class InsertUser {
    
        public static User insert(User inputData, DbConn dbc) {

        User errorMsgs = new User();
        errorMsgs = validate(inputData);
        if (!errorMsgs.isEmpty()) {  // at least one field has an error, don't go any further.
            return errorMsgs;

        } else { // all fields passed validation
            try{

          
            // Start preparing SQL statement
            String sql = "INSERT INTO user_t (user_id, email, first_name, last_name";
            
            boolean hasPhoneNumber = false;
            if(inputData.getPhoneNumber().length() != 0){
                String phoneNumber = User.removeDashes(inputData.getPhoneNumber());
                inputData.setPhoneNumber(phoneNumber);
                sql += ", phone_number) ";
                hasPhoneNumber = true;
            } else {
                sql += ") ";
            }
             sql += "values (?,?,?,?";
             if(hasPhoneNumber){
                 sql +=",?);";
             } else {
                 sql +=");";
             }

            
           PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);

            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, inputData.getTuId());
            pStatement.setString(2, inputData.getEmail());
            pStatement.setString(3, inputData.getFirstName());
            pStatement.setString(4, inputData.getLastName());
            if(hasPhoneNumber){
               pStatement.setString(5, inputData.getPhoneNumber()); 
            }
            

            // here the SQL statement is actually executed
            int numRows = pStatement.executeUpdate();

            // This will return empty string if all went well, else all error messages.
           
            } catch(SQLException e){
               inputData.setError(e.toString());
            }
            return inputData;
        }
        
    } // insert

        public static User validate(User user){
            User error = new User();
            
            //Reg Ex for phone number : matches 9999999999, 1-999-999-9999 and 999-999-9999
            String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$" ;
            
            if(user.getTuId().length() == 0 || user.getTuId().length() > 45){
                error.setTuId("Invalid TU ID");
            }
            
            EmailValidator emailValidator = EmailValidator.getInstance();
            if(!emailValidator.isValid(user.getEmail())){
                error.setEmail("Invalid Email");
            }
            
            if(user.getFirstName().length() == 0 || user.getFirstName().length() > 45){
                error.setFirstName("Invalid length for First Name");
            }
            
            if(user.getLastName().length() == 0 || user.getLastName().length() > 45){
                error.setLastName("Invalid length for Last Name");
            }
            
            //Match the phone number to regex, number can be blank
            if (user.getPhoneNumber().length() > 0 && !user.getPhoneNumber().matches(regexStr)){
                error.setPhoneNumber("Invalid format for Phone Number");
            }
            
            return error;
            
        }
}
