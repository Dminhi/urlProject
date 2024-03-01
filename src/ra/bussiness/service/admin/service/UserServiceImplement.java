package ra.bussiness.service.admin.service;

import ra.bussiness.model.Users;
import ra.bussiness.service.admin.IUserService;
import ra.bussiness.util.FormatDate;
import ra.bussiness.util.HashCode;
import ra.bussiness.util.IOFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImplement implements IUserService {
    public static List<Users> usersList;

    public UserServiceImplement() {
        usersList = IOFile.readFromFile(IOFile.USERS_PATH);
    }

    @Override
    public boolean register(Users users) {
        String hashCode = HashCode.hashPassword(users.getPassword());
        users.setPassword(hashCode);
        users.setUsersId(getNewId());
        usersList.add(users);
        IOFile.writeToFile(IOFile.USERS_PATH, usersList);
        System.out.println("Đăng ký thành công");
        return true;
    }

    @Override
    public Users login(String userName, String password) {
        usersList = IOFile.readFromFile(IOFile.USERS_PATH);
        for (Users users : usersList) {
            if (users.getUserName().equals(userName) && users.getPassword().equals(HashCode.hashPassword(password))) {
                return users;
            }
        }
        return null;
    }


    @Override
    public List<Users> findAll() {
        return usersList.stream().filter(Users::isRole).collect(Collectors.toList());
    }

    @Override
    public Users findById(Integer id) {
        return usersList.stream().filter(e -> e.getUsersId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Users users) {
        if (findById(users.getUsersId()) == null) {
            // Thêm mới
            users.setUsersId(getNewId());
            usersList.add(users);
        } else {
            // Cập nhật
            usersList.set(usersList.indexOf(findById(users.getUsersId())), users);
        }
        IOFile.writeToFile(IOFile.USERS_PATH,usersList);

    }

    @Override
    public void deleteById(Integer id) {
        usersList.remove(findById(id));
    }

    public List<Users> findByName(String userName) {
        return usersList.stream().filter(u -> u.getUserName().contains(userName)).collect(Collectors.toList());
    }

    public void changeStatusUserById(Integer id) {
        if (findById(id) == null) {
            System.out.println("Id không tồn tại");
        }
        usersList.stream().filter(e -> e.getUsersId() == id).findFirst().ifPresent(users -> users.setStatus(!users.getStatus()));
        findById(id).setUpdatedAt(FormatDate.formattedDate());
        IOFile.writeToFile(IOFile.USERS_PATH, usersList);
        System.out.println("Đã "+ (findById(id).getStatus()?"Mở khoá thành công ":"Khoá thành công ") +findById(id).getFullName());
    }

    public int getNewId() {
        int maxId = 0;
        for (Users users : usersList) {
            if (users.getUsersId() > maxId) {
                maxId = users.getUsersId();
            }
        }
        return maxId + 1;
    }
}
