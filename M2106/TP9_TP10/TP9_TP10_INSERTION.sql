-- Agence([numAgence](1),telAgence)
-- Agent([numAgent](1),nomAgent,prenomAgent,salaire, directeur,numAgence=@Agence.numAgence (NN))
-- Client([numClient](1),nomClient(NN),prenomClient(NN),adresseClient,dateNaissanceClient,numAgent=@Agent.numAgent(NN))
-- Appartient([numCompte=@Compte.numCompte,numClient=@Client.numClient](1))
-- Compte([numCompte](1),solde,libTypeCompte (NN))
-- Operation([numOperation](1),dateOperation,typeOperation,montant,client=@Client.numClient(NN), numCompte=@Compte.numCompte(NN))

DROP TABLE Operation;
DROP TABLE Appartient;
DROP TABLE Compte;
DROP TABLE Client;
DROP TABLE Agent;
DROP TABLE Agence;

CREATE TABLE Agence (
			numAgence NUMBER(4)
			CONSTRAINT pkAgence PRIMARY KEY,
			noTel VARCHAR2(20)
			);
			
CREATE TABLE Agent (
			numAgent NUMBER(4)
			CONSTRAINT pkAgent PRIMARY KEY,
			nom VARCHAR2(20),
			
			
			prenom VARCHAR2(20),
			
			salaire NUMBER(5)
			CONSTRAINT ckSalaire
			CHECK (salaire >= 1149),
			
			estDirecteur NUMBER(1),
			
			lAgence NUMBER(4)
			CONSTRAINT 	fkLAgence
			REFERENCES	Agence(numAgence)
			CONSTRAINT	nnLAgence
			NOT NULL
			);
			
CREATE TABLE Client (
			numClient NUMBER(4)
			CONSTRAINT pkClient PRIMARY KEY,
			nom VARCHAR2(20)
			CONSTRAINT nnNom NOT NULL,
			prenom VARCHAR2(20)
			CONSTRAINT nnPrenom NOT NULL,
			adresse VARCHAR2(40),
			dateNaissance DATE,
			lAgent NUMBER(4)
			CONSTRAINT fkLAgent
			REFERENCES Agent(numAgent)
			CONSTRAINT nnLAgent
			NOT NULL
			);
			
CREATE TABLE Compte (
			numCompte NUMBER(4)
			CONSTRAINT pkCompte PRIMARY KEY,
			
			solde NUMBER(10),
			
			libelle VARCHAR2(30)
			CONSTRAINT ckLibelle
			CHECK (UPPER(libelle) = 'EPARGNE' OR UPPER(libelle) = 'COURANT')
			);

CREATE TABLE Appartient (

			unCompte NUMBER(4)
			CONSTRAINT fkUnCompte
			REFERENCES Compte(numCompte),
			
			unClient NUMBER(4)
			CONSTRAINT fkUnClient
			REFERENCES Client(numClient),
			
			CONSTRAINT pkAppartient
			PRIMARY KEY (unClient, unCompte)
			);

CREATE TABLE Operation (
			numOperations NUMBER(4)
			CONSTRAINT pkOperations PRIMARY KEY,
			
			dateOpe DATE DEFAULT CURRENT_DATE,
			
			typeOp VARCHAR2(20)
			CONSTRAINT ckType
			CHECK (UPPER(typeOp) = 'RETRAIT' OR UPPER(typeOp) = 'DEPOT'),
			
			montant NUMBER(10)
			CONSTRAINT ckmontant
			CHECK (montant >= 0),
			
			leClient NUMBER(4)
			CONSTRAINT fkLeClient
			REFERENCES Client(numClient)
			CONSTRAINT nnLeClient
			NOT NULL,
			
			leCompte NUMBER(4)
			CONSTRAINT fkLeCompte
			REFERENCES Compte(numCompte)
			CONSTRAINT nnLeCompte
			NOT NULL
			);


