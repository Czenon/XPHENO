package MyPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
	private int id;
	private String username;
	private String password;
	private String email;
 */

public class UserDAO {
	
    public int addUser(User user) {
    	
    	// Check if user exists first
    	int userID = 0;
    	int rowsAffected = 0;
    	
    	try (Connection connection = DBConnection.getConnection();  
    			PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id FROM users WHERE username = ? OR password = ?;")) {
         
    			preparedStatement.setString(1, user.getUsername());
    			preparedStatement.setString(2, user.getPassword());
    			ResultSet userIDRS = preparedStatement.executeQuery();
    			
            if (userIDRS.next()) {
            	userID = userIDRS.getInt(1);
            }
            else {
            		PreparedStatement preparedStatement1 = connection.prepareStatement(
            		"INSERT INTO users (username, password, email) VALUES (?, ?, ?)");

                    preparedStatement1.setString(1, user.getUsername());
                    preparedStatement1.setString(2, user.getPassword());
                    preparedStatement1.setString(3, user.getEmail());
                           
                    rowsAffected = preparedStatement1.executeUpdate();
            	}
            }
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
		return rowsAffected;
    }
    
public String loginAsUser(User user) {
    	
    	// Check if user exists first
    	int userID = 0;
    	int rowsAffected = 0;
    	String username = null;
    	
    	try (Connection connection = DBConnection.getConnection();  
    			PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, username FROM users WHERE username = ? AND password = ?;")) {
         
    			preparedStatement.setString(1, user.getUsername());
    			preparedStatement.setString(2, user.getPassword());
    			ResultSet userIDRS = preparedStatement.executeQuery();
    			
            if (userIDRS.next()) {
            	userID = userIDRS.getInt(1);
            	username = userIDRS.getString(2);	
            	
            	rowsAffected = 2;
            	}
            }
    	
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
		return username;
    } 
}
