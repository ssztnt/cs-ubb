 
 CREATE DATABASE FabricaInstrumenteMuzicale 
 go
 
 use FabricaInstrumenteMuzicale
 go

 CREATE TABLE Depozite 
( 
	idDepozit INT PRIMARY KEY IDENTITY,
	Localitate VARCHAR(50),
	Nume VARCHAR(50),
)

