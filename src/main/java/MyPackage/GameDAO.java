package MyPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
  	private int id;
	private String title;
	private String description;
	private int author_id;
 */

public class GameDAO {
	
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM games")) {

            while (resultSet.next()) {
            	Game game = new Game();
      			String author = null;
            	game.setId(resultSet.getInt("id"));
            	game.setTitle(resultSet.getString("title"));
            	game.setDescription(resultSet.getString("description"));
            	game.setAuthor_id(resultSet.getInt("author_id"));
            	game.setCoverimgpath(resultSet.getString("coverimage_filename"));
            	game.setGenre(resultSet.getString("genre"));
            	
    			PreparedStatement preparedStatement = connection.prepareStatement(
    	                "SELECT username FROM users WHERE id = " + game.getAuthor_id());

    	    			ResultSet gameAuthorName = preparedStatement.executeQuery();
    	    			
    	            if (gameAuthorName.next()) {
    	            	author = gameAuthorName.getString(1);
    	            }
    	      
    	            game.setAuthor(author);
            	
            	games.add(game);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }

    public int addGame(Game game) {
    	
    	// Check if game exists first
    	int gameID = 0;
    	int authorID = 0;
    	int rowsAffected = 0;
    	
    	try {
    			Connection connection = DBConnection.getConnection();  
    			PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id FROM games WHERE title = ?;");
         
    			preparedStatement.setString(1, game.getTitle());
    			ResultSet gameIDRS = preparedStatement.executeQuery();
    			
            if (gameIDRS.next()) {
            	gameID = gameIDRS.getInt(1);
            }
            else {
    			PreparedStatement preparedStatementAuthor = connection.prepareStatement(
    	                "SELECT id FROM users WHERE username = ?;");
    			preparedStatementAuthor.setString(1, game.getAuthor());
    			ResultSet gameAuthorID = preparedStatementAuthor.executeQuery();
                if (gameAuthorID.next()) {
                	authorID = gameAuthorID.getInt(1);
                }

                PreparedStatement preparedStatement1 = connection.prepareStatement(
                "INSERT INTO games (title, description, author_id, genre, coverimage_filename) VALUES (?, ?, ?, ?, ?)");

               preparedStatement1.setString(1, game.getTitle());
               preparedStatement1.setString(2, game.getDescription());
               preparedStatement1.setInt(3, authorID);			
               preparedStatement1.setString(4, game.getGenre());
               preparedStatement1.setString(5, game.getCoverimgpath());
               
               rowsAffected = preparedStatement1.executeUpdate();
                                       	
            }
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
		return rowsAffected;
    }

    
    public void updateGame(Game game) {
		
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE games SET title=?, description=?, coverimage_filename = ?, genre = ? WHERE id=?")) {

            preparedStatement.setString(1, game.getTitle());
            preparedStatement.setString(2, game.getDescription());
            preparedStatement.setString(3, game.getCoverimgpath());
            preparedStatement.setString(4, game.getGenre());
            preparedStatement.setInt(5, game.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteGame(Game game) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM games WHERE id = ?;")) {

            preparedStatement.setInt(1, game.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
