using AvaloniaApplication1.Models;
using AvaloniaApplication1.Services;
using AvaloniaApplication1.Validation;
using ReactiveUI;
using System;
using System.Collections.Generic;
using System.Reactive;
using System.Reactive.Concurrency;

namespace AvaloniaApplication1.ViewModels
{
    public class LoginViewModel : ViewModelBase
    {
        private readonly IAtletismService _service;

        public event Action? LoginSucceeded;
        public ReactiveCommand<Unit, Unit> LoginCommand { get; }

        public string Email { get; set; } = "";
        public string Password { get; set; } = "";
        public string ErrorMessage { get; set; } = "";

        public LoginViewModel(IAtletismService service)
        {
            _service = service;
            LoginCommand = ReactiveCommand.Create(ExecuteLogin);
        }

        private void ExecuteLogin()
        {
            if (Password != "magic")
            {
                ErrorMessage = "Parola este incorectă.";
                return;
            }

            if (!ParticipantValidator.IsValidEmail(Email))
            {
                ErrorMessage = "Formatul emailului nu este valid.";
                return;
            }

            List<Participant> found = _service.GetParticipantsByEmail(Email);
            if (found.Count == 0)
            {
                ErrorMessage = "Nu există niciun participant cu acest email.";
                return;
            }

            // ✅ Login OK
            ErrorMessage = "";
            LoginSucceeded?.Invoke();
        }
    }
}