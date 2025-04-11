using System;
using AvaloniaApplication1.Models;

namespace AvaloniaApplication1.Validation
{
    public static class InscriereValidator
    {
        public static (bool IsValid, string ErrorMessage) Validate(Inscriere inscriere)
        {
            if (string.IsNullOrWhiteSpace(inscriere.IdParticipant))
                return (false, "ID-ul participantului este necesar.");
            if (string.IsNullOrWhiteSpace(inscriere.ConcursName))
                return (false, "Numele concursului este necesar.");
            if (!DateTime.TryParse(inscriere.Timestamp, out _))
                return (false, "Timestamp-ul trebuie să fie un format valid.");

            return (true, string.Empty);
        }
    }
}