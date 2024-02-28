package ra.presention.admin;

import ra.bussiness.model.Album;
import ra.bussiness.model.Singer;
import ra.bussiness.service.admin.service.AlbumServiceImplement;
import ra.bussiness.service.admin.service.SingerServiceImplement;
import ra.bussiness.service.admin.service.SongServiceImplement;
import ra.bussiness.util.InputMethods;

public class AlbumManagement {
    public static SongServiceImplement songService = new SongServiceImplement();

    public static SingerServiceImplement singerService = new SingerServiceImplement();
    static AlbumServiceImplement albumService = new AlbumServiceImplement();
    public static void AlbumController(){
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("| Quản lý album                        |");
            System.out.println("|--------------------------------------|");
            System.out.println("| 0. Quay lại                          |");
            System.out.println("| 1. Hiển thị danh sách album          |");
            System.out.println("| 2. Thêm mới album                    |");
            System.out.println("| 3. Sửa thông tin album               |");
            System.out.println("| 4. Xoá album                         |");
            System.out.println("| 5. Tìm kiếm album                    |");
            System.out.println("| 6. Tìm kiếm theo tên                 |");
            System.out.println("|--------------------------------------");
            System.out.println("Chọn một tùy chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displayAlbum();
                    break;
                case 2:
                    addNewAlbum();
                    break;
                case 3:
                    updateAlbum();
                    break;
                case 4:
                    deleteAlbum();
                    break;
                case 5:
                    findAlbum();
                    break;
                case 6:
                    findAlbumByName();
                default:
                    System.out.println("Mời nhập lại lựa chọn");
            }
        }
    }
    public static void addNewAlbum() {
        System.out.println("Số lượng Album cần thêm");
        byte quantity = InputMethods.getByte();
        for (int i = 1; i <= quantity; i++) {
            System.out.println("Nhập thông tin Album thứ " + i);
            Album newAlbum = new Album();
            newAlbum.inputData();
            albumService.save(newAlbum);
            System.out.println();
        }
        System.out.println("Đã thêm mới " + quantity + " ca sĩ");
    }
    public static void displayAlbum(){
        if (albumService.findAll().isEmpty()){
            System.err.println("Danh sách rỗng");
            return;
        }
        for (Album album : albumService.findAll()) {
            System.out.println(album);
        }
    }
    public static void updateAlbum(){
        System.out.println("Nhập id danh muc cần sửa");
        int idAlbum = InputMethods.getInteger();
        Album editAlbum = albumService.findById(idAlbum);
        if (editAlbum == null){
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println("Thông tin Album cần sửa");
        System.out.println(editAlbum);
        System.out.println("Nhập thông tin mới");
        editAlbum.inputData();
        albumService.save(editAlbum);
        System.out.println("Cập nhật thành công");
    }
    public static void deleteAlbum(){
        System.out.println("Nhập id Album cần xoá");
        int idAlbum = InputMethods.getInteger();
        Album deleteAlbum = albumService.findById(idAlbum);
        if (deleteAlbum==null){
            System.out.println("Không tồn tại id");
            return;
        }
        albumService.deleteById(idAlbum);
        System.out.println("Xoá thành công");

    }
    public static void findAlbum(){
        System.out.println("Nhập id Album cần tìm");
        int idAlbum = InputMethods.getInteger();
        Album findAlbum = albumService.findById(idAlbum);
        if (findAlbum == null){
            System.err.println("Không tồn tại id");
            return;
        }
        System.out.println(findAlbum);;
    }
    public static void findAlbumByName() {
        System.out.println("Nhập tên tài khoản cần tìm kiếm");
        String albumName = InputMethods.getString();
        System.out.println(albumService.findByName(albumName));
    }
}