-- ATTENTION :
-- utilisez DELETE avec l'ordre correct !!!!!
TRUNCATE TABLE Agence;
TRUNCATE TABLE Agent;
TRUNCATE TABLE Client;
TRUNCATE TABLE Appartient;
TRUNCATE TABLE Compte;
TRUNCATE TABLE Operation;

------------------------------------------------------------------------------------------------------------------------------------
--Insertion d'agences
------------------------------------------------------------------------------------------------------------------------------------
--
-- ATTENTION : le numero de tel EST UNE CHAINE
--
INSERT ALL
  INTO Agence VALUES (1, '06 00 00 00 01')
  INTO Agence VALUES (2, '06 00 00 00 02')
  INTO Agence VALUES (3, '06 00 00 00 03')
  INTO Agence VALUES (4, '06 00 00 00 04')
  SELECT * FROM DUAL;

------------------------------------------------------------------------------------------------------------------------------------
--Insertion d'agents
------------------------------------------------------------------------------------------------------------------------------------
INSERT ALL
  -- un agent de l'agence 1
  INTO Agent VALUES (1,'Guillemot','Clement',1500,0,1)
  -- le directeur de l'agence 1
  INTO Agent VALUES (2,'Lacroix','Benoit',3000,1,1)
  -- trois agents de l'agence 2 dont un directeur
  INTO Agent VALUES (3,'Jeaunot','Louis',4000,0,2)
  INTO Agent VALUES (4,'Baron','Patrice',1750,0,2)
  INTO Agent VALUES (5,'Le Marechal','Adeline',4500,1,2)
  -- le directeur de l'agence 4
  INTO Agent VALUES (6,'Le Breton','Matthieu',2500,1,4)
  -- agents de l'agence 3
  INTO Agent VALUES (7,'Marigot','Samuel',1600,0,3)
  INTO Agent VALUES (8,'Moliere','Nicolas',1600,0,3)
  INTO Agent VALUES (9,'Olivier','Aurore',1600,0,3)
  INTO Agent VALUES (10,'Pellerin','Louise',1600,0,3)
  INTO Agent VALUES (11,'Caro','Herve',1600,0,3)
  INTO Agent VALUES (12,'Boutin','Clementine',2605,1,3)
  SELECT * FROM DUAL;

------------------------------------------------------------------------------------------------------------------------------------
--Insertion de clients
------------------------------------------------------------------------------------------------------------------------------------
INSERT ALL
  -- 2 clients conseillés par l'agent 1
  INTO Client VALUES (1,'Villaume','Clement','1 rue Michel de Montaigne','01/01/1978',1)
  INTO Client VALUES (2,'Salun','prenomClient2','2 rue Michel de Montaigne','02/02/1990',1)
  -- un client conseillé par l'agent 2 (directeur)
  INTO Client VALUES (3,'Colin','prenomClient3','3 rue Michel de Montaigne','03/03/1978',2)
  -- 3 clients conseillés par l'agent 3
  INTO Client VALUES (4,'Le Gall','prenomClient4','4 rue Michel de Montaigne','04/04/1978',3)
  INTO Client VALUES (5,'Deschamp','prenomClient5','5 rue Michel de Montaigne','05/05/1978',3)
  INTO Client VALUES (6,'Cloarec','prenomClient6','6 rue Michel de Montaigne','06/06/1978',3)
  -- 3 clients conseillés par l'agent 6
  INTO Client VALUES (7,'Cadic','prenomClient7','7 rue Michel de Montaigne', SYSDATE-365*31,6)
  INTO Client VALUES (8,'Rocheteau','prenomClient8','8 rue Michel de Montaigne','08/08/1978',6)
  INTO Client VALUES (9,'Lenon','prenomClient9','9 rue Michel de Montaigne','09/09/1978',6)
  -- 4 clients conseillés par l'agent 12 (directeur)
  INTO Client VALUES (10,'Picard','prenomClient10','10 rue Michel de Montaigne','10/10/1978',12)
  INTO Client VALUES (11,'Le Roullec','CLEMENTINE','11 rue Michel de Montaigne','11/11/1978',12)
  INTO Client VALUES (12,'Le neveu','prenomClient12','12 rue Michel de Montaigne','12/12/1978',12)
  INTO Client VALUES (13,'Debost','prenomClient13','13 rue Michel de Montaigne','13/01/1978',12)
