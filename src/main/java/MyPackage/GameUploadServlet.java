package MyPackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@WebServlet(name = "GameUploadServlet", urlPatterns = "/GameUploadServlet")
@MultipartConfig

public class GameUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GameDAO gameDAO = new GameDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String action = request.getParameter("action");
	     
	     if (action != null && action.equals("getgames")) {
	         
		     List<Game> games = gameDAO.getAllGames();

		     request.setAttribute("games", games);

		     RequestDispatcher dispatcher = request.getRequestDispatcher("games.jsp");
		     dispatcher.forward(request, response);
	     }
	     
		 else if (action != null && action.equals("deletegame")) {
		    int deleteGameID = Integer.valueOf(request.getParameter("id"));
		    System.out.println(deleteGameID);
		    Game game = new Game();
		    game.setId(deleteGameID);
		    	
		    gameDAO.deleteGame(game);
		    	
		    response.sendRedirect("/XPHENO/GameUploadServlet?action=getgames");
		   }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	     String action = request.getParameter("action");
	     // Check if we're updating a game
	     if (action != null && action.equals("updategame")) {
		     int updateGameID = Integer.valueOf(request.getParameter("id"));
	    	String updateGameTitle = request.getParameter("gametitle");
	    	String updateGameGenre = request.getParameter("gamegenre");
	    	String updateGameDescription = request.getParameter("gamedesc");
	    	 
		    Part filePart = request.getPart("file"); 											
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
		    InputStream fileContent = filePart.getInputStream();
		    
	    	final String IMGPATH = System.getProperty("user.dir") + "/src/main/webapp/userimgs";
	    	// Store file in the directory
	    	File file = new File(IMGPATH, fileName);
	    	
	    	// Overwrites existing file if names match, TODO add automatic renaming
	    	Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING); 

			Game game = new Game();
			game.setDescription(updateGameDescription);
			game.setTitle(updateGameTitle);
			game.setCoverimgpath("userimgs/" + fileName);
			game.setGenre(updateGameGenre);
			game.setId(updateGameID);
				
			gameDAO.updateGame(game);
			
			response.sendRedirect("/XPHENO/GameUploadServlet?action=getgames");
	     
	     }
	    // Otherwise assume we're adding a new game
	    else if (action != null && action.equals("uploadgame")) {
	    	Part filePart = request.getPart("file"); 											
	    	String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
	    	InputStream fileContent = filePart.getInputStream();

	    	String title = request.getParameter("gametitle");
	    	String genre = request.getParameter("gamegenre");
	    	String description = request.getParameter("gamedesc"); 		
		
	    	// Create a session object to retrieve the uploader's username stored in the JSP page
	    	HttpSession session = request.getSession();
	    	String author = (String) session.getAttribute("username");
	    	
	    	// If the game was uploaded as a guest, the author value will be null, so we avoid adding null values to the DB
	    	if (author == null) {
	    		author = "guest";
	    	}
		
	    	// Make a directory for storing user uploaded images
	    	// The IMGPATH works ONLY if you set your Apache Tomcat working directory in Eclipse settings  to your Eclipse project (XPHENO in this case)
	    	// TODO make a better solution
	    	final String IMGPATH = System.getProperty("user.dir") + "/src/main/webapp/userimgs";
	    	Files.createDirectories(Paths.get(IMGPATH));
	    	
	    	// Store file in the directory
	    	File file = new File(IMGPATH, fileName);

	    	// Overwrites existing file if names match, TODO add automatic renaming
	    	try {
	    		Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING); 

				Game game = new Game();
				game.setDescription(description);
				game.setTitle(title);
				game.setAuthor(author);
				game.setCoverimgpath("userimgs/" + fileName);
				game.setGenre(genre);
				
				int rowsAffected = gameDAO.addGame(game);
				
				if (rowsAffected > 0) {
					response.sendRedirect("/XPHENO/GameUploadServlet?action=getgames");
				} else {
					response.sendRedirect("upload-failure.jsp");
				}

			}
			catch (java.nio.file.FileAlreadyExistsException e) {
				System.out.println(e);
			}
			
	     }

	}
}
