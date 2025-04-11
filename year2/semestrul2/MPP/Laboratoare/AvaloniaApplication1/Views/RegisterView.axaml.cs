using Avalonia.Controls;
using AvaloniaApplication1.Services;
using AvaloniaApplication1.ViewModels;

namespace AvaloniaApplication1.Views
{
    public partial class RegisterView : UserControl
    {
        public RegisterView(IAtletismService service)
        {
            InitializeComponent();
            DataContext = new RegisterViewModel(service);
        }
    }
}