--creeare views---
CREATE VIEW VW_Produse AS
SELECT 
    idProdus, Nume, Pret, Stoc
FROM 
    Produse; --tabel
GO

CREATE VIEW VW_ProduseFurnizori AS
SELECT 
    p.idProdus, p.Nume AS NumeProdus, p.Pret, p.Stoc, f.Nume AS NumeFurnizor
FROM 
    Produse p
JOIN 
    Furnizori f ON p.idFurnizor = f.idFurnizor; --2tabele
GO

CREATE VIEW VW_ProdusePeCategorie AS
SELECT 
    c.Nume AS NumeCategorie, COUNT(p.idProdus) AS NumarProduse, AVG(p.Pret) AS PretMediu
FROM 
    Produse p
JOIN 
    Categorie c ON p.idCategorie = c.idCategorie
GROUP BY     -- 2tabele + group by 
    c.Nume;
GO