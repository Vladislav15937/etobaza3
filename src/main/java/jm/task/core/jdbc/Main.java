package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vladislav", "Injewatkin", (byte) 22);
        userService.saveUser("Valeria", "Boyarskaya", (byte) 20);
        userService.saveUser("Tamara", "Injewatkina", (byte) 17);
        userService.saveUser("Andrey", "Injewatkin", (byte) 43);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
