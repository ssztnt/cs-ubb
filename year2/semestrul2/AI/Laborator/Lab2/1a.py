import pandas as pd

# Funcție pentru încărcarea datelor
def load_data(file_path):
    df = pd.read_csv(file_path, skiprows=1, low_memory=False)
    return df

# 1. Funcție pentru afișarea numărului de respondenți
def numar_respondenti(df):
    numar = df.shape[0]
    print(f"Numarul de respondenți: {numar} \n")
    return numar

# 2. Funcție pentru afișarea numărului de atribute și tipurile lor
def atribute_si_tipuri(df):
    numar_atribute = df.shape[1]
    print(f"Numarul de atribute per respondent: {numar_atribute}")

    tipuri_atribute = df.dtypes
    print("\nTipurile de atribute:\n", tipuri_atribute, "\n")
    return numar_atribute, tipuri_atribute

# 3. Funcție pentru afișarea numărului de respondenți fără valori lipsă
def respondenti_completi(df):
    numar_respondenti_completi = df.dropna().shape[0]
    print(f"Numarul de respondenți cu date complete: {numar_respondenti_completi} \n")
    return numar_respondenti_completi

# 4. Funcție pentru calculul duratei medii a studiilor
def durata_medie_studii(df):
    durata_studii = {
        "Bachelor’s degree": 3,
        "Master’s degree": 5,  # 3 ani de licenta + 2 de master
        "Doctoral degree": 8,  # 3 ani de licenta + 2 de master + 3 de doctorat
        "Professional doctorate": 8
    }

    df["Ani_Studii"] = df["What is the highest level of formal education that you have attained or plan to attain within the next 2 years?"].map(durata_studii)

    durata_medie_totala = df["Ani_Studii"].mean()
    durata_medie_romania = df[df["In which country do you currently reside?"] == "Romania"]["Ani_Studii"].mean()
    durata_medie_romania_femei = df[(df["In which country do you currently reside?"] == "Romania") & (df["What is your gender? - Selected Choice"] == "Woman")]["Ani_Studii"].mean()

    print(f"Durata medie a studiilor pentru toti respondentii: {durata_medie_totala:.2f} ani")
    print(f"Durata medie a studiilor pentru respondentii din Romania: {durata_medie_romania:.2f} ani")
    print(f"Durata medie a studiilor pentru femeile din Romania: {durata_medie_romania_femei:.2f} ani")

    if durata_medie_romania > durata_medie_totala:
        print("Respondentii din Romania au o durata medie a studiilor mai mare decat media generala.")
    else:
        print("Respondentii din Romania au o durata medie a studiilor mai mica sau egala cu media generala.")

    if durata_medie_romania_femei > durata_medie_romania:
        print("Femeile din Romania au o durata medie a studiilor mai mare decat media respondentilor din Romania. \n")
    else:
        print("Femeile din Romania au o durata medie a studiilor mai mica sau egala cu media respondentilor din Romania. \n")

    return durata_medie_totala, durata_medie_romania, durata_medie_romania_femei

# 5. Funcție pentru afișarea numărului de femei din România cu date complete
def femei_romania_complete(df):
    respondente_romania = df[(df["In which country do you currently reside?"] == "Romania") &
                             (df["What is your gender? - Selected Choice"] == "Woman")]
    respondente_romania_complete = respondente_romania.dropna()
    numar_femei_romania_complete = respondente_romania_complete.shape[0]
    print(f"Numarul de respondente femei din Romania cu date complete: {numar_femei_romania_complete} \n")
    return numar_femei_romania_complete

# 6. Funcție pentru analiza femeilor din România care programează în Python și C++
def femei_programatoare(df):
    femei_python = df[(df["In which country do you currently reside?"] == "Romania") &
                      (df["What is your gender? - Selected Choice"] == "Woman") &
                      (df["What programming languages do you use on a regular basis? (Select all that apply) - Selected Choice - Python"] == "Python")]
    femei_cpp = df[(df["In which country do you currently reside?"] == "Romania") &
                   (df["What is your gender? - Selected Choice"] == "Woman") &
                   (df["What programming languages do you use on a regular basis? (Select all that apply) - Selected Choice - C++"] == "C++")]

    numar_femei_python = femei_python.shape[0]
    numar_femei_cpp = femei_cpp.shape[0]

    print(f"Numarul de femei din Romania care programeaza in Python: {numar_femei_python}")
    print(f"Numarul de femei din Romania care programeaza in C++: {numar_femei_cpp}")

    interval_femei_python = femei_python['What is your age (# years)?'].value_counts().idxmax()
    interval_femei_cpp = femei_cpp['What is your age (# years)?'].value_counts().idxmax()

    print(f"Intervalul de varsta cu cele mai multe femei care programeaza in Python: {interval_femei_python}")
    print(f"Intervalul de varsta cu cele mai multe femei care programeaza in C++: {interval_femei_cpp}")

    if numar_femei_python > numar_femei_cpp:
        print("Mai multe femei din Romania programeaza in Python decat in C++.\n")
    else:
        print("Mai multe femei din Romania programeaza in C++ decat in Python.\n")

    return numar_femei_python, numar_femei_cpp, interval_femei_python, interval_femei_cpp

# 7. Funcție pentru analiza tipurilor de date și a domeniilor de valori
def analiza_atribute(df):
    print("Tipurile de date pentru fiecare coloana:")
    print(df.dtypes)

    print("\nDomeniul de valori și valorile extreme pentru fiecare atribut numeric:")
    numeric_columns = df.select_dtypes(include=["float64", "int64"]).columns

    for col in numeric_columns:
        min_value = df[col].min()
        max_value = df[col].max()
        print(f"{col}: Min = {min_value}, Max = {max_value}")

    print("\nNumărul de valori distincte pentru fiecare atribut nenumeric:")
    categorical_columns = df.select_dtypes(include=["object"]).columns

    for col in categorical_columns:
        unique_values = df[col].nunique()
        print(f"{col}: {unique_values} valori distincte")
        print(f"Valori distincte: {df[col].unique()[:5]}...")

# 8. Funcție pentru analiza vechimii în programare
def analiza_vechime(df):
    intervale_vechime = {
        '5-10 years': 7.5,
        '20+ years': 20,
        '1-3 years': 2,
        '< 1 years': 0.5,
        '3-5 years': 4,
        '10-20 years': 15,
        'I have never written code': 0
    }

    df['vechime_programare_ani'] = df['For how many years have you been writing code and/or programming?'].map(intervale_vechime)

    min_vechime = df['vechime_programare_ani'].min()
    max_vechime = df['vechime_programare_ani'].max()
    mean_vechime = df['vechime_programare_ani'].mean()
    std_vechime = df['vechime_programare_ani'].std()
    median_vechime = df['vechime_programare_ani'].median()

    print(f"Minimul: {min_vechime}")
    print(f"Maximul: {max_vechime}")
    print(f"Media: {mean_vechime}")
    print(f"Deviația standard: {std_vechime}")
    print(f"Mediana: {median_vechime}")

    return min_vechime, max_vechime, mean_vechime, std_vechime, median_vechime

# Funcția principală care rulează toate funcțiile
def main():
    file_path = 'data/surveyDataSience.csv'
    df = load_data(file_path)

    # Rulează fiecare funcție
    numar_respondenti(df)
    atribute_si_tipuri(df)
    respondenti_completi(df)
    durata_medie_studii(df)
    femei_romania_complete(df)
    femei_programatoare(df)
    analiza_atribute(df)
    analiza_vechime(df)

# Rulează funcția principală
if __name__ == "__main__":
    main()