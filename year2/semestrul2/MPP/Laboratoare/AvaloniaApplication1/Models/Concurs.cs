using System;

namespace AvaloniaApplication1.Models
{
    public class Concurs
    {
        public string IdConcurs { get; set; }
        public string Nume { get; set; }
        public DateTime Data { get; set; }
        public string Locatie { get; set; }

        public Concurs(string idConcurs, string nume, string data, string locatie)
        {
            IdConcurs = idConcurs;
            Nume = nume;
            Data = DateTime.Parse(data);
            Locatie = locatie;
        }

        public override string ToString()
        {
            return $"Concurs{{IdConcurs='{IdConcurs}', Nume='{Nume}', Data='{Data}', Locatie='{Locatie}'}}";
        }

        public override bool Equals(object obj)
        {
            return obj is Concurs concurs && IdConcurs == concurs.IdConcurs;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(IdConcurs);
        }
    }
}