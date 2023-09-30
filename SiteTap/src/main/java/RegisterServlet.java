import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SiteTap?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "23340809Lhl@");
            String ema = request.getParameter("email");
            String pas = request.getParameter("password");
            
            String name = request.getParameter("name");
            PreparedStatement checkStmt = con.prepareStatement("SELECT email FROM login WHERE email = ?");
            checkStmt.setString(1, ema);
            ResultSet checkResult = checkStmt.executeQuery();
            if (checkResult.next()) {
                
                response.sendRedirect("registerErr.jsp"); 
            } else {
                PreparedStatement insertStmt = con.prepareStatement("INSERT INTO login (name, email, password) VALUES (?, ?, ?)");
                insertStmt.setString(1, name);
                insertStmt.setString(2, ema);
                insertStmt.setString(3, pas);
                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    response.sendRedirect("login.jsp"); 
                } else {
                    response.sendRedirect("registration.jsp?error=unknown"); 
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
