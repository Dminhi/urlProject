package ra.presention.admin;

import ra.bussiness.model.E;
import ra.bussiness.model.History;
import ra.bussiness.service.admin.service.HistoryServiceImplement;
import ra.bussiness.util.IOFile;
import ra.bussiness.util.InputMethods;

import java.util.ArrayList;
import java.util.List;

public class HistoryManagement {
    static HistoryServiceImplement historyService = new HistoryServiceImplement();

    public static void HistoryController() {
        while (true) {
            System.out.println("\033[34m╔══════════════════════════════════════╗");
            System.out.println("║         Quản lý lịch sử               ║");
            System.out.println("║═══════════════════════════════════════║");
            System.out.println("║  0. Quay lại                          ║");
            System.out.println("║  1. Hiển thị danh sách lịch sử mua    ║");
            System.out.println("║  2. Tìm kiếm lịch sử                  ║");
            System.out.println("║  3. Duyệt đơn hàng                    ║");
            System.out.println("║═══════════════════════════════════════║");
            System.out.println("║  Chọn một tùy chọn:                   ║");
            System.out.println("╚═══════════════════════════════════════╝\033[0m");
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
        HistoryServiceImplement historyService = new HistoryServiceImplement();
        if (historyService.findAll().isEmpty()) {
            System.err.println("Danh sách rỗng");
            return;
        }
        System.out.println("Danh sách đơn hàng cần duyệt");
        List<History> historyList = new ArrayList<>();
        for (History history : historyService.findAll()) {
            if (history.getCheck() == E.WAITING) {
                historyList.add(history);
            }
        }
        if(historyList.isEmpty()){
            System.out.println("Không còn đơn hàng để duyệt");
            return;
        } else {
            for (History history : historyList) {
                System.out.println(history);
            }
        }
        System.out.println("Nhập id　đơn hàng cần duyệt");
        int idOrder = InputMethods.getInteger();
        History historyOrder = historyService.findById(idOrder);
        if (historyOrder == null) {
            System.out.println("Không tồn tại id đơn hàng cần duyệt");
        } else {
            while (true) {
                System.out.println("Có chấp nhận đơn hàng không");
                System.out.println("1 : Có , 2: Không");
                int choice = InputMethods.getInteger();
                switch (choice) {
                    case 1:
                        for (History history : HistoryServiceImplement.historyList) {
                            if (history.getHistoryId() == idOrder) {
                                history.setCheck(E.ACCEPT);
                                historyOrder.getUser().getMyAlbum().addAll(historyOrder.getSongList());
                            }
                        }

                        break;
                    case 2:
                        for (History history : HistoryServiceImplement.historyList) {
                            if (history.getHistoryId() == idOrder) {
                                history.setCheck(E.DISAAGREE);
                            }
                        }
                        break;
                    default:
                        System.out.println("Mời nhập lại");
                }
                break;
            }
        }
        IOFile.writeToFile(IOFile.HISTORY_PATH, HistoryServiceImplement.historyList);
    }

    public static void findHistoryByUserName() {
        System.out.println("Nhập userName cần tìm kiếm");
        String userName = InputMethods.getString();
        for (History history : historyService.findAll()) {
            if (history.getUser().getUserName().contains(userName)) {
                System.out.println(history);
            }
        }
    }
}
