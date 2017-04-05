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
public class UpdateProduct {
    public static Product update(DbConn dbc, Product inputData){
        Product errorMsgs = validate(inputData);
        
        if(errorMsgs.isEmpty()){
            try{
          
            // Start preparing SQL statement
            StringBuilder sql = new StringBuilder("UPDATE product SET ");
            
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
            
            boolean newPrice = false;
            if(inputData.getPrice().length() != 0){
                if(commaNeeded){
                    sql.append(",");
                }
                sql.append(" price = ?");
                newPrice = true;
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
            
            sql.append(" WHERE product_id = ?");       
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
           if(newPrice){
               pStatement.setDouble(i, FormatUtils.stringToDollar(inputData.getPrice()));
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
            pStatement.setString(i, inputData.getProductId());
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
    
    public static Product validate(Product product){
            Product error = new Product();
            
            error.setTitle(ValidationUtils.stringValidationMsg(product.getTitle(), 45, false));
            
            error.setDescription(ValidationUtils.stringValidationMsg(product.getDescription(), 2000, false));
            
            error.setPrice(ValidationUtils.dollarValidation(product.getPrice(), false));
            
            error.setIsActive(ValidationUtils.booleanValidation(product.getIsActive(), false));
            
            error.setProductId(ValidationUtils.integerValidationMsg(product.getProductId(), true));
            
            return error;
            
        }
}
