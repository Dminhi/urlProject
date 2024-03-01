package ra.presention.admin;

import ra.bussiness.model.Singer;
import ra.bussiness.model.Song;
import ra.bussiness.model.SongCatagory;
import ra.bussiness.service.admin.service.SingerServiceImplement;
import ra.bussiness.service.admin.service.SongCatagoryImplementService;
import ra.bussiness.util.IOFile;
import ra.bussiness.util.InputMethods;

import java.util.List;

public class SongCatagoryManagement {
    static SongCatagoryImplementService songCatagoryService = new SongCatagoryImplementService();

    public static void SongCatagoryController() {
        while (true) {
            System.out.println("\033[34m╔══════════════════════════════════════╗");
            System.out.println("║ Quản lý danh mục bài hát             ║");
            System.out.println("║══════════════════════════════════════║");
            System.out.println("║ 0. Quay lại                          ║");
            System.out.println("║ 1. Hiển thị danh mục bài hát         ║");
            System.out.println("║ 2. Thêm mới danh mục bài hát         ║");
            System.out.println("║ 3. Sửa thông tin danh mục bài hát    ║");
            System.out.println("║ 4. Xoá danh mục                      ║");
            System.out.println("║ 5. Tìm kiếm danh mục theo id         ║");
            System.out.println("║ 6. Tìm kiếm danh mục theo tên        ║");
            System.out.println("║══════════════════════════════════════║");
            System.out.println("║ Chọn một tùy chọn:                   ║");
            System.out.println("╚══════════════════════════════════════╝\033[0m");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displaySongCatagory();
                    break;
                case 2:
                    addNewSongCatagory();
                    break;
                case 3:
                    updateSongCatagory();
                    break;
                case 4:
                    deleteSongCatagory();
                    break;
                case 5:
                    findSongCatagory();
                    break;
                case 6:
                    findSongCatagoryByName();
                    break;
                default:
                    System.out.println("Mời nhập lại");
            }
        }
    }

    public static void addNewSongCatagory() {
        System.out.println("Số lượng danh mục cần thêm");
        byte quantity = InputMethods.getByte();
        for (int i = 1; i <= quantity; i++) {
            System.out.println("Nhập thông tin danh mục thứ " + i);
            SongCatagory newSongCatagory = new SongCatagory();
            newSongCatagory.inputData();
            songCatagoryService.save(newSongCatagory);
            System.out.println();
        }
        System.out.println("Đã thêm mới " + quantity + " danh mục");
    }

    public static void displaySongCatagory() {
        if (songCatagoryService.findAll().isEmpty()) {
            System.err.println("Danh sách rỗng");
            return;
        }
        for (SongCatagory songCatagory : songCatagoryService.findAll()) {
            System.out.println(songCatagory);
        }
    }

    public static void updateSongCatagory() {
        System.out.println("Nhập id danh muc cần sửa");
        int idSongCatagory = InputMethods.getInteger();
        SongCatagory editSongCatagory = songCatagoryService.findById(idSongCatagory);
        if (editSongCatagory == null) {
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println("Thông tin danh mục cần sửa");
        System.out.println(editSongCatagory);
        System.out.println("Nhập thông tin mới");
        editSongCatagory.inputData();
        songCatagoryService.save(editSongCatagory);
        System.out.println("Cập nhật thành công");
    }

    public static void deleteSongCatagory() {
        List<Song> songList = IOFile.readFromFile(IOFile.SONGS_PATH);
        System.out.println("Nhập id danh muc cần xoá");
        int idSongCatagory = InputMethods.getInteger();
        SongCatagory deleteSongCatagory = songCatagoryService.findById(idSongCatagory);
        if (deleteSongCatagory == null) {
            System.out.println("Không tồn tại id");
            return;
        }
        boolean flag = true;
        for (Song song : songList) {
            if (song.getSongCatagory().getIdCatagory() == idSongCatagory) {
                flag = false;
                System.out.println("Không thể xoá danh mục đã có trong bài hát");
                break;
            }
        }
        if (flag) {
            songCatagoryService.deleteById(idSongCatagory);
            System.out.println("Xoá thành công");
        }
    }

    public static void findSongCatagory() {
        System.out.println("Nhập id danh mục cần tìm");
        int idSongCatagory = InputMethods.getInteger();
        SongCatagory findSongCatagory = songCatagoryService.findById(idSongCatagory);
        if (findSongCatagory == null) {
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println(findSongCatagory);
        ;
    }

    public static void findSongCatagoryByName() {
        System.out.println("Nhập tên danh mục cần tìm kiếm");
        String songCatagoryName = InputMethods.getString();
        System.out.println(songCatagoryService.findByName(songCatagoryName));
    }


}

