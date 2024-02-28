package ra.bussiness.model;

import ra.bussiness.service.admin.service.SongCatagoryImplementService;
import ra.bussiness.service.admin.service.SongServiceImplement;
import ra.bussiness.util.InputMethods;
import ra.bussiness.util.Validation;

import java.io.Serializable;

public class SongCatagory implements Serializable {
    int idCatagory;
    String catagoryName;

    public SongCatagory(int idCatagory, String catagoryName) {
        this.idCatagory = idCatagory;
        this.catagoryName = catagoryName;
    }

    public SongCatagory() {
    }

    public int getIdCatagory() {
        return idCatagory;
    }

    public void setIdCatagory(int idCatagory) {
        this.idCatagory = idCatagory;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }
    public void inputData (){
        while (true) {
            System.out.println("Nhập tên danh mục bài hát");
            String newSongCatagory = InputMethods.getString();
            if (Validation.validateEmpty(newSongCatagory)) {
                boolean flag = true;
                for (SongCatagory songCatagory : SongCatagoryImplementService.songCatagoryList) {
                    if (songCatagory.getCatagoryName().equals(newSongCatagory)) {
                        System.err.println("Tên danh mục đã tồn tại");
                        flag = false;
                    }
                }
                if (flag) {
                    this.catagoryName=newSongCatagory;
                    break;
                }
            } else {
                System.err.println("Tên danh mục không được để trống");
            }
        }
    }

    @Override
    public String toString() {
        return "SongCatagory[" +
                "idCatagory : " + idCatagory +
                ", catagoryName : '" + catagoryName + '\'' +
                ']';
    }
}
