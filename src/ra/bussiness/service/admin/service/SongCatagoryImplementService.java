package ra.bussiness.service.admin.service;

import ra.bussiness.model.Song;
import ra.bussiness.model.SongCatagory;
import ra.bussiness.service.admin.ICatagoryService;
import ra.bussiness.util.FormatDate;
import ra.bussiness.util.IOFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongCatagoryImplementService implements ICatagoryService {
    public static List<SongCatagory> songCatagoryList = new ArrayList<>();

    public SongCatagoryImplementService() {songCatagoryList=IOFile.readFromFile(IOFile.SONGCATEGORY_PATH);
    }

    @Override
    public List<SongCatagory> findAll() {
        return songCatagoryList;
    }

    @Override
    public SongCatagory findById(Integer id) {
        return songCatagoryList.stream().filter(e -> e.getIdCatagory() == id).findFirst().orElse(null);
    }

    @Override
    public void save(SongCatagory songCatagory) {
        if (findById(songCatagory.getIdCatagory()) == null) {
            // Thêm mới
            songCatagory.setIdCatagory(getNewId());
            songCatagoryList.add(songCatagory);
        } else {
            // Cập nhật
            songCatagoryList.set(songCatagoryList.indexOf(findById(songCatagory.getIdCatagory())), songCatagory);
        }
        IOFile.writeToFile(IOFile.SONGCATEGORY_PATH, songCatagoryList);

    }

    @Override
    public void deleteById(Integer id) {
        songCatagoryList.remove(findById(id));

    }
    public int getNewId() {
        int maxId = 0;
        for (SongCatagory songCatagory : songCatagoryList) {
            if (songCatagory.getIdCatagory() > maxId) {
                maxId = songCatagory.getIdCatagory();
            }
        }
        return maxId + 1;
    }
    public List<SongCatagory> findByName(String songCatagoryName) {
        return songCatagoryList.stream().filter(u -> u.getCatagoryName().contains(songCatagoryName)).collect(Collectors.toList());
    }
}
