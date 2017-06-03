---- 1. Trigger de table : toutes les agences ont exactement un directeur.
CREATE OR REPLACE TRIGGER DirecteurUniqueTrigger
AFTER INSERT OR UPDATE ON Agent
DECLARE
  nbAgenceIncorecte NUMBER(4);
BEGIN
	SELECT COUNT(*) INTO nbAgenceIncorecte
	FROM (  SELECT lAgence
			FROM Agent
			WHERE estDirecteur = 1
			GROUP BY lAgence
			HAVING COUNT(*) > 1
		 )
	;
  
	IF (nbAgenceIncorecte > 1) THEN
		RAISE_APPLICATION_ERROR(-20010, 'toutes les agences doivent avoir exactement un directeur');
	END IF;
END;
/

-- TEST
DELETE FROM Operation;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;

INSERT INTO Agence VALUES (1, '06 00 00 00 01');
INSERT INTO Agence VALUES (2, '06 00 00 00 02');
INSERT INTO Agence VALUES (3, '06 00 00 00 03');
  
-- un agent de l'agence 1
INSERT INTO Agent VALUES (1,'Guillemot','Clement',1500,0,1);
INSERT INTO Agent VALUES (2,'Lacroix','Benoit',3000,1,1);
-- trois agents de l'agence 2 dont un directeur
INSERT INTO Agent VALUES (3,'Jeaunot','Louis',4000,0,2);
INSERT INTO Agent VALUES (4,'Baron','Patrice',1750,0,2);
INSERT INTO Agent VALUES (5,'Le Marechal','Adeline',4500,1,2);
INSERT INTO Agent VALUES (6,'dfsfd','gsdgd',4500,1,2);
INSERT INTO Agent VALUES (7,'sgqgf','jygffd',4500,1,2);
-- un agent dans l'agance 3 sans directeur
INSERT INTO Agent VALUES (8,'Marigot','Samuel',1600,0,3);

-----------------------------------------------------------------------

---- 2. Trigger de table, qui utilisera nécessairement un curseur, faisant
---- en sorte que lorqu'on insère un agent dans la table agent, ou lorqu'on
----  modidifie un agent dans cette même table, on vérifie que sur l'ensemble
---- des agences présentes dans la table agent le directeur de chacune 
---- d'entre elles est mieux payé que ses agents.
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
		  WHERE estDirecteur = 1)
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

-- TEST
DELETE FROM Operation;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;

-- l'agence
INSERT INTO Agence VALUES (1, '06 00 00 00 01');

-- le salarie et le directeur
INSERT INTO Agent VALUES (1,'Guillemot','Clement',3500,0,1);
INSERT INTO Agent VALUES (2,'Lacroix','Benoit',3000,1,1);

-----------------------------------------------------------------------


---- 3. Trigger : un agent ne peut pas suivre plus de cinq clients.
CREATE OR REPLACE TRIGGER nbrClients
AFTER INSERT OR UPDATE ON Client
DECLARE 
	CURSOR clientsParAgents IS
	SELECT lAgent, nbrclients
	FROM( SELECT lAgent, COUNT(*) AS nbrclients
		  FROM Client
		  GROUP BY lAgent)
	WHERE nbrclients > 5;
	unAgent Client.lAgent%TYPE;
	nbrclients NUMBER(3);
BEGIN 
	OPEN clientsParAgents;
	LOOP
		FETCH clientsParAgents INTO unAgent, nbrclients;
		EXIT WHEN clientsParAgents%NOTFOUND;
		RAISE_APPLICATION_ERROR(-20012, 'L agent ' ||unAgent|| ' a atteind son nombre max de clients.');
	END LOOP;
	CLOSE clientsParAgents;
END;
/

-- TEST
DELETE FROM Operation;
DELETE FROM Appartient;
DELETE FROM Compte;
DELETE FROM Client;
DELETE FROM Agent;
DELETE FROM Agence;

-- l'agence et ses deux salarié
INSERT INTO Agence VALUES (1, '06 00 00 00 01');
INSERT INTO Agent VALUES (1,'Guillemot','Clement',2500,0,1);
INSERT INTO Agent VALUES (2,'Lacroix','Benoit',3000,1,1);

-- les 6 client du salarie 1
INSERT INTO Client VALUES (1,'Villaume','Clement','1 rue Michel de Montaigne','01/01/1978',1);
INSERT INTO Client VALUES (2,'Salun','prenomClient2','2 rue Michel de Montaigne','02/02/1990',1);
INSERT INTO Client VALUES (3,'Colin','prenomClient3','3 rue Michel de Montaigne','03/03/1978',1);
INSERT INTO Client VALUES (4,'Le Gall','prenomClient4','4 rue Michel de Montaigne','04/04/1978',1);
INSERT INTO Client VALUES (5,'Deschamp','prenomClient5','5 rue Michel de Montaigne','05/05/1978',1);
INSERT INTO Client VALUES (6,'Cloarec','prenomClient6','6 rue Michel de Montaigne','06/06/1978',1);
