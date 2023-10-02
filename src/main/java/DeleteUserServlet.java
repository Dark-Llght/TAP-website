import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the user ID to be deleted from the request parameter
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Database connection variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish a database connection (adjust your connection details)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SiteTap?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "23340809Lhl@");

            // Define an SQL query to delete the user record based on the user's ID
            String sql = "DELETE FROM login WHERE id=?";

            // Create a prepared statement
            pstmt = conn.prepareStatement(sql);

            // Set the user ID in the prepared statement
            pstmt.setInt(1, userId);

            // Execute the delete query
            pstmt.executeUpdate();

            // Redirect back to the admin page after deleting
            response.sendRedirect(request.getContextPath() + "/adminPage.jsp");

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
