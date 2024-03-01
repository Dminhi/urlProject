package ra.presention.admin;

import ra.bussiness.util.InputMethods;

public class AdminHome {
    public static void adminController() {
        while (true) {
            System.out.println("\033[34m╔════════════════════════════════════════╗");
            System.out.println("║ Chào mừng đến với Trang Admin          ║");
            System.out.println("║----------------------------------------║");
            System.out.println("║ 0. Đăng xuất                           ║");
            System.out.println("║ 1. Quản lý người dùng                  ║");
            System.out.println("║ 2. Quản lý ca sĩ                       ║");
            System.out.println("║ 3. Quản lý Album                       ║");
            System.out.println("║ 4. Quản lý bài hát                     ║");
            System.out.println("║ 5. Quản lý lịch sử                     ║");
            System.out.println("║ 6. Quản lý danh mục                    ║");
            System.out.println("║ 7. Thống kê đơn hàng                   ║");
            System.out.println("║----------------------------------------║");
            System.out.println("║ Chọn một tùy chọn:                     ║");
            System.out.println("╚════════════════════════════════════════╝\033[0m");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    UserManagement.userControler();
                    break;
                case 2:
                    SingerManagement.SingerController();
                    break;
                case 3:
                    AlbumManagement.AlbumController();
                    break;
                case 4:
                    SongManagement.SongController();
                    break;
                case 5:
                    HistoryManagement.HistoryController();
                    break;
                case 6:
                    SongCatagoryManagement.SongCatagoryController();
                    break;
                case 7:
                    Dashboard.DashboardController();
                    break;
                default:
                    System.out.println("Mời nhập lại lựa chọn");
            }
        }
    }
}
