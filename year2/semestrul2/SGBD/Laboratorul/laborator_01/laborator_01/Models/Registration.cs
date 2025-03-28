using System;

namespace laborator_01.Models
{
    public class Registration
    {
        public int Id { get; set; }
        public int ParticipantId { get; set; }
        public int EventId { get; set; }
        public DateTime RegistrationDate { get; set; }
        
        public override string ToString() =>
            $"Participant ID: {ParticipantId}, Registered: {RegistrationDate:yyyy-MM-dd}";
    }
}