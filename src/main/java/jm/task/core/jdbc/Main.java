package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Робот Кава", "ПСФП5", (byte) 14);
        userService.saveUser("Держи Томик", "Дверь", (byte) 14);
        userService.saveUser("Bob2", "Второй", (byte) 14);
        userService.saveUser("Bob3", "Третий", (byte) 14);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
