using Avalonia.Controls;
using Avalonia;
using laborator_01.Models;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text.Json;
using System.Threading.Tasks;
using Avalonia.Interactivity;

namespace laborator_01;

public partial class MainWindow : Window
{
    private FormConfig? _config;
    private Registration? selectedRegistration = null;

    public MainWindow()
    {
        Console.WriteLine("MainWindow constructor reached");
        InitializeComponent();
    }

    private async Task LoadConfig(string fileName)
    {
        try
        {
            string path = Path.Combine(AppContext.BaseDirectory, fileName);
            string json = File.ReadAllText(path);
            Console.WriteLine("Raw JSON:");
            Console.WriteLine(json);

            _config = JsonSerializer.Deserialize<FormConfig>(json, new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            });

            if (_config == null || _config.Master == null || _config.Detail == null)
            {
                Console.WriteLine("Invalid config.");
                return;
            }

            this.Title = _config.Title;
            LoadMasterFromConfig();
        }
        catch (Exception ex)
        {
            Console.WriteLine("Config error: " + ex.Message);
        }
    }
    

    private async void LoadMasterFromConfig()
    {
        if (_config == null)
            return;

        var items = new List<Event>();
        var connString = "Host=localhost;Username=postgres;Password=;Database=plaiurares";

        using var conn = new NpgsqlConnection(connString);
        await conn.OpenAsync();

        using var cmd = new NpgsqlCommand(_config.Master.Query, conn);
        using var reader = await cmd.ExecuteReaderAsync();

        Console.WriteLine("Running query: " + _config.Master.Query);
        Console.WriteLine("Reading master data...");

        while (await reader.ReadAsync())
        {
            int id = -1;
            string title = "";
            DateTime date = DateTime.MinValue;

            // ✅ Get ID safely regardless of type
            object idValue = reader.GetValue(reader.GetOrdinal(_config.Master.IdField));
            if (idValue is int intVal)
            {
                id = intVal;
            }
            else if (int.TryParse(idValue?.ToString(), out int parsedId))
            {
                id = parsedId;
            }

            // ✅ Get title/display safely
            object displayValue = reader.GetValue(reader.GetOrdinal(_config.Master.DisplayField));
            title = displayValue?.ToString() ?? "";

            // ✅ Optional: get date safely if exists
            if (HasColumn(reader, "date") && !reader.IsDBNull(reader.GetOrdinal("date")))
            {
                object dateValue = reader.GetValue(reader.GetOrdinal("date"));
                if (dateValue is DateTime dt)
                    date = dt;
                else
                    DateTime.TryParse(dateValue.ToString(), out date);
            }

            items.Add(new Event
            {
                Id = id,
                Title = title,
                Date = date
            });
        }

        Console.WriteLine($"Total items loaded: {items.Count}");
        EventsListBox.ItemsSource = items;
    }


    private bool HasColumn(NpgsqlDataReader reader, string columnName)
    {
        for (int i = 0; i < reader.FieldCount; i++)
        {
            if (reader.GetName(i).Equals(columnName, StringComparison.OrdinalIgnoreCase))
                return true;
        }
        return false;
    }


    private async void OnEventSelected(object? sender, SelectionChangedEventArgs e)
    {
        if (_config == null || EventsListBox.SelectedItem is not Event selectedEvent)
            return;

        var detailItems = new List<string>(); // using string as a flexible display type
        var connString = "Host=localhost;Username=postgres;Password=;Database=plaiurares";

        using var conn = new NpgsqlConnection(connString);
        await conn.OpenAsync();

        string query = _config.Detail.Query;
        using var cmd = new NpgsqlCommand(query, conn);
        cmd.Parameters.AddWithValue("id", selectedEvent.Id);

        using var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            var parts = new List<string>();

            for (int i = 0; i < reader.FieldCount; i++)
            {
                string column = reader.GetName(i);
                object value = reader.IsDBNull(i) ? "NULL" : reader.GetValue(i);
                parts.Add($"{column}: {value}");
            }

            detailItems.Add(string.Join(" | ", parts));
        }

        RegistrationsListBox.ItemsSource = detailItems;
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
        {
            await MessageBox("No registration selected.");
            return;
        }

        try
        {
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
        catch (Exception ex)
        {
            Debug.WriteLine($"Error deleting registration: {ex.Message}");
            await MessageBox("An error occurred while deleting the registration.");
        }
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
    
    private async void OnLoadEventsConfig(object? sender, RoutedEventArgs e)
    {
        await LoadConfig("form_config.json");
    }

    private async void OnLoadOrganizersConfig(object? sender, RoutedEventArgs e)
    {
        await LoadConfig("form_config_organizers.json");
    }
    private async void OnLoadParticipantsConfig(object? sender, RoutedEventArgs e)
    {
        await LoadConfig("form_config_participants.json");
    }

    private async void OnLoadFeedbackConfig(object? sender, RoutedEventArgs e)
    {
        await LoadConfig("form_config_feedback.json");
    }



}