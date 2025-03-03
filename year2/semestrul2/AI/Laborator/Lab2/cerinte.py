import csv

# Citirea fișierului CSV
file_path = '/Users/plaiurares/cs-ubb/year2/semestrul2/AI/Laborator/Lab2/surveyDataSience.csv'


def read_csv(file_path):
    with open(file_path, mode='r') as file:
        reader = csv.reader(file)
        # Sărim peste antet
        next(reader)
        # Numărăm rândurile
        num_respondents = sum(1 for row in reader)

    print(f"Numărul de respondenți: {num_respondents}"  )

def numar_si_tip_atribute(file_path):
    with open(file_path, mode='r') as file:
        reader = csv.reader(file)
        # Citim antetul
        header = next(reader)
        num_atribute = len(header)
        tip_atribute = [type(value).__name__ for value in header]
        
    print(f"Numărul de atribute: {num_atribute}")
    print(f"Tipurile de atribute: {tip_atribute}")

def numar_respondenti_completi(file_path):
    with open(file_path, mode='r') as file:
        reader = csv.reader(file)
        # Citim antetul
        header = next(reader)
        num_atribute = len(header)
        # Numărăm rândurile complete
        num_respondenti_completi = sum(1 for row in reader if len(row) == num_atribute)

def main():
    read_csv(file_path)
    numar_si_tip_atribute(file_path)
    numar_respondenti_completi(file_path)

main()
    