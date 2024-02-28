package ra.bussiness.service.admin.service;

import ra.bussiness.model.Album;
import ra.bussiness.model.Singer;
import ra.bussiness.model.Song;
import ra.bussiness.service.admin.IAlbumService;
import ra.bussiness.util.IOFile;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumServiceImplement implements IAlbumService {
    public static List<Album> albumList;

    public AlbumServiceImplement() {
        albumList = IOFile.readFromFile(IOFile.ALBUM_PATH);
    }

    @Override
    public List<Album> findAll() {
        return albumList;
    }

    @Override
    public Album findById(Integer id) {
        return albumList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);

    }

    @Override
    public void save(Album album) {
        if (findById(album.getId()) == null) {
            // Thêm mới
            album.setId(getNewId());
            albumList.add(album);
        } else {
            // Cập nhật
            albumList.set(albumList.indexOf(findById(album.getId())), album);
        }
        IOFile.writeToFile(IOFile.ALBUM_PATH, albumList);


    }

    @Override
    public void deleteById(Integer id) {
        albumList.remove(findById(id));
    }

    public int getNewId() {
        int maxId = 0;
        for (Album album : albumList) {
            if (album.getId() > maxId) {
                maxId = album.getId();
            }
        }
        return maxId + 1;
    }

    public List<Album> findByName(String albumName) {
        return albumList.stream().filter(u -> u.getName().contains(albumName)).collect(Collectors.toList());
    }
}
