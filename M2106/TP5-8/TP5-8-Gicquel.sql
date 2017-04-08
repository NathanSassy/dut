---- SUPPRESSION DES TABLES EXISTANTES ----
DROP TABLE Operations;
DROP TABLE Appartient;
DROP TABLE Compte;
DROP TABLE Client;
DROP TABLE Agent;
DROP TABLE Agence;
-- Ne pas oublier de detruire les tables des TPs precedents ! --


---- CREATION DES TABLES ----

CREATE TABLE Agence (
			numAgence NUMBER(4)
			CONSTRAINT pkAgence PRIMARY KEY,
			noTel NUMBER(10),
			adresse VARCHAR2(40)
			);
			
CREATE TABLE Agent (
			numAgent NUMBER(4)
			CONSTRAINT pkAgent PRIMARY KEY,
			nom VARCHAR2(20),
			
			
			prenom VARCHAR2(20),
			
			estDirecteur NUMBER(1),
			
			salaire NUMBER(5)
			CONSTRAINT ckSalaire
			CHECK (salaire >= 1149),
			
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
			
			libelle VARCHAR2(30)
			CONSTRAINT ckLibelle
			CHECK (UPPER(libelle) = 'EPARGNE' OR UPPER(libelle) = 'COURANT'),
			
			solde NUMBER(10)
			);

CREATE TABLE Appartient (
			unClient NUMBER(4)
			CONSTRAINT fkUnClient
			REFERENCES Client(numClient),
			
			unCompte NUMBER(4)
			CONSTRAINT fkUnCompte
			REFERENCES Compte(numCompte),
			
			CONSTRAINT pkAppartient
			PRIMARY KEY (unClient, unCompte)
			);

