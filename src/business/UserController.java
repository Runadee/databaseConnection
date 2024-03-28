package business;

import dataAccessObject.UserDataAccess;

public class UserController {

    private UserDataAccess userDataAccess;

    public UserController() {
        this.userDataAccess = new UserDataAccess();
    }
}
