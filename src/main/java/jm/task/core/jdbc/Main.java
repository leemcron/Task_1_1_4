package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {



    public static void main(String[] args) throws SQLException {

        final UserService userService = new UserServiceImpl();
        
        userService.createUsersTable();

        User user1 = new User("Tom", "Hardy", (byte) 44);
        userService.saveUser(user1.getName(),user1.getLastName(),user1.getAge());

        User user2 = new User("Asal", "Brit", (byte) 33);
        userService.saveUser(user2.getName(),user2.getLastName(),user2.getAge());

        User user3 = new User("Simon", "Sidor", (byte) 33);
        userService.saveUser(user3.getName(),user3.getLastName(),user3.getAge());

        List<User> allUsers = userService.getAllUsers();

        allUsers.forEach(System.out::println);

       userService.cleanUsersTable();
    userService.dropUsersTable();

    }
}
