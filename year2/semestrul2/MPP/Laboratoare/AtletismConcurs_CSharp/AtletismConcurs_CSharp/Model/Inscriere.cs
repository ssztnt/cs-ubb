namespace AtletismConcurs_CSharp.Model
{
    public class Inscriere : Entity<long>
    {
        public string IdInscriere { get; set; }
        public string IdParticipant { get; set; }
        public string DataInscriere { get; set; }

        public Inscriere(string idInscriere, string idParticipant, string dataInscriere)
        {
            IdInscriere = idInscriere;
            IdParticipant = idParticipant;
            DataInscriere = dataInscriere;
        }

        public override string ToString()
        {
            return $"Inscriere{{idInscriere='{IdInscriere}', idParticipant='{IdParticipant}', dataInscriere='{DataInscriere}'}}";
        }

        public override bool Equals(object obj)
        {
            if (this == obj) return true;
            if (obj == null || GetType() != obj.GetType()) return false;
            Inscriere inscriere = (Inscriere)obj;
            return IdInscriere.Equals(inscriere.IdInscriere);
        }

        public override int GetHashCode()
        {
            return IdInscriere.GetHashCode();
        }
    }
}