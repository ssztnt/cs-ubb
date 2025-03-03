# Complexitate timp: O(n)
# Complexitate memorie: O(1)
# parametrii de intrare : arr - lista de numere
# return : elementul care se repeta in lista
def find_duplicate(arr):
    n = len(arr)
    expected_sum = (n - 1) * n // 2
    actual_sum = sum(arr)
    return actual_sum - expected_sum



#Complexitate timp: O(n)
#Complexitate memorie: O(1)
# parametrii de intrare : sir - lista de numere
# return : elementul care se repeta in lista

def gaseste_valoarea_repetata(sir):
    n = len(sir)
    # Calculăm suma așteptată a elementelor unice (1 + 2 + ... + n-1)
    suma_unica = (n - 1) * n // 2
    
    # Calculăm suma elementelor din șir
    suma_sir = sum(sir)
    
    # Valoarea care se repetă este diferența dintre cele două sume
    return suma_sir - suma_unica

def meniu():
    while True:
        print("\n--- Meniu ---")
        print("1. Folosește funcția find_duplicate")
        print("2. Folosește funcția gaseste_valoarea_repetata")
        print("3. Ieșire")
        
        optiune = input("Alege o opțiune (1/2/3): ")
        
        if optiune == "1":
            # Citim șirul de la utilizator
            sir = input("Introdu elementele șirului separate prin spațiu: ").split()
            # Convertim elementele în numere întregi
            sir = [int(x) for x in sir]
            # Apelăm funcția și afișăm rezultatul
            rezultat = find_duplicate(sir)
            print(f"Valoarea care se repetă este: {rezultat}")
        
        elif optiune == "2":
            # Citim șirul de la utilizator
            sir = input("Introdu elementele șirului separate prin spațiu: ").split()
            # Convertim elementele în numere întregi
            sir = [int(x) for x in sir]
            # Apelăm funcția și afișăm rezultatul
            rezultat = gaseste_valoarea_repetata(sir)
            print(f"Valoarea care se repetă este: {rezultat}")
        
        elif optiune == "3":
            print("Ieșire din program. La revedere!")
            break
        
        else:
            print("Opțiune invalidă! Te rog să alegi 1, 2 sau 3.")

        assert find_duplicate([1, 2, 3, 4, 4, 5]) == 4
        assert find_duplicate([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10]) == 10
        assert gaseste_valoarea_repetata([1, 2, 3, 4, 4, 5]) == 4
        assert gaseste_valoarea_repetata([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10]) == 10
# Pornim meniul
meniu()