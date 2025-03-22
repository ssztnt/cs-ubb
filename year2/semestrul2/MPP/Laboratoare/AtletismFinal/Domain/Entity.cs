using System;

namespace AtletismFinal.Domain
{
    [Serializable]
    public class Entity<ID>
    {
        public ID Id { get; set; }

        public override bool Equals(object obj)
        {
            if (this == obj) return true;
            if (obj == null || GetType() != obj.GetType()) return false;
            Entity<ID> entity = (Entity<ID>)obj;
            return EqualityComparer<ID>.Default.Equals(Id, entity.Id);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Id);
        }
    }
}