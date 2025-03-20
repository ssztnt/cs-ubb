package org.example.Repository;

import org.example.Model.Participant;
import org.example.Repository.Interfaces.ParticipantRepository;
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

public class DBParticipantRepository implements ParticipantRepository {
    private DBUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public DBParticipantRepository(Properties properties) {
        dbUtils = new DBUtils(properties);
    }

    @Override
    public List<Participant> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Participant> participantList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * from Participant")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Participant participant = getParticipantFromResultSet(resultSet);
                    participantList.add(participant);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding all elements DB" + e);
        }
        logger.traceExit(participantList);
        return participantList;
    }

    @Override
    public List<Participant> findByNameandSurname(String name, String surname) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Participant> participantList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Participant WHERE nume = ? AND prenume = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Participant participant = getParticipantFromResultSet(resultSet);
                    participantList.add(participant);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding Participant by name and surname: " + e);
        }
        logger.traceExit(participantList);
        return participantList;
    }

    @Override
    public List<Participant> findByEmail(String email) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Participant> participantList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Participant WHERE email = ?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Participant participant = getParticipantFromResultSet(resultSet);
                    participantList.add(participant);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding Participant by email: " + e);
        }
        logger.traceExit(participantList);
        return participantList;
    }

    private Participant getParticipantFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id_participant");
        String nume = resultSet.getString("nume");
        String prenume = resultSet.getString("prenume");
        String varsta = resultSet.getString("varsta");
        String email = resultSet.getString("email");
        return new Participant(id, nume, prenume, varsta, email);
    }
}