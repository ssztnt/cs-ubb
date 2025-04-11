using System;
using System.Collections.ObjectModel;
using AvaloniaApplication1.Models;
using AvaloniaApplication1.Services;

namespace AvaloniaApplication1.ViewModels
{
    public class ConcursListViewModel : ViewModelBase
    {
        private readonly IAtletismService _service;

        public ObservableCollection<Concurs> Concursuri { get; set; } = new();

        public ConcursListViewModel(IAtletismService service)
        {
            _service = service ?? throw new ArgumentNullException(nameof(service));
            LoadConcursuri();
        }

        private void LoadConcursuri()
        {
            var concursuri = _service.FindAllConcursuri();

            // 🔐 Defensive check to avoid null crash
            if (concursuri != null)
            {
                foreach (var concurs in concursuri)
                {
                    Concursuri.Add(concurs);
                }
            }
        }
    }
}