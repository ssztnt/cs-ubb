using AvaloniaApplication1.Models;
using AvaloniaApplication1.Services;
using AvaloniaApplication1.Validation;
using ReactiveUI;
using System;
using System.Reactive;

namespace AvaloniaApplication1.ViewModels
{
    public class RegisterViewModel : ViewModelBase
    {
        private readonly IAtletismService _service;

        public string Nume { get; set; } = "";
        public string Prenume { get; set; } = "";
        public string Varsta { get; set; } = "";
        public string Email { get; set; } = "";

        public string ErrorMessage { get; set; } = "";
        public string SuccessMessage { get; set; } = "";

        public ReactiveCommand<Unit, Unit> RegisterCommand { get; }

        public RegisterViewModel(IAtletismService service)
        {
            _service = service;
            RegisterCommand = ReactiveCommand.Create(RegisterParticipant);
        }

        
        private void RegisterParticipant()
        {
            var participant = new Participant(Nume, Prenume, Varsta, Email);

            if (!ParticipantValidator.IsValid(participant, out string validationError))
            {
                ErrorMessage = validationError;
                SuccessMessage = "";
                return;
            }

            _service.SaveParticipant(participant);

            SuccessMessage = "✅ Participant registered!";
            ErrorMessage = "";
            ClearFields();
        }


                

        private void ClearFields()
        {
            Nume = "";
            Prenume = "";
            Varsta = "";
            Email = "";
        }
    }
}