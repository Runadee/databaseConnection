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
}
