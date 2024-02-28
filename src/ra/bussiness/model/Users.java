package ra.bussiness.model;

import ra.bussiness.service.admin.service.UserServiceImplement;
import ra.bussiness.util.FormatDate;
import ra.bussiness.util.InputMethods;
import ra.bussiness.util.Validation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {
    private int usersId;
    private int accountType = 1;
    private String userName, email, fullName, password, phone;
    private String createdAt = FormatDate.formattedDate(), updatedAt = null;

    private boolean status = true;
    private boolean role = true;
    private List<Song> favouriteSong,myAlbum;




    public Users() {

    }

    public Users(int usersId, int accountType, String userName, String email, String fullName, String password, String phone, String createdAt, String updatedAt, boolean status, boolean role, List<Song> favouriteSong, List<Song> myAlbum) {
        this.usersId = usersId;
        this.accountType = accountType;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.role = role;
        this.favouriteSong = favouriteSong;
        this.myAlbum = myAlbum;
    }

    public List<Song> getFavouriteSong() {
        if (favouriteSong == null) {
            favouriteSong = new ArrayList<>();
        }
        return favouriteSong;
    }

    public void setFavouriteSong(List<Song> favouriteSong) {
        this.favouriteSong = favouriteSong;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Song> getMyAlbum() {
        if (myAlbum == null) {
            myAlbum = new ArrayList<>();
        }
        return myAlbum;
    }

    public void setMyAlbum(List<Song> myAlbum) {
        this.myAlbum = myAlbum;
    }

    public String getEmail() {
        return email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users[" +
                "usersId : " + usersId +
                ", accountType : " + accountType +
                ", userName : '" + userName + '\'' +
                ", email : '" + email + '\'' +
                ", fullName : '" + fullName + '\'' +
                ", password : '" + password + '\'' +
                ", phone : '" + phone + '\'' +
                ", createdAt : '" + createdAt + '\'' +
                ", updatedAt : '" + updatedAt + '\'' +
                ", status : " + status +
                ", role : " + role +
                ", favouriteSong : " + favouriteSong +
                ", myAlbum : " + myAlbum +
                '}';
    }

    public void inputData(boolean isAdd) {
        if (isAdd) {
            while (true) {
                System.out.println("Nhập tên đăng nhập");
                this.userName = InputMethods.getString();
                if (Validation.validateUserName(userName)) {
                    boolean flag = true;
                    for (Users users : UserServiceImplement.usersList) {
                        if (users.getUserName().equals(userName)) {
                            System.err.println("Tên đăng nhập đã tồn tại");
                            flag = false;
                        }
                    }
                    if (flag) {
                        break;
                    }
                } else {
                    System.err.println("Tên đăng nhập phải có ít nhất 6 ký tự và không có ký tự đặc biệt");
                }

            }
            while (true) {
                System.out.println("Nhập mật khẩu");
                this.password = InputMethods.getString();
                if (Validation.validateEmpty(password)) {
                    break;
                } else {
                    System.err.println("Họ và tên không được để trống");
                }
            }
        }

        while (true) {
            System.out.println("Nhập email");
            this.email = InputMethods.getString();
            if (Validation.validateEmail(email)) {
                boolean flag = true;
                for (Users users : UserServiceImplement.usersList) {
                    if (users.getEmail() != null && users.getEmail().equals(email)) {
                        System.err.println("Email đã tồn tại");
                        flag = false;
                    }
                }
                if (flag) {
                    break;
                }
            } else {
                System.err.println("Email không đúng định dạng");
            }

        }

        while (true) {
            System.out.println("Nhập họ và tên");
            this.fullName = InputMethods.getString();
            if (Validation.validateEmpty(fullName)) {
                break;
            } else {
                System.err.println("Họ và tên không được để trống");
            }
        }

        while (true) {
            System.out.println("Nhập số điện thoại");
            this.phone = InputMethods.getString();
            if (Validation.validateVNPhoneNumber(phone)) {
                boolean flag = true;
                for (Users users : UserServiceImplement.usersList) {
                    if (users.getPhone() != null && users.getPhone().equals(phone)) {
                        System.err.println("Số điện thoại đã tồn tại");
                        flag = false;
                    }
                }
                if (flag) {
                    break;
                }
            } else {
                System.err.println("Số điện thoại tại Việt Nam bắt buộc phải có mã vùng + 84 và 9 số đằng sau");
            }
        }
    }

    public void displayData() {
    }
}
