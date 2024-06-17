package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO = new UserDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password  = request.getParameter("password");
		
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		// Return number of rows updated from creating a new user
		int rowsAffected = userDAO.loginAsUser(user);
		
		if (rowsAffected > 0) {
			response.sendRedirect("registration-success.jsp");
		} else {
			response.sendRedirect("registration-failure.jsp");
		}
	}

}
