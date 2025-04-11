namespace laborator_01.Models;

public class Organizer
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string Summary => Name;
}
