package com.triaxyd.users;
import com.triaxyd.users.Users;
import com.triaxyd.database.DatabaseConnector;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;


@WebServlet(name = "UsersSignUp", value = "/UsersSignUp")
public class UsersSignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String message = "";
        if (checkUserExists(username)) {
            //User already exists
            message = "User " + username +" already exists";
        } else {
            //user not found
            try (Connection connection = DatabaseConnector.connect()) {

                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Date createTime = Date.valueOf(LocalDate.now());
                String role = checkUserRole(password);
                String salt = BCrypt.gensalt(12);
                String hashedPassword = BCrypt.hashpw(password, salt);

                //add to user table
                if(createUser(connection, username, email, hashedPassword, createTime, role, salt)){

                }else{
                    //something went wrong
                }

                //create the role and add to role table(customer,admin,contentadmin)
                if(createRole(connection,username,name,role)){

                }else{
                    //something went wrong
                }

                message = "user with username " + username + " created";


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("message",message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try{
            dispatcher.forward(request,response);
        }catch(ServletException e){

        }
    }

    private boolean checkUserExists(String username) {
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM user WHERE username= ?";

        try {
            Connection connection = DatabaseConnector.connect();
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            //Error
        }
        return false;
    }

    private String checkUserRole(String password) {
        if (password.equals("ImAdmin123")) {
            return "Admin";
        } else if (password.equals("ImContentAdmin123")){
            return "ContentAdmin";
        }else{
            return "Customer";
        }
    }


    private boolean createUser(Connection connection, String username, String email, String hashedPassword, Date createTime, String role, String salt) {
        try {
            String sql = "INSERT INTO user VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setDate(4, createTime);
            preparedStatement.setString(5, role);
            preparedStatement.setString(6, salt);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                preparedStatement.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean createRole(Connection connection, String username, String name, String role) {
        String table_name;

        if (role.equals("Admin")) {
            table_name = "admins";
        } else if (role.equals("ContentAdmin")) {
            table_name = "content_admin";
        } else {
            table_name = "customers";
        }

        try {
            String sql = "INSERT INTO " + table_name + " VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 0);
            statement.setString(2, name);
            statement.setString(3, username);
            int rowsAffectedCustomer = statement.executeUpdate();
            if (rowsAffectedCustomer > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
