def ultimul_cuvant_lexical(sir):
    cuvinte = sir.split() # split() returneaza o lista de cuvinte
    cuvinte.sort() # sort() sorteaza lista de cuvinte 
    return cuvinte[-1] # returneaza ultimul cuvant din lista sortata



#ai
def ultimul_cuvant_alfabetic(text):
    # Împarte textul în cuvinte
    cuvinte = text.split()
    
    # Inițializează variabila pentru cel mai mare cuvânt
    ultimul_cuvant = cuvinte[0]
    
    # Parcurge fiecare cuvânt și găsește cel mai mare (alfabetic)
    for cuvant in cuvinte:
        if cuvant > ultimul_cuvant:
            ultimul_cuvant = cuvant
    
    return ultimul_cuvant

def meniu():
    print("Alegeți o opțiune:")
    print("1. Găsește ultimul cuvânt lexical")
    print("2. Găsește ultimul cuvânt alfabetic")
    optiune = input("Introduceți opțiunea (1 sau 2): ")
    return optiune

optiune = meniu()
if optiune == '1':
    text = input("Introduceți textul: ")
    print(f"Ultimul cuvânt lexical este: {ultimul_cuvant_lexical(text)}")
elif optiune == '2':
    text = input("Introduceți textul: ")
    print(f"Ultimul cuvânt (alfabetic) este: {ultimul_cuvant_alfabetic(text)}")
else:
    print("Opțiune invalidă")


#complexitate prima : O(n log n)
#complexitate a doua : O(n) 