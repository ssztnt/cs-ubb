using Avalonia.Controls;
using AvaloniaApplication1.ViewModels;

namespace AvaloniaApplication1.Views
{
    public partial class ConcursListView : UserControl
    {
        public ConcursListView()
        {
            InitializeComponent(); // This should now be resolved!
            DataContext = new ConcursListViewModel(App.service!);
        }
    }
}