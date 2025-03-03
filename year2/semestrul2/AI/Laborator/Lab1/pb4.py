def cuvinte_care_apar_doar_o_data(text):
    """
    Returneaza o lista cu cuvintele care apar o singura data in text.
    """
    cuvinte = text.split()
    aparitii = {}
    for cuvant in cuvinte:
        if cuvant in aparitii:
            aparitii[cuvant] += 1
        else:
            aparitii[cuvant] = 1
    return [cuvant for cuvant in aparitii if aparitii[cuvant] == 1]

def cuvinte_unice(text):
    # Împarte textul în cuvinte
    cuvinte = text.split()
    
    # Folosim un dicționar pentru a număra aparițiile fiecărui cuvânt
    frecventa = {}
    for cuvant in cuvinte:
        if cuvant in frecventa:
            frecventa[cuvant] += 1
        else:
            frecventa[cuvant] = 1
    
    # Selectăm cuvintele care apar exact o dată
    cuvinte_unice = [cuvant for cuvant, count in frecventa.items() if count == 1]
    
    return cuvinte_unice


def meniu():
    print("1. Cuvinte care apar o singura data")
    print("2. Cuvinte unice")
    print("3. Iesire")
    optiune = input("Optiune: ")
    return optiune
optiune = meniu()
while optiune != "3":
    text = input("Introduceti textul: ")
    if optiune == "1":
        print(cuvinte_care_apar_doar_o_data(text))
    elif optiune == "2":
        print(cuvinte_unice(text))
    optiune = meniu()
print("La revedere!")
