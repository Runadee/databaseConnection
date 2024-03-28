package dataAccessObject;

import core.Database;

import java.sql.Connection;

public class UserDataAccess {
    private Connection connection;

    public UserDataAccess() {
        this.connection = Database.connection();

        // Gelen verileri , User entity nesnelere aktaracak
        // CRUD işlemleri ekleme silme okuma güncelleme
    }
}
