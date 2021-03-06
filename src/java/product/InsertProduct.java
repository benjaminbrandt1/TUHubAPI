/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package product;

import dbUtils.DbConn;
import dbUtils.FormatUtils;
import dbUtils.ValidationUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class InsertProduct {
    public static Product insert(Product inputData, DbConn dbc) {

        Product errorMsgs = new Product();
        errorMsgs = validate(inputData);
        if (!errorMsgs.isEmpty()) {  // at least one field has an error, don't go any further.
            return errorMsgs;

        } else { // all fields passed validation
            try{

          
            // Start preparing SQL statement
            String sql = "INSERT INTO picture VALUES();";
            
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            pStatement.executeUpdate();
                    
                    
            sql = "INSERT INTO product (title,";
            
            boolean hasDescription = false;
            if(inputData.getDescription().length() != 0){
                sql += "description, ";
                hasDescription = true;
            } 
            
            sql += " price, is_active, user_id_product, picture_key_product) \n"
                    + "VALUES (?, ";
            
            if(hasDescription){
                sql += "?, ";
            }
            
            sql += "?, ?, ?, LAST_INSERT_ID());";           
            
           pStatement = dbc.getConn().prepareStatement(sql);

            // Encode string values into the prepared statement.
           int i = 1;
           
            pStatement.setString(i, inputData.getTitle());
            i++;
            
            if(hasDescription){
                 pStatement.setString(i, inputData.getDescription());
                 i++;
            }
            
            pStatement.setDouble(i, FormatUtils.stringToDollar(inputData.getPrice()));
            i++;
            
            if(inputData.getIsActive().equalsIgnoreCase("true")){
                pStatement.setBoolean(i, true);
            } else {
                pStatement.setBoolean(i, false);
            }
            i++;
            
            pStatement.setString(i, inputData.getOwnerId());

            // here the SQL statement is actually executed
            pStatement.executeUpdate();

            // This will return empty string if all went well, else all error messages.
           
            } catch(SQLException e){
               inputData.setError(e.toString());
            }
            return inputData;
        }
        
    } // insert

        public static Product validate(Product product){
            Product error = new Product();
            
            error.setTitle(ValidationUtils.stringValidationMsg(product.getTitle(), 45, true));
            
            error.setDescription(ValidationUtils.stringValidationMsg(product.getDescription(), 2000, false));
            
            error.setPrice(ValidationUtils.dollarValidation(product.getPrice(), true));
            
            error.setIsActive(ValidationUtils.booleanValidation(product.getIsActive(), true));
            
            error.setOwnerId(ValidationUtils.stringValidationMsg(product.getOwnerId(), 45, true));
            
            return error;
            
        }
}
