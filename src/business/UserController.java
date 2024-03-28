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
}
