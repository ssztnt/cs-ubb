using System;
using System.Collections.Generic;
using System.Data;
using AvaloniaApplication1.Models;
using AvaloniaApplication1.Repositories.Interfaces;
using AvaloniaApplication1.Repositories.Utils;

namespace AvaloniaApplication1.Repositories.Db
{
    public class DbInscriereRepository : IInscriereRepository
    {
        private readonly DbUtils _dbUtils;

        public DbInscriereRepository(DbUtils dbUtils)
        {
            _dbUtils = dbUtils;
        }

        public IEnumerable<Inscriere> FindAll()
        {
            var list = new List<Inscriere>();
            using var conn = _dbUtils.GetConnection();
            conn.Open();

            using var cmd = conn.CreateCommand();
            cmd.CommandText = "SELECT * FROM Inscriere";
            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadInscriere(reader));
            }

            return list;
        }

        public IEnumerable<Inscriere> FindByParticipant(Participant participant)
        {
            var list = new List<Inscriere>();
            using var conn = _dbUtils.GetConnection();
            conn.Open();

            using var cmd = conn.CreateCommand();
            cmd.CommandText = "SELECT * FROM Inscriere WHERE id_participant = @id";
            var p = cmd.CreateParameter(); p.ParameterName = "@id"; p.Value = participant.IdParticipant; cmd.Parameters.Add(p);

            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadInscriere(reader));
            }

            return list;
        }

        public Inscriere? FindOne(long id)
        {
            using var conn = _dbUtils.GetConnection();
            conn.Open();

            using var cmd = conn.CreateCommand();
            cmd.CommandText = "SELECT * FROM Inscriere WHERE id_inscriere = @id";
            var p = cmd.CreateParameter(); p.ParameterName = "@id"; p.Value = id.ToString(); cmd.Parameters.Add(p);

            using var reader = cmd.ExecuteReader();
            if (reader.Read())
            {
                return ReadInscriere(reader);
            }

            return null;
        }

        public Inscriere? Save(Inscriere inscriere)
        {
            using var connection = _dbUtils.GetConnection();
            connection.Open();

            using var cmd = connection.CreateCommand();
            cmd.CommandText = @"
        INSERT INTO Inscriere (id_inscriere, id_participant, concurs_name, timestamp)
        VALUES (@id, @participant, @concurs, @timestamp)";

            var p1 = cmd.CreateParameter(); p1.ParameterName = "@id"; p1.Value = inscriere.IdInscriere; cmd.Parameters.Add(p1);
            var p2 = cmd.CreateParameter(); p2.ParameterName = "@participant"; p2.Value = inscriere.IdParticipant; cmd.Parameters.Add(p2);
            var p3 = cmd.CreateParameter(); p3.ParameterName = "@concurs"; p3.Value = inscriere.ConcursName; cmd.Parameters.Add(p3);

            // ✅ Proper DateTime value for PostgreSQL timestamp column
            var p4 = cmd.CreateParameter(); p4.ParameterName = "@timestamp"; p4.Value = DateTime.Now; cmd.Parameters.Add(p4);

            cmd.ExecuteNonQuery();
    
            return inscriere;
        }





        public Inscriere? Delete(long id)
        {
            // Optional implementation
            return null;
        }

        public Inscriere? Update(long id, Inscriere entity)
        {
            // Optional implementation
            return null;
        }

        public Inscriere? Update(Inscriere entity)
        {
            // Optional implementation
            return null;
        }

        private Inscriere ReadInscriere(IDataReader reader)
        {
            return new Inscriere(
                reader.GetString(reader.GetOrdinal("id_inscriere")),
                reader.GetString(reader.GetOrdinal("id_participant")),
                reader.GetString(reader.GetOrdinal("concurs_name")),
                reader.GetDateTime(reader.GetOrdinal("timestamp")).ToString("yyyy-MM-dd HH:mm:ss")
            );
        }

        private static IDbDataParameter CreateParam(IDbCommand cmd, string name, object value)
        {
            var param = cmd.CreateParameter();
            param.ParameterName = name;
            param.Value = value;
            return param;
        }
        
        
    }
}
