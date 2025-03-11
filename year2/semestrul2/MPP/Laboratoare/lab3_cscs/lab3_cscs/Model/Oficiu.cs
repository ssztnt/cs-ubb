namespace lab3_cscs.Model
{
    public class Oficiu
    {
        public string IdOficiu { get; set; }
        public string NumeOficiu { get; set; }
        public string Adresa { get; set; }

        public Oficiu(string idOficiu, string numeOficiu, string adresa)
        {
            IdOficiu = idOficiu;
            NumeOficiu = numeOficiu;
            Adresa = adresa;
        }

        public override string ToString()
        {
            return $"Oficiu{{idOficiu='{IdOficiu}', numeOficiu='{NumeOficiu}', adresa='{Adresa}'}}";
        }

        public override bool Equals(object obj)
        {
            if (this == obj) return true;
            if (obj == null || GetType() != obj.GetType()) return false;
            Oficiu oficiu = (Oficiu)obj;
            return IdOficiu.Equals(oficiu.IdOficiu);
        }

        public override int GetHashCode()
        {
            return IdOficiu.GetHashCode();
        }
    }
}