package ra.presention.admin;

import ra.bussiness.model.Singer;
import ra.bussiness.model.Song;
import ra.bussiness.model.Users;
import ra.bussiness.service.admin.service.SingerServiceImplement;
import ra.bussiness.service.admin.service.SongServiceImplement;
import ra.bussiness.service.admin.service.UserServiceImplement;
import ra.bussiness.util.InputMethods;

public class SongManagement {
    static SongServiceImplement songService = new SongServiceImplement();
    public static SingerServiceImplement singerService = new SingerServiceImplement();

    public static void SongController() {
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("| Quản lý bài hát                      |");
            System.out.println("|--------------------------------------|");
            System.out.println("| 0. Quay lại                          |");
            System.out.println("| 1. Hiển thị danh sách bài hát        |");
            System.out.println("| 2. Thêm mới bài hát                  |");
            System.out.println("| 3. Sửa thông tin bài hát             |");
            System.out.println("| 4. Xoá bài hát                       |");
            System.out.println("| 5. Tìm kiếm bài hát                  |");
            System.out.println("| 6. Tìm kiếm bài hát theo tên         |");
            System.out.println("| 7. Thay đổi trạng thái bài hát         |");
            System.out.println("|--------------------------------------");
            System.out.println("Chọn một tùy chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displaySong();
                    break;
                case 2:
                    addNewSong();
                    break;
                case 3:
                    updateSong();
                    break;
                case 4:
                    deleteSong();
                    break;
                case 5:
                    findSong();
                    break;
                case 6:
                    findSongByName();
                    break;
                case 7:
                    changeSongStatus();
                    break;
                default:
                    System.out.println("Mời nhập lại lựa chọn");
            }
        }
    }

    public static void addNewSong() {
        System.out.println("Số lượng bài hát cần thêm");
        byte quantity = InputMethods.getByte();
        for (int i = 1; i <= quantity; i++) {
            System.out.println("Nhập thông tin bài hát thứ " + i);
            Song newSong = new Song();
            newSong.inputData();
            songService.save(newSong);
            System.out.println();
        }
        System.out.println("Đã thêm mới " + quantity + " ca sĩ");
    }

    public static void displaySong() {
        if (songService.findAll().isEmpty()) {
            System.err.println("Danh sách rỗng");
            return;
        }
        for (Song song : songService.findAll()) {
            System.out.println(song);
        }
    }

    public static void updateSong() {
        System.out.println("Danh sách bài hát");
        for (Song song : SongServiceImplement.songList) {
            System.out.println(song);
        }
        System.out.println("Nhập id bài hát cần sửa");
        int idSong = InputMethods.getInteger();
        Song editSong = songService.findById(idSong);
        if (editSong == null) {
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println("Thông bài hát cần sửa");
        System.out.println(editSong);
        System.out.println("Nhập thông tin mới");
        editSong.inputData();
        songService.save(editSong);
        System.out.println("Cập nhật thành công");
    }

    public static void deleteSong() {
        System.out.println("Nhập id danh muc cần xoá");
        int idSong = InputMethods.getInteger();
        Song deleteSong = songService.findById(idSong);
        if (deleteSong == null) {
            System.out.println("Không tồn tại id");
            return;
        }
        songService.deleteById(idSong);
        System.out.println("Xoá thành công");

    }

    public static void findSong() {
        System.out.println("Nhập id sản phẩm cần tìm");
        int idSong = InputMethods.getInteger();
        Song findSong = songService.findById(idSong);
        if (findSong == null) {
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println(findSong);
        ;
    }

    public static void findSongByName() {
        System.out.println("Nhập tên tài khoản cần tìm kiếm");
        String songName = InputMethods.getString();
        System.out.println(songService.findByName(songName));
    }

    public static void changeSongStatus(){
        System.out.println("Danh sach baì hát");
        for (Song song : SongServiceImplement.songList) {
            System.out.println(song);
        }
        System.out.println("Nhập id cần đổi trạng thái");
        int id = InputMethods.getInteger();
        songService.changeStatusSongById(id);
    }
}
