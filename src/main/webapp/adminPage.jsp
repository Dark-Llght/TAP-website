<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
</head>
<body>
    <h1>Admin Page</h1>
    <table>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>
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
                String query = "SELECT * FROM login";
                rs = stmt.executeQuery(query);
                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("email") %></td>
            <td><%= rs.getString("password") %></td>
            <td>
                <!-- Edit Button -->
                <form action="EditUserServlet" method="post">
                    <input type="hidden" name="userId">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <!-- Delete Button -->
                <form action="DeleteUserServlet" method="post">
                    <input type="hidden" name="userId" >
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
