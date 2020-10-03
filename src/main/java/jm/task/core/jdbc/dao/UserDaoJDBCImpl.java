package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection con = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    " (Id INT PRIMARY KEY AUTO_INCREMENT," +
                    " Name varchar(30)," +
                    " LastName varchar(35)," +
                    " Age INT )";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Database has been created!");
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }


    }

    public void dropUsersTable() {
        try {
            String sql = "DROP TABLE users";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sql = "INSERT INTO users (Name, LastName, Age) VALUES (?, ?, ? )";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            String sql = "DELETE FROM users WHERE Id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            String sql = "SELECT*FROM users";
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getLong(4));
                allUsers.add(user);
            }
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            String sql = "DELETE FROM users";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
