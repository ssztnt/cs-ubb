import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Încarcă datele
df = pd.read_csv('data/surveyDataSience.csv', skiprows=1, low_memory=False)

# 1. Distribuția vârstelor pentru respondenții care programează în Python
def plot_age_distribution_python_programmers(df):
    # Filtrarea respondenților care programează în Python
    df_python = df[df['What programming languages do you use on a regular basis? (Select all that apply) - Selected Choice - Python'].str.contains('Python', na=False)]

    # Definirea categoriilor de vârstă
    categorii_varsta = ['18-21', '22-24', '25-29', '30-34', '35-39', '40-44', '45-49', '50-54', '55-59', '60-69', '70+']

    # Calcularea distribuției pe categorii de vârstă
    distributie_varsta = df_python['What is your age (# years)?'].value_counts().reindex(categorii_varsta, fill_value=0)

    # Vizualizarea distribuției
    plt.figure(figsize=(10, 6))
    distributie_varsta.plot(kind='bar', color='skyblue')
    plt.title('Distribuția vârstelor pentru respondenții care programează în Python')
    plt.xlabel('Interval vârstă')
    plt.ylabel('Număr de respondenți')
    plt.xticks(rotation=45)
    plt.show()

# 2. Distribuția vârstelor pentru respondenții din România care programează în Python
def plot_age_distribution_romania_python_programmers(df):
    # Filtrarea respondenților din România care programează în Python
    df_romania_python = df[(df['In which country do you currently reside?'] == 'Romania') &
                           (df['What programming languages do you use on a regular basis? (Select all that apply) - Selected Choice - Python'].str.contains('Python', na=False))]

    # Definirea categoriilor de vârstă
    categorii_varsta = ['18-21', '22-24', '25-29', '30-34', '35-39', '40-44', '45-49', '50-54', '55-59', '60-69', '70+']

    # Calcularea distribuției pe categorii de vârstă
    distributie_varsta = df_romania_python['What is your age (# years)?'].value_counts().reindex(categorii_varsta, fill_value=0)

    # Vizualizarea distribuției
    plt.figure(figsize=(10, 6))
    distributie_varsta.plot(kind='bar', color='skyblue')
    plt.title('Distribuția vârstelor pentru respondenții din România care programează în Python')
    plt.xlabel('Interval vârstă')
    plt.ylabel('Număr de respondenți')
    plt.xticks(rotation=45)
    plt.show()

# 3. Distribuția vârstelor pentru respondentele femei din România care programează în Python
def plot_age_distribution_romania_women_python_programmers(df):
    # Filtrarea respondenților femei din România care programează în Python
    df_femei_romania_python = df[(df['In which country do you currently reside?'] == 'Romania') &
                                (df['What is your gender? - Selected Choice'] == 'Woman') &
                                (df['What programming languages do you use on a regular basis? (Select all that apply) - Selected Choice - Python'].str.contains('Python', na=False))]

    # Definirea categoriilor de vârstă
    categorii_varsta = ['18-21', '22-24', '25-29', '30-34', '35-39', '40-44', '45-49', '50-54', '55-59', '60-69', '70+']

    # Calcularea distribuției pe categorii de vârstă
    distributie_varsta = df_femei_romania_python['What is your age (# years)?'].value_counts().reindex(categorii_varsta, fill_value=0)

    # Vizualizarea distribuției
    plt.figure(figsize=(10, 6))
    distributie_varsta.plot(kind='bar', color='lightcoral')
    plt.title('Distribuția vârstelor pentru respondentele femei din România care programează în Python')
    plt.xlabel('Interval vârstă')
    plt.ylabel('Număr de respondente')
    plt.xticks(rotation=45)
    plt.show()

# 4. Identificarea outlierilor în vechimea în programare
def plot_outliers_in_programming_experience(df):
    # Definirea intervalelor de vechime
    def vechime_in_ani(valoare):
        if '5-10 years' in valoare:
            return (5 + 10) / 2
        elif '20+ years' in valoare:
            return 20  # presupunem valoarea minimă de 20
        elif '1-3 years' in valoare:
            return (1 + 3) / 2
        elif '< 1 years' in valoare:
            return 0.5  # considerăm 0.5 ani
        elif '3-5 years' in valoare:
            return (3 + 5) / 2
        elif '10-20 years' in valoare:
            return (10 + 20) / 2
        elif 'I have never written code' in valoare:
            return 0  # presupunem că acești respondenți nu au vechime
        return None

    # Aplicarea funcției pe coloana 'vechime'
    df['vechime_ani'] = df['For how many years have you been writing code and/or programming?'].apply(vechime_in_ani)

    # Crearea unui boxplot pentru a identifica outlierii
    plt.figure(figsize=(10, 6))
    sns.boxplot(data=df, x='vechime_ani', color='lightgreen')
    plt.title('Boxplot pentru vechimea în programare')
    plt.xlabel('Vechime în ani')
    plt.show()

    # Calcularea valorilor pentru outlieri
    Q1 = df['vechime_ani'].quantile(0.25)
    Q3 = df['vechime_ani'].quantile(0.75)
    IQR = Q3 - Q1

    limita_inferioara = Q1 - 1.5 * IQR
    limita_superioara = Q3 + 1.5 * IQR

    # Filtrarea outlierilor
    outlieri = df[(df['vechime_ani'] < limita_inferioara) | (df['vechime_ani'] > limita_superioara)]
    print("Outlieri:\n", outlieri)

# Exemplu de utilizare a funcțiilor
if __name__ == "__main__":
    # 1.
    plot_age_distribution_python_programmers(df)

    # 2.
    plot_age_distribution_romania_python_programmers(df)

    # 3.
    plot_age_distribution_romania_women_python_programmers(df)

    # 4.
    plot_outliers_in_programming_experience(df)