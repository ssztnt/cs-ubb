using System;
using System.Collections.Generic;

namespace AvaloniaApplication1.Models
{
    [Serializable]
    public class Entity<TId>
    {
        public TId Id { get; set; }

        public override bool Equals(object obj)
        {
            return obj is Entity<TId> entity && EqualityComparer<TId>.Default.Equals(Id, entity.Id);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Id);
        }
    }
}
