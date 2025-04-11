using System;
using System.Data;
using Npgsql;
using Microsoft.Extensions.Configuration;

namespace AvaloniaApplication1.Repositories.Utils
{
    public class DbUtils
    {
        private readonly string _connectionString;

        public DbUtils(IConfiguration config)
        {
            _connectionString = config.GetConnectionString("PostgresConnection");
        }

        public IDbConnection GetConnection()
        {
            return new NpgsqlConnection(_connectionString);
        }

        public bool TestConnection()
        {
            try
            {
                using var connection = GetConnection();
                connection.Open();
                Console.WriteLine("✔ Connected to PostgreSQL database successfully.");
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"❌ Connection failed: {ex.Message}");
                return false;
            }
        }
    }
}