USE InstrumenteMuzicale2
GO

-- Actualizează numărul de rânduri pentru tabelele asociate testului
UPDATE TestTables
SET NoOfRows = 100
WHERE TestID = (SELECT TestID FROM Tests WHERE Name = 'Main');

-- Execută testul "Main"
EXEC ExecutaTest @NumeTest = 'Main';

-- Verifică rezultatele testului în tabelele relevante
SELECT * FROM TestRuns;
SELECT * FROM TestRunTables;
SELECT * FROM TestRunViews;

-- Vizualizează datele generate în tabelele de test
SELECT * FROM TestAngajati;
SELECT * FROM TestProduseDepozitate;
SELECT * FROM TestComenzi;

SELECT Name, COUNT(*) AS NrRânduri
FROM Tests
GROUP BY Name
HAVING COUNT(*) > 1;


DELETE FROM TestRuns
DELETE FROM TestRunTables
DELETE FROM TestRunViews