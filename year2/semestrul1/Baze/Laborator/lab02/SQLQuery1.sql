USE InstrumenteMuzicale2
GO

INSERT INTO Furnizori (Nume, Locatie) 
VALUES 
    ('Pian', 'Germania'),
    ('Chitară', 'Statele Unite'),
    ('Vioară', 'Italia'),
    ('Tobe', 'Japonia'),
    ('Saxofon', 'Franța'),
    ('Flaut', 'India'),
    ('Trompetă', 'Statele Unite');

INSERT INTO Categorie (Nume) 
VALUES 
    ('Coarde'),
    ('Percuție'),
    ('Suflători'),
    ('Claviaturi'),
    ('Electronice');

INSERT INTO Produse (Nume, Pret, Stoc, idFurnizor, idCategorie)
VALUES
    -- Yamaha (1)
    ('Pian Digital Yamaha P-125', 3000, 5, 1, 4),
    ('Chitară Acustică Yamaha F310', 600, 8, 1, 1),
    ('Trompetă Yamaha YTR-2330', 1800, 3, 1, 3),
    
    -- Fender (2)
    ('Chitară Electrică Fender Stratocaster', 4000, 2, 2, 1),
    ('Amplificator Fender Mustang', 1500, 4, 2, 5),
    
    -- Gibson (3)
    ('Chitară Electrică Gibson Les Paul', 5000, 1, 3, 1),
    ('Chitară Acustică Gibson G-45', 3200, 3, 3, 1),
    
    -- Roland (4)
    ('Clapă Roland FP-30X', 2800, 6, 4, 4),
    ('Tobe Electronice Roland TD-1DMK', 3600, 2, 4, 2),
    ('Sintetizator Roland JUNO-DS61', 3800, 3, 4, 4),
    
    -- Kawai (5)
    ('Pian Digital Kawai ES110', 3300, 4, 5, 4),
    ('Pian Acustic Kawai K-300', 10000, 1, 5, 4),
    
    -- Steinway & Sons (6)
    ('Pian Acustic Steinway Model D', 50000, 1, 6, 4),
    ('Pian Acustic Steinway Model B', 42000, 1, 6, 4),
    
    -- Casio (7)
    ('Clapă Casio CT-X700', 800, 7, 7, 4),
    ('Clapă Casio SA-77', 200, 10, 7, 4);


INSERT INTO Depozite (Localitate, Nume) 
VALUES 
    ('Timisoara', 'depozit vest'),
    ('Constanta', 'depozit litoral'),
    ('Iasi', 'depozit nord-est');


INSERT INTO Manageri (idManager, Nume, Experienta) 
VALUES 
    (4, 'Kwame Bantu', 12),
    (5, 'Amina Jaha', 10),
    (6, 'Tunde Okoro', 7);

INSERT INTO Departamente (Nume, nrAngajati) 
VALUES 
    ('Vânzări Instrumente', 15),
    ('Service și Reparații', 8),
    ('Stoc și Depozitare', 12),
    ('Promovare Muzicală', 5);

INSERT INTO ProduseDepozite(idProdus, idDepozit)
VALUES (1,1), (5, 1), (6, 1), (2, 2), (3, 2), (4,2), (1,3)



INSERT INTO Angajati(Nume, Prenume, Departament, Depozit, Vechime)
VALUES ('Costi', 'Cornea', 1, 1, 3),
	   ('Virgil', 'Cinca', 1, 1, 4),
	   ('Radu', 'Gogean', 1, 1, 5),
	   ('Cristina', 'Donceanu', 2, 1, 7),
	   ('Carmen', 'Tugurlan', 2, 1, 8),
	   ('Rodica', 'Arcos', 3, 1, 4),
	   ('Augustina', 'Tomescu', 3, 1, 6),
	   ('Andrei', 'Toma', 3, 1, 9),
	   ('Emil', 'Vladu', 4, 1, 3),
	   ('Nicolae', 'Mihai', 4, 1, 6),
	   ('Laur', 'Mihnea', 4, 1, 9),

	   ('Costica', 'Pacurar', 1, 2, 3),
	   ('Marc', 'Moisuc', 1, 2, 4),
	   ('Tudor', 'Plesu', 1, 2, 5),
	   ('Horea', 'Neagoe', 2, 2, 7),
	   ('Doru', 'Popa', 2, 2, 8),
	   ('Dorian', 'Popa', 3, 2, 4),
	   ('Dorina', 'Mihaili', 3, 2, 6),
	   ('Ana', 'Pavel', 3, 2, 9),
	   ('Cici', 'Galca', 4, 2, 3),
	   ('Silvia', 'Cojocar', 4, 2, 6),
	   ('Victoria', 'Voicu', 4, 2, 9),

	   ('Viorel', 'Stefan', 1, 3, 3),
	   ('Ionus', 'Theo', 1, 3, 4),
	   ('Nandru', 'Oprea', 1, 3, 5),
	   ('Toma', 'Oprea', 2, 3, 7),
	   ('Bogdan', 'Banciu', 2, 3, 8),
	   ('Mircea', 'Bravo', 3, 3, 4),
	   ('Gabriela', 'Tomescu', 3, 3, 6),
	   ('Gabi', 'Poenaru', 3, 3, 9),
	   ('Eminovici', 'Mihai', 4, 3, 3),
	   ('Marina', 'Cozma', 4, 3, 6),
	   ('Costela', 'Diaconu', 4, 3, 9)

INSERT INTO Clienti (Nume) 
VALUES 
    ('Gheorghe Hagi'),
    ('Cristiano Popescu'),
    ('Lionel Munteanu'),
    ('Kylian Ionescu'),
    ('David Radu');

INSERT INTO Comenzi(idClient, idAngajatRaspunzator)
VALUES (1,1), (2, 1), (3, 12), (4, 13), (5, 14)

