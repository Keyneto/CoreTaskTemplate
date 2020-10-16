package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();


        userService.saveUser("Вася", "Пупкин", (byte) 20);
        userService.saveUser("Коля", "Кукурузкин", (byte) 40);
        userService.saveUser("Женечек", "Коков", (byte) 25);
        userService.saveUser("Ванечек", "Алексеев", (byte) 25);
        List<User> listUsers = userService.getAllUsers();
        for (User us : listUsers) System.out.println(us);
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

}
