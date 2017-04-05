/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package product;

import dbUtils.DbConn;
import dbUtils.FormatUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class ProductSearch {
    
    //Returns a list of every product in the product table
    public static ProductList getAllProductList(DbConn dbc, boolean activeOnly, String start, String numRows){
        ProductList productList = new ProductList();
         try {

            // prepare (compiles) the SQL statement
            String sql = "SELECT product_id, title, description, price, is_active, user_id_product, date_posted, picture_key_product"
                    + " FROM product";
            if(activeOnly){
                sql += " WHERE is_active = 1";
            } 
            
            sql += " ORDER BY date_posted DESC LIMIT ? OFFSET ?;";
            
                     
           
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
             pStatement.setInt(1,Integer.parseInt(numRows));
            pStatement.setInt(2, Integer.parseInt(start));

            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                String productId = FormatUtils.formatInteger(results.getObject("product_id"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String description = FormatUtils.formatString(results.getObject("description"));
                String price = FormatUtils.formatDollar(results.getObject("price"));              
                String is_active = FormatUtils.formatBoolean(results.getObject("is_active"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_product"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String pictureFolder = FormatUtils.formatInteger(results.getObject("picture_key_product"));
                productList.addOption(new Product(productId, title, description, price, is_active, ownerId, datePosted, pictureFolder));
            } 
            return productList;
            
        } catch (SQLException e) {
            productList.dbError = (e.toString());
            return productList;
        }
    }
    
    //Returns a list of every user in the user_t table
    public static Product getProductById(DbConn dbc, int productId){
        Product product = new Product();
         try {

            // prepare (compiles) the SQL statement
            String sql = "SELECT product_id, title, description, price, is_active, user_id_product, date_posted, picture_key_product"
                    + " FROM product WHERE product_id = ?;";
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
            pStatement.setInt(1, productId);
            
            ResultSet results = pStatement.executeQuery();
       
            if (results.next()) {
                product.setProductId(FormatUtils.formatInteger(results.getObject("product_id")));
                product.setTitle(FormatUtils.formatString(results.getObject("title")));
                product.setDescription(FormatUtils.formatString(results.getObject("description")));
                product.setPrice(FormatUtils.formatDollar(results.getObject("price")));              
                product.setIsActive(FormatUtils.formatBoolean(results.getObject("is_active")));
                product.setOwnerId(FormatUtils.formatString(results.getObject("user_id_product")));
                product.setDatePosted(FormatUtils.formatDateYearFirst(results.getObject("date_posted")));
                product.setPicFileName(FormatUtils.formatInteger(results.getObject("picture_key_product")));
                
            } else {
                product.setError("No product listing with that ID found.");
            }
            return product;
            
        } catch (SQLException e) {
            product.setError(e.toString());
            return product;
        }
    }
    
    public static ProductList getProductsByUserId(DbConn dbc, String userId, String start, String numRows){
        ProductList productList = new ProductList();
         try {

            // prepare (compiles) the SQL statement
            String sql = "SELECT product_id, title, description, price, is_active, user_id_product, date_posted, picture_key_product"
                    + " FROM product WHERE user_id_product = ? ORDER BY date_posted DESC LIMIT ? OFFSET ?;";
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
            pStatement.setString(1, userId);
            pStatement.setInt(2,Integer.parseInt(numRows));
            pStatement.setInt(3, Integer.parseInt(start));
            
            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                String productId = FormatUtils.formatInteger(results.getObject("product_id"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String description = FormatUtils.formatString(results.getObject("description"));
                String price = FormatUtils.formatDollar(results.getObject("price"));              
                String is_active = FormatUtils.formatBoolean(results.getObject("is_active"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_product"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String pictureFolder = FormatUtils.formatInteger(results.getObject("picture_key_product"));
                productList.addOption(new Product(productId, title, description, price, is_active, ownerId, datePosted, pictureFolder));
            } 
            return productList;
            
        } catch (SQLException e) {
            productList.dbError = (e.toString());
            return productList;
        }
    }
    
    public static ProductList searchActiveProductTitles(DbConn dbc, String titleKeyWord, String start, String numRows){
        ProductList productList = new ProductList();
         try {

            // prepare (compiles) the SQL statement
            String sql = "SELECT product_id, title, description, price, is_active, user_id_product, date_posted, picture_key_product"
                    + " FROM product WHERE is_active = 1"
                    + " AND title LIKE ? ORDER BY date_posted DESC LIMIT ? OFFSET ?;";
                                                    
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
            pStatement.setString(1, "%" + titleKeyWord + "%");
            pStatement.setInt(2,Integer.parseInt(numRows));
            pStatement.setInt(3, Integer.parseInt(start));
            
            ResultSet results = pStatement.executeQuery();
       
            while (results.next()) {
                String productId = FormatUtils.formatInteger(results.getObject("product_id"));
                String title = FormatUtils.formatString(results.getObject("title"));
                String description = FormatUtils.formatString(results.getObject("description"));
                String price = FormatUtils.formatDollar(results.getObject("price"));              
                String is_active = FormatUtils.formatBoolean(results.getObject("is_active"));
                String ownerId = FormatUtils.formatString(results.getObject("user_id_product"));
                String datePosted = FormatUtils.formatDateYearFirst(results.getObject("date_posted"));
                String pictureFolder = FormatUtils.formatInteger(results.getObject("picture_key_product"));
                productList.addOption(new Product(productId, title, description, price, is_active, ownerId, datePosted, pictureFolder));
            } 
            return productList;
            
        } catch (SQLException e) {
            productList.dbError = (e.toString());
            return productList;
        }
    }
    
}
