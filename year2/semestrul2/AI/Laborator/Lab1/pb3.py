def produs_scalar(vector , vector2):
    suma = 0
    for i in range(len(vector)):
        suma += vector[i] * vector2[i]
    return suma

def produs_scalar_optimizat(vector1, vector2):
    # Reprezentăm vectorii rari ca dicționare: {index: valoare}
    sparse1 = {i: val for i, val in enumerate(vector1) if val != 0}
    sparse2 = {i: val for i, val in enumerate(vector2) if val != 0}
    
    # Calculăm produsul scalar folosind doar elementele nenule comune
    produs = 0
    for i in sparse1:
        if i in sparse2:
            produs += sparse1[i] * sparse2[i]
    
    return produs

def meniu():
    print("Alegeți o opțiune:")
    print("1. Calculează produsul scalar")
    print("2. Calculează produsul scalar (optimizat)")
    optiune = input("Introduceți opțiunea (1 sau 2): ")
    return optiune

optiune = meniu()

if optiune == '1':
    vector1 = list(map(int, input("Introduceți primul vector: ").split()))
    vector2 = list(map(int, input("Introduceți al doilea vector: ").split()))
    print(f"Produsul scalar este: {produs_scalar(vector1, vector2)}")

elif optiune == '2':
    vector1 = list(map(int, input("Introduceți primul vector: ").split()))
    vector2 = list(map(int, input("Introduceți al doilea vector: ").split()))
    print(f"Produsul scalar (optimizat) este: {produs_scalar_optimizat(vector1, vector2)}")
else:
    print("Opțiune invalidă")
#complexitate prima : O(n)
#complexitate a doua : O(n)