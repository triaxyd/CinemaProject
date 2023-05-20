package com.triaxyd.users;

import com.triaxyd.database.DatabaseConnector;
import org.mindrot.jbcrypt.BCrypt;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                    if (password.equals("ImAdmin123")){
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
                    }else if(password.equals("ImContentAdmin123")){
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
                    user.setRole(checkRole(resultSet));
                    user.setSalt(resultSet.getString("salt"));
                    return user;
                }else{
                    //wrong password
                }
            }else{
                //No account was found
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    return user;
    }



    public String checkRole(ResultSet resultSet)  {
        String role = null;
        try {
            role = resultSet.getString("role");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (role.equals("Admin")){
            return "Admin";
        }else if(role.equals("ContentAdmin")){
            return "ContentAdmin";
        }else{
            return "Customer";
        }
    }


}
