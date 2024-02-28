package ra.bussiness.model;

import ra.bussiness.util.IOFile;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class History implements Serializable {
    private int historyId, typePackage;
    private double totalPrice;
    private String serialNumber, orderAt, createdAt, updatedAt;
    private Users user;
    private List<Song> songList;
    private E check=E.WAITING;

    private List<History> historyList = new ArrayList<>();

    public History() {
        historyList=IOFile.readFromFile(IOFile.HISTORY_PATH);
    }

    public History(int historyId, double totalPrice, int typePackage, String serialNumber, String orderAt, String createdAt, String updatedAt, Users user, List<Song> songList, E check, List<History> historyList) {
        this.historyId = historyId;
        this.totalPrice = totalPrice;
        this.typePackage = typePackage;
        this.serialNumber = serialNumber;
        this.orderAt = orderAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.songList = songList;
        this.check = check;
        this.historyList = historyList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public int getHistoryId() {
        return historyId;
    }



    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTypePackage() {
        return typePackage;
    }

    public void setTypePackage(int typePackage) {
        this.typePackage = typePackage;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(String orderAt) {
        this.orderAt = orderAt;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users userId) {
        this.user = userId;
    }

    public E getCheck() {
        return check;
    }

    public void setCheck(E check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", totalPrice=" + totalPrice +
                ", typePackage=" + typePackage +
                ", serialNumber='" + serialNumber + '\'' +
                ", orderAt='" + orderAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", user=" + user.getFullName() +
                ", songList=" + songList +
                ", check=" + check +
                ", historyList=" + historyList +
                '}';
    }
}
