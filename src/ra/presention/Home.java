package ra.presention;

import ra.bussiness.model.Users;
import ra.bussiness.service.admin.IUserService;
import ra.bussiness.service.admin.service.UserServiceImplement;
import ra.bussiness.util.IOFile;
import ra.bussiness.util.InputMethods;
import ra.presention.admin.AdminHome;
import ra.presention.admin.UserManagement;
import ra.presention.user.UserHome;

public class Home {
    public static IUserService userService = new UserServiceImplement();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\033[34m╔═══════════════════════════════════════╗");
            System.out.println("║  Chào mừng đến với Ứng dụng nghe nhạc ║");
            System.out.println("║═══════════════════════════════════════║\033[0m");
            System.out.println("\033[34m║  1. Đăng ký                           ║");
            System.out.println("║  2. Đăng nhập                         ║");
            System.out.println("║  3. Thoát                             ║");
            System.out.println("║═══════════════════════════════════════║");
            System.out.println("║  Chọn một tùy chọn:                   ║\033[0m");
            System.out.println("\033[34m╚═══════════════════════════════════════╝\033[0m");


            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Mời nhập lại tuỳ chọn");
            }
        }
    }

    public static void register() {
        Users user = new Users();
        System.out.println("Mời nhập thông tin đăng ký");
        user.inputData(true);
        userService.register(user);
    }

    public static void login() {
        System.out.println("Nhập tên đăng nhập");
        String userName = InputMethods.getString();
        System.out.println("Nhập mật khẩu");
        String userPassword = InputMethods.getString();
        Users userLogin = userService.login(userName, userPassword);
        if (userLogin == null) {
            System.err.println("Sai tài khoản hoặc mật khẩu");
            return;
        }
        else { if (!userLogin.getStatus()) {
            System.out.println("Tài khoản đã bị khoá");
            return;
        }}
        IOFile.writeUserLoginToFile(IOFile.USERSLOGIN_PATH, userLogin);
        System.out.println("Đăng nhập thành công");
        if (userLogin.isRole()) {
            UserHome.userControler();
            // Chuyển sang trang chủ của user
        } else {
            // Chuyển sang trang chủ của admin
            AdminHome.adminController();
        }
    }
}