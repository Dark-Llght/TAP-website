import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the updated user data from the request (assuming you have form fields)
        String updatedUsername = request.getParameter("updatedUsername");
        String updatedEmail = request.getParameter("updatedEmail");
        String updatedPassword = request.getParameter("updatedPassword");

        // Database connection variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish a database connection (adjust your connection details)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SiteTap?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "23340809Lhl@");

            // Define an SQL query to update the user record based on the user's ID
            String sql = "UPDATE login SET name=?, email=?, password=?";

            // Create a prepared statement
            pstmt = conn.prepareStatement(sql);

            // Set the updated user data in the prepared statement
            pstmt.setString(1, updatedUsername);
            pstmt.setString(2, updatedEmail);
            pstmt.setString(3, updatedPassword);

            // Execute the update query
            pstmt.executeUpdate();

            // Redirect back to the admin page after editing
            response.sendRedirect(request.getContextPath() + "/admin.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions here, e.g., show an error message
        } finally {
            try {
                // Close the database resources
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
