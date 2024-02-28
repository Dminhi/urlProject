package ra.bussiness.service.admin.service;

import ra.bussiness.model.Singer;
import ra.bussiness.model.Song;
import ra.bussiness.service.admin.ISongService;
import ra.bussiness.util.FormatDate;
import ra.bussiness.util.IOFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongServiceImplement implements ISongService {

    public static List<Song> songList = new ArrayList<>();

    public SongServiceImplement() {
        songList = IOFile.readFromFile(IOFile.SONGS_PATH);
    }

    @Override
    public List<Song> findAll() {
        return songList;
    }

    @Override
    public Song findById(Integer id) {
        return songList.stream().filter(e -> e.getSongId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Song song) {
        if (findById(song.getSongId()) == null) {
            // Thêm mới
            song.setSongId(getNewId());
            songList.add(song);
        } else {
            // Cập nhật
            song.setUpdatedAt(FormatDate.formattedDate());
            songList.set(songList.indexOf(findById(song.getSongId())), song);
        }
        IOFile.writeToFile(IOFile.SONGS_PATH, songList);

    }

    @Override
    public void deleteById(Integer id) {
        songList.remove(findById(id));
    }

    public int getNewId() {
        int maxId = 0;
        for (Song song : songList) {
            if (song.getSongId() > maxId) {
                maxId = song.getSongId();
            }
        }
        return maxId + 1;
    }

    public List<Song> findByName(String songName) {
        return songList.stream().filter(u -> u.getProductName().contains(songName)).collect(Collectors.toList());
    }

    public void changeStatusSongById(Integer id) {
        if (findById(id) == null) {
            System.out.println("Id không tồn tại");
        }
        songList.stream().filter(e -> e.getSongId() == id).findFirst().ifPresent(song -> song.setActiveSong(!song.isActiveSong()));
        findById(id).setUpdatedAt(FormatDate.formattedDate());
        IOFile.writeToFile(IOFile.SONGS_PATH, songList);
        System.out.println("Đã "+ (findById(id).isActiveSong()?"Mở khoá thành công ":"Khoá thành công ") +findById(id).getProductName());
    }
}
