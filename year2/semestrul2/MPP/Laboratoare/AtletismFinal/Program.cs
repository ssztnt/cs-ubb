using Avalonia;
using Avalonia.Controls.ApplicationLifetimes;
using Avalonia.ReactiveUI;
using log4net;
using log4net.Config;
using System;
using System.Configuration;
using System.IO;
using System.Reflection;

namespace AtletismFinal;

public static class Program
{
    private static readonly ILog log = LogManager.GetLogger(typeof(Program));

    [STAThread]
    public static void Main(string[] args)
    {
        // ✅ Initialize log4net
        var logRepository = LogManager.GetRepository(Assembly.GetEntryAssembly());
        XmlConfigurator.Configure(logRepository, new FileInfo("log4net.config"));
        log.Info("Starting Avalonia App");

        // ✅ Start Avalonia UI App
        BuildAvaloniaApp().StartWithClassicDesktopLifetime(args);

        log.Info("Shutting down Avalonia App");
    }

    // Avalonia App Builder
    public static AppBuilder BuildAvaloniaApp()
        => AppBuilder.Configure<App>()
            .UsePlatformDetect()
            .LogToTrace()
            .UseReactiveUI();
}