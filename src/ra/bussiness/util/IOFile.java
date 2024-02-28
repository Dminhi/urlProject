package ra.bussiness.util;

import ra.bussiness.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static final String USERSLOGIN_PATH = "/Users/dminhi/Desktop/Project/Project/src/ra/bussiness/data/admin/userLogin.txt";

    public static final String USERS_PATH = "/Users/dminhi/Desktop/Project/Project/src/ra/bussiness/data/admin/user.txt";
    public static final String SINGERS_PATH = "/Users/dminhi/Desktop/Project/Project/src/ra/bussiness/data/admin/singer.txt";
    public static final String SONGS_PATH = "/Users/dminhi/Desktop/Project/Project/src/ra/bussiness/data/admin/song.txt";
    public static final String HISTORY_PATH = "/Users/dminhi/Desktop/Project/Project/src/ra/bussiness/data/admin/history.txt";
    public static final String ALBUM_PATH = "/Users/dminhi/Desktop/Project/Project/src/ra/bussiness/data/admin/album.txt";
    public static final String SONGCATEGORY_PATH = "/Users/dminhi/Desktop/Project/Project/src/ra/bussiness/data/admin/songcategory.txt";
    public static <T> void  writeToFile(String path, List<T> list){
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static <T> List<T> readFromFile(String path){
        File file = new File(path);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<T> list = new ArrayList<>();
        try{
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
            ois.read();
            ois.close();
        }catch (EOFException | FileNotFoundException e){

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return list;
    }
    public static <T> void  writeUserLoginToFile(String path, T t){
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Users readUserLoginFromFile(String path){
        File file = new File(path);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Users users =null;
        try{
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            users = (Users) ois.readObject();
            ois.read();
            ois.close();
        }catch (EOFException | FileNotFoundException e){

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return users;
    }

}