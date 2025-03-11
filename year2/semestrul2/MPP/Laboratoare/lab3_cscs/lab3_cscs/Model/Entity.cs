namespace lab3_cscs.Model;

    [Serializable]
    public class Entity<ID>
    {
        private ID id;

        public ID Id
        {
            get { return id; }
            set { id = value; }
        }

        public override bool Equals(object obj)
        {
            if (this == obj) return true;
            if (obj == null || GetType() != obj.GetType()) return false;
            Entity<ID> entity = (Entity<ID>)obj;
            return EqualityComparer<ID>.Default.Equals(Id, entity.Id);
        }

        public override int GetHashCode()
        {
            return EqualityComparer<ID>.Default.GetHashCode(Id);
        }
    }
