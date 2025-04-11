using Avalonia.Controls;
using AvaloniaApplication1.Services;
using AvaloniaApplication1.ViewModels;

namespace AvaloniaApplication1.Views
{
    public partial class MainWindow : Window
    {
        public MainWindow(IAtletismService service)
        {
            InitializeComponent();
            DataContext = new MainWindowViewModel(service);
        }
    }
}