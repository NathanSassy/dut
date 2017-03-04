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
			
			type VARCHAR2(20)
			CONSTRAINT ckType
			CHECK (UPPER(type) = 'RETRAINT' OR UPPER(type) = 'DEPOT'),
			
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


---- IMPLEMENTATION DES CONTRAINTES ----

-- un client est connu par l'agence pour laquelle travaille
-- l'agent qui conseille ce client (VIEW)
CREATE VIEW AgenceClientAgent
SELECT numClient, numAgence
FROM Agence, Agent, Client
WHERE Agent.lAgence = Agence.numAgence
AND Client.lAgent = Agent.numAgent
;




-- un client ne peut être conseillé par un agent ayant le même nom
-- nom que lui (TRIGGER)

-- une hause de salaire <= 10% et une baisse de salaire <= 8% (TRIGGER)

-- un client ne peut effectuer un retrait > salaire (TRIGGER)

-- un client ne peut retirrer de l'argent que sur un compte qui lui
-- appartient (TRIGGER)

-- un directeur est mieux payé que les autres agents de 
-- l'agence (TRIGGER de table)

-- une agence n'a qu'un directeur (VIEW)
