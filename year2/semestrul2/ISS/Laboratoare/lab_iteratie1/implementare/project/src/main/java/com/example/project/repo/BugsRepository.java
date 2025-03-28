package com.example.project.repo;

import com.example.project.domain.Bug;
import com.example.project.domain.StatusType;
import com.example.project.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BugsRepository implements RepositoryInterface<Long, Bug>{
    private JdbcUtils jdbcUtils;

    public BugsRepository(Properties properties) {
        this.jdbcUtils = new JdbcUtils(properties);
    }


    @Override
    public Bug findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Bug> findAll() {
        List<Bug> bugs = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement statement = con.prepareStatement("select id,name, description, status, resolved_by " +
                "from BUG"))  {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Long id = set.getLong("id");
                String name = set.getString("name");
                String description = set.getString("description");
                StatusType status = StatusType.valueOf(set.getString("status"));
                Long resolved_by = set.getLong("resolved_by");


                Bug bug = new Bug(name,description,status,resolved_by);
                bug.setId(id);
                bugs.add(bug);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bugs;
    }

    @Override
    public void save(Bug entity) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement prepStatement = con.prepareStatement("insert into BUG (name,description,status,resolved_by) " +
                "values (?,?,?,?)")){
            prepStatement.setString(1, entity.getName());
            prepStatement.setString(2, entity.getDescription());
            prepStatement.setString(3,entity.getStatus().toString());
            if(entity.getResolved_by() != null)
                prepStatement.setLong(4,entity.getResolved_by());
            int affectedRows = prepStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Bug entity) {

    }

    public void resolve(Bug bug, User user) {
        if(bug.getId() == null) {
            throw new IllegalArgumentException("Error! Id cannot be null!");
        }

        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement statement = con.prepareStatement("update BUG " +
                "set status = ?, resolved_by = ? where id = ?")) {

            statement.setString(1, StatusType.RESOLVED.toString());
            statement.setLong(2, user.getId());
            statement.setLong(3, bug.getId());

            int affectedRows = statement.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
