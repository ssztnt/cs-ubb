namespace lab3_cscs.Model
{
    public class Proba
    {
        public string IdProba { get; set; }
        public string NumeProba { get; set; }
        public string Distanta { get; set; }
        public string IdConcurs { get; set; }

        public Proba(string idProba, string numeProba, string distanta, string idConcurs)
        {
            IdProba = idProba;
            NumeProba = numeProba;
            Distanta = distanta;
            IdConcurs = idConcurs;
        }

        public override string ToString()
        {
            return $"Proba{{idProba='{IdProba}', numeProba='{NumeProba}', distanta='{Distanta}', idConcurs='{IdConcurs}'}}";
        }

        public override bool Equals(object obj)
        {
            if (this == obj) return true;
            if (obj == null || GetType() != obj.GetType()) return false;
            Proba proba = (Proba)obj;
            return IdProba.Equals(proba.IdProba);
        }

        public override int GetHashCode()
        {
            return IdProba.GetHashCode();
        }
    }
}