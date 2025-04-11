using Avalonia;
using Avalonia.Controls;
using Avalonia.Controls.ApplicationLifetimes;
using Avalonia.Threading;
using AvaloniaApplication1.Services;
using AvaloniaApplication1.ViewModels;

namespace AvaloniaApplication1.Views
{
    public partial class LoginView : UserControl
    {
        public LoginView(IAtletismService service)
        {
            InitializeComponent();

            var vm = new LoginViewModel(service);
            DataContext = vm;

            vm.LoginSucceeded += () =>
            {
                Dispatcher.UIThread.Post(() =>
                {
                    var mainWindow = new MainWindow(service);

                    // Show main window
                    mainWindow.Show();

                    // HIDE login instead of closing it (especially if it's MainWindow)
                    var loginWindow = this.VisualRoot as Window;
                    loginWindow?.Hide();
                });
            };


        }
    }
}