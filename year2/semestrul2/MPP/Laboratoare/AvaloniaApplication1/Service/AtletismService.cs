using System;
using System.Collections.Generic;
using AvaloniaApplication1.Models;
using AvaloniaApplication1.Repositories.Db;
using AvaloniaApplication1.Repositories.Interfaces;

namespace AvaloniaApplication1.Services
{
    public class AtletismService : IAtletismService
    {
        private readonly DbParticipantRepository _participantRepository;
        private readonly DbInscriereRepository _inscriereRepository;
        private readonly DbConcursRepository _concursRepository;

        public AtletismService(IParticipantRepository participantRepo, DbInscriereRepository inscriereRepo, DbConcursRepository concursRepo)
        {
            _participantRepository = (DbParticipantRepository)participantRepo;
            _inscriereRepository = (DbInscriereRepository)inscriereRepo;
            _concursRepository = (DbConcursRepository)concursRepo;
        }

        public List<Participant> GetAllParticipants()
        {
            return _participantRepository.FindAll();
        }

        public List<Participant> GetParticipantsByNameAndSurname(string name, string surname)
        {
            return _participantRepository.FindByNameAndSurname(name, surname);
        }

        public List<Participant> GetParticipantsByEmail(string email)
        {
            return _participantRepository.FindByEmail(email);
        }

        public void SaveParticipant(Participant participant)
        {
            _participantRepository.SaveParticipant(participant);
        }

        public void SaveInscriere(Inscriere inscriere)
        {
            _inscriereRepository.Save(inscriere);
        }

        public List<Concurs> GetConcursList()
        {
            return new List<Concurs>(_concursRepository.FindAll());
        }

        public List<Inscriere> GetAllInscrieri()
        {
            return new List<Inscriere>(_inscriereRepository.FindAll());
        }

        public List<Concurs> GetConcursByName(string name)
        {
            return new List<Concurs>(_concursRepository.FindByName(name));
        }
        
        public List<Participant> FindAllParticipants()
        {
            return new List<Participant>(_participantRepository.FindAll());
        }

        public List<Concurs> FindAllConcursuri()
        {
            return new List<Concurs>(_concursRepository.FindAll());
        }

        public List<Inscriere> FindAllInscrieri()
        {
            return new List<Inscriere>(_inscriereRepository.FindAll());
        }
        
        public void AddInscriere(string idParticipant, string concursName)
        {
            var timestamp = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            var inscriere = new Inscriere(Guid.NewGuid().ToString(), idParticipant, concursName, timestamp);
            _inscriereRepository.Save(inscriere);
        }
        
        

    }
    
}