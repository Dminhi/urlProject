package ra.presention.admin;

import ra.bussiness.model.Singer;
import ra.bussiness.service.admin.service.SingerServiceImplement;
import ra.bussiness.util.InputMethods;

public class SingerManagement {
    static SingerServiceImplement singerService = new SingerServiceImplement();
    public static void SingerController(){
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("| Quản lý ca sỹ                        |");
            System.out.println("|--------------------------------------|");
            System.out.println("| 0. Quay lại                          |");
            System.out.println("| 1. Hiển thị danh sách ca sĩ          |");
            System.out.println("| 2. Thêm mới ca sĩ                    |");
            System.out.println("| 3. Sửa thông tin ca sĩ               |");
            System.out.println("| 4. Xoá ca sĩ                         |");
            System.out.println("| 5. Tìm kiếm ca sĩ theo id            |");
            System.out.println("| 6. Tìm kiếm ca sĩ theo tên           |");
            System.out.println("|--------------------------------------");
            System.out.println("Chọn một tùy chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displaySinger();
                    break;
                case 2:
                    addNewSinger();
                    break;
                case 3:
                    updateSinger();
                    break;
                case 4:
                    deleteSinger();
                    break;
                case 5:
                    findSinger();
                    break;
                case 6:
                    findSingerByName();
                    break;
                default:
                    System.out.println("Mời nhập lại");
            }
        }
    }
    public static void addNewSinger() {
        System.out.println("Số lượng ca sĩ cần thêm");
        byte quantity = InputMethods.getByte();
        for (int i = 1; i <= quantity; i++) {
            System.out.println("Nhập thông tin ca sĩ thứ " + i);
            Singer newSinger = new Singer();
            newSinger.inputData();
            singerService.save(newSinger);
            System.out.println();
        }
        System.out.println("Đã thêm mới " + quantity + " ca sĩ");
    }
    public static void displaySinger(){
        if (singerService.findAll().isEmpty()){
            System.err.println("Danh sách rỗng");
            return;
        }
        for (Singer singer : singerService.findAll()) {
            System.out.println(singer);
        }
    }
    public static void updateSinger(){
        System.out.println("Nhập id danh muc cần sửa");
        int idSinger = InputMethods.getInteger();
        Singer editSinger = singerService.findById(idSinger);
        if (editSinger == null){
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println("Thông tin ca sĩ cần sửa");
        System.out.println(editSinger);
        System.out.println("Nhập thông tin mới");
        editSinger.inputData();
        singerService.save(editSinger);
        System.out.println("Cập nhật thành công");
    }
    public static void deleteSinger(){
        System.out.println("Nhập id danh muc cần xoá");
        int idSinger = InputMethods.getInteger();
        Singer deleteSinger = singerService.findById(idSinger);
        if (deleteSinger==null){
            System.out.println("Không tồn tại id");
            return;
        }
        singerService.deleteById(idSinger);
        System.out.println("Xoá thành công");

    }
    public static void findSinger(){
        System.out.println("Nhập id ca sĩ cần tìm");
        int idSinger = InputMethods.getInteger();
        Singer findSinger = singerService.findById(idSinger);
        if (findSinger == null){
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println(findSinger);;
    }
    public static void findSingerByName() {
        System.out.println("Nhập tên ca sĩ cần tìm kiếm");
        String singerName = InputMethods.getString();
        System.out.println(singerService.findByName(singerName));
    }
}
