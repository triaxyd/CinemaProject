package com.triaxyd.users;

import com.triaxyd.cinema.CinemaDAO;
import com.triaxyd.database.DatabaseConnector;
import org.mindrot.jbcrypt.BCrypt;

import javax.xml.crypto.Data;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public Users checkLogin(String username,String password){
        Users user = null;
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM user WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                //account found
                if(BCrypt.checkpw(password,resultSet.getString("password"))){
                    if (resultSet.getString("role").equals("Admin")){
                        user = new Admins();
                        String sqlAdmin = "SELECT * FROM admins WHERE user_username=?";
                        PreparedStatement psAdmin = connection.prepareStatement(sqlAdmin);
                        psAdmin.setString(1,username);
                        ResultSet rsAdmin = psAdmin.executeQuery();
                        if(rsAdmin.next()){
                            ((Admins)user).setId(rsAdmin.getInt("ID"));
                            ((Admins)user).setName(rsAdmin.getString("NAME"));
                        }
                        rsAdmin.close();
                        psAdmin.close();
                    }else if(resultSet.getString("role").equals("ContentAdmin")){
                        user = new ContentAdmins();
                        String sqlContentAdmin = "SELECT * FROM content_admin WHERE user_username=?";
                        PreparedStatement psContentAdmin = connection.prepareStatement(sqlContentAdmin);
                        psContentAdmin.setString(1,username);
                        ResultSet rsContentAdmin = psContentAdmin.executeQuery();
                        if(rsContentAdmin.next()){
                            ((ContentAdmins)user).setId(rsContentAdmin.getInt("ID"));
                            ((ContentAdmins)user).setName(rsContentAdmin.getString("NAME"));
                        }
                        rsContentAdmin.close();
                        psContentAdmin.close();
                    }else{
                        user = new Customers();
                        String sqlCustomer = "SELECT * FROM customers WHERE user_username=?";
                        PreparedStatement psCustomer = connection.prepareStatement(sqlCustomer);
                        psCustomer.setString(1,username);
                        ResultSet rsCustomer = psCustomer.executeQuery();
                        if(rsCustomer.next()){
                            ((Customers)user).setId(rsCustomer.getInt("ID"));
                            ((Customers)user).setName(rsCustomer.getString("NAME"));
                        }
                        rsCustomer.close();
                        psCustomer.close();
                    }
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setCreationDate(resultSet.getDate("create_time"));
                    user.setRole(resultSet.getString("role"));
                    user.setSalt(resultSet.getString("salt"));
                    return user;
                }else{
                    //wrong password
                }
            }else{

            }
        }catch(SQLException e){
            return user;
        }
    return user;
    }




    public boolean checkUserExists(String username) {
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



    public boolean createUser(String username, String email, String hashedPassword, Date createTime, String role, String salt) {
        try {
            Connection connection = DatabaseConnector.connect();
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
        } catch (Exception e) {
            return false;
        }
    }


    public boolean createRole(String username, String name, String role) {
        String table_name;

        if (role.equals("Admin")) {
            table_name = "admins";
        } else if (role.equals("ContentAdmin")) {
            table_name = "content_admin";
        } else {
            table_name = "customers";
        }

        try {
            Connection connection = DatabaseConnector.connect();
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
            return false;
        }
    }


    public static List<Users> getUsers() {
        List<Users> usersList = new ArrayList<>();
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM user";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //usersList.add()
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return usersList;
    }


    public Users getUser(String username){
        Users user = null;
        try{
            Connection connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                if(rs.getString("role").equals("Admin")){
                    user = new Admins();
                    user.setRole("Admin");
                } else if (rs.getString("role").equals("ContentAdmin")) {
                    user = new ContentAdmins();
                    user.setRole("ContentAdmin");
                }else{
                    user = new Customers();
                    user.setRole("Customer");
                }
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreationDate(rs.getDate("create_time"));
                user.setSalt(rs.getString("salt"));
            }else{
                return null;
            }


            String sqlRole;
            if(user.getRole().equals("ContentAdmin")) sqlRole = "SELECT * FROM content_admin WHERE user_username = ?";
            else if(user.getRole().equals("Admin")) sqlRole = "SELECT * FROM admins WHERE user_username = ?";
            else sqlRole = "SELECT * FROM customers WHERE user_username = ?";

            PreparedStatement psRole = connection.prepareStatement(sqlRole);
            psRole.setString(1,username);
            ResultSet rsRole = psRole.executeQuery();

            if(rsRole.next()){
                if(user.getRole().equals("ContentAdmin")){
                    ((ContentAdmins)user).setId(rsRole.getInt("ID"));
                    ((ContentAdmins)user).setName(rsRole.getString("NAME"));
                }else if(user.getRole().equals("Admin")){
                    ((Admins)user).setId(rsRole.getInt("ID"));
                    ((Admins)user).setName(rsRole.getString("NAME"));
                }else{
                    ((Customers)user).setId(rsRole.getInt("ID"));
                    ((Customers)user).setName(rsRole.getString("NAME"));
                }
            }
            return user;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean deleteUser(Users user){
        try{
            Connection connection = DatabaseConnector.connect();
            connection.setAutoCommit(false);

            String sqlRole;
            if(user.getRole().equals("Customer")) sqlRole = "DELETE FROM customers WHERE user_username = ?";
            else if(user.getRole().equals("ContentAdmin")) sqlRole = "DELETE FROM content_admin WHERE user_username= ?";
            else return false;

            PreparedStatement psRole = connection.prepareStatement(sqlRole);
            psRole.setString(1,user.getUsername());

            psRole.executeUpdate();


            String sqlUser = "DELETE FROM user WHERE username = ?" ;
            PreparedStatement psUser = connection.prepareStatement(sqlUser);
            psUser.setString(1,user.getUsername());

            psUser.executeUpdate();


            connection.commit();
            connection.setAutoCommit(true);

            return true;

        }catch(SQLException e){
            return false;
        }
    }



}
