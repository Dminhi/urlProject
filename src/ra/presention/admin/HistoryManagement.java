package ra.presention.admin;

import ra.bussiness.model.E;
import ra.bussiness.model.History;
import ra.bussiness.service.admin.service.HistoryServiceImplement;
import ra.bussiness.util.IOFile;
import ra.bussiness.util.InputMethods;

import java.util.List;

public class HistoryManagement {
    static HistoryServiceImplement historyService = new HistoryServiceImplement();

    public static void HistoryController() {
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("| Quản lý lịch sử                      |");
            System.out.println("|--------------------------------------|");
            System.out.println("| 0. Quay lại                          |");
            System.out.println("| 1. Hiển thị danh sách lịch sử mua    |");
            System.out.println("| 2. Tìm kiếm lịch sử                  |");
            System.out.println("| 3. Duyệt đơn hàng                    |");
            System.out.println("|--------------------------------------");
            System.out.println("Chọn một tùy chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displayHistory();
                    break;
                case 2:
                    findHistoryByUserName();
                    break;
                case 3:
                    checkOrder();
                    break;
                default:
                    System.out.println("Mời nhập lại");
            }
        }
    }

    public static void displayHistory() {
        if (historyService.findAll().isEmpty()) {
            System.err.println("Danh sách rỗng");
            return;
        }
        for (History history : historyService.findAll()) {
            System.out.println(history);
        }
    }

    public static void checkOrder() {
        if (historyService.findAll().isEmpty()) {
            System.err.println("Danh sách rỗng");
            return;
        }
        for (History history : HistoryServiceImplement.historyList) {
            if(history.getCheck()==E.WAITING){
                System.out.println(history);
            }
        }
        System.out.println("Nhập id bài hát cần duyệt");
        int idOrder = InputMethods.getInteger();
        History historyOrder = historyService.findById(idOrder);
        if (historyOrder == null) {
            System.out.println("Không tồn tại id bài hát cần duyệt");
        } else {
            while (true) {
                System.out.println("Có chấp nhận bài hát không");
                System.out.println("1 : Có , 2: Không");
                int choice = InputMethods.getInteger();
                switch (choice){
                    case 1:
                        historyOrder.setCheck(E.ACCEPT);
                        historyOrder.getUser().getMyAlbum().addAll(historyOrder.getSongList());
                        break;
                    case 2:
                        historyOrder.setCheck(E.DISAAGREE);
                        break;
                    default:
                        System.out.println("Mời nhập lại");
                }
                break;
            }
        }
        IOFile.writeToFile(IOFile.HISTORY_PATH,HistoryServiceImplement.historyList);
    }

    public static void findHistoryByUserName(){
        System.out.println("Nhập userName cần tìm kiếm");
        String userName = InputMethods.getString();
        for (History history : historyService.findAll()) {
            if(history.getUser().getUserName().contains(userName)){
                System.out.println(history);
            }
        }
    }
}
