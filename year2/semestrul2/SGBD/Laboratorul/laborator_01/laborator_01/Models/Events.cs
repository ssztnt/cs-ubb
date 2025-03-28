using System;

namespace laborator_01.Models;

public class Event
{
    public int Id { get; set; }
    public string Title { get; set; }
    public DateTime Date { get; set; }  
    
    public override string ToString() => $"{Title} ({Date:yyyy-MM-dd})";

    // Custom method

    public string Summary()
    {
        return $"{Title} - {Date:yyyy-MM-dd}";
    }
}