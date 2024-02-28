package ra.presention.admin;

import ra.bussiness.model.Users;
import ra.bussiness.service.admin.IUserService;
import ra.bussiness.service.admin.service.UserServiceImplement;
import ra.bussiness.util.InputMethods;

public class UserManagement {
    public static UserServiceImplement userService = new UserServiceImplement();

    public static void userControler() {
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("| Quản lý người dùng                   |");
            System.out.println("|--------------------------------------|");
            System.out.println("| 0. Quay lại                          |");
            System.out.println("| 1. Hiển thị danh sách người dùng     |");
            System.out.println("| 2. Thêm mới tài khoản                |");
            System.out.println("| 3. Tìm kiếm                          |");
            System.out.println("| 4. Đổi trạng thái người dùng         |");
            System.out.println("|--------------------------------------");
            System.out.println("Chọn một tùy chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displayUser();
                    break;
                case 2:
                    addNewUser();
                    break;
                case 3:
                    findUserById();
                    break;
                case 4:
                    changeUserStatus();
                    break;
            }
        }
    }

    public static void displayUser() {
        if (userService.findAll().isEmpty()) {
            System.err.println("Danh sách người dùng rỗng");
            return;
        }
        for (Users users : userService.findAll()) {
            System.out.println(users);
        }
    }

    public static void findUserByName() {
        System.out.println("Nhập tên tài khoản cần tìm kiếm");
        String userName = InputMethods.getString();
        System.out.println(userService.findByName(userName));
    }
    public static void addNewUser(){
        System.out.println("Nhập số lượng người dùng cần thêm");
        int number = InputMethods.getInteger();
        for (int i = 1; i <= number; i++) {
            Users newUser = new Users();
            System.out.println("Nhập thông tin của người dùng thứ " + i);
            newUser.inputData(true);
            newUser.setUsersId(getNewId());
            userService.register(newUser);
            System.out.println("Đã thêm mới "+i+" người dùng");
        }
    }
    public static void changeUserStatus(){
        System.out.println("Danh sach người dùng");
        for (Users users : UserServiceImplement.usersList) {
            System.out.println(users);
        }
        System.out.println("Nhập id cần đổi trạng thái");
        int id = InputMethods.getInteger();
        userService.changeStatusUserById(id);
    }
    public static void findUserById(){
        System.out.println("Nhập id sản phẩm cần tìm");
        int idUser = InputMethods.getInteger();
        Users findUser = userService.findById(idUser);
        if (findUser == null){
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println(findUser);
    }
    public static int getNewId() {
        int maxId = 0;
        for (Users users : UserServiceImplement.usersList) {
            if (users.getUsersId() > maxId) {
                maxId = users.getUsersId();
            }
        }
        return maxId + 1;
    }
}
