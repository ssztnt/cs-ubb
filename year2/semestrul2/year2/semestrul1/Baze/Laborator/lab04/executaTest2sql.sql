use InstrumenteMuzicale2
go
CREATE  OR ALTER PROCEDURE ExecutaTest @NumeTest VARCHAR(50)
AS
BEGIN
	IF (NOT EXISTS(SELECT TestID FROM Tests WHERE Name = @NumeTest))
		RAISERROR('Testul nu exista', 16, 1);

	DECLARE @TestID INT;
	SET @TestID = (SELECT TOP 1 TestID FROM Tests WHERE Name = @NumeTest ORDER BY TestID);

	DECLARE CursorTestTables_Asc CURSOR FAST_FORWARD FOR
		SELECT TableID, Position, NoOfRows FROM TestTables WHERE TestID = @TestID ORDER BY Position ASC;
	OPEN CursorTestTables_Asc;

	DECLARE @TableID INT, @Pos INT, @NoOfRows INT;
	DECLARE @TableName VARCHAR(50), @InsertProcedure VARCHAR(50);

	-- Creare TestRun
	DECLARE @TestRunID INT;
	INSERT INTO TestRuns (Description) VALUES ('Executia testului ' + @NumeTest);
	SET @TestRunID = @@IDENTITY;

	-- Declarare cronometre
	DECLARE @StartTime DATETIME, @EndTime DATETIME; -- Start si final total
	DECLARE @CurrentStartTime DATETIME, @CurrentEndTime DATETIME; -- Start si final pentru fiecare test in parte

	-- Stergerea datelor din tabele
	FETCH NEXT FROM CursorTestTables_Asc INTO @TableID, @Pos, @NoOfRows;
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET @TableName = (SELECT Name FROM Tables WHERE TableID = @TableID);
		EXEC('DELETE FROM ' + @TableName);

		FETCH NEXT FROM CursorTestTables_Asc INTO @TableID, @Pos, @NoOfRows;
	END;
	CLOSE CursorTestTables_Asc;
	DEALLOCATE CursorTestTables_Asc;

	DECLARE CursorTestTables_Desc CURSOR FAST_FORWARD FOR
		SELECT TableID, Position, NoOfRows FROM TestTables WHERE TestID = @TestID ORDER BY Position DESC;
	OPEN CursorTestTables_Desc;

	SET @StartTime = GETDATE();

	-- Inserarea datelor in tabele
	FETCH NEXT FROM CursorTestTables_Desc INTO @TableID, @Pos, @NoOfRows;
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET @TableName = (SELECT Name FROM Tables WHERE TableID = @TableID);
		SET @InsertProcedure = @TableName + '_Inserari';

		SET @CurrentStartTime = GETDATE();
		EXEC @InsertProcedure @NoOfRows;
		SET @CurrentEndTime = GETDATE();

		INSERT INTO TestRunTables (TestRunID, TableID, StartAt, EndAt)
		VALUES (@TestRunID, @TableID, @CurrentStartTime, @CurrentEndTime);

		FETCH NEXT FROM CursorTestTables_Desc INTO @TableID, @Pos, @NoOfRows;
	END;

	CLOSE CursorTestTables_Desc;
	DEALLOCATE CursorTestTables_Desc;

	-- Testare view-uri
	DECLARE CursorTestViews CURSOR FAST_FORWARD FOR
		SELECT ViewID FROM TestViews WHERE TestID = @TestID;
	OPEN CursorTestViews;

	DECLARE @ViewID INT, @ViewName VARCHAR(50);

	FETCH NEXT FROM CursorTestViews INTO @ViewID;
	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET @ViewName = (SELECT Name FROM Views WHERE ViewID = @ViewID);

		SET @CurrentStartTime = GETDATE();
		EXEC('SELECT * FROM ' + @ViewName);
		SET @CurrentEndTime = GETDATE();

		INSERT INTO TestRunViews (TestRunID, ViewID, StartAt, EndAt)
		VALUES (@TestRunID, @ViewID, @CurrentStartTime, @CurrentEndTime);

		FETCH NEXT FROM CursorTestViews INTO @ViewID;
	END;

	SET @EndTime = GETDATE();

	-- Setez timpul pentru intreg testul
	UPDATE TestRuns SET StartAt = @StartTime, EndAt = @EndTime WHERE TestRunID = @TestRunID;

	CLOSE CursorTestViews;
	DEALLOCATE CursorTestViews;
END;
GO

CREATE PROCEDURE TestProduseDepozitate_Inserari @NoOfRows INT
AS
BEGIN
    DECLARE @Num INT, @NumStr VARCHAR(10);
    SET @Num = 1; 

    WHILE @Num <= @NoOfRows
    BEGIN
        -- Generarea datelor pentru inserare
        SET @NumStr = CAST(@Num AS VARCHAR(10));

        -- Inserare în tabel
        INSERT INTO TestProduseDepozitate (ProdusID, DepozitID, Cantitate)
        VALUES 
        (
            (SELECT TOP 1 idProdus FROM Produse ORDER BY NEWID()), -- Aleator produs existent
            (SELECT TOP 1 idDepozit FROM Depozite ORDER BY NEWID()), -- Aleator depozit existent
            10 + (@Num % 20) -- Cantitate între 10 și 29
        );

        -- Incrementare contor
        SET @Num += 1;
    END;
END;
GO

CREATE PROCEDURE TestAngajati_Inserari @NoOfRows INT
AS
BEGIN
    DECLARE @Num INT = 1;

    WHILE @Num <= @NoOfRows
    BEGIN
        INSERT INTO TestAngajati (Nume, Prenume, DepartamentID, DataAngajare)
        VALUES 
        ('Nume' + CAST(@Num AS VARCHAR), 
         'Prenume' + CAST(@Num AS VARCHAR), 
         (SELECT TOP 1 idDepartament FROM Departamente ORDER BY NEWID()), 
         DATEADD(DAY, -@Num, GETDATE()));

        SET @Num += 1;
    END;
END;
GO


CREATE PROCEDURE TestComenzi_Inserari @NoOfRows INT
AS
BEGIN
    DECLARE @Num INT = 1;

    WHILE @Num <= @NoOfRows
    BEGIN
        INSERT INTO TestComenzi (ComandaID, ClientID, DataComanda)
        VALUES 
        (@Num, 
         (SELECT TOP 1 idClient FROM Clienti ORDER BY NEWID()), 
         DATEADD(DAY, -@Num, GETDATE()));

        SET @Num += 1;
    END;
END;
GO
