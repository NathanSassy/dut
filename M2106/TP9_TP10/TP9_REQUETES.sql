-- 1. Quels sont les noms et prénoms des agents qui travaillent dans l'agence numéro 2 ?
SELECT nom, prenom
FROM Agent
WHERE lAgence = 2
;


-- 2. Quels sont les comptes qui ne font l'objet d'aucune opération ?
SELECT numCompte
FROM Compte
MINUS
SELECT DISTINCT leCompte
FROM Operation
;

-- 3. A qui appartiennent les comptes ayant un solde de 5000 euros ?
SELECT numClient
FROM Client, Compte, Appartient
WHERE unCompte = numCompte
AND unClient = numClient
AND solde = 5000
;


-- 4. Quels sont les comptes qui ont le plus faible solde ?
SELECT numCompte
FROM Compte
WHERE solde = ( SELECT MIN(solde)
				FROM Compte
			  )
;


-- 5. Donnez les 5 premiers Clients ordonnés par ordre alphabétique des noms ?
SELECT numClient
FROM Client
WHERE ROWNUM <= 5
ORDER BY nom
;


-- 6. Quelles sont les agences qui emploient le plus d'agents ?
SELECT numAgence
FROM Agence, Agent
WHERE numAgence = lAgence
GROUP BY numAgence
HAVING COUNT(numAgent) = ( SELECT MAX(COUNT(numAgent))
						   FROM Agence, Agent
						   WHERE numAgence = lAgence
						   GROUP BY numAgence
						 )
;


-- 7. Quelles sont les agences qui ont le plus de clientèle ?
SELECT numAgence
FROM Agence, Agent, Client
WHERE numAgence = lAgence
AND numAgent = lAgent
GROUP BY numAgence, lAgent
HAVING COUNT(numClient) = ( SELECT MAX(COUNT(numClient))
							FROM Agence, Agent, Client
							WHERE numAgence = lAgence
							AND numAgent = lAgent
							GROUP BY numAgence, lAgent
						  )
;


-- 8. Quel est le nom du propriétaire du compte qui a fait l'objet d'une opération d'un montant de 10000 euros ?
SELECT nom
FROM Client, Appartient, Compte, Operation
WHERE numClient = unClient
AND Compte.numCompte = Appartient.unCompte
AND Operation.leCompte = Appartient.unCompte
AND Operation.montant = 10000
;


-- 9. Quels sont les noms et prénoms des agents qui sont dirigés par Clementine Boutin ?
SELECT DISTINCT nom, prenom
FROM Agent
WHERE estDirecteur = 0
AND lAgence = ( SELECT lAgence
				FROM Agent
				WHERE UPPER(nom) = 'BOUTIN'
				AND UPPER(prenom) = 'CLEMENTINE'
			  )
;


-- 10. Pour chaque directeurs, donner le nombre d'agents de son agence.
SELECT nom AS nomDir, prenom AS prenomDir, lAgence, nbAgents
FROM Agent, ( SELECT COUNT(*) AS nbAgents, lAgence AS Agence2
			  FROM Agent
			  GROUP BY lAgence
			)
WHERE estDirecteur = 1
AND Agence2 = lAgence;


-- 11. Quels sont les noms des agents qui conseillent des clients ayant un compte épargne ?
SELECT DISTINCT Agent.nom, Agent.prenom
FROM Agent, Client, Appartient, Compte
WHERE lAgent = Agent.numAgent
AND unClient = numClient
AND unCompte = Compte.numCompte
AND Compte.libelle = 'EPARGNE'
;


-- 12. Quelles sont les opérations effectuées par des clients conseillés par l'agent numéro 12 ?
SELECT DISTINCT nom, prenom, typeOP, numOperations
FROM Client, Operation
WHERE lAgent = 12
AND leClient = numClient
;


-- 13. Quels sont les noms et prénoms des 3 agents qui gagnent le plus ?
SELECT nom, prenom, salaire
FROM ( SELECT *
	   FROM Agent
	   ORDER BY salaire DESC
	 )
WHERE ROWNUM <= 3
;

