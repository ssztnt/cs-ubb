using System;

namespace AvaloniaApplication1.Models
{
    public class Participant
    {
        public string IdParticipant { get; set; }
        public string Nume { get; set; }
        public string Prenume { get; set; }
        public string Varsta { get; set; }
        public string Email { get; set; }

        public Participant(string nume, string prenume, string varsta, string email)
        {
            Nume = nume;
            Prenume = prenume;
            Varsta = varsta;
            Email = email;
        }

        public override string ToString()
        {
            return $"Participant{{IdParticipant='{IdParticipant}', Nume='{Nume}', Prenume='{Prenume}', Varsta='{Varsta}', Email='{Email}'}}";
        }

        public override bool Equals(object obj)
        {
            return obj is Participant other &&
                   IdParticipant == other.IdParticipant &&
                   Nume == other.Nume &&
                   Prenume == other.Prenume &&
                   Varsta == other.Varsta &&
                   Email == other.Email;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(IdParticipant, Nume, Prenume, Varsta, Email);
        }
    }
}