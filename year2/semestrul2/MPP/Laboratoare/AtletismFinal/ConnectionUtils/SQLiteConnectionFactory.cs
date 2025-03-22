using System.Data;
using System.Data.SQLite;

namespace AtletismFinal.connectionUtils;

public class SqliteConnectionFactory : ConnectionFactory
{
    public override IDbConnection createConnection(IDictionary<string, string> props)
    {
        // Extrage connection string-ul din dicționarul de proprietăți
        string connectionString = props["ConnectionString"];
        Console.WriteLine("SQLite --- se deschide o conexiune la ... {0}", connectionString);

        // Returnează o nouă conexiune SQLite
        return new SQLiteConnection(connectionString);
    }
}