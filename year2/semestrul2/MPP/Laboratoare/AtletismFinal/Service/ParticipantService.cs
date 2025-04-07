namespace AtletismFinal.Service;


using System;
using AtletismFinal.Domain;
using AtletismFinal.Repository;
using log4net;

public class ParticipantService
{
    private readonly DBParticipantRepository _repository;
    private static readonly ILog log = LogManager.GetLogger(typeof(ParticipantService));

    public ParticipantService(DBParticipantRepository repository)
    {
        _repository = repository;
    }

    public void RegisterParticipant(string nume, string prenume, string varsta, string email)
    {
        log.Info("Registering participant...");

        if (string.IsNullOrWhiteSpace(nume) ||
            string.IsNullOrWhiteSpace(prenume) ||
            string.IsNullOrWhiteSpace(varsta) ||
            string.IsNullOrWhiteSpace(email))
        {
            throw new ArgumentException("All participant fields are required.");
        }

        var participant = new Participant(
            idParticipant: Guid.NewGuid().ToString(),
            nume: nume,
            prenume: prenume,
            varsta: varsta,
            email: email
        );

        // Do the actual insert
        _repository.AddParticipant(participant); // We will add this method next

        log.Info("Participant registered.");
    }
}
