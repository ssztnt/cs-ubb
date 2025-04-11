using Avalonia;
using Avalonia.Controls;
using Avalonia.Markup.Xaml;
using AvaloniaApplication1.ViewModels;

namespace AvaloniaApplication1.Views;

public partial class ParticipantListView : UserControl
{
    public ParticipantListView()
    {
        InitializeComponent();
        DataContext = new ParticipantListViewModel(App.service!); // ✅ Make sure App.service is not null
    }
}