using System;
using AvaloniaApplication1.Models;

namespace AvaloniaApplication1.Validation
{
    public static class ConcursValidator
    {
        public static (bool IsValid, string ErrorMessage) Validate(Concurs concurs)
        {
            if (string.IsNullOrWhiteSpace(concurs.Nume))
                return (false, "Numele concursului este obligatoriu.");
            if (string.IsNullOrWhiteSpace(concurs.Locatie))
                return (false, "Locația este obligatorie.");

            return (true, string.Empty);
        }
    }
}