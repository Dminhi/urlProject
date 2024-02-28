package ra.bussiness.model;

import ra.bussiness.service.admin.service.AlbumServiceImplement;
import ra.bussiness.service.admin.service.SingerServiceImplement;
import ra.bussiness.service.admin.service.SongServiceImplement;
import ra.bussiness.util.InputMethods;
import ra.bussiness.util.Validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Album implements Serializable {
    private int id;
    private String name,description,image;
    private Singer singer;
    private List<Song> song = new ArrayList<>();

    private double albumPrice;

    public Album() {

    }

    public Album(int id, String name, String description, String image, Singer singer, List<Song> song, double albumPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.singer = singer;
        this.song = song;
        this.albumPrice = albumPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(double albumPrice) {
        this.albumPrice = albumPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public List<Song> getSong() {
        return song;
    }

    public void setSong(List<Song> song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", singer=" + singer.getSingerName() +
                ", song=" + Arrays.toString(song.stream().map(Song::getProductName).toList().toArray()) +
                ", albumPrice=" + albumPrice +
                '}';
    }

    public void inputData(){
        while (true) {
            System.out.println("Nhập tên Album");
            String newNameAlbum = InputMethods.getString();
            if (Validation.validateEmpty(newNameAlbum)) {
                boolean flag = true;
                for (Album album : AlbumServiceImplement.albumList) {
                    if (album.getName().equals(newNameAlbum)) {
                        System.err.println("Tên Album đã tồn tại");
                        flag = false;
                    }
                }
                if (flag) {
                    this.name=newNameAlbum;
                    break;
                }
            } else {
                System.err.println("Tên Album không được để trống");
            }
        }


        System.out.println("Danh sách bài hát");
        for (Song songName : SongServiceImplement.songList) {
            System.out.println(songName);
        }
        System.out.println("Nhập số lượng bài hát muốn thêm vào album");
        int quantity = InputMethods.getInteger();
        for (int i = 0; i < quantity; i++) {
            System.out.println("Nhập id bài hát thêm vào Album");
            int idSong = InputMethods.getInteger();
            for (Song c : SongServiceImplement.songList) {
                if (c.getSongId() == idSong) {
                    this.song.add(c);
                }
            }
        }


        System.out.println("Nhập mô tả Album");
        this.description=InputMethods.getString();

        System.out.println("Danh sách ca sĩ ");
        for (Singer singer : SingerServiceImplement.singerList) {
            System.out.println(singer);
        }
        System.out.println("Nhập id ca sĩ");
        int idSinger = InputMethods.getInteger();
        for (Singer c : SingerServiceImplement.singerList) {
            if (c.getSingerId() == idSinger) {
                this.singer = c;
            }
        }

        while (true) {
            System.out.println("Nhập giá Album");
            this.albumPrice = InputMethods.getDouble();
            if (Validation.validateEmptyNumber(albumPrice)) {
                break;
            }
            System.out.println("Giá album không được để trống");

        }
    }
}
