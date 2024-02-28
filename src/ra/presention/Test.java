package ra.presention;

import ra.bussiness.model.Users;
import ra.bussiness.service.admin.service.UserServiceImplement;
import ra.bussiness.util.HashCode;
import ra.bussiness.util.IOFile;

import java.util.List;

public class Test {
   static UserServiceImplement userServiceImplement = new UserServiceImplement();

    public static void main(String[] args) {
        Users users = new Users();
        users.setUsersId(1000);
        users.setUserName("admin123");
        users.setPassword("admin123");
        users.setRole(false);
        userServiceImplement.register(users);
    }
}