CREATE TABLE Operations (
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


-----------------------------------------------------------------------

---- IMPLEMENTATION DES CONTRAINTES ----

-----------------------------------------------------------------------

---- un client est connu par l'agence pour laquelle travaille
---- l'agent qui conseille ce client (VIEW)
CREATE OR REPLACE VIEW ClientAgence(unC, uneA)
AS	(
	SELECT numClient, lAgence
	FROM Agence, Client, Agent
	WHERE Agence.numAgence = Agent.lAgence
	AND Agent.numAgent = Client.lAgent
	);

-- Test
DELETE FROM Operations;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;
INSERT INTO Agence VALUES(1, 0299523614, 'add1');
INSERT INTO Agent VALUES(1,'A', 'B', 0, 1300, 1);
INSERT INTO Client VALUES(1, 'C', 'D', 'add2', TO_DATE('09/03/1980'), 1);

-----------------------------------------------------------------------

---- un client ne peut être conseillé par un agent ayant le même
----- nom que lui (TRIGGER)
CREATE OR REPLACE TRIGGER ClientAgentNom
BEFORE INSERT OR UPDATE ON Client
FOR EACH ROW
DECLARE
	AgentNom Agent.nom%TYPE;
--WHEN (NEW.nom IS NOT NULL)	
BEGIN
	SELECT nom INTO AgentNom
	FROM Agent
	WHERE :NEW.lAgent = numAgent
	;
	
	IF (:new.nom = AgentNom) THEN
		RAISE_APPLICATION_ERROR(-20001, 'un client ne peut être conseillé par un agent ayant le même nom que lui');
	END IF;
END;
/

-- Test
DELETE FROM Operations;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;
INSERT INTO Agence VALUES(1, 0299523614, 'add1');
INSERT INTO Agence VALUES(2, 0299523614, 'add2');
INSERT INTO Agent VALUES(1,'A', 'B', 0, 1300, 2);
INSERT INTO Client VALUES(1, 'A', 'C', 'add2', TO_DATE('09/03/1980'), 1);

-----------------------------------------------------------------------

---- une hause de salaire <= 10% et une baisse de salaire <= 8% (TRIGGER)
---- un client ne peut effectuer un retrait > salaire (TRIGGER)
CREATE OR REPLACE TRIGGER EvoluSalaire
BEFORE UPDATE ON Agent
FOR EACH ROW
BEGIN
	IF (:NEW.salaire > :OLD.salaire * 1.1 OR :NEW.salaire < :OLD.salaire * 0.92) THEN
		RAISE_APPLICATION_ERROR(-20003, 'une hause de salaire <= 10% et une baisse de salaire <= 8%');
	END IF;
END;
/

-- Test
DELETE FROM Operations;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;
INSERT INTO Agence VALUES(1, 0299523614, 'add1');
INSERT INTO Agent VALUES(1,'A', 'B', 0, 1300, 1);

UPDATE Agent
SET Salaire = 600
WHERE numAgent = 1;

-----------------------------------------------------------------------

---- un client ne peut retirer de l'argent que sur un compte qui lui
---- appartient (TRIGGER)
CREATE OR REPLACE TRIGGER ClientCompte
BEFORE INSERT OR UPDATE ON Operations
FOR EACH ROW
WHEN (NEW.typeOp = 'RETRAIT')
DECLARE
	ApCompte Appartient.unCompte%TYPE;
BEGIN
	SELECT unCompte INTO ApCompte
	FROM Appartient
	WHERE :NEW.leClient = unClient
	;
	
	IF (:NEW.leCompte != ApCompte) THEN
		RAISE_APPLICATION_ERROR(-20004, 'ne peut retirer de largent que sur un compte qui lui appartient');
	END IF;
END;
/

-- Test
DELETE FROM Operations;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;
INSERT INTO Agence VALUES(1, 0299523614, 'add1');
INSERT INTO Agent VALUES(1,'A', 'B', 0, 1300, 1);
INSERT INTO Client VALUES(1, 'C', 'D', 'add2', TO_DATE('09/03/1980'), 1);
INSERT INTO Compte VALUES(1, 'COURANT', 500);
INSERT INTO Compte VALUES(2, 'COURANT', 600);
INSERT INTO Appartient VALUES(1, 1);
INSERT INTO Operations VALUES(1, TO_DATE('09/03/2017'), 'RETRAIT', 20, 1, 1);
INSERT INTO Operations VALUES(2, TO_DATE('09/03/2017'), 'RETRAIT', 20, 1, 2);

-----------------------------------------------------------------------

-- un client ne peut effectuer un retrait > salaire (TRIGGER)
CREATE OR REPLACE TRIGGER RetraitSupSalaire
BEFORE INSERT OR UPDATE ON Operations
FOR EACH ROW
DECLARE
	salaire Compte.solde%TYPE;	
BEGIN
	SELECT solde INTO salaire
	FROM Compte
	WHERE :NEW.leCompte = Compte.numCompte
	;
	
	IF (:new.typeOp = 'RETRAIT' AND :NEW.montant > salaire) THEN
		RAISE_APPLICATION_ERROR(-20002, 'Le montant ne peut pas être sup au solde');
	END IF;
END;
/

-- Test
DELETE FROM Operations;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;
INSERT INTO Agence VALUES(1, 0299523614, 'add2');
INSERT INTO Agent VALUES(1,'A', 'B', 0, 1300, 1);
INSERT INTO Client VALUES(1, 'C', 'D', 'add2', TO_DATE('09/03/1980'), 1);
INSERT INTO Compte VALUES(1, 'COURANT', 500);
INSERT INTO Appartient VALUES(1,1);
INSERT INTO Operations VALUES(1, TO_DATE('09/03/2017'), 'RETRAIT', 800, 1, 1);

-----------------------------------------------------------------------

---- un directeur est mieux payé que les autres agents de 
---- l'agence (VIEW)
CREATE OR REPLACE VIEW DirecteurMieuPaye(Agent)
AS	( SELECT numAgent
	  FROM Agent, Agence Ag
	  WHERE lAgence = Ag.numAgence
	  AND estDirecteur = 1
	  AND salaire < ( SELECT MAX(salaire)
					  FROM Agent
					  WHERE Ag.numAgence = lAgence
					  AND estDirecteur = 0
					)
	)
;

-- Test
DELETE FROM Operations;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;
INSERT INTO Agence VALUES(1, 0299523614, 'add2');
INSERT INTO Agent VALUES(1,'A', 'B', 0, 1300, 1);
INSERT INTO Agent VALUES(2,'C', 'D', 1, 1400, 1);
INSERT INTO Agent VALUES(3,'E', 'F', 0, 1500, 1);

-----------------------------------------------------------------------

---- Tout compte appartient à au moins un client (VIEW)
CREATE OR REPLACE VIEW ComptesSansProprio(Compte)
AS ( SELECT numCompte
	 FROM Compte, Appartient
	 WHERE numCompte = unCompte
	 AND unClient IS NULL
   )
;

-- Test
DELETE FROM Operations;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;
INSERT INTO Agence VALUES(1, 0299523614, 'add1');
INSERT INTO Agent VALUES(1,'A', 'B', 0, 1300, 1);
INSERT INTO Client VALUES(1, 'C', 'D', 'add2', TO_DATE('09/03/1980'), 1);
INSERT INTO Client VALUES(2	, 'E', 'F', 'add3', TO_DATE('09/03/1980'), 1);
INSERT INTO Compte VALUES(1, 'COURANT', 500);
INSERT INTO Appartient VALUES(2, 1);

-----------------------------------------------------------------------

---- une agence n'a qu'un directeur (VIEW)
CREATE OR REPLACE VIEW DirecteurUnique(lesAgences)
AS	( ( SELECT numAgence
		FROM Agence
		MINUS
		SELECT DISTINCT lAgence
		FROM Agent
	  )
	  UNION
	  (
		SELECT lAgence
		FROM AGENT
		WHERE estDirecteur = 1
		GROUP BY lAgence
		HAVING COUNT(*) != 1
	  )
	)
;

-- Test
DELETE FROM Operations;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;
INSERT INTO Agence VALUES(1, 0299523614, 'add1');
INSERT INTO Agence VALUES(2, 0299523615, 'add2');
INSERT INTO Agent VALUES(1,'A', 'B', 0, 1300, 1);
INSERT INTO Agent VALUES(2,'A', 'B', 1, 1300, 2);
INSERT INTO Agent VALUES(3,'A', 'B', 1, 1300, 2);

-----------------------------------------------------------------------

