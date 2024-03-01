package ra.presention.admin;

import ra.bussiness.model.Album;
import ra.bussiness.model.History;
import ra.bussiness.model.Song;
import ra.bussiness.model.Users;
import ra.bussiness.service.admin.service.AlbumServiceImplement;
import ra.bussiness.service.admin.service.HistoryServiceImplement;
import ra.bussiness.util.IOFile;
import ra.bussiness.util.InputMethods;
import ra.bussiness.util.Validation;

import java.util.List;

public class Dashboard {
    static HistoryServiceImplement historyService = new HistoryServiceImplement();

    public static void DashboardController() {
        while (true) {
            System.out.println("\033[34m╔════════════════════════════════════════╗");
            System.out.println("║ Quản lý Dashboard                                    ║");
            System.out.println("║------------------------------------------------------║");
            System.out.println("║ 0. Quay lại                                          ║");
            System.out.println("║ 1. Thống kê đơn hàng theo ngày/tháng/năm             ║");
            System.out.println("║ 2. Thống kê doanh thu theo ngày/tháng/năm            ║");
            System.out.println("║ 3. Thống kê bài hát theo ca sĩ/album/ngày phát hành  ║");
            System.out.println("║ 4. Thống kê user theo từng loại tài khoản            ║");
            System.out.println("║------------------------------------------------------║");
            System.out.println("║ Chọn một tùy chọn:                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════╝\033[0m");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    statisticalByTime();
                    break;
                case 2:
                    statisticalIncomeByTime();
                    break;
                case 3:
                    statisticalSongBySingerAlbumDate();
                    break;
                case 4:
                    statisticalUserByType();
                    break;
                default:
                    System.out.println("Mời nhập lại");
            }
        }
    }

    public static void statisticalByTime() {
        List<History> historyList = IOFile.readFromFile(IOFile.HISTORY_PATH);
        System.out.println("Nhập lựa chọn thống kê \n 1: Theo ngày \n 2 : Theo tháng \n 3 : Theo năm");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
                while (true) {
                    System.out.println("Nhập ngày muốn thống kê theo định dang dd/MM/yyyy");
                    String orderTime = InputMethods.getString();
                    if (Validation.validateDate(orderTime)) {
                        int orderQuantity = 0;
                        for (History history : historyList) {
                            if (history.getCreatedAt().equals(orderTime)) {
                                orderQuantity++;
                            }
                        }
                        System.out.println("Số đơn hàng trong ngày " + orderTime + " là : " + orderQuantity);
                        break;
                    } else {
                        System.err.println("Nhập không đúng định dạng mời nhập lại");
                    }
                }
                break;

            case 2:
                while (true) {
                    System.out.println("Nhập ngày muốn thống kê theo định dang MM/yyyy");
                    String orderMonth = InputMethods.getString();
                    if (Validation.validateMonth(orderMonth)) {
                        int orderQuantity = 0;
                        for (History history : historyList) {
                            if (history.getCreatedAt().contains(orderMonth)) {
                                orderQuantity++;
                            }
                        }
                        System.out.println("Số đơn hàng trong tháng " + orderMonth + " là : " + orderQuantity);
                        break;
                    } else {
                        System.err.println("Nhập không đúng định dạng mời nhập lại");
                    }
                }
                break;
            case 3:
                while (true) {
                    System.out.println("Nhập ngày muốn thống kê theo định dang yyyy");
                    String orderYear = InputMethods.getString();
                    if (Validation.validateYear(orderYear)) {
                        int orderQuantity = 0;
                        for (History history : historyList) {
                            if (history.getCreatedAt().contains(orderYear)) {
                                orderQuantity++;
                            }
                        }
                        System.out.println("Số đơn hàng trong năm " + orderYear + " là : " + orderQuantity);
                        break;
                    } else {
                        System.err.println("Nhập không đúng định dạng mời nhập lại");
                    }
                }
                break;
            default:
                System.out.println("Mời nhập lại");
        }
    }

    public static void statisticalIncomeByTime() {
        List<History> historyList = IOFile.readFromFile(IOFile.HISTORY_PATH);
        System.out.println("Nhập lựa chọn thống kê doanh thu \n 1: Theo ngày \n 2 : Theo tháng \n 3 : Theo năm");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
                while (true) {
                    System.out.println("Nhập ngày muốn thống kê doanh thu theo định dang dd/MM/yyyy");
                    String orderTime = InputMethods.getString();
                    if (Validation.validateDate(orderTime)) {
                        double orderQuantity = 0;
                        for (History history : historyList) {
                            if (history.getCreatedAt().equals(orderTime)) {
                                orderQuantity +=  history.getTotalPrice();
                            }
                        }
                        System.out.println("Doanh thu trong ngày " + orderTime + " là : " + orderQuantity);
                        break;
                    } else {
                        System.err.println("Nhập không đúng định dạng mời nhập lại");
                    }
                }
                break;

            case 2:
                while (true) {
                    System.out.println("Nhập ngày muốn thống kê doanh thu theo định dang MM/yyyy");
                    String orderMonth = InputMethods.getString();
                    if (Validation.validateMonth(orderMonth)) {
                        double orderQuantity = 0;
                        for (History history : historyList) {
                            if (history.getCreatedAt().contains(orderMonth)) {
                                orderQuantity += history.getTotalPrice();

                            }
                        }
                        System.out.println("Doanh thu trong tháng " + orderMonth + " là : " + orderQuantity);
                        break;
                    } else {
                        System.err.println("Nhập không đúng định dạng mời nhập lại");
                    }
                }
                break;
            case 3:
                while (true) {
                    System.out.println("Nhập ngày muốn thống kê doanh thu theo định dang yyyy");
                    String orderYear = InputMethods.getString();
                    if (Validation.validateYear(orderYear)) {
                        double orderQuantity = 0;
                        for (History history : historyList) {
                            if (history.getCreatedAt().contains(orderYear)) {
                                orderQuantity += history.getTotalPrice();

                            }
                        }
                        System.out.println("Doanh thu trong năm " + orderYear + " là : " + orderQuantity);
                        break;
                    } else {
                        System.err.println("Nhập không đúng định dạng mời nhập lại");
                    }
                }
                break;
            default:
                System.out.println("Mời nhập lại");
        }
    }

    public static void statisticalSongBySingerAlbumDate() {
        List<Song> songList = IOFile.readFromFile(IOFile.SONGS_PATH);
        System.out.println("Nhập lựa chọn thống kê bài hát \n 1: Ca sĩ \n 2 : Album \n 3 : Theo thời gian");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
                System.out.println("Nhập tên ca sĩ cần thống kê bài hát");
                String singerName = InputMethods.getString();
                for (Song song : songList) {
                    if(song.getSingerName().getSingerName().contains(singerName)){
                        System.out.println(song.getProductName());
                    } else {
                        System.out.println("Ca sĩ không tồn tại");
                    }
                }
                break;
            case 2:
                System.out.println("Nhập tên album cần thống kê bài hát");
                String albumName = InputMethods.getString();
                for (Song song : songList) {
                    if(song.getAlbum().getName().equals(albumName)){
                        System.out.println(song);
                    }
                }
                break;
            case 3:while (true) {
                System.out.println("Nhập ngày muốn thống kê bài hát theo định dang dd/MM/yyyy");
                String orderTime = InputMethods.getString();
                if (Validation.validateDate(orderTime)) {
                    int orderQuantity = 0;
                    for (Song song : songList) {
                        if (song.getCreatedAt().equals(orderTime)) {
                            orderQuantity ++;
                        }
                    }
                    System.out.println("Bài hát được tạo trong ngày " + orderTime + " là : " + orderQuantity);
                    break;
                } else {
                    System.err.println("Nhập không đúng định dạng mời nhập lại");
                }
            }
                break;
        }
    }
    public static void statisticalUserByType(){
        List<Users> usersList = IOFile.readFromFile(IOFile.USERS_PATH);
        boolean flag = true;
        int normal = 0;
        int vip = 0;

        for (Users users : usersList) {
            if(users.getAccountType()){
                normal++;
            } else {
                vip++;
            }
        }
        System.out.println("Số tài khoản thường là " + normal);
        System.out.println("Số tài khoản vip là "+vip);
    }
}
