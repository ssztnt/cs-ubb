using System;
using System.Text.RegularExpressions;
using AvaloniaApplication1.Models;

namespace AvaloniaApplication1.Validation
{
    public static class ParticipantValidator
    {
        public static bool IsValid(Participant participant, out string error)
        {
            if (string.IsNullOrWhiteSpace(participant.Nume))
            {
                error = "Nume is required.";
                return false;
            }
            if (string.IsNullOrWhiteSpace(participant.Prenume))
            {
                error = "Prenume is required.";
                return false;
            }
            if (!int.TryParse(participant.Varsta, out int varsta) || varsta <= 0)
            {
                error = "Varsta must be a positive number.";
                return false;
            }
            if (string.IsNullOrWhiteSpace(participant.Email) || !Regex.IsMatch(participant.Email, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                error = "Invalid email format.";
                return false;
            }
            
            

            error = string.Empty;
            return true;
        }
        
        public static bool IsValidEmail(string email)
        {
            return Regex.IsMatch(email,
                @"^[^@\s]+@[^@\s]+\.[^@\s]+$",
                RegexOptions.IgnoreCase);
        }

    }
}