using Microsoft.Data.Sqlite;
using log4net;
using log4net.Config;
using System.Reflection;
using System.Configuration;

public static class Program
{
    private static readonly ILog log = LogManager.GetLogger(typeof(Program));

    public static void Main(string[] args)
    {
        var logRepository = LogManager.GetRepository(Assembly.GetEntryAssembly());
        XmlConfigurator.Configure(logRepository, new FileInfo("log4net.config"));
        log.Info("Starting main");

        string connectionString = GetConnectionStringByName("SQLiteConnection");

        using (var connection = new SqliteConnection(connectionString))
        {
            connection.Open();
            var command = connection.CreateCommand();
            command.CommandText = "SELECT * FROM Participant";
            using (var reader = command.ExecuteReader())
            {
                while (reader.Read())
                {
                    Console.WriteLine(reader.GetString(0));
                    Console.WriteLine("mergemerge");
                }
            }
        }

        log.Info("Ending main");
    }

    private static string GetConnectionStringByName(string name)
    {
        string returnValue = null;
        var settings = ConfigurationManager.ConnectionStrings[name];
        if (settings != null)
            returnValue = settings.ConnectionString;
        return returnValue;
    }
}