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

    private Inscriere getInscriereFromResultSet(ResultSet rs) throws SQLException {
        String id = rs.getString("id_inscriere");
        String participantId = rs.getString("id_participant");
        String concursName = rs.getString("concurs_name");
        String timestamp = rs.getString("timestamp");
        return new Inscriere(id, participantId, concursName, timestamp);
    }



    @Override
    public Optional<Inscriere> save(Inscriere entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO Inscriere VALUES (?, ?, ?, ?)")) {
            ps.setString(1, entity.getId_inscriere());
            ps.setString(2, entity.getId_participant());
            ps.setString(3, entity.getConcurs_name());
            ps.setString(4, entity.getTimestamp());

            ps.executeUpdate();
            System.out.println("✔ Inscriere saved to DB");
            return Optional.of(entity);
        } catch (SQLException e) {
            System.err.println("❌ Failed to insert inscriere: " + e.getMessage());
            e.printStackTrace();
        }
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