package org.example.Repository;

import org.example.Model.Inscriere;
import org.example.Model.Participant;
import org.example.Repository.Interfaces.InscriereRepository;
import org.example.Repository.Utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class DBInscriereRepository implements InscriereRepository {
    private DBUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public DBInscriereRepository(Properties properties) {
        dbUtils = new DBUtils(properties);
    }

    @Override
    public Iterable<Inscriere> findByid_inscriere(Inscriere inscriere) {
        return null;
    }

    @Override
    public Iterable<Inscriere> findByid_participant(Participant participant) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Inscriere> inscriereList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Inscriere WHERE id_participant = ?")) {
            preparedStatement.setString(1, participant.getId_participant());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Inscriere foundInscriere = getInscriereFromResultSet(resultSet);
                    inscriereList.add(foundInscriere);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding Inscriere by id_participant: " + e);
        }
        logger.traceExit(inscriereList);
        return inscriereList;
    }

    @Override
    public Optional<Inscriere> findOne(Long id) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        Inscriere inscriere = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Inscriere WHERE id_inscriere = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    inscriere = getInscriereFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding Inscriere by id_inscriere: " + e);
        }
        logger.traceExit(inscriere);
        return Optional.ofNullable(inscriere);
    }

    @Override
    public Iterable<Inscriere> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Inscriere> inscriereList = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Inscriere")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Inscriere inscriere = getInscriereFromResultSet(resultSet);
                    inscriereList.add(inscriere);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error finding all Inscriere: " + e);
        }
        logger.traceExit(inscriereList);
        return inscriereList;
    }

    private Inscriere getInscriereFromResultSet(ResultSet resultSet) throws SQLException {
        String id = String.valueOf(resultSet.getLong("id"));
        String idParticipant = resultSet.getString("id_participant");
        String idConcurs = resultSet.getString("id_concurs");
        return new Inscriere(id, idParticipant, idConcurs);
    }

    @Override
    public Optional<Inscriere> save(Inscriere entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Inscriere> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Inscriere update(Long aLong, Inscriere entity) {
        return null;
    }

    @Override
    public Optional<Inscriere> update(Inscriere entity) {
        return Optional.empty();
    }
}