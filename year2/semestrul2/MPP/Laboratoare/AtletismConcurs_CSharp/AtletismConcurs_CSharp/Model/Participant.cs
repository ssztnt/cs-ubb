namespace AtletismConcurs_CSharp.Model
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
            return IdParticipant.Equals(that.IdParticipant) &&
                   Nume.Equals(that.Nume) &&
                   Prenume.Equals(that.Prenume) &&
                   Varsta.Equals(that.Varsta) &&
                   Email.Equals(that.Email);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(IdParticipant, Nume, Prenume, Varsta, Email);
        }

        public override string ToString()
        {
            return $"Participant{{idParticipant='{IdParticipant}', nume='{Nume}', prenume='{Prenume}', varsta='{Varsta}', email='{Email}'}}";
        }
    }
}