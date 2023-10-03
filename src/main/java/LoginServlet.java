import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SiteTap?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "23340809Lhl@");
			// After successful login
			String ema = request.getParameter("email");
			String pas = request.getParameter("password");
			PreparedStatement ps = con.prepareStatement("SELECT id FROM login WHERE email=? AND password=?");
			ps.setString(1, ema);
			ps.setString(2, pas);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
			    int userId = rs.getInt("id"); // Assuming 'id' is the name of the column containing the user's ID
			    HttpSession session = request.getSession();
			    session.setAttribute("user_id", userId);

			    if (ema != null && ema.equals("lcfilho19@gmail.com") && pas != null && pas.equals("2334")) {
			        RequestDispatcher rd = request.getRequestDispatcher("adminPage.jsp");
			        rd.forward(request, response);
			    } else {
			        RequestDispatcher rd = request.getRequestDispatcher("download.html");
			        rd.forward(request, response);
			    }
			} else {
			    RequestDispatcher rd = request.getRequestDispatcher("loginErr.jsp");
			    rd.forward(request, response);
			}


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
