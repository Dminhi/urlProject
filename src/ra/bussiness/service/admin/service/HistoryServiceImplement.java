package ra.bussiness.service.admin.service;

import ra.bussiness.model.History;
import ra.bussiness.model.Singer;
import ra.bussiness.service.admin.IHistoryService;
import ra.bussiness.util.IOFile;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryServiceImplement implements IHistoryService {
    public static List<History> historyList;

    public HistoryServiceImplement() {
        historyList = IOFile.readFromFile(IOFile.HISTORY_PATH);
    }

    @Override
    public List<History> findAll() {
        return historyList;
    }

    @Override
    public History findById(Integer id) {
        return historyList.stream().filter(e->e.getHistoryId()==id).findFirst().orElse(null);
    }

    @Override
    public void save(History history) {
        if (findById(history.getHistoryId()) == null) {
            // Thêm mới
            history.setHistoryId(getNewId());
            historyList.add(history);
        } else {
            // Cập nhật
            historyList.set(historyList.indexOf(findById(history.getHistoryId())), history);
        }
        IOFile.writeToFile(IOFile.HISTORY_PATH, historyList);

    }

    @Override
    public void deleteById(Integer id) {
    historyList.remove(findById(id));
    }
    public int getNewId() {
        int maxId = 0;
        for (History history : historyList) {
            if (history.getHistoryId() > maxId) {
                maxId = history.getHistoryId();
            }
        }
        return maxId + 1;
    }
}
