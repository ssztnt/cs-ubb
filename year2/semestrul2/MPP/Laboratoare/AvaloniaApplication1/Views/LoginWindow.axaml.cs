using Avalonia.Controls;
using AvaloniaApplication1.Services;

namespace AvaloniaApplication1.Views
{
    public partial class LoginWindow : Window
    {
        public LoginWindow(IAtletismService service)
        {
            InitializeComponent();

            // Set LoginView as content manually
            Content = new LoginView(service);
        }
    }

}