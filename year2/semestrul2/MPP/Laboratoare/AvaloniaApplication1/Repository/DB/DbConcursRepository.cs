using System;
using System.Collections.Generic;
using System.Data;
using AvaloniaApplication1.Models;
using AvaloniaApplication1.Repositories.Interfaces;
using AvaloniaApplication1.Repositories.Utils;

namespace AvaloniaApplication1.Repositories.Db
{
    public class DbConcursRepository : IConcursRepository
    {
        private readonly DbUtils _dbUtils;

        public DbConcursRepository(DbUtils dbUtils)
        {
            _dbUtils = dbUtils;
        }

        public IEnumerable<Concurs> FindAll()
        {
            var list = new List<Concurs>();
            using var connection = _dbUtils.GetConnection();
            connection.Open();

            using var cmd = connection.CreateCommand();
            cmd.CommandText = "SELECT * FROM Concurs";

            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadConcurs(reader));
            }

            return list;
        }

        public IEnumerable<Concurs> FindById(string id)
        {
            var list = new List<Concurs>();
            using var conn = _dbUtils.GetConnection();
            conn.Open();

            using var cmd = conn.CreateCommand();
            cmd.CommandText = "SELECT * FROM Concurs WHERE id_concurs = @id";
            var p = cmd.CreateParameter(); p.ParameterName = "@id"; p.Value = id; cmd.Parameters.Add(p);

            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadConcurs(reader));
            }

            return list;
        }

        public IEnumerable<Concurs> FindByName(string name)
        {
            var list = new List<Concurs>();
            using var conn = _dbUtils.GetConnection();
            conn.Open();

            using var cmd = conn.CreateCommand();
            cmd.CommandText = "SELECT * FROM Concurs WHERE nume = @name";
            var p = cmd.CreateParameter(); p.ParameterName = "@name"; p.Value = name; cmd.Parameters.Add(p);

            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadConcurs(reader));
            }

            return list;
        }

        public IEnumerable<Concurs> FindByLocation(string location)
        {
            var list = new List<Concurs>();
            using var conn = _dbUtils.GetConnection();
            conn.Open();

            using var cmd = conn.CreateCommand();
            cmd.CommandText = "SELECT * FROM Concurs WHERE locatie = @location";
            var p = cmd.CreateParameter(); p.ParameterName = "@location"; p.Value = location; cmd.Parameters.Add(p);

            using var reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                list.Add(ReadConcurs(reader));
            }

            return list;
        }

        private Concurs ReadConcurs(IDataReader reader)
        {
            return new Concurs(
                reader.GetString(reader.GetOrdinal("id_concurs")),
                reader.GetString(reader.GetOrdinal("nume")),
                reader.GetDateTime(reader.GetOrdinal("data")).ToString("yyyy-MM-dd"), // ✅ Fix is here
                reader.GetString(reader.GetOrdinal("locatie"))
            );
        }
    }
}
