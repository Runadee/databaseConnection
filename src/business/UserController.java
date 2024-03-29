package business;

import dataAccessObject.UserDataAccess;
import entity.User;

import java.util.ArrayList;

public class UserController {

    private UserDataAccess userDataAccess;

    public UserController() {
        this.userDataAccess = new UserDataAccess();
    }

    public ArrayList<User> findAll() {
        return this.userDataAccess.findAll();
    }

    public User getById(int id) {
        if (id == 0) {
            System.out.println("The Value of ID cannot be 0 or minus !");
            return new User();
        }

        return this.userDataAccess.getById(id);
    }

    public boolean update(User user) {

        User checkUser = this.getById(user.getId());

        if (checkUser == null || checkUser.getId() == 0) {
            return false;
        }
        return this.userDataAccess.update(user);
    }

    public boolean save(User user) {
        return this.userDataAccess.save(user);
    }


    public boolean delete(User user) {
        return this.userDataAccess.delete(user);
    }


}
