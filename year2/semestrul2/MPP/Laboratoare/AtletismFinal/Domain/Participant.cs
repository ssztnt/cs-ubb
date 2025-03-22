using System;

namespace AtletismFinal.Domain
{
    public class Participant
    {
        public string IdParticipant { get; set; }
        public string Nume { get; set; }
        public string Prenume { get; set; }
        public string Varsta { get; set; }
        public string Email { get; set; }

        public Participant(string idParticipant, string nume, string prenume, string varsta, string email)
        {
            IdParticipant = idParticipant;
            Nume = nume;
            Prenume = prenume;
            Varsta = varsta;
            Email = email;
        }

        public override bool Equals(object obj)
        {
            if (this == obj) return true;
            if (obj == null || GetType() != obj.GetType()) return false;
            Participant that = (Participant)obj;
            return IdParticipant == that.IdParticipant &&
                   Nume == that.Nume &&
                   Prenume == that.Prenume &&
                   Varsta == that.Varsta &&
                   Email == that.Email;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(IdParticipant, Nume, Prenume, Varsta, Email);
        }

        public override string ToString()
        {
            return $"Participant{{IdParticipant='{IdParticipant}', Nume='{Nume}', Prenume='{Prenume}', Varsta='{Varsta}', Email='{Email}'}}";
        }
    }
}