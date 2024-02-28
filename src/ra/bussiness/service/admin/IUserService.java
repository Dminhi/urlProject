package ra.bussiness.service.admin;

import ra.bussiness.model.Users;
import ra.bussiness.service.IGenericService;

public interface IUserService extends IGenericService<Users,Integer> {
    boolean register(Users users);
    Users login(String userName,String password);
}
