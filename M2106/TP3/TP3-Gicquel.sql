---- SUPPRESSION DES TABLES EXISTANTES ----
DROP TABLE Reservation;
DROP TABLE Bateau;
DROP TABLE Emplacement;
DROP TABLE Proprietaire;


---- CREATION DES TABLES ----

CREATE TABLE Proprietaire (
       	     		identifiant	NUMBER(4)
			CONSTRAINT	pkProprietaire PRIMARY KEY,
			nom		VARCHAR2(20),
			prenom		VARCHAR2(20),
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
			nom		VARCHAR2(20),
			longeur		NUMBER(2),
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
			dateDebut	DATE,
			dateFin		DATE,
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

---- INSERTION DES TUPLES ----

-- Pour Proprietaire
INSERT INTO Proprietaire VALUES (1, 'Martel', 'Benoit', '23 Rue des barbapapas') ;
INSERT INTO Proprietaire VALUES (2, 'Boudet', 'Alexandre', '12 Rue des maths') ;
INSERT INTO Proprietaire VALUES (3, 'Hervé', 'Maxime', '5 Impasse des sarasins') ;
INSERT INTO Proprietaire VALUES (4, 'Gicquel', 'Antoine', '17 Rue des tractions') ;
INSERT INTO Proprietaire VALUES (5, 'Capitaine', 'Haddock', '2 Rue du port') ;

-- Pour Emplacement
INSERT INTO Emplacement VALUES (1, 12) ;
INSERT INTO Emplacement VALUES (2, 15) ;
INSERT INTO Emplacement VALUES (3, 12) ;
INSERT INTO Emplacement VALUES (4, 20) ;
INSERT INTO Emplacement VALUES (5, 32) ;
INSERT INTO Emplacement VALUES (6, 32) ;


-- Pour Bateau
INSERT INTO Bateau VALUES (0001, 'Poseidon' , 12, 4, 1, 1, TO_DATE('03/02/2017'), TO_DATE('03/04/2017') ) ;
INSERT INTO Bateau VALUES (0002, 'Zeus' , 20, 5, 2, 2, TO_DATE('03/02/2017'), TO_DATE('03/05/2017') ) ;
INSERT INTO Bateau VALUES (0003, 'Aphrodite' , 15, 4, 3, 3, TO_DATE('03/02/2017'), TO_DATE('03/04/2018') ) ;
INSERT INTO Bateau VALUES (0004, 'Apollon' , 6, 2, 4, 4, TO_DATE('03/02/2017'), TO_DATE('15/04/2017') ) ;

-- Pour Reservation
INSERT INTO Reservation VALUES (1, TO_DATE('03/02/2017'), TO_DATE('03/04/2017'), 0001, 2) ;
INSERT INTO Reservation VALUES (2, TO_DATE('03/02/2017'), TO_DATE('03/05/2017'), 0003, 4) ;
INSERT INTO Reservation VALUES (3, TO_DATE('03/02/2017'), TO_DATE('03/04/2018'), 0004, 1) ;
INSERT INTO Reservation VALUES (4, TO_DATE('03/02/2017'), TO_DATE('15/04/2017'), 0002, 3) ;


---- JEU DE TEST -----

-- Pour Proprietaire
INSERT INTO Proprietaire VALUES (1, 'Carrez', 'Robin', '14 Rue des schlags') ; -- meme identifiant
INSERT INTO Proprietaire VALUES (5, 'Pavart', 'Alexandre', '12 Rue des maths') ; -- meme addresse

-- Pour Emplacement
INSERT INTO Emplacement VALUES (1, 42) ; -- meme numero

-- Pour Bateau
INSERT INTO Bateau VALUES (0005, 'Ares' , 2, 1, 1, 5, TO_DATE('03/02/2017'), TO_DATE('03/04/2017') ) ; -- meme proprietaire
INSERT INTO Bateau VALUES (0006, 'Artemis' , 2, 1, 5, 1, TO_DATE('03/02/2017'), TO_DATE('03/04/2017') ) ; -- meme emplacement
INSERT INTO Bateau VALUES (0001, 'Athena' , 12, 4, 5, 5, TO_DATE('03/02/2017'), TO_DATE('03/04/2017') ) ; -- meme noImm
INSERT INTO Bateau VALUES (0001, 'Demeter' , 12, 4, 5, TO_DATE('03/02/2017'), TO_DATE('03/04/2017') ) ; -- manque d'argument (NN)

-- Pour Reservation
INSERT INTO Reservation VALUES (1, TO_DATE('03/02/2017'), TO_DATE('03/04/2017'), 0001, 6) ; -- meme numero
INSERT INTO Reservation VALUES (5, TO_DATE('03/02/2017'), TO_DATE('03/04/2017'), 0001, 6) ; -- meme bateau
INSERT INTO Reservation VALUES (TO_DATE('03/02/2017'), TO_DATE('03/04/2017'), 0001, 6) ; -- manque d'argument (NN)
