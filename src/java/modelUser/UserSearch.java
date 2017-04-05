/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelUser;

import dbUtils.DbConn;
import dbUtils.FormatUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ben
 */
public class UserSearch {
 
    //Returns a list of every user in the user_t table
    public static UserList getAllUsersList(DbConn dbc, String start, String numRows){
        UserList personList = new UserList();
         try {

            // prepare (compiles) the SQL statement
            String sql = "SELECT user_id, email, first_name, last_name, phone_number"
                    + " FROM user_t LIMIT ? OFFSET ?;";
            
                     
           
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            
             pStatement.setInt(1,Integer.parseInt(numRows));
            pStatement.setInt(2, Integer.parseInt(start));

            ResultSet results = pStatement.executeQuery();
            while (results.next()) {
                String email = FormatUtils.formatString(results.getObject("email"));
                String phoneNumber = FormatUtils.formatString(results.getObject("phone_number"));
                String firstName = FormatUtils.formatString(results.getObject("first_name"));
                String lastName = FormatUtils.formatString(results.getObject("last_name"));              
                String userId = FormatUtils.formatString(results.getObject("user_id"));
                personList.addOption(new User(userId, email, firstName, lastName, phoneNumber));
            } 
            return personList;
            
        } catch (SQLException e) {
            personList.dbError = (e.toString());
            return personList;
        }
    }
    
    //Search for a specific user by user_id
    public static UserList getUserById(DbConn dbc, String id){
        UserList personList = new UserList();
         try {

            // prepare (compiles) the SQL statement
            String sql = "SELECT user_id, email, first_name, last_name, phone_number"
                    + " FROM user_t" + 
                    " WHERE user_id = ?;";
            
                     
           
            PreparedStatement pStatement = dbc.getConn().prepareStatement(sql);
            pStatement.setString(1, id);

            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                String email = FormatUtils.formatString(results.getObject("email"));
                String phoneNumber = FormatUtils.formatString(results.getObject("phone_number"));
                String firstName = FormatUtils.formatString(results.getObject("first_name"));
                String lastName = FormatUtils.formatString(results.getObject("last_name"));              
                String userId = FormatUtils.formatString(results.getObject("user_id"));
                personList.addOption(new User(userId, email, firstName, lastName, phoneNumber));
            } 
            return personList;
            
        } catch (SQLException e) {
            personList.dbError = (e.toString());
            return personList;
        }
        

    }
}
