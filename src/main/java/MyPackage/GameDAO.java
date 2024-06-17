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
            	game.setId(resultSet.getInt("id"));
            	game.setTitle(resultSet.getString("title"));
            	game.setDescription(resultSet.getString("description"));
            	game.setAuthor_id(resultSet.getInt("author_id"));

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
                PreparedStatement preparedStatement1 = connection.prepareStatement(
                "INSERT INTO games (title, description, author_id, genre, coverimage_filename) VALUES (?, ?, ?, ?, ?)");

               preparedStatement1.setString(1, game.getTitle());
               preparedStatement1.setString(2, game.getDescription());
               preparedStatement1.setInt(3, 1);			// TODO add automatic id fetching
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
                     "UPDATE games SET title=?, description=? WHERE id=?")) {

            preparedStatement.setString(1, game.getTitle());
            preparedStatement.setString(2, game.getDescription());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteGame(int gameId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM games WHERE id=?")) {

            preparedStatement.setInt(1, gameId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
