use InstrumenteMuzicale2
GO

CREATE TABLE TestAngajati (
    AngajatID INT PRIMARY KEY IDENTITY,
    Nume VARCHAR(50) NOT NULL,
    Prenume VARCHAR(50) NOT NULL,
    DepartamentID INT NOT NULL FOREIGN KEY REFERENCES Departamente(idDepartament),
    DataAngajare DATE NOT NULL
);

CREATE TABLE TestProduseDepozitate (
    ProdusID INT NOT NULL FOREIGN KEY REFERENCES Produse(idProdus),
    DepozitID INT NOT NULL FOREIGN KEY REFERENCES Depozite(idDepozit),
    Cantitate INT CHECK (Cantitate > 0),
    PRIMARY KEY (ProdusID, DepozitID)
);

CREATE TABLE TestComenzi (
    ComandaID INT NOT NULL,
    ClientID INT NOT NULL FOREIGN KEY REFERENCES Clienti(idClient),
    DataComanda DATE NOT NULL,
    PRIMARY KEY (ComandaID, ClientID)
);