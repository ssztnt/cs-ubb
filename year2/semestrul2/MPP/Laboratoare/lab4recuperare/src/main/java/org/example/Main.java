package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Model.Participant;
import org.example.Repository.DBParticipantRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started");

        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("bd.config")) {
            if (input == null) {
                logger.error("Sorry, unable to find bd.config");
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
            return;
        }

        DBParticipantRepository participantRepository = new DBParticipantRepository(properties);
        List<Participant> participants = participantRepository.findAll();

        for (Participant participant : participants) {
            System.out.println(participant);
        }

        logger.info("Application finished");
    }
}