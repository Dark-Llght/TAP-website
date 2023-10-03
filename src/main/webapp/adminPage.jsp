<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
</head>
<body>
    <h1>Admin Page</h1> <form action="registerAdm.jsp">
    <input type="submit" value="Add User" />
</form>
    <table>
        <tr>
        	<th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>
            <th>DownloadedVersion</th>
            <th>Edit</th>
            <th>Delete</th>
            <!-- Add more fields as needed -->
        </tr>
        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SiteTap?useTimezone=true&serverTimezone=UTC&useSSL=false", "root", "23340809Lhl@");
                stmt = conn.createStatement();
                
                // Modify the SQL query to perform a join between login and downloads tables
                String query = "SELECT login.*, downloads.download_id " +
                               "FROM login " +
                               "LEFT JOIN downloads ON login.id = downloads.user_id";
                
                rs = stmt.executeQuery(query);
                while (rs.next()) {
        %>
        <tr>
        	<td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("email") %></td>
            <td><%= rs.getString("password") %></td>
            <td><%= rs.getString("download_id") %></td>
            <td>
                <!-- Edit Button -->
                <form action="AdmUpdateCredentials" method="post">
                    <input type="hidden" name="userId" value="<%= rs.getInt("id") %>">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <!-- Delete Button -->
                <form action="DeleteUserServlet" method="post">
                    <input type="hidden" name="userId" value="<%= rs.getInt("id") %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        %>
    </table>
</body>
<style>
    /* CSS styles here */
</style>
</html>
