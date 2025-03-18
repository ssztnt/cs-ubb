using ConnectionUtils;
using lab3_cscs.Repository;
using lab3_cscs.Repository.Utils;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;

public class Program
{
    public static void Main(string[] args)
    {
        var configuration = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json")
            .Build();

        var connectionString = configuration.GetConnectionString("DefaultConnection");

        var serviceProvider = new ServiceCollection()
            .AddSingleton<DBUtils>()
            .AddSingleton<DBParticipantRepository>()
            .AddSingleton<ConnectionFactory, SqliteConnectionFactory>()
            .BuildServiceProvider();

        var connectionFactory = serviceProvider.GetService<ConnectionFactory>();
        var connectionProps = new Dictionary<string, string> { { "ConnectionString", connectionString } };

        try
        {
            using (var connection = connectionFactory.createConnection(connectionProps))
            {
                connection.Open();
                Console.WriteLine("Connection to the database was successful.");
            }

            var participantRepository = serviceProvider.GetService<DBParticipantRepository>();
            var participant = participantRepository.FindOne("some-participant-id");

            if (participant != null)
            {
                Console.WriteLine($"ID: {participant.IdParticipant}, Name: {participant.Nume} {participant.Prenume}, Age: {participant.Varsta}, Email: {participant.Email}");
            }
            else
            {
                Console.WriteLine("Participant not found.");
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Failed to connect to the database: {ex.Message}");
        }
    }
}