using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SQLite;
using AtletismFinal.Domain;
using AtletismFinal.Repository;
using AtletismFinal.Repository;
using log4net;

namespace AtletismFinal.Repository
{
    public class DBParticipantRepository : ParticipantRepository
    {
        private readonly string _connectionString;
        private static readonly ILog logger = LogManager.GetLogger(typeof(DBParticipantRepository));

        public DBParticipantRepository(string connectionString)
        {
            _connectionString = connectionString;
        }

        public List<Participant> FindAll()
        {
            logger.Info("Entering FindAll");
            var participantList = new List<Participant>();
            using (var con = new SQLiteConnection(_connectionString))
            {
                con.Open();
                using (var cmd = new SQLiteCommand("SELECT * FROM Participant", con))
                {
                    using (var reader = cmd.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            var participant = GetParticipantFromReader(reader);
                            participantList.Add(participant);
                        }
                    }
                }
            }
            logger.Info("Exiting FindAll");
            return participantList;
        }

        public List<Participant> FindByNameAndSurname(string name, string surname)
        {
            logger.Info("Entering FindByNameAndSurname");
            var participantList = new List<Participant>();
            using (var con = new SQLiteConnection(_connectionString))
            {
                con.Open();
                using (var cmd = new SQLiteCommand("SELECT * FROM Participant WHERE nume = @name AND prenume = @surname", con))
                {
                    cmd.Parameters.AddWithValue("@name", name);
                    cmd.Parameters.AddWithValue("@surname", surname);
                    using (var reader = cmd.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            var participant = GetParticipantFromReader(reader);
                            participantList.Add(participant);
                        }
                    }
                }
            }
            logger.Info("Exiting FindByNameAndSurname");
            return participantList;
        }

        public List<Participant> FindByEmail(string email)
        {
            logger.Info("Entering FindByEmail");
            var participantList = new List<Participant>();
            using (var con = new SQLiteConnection(_connectionString))
            {
                con.Open();
                using (var cmd = new SQLiteCommand("SELECT * FROM Participant WHERE email = @Email", con))
                {
                    cmd.Parameters.AddWithValue("@Email", email);
                    using (var reader = cmd.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            var participant = GetParticipantFromReader(reader);
                            participantList.Add(participant);
                        }
                    }
                }
            }
            logger.Info("Exiting FindByEmail");
            return participantList;
        }

        private Participant GetParticipantFromReader(IDataReader reader)
        {
            var id = reader["id_participant"].ToString();
            var nume = reader["nume"].ToString();
            var prenume = reader["prenume"].ToString();
            var varsta = reader["varsta"].ToString();
            var email = reader["email"].ToString();
            return new Participant(id, nume, prenume, varsta, email);
        }
    }
}