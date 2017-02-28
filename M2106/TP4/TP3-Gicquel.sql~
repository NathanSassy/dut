DROP TABLE Employe;
DROP TABLE Service;

CREATE TABLE Service (
			code		NUMBER(4)
			CONSTRAINT	pkService PRIMARY KEY,
			activite	VARCHAR2(20)
			CONSTRAINT 	nnActivite
			NOT NULL,
			localisation	VARCHAR2(30)
			CONSTRAINT	nnLocalisation
			NOT NULL			
		     );

CREATE TABLE Employe (
			nom		VARCHAR2(15),
			prenom		VARCHAR2(12),
			CONSTRAINT	pkEmploye
			PRIMARY KEY	(nom,prenom),
			adresse		VARCHAR2(40)
			CONSTRAINT 	uqAdresse
			UNIQUE,
			specialite	VARCHAR2(20),
			sonService	NUMBER(4)
			CONSTRAINT 	fkSonService
			REFERENCES	Service(code),
			Nchef		VARCHAR2(15),
			Pchef		VARCHAR2(12),
			CONSTRAINT	fkChef
			FOREIGN KEY	(Nchef,Pchef)
			REFERENCES	Employe(nom,prenom)
		     );
		     
INSERT INTO Service VALUES (1,'depanage','Vannes');
INSERT INTO Service VALUES (2,'administration','Lorient');
INSERT INTO Service VALUES (3,'depanage','Quimper');
INSERT INTO Service VALUES (4,'informatique','Rennes');
INSERT INTO Service VALUES (5,'depanage','Brest');
INSERT INTO Service VALUES (6,'nancier','Rennes');

INSERT INTO Employe VALUES ('SATTLER','Donatien','1 squarre des venètes 56000 Vannes','bricoleur',1,NULL,NULL);
INSERT INTO Employe VALUES ('BARBE','Titouan','2 rue de l ile aux moines 56000 Vannes','bricoleur',1,'SATTLER','Donatien') ;
INSERT INTO Employe VALUES ('BINOT','Justine','12 rue les bleuets 56000 Vannes','DRH',2,NULL, NULL) ;
INSERT INTO Employe VALUES ('DESCHARREAUX','Julian','8 rue de la lune 56000 Vannes','communication',2,'BINOT','Justine') ;
INSERT INTO Employe VALUES ('ALLAIN','Louis','12 impasse des genets 56000 Vannes','secretaire',2,'BINOT','Justine') ;
INSERT INTO Employe VALUES ('BASTARD','Gauthier','31 rue mimosa 56000 Vannes','chef de projet',4,NULL,NULL) ;
INSERT INTO Employe VALUES ('CAROFF','Thomas','21 rue des lilas 56000 Vannes','web developpeur',4,'BASTARD','Gauthier') ;
INSERT INTO Employe VALUES ('CATALAN','Aurelien','12 rue de l ile d artz 56000 Vannes','specialiste BDD',4,'BASTARD','Gauthier') ;
INSERT INTO Employe VALUES ('HEINLE','Helene','67 avenue des champs elyses 75000 Paris','experte comptable',6,NULL,NULL) ;



-- Dans la table Service :
INSERT INTO Service VALUE (5,'informatique','Brest');
INSERT INTO Service VALUE (7,NULL,'Brest');
INSERT INTO Service VALUE (8,'nancier',NULL);

-- Dans la table Employe :
INSERT INTO Employe VALUES ('LE GAL','Elodie','8 rue du chemin vert','expert comptable',6,'LE MOAL','Mael') ;
INSERT INTO Employe VALUES ('LE MOAL','Mael','21 rue des lilas 56000 Vannes','Comptable',6,'HEINLE','Helene') ;
INSERT INTO Employe VALUES ('BEAUDIC','Aurore','5 rue du marechal jore 56000 Vannes',NULL,'HEINLE','Helene') ;
