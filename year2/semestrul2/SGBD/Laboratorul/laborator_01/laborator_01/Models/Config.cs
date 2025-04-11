namespace laborator_01.Models;

public class FormConfig
{
    public string Title { get; set; }
    public MasterConfig Master { get; set; }
    public DetailConfig Detail { get; set; }
}

public class MasterConfig
{
    public string Query { get; set; }
    public string Table { get; set; }
    public string DisplayField { get; set; }
    public string IdField { get; set; }
}

public class DetailConfig
{
    public string Query { get; set; }
    public string Table { get; set; }
    public string ForeignKey { get; set; }
}
