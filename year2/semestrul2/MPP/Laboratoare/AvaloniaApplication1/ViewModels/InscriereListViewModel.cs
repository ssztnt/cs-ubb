using ReactiveUI;
using System.Collections.ObjectModel;
using System.Linq;
using System.Reactive;
using AvaloniaApplication1.Models;
using AvaloniaApplication1.Services;

namespace AvaloniaApplication1.ViewModels
{
    public class InscriereListViewModel : ViewModelBase
    {
        private readonly IAtletismService _service;
        public ObservableCollection<Inscriere> Inscrieri { get; } = new();

        public ReactiveCommand<Unit, Unit> SortByConcursCommand { get; }

        public InscriereListViewModel(IAtletismService service)
        {
            _service = service;

            LoadInscrieri();

            SortByConcursCommand = ReactiveCommand.Create(SortInscrieriByConcurs);
        }

        private void LoadInscrieri()
        {
            Inscrieri.Clear();
            foreach (var i in _service.FindAllInscrieri())
                Inscrieri.Add(i);
        }

        private void SortInscrieriByConcurs()
        {
            var sorted = Inscrieri.OrderBy(i => i.ConcursName).ToList();
            Inscrieri.Clear();
            foreach (var i in sorted)
                Inscrieri.Add(i);
        }
    }
}