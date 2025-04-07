using Avalonia;
using Avalonia.Controls.ApplicationLifetimes;
using Avalonia.Markup.Xaml;

namespace AtletismFinal;

public partial class App : Application
{
    public override void Initialize()
    {
        AvaloniaXamlLoader.Load(this); // must load App.axaml
    }

    public override void OnFrameworkInitializationCompleted()
    {
        if (ApplicationLifetime is IClassicDesktopStyleApplicationLifetime desktop)
        {
            desktop.MainWindow = new MainWindow(); // this must not be null
        }

        base.OnFrameworkInitializationCompleted();
    }
}