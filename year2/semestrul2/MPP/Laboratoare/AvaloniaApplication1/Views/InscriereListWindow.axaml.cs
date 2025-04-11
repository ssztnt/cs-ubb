using Avalonia.Controls;
using AvaloniaApplication1.ViewModels;
using AvaloniaApplication1.Services;

namespace AvaloniaApplication1.Views
{
    public partial class InscriereListWindow : Window
    {
        public InscriereListWindow(IAtletismService service)
        {
            InitializeComponent();
            DataContext = new InscriereListViewModel(service);
        }
    }
}