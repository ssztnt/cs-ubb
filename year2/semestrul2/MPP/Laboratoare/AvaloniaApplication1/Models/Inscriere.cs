using System;

namespace AvaloniaApplication1.Models
{
    public class Inscriere : Entity<long>
    {
        public string IdInscriere { get; set; }
        public string IdParticipant { get; set; }
        public string ConcursName { get; set; }
        public string Timestamp { get; set; }

        public Inscriere(string idInscriere, string idParticipant, string concursName, string timestamp)
        {
            IdInscriere = idInscriere;
            IdParticipant = idParticipant;
            ConcursName = concursName;
            Timestamp = timestamp;
        }

        public override string ToString()
        {
            return $"Inscriere{{Id='{IdInscriere}', Participant='{IdParticipant}', Concurs='{ConcursName}', Timestamp='{Timestamp}'}}";
        }

        public override bool Equals(object obj)
        {
            return obj is Inscriere inscriere && IdInscriere == inscriere.IdInscriere;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(IdInscriere);
        }
    }
}