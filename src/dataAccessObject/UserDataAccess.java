package dataAccessObject;

import core.Database;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public User getById(int id) {

        User user = new User();

        String query = "SELECT * FROM public.user WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setMail(resultSet.getString("mail"));
                user.setPassword(resultSet.getString("password"));
                user.setType(User.Type.valueOf(resultSet.getString("type")));
                user.setGender(User.Gender.valueOf(resultSet.getString("gender")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public boolean update(User user) {
        String query = "UPDATE public.user SET name = ?, mail = ?, password = ?, type = ?, gender = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getMail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4,user.getType().toString());
            preparedStatement.setString(5,user.getGender().toString());
            preparedStatement.setInt(6,user.getId());

            return preparedStatement.executeUpdate() != -1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean save(User user) {
        String query = "INSERT INTO public.user (name, mail, password, type, gender) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getMail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4,user.getType().toString());
            preparedStatement.setString(5,user.getGender().toString());

            return preparedStatement.executeUpdate() != -1;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




}
