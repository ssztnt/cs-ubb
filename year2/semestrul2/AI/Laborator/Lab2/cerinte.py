import csv

# Citirea fișierului CSV
file_path = '/Users/plaiurares/cs-ubb/year2/semestrul2/AI/Laborator/Lab2/surveyDataSience.csv'

with open(file_path, mode='r') as file:
    reader = csv.reader(file)
    # Sărim peste antet
    next(reader)
    # Numărăm rândurile
    num_respondents = sum(1 for row in reader)

print(f"Numărul de respondenți: {num_respondents}")