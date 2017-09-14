-- Clear tables
DELETE FROM PointDansTrajet;
DELETE FROM PointGPS;
DELETE FROM Trajet;

-- Ajout de trajet
INSERT INTO Trajet VALUES (1, 'Rennes - Paris', CURRENT_DATE);
INSERT INTO Trajet VALUES (2,'Paris - Marseille', CURRENT_DATE);
INSERT INTO Trajet VALUES (3,'Vannes - Lorient', CURRENT_DATE);

-- Ajout de PointGPS
INSERT INTO PointGPS VALUES (1, 47.782527, -3.327484);
INSERT INTO PointGPS VALUES (2,47.771453, -3.177795);
INSERT INTO PointGPS VALUES (3, 47.663353, -2.905884);

-- Ajout de PointDansTrajet
INSERT INTO PointDansTrajet VALUES (3, 1);
INSERT INTO PointDansTrajet VALUES (3, 2);
INSERT INTO PointDansTrajet VALUES (3, 3);




