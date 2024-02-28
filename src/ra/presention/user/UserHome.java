package ra.presention.user;

import ra.bussiness.model.*;
import ra.bussiness.service.admin.service.*;
import ra.bussiness.util.FormatDate;
import ra.bussiness.util.IOFile;
import ra.bussiness.util.InputMethods;
import ra.presention.Home;
import ra.presention.admin.SongManagement;

import java.util.*;
import java.util.stream.Collectors;

public class UserHome {
    public static List<History> historyList = IOFile.readFromFile(IOFile.HISTORY_PATH);
    public static List<Album> albumList = IOFile.readFromFile(IOFile.ALBUM_PATH);
    public static List<Song> songList = IOFile.readFromFile(IOFile.SONGS_PATH);
    public static Users userLogin = IOFile.readUserLoginFromFile(IOFile.USERSLOGIN_PATH);
    public static UserServiceImplement userService = new UserServiceImplement();

    public static SongCatagoryImplementService songCatagoryService = new SongCatagoryImplementService();

    public static HistoryServiceImplement historyService = new HistoryServiceImplement();


    public static List<SongCatagory> songCatagoryList = IOFile.readFromFile(IOFile.SONGCATEGORY_PATH);

    public static void userControler() {
        Users userLogin = IOFile.readUserLoginFromFile(IOFile.USERSLOGIN_PATH);
        SongServiceImplement songService = new SongServiceImplement();
        SongCatagoryImplementService songCatagoryService = new SongCatagoryImplementService();
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("| Chào mừng " + userLogin.getFullName() + " đến với Trang chủ|");
            System.out.println("|--------------------------------------|");
            System.out.println("| 0. Đăng xuất                         |");
            System.out.println("| 1. Tìm kiếm bài hát/ca sĩ/album      |");
            System.out.println("| 2. Hiển thị bài hát                  |");
            System.out.println("| 3. Hiển thị bài hát trending         |");
            System.out.println("| 4. Hiển thị ca sĩ trending           |");
            System.out.println("| 5. Hiển thị album trending           |");
            System.out.println("| 6. Danh sách các bài hát đã mua      |");
            System.out.println("| 7. Hiển thị bài hát theo danh mục    |");
            System.out.println("| 8. Công cụ tìm kiếm nâng cao         |");
            System.out.println("| 9. Mua bài hát                       |");
            System.out.println("| 10. Thêm vào yêu thích               |");
            System.out.println("| 11. Trang bài hát yêu thích          |");
            System.out.println("| 12. Quản lý thông tin cá nhân        |");
            System.out.println("|--------------------------------------");

            System.out.println("Chọn một tùy chọn: ");

            byte choice = InputMethods.getByte();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    findByName();
                    break;
                case 2:
                    displayActiveSong();
                    break;
                case 3:
                    displayTrendingSong();
                    break;
                case 4:
                    displayTrendingSinger();
                    break;
                case 5:
                    displayTrendingAlbum();
                    break;
                case 6:
                    displayBoughtSong();
                    break;
                case 7:
                    displaySongByCatagory();
                    break;
                case 8:
                    break;
                case 9:
                    buySong();
                    break;
                case 10:
                    addFavouriteSong();
                    break;
                case 11:
                    FavouriteSong();
                    break;
                case 12:
                    UserInfomation.UserInfomation();
                    break;

                default:
                    System.out.println("Mời nhập lại tuỳ chọn");
            }
        }
    }

    public static void findByName() {
        System.out.println("Nhập tên tìm kiếm");
        String searchName = InputMethods.getString();
        boolean flag = true;

        for (Album album : albumList) {
            if (album.getName().contains(searchName) || album.getSinger().getSingerName().contains(searchName)) {
                System.out.println(album);
                flag = false;
            }

            for (Song song : album.getSong()) {
                if (song.getProductName().contains(searchName)) {
                    System.out.println(album);
                    flag = false;
                    break; // Ngừng kiểm tra các bài hát nếu đã in album một lần
                }
            }

            if (!flag) {
                break; // Ngừng kiểm tra các album nếu đã in ít nhất một lần
            }
        }

        if (flag) {
            System.out.println("Không tìm thấy kết quả");
        }

    }

    public static void displaySongByCatagory() {
        if (SongCatagoryImplementService.songCatagoryList.isEmpty()) {
            System.out.println("Danh sach danh mục rỗng");
        }
        System.out.println("Danh sách danh mục");
        for (SongCatagory songCatagory : SongCatagoryImplementService.songCatagoryList) {
            System.out.println(songCatagory);
        }

        while (true) {
            boolean flag = true;
            System.out.println("Chọn id danh mục");
            int idCatogory = InputMethods.getInteger();
            for (Song song : songList) {
                if (song.getSongCatagory().getIdCatagory() == idCatogory) {
                    System.out.println(song);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.err.println("Id không tồn tại");
            }
            break;
        }
    }

    public static void displayActiveSong() {
        for (Song song : SongServiceImplement.songList) {
            if (song.isActiveSong()) {
                System.out.println(song);
            }
        }
    }

    public static void buySong() {
        List<Song> listSong = new ArrayList<>();
        System.out.println("Danh sách bài hát");
        displayActiveSong();
        System.out.println("Nhập số lượng bài hát muốn mua");
        int quantity = InputMethods.getInteger();
        for (int i = 0; i < quantity; i++) {
            while (true) {
                System.out.println("Nhập id bài hát muốn mua");
                int idSong = InputMethods.getInteger();
                boolean flag = true;
                for (Song song : SongServiceImplement.songList) {
                    if (song.getSongId() == idSong) {
                        listSong.add(song);
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.err.println("Không tìm thấy bài hát vui long nhập lại");
                } else {
                    System.out.println("Đã mua thành công");
                    break;
                }
            }
        }
        double totalPrice = 0;
        for (Song song : listSong) {
            totalPrice+=song.getSongPrice();
        }
        History history = new History();
        history.setSongList(listSong);
        history.setOrderAt(FormatDate.formattedDate());
        history.setHistoryId(getNewId());
        history.setUser(userLogin);
        UUID uuid = UUID.randomUUID();
        history.setSerialNumber(uuid.toString());
        history.setCreatedAt(FormatDate.formattedDate());
        history.setTotalPrice(totalPrice);
        historyList.add(history);
        IOFile.writeToFile(IOFile.HISTORY_PATH, historyList);
    }

    public static int getNewId() {
        int maxId = 0;
        for (History history : HistoryServiceImplement.historyList) {
            if (history.getHistoryId() > maxId) {
                maxId = history.getHistoryId();
            }
        }
        return maxId + 1;
    }

    public static void displayBoughtSong() {
        for (History history : historyList) {
            if (history.getCheck() == E.ACCEPT && history.getUser().getUsersId() == userLogin.getUsersId()) {
                System.out.println(history.getSongList());
            }
        }
    }

    public static void addFavouriteSong() {
        System.out.println("Danh sách bài hát");
        displayActiveSong();
        System.out.println("Nhập số lượng bài hát yêu thích muốn thêm vào");
        int quantity = InputMethods.getInteger();
        for (int i = 0; i < quantity; i++) {
            while (true) {
                System.out.println("Nhập id bài hát muốn thêm");
                int idSong = InputMethods.getInteger();
                boolean flag = true;
                for (Song song : SongServiceImplement.songList) {
                    if (song.getSongId() == idSong) {
                        Song songExist = userLogin.getFavouriteSong().stream().filter(e -> e.getSongId() == idSong).findFirst().orElse(null);
                        if (songExist == null) {
                            userLogin.getFavouriteSong().add(song);
                            flag = false;
                            break;
                        }
                        break;
                    }
                }
                if (flag) {
                    System.err.println("Không tìm thấy bài hát vui long nhập lại");
                } else {
                    System.out.println("Đã thêm thành công");
                    break;
                }
            }
        }
        UserServiceImplement.usersList.set(UserServiceImplement.usersList.indexOf(userService.findById(userLogin.getUsersId())), userLogin);
        IOFile.writeToFile(IOFile.USERS_PATH, UserServiceImplement.usersList);
    }

    public static void FavouriteSong() {
        Users userLogin = IOFile.readUserLoginFromFile(IOFile.USERSLOGIN_PATH);
        for (Song song : userLogin.getFavouriteSong()) {
            System.out.println(song);
        }
    }

    public static void displayTrendingSong() {
        Map<String, Integer> songIntegerMap = new HashMap<>();
        List<Users> userList = IOFile.readFromFile(IOFile.USERS_PATH);
        for (Users users : userList) {
            for (Song song : users.getFavouriteSong()) {
                songIntegerMap.put(song.getProductName(), songIntegerMap.getOrDefault(song.getProductName(), 0) + 1);
            }
        }
        // Chuyển Map thành List<Map.Entry>

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(songIntegerMap.entrySet());

        // Sắp xếp List bằng Comparator giảm dần theo giá trị

        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Tạo một Map mới từ List đã sắp xếp
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // In Map đã sắp xếp
        for (Map.Entry<String, Integer> songIntegerEntry : sortedMap.entrySet()) {
            System.out.println(songIntegerEntry);
        }
    }

    public static void displayTrendingSinger() {
        Map<String, Integer> singerIntegerMap = new HashMap<>();
        List<Users> userList = IOFile.readFromFile(IOFile.USERS_PATH);
        for (Users users : userList) {
            for (Song song : users.getFavouriteSong()) {
                singerIntegerMap.put(song.getSingerName().getSingerName(), singerIntegerMap.getOrDefault(song.getSingerName().getSingerName(), 0) + 1);
            }
        }
        // Chuyển Map thành List<Map.Entry>

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(singerIntegerMap.entrySet());

        // Sắp xếp List bằng Comparator giảm dần theo giá trị

        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Tạo một Map mới từ List đã sắp xếp
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // In Map đã sắp xếp
        for (Map.Entry<String, Integer> singerIntegerEntry : sortedMap.entrySet()) {
            System.out.println(singerIntegerEntry);
        }
    }

    public static void displayTrendingAlbum() {
    }
}



