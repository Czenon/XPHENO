package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "GameUploadServlet", urlPatterns = "/GameUploadServlet")
@MultipartConfig

public class GameUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GameDAO gameDAO = new GameDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   
	    Part filePart = request.getPart("file"); 											
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
	    InputStream fileContent = filePart.getInputStream();

		String title = request.getParameter("gametitle");
		String genre = request.getParameter("gamegenre");
		String description = request.getParameter("gamedesc"); 		
		
		// Make a directory for storing user uploaded images
		Files.createDirectories(Paths.get("C:/DBimgs"));
		
		File file = new File("C:/DBimgs", fileName);

		// Overwrites existing file if names match, TODO add automatic renaming
		try {
			Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING); 
			System.out.println("Title: " + title);
			System.out.println(" Description: " + description);
			System.out.println(" Genre: " + genre);
			System.out.println(" Filename: " + fileName);
			Game game = new Game();
			game.setDescription(description);
			game.setTitle(title);
			game.setAuthor_id(1);
			game.setCoverimgpath("C:/DBimgs" + fileName);
			game.setGenre(genre);
			
			int rowsAffected = gameDAO.addGame(game);
			
			if (rowsAffected > 0) {
				response.sendRedirect("registration-success.jsp");
			} else {
				response.sendRedirect("registration-failure.jsp");
			}

		}
		catch (java.nio.file.FileAlreadyExistsException e) {
			System.out.println(e);
		}
		

	}
}
