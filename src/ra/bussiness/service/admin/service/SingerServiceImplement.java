package ra.bussiness.service.admin.service;

import ra.bussiness.model.Singer;
import ra.bussiness.model.Users;
import ra.bussiness.service.admin.ISingerService;
import ra.bussiness.util.IOFile;

import java.util.List;
import java.util.stream.Collectors;

public class SingerServiceImplement implements ISingerService {

    public static List<Singer> singerList;

    public SingerServiceImplement() {
        singerList = IOFile.readFromFile(IOFile.SINGERS_PATH);
    }

    @Override
    public List<Singer> findAll() {
        return singerList;
    }

    @Override
    public Singer findById(Integer id) {
        return singerList.stream().filter(e -> e.getSingerId() == id).findFirst().orElse(null);

    }

    @Override
    public void save(Singer singer) {
        if (findById(singer.getSingerId()) == null) {
            // Thêm mới
            singer.setSingerId(getNewId());
            singerList.add(singer);
        } else {
            // Cập nhật
            singerList.set(singerList.indexOf(findById(singer.getSingerId())), singer);
        }
        IOFile.writeToFile(IOFile.SINGERS_PATH, singerList);

    }

    @Override
    public void deleteById(Integer id) {
        singerList.remove(findById(id));
    }

    public int getNewId() {
        int maxId = 0;
        for (Singer singer : singerList) {
            if (singer.getSingerId() > maxId) {
                maxId = singer.getSingerId();
            }
        }
        return maxId + 1;
    }

    public List<Singer> findByName(String singerName) {
        return singerList.stream().filter(u -> u.getSingerName().contains(singerName)).collect(Collectors.toList());
    }
}
