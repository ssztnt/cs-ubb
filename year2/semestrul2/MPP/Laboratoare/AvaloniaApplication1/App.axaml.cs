using Avalonia;
using Avalonia.Controls.ApplicationLifetimes;
using Avalonia.Markup.Xaml;
using AvaloniaApplication1.Repositories.Db;
using AvaloniaApplication1.Repositories.Utils;
using AvaloniaApplication1.Services;
using AvaloniaApplication1.Views;
using Microsoft.Extensions.Configuration;

namespace AvaloniaApplication1
{
    public partial class App : Application
    {
        public static IAtletismService? service { get; private set; }

        public override void OnFrameworkInitializationCompleted()
        {
            if (ApplicationLifetime is IClassicDesktopStyleApplicationLifetime desktop)
            {
                // Load configuration from JSON
                var configuration = new ConfigurationBuilder()
                    .AddJsonFile("appsettings.json", optional: false, reloadOnChange: true)
                    .Build();

                // Setup repositories and services
                var dbUtils = new DbUtils(configuration);
                var participantRepo = new DbParticipantRepository(dbUtils);
                var concursRepo = new DbConcursRepository(dbUtils);
                var inscriereRepo = new DbInscriereRepository(dbUtils);

                service = new AtletismService(participantRepo, inscriereRepo, concursRepo);

                // Show the login window
                var loginWindow = new LoginWindow(service);
                desktop.MainWindow = loginWindow;
                loginWindow.Show();
            }

            base.OnFrameworkInitializationCompleted();
        }
    }
}
