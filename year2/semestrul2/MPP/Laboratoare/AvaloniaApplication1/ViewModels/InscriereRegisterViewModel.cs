using ReactiveUI;
using System;
using System.Collections.ObjectModel;
using System.Reactive;
using AvaloniaApplication1.Models;
using AvaloniaApplication1.Services;

namespace AvaloniaApplication1.ViewModels
{
    public class InscriereRegisterViewModel : ViewModelBase
    {
        private readonly IAtletismService _service;

        public ObservableCollection<Participant> Participants { get; } = new();
        public ObservableCollection<Concurs> Concursuri { get; } = new();

        private Participant? _selectedParticipant;
        public Participant? SelectedParticipant
        {
            get => _selectedParticipant;
            set => this.RaiseAndSetIfChanged(ref _selectedParticipant, value);
        }

        private Concurs? _selectedConcurs;
        public Concurs? SelectedConcurs
        {
            get => _selectedConcurs;
            set => this.RaiseAndSetIfChanged(ref _selectedConcurs, value);
        }

        public ReactiveCommand<Unit, Unit> RegisterCommand { get; }

        private string _errorMessage = "";
        public string ErrorMessage
        {
            get => _errorMessage;
            set => this.RaiseAndSetIfChanged(ref _errorMessage, value);
        }

        private string _successMessage = "";
        public string SuccessMessage
        {
            get => _successMessage;
            set => this.RaiseAndSetIfChanged(ref _successMessage, value);
        }

        public InscriereRegisterViewModel(IAtletismService service)
        {
            _service = service;

            LoadData();

            RegisterCommand = ReactiveCommand.Create(RegisterInscriere);
        }

        private void LoadData()
        {
            Participants.Clear();
            foreach (var p in _service.FindAllParticipants())
                Participants.Add(p);

            Concursuri.Clear();
            foreach (var c in _service.FindAllConcursuri())
                Concursuri.Add(c);
        }

        private void RegisterInscriere()
        {
            ErrorMessage = "";
            SuccessMessage = "";

            if (SelectedParticipant == null || SelectedConcurs == null)
            {
                ErrorMessage = "You must select a participant and a contest.";
                return;
            }

            // Generate a new ID like i4, i5, etc.
            var existingInscrieri = _service.FindAllInscrieri();
            var nextIdNumber = existingInscrieri.Count + 1;
            var newId = $"i{nextIdNumber}";

            var inscriere = new Inscriere(
                newId,                                         // IdInscriere
                SelectedParticipant.IdParticipant,             // IdParticipant
                SelectedConcurs.Nume,                          // ConcursName
                DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss")   // Timestamp
            );

            try
            {
                _service.SaveInscriere(inscriere);
                SuccessMessage = "Inscriere registered successfully!";
            }
            catch (Exception ex)
            {
                ErrorMessage = $"Error: {ex.Message}";
            }
        }

    }
}
