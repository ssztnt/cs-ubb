using ReactiveUI;
using System.Collections.ObjectModel;
using System.Reactive;
using AvaloniaApplication1.Services;
using AvaloniaApplication1.Views;

namespace AvaloniaApplication1.ViewModels
{
    public class MainWindowViewModel : ViewModelBase
    {
        public ObservableCollection<string> Tabs { get; } = new()
        {
            "Participants", "Concursuri"
        };

        private string _selectedTab = "Participants";
        public string SelectedTab
        {
            get => _selectedTab;
            set => this.RaiseAndSetIfChanged(ref _selectedTab, value);
        }

        public ReactiveCommand<Unit, Unit> OpenInscrieriCommand { get; }
        public ReactiveCommand<Unit, Unit> ViewInscrieriCommand { get; }
        
        


        public InscriereRegisterViewModel InscriereRegisterVM { get; }

        public ParticipantListViewModel ParticipantListVM { get; }
        public ConcursListViewModel ConcursListVM { get; }

        public MainWindowViewModel(IAtletismService service)
        {
            InscriereRegisterVM = new InscriereRegisterViewModel(service);
            ParticipantListVM = new ParticipantListViewModel(service);
            ConcursListVM = new ConcursListViewModel(service);

            OpenInscrieriCommand = ReactiveCommand.Create(() =>
            {
                var window = new InscriereListWindow(service);
                window.Show();
            });
            
            ViewInscrieriCommand = ReactiveCommand.Create(() =>
            {
                var window = new InscriereListWindow(service);
                window.Show();
            });
            
            

        }
    }
}