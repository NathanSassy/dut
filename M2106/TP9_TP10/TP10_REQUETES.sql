-- 1. Donner toutes les opérations de RETRAIT effectuées depuis deux jours
SELECT DISTINCT nom, prenom, typeOP, numOperations
FROM Client, Operation
WHERE leClient = numClient
AND dateOpe >= SYSDATE - 2
;


-- 2. Quels sont les comptes qui ont fait l'objet d'un 
-- dépôt de plus de 1000 euros ?
SELECT numCompte
FROM Compte, Operation
WHERE leCompte = numCompte
AND typeOP = 'DEPOT'
AND montant > 1000
;


-- 3. Quels sont les directeurs qui dirigent le moins d'agents ?
SELECT lAgence, COUNT(*) AS nbAgents
FROM Agent
GROUP BY lAgence
HAVING COUNT(*) = ( SELECT MIN(COUNT(*))
					FROM Agent
					GROUP BY lAgence
				  )
;


-- 4. Quels sont les agents (numéros, noms et prénoms) qui
-- conseillent le moins de clients ?
SELECT numAgent, Agent.nom, Agent.prenom
FROM Agent, Client
WHERE numAgent = lAgent
GROUP BY numAgent, Agent.nom, Agent.prenom
HAVING COUNT(numClient) = ( SELECT MIN(COUNT(numClient))
							FROM Agent, Client
							WHERE numAgent = lAgent
							GROUP BY numClient
						  )
;


-- 5. Quel le type de compte le plus représenté dans la table compte ?
SELECT libelle, COUNT(*) AS nbComptes
FROM Compte
GROUP BY libelle
HAVING COUNT(*) = ( SELECT MAX(COUNT(*))
					FROM Compte
					GROUP BY libelle
				  )
;


-- 6. Quelles sont les agences qui ont le plus de clientèle ?
SELECT lAgence
FROM Agent, Client
WHERE lAgent = numAgent
GROUP BY lAgence
HAVING COUNT(numClient) = ( SELECT MAX(COUNT(numClient))
							FROM Client,Agent
							WHERE lAgent = numAgent
							GROUP BY lAgence
						  )
;	

-- 7. Quels sont les noms et prénoms du directeur qui
-- dirige Patrice Baron ?
SELECT nom, prenom
FROM Agent
WHERE estDirecteur = 1
AND lAgence  = ( SELECT lAgence
				 FROM Agent
				 WHERE UPPER(nom) = 'BARON'
				 AND UPPER(prenom) = 'PATRICE'
			   )
;



-- 8. Quels sont les clients qui sont conseillés par un
-- agent qui ont le même prénom qu'eux ?
SELECT Client.nom, numClient
FROM Agent, Client
WHERE lAgent = numAgent
AND UPPER(Agent.prenom) = UPPER(Client.prenom)
;


-- 9. Quels sont les noms et prénoms de l'agent qui conseille 
-- le Client qui possède un compte dont le solde est de 85000 euros ?
SELECT Agent.nom, Agent.prenom
FROM Agent, Client, Compte, Appartient
WHERE lAgent = numAgent
AND unClient = numClient
AND unCompte = numCompte
AND solde = 85000
;


-- 10. Quel est le nom de l'agent qui dirige tous les
-- agents gagnant exactement 1500 euros ?
SELECT DISTINCT nom
FROM Agent
WHERE estDirecteur = 1
AND lAgence = ( SELECT lAgence
				FROM Agent
				WHERE salaire = 1500
			  )
;


-- 11. Quels est le numéro de telephone de l'agence dont
-- le directeur dirige l'agent louis Jeaunot
SELECT noTel
FROM Agence
WHERE numAgence = ( SELECT lAgence
					FROM Agent
					WHERE UPPER(prenom) = 'LOUIS'
					AND UPPER(nom) = 'JEAUNOT'
				  )
;


-- 12. Quelle est l'agence dont la moyenne des salaires des
-- agents est le plus élevé ?
SELECT lAgence
FROM Agent
HAVING SUM(salaire)/COUNT(*) = ( SELECT MAX(SUM(salaire)/COUNT(*))
								 FROM Agent
								 GROUP BY lAgence
							   )
GROUP BY lAgence
;


-- 13. Donner la liste des comptes appartenant à plus d'une personne.
SELECT unCompte
FROM Appartient
HAVING COUNT(unClient) > 1
GROUP BY unCompte;


-- 14. Quelle est l'agence dont la somme des soldes des
-- comptes epargne est le plus élevé ?
SELECT lAgence, SUM(solde)
FROM Agent, Client, Appartient, Compte
WHERE lAgent = numAgent
AND unCompte = numCompte
AND unClient = numClient
AND UPPER(libelle) = 'EPARGNE'
HAVING SUM(solde) = ( SELECT MAX(SUM(solde))
					  FROM Agent, Client, Appartient, Compte
					  WHERE lAgent = numAgent
					  AND unCompte = numCompte
					  AND unClient = numClient
					  AND UPPER(libelle) = 'EPARGNE'
					  GROUP BY lAgence
					)
GROUP BY lAgence
;
