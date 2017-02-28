---- SUPPRESSION DES TABLES EXISTANTES ----
DROP TABLE Reservation;
DROP TABLE Bateau;
DROP TABLE Emplacement;
DROP TABLE Proprietaire;


---- CREATION DES TABLES ----

CREATE TABLE Proprietaire (
       	    identifiant	NUMBER(4)
			CONSTRAINT	pkProprietaire PRIMARY KEY,
			nom		VARCHAR2(20)
			CONSTRAINT nnNom
			NOT NULL,
			prenom		VARCHAR2(20)
			CONSTRAINT nnPrenom
			NOT NULL,
			CONSTRAINT uqNP UNIQUE (nom, prenom),
			adresse		VARCHAR2(40)
			CONSTRAINT	uqAdresse1
			UNIQUE
			  );

CREATE TABLE Emplacement (
			numero		NUMBER(4)
			CONSTRAINT	pkEmplacement PRIMARY KEY,
			longueur	NUMBER(2)
		     	 );

CREATE TABLE Bateau (
			NoImm		NUMBER(4)
			CONSTRAINT	pkBateau PRIMARY KEY,
			nom			VARCHAR2(20),
			longueur	NUMBER(2),
			largeur		NUMBER(2),
			leP		NUMBER(4)
			CONSTRAINT 	fkLeP
			REFERENCES	Proprietaire(identifiant)
			CONSTRAINT	nnLeP
			NOT NULL
			CONSTRAINT	uqLeP
			UNIQUE,
			lEmplacement	NUMBER(4)
			CONSTRAINT	fkLEmplacement
			REFERENCES 	Emplacement(numero)
			CONSTRAINT	uqlEmplacement
			UNIQUE,
			dateDebut	DATE,
			dateFin		DATE
		     );

CREATE TABLE Reservation (
       	    numero		NUMBER(4)
			CONSTRAINT	pkReservation PRIMARY KEY,
			dateD	DATE,
			dateF	DATE,
			leB		NUMBER(4)
			CONSTRAINT 	fkLeB
			REFERENCES	Bateau(NoImm)
			CONSTRAINT	nnLeB
			NOT NULL,
			lEmpl		NUMBER(4)
			CONSTRAINT 	fkLEmpl
			REFERENCES	Emplacement(numero)
			CONSTRAINT	nnLEmpl
			NOT NULL
			 );


---- IMPLEMENTATION DES CONTRAINTES ----

-- Pour emplacement bateau
CREATE OR REPLACE TRIGGER Stationnement
BEFORE UPDATE ON Bateau
FOR EACH ROW
WHEN ((NEW.lEmplacement IS NOT NULL) AND (NEW.NoImm IS NOT NULL) AND (NEW.dateFin IS NOT NULL) AND (NEW.dateDebut IS NOT NULL))
DECLARE
	nbResa NUMBER;
BEGIN
	SELECT COUNT(*) INTO nbResa
	FROM Reservation
	WHERE lEmpl = :NEW.lEmplacement
	AND leB = :NEW.NoImm
	AND dateF = :NEW.dateFin
	AND dateD = :NEW.dateDebut
	;
	
	IF (nbResa = 0) THEN
		RAISE_APPLICATION_ERROR(-20004, 'Stationnement dans un emplacement reservé');
	END IF;
END;
/

-- Pour les dates de reservation
CREATE OR REPLACE TRIGGER VerifDates
BEFORE INSERT OR UPDATE ON Reservation
FOR EACH ROW
WHEN ((NEW.dateD IS NOT NULL) AND (NEW.dateF IS NOT NULL))
BEGIN
	IF (:NEW.dateD > :NEW.dateF) THEN
		RAISE_APPLICATION_ERROR(-20001, 'La date de fin est superieur a celle de debut');
	END IF;
END;
/

-- Pour la taille du bateau et celle de l'emplacement
CREATE OR REPLACE TRIGGER ResaTailleEmpl
BEFORE UPDATE ON Reservation
FOR EACH ROW
DECLARE
	tailleEmp Emplacement.longueur%TYPE;
	tailleBat Bateau.longueur%TYPE;	
BEGIN
	SELECT longueur INTO tailleEmp
	FROM Emplacement
	WHERE numero = :NEW.lEmpl
	;
	
	SELECT longueur INTO tailleBat
	FROM Bateau
	WHERE NoImm = :NEW.leB
	;
	
	IF (tailleEmp < tailleBat) THEN
		RAISE_APPLICATION_ERROR(-20002, 'Le bateau est plus grand que lemplacement');
	END IF;
	
	EXCEPTION
	WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR(-20003, 'pas de taille possible');
END;
/

---- TEST DES CONTRAINTES ----
INSERT INTO Reservation VALUES
