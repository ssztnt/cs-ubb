using System;
using System.Collections.Generic;
using System.Data;
using AvaloniaApplication1.Models;
using AvaloniaApplication1.Repositories.Interfaces;
using AvaloniaApplication1.Repositories.Utils;

namespace AvaloniaApplication1.Repositories.Db
{
    public class DbParticipantRepository : IParticipantRepository
    {
        private readonly DbUtils _dbUtils;

        public DbParticipantRepository(DbUtils dbUtils)
        {
            _dbUtils = dbUtils;
        }

        public List<Participant> FindAll()
        {
            var list = new List<Participant>();
            using var connection = _dbUtils.GetConnection();
            connection.Open();

            using var cmd = connection.CreateCommand();
            cmd.CommandText = "SELECT * FROM Participant";

            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadParticipant(reader));
            }

            return list;
        }

        public List<Participant> FindByNameAndSurname(string name, string surname)
        {
            var list = new List<Participant>();
            using var connection = _dbUtils.GetConnection();
            connection.Open();

            using var cmd = connection.CreateCommand();
            cmd.CommandText = "SELECT * FROM Participant WHERE nume = @name AND prenume = @surname";
            var p1 = cmd.CreateParameter(); p1.ParameterName = "@name"; p1.Value = name; cmd.Parameters.Add(p1);
            var p2 = cmd.CreateParameter(); p2.ParameterName = "@surname"; p2.Value = surname; cmd.Parameters.Add(p2);

            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadParticipant(reader));
            }
            return list;
        }

        public List<Participant> FindByEmail(string email)
        {
            var list = new List<Participant>();
            using var connection = _dbUtils.GetConnection();
            connection.Open();

            using var cmd = connection.CreateCommand();
            cmd.CommandText = "SELECT * FROM Participant WHERE email = @email";
            var p = cmd.CreateParameter(); p.ParameterName = "@email"; p.Value = email; cmd.Parameters.Add(p);

            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadParticipant(reader));
            }
            return list;
        }

        public Participant? SaveParticipant(Participant participant)
        {
            using var connection = _dbUtils.GetConnection();
            connection.Open();

            using var cmd = connection.CreateCommand();
            cmd.CommandText = @"
                INSERT INTO Participant (id_participant, nume, prenume, varsta, email)
                VALUES (@id, @nume, @prenume, @varsta, @email)";
            var p0 = cmd.CreateParameter(); p0.ParameterName = "@id"; p0.Value = participant.IdParticipant; cmd.Parameters.Add(p0);
            var p1 = cmd.CreateParameter(); p1.ParameterName = "@nume"; p1.Value = participant.Nume; cmd.Parameters.Add(p1);
            var p2 = cmd.CreateParameter(); p2.ParameterName = "@prenume"; p2.Value = participant.Prenume; cmd.Parameters.Add(p2);
            var p3 = cmd.CreateParameter(); p3.ParameterName = "@varsta"; p3.Value = int.Parse(participant.Varsta); cmd.Parameters.Add(p3);
            var p4 = cmd.CreateParameter(); p4.ParameterName = "@email"; p4.Value = participant.Email; cmd.Parameters.Add(p4);

            cmd.ExecuteNonQuery();
            return participant;
        }

        private Participant ReadParticipant(IDataReader reader)
        {
            return new Participant(
                reader.GetString(reader.GetOrdinal("nume")),
                reader.GetString(reader.GetOrdinal("prenume")),
                reader.GetInt32(reader.GetOrdinal("varsta")).ToString(),
                reader.GetString(reader.GetOrdinal("email")))
            {
                IdParticipant = reader.GetString(reader.GetOrdinal("id_participant"))
            };
        }
    }
}