SELECT * FROM DUAL
;


------------------------------------------------------------------------------------------------------------------------------------
--Insertion dans Compte : Compte([numCompte](1),solde,numTypeCompte=@TypeCompte.numTypeCompte(NN))
------------------------------------------------------------------------------------------------------------------------------------
INSERT ALL
  INTO Compte VALUES (1,50,'COURANT')
  INTO Compte VALUES (2,13000,'EPARGNE')
  INTO Compte VALUES (3,1000,'COURANT')
  INTO Compte VALUES (4,500,'COURANT')
  INTO Compte VALUES (5,1000000,'EPARGNE')
  INTO Compte VALUES (6,1,'EPARGNE')
  INTO Compte VALUES (7,5000,'COURANT')
  INTO Compte VALUES (8,5005,'COURANT')
  INTO Compte VALUES (9,500,'COURANT')
  INTO Compte VALUES (10,14000,'EPARGNE')
  INTO Compte VALUES (11,12,'COURANT')
  INTO Compte VALUES (12,8500,'EPARGNE')
  INTO Compte VALUES (13,18500,'EPARGNE')
  INTO Compte VALUES (14,2548,'COURANT')
  INTO Compte VALUES (15,85000,'EPARGNE')
  SELECT * FROM DUAL;


------------------------------------------------------------------------------------------------------------------------------------
--Insertion dans Appartient : Appartient([numCompte=@Compte.numCompte,numClient=@Client.numClient](1))
------------------------------------------------------------------------------------------------------------------------------------
INSERT ALL
  INTO Appartient VALUES (1,1)
  INTO Appartient VALUES (1,2)
  INTO Appartient VALUES (2,3)
  INTO Appartient VALUES (3,4)
  INTO Appartient VALUES (4,5)
  INTO Appartient VALUES (5,6)
  INTO Appartient VALUES (6,7)
  INTO Appartient VALUES (7,8)
  INTO Appartient VALUES (8,9)
  INTO Appartient VALUES (9,10)
  INTO Appartient VALUES (10,11)
  INTO Appartient VALUES (11,12)
  INTO Appartient VALUES (12,13)
  INTO Appartient VALUES (13,1)
  INTO Appartient VALUES (14,2)
  INTO Appartient VALUES (15,3)
  SELECT * FROM DUAL;


--------------------------------------------------------------------------------------------------------------------------------------------------------------
--Insertion dans Operation : Operation([numOperation](1),dateOperation,typeOperation,montant,client=@Client.numClient(NN), numCompte=@Compte.numCompte(NN))
--------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT ALL
  INTO Operation VALUES (1,'10/10/2016','RETRAIT',10,11,9)
  INTO Operation VALUES (2,'10/10/2016','RETRAIT',500,13,7)
  INTO Operation VALUES (3,'11/10/2016','DEPOT',500,13,8)
  INTO Operation VALUES (4,'12/10/2016','DEPOT',200,13,8)
  INTO Operation VALUES (5,'13/11/2016','RETRAIT',100,13,8)
  INTO Operation VALUES (6,'13/11/2016','RETRAIT',400,13,8)
  INTO Operation VALUES (7,'23/12/2016','DEPOT',10000,13,8)
  INTO Operation VALUES (8,SYSDATE-1,'RETRAIT',100,13,8)
  INTO Operation VALUES (9,SYSDATE-2,'RETRAIT',400,13,8)
  INTO Operation VALUES (10,SYSDATE,'RETRAIT',500,10,7)
  INTO Operation VALUES (11,SYSDATE-4,'RETRAIT',600,10,7)
  INTO Operation VALUES (12,SYSDATE-4,'RETRAIT',700,10,7)
  INTO Operation VALUES (13,NULL,'DEPOT',750,10,7)
  SELECT * FROM DUAL
;

