import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SiteTap?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "23340809Lhl@");
			String ema = request.getParameter("email");
			String pas = request.getParameter("password");
			PreparedStatement ps = con.prepareStatement("select email from login where email=? and password=?");
			ps.setString(1, ema);
			ps.setString(2, pas);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("download.html");
				rd.forward(request, response);
			}
			else {
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
