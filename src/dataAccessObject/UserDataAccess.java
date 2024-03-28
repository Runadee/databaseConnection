package dataAccessObject;

import core.Database;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDataAccess {
    private Connection connection;

    public UserDataAccess() {
        this.connection = Database.connection();

        // Gelen verileri , User entity nesnelere aktaracak
        // CRUD işlemleri ekleme silme okuma güncelleme
    }

    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM public.user";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setMail(resultSet.getString("mail"));
                user.setPassword(resultSet.getString("password"));
                user.setType(User.Type.valueOf(resultSet.getString("type")));
                user.setGender(User.Gender.valueOf(resultSet.getString("gender")));

                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
