using Avalonia.Controls;
using laborator_01.Models;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;

namespace laborator_01;

public partial class MainWindow : Window
{   
    private Registration? selectedRegistration = null;

    public MainWindow()
    {
        InitializeComponent();
        LoadEvents();
    }

    private async void LoadEvents()
    {
        var events = await GetEventsFromDatabase();

        Debug.WriteLine($"Loaded {events.Count} events");
        EventsListBox.ItemsSource = events;
    }

    private async void OnEventSelected(object? sender, SelectionChangedEventArgs e)
    {
        if (EventsListBox.SelectedItem is Event selectedEvent)
        {
            Debug.WriteLine($"Selected event: {selectedEvent.Title} (ID: {selectedEvent.Id})");

            var registrations = await GetRegistrationsForEvent(selectedEvent.Id);

            Debug.WriteLine($"Found {registrations.Count} registrations");

            RegistrationsListBox.ItemsSource = registrations;
        }
    }

    private async Task<List<Event>> GetEventsFromDatabase()
    {
        var events = new List<Event>();
        var connString = "Host=localhost;Username=postgres;Password=;Database=plaiurares";

        using var conn = new NpgsqlConnection(connString);
        await conn.OpenAsync();

        string query = "SELECT id, title, date FROM Events";
        using var cmd = new NpgsqlCommand(query, conn);

        using var reader = await cmd.ExecuteReaderAsync();
        while (await reader.ReadAsync())
        {
            events.Add(new Event
            {
                Id = reader.GetInt32(0),
                Title = reader.GetString(1),
                Date = reader.GetDateTime(2)
            });
        }

        return events;
    }

    private async Task<List<Registration>> GetRegistrationsForEvent(int eventId)
    {
        var result = new List<Registration>();
        var connString = "Host=localhost;Username=postgres;Password=;Database=plaiurares";

        using var conn = new NpgsqlConnection(connString);
        await conn.OpenAsync();

        string query = "SELECT id, participant_id, event_id, registration_date FROM Registrations WHERE event_id = @eventId";
        using var cmd = new NpgsqlCommand(query, conn);
        cmd.Parameters.AddWithValue("eventId", eventId);

        using var reader = await cmd.ExecuteReaderAsync();
        while (await reader.ReadAsync())
        {
            result.Add(new Registration
            {
                Id = reader.GetInt32(0),
                ParticipantId = reader.GetInt32(1),
                EventId = reader.GetInt32(2),
                RegistrationDate = reader.GetDateTime(3)
            });
        }

        return result;
    }
    
    private void OnRegistrationSelected(object? sender, SelectionChangedEventArgs e)
    {
        if (RegistrationsListBox.SelectedItem is Registration reg)
        {
            selectedRegistration = reg;
            RegistrationDateTextBox.Text = reg.RegistrationDate.ToString("yyyy-MM-dd");
        }
    }
    
    private async void OnDeleteClick(object? sender, Avalonia.Interactivity.RoutedEventArgs e)
    {
        if (selectedRegistration is null)
            return;

        var connString = "Host=localhost;Username=postgres;Password=;Database=plaiurares";

        using var conn = new NpgsqlConnection(connString);
        await conn.OpenAsync();

        var cmd = new NpgsqlCommand("DELETE FROM Registrations WHERE id = @id", conn);
        cmd.Parameters.AddWithValue("id", selectedRegistration.Id);
        await cmd.ExecuteNonQueryAsync();

        // Reîncarcă lista după ștergere
        if (EventsListBox.SelectedItem is Event selectedEvent)
        {
            var registrations = await GetRegistrationsForEvent(selectedEvent.Id);
            RegistrationsListBox.ItemsSource = registrations;
        }

        selectedRegistration = null;
        RegistrationDateTextBox.Text = "";
    }
    
    private async void OnUpdateClick(object? sender, Avalonia.Interactivity.RoutedEventArgs e)
    {
        if (selectedRegistration is null)
            return;

        if (!DateTime.TryParse(RegistrationDateTextBox.Text, out DateTime newDate))
        {
            await MessageBox("Invalid date format (expected: yyyy-MM-dd)");
            return;
        }

        var connString = "Host=localhost;Username=postgres;Password=;Database=plaiurares";
        using var conn = new NpgsqlConnection(connString);
        await conn.OpenAsync();

        var cmd = new NpgsqlCommand("UPDATE Registrations SET registration_date = @date WHERE id = @id", conn);
        cmd.Parameters.AddWithValue("date", newDate);
        cmd.Parameters.AddWithValue("id", selectedRegistration.Id);
        await cmd.ExecuteNonQueryAsync();

        // Reîncarcă lista după update
        if (EventsListBox.SelectedItem is Event selectedEvent)
        {
            var registrations = await GetRegistrationsForEvent(selectedEvent.Id);
            RegistrationsListBox.ItemsSource = registrations;
        }

        RegistrationDateTextBox.Text = "";
        selectedRegistration = null;
    }
    
    
    private async Task MessageBox(string message)
    {
        await new Window
        {
            Width = 300,
            Height = 100,
            Content = new TextBlock { Text = message, VerticalAlignment = Avalonia.Layout.VerticalAlignment.Center, HorizontalAlignment = Avalonia.Layout.HorizontalAlignment.Center },
            WindowStartupLocation = WindowStartupLocation.CenterOwner
        }.ShowDialog(this);
    }
    
    private async void OnAddRegistrationClick(object? sender, Avalonia.Interactivity.RoutedEventArgs e)
    {
        if (EventsListBox.SelectedItem is not Event selectedEvent)
        {
            await MessageBox("Select an event first.");
            return;
        }

        if (!int.TryParse(ParticipantIdTextBox.Text, out int participantId))
        {
            await MessageBox("Invalid participant ID.");
            return;
        }

        if (!DateTime.TryParse(NewRegistrationDateTextBox.Text, out DateTime registrationDate))
        {
            await MessageBox("Invalid date format (expected: yyyy-MM-dd).");
            return;
        }

        var connString = "Host=localhost;Username=postgres;Password=;Database=plaiurares";

        using var conn = new NpgsqlConnection(connString);
        await conn.OpenAsync();

        var insertCmd = new NpgsqlCommand("INSERT INTO Registrations (participant_id, event_id, registration_date) VALUES (@pid, @eid, @rdate)", conn);
        insertCmd.Parameters.AddWithValue("pid", participantId);
        insertCmd.Parameters.AddWithValue("eid", selectedEvent.Id);
        insertCmd.Parameters.AddWithValue("rdate", registrationDate);

        await insertCmd.ExecuteNonQueryAsync();

        // Reîncarcă lista de înregistrări
        var registrations = await GetRegistrationsForEvent(selectedEvent.Id);
        RegistrationsListBox.ItemsSource = registrations;

        // Golește câmpurile
        ParticipantIdTextBox.Text = "";
        NewRegistrationDateTextBox.Text = "";
    }

}
