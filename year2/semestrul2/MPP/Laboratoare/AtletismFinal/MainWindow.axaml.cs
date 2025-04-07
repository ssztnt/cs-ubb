using Avalonia.Controls;
using Avalonia.Interactivity;
using System;
using System.Configuration;
using AtletismFinal.Service;
using AtletismFinal.Repository;

namespace AtletismFinal;

public partial class MainWindow : Window
{
    private readonly ParticipantService _service;

    public MainWindow()
    {
        InitializeComponent();
        
        var connectionString = ConfigurationManager.ConnectionStrings["SQLiteConnection"]?.ConnectionString;
        var repo = new DBParticipantRepository(connectionString);
        _service = new ParticipantService(repo);
    }

    private void OnRegisterClicked(object? sender, RoutedEventArgs e)
    {
        string nume = NumeBox.Text;
        string prenume = PrenumeBox.Text;
        string varsta = VarstaBox.Text;
        string email = EmailBox.Text;

        try
        {
            _service.RegisterParticipant(nume, prenume, varsta, email);
            StatusText.Text = "Participant registered successfully!";
            StatusText.Foreground = Avalonia.Media.Brushes.Green;

            NumeBox.Text = PrenumeBox.Text = VarstaBox.Text = EmailBox.Text = "";
        }
        catch (Exception ex)
        {
            StatusText.Text = $"Error: {ex.Message}";
            StatusText.Foreground = Avalonia.Media.Brushes.Red;
            
            NumeBox.Text = PrenumeBox.Text = VarstaBox.Text = EmailBox.Text = "";
            
        }
    }
}