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

@WebServlet("/adminPage")
public class AdmUpdateCredentials extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SiteTap?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "23340809Lhl@");

            PreparedStatement checkStmt = con.prepareStatement("SELECT email FROM login WHERE email = ? AND password = ?");
            checkStmt.setString(1, email);
            checkStmt.setString(2, currentPassword);
            ResultSet checkResult = checkStmt.executeQuery();
            if (checkResult.next()) {
                PreparedStatement updateStmt = con.prepareStatement("UPDATE login SET password = ? WHERE email = ?");
                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, email);
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                	request.getRequestDispatcher("adminPage.jsp").forward(request, response);
                } else {
                	request.getRequestDispatcher("userconfigErr.jsp?error=unknown").forward(request, response);
                }
            } else {
            	request.getRequestDispatcher("userconfigErr.jsp?error=unknown").forward(request, response);
            }

            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
