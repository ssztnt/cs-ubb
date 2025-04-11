package com.example.project.repo;

import com.example.project.domain.TypeEnum;
import com.example.project.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class UserRepository implements RepositoryInterface<Long, User>{
    private JdbcUtils jdbcUtils;

    public UserRepository(Properties properties) {
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public User findOne(Long aLong) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement statement = con.prepareStatement("select * from USER " +
                "where id = ?")){

            statement.setLong(1,aLong);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                TypeEnum type = TypeEnum.valueOf(resultSet.getString("type"));
                User user = new User(username,password,type);
                user.setId(aLong);

                return user;
            }
            else {
                return null;
            }
        }
        catch (SQLException e){
            System.out.println("error db"+e);
        }

        return null;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {
        Connection con = jdbcUtils.getConnection();

        try (PreparedStatement prepStatement = con.prepareStatement(
                "INSERT INTO USER (username,password,type) VALUES (?, ?, ?)")) {
            prepStatement.setString(1, entity.getUsername());
            String hashedPassword = PasswordHashing.hashPassword(entity.getPassword());
            prepStatement.setString(2, hashedPassword);
            prepStatement.setString(3,entity.getType().toString());
            prepStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error from DataBase: " + e);
        }


    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(User entity) {

    }

    public User find_by_data(String usernameField, String passwordField, TypeEnum type) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement statement = con.prepareStatement("select * from USER " +
                "where username = ? and password = ? and type = ?")){

            statement.setString(1,usernameField );
            statement.setString(2,PasswordHashing.hashPassword(passwordField));
            statement.setString(3,type.toString());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Long id = resultSet.getLong("id");
                User user = new User(usernameField,passwordField,type);
                user.setId(id);

                return user;
            }
            else {
                return null;
            }
        }
        catch (SQLException e){
            System.out.println("error db"+e);
        }

        return null;
    }
}
