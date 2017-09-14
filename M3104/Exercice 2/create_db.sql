CREATE TABLE Trajet (
			id NUMBER(4)
			CONSTRAINT pkTrajet PRIMARY KEY,
			description VARCHAR2(20),
			dateTrajet DATE DEFAULT CURRENT_DATE
			);
			
CREATE TABLE PointGPS (
			id NUMBER(4)
			CONSTRAINT pkPointGPS PRIMARY KEY,
			latitude FLOAT(10) NOT NULL,
			longitude FLOAT(10) NOT NULL
			);
			
CREATE TABLE PointDansTrajet (
			trajet NUMBER(4)
			CONSTRAINT fkTrajet
			REFERENCES Trajet(id)
			CONSTRAINT nnTrajet
			NOT NULL,
			point NUMBER(4)
			CONSTRAINT fkPoint
			REFERENCES PointGPS(id)
			CONSTRAINT nnPoint
			NOT NULL
			);

