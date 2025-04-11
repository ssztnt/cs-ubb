using AvaloniaApplication1.Models;
using AvaloniaApplication1.Services;
using ReactiveUI;
using System.Collections.ObjectModel;

namespace AvaloniaApplication1.ViewModels
{
    public class ParticipantListViewModel : ViewModelBase
    {
        private readonly IAtletismService _service;

        public ObservableCollection<Participant> Participants { get; } = new();

        public ParticipantListViewModel(IAtletismService service)
        {
            _service = service;
            LoadParticipants(); // ✅ THIS SHOULD BE CALLED
        }

        private void LoadParticipants()
        {
            var all = _service.FindAllParticipants();
            Participants.Clear();
            foreach (var p in all)
                Participants.Add(p);
        }
    }
}