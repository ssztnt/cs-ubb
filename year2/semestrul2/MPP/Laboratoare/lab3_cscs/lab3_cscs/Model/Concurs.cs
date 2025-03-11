namespace lab3_cscs.Model;
public class Concurs
    {
        public string IdConcurs { get; set; }
        public string Nume { get; set; }
        public string Data { get; set; }
        public string Locatie { get; set; }

        public Concurs(string idConcurs, string nume, string data, string locatie)
        {
            IdConcurs = idConcurs;
            Nume = nume;
            Data = data;
            Locatie = locatie;
        }

        public override string ToString()
        {
            return $"Concurs{{idConcurs='{IdConcurs}', nume='{Nume}', data='{Data}', locatie='{Locatie}'}}";
        }

        public override bool Equals(object obj)
        {
            if (this == obj) return true;
            if (obj == null || GetType() != obj.GetType()) return false;
            Concurs concurs = (Concurs)obj;
            return IdConcurs.Equals(concurs.IdConcurs);
        }

        public override int GetHashCode()
        {
            return IdConcurs.GetHashCode();
        }
    }
