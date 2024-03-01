package ra.bussiness.model;

import ra.bussiness.service.admin.service.*;
import ra.bussiness.util.FormatDate;
import ra.bussiness.util.InputMethods;
import ra.bussiness.util.Validation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Song implements Serializable {
    private int songId;

    private Singer singerName;
    private String productName, description, source;

    private String createdAt = FormatDate.formattedDate(), updatedAt = null;

    private List<String> image;
    private double songPrice;
    private SongCatagory songCatagory;

    private boolean activeSong = true;

    public SongCatagory getSongCatagory() {
        return songCatagory;
    }

    public void setSongCatagory(SongCatagory songCatagory) {
        this.songCatagory = songCatagory;
    }

    private Album album;

    public Song(int songId, Singer singerName, String productName, String description, String source, String createdAt, String updatedAt, List<String> image, double songPrice, SongCatagory songCatagory, boolean activeSong, Album album) {
        this.songId = songId;
        this.singerName = singerName;
        this.productName = productName;
        this.description = description;
        this.source = source;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.image = image;
        this.songPrice = songPrice;
        this.songCatagory = songCatagory;
        this.activeSong = activeSong;
        this.album = album;
    }

    public Song() {
    }

    public boolean isActiveSong() {
        return activeSong;
    }

    public void setActiveSong(boolean activeSong) {
        this.activeSong = activeSong;
    }

    public double getSongPrice() {
        return songPrice;
    }

    public void setSongPrice(double songPrice) {
        this.songPrice = songPrice;
    }

    public Album getAlbum() {
        if (album == null) {
            album = new Album();
        }
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public Singer getSingerName() {
        return singerName;
    }

    public void setSingerName(Singer singerName) {
        this.singerName = singerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Song{" +
                "songId : " + songId +
                ", singerName : " + singerName.getSingerName() +
                ", productName : '" + productName + '\'' +
                ", description : '" + description + '\'' +
                ", createdAt : '" + createdAt + '\'' +
                ", updatedAt : '" + updatedAt + '\'' +
                ", songPrice : " + songPrice +
                ", songCatagory : " + songCatagory.getCatagoryName() +
                ", activeSong : " + (activeSong?"Đang hoạt đông":"Không hoạt động") +
                ", album : " + (album==null?"null":album.getName()) +
                "}";
    }

    public void inputData() {
        while (true) {
            System.out.println("Nhập tên bài hát");
            String newNameSong = InputMethods.getString();
            if (Validation.validateEmpty(newNameSong)) {
                boolean flag = true;
                for (Song song : SongServiceImplement.songList) {
                    if (song.getProductName().equals(newNameSong)) {
                        System.err.println("Tên bài hát đã tồn tại");
                        flag = false;
                    }
                }
                if (flag) {
                    this.productName = newNameSong;
                    break;
                }
            } else {
                System.err.println("Tên bài hát không được để trống");
            }
        }

        if (SingerServiceImplement.singerList.isEmpty()) {
            System.out.println("Danh sach ca sĩ rỗng");
        }
        boolean check = true;
        while (true) {
            System.out.println("Danh sách ca sĩ ");
            for (Singer singer : SingerServiceImplement.singerList) {
                System.out.println(singer);
            }
            System.out.println("Nhập id ca sĩ");
            int idSinger = InputMethods.getInteger();
            for (Singer c : SingerServiceImplement.singerList) {
                if (c.getSingerId() == idSinger) {
                    this.singerName = c;
                    System.out.println("Thêm ca sĩ thành công");
                    check = false;
                    break;
                }
            }
            if (check) {
                System.err.println("Id không tồn tại");
            } else break;
        }

        SongCatagoryImplementService catagoryService = new SongCatagoryImplementService();
        if (catagoryService.findAll().isEmpty()) {
            System.err.println("Danh mục bài hát rỗng");
        }
        boolean flag = true;
        while (true) {
            System.out.println("Danh sách danh mục ");
            for (SongCatagory songCatagory : catagoryService.findAll()) {
                System.out.println(songCatagory);
            }
            System.out.println("Nhập id danh mục");
            int idSongCategory = InputMethods.getInteger();
            for (SongCatagory c : SongCatagoryImplementService.songCatagoryList) {
                if (c.getIdCatagory() == idSongCategory) {
                    this.songCatagory = c;
                    System.out.println("Thêm vào danh mục thành công");
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.err.println("Id không tồn tại");
            } else break;
        }


        System.out.println("Nhập mô tả bài hát");
        this.description = InputMethods.getString();

        while (true) {
            System.out.println("Nhập giá bài hát");
            this.songPrice = InputMethods.getDouble();
            if (Validation.validateEmptyNumber(songPrice)) {
                break;
            }
            System.out.println("Giá bài hát không được để trống");

        }
        AlbumServiceImplement albumService = new AlbumServiceImplement();
        System.out.println("Có muốn thêm bài hát vào album không \n 1 : Có \n 2: Không");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                while (true) {
                    System.out.println("Danh sách album ");
                    for (Album album : albumService.findAll()) {
                        System.out.println(album);
                    }
                    System.out.println("Nhập id album");
                    int idAlbum = InputMethods.getInteger();
                    for (Album a : AlbumServiceImplement.albumList) {
                        if (a.getId() == idAlbum) {
                            this.album = a;
                            System.out.println("Thêm vào danh mục thành công");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        System.err.println("Id không tồn tại");
                    } else break;
                }
                break;
            case 2:
                break;
            default:
                System.out.println("Mời chọn lại");
        }
    }
}
