---- SUPPRESSION DES TABLES EXISTANTES ----
DROP TABLE Operations;
DROP TABLE Appartient;
DROP TABLE Compte;
DROP TABLE Client;
DROP TABLE Agent;
DROP TABLE Agence;

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

ALTER TABLE Agent
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

---- contraintes textuelles non traduites dans le schéma relationnel :
---- 