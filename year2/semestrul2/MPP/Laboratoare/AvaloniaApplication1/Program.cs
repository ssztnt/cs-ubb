using Avalonia;
using System;
using AvaloniaApplication1.Views;
using AvaloniaApplication1.Services;
using AvaloniaApplication1.Repositories.Db;
using AvaloniaApplication1.Repositories.Utils;
using Microsoft.Extensions.Configuration;
using Avalonia.Controls.ApplicationLifetimes;
using ReactiveUI;
using Avalonia.ReactiveUI;

namespace AvaloniaApplication1
{
    class Program
    {
        [STAThread]
        public static void Main(string[] args)
        {
            BuildAvaloniaApp()
                .StartWithClassicDesktopLifetime(args);
        }

        public static AppBuilder BuildAvaloniaApp()
            => AppBuilder.Configure<App>()
                .UsePlatformDetect()
                .UseReactiveUI() // ✅ Required if using ReactiveUI
                .LogToTrace();
    }
}