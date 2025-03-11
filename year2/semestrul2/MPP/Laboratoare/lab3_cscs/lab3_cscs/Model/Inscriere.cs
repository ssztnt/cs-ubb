namespace lab3_cscs.Model
{
    public class Inscriere
    {
        public string IdInscriere { get; set; }
        public string IdParticipant { get; set; }
        public string IdProba { get; set; }
        public string DataInscriere { get; set; }

        public Inscriere(string idInscriere, string idParticipant, string idProba, string dataInscriere)
        {
            IdInscriere = idInscriere;
            IdParticipant = idParticipant;
            IdProba = idProba;
            DataInscriere = dataInscriere;
        }

        public override string ToString()
        {
            return $"Inscriere{{idInscriere='{IdInscriere}', idParticipant='{IdParticipant}', idProba='{IdProba}', dataInscriere='{DataInscriere}'}}";
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