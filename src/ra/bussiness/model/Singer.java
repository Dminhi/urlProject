package ra.bussiness.model;

import ra.bussiness.util.InputMethods;
import ra.bussiness.util.Validation;

import java.io.Serializable;
import java.util.List;

public class Singer implements Serializable {
    private int singerId;
    private String singerName, description;
    private boolean status = true;



    public Singer() {
    }

    public Singer(int singerId, String singerName, String description, boolean status, List<Song> listSong, List<Album> album) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.description = description;
        this.status = status;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Singer[" +
                "singerId : " + singerId +
                ", singerName : '" + singerName + '\'' +
                ", description : '" + description + '\'' +
                ", status : " + (status?"Đang hoạt động":"Không hoạt động") +
                ']';
    }
    public void inputData(){
        while (true) {
            System.out.println("Nhập tên ca sĩ");
            this.singerName = InputMethods.getString();
            if (Validation.validateEmpty(singerName)) {
                break;
            } else {
                System.err.println("Họ và tên không được để trống");
            }
        }
        System.out.println("Nhập mô tả ca sĩ");
        this.description = InputMethods.getString();

    }
}
