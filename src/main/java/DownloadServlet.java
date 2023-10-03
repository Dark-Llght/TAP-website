import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SiteTap?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "23340809Lhl@");

            // Retrieve the downloadId parameter from the URL
            String downloadId = request.getParameter("downloadId");

            // Check if downloadId is not null and is a valid integer
            if (downloadId != null && downloadId.matches("\\d+")) {
                // Parse the downloadId to an integer
                int downloadIdInt = Integer.parseInt(downloadId);

                // Your download handling logic here using downloadIdInt
                // ...

                // For demonstration, insert a record into the user_download_associations table
                int userId = (int) request.getSession().getAttribute("user_id");
                String insertQuery = "INSERT INTO downloads (user_id, download_id) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setInt(2, downloadIdInt);
                    preparedStatement.executeUpdate();
                }

                // Redirect to a success page or perform other actions
                response.sendRedirect("download.html?success=true");
            } else {
                // Handle invalid downloadId parameter
                response.sendRedirect("error.jsp");
            }

            // Close the database connection
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle database errors
            response.sendRedirect("error.jsp");
        }
    }
}
