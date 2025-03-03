import math

#Complexitatea timp: O(1)
#Complexitatea memorie: O(1)

def distanta_euclidiana(punct1, punct2):
    # Extrage coordonatele
    x1, y1 = punct1
    x2, y2 = punct2
    
    # Calculează diferențele
    dx = x2 - x1
    dy = y2 - y1
    
    # Calculează distanța Euclideană
    distanta = math.sqrt(dx**2 + dy**2)
    
    return distanta

#Complexitatea timp: O(1)
#Complexitatea memorie: O(1)
def distanta_euclidiana_optimizata(punct1, punct2):
    # Calculează distanța Euclideană
    distanta = math.sqrt((punct2[0] - punct1[0])**2 + (punct2[1] - punct1[1])**2)
    
    return distanta

def meniu():
    print("Alegeți o opțiune:")
    print("1. Calculează distanța Euclideană")
    print("2. Calculează distanța Euclideană (optimizat)")
    optiune = input("Introduceți opțiunea (1 sau 2): ")
    return optiune

optiune = meniu()

if optiune == '1':
    punct1 = tuple(map(float, input("Introduceți coordonatele primului punct (x, y): ").split()))
    punct2 = tuple(map(float, input("Introduceți coordonatele celui de-al doilea punct (x, y): ").split()))
    print(f"Distanța Euclideană este: {distanta_euclidiana(punct1, punct2)}")

elif optiune == '2':
    punct1 = tuple(map(float, input("Introduceți coordonatele primului punct (x, y): ").split()))
    punct2 = tuple(map(float, input("Introduceți coordonatele celui de-al doilea punct (x, y): ").split()))
    print(f"Distanța Euclideană (optimizat) este: {distanta_euclidiana_optimizata(punct1, punct2)}")

    assert distanta_euclidiana((0, 0), (3, 4)) == 5.0
    assert distanta_euclidiana((0, 0), (1, 1)) == math.sqrt(2)
    assert distanta_euclidiana_optimizata((0, 0), (3, 4)) == 5.0
    assert distanta_euclidiana_optimizata((0, 0), (1, 1)) == math.sqrt(2)

meniu()