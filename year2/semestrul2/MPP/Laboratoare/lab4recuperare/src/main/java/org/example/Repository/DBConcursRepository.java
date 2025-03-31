package org.example.Repository;

import org.example.Model.Concurs;
import org.example.Repository.Interfaces.ConcursRepository;
import org.example.Repository.Utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBConcursRepository implements ConcursRepository {
    private DBUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public DBConcursRepository(Properties properties) {
        dbUtils = new DBUtils(properties);
    }

    @Override
    public Iterable<Concurs> findByName(String name) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Concurs> concursList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Concurs WHERE nume = ?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Concurs concurs = getConcursFromResultSet(resultSet);
                    concursList.add(concurs);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding Concurs by name: " + e);
        }
        logger.traceExit(concursList);
        return concursList;
    }

    @Override
    public Iterable<Concurs> findbyID(Long id) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Concurs> concursList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Concurs WHERE id_concurs = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Concurs concurs = getConcursFromResultSet(resultSet);
                    concursList.add(concurs);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding Concurs by ID: " + e);
        }
        logger.traceExit(concursList);
        return concursList;
    }

    @Override
    public Iterable<Concurs> findbyLocation(String location) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Concurs> concursList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Concurs WHERE locatie = ?")) {
            preparedStatement.setString(1, location);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Concurs concurs = getConcursFromResultSet(resultSet);
                    concursList.add(concurs);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding Concurs by location: " + e);
        }
        logger.traceExit(concursList);
        return concursList;
    }

    private Concurs getConcursFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id_concurs");   // ✅ FIXED
        String name = resultSet.getString("nume");
        String date = resultSet.getString("data");
        String location = resultSet.getString("locatie");
        return new Concurs(id, name, date , location);
    }


    @Override
    public Iterable<Concurs> findAll() {
        List<Concurs> list = new ArrayList<>();
        Connection con = dbUtils.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Concurs");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(getConcursFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}