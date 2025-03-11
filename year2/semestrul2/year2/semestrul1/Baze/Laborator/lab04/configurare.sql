USE InstrumenteMuzicale2
GO

-- Tabelele pentru referință
INSERT INTO dbo.Tables (Name) VALUES
('TestAngajati'),            -- doar o cheie primară și o cheie străină
('TestProduseDepozitate'),   -- cheie primară compusă și chei străine
('TestComenzi');             -- cheie primară compusă

INSERT INTO Views (Name) VALUES
('VW_Produse'),              -- SELECT pe o tabelă
('VW_ProduseFurnizori'),     -- SELECT pe două tabele
('VW_ProdusePeCategorie');   -- SELECT pe două tabele + GROUP BY

INSERT INTO Tests (Name) VALUES ('Main');

-- Vizualizarea datelor introduse
SELECT * FROM Tables;
SELECT * FROM Views;

INSERT INTO TestViews (TestID, ViewID) VALUES
(1, 1),
(1, 2),
(1, 3)

INSERT INTO TestTables (TestID, TableID, Position, NoOfRows) VALUES
(1, 3, 1, 10),
(1, 2, 2, 10),
(1, 1, 3, 10)
