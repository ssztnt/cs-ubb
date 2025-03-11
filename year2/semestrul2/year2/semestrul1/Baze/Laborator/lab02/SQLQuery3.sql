USE InstrumenteMuzicale2
GO

-- Produsele împreună cu furnizorii ce fac parte din categoria 'Coarde'
SELECT p.Nume, p.Pret, p.Stoc, f.Nume AS Furnizor, f.Locatie
FROM Furnizori f 
INNER JOIN Produse p ON f.idFurnizor = p.idFurnizor
WHERE p.idCategorie IN (SELECT c.idCategorie FROM Categorie c WHERE c.Nume = 'Coarde');


-- Furnizorii și numărul produselor din categoria 'Coarde'
SELECT f.Nume, f.Locatie, COUNT(*) AS Numar_Produse_Coarde
FROM Furnizori f 
INNER JOIN Produse p ON f.idFurnizor = p.idFurnizor
WHERE p.idCategorie IN (SELECT c.idCategorie FROM Categorie c WHERE c.Nume = 'Coarde')
GROUP BY f.Nume, f.Locatie;

-- 3) Departamentele de angajati cu mai mult de 5 persoane
SELECT d.Nume, Count(*) AS Numar_Angajati
FROM Departamente d
INNER JOIN Angajati a
ON d.idDepartament = a.Departament
GROUP BY d.Nume HAVING COUNT(*)>20


-- 4) Depozitele ce au mai mult de 5 angajati cu vechime >= de 5 ani
SELECT d.Nume, d.Localitate, COUNT(*) AS Nr_Angajati
FROM Angajati a
INNER JOIN Departamente dep
ON a.Departament = dep.idDepartament
INNER JOIN Depozite d
ON a.Depozit = d.idDepozit
WHERE a.Vechime >= 5
GROUP BY d.Nume, d.Localitate HAVING COUNT(*) > 5 


-- 5) Departamentele si numarul de angajati din Depozitul 'Iasi'
SELECT d.Nume, COUNT(a.Nume) as Nr_Angajati 
FROM Departamente d
RIGHT OUTER JOIN Angajati a
ON d.idDepartament = a.Departament
RIGHT OUTER JOIN Depozite dep
ON a.Depozit = dep.idDepozit
WHERE dep.Localitate = 'Iasi'
GROUP BY d.Nume



-- 6) Clientii ce au comanda pregatita de angajati cu vechime de peste 3 ani
SELECT c.Nume
FROM Clienti c
RIGHT OUTER JOIN Comenzi com
ON c.idClient = com.idClient
INNER JOIN Angajati a
ON com.idAngajatRaspunzator = a.idAngajat
WHERE a.Vechime > 3

-- 7) Toate departamentele din fiecare depozit
SELECT DISTINCT dep.Nume AS Nume_Depozit, dep.Localitate, d.Nume AS Nume_Departament
FROM Departamente d
RIGHT OUTER JOIN Angajati a
ON d.idDepartament = a.Departament
RIGHT OUTER JOIN Depozite dep
ON a.Depozit = dep.idDepozit

--8) Numele clientilor	, numele produselor si numele angajatului ce a gestionat comanda 
SELECT c.Nume AS Nume_Client, p.Nume AS Nume_Produs, a.Nume AS Nume_Angajat
FROM Clienti c
INNER JOIN Comenzi com ON c.idClient = com.idClient
INNER JOIN Angajati a ON com.idAngajatRaspunzator = a.idAngajat
INNER JOIN Produse p ON com.idClient = p.idProdus; -- Ajustează pentru a lega produsele corecte

--9) Departamente si angajatii din depozite 

SELECT d.Nume AS Nume_Departament, COUNT(a.idAngajat) AS Nr_Angajati, dep.Nume AS Nume_Depozit
FROM Departamente d
INNER JOIN Angajati a ON d.idDepartament = a.Departament
INNER JOIN Depozite dep ON a.Depozit = dep.idDepozit
GROUP BY d.Nume, dep.Nume;

--10) Producatorii unici de produse muzicale in depozit 

SELECT DISTINCT f.Nume AS Nume_Furnizor, d.Nume AS Nume_Depozit, d.Localitate
FROM Furnizori f
INNER JOIN Produse p ON f.idFurnizor = p.idFurnizor
INNER JOIN ProduseDepozite pd ON p.idProdus = pd.idProdus
INNER JOIN Depozite d ON pd.idDepozit = d.idDepozit;


