package ra.presention.user;

import ra.bussiness.model.Singer;
import ra.bussiness.model.Users;
import ra.bussiness.service.admin.service.UserServiceImplement;
import ra.bussiness.util.FormatDate;
import ra.bussiness.util.HashCode;
import ra.bussiness.util.IOFile;
import ra.bussiness.util.InputMethods;

import java.util.Objects;

public class UserInfomation {
    static Users users = IOFile.readUserLoginFromFile(IOFile.USERSLOGIN_PATH);
    static UserServiceImplement userService = new UserServiceImplement();

    public static void UserInfomation() {
        Users users = IOFile.readUserLoginFromFile(IOFile.USERSLOGIN_PATH);
        while (true) {
            System.out.println("\033[34m╔══════════════════════════════════════╗");
            System.out.println("║ Quản lý thông tin cá nhân            ║");
            System.out.println("║══════════════════════════════════════║");
            System.out.println("║ 0. Quay lại                          ║");
            System.out.println("║ 1. Hiển thị thông tin cá nhân        ║");
            System.out.println("║ 2. Chỉnh sửa thông tin cá nhân       ║");
            System.out.println("║ 3. Đổi mật khẩu                      ║");
            System.out.println("║══════════════════════════════════════║");
            System.out.println("║ Chọn một tùy chọn:                   ║");
            System.out.println("╚══════════════════════════════════════╝\033[0m");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.println(users);
                    break;
                case 2:
                    updateInforUser();
                    break;
                case 3:
                    changePassword();
                    break;
                default:
                    System.out.println("Mời nhập lại lựa chọn");
            }
        }
    }

    public static void updateInforUser() {
        Users users = IOFile.readUserLoginFromFile(IOFile.USERSLOGIN_PATH);
        System.out.println("Thông tin user cần sửa");
        System.out.println(users);
        System.out.println("Nhập thông tin mới");
        users.inputData(false);
        userService.save(users);
        System.out.println("Cập nhật thành công");
    }

    public static void changePassword() {
        Users users = IOFile.readUserLoginFromFile(IOFile.USERSLOGIN_PATH);
        while (true) {
            System.out.println("Nhập mật khẩu cũ");
            String oldPassWord = InputMethods.getString();
            if (Objects.equals(HashCode.hashPassword(oldPassWord), users.getPassword())) {
                System.out.println("Nhập thông tin mật khẩu mới");
                String newPassword = InputMethods.getString();
                users.setPassword(HashCode.hashPassword(newPassword));
                users.setUpdatedAt(FormatDate.formattedDate());
                userService.save(users);
                System.out.println("Đổi nhập khẩu thành công");
                break;
            } else {
                System.out.println("Sai mật khẩu, mời nhập lại");
            }
        }
    }
}
