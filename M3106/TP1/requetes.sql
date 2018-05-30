---- SUPPRESSION DES TABLES EXISTANTES ----
DROP TABLE Operations;
DROP TABLE Appartient;
DROP TABLE Compte;
DROP TABLE Client;
ALTER TABLE Agence DROP CONSTRAINT fkDirecteur;
DROP TABLE Agent;
DROP TABLE Agence;

DROP SEQUENCE IncrementationSequenceAgence;
DROP SEQUENCE IncrementationSequenceAgent;
DROP SEQUENCE IncrementationSequenceClient;
DROP SEQUENCE IncrementationSequenceCompte;
DROP SEQUENCE IncrementationSequenceOpes;

---- CREATION DES TABLES ----
CREATE TABLE Agence (
			numAgence NUMBER(4)
			CONSTRAINT pkAgence PRIMARY KEY,
			telAgence NUMBER(10),
			adresse VARCHAR2(40)
);
			
CREATE TABLE Agent (
			numAgent NUMBER(4)
			CONSTRAINT pkAgent PRIMARY KEY,
			nomAgent VARCHAR2(20),
			prenomAgent VARCHAR2(20),
			salaire NUMBER(5),
			lAgence NUMBER(4)
			CONSTRAINT 	fkLAgence
			REFERENCES	Agence(numAgence)
			CONSTRAINT	nnLAgence
			NOT NULL
);

ALTER TABLE Agence
ADD directeur NUMBER
CONSTRAINT 	fkDirecteur
REFERENCES	Agent(numAgent)
CONSTRAINT	uqDirecteur
UNIQUE
;
			
CREATE TABLE Client (
			numClient NUMBER(4)
			CONSTRAINT pkClient PRIMARY KEY,
			nomClient VARCHAR2(20)
			CONSTRAINT nnNom NOT NULL,
			prenomClient VARCHAR2(20)
			CONSTRAINT nnPrenom NOT NULL,
			adresseClient VARCHAR2(40),
			dateNaissanceClient DATE,
			lAgent NUMBER(4)
			CONSTRAINT fkLAgent
			REFERENCES Agent(numAgent)
			CONSTRAINT nnLAgent
			NOT NULL
);
			
CREATE TABLE Compte (
			numCompte NUMBER(4)
			CONSTRAINT pkCompte PRIMARY KEY,
			typeCompte VARCHAR2(30)
			CONSTRAINT ckTypeCompte
			CHECK (UPPER(typeCompte) = 'EPARGNE' OR UPPER(typeCompte) = 'COURANT'),
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
			dateOperation DATE DEFAULT CURRENT_DATE,
			typeOperation VARCHAR2(20)
			CONSTRAINT ckType
			CHECK (UPPER(typeOperation) = 'RETRAIT' OR UPPER(typeOperation) = 'DEPOT'),
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


---- AUTO INCREMENTATION

CREATE SEQUENCE IncrementationSequenceAgence;
CREATE SEQUENCE IncrementationSequenceAgent;
CREATE SEQUENCE IncrementationSequenceClient;
CREATE SEQUENCE IncrementationSequenceCompte;
CREATE SEQUENCE IncrementationSequenceOpes;

CREATE OR REPLACE TRIGGER IncrementationTriggerAgence
    BEFORE INSERT ON Agence
    FOR EACH ROW
BEGIN
    IF :new.numAgence IS NULL THEN
        SELECT IncrementationSequenceAgence.NEXTVAL
        INTO :new.numAgence
        FROM DUAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER IncrementationSequenceAgent
    BEFORE INSERT ON Agent
    FOR EACH ROW
BEGIN
    IF :new.numAgent IS NULL THEN
        SELECT IncrementationSequenceAgent.NEXTVAL
        INTO :new.numAgent
        FROM DUAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER IncrementationSequenceClient
    BEFORE INSERT ON Client
    FOR EACH ROW
BEGIN
    IF :new.numClient IS NULL THEN
        SELECT IncrementationSequenceClient.NEXTVAL
        INTO :new.numClient
        FROM DUAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER IncrementationSequenceCompte
    BEFORE INSERT ON Compte
    FOR EACH ROW
BEGIN
    IF :new.numCompte IS NULL THEN
        SELECT IncrementationSequenceCompte.NEXTVAL
        INTO :new.numCompte
        FROM DUAL;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER IncrementationSequenceOpes
    BEFORE INSERT ON Operations
    FOR EACH ROW
BEGIN
    IF :new.numOperations IS NULL THEN
        SELECT IncrementationSequenceOpes.NEXTVAL
        INTO :new.numOperations
        FROM DUAL;
    END IF;
END;
/


-- Trigger de contraintes

CREATE OR REPLACE TRIGGER DirMieuxPaye
AFTER INSERT OR UPDATE ON Agent
DECLARE
	CURSOR sal IS
	SELECT lAgence, salMax, salDir
	FROM (SELECT lAgence, MAX(salaire) AS salMax
		  FROM Agent
		  GROUP BY lAgence) ,
		 (SELECT lAgence AS lAgence2, salaire AS salDir
		  FROM Agent
		  WHERE numAgent IN (SELECT directeur FROM Agence))
	WHERE lAgence = lAgence2;
	uneAgence Agent.lAgence%TYPE;
	leSalMax NUMBER(5);
	leSalDir NUMBER(5);
BEGIN
	OPEN sal;
	LOOP 
	FETCH sal INTO uneAgence, leSalMax, leSalDir;
		EXIT WHEN sal%NOTFOUND;
		IF(leSalMax != leSalDir) THEN
			RAISE_APPLICATION_ERROR(-20011, 'Le directeur de l agence ' ||uneAgence|| ' n a pas le salaire max.');
		END IF;
	END LOOP;
	CLOSE sal;
END;
/

-- l'agence
INSERT INTO Agence VALUES (1, 0600000001);

-- le salarie et le directeur
INSERT INTO Agent VALUES (1,'Guillemot','Clement',3500,0,1);
INSERT INTO Agent VALUES (2,'Lacroix','Benoit',3000,1,1);
