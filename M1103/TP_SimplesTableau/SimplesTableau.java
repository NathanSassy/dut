public class SimplesTableau
{

	/**
	* La methode principale
	*/
	void principal()
	{
		// appel des cas de test un par un
		testAfficherTab() ;
		testSaisir();
		testAfficherTabLgn();
		testTirerAleatoire();
		testRemplirAleatoire();
		testEgalite();
		testCopier();
		testDecalerGche();
		testEchange();
		testNbOccurrences();
		testLeMinEtLeMax();
		testInverse();
		testMelange();
		testSupprimerUneValeur();
		testInclusion();
	}

	/**
	* Verifie les parametres
	* @param leTab le tableau
	* @param nbElem le nombre d'elements du tableau
	* @return renvoie vrai si les parametres sont corrects
	*/
	boolean verifieParamTab(int [] leTab, int nbElem)
	{
		boolean correct = true;
		if (leTab == null)
		{
			System.out.println("Le tableau est null");
			correct = false;
		}
		else if (nbElem <= 0)
		{
			System.out.println("nbElem doit etre positif");
			correct = false;
		}
		else if (nbElem > leTab.length)
		{
			System.out.println("nbElem est en dehors des bornes du tableau");
			correct = false;
		}
		return correct;
	}

	/**
	* Affiche le tableau
	* @param leTab le tableau a afficher
	* @param nbElem le nombre d'elements a afficher
	*/
	void afficherTab(int[] leTab, int nbElem)
	{
		if (verifieParamTab(leTab, nbElem))
		{
			for(int i = 0; i < nbElem; i++)
			{
				System.out.println(leTab[i]);
			}
		}
	}

	/**
	* Test de la methode afficherTab
	*/
	void testAfficherTab()
	{
		System.out.println("\n=== Test de la methode afficherTab ===\n");

		System.out.println("Test avec un tableau vide");
		int[] tab = null;
		afficherTab(tab, 10);

		System.out.println("\nTest avec un tableau rempli de 5 elements");
		int tab2[] = {5, 2, 89, -2, 3};
		afficherTab(tab2,5);

		System.out.println("\n=== Fin du test de la methode afficherTab ===\n");
	}

	/**
	* Saisi du tableau
	* @param leTab le tableau où est enrengistre la saisi
	* @param nbElem le nombre d'element a saisir
	*/
	void saisir(int[] leTab, int nbElem)
	{
		if (verifieParamTab(leTab, nbElem))
		{
			for(int i = 0; i < nbElem; i++)
			{
				leTab[i] = SimpleInput.getInt("Entrez un entier");
			}
		}
	}

	/**
	* Test de la methode saisir
	*/
	void testSaisir() {
		System.out.println("\n=== Test de la methode saisir ===\n");

		System.out.println("Test avec un tableau vide");
		int [] tab = null;
		saisir(tab, 10);

		System.out.println("\nTest avec un tableau a saisir de 5 elements");
		tab = new int[5];
		saisir(tab,5);
		System.out.println("Affichage du tab :");
		afficherTab(tab,5);

		System.out.println("\n=== Fin du test de la methode saisir ===\n");
	}

	/**
	* Affiche le contenu d'un tableau case par case
	* et par ligne de nbLgn elements
	* @param leTab le tableau a afficher
	* @param nbElem le nombre d'entiers que contient le tableau
	* @param nbLgn le nombre d'elements par ligne
	*/
	void afficherTabLgn(int[] leTab, int nbElem, int nbLgn)
	{
		if(nbLgn <= 0)
		{
			System.out.println("nbLgn doit etre positif");
		}
		else if (verifieParamTab(leTab, nbElem))
		{
			for (int i = 0; i < nbElem; i++)
			{
				System.out.print(leTab[i] + " ");
				if(((i+1) % nbLgn) == 0)
				{
					System.out.print("\n");
				}
			}
		}
	}

	/**
	* Test de la methode afficherTabLgn
	*/
	void testAfficherTabLgn()
	{
		System.out.println("\n=== Test de la methode afficherTab ===\n");

		System.out.println("Test avec un tableau vide");
		int [] tab = null;
		saisir(tab, 10);

		System.out.println("\nTest avec un tableau de 9 elements et affichage de 3 elements par ligne");
		int tab2[] = {5, 2, 89, -2, 3, 42, 9, 1, 7};
		afficherTabLgn(tab2, 9, 3);

		System.out.println("\nTest avec un tableau de 9 elements et affichage de 1 elements par ligne");
		afficherTabLgn(tab2, 9, 1);

		System.out.println("\nTest avec un tableau de 9 elements et affichage de 0 elements par ligne");
		afficherTabLgn(tab2, 9, 0);
		System.out.println("\nTest avec un tableau de 9 elements et affichage de 4 elements par ligne");
		afficherTabLgn(tab2, 9, 4);

		System.out.println("\n=== Fin du test de la methode afficherTab ===\n");
	}

	/**
	* Renvoie un entier aleatoire compris entre min et max (min &le; valeur &le; max)
	* @param min valeur minimum
	* @param max valeur maximum
	* @return l'entier aleatoire
	*/
	int tirerAleatoire(int min, int max)
	{
		return (int)(Math.random() * (max+1-min)) + min;
	}

	/**
	* Test de la methode tirerAleatoire
	*/
	void testTirerAleatoire()
	{
		System.out.println("\n=== Test de la methode tirerAleatoire ===\n");
		System.out.println("Generation de 10 entier, aleatoirement, compris entre -5 et 10 :");

		for(int i = 0; i < 11; i++)
		{
			System.out.println("n°" + i + " = " + tirerAleatoire(-5,10));
		}

		System.out.println("\n=== Fin du test de la methode tirerAleatoire ===\n");
	}

	void remplirAleatoire(int[] leTab, int nbElem, int min, int max)
	{
		if(min > max)
		{
			System.out.println("max doit etre superieur ou egale a min");
		}
		else if(verifieParamTab(leTab, nbElem))
		{
			for(int i = 0; i < nbElem; i++)
			{
				leTab[i] = tirerAleatoire(min,max);
			}
		}
	}

	/**
	* Test de la methode remplirAleatoire
	*/
	void testRemplirAleatoire()
	{
		System.out.println("\n=== Test de la methode remplirAleatoire ===\n");

		System.out.println("Essai avec un tableau de 10 entiers");
		System.out.println("Les valeurs seront comprises entre 0 et 100");
		int [] tab = new int[10];
		remplirAleatoire(tab, 10, 0, 100);
		System.out.println("Affichage du tableau : ");
		afficherTab(tab, 10);

		System.out.println("\n=== Fin du test de la methode remplirAleatoire ===\n");
	}

	/**
	* Renvoie vrai si les 2 tableaux passes en parametre sont exactement
	* les memes en nombre d'elements et en contenu (case par case).
	* @param tab1 le 1er tableau a comparer
	* @param tab2 le 2eme tableau a comparer
	* @param nbElem1 le nombre d'entiers presents dans le 1er tableau
	* @param nbElem2 Le nombre d'entiers presents dans le 2eme tableau
	* @return true si egalite parfaite sinon false
	*/
	boolean egalite(int[] tab1, int[] tab2, int nbElem1, int nbElem2)
	{
		boolean egal = false;
		boolean verif1 = verifieParamTab(tab1, nbElem1);
		boolean verif2 = verifieParamTab(tab2, nbElem2);

		if (verif1 && verif2 && nbElem1 == nbElem2)
		{
			egal = true;
			for (int i = 0; i < nbElem1; i++)
			{
				if (tab1[i] != tab2[i])
				{
					egal = false;
				}
			}
		}
		return egal;
	}

	/**
	* Test de la methode egalite
	*/
	void testEgalite()
	{
		System.out.println("\n=== Test de la methode egalite ===");
		System.out.println("\nTest avec un tableau null");
		int [] tab1 = null;
		int [] tab2 = null;
		System.out.println("egal : " + egalite(tab1, tab2, 3, 3));

		System.out.println("\nTest avec un element non positif");
		tab1 = new int[5];
		tab2 = new int[5];
		System.out.println("egal : " + egalite(tab1, tab2, 3, 0));

		System.out.println("\nTest avec deux tableaux differents de meme taille");
		int [] tab3 = {3, 7, 9, 5};
		int [] tab4 = {3, 7, 7, 5};
		System.out.println("egal : " + egalite(tab3, tab4, 4, 4));

		System.out.println("\nTest avec deux tableaux identiques");
		int [] tab5 = {6, 7, 8, 9, 10};
		int [] tab6 = {6, 7, 8, 9, 10};
		System.out.println("egal : " + egalite(tab5, tab6, 5, 5));
		System.out.println("\n=== Fin du test de la methode egalite ===\n");
	}

	/**
	* Renvoie la copie exacte (clone) du tableau passe en parametre.
	* @param tabToCopy le tableau a copier
	* @param nbElem - le nombre d'entiers presents dans le tableau
	* @return le nouveau tableau qui est la copie du tableau passe en parametre
	*/
	int[] copier(int[] tabToCopy, int nbElem)
	{
		int [] copie = null;
		if (verifieParamTab(tabToCopy, nbElem))
		{
			copie = new int [nbElem];
			for (int i = 0; i < nbElem; i++)
			{
				copie[i] = tabToCopy[i];
			}
		}
		return copie;
	}

	/**
	* Test de la methode copier
	*/
	void testCopier()
	{
		System.out.println("\n=== Test de la methode copier ===");
		int [] copie;

		System.out.println("\nTest avec un tableau null");
		int [] tab1 = null;
		copier(tab1, 3);

		System.out.println("\nTest avec un tableau de 3 elements");
		int [] tab2 = {3, 4, 5};
		copie = copier(tab2, 3);
		System.out.println("Affichage original: " + tab2[0] + ", " + tab2[1] + ", " + tab2[2]);
		System.out.println("Affichage copie: " + copie[0] + ", " + copie[1] + ", " + copie[2]);

		System.out.println("\n=== Fin du test de la methode Copier ===\n");
	}

	/**
	* Decale de une case de la droite vers la gauche toutes les
	* cases d'un tableau a partir d'un indice "ind" et jusque nbElem-1
	* @param leTab le tableau auquel les cases seront decales
	* @param nbElem le nombre d'entiers presents dans le tableau
	* @param ind l'indice a partir duquel commence le decalage a gauche
	*/
	void decalerGche(int[] leTab, int nbElem, int ind)
	{
		if (ind < 0 || ind > (nbElem-1))
		{
			System.out.println("Indice en dehors des bornes");
		}
		else if (verifieParamTab(leTab, nbElem))
		{
			for (int i = ind; i < (nbElem-1); i++)
			{
				leTab[i] = leTab[i+1];
			}
		}
	}

	/**
	* Test de la methode decalerGche
	*/
	void testDecalerGche()
	{
		System.out.println("\n=== Test de la methode decalerGche ===");

		System.out.println("\nTest avec un tableau null");
		int [] tab1 = null;
		decalerGche(tab1, 5, 3);

		System.out.println("\nTest avec un mauvais indice");
		int [] tab2 = {0, 1, 2, 3, 4, 5, 6};
		decalerGche(tab2, 7, 6);

		System.out.println("\nTest avec des parametres corrects\n");
		System.out.print("\nInitial : \n");
		afficherTab(tab2, 7);

		decalerGche(tab2, 7, 4);
		System.out.print("\ndecale avec indice 4 : \n");
		afficherTab(tab2, 7);

		System.out.println("\n\n=== Fin du test de la methode decalerGche ===\n");
	}

	/**
	* Echange les contenus des cases du tableau passe en
	* parametre, cases identifiees par les indices ind1 et ind2
	* @param leTab le tableau
	* @param nbElem le nombre d'entiers presents dans le tableau
	* @param ind1 numero de la premiere case a echanger
	* @param ind2 numero de la deuxieme case a echanger
	*/
	void echange(int[] leTab, int nbElem, int ind1, int ind2)
	{
		if (ind1 < 0 || ind1 < 0)
		{
			System.out.println("Indice trop petit");
		}
		else if (ind1 > nbElem || ind2 > nbElem)
		{
			System.out.println("Indice trop grand");
		}
		else if (verifieParamTab(leTab, nbElem))
		{
			int tmp = leTab[ind2];
			leTab[ind2] = leTab[ind1];
			leTab[ind1] = tmp;
		}
	}

	/**
	* Test de la methode echange
	*/
	void testEchange()
	{
		System.out.println("\n=== Test de la methode echange ===");

		int [] tab1 = {3, 4, 5, 6, 7, 8};
		
		System.out.print("\nInitial : ");
		afficherTab(tab1, 6);
		System.out.println("\nEchange des cellules 2 et 3");
		echange(tab1, 6, 2, 3);
		System.out.print("Apres echange : ");
		afficherTab(tab1, 6);

		System.out.println("\n\n=== Fin du test de la methode echange ===\n");
	}

	/**
	* Renvoie le nombre d'occurrences d'un entier dans un tableau
	* @param leTab le tableau
	* @param nbElem le nombre d'entiers presents dans le tableau
	* @param elem l'entier a rechercher dans le tableau
	* @return le nombre d'occurrences
	*/
	int nbOccurrences(int[] leTab, int nbElem, int elem)
	{
		int occurences = 0;
		if (verifieParamTab(leTab, nbElem))
		{
			for (int i = 0; i < nbElem; i++)
			{
				if(leTab[i] == elem)
				{
					occurences++;
				}
			}
		}
		return occurences;
	}

	/**
	* Test de la methode nbOccurrences
	*/
	void testNbOccurrences()
	{
		System.out.println("\n=== Test de la methode nbOccurrences ===");

		System.out.println("\nTest avec un tableau null");
		int [] tab1 = null;
		nbOccurrences(tab1, 0, 5);

		System.out.println("\nTest avec ce tableau :");
		int [] tab2 = {1, 2, 3, 3, 3, 4, 2, 4, 5, 5, 6};
		afficherTab(tab2, 11);

		System.out.println("\nNombre de '2' : " + nbOccurrences(tab2, 11, 2));
		System.out.println("Nombre de '3' : " + nbOccurrences(tab2, 11, 3));
		System.out.println("Nombre de '1' : " + nbOccurrences(tab2, 11, 1));
		System.out.println("Nombre de '6' : " + nbOccurrences(tab2, 11, 6));

		System.out.println("\n\n=== Fin du test de la methode nbOccurrences ===\n");
	}

	/**
	* Renvoie le maximum parmi les elements du tableau
	* @param leTab le tableau
	* @param nbElem le nombre d'entiers presents dans le tableau
	* @return le maximum des elements du tableau
	*/
	int leMax(int[] leTab, int nbElem)
	{
		int max = 0;

		if (verifieParamTab(leTab, nbElem))
		{
			max = leTab[0];
			for (int i = 0; i < nbElem; i++)
			{
				if (max < leTab[i])
				{
					max = leTab[i];
				}
			}
		}
		return max;
	}

	/**
	* Renvoie le maximum parmi les elements du tableau
	* @param leTab le tableau
	* @param nbElem le nombre d'entiers presents dans le tableau
	* @return le minimum des elements du tableau
	*/
	int leMin(int[] leTab, int nbElem)
	{
		int min = 0;

		if (verifieParamTab(leTab, nbElem))
		{
			min = leTab[0];
			for (int i = 0; i < nbElem; i++)
			{
				if (min > leTab[i])
				{
					min = leTab[i];
				}
			}
		}
		return min;
	}

	/**
	* Test des methodes leMin et leMax
	*/
	void testLeMinEtLeMax()
	{
		System.out.println("\n=== Test des methodes leMax et leMin ===");

		System.out.println("\nTest avec ce tableau :");
		int [] tab1 = {-2, -1, 0, 1, 2, 3, 4, 5};
		afficherTab(tab1, 8);

		System.out.println("\nLe minimum : " + leMin(tab1, 8));
		System.out.println("Le maximum : " + leMax(tab1, 8));

		System.out.println("\n\n=== Fin du test des methodes leMax et leMin ===\n");
	}

	/**
	* Renvoie un nouveau tableau qui est l'inverse de celui passe en parametre
	* @param leTab le tableau
	* @param nbElem le nombre d'entiers presents dans le tableau
	* @return le nouveau tableau qui est l'inverse de leTab sur la plage (0...nbElem-1)
	*/
	int[] inverse(int[] leTab, int nbElem)
	{
		int [] invTab = null;
		if (verifieParamTab(leTab, nbElem))
		{
			invTab = new int [nbElem];
			int j = nbElem-1;
			for (int i = 0; i < nbElem; i++)
			{
				invTab[i] = leTab[j];
				j--;
			}
		}
		return invTab;
	}

	/**
	* Test de la methode inverse
	*/
	void testInverse()
	{
		System.out.println("\n=== Test de la methode inverse ===");

		System.out.println("\nTest avec ce tableau :");
		int [] tab1 = {-2, -1, 0, 1, 2, 3, 4, 5};
		afficherTab(tab1, 8);

		System.out.println("\nL'inverse du tableau :");
		int [] tab2 = inverse(tab1, 8);
		afficherTab(tab2, 8);

		System.out.println("\n\n=== Fin du test de la methode inverse ===\n");
	}


	/**
	* Retourne un nouveau tableau qui a la meme taille et les memes occurrences d'elements que le
	* tableau passe en parametre mais ces elements sont repartis selon des indices aleatoires
	* @param leTab le tableau
	* @param nbElem le nombre d'entiers presents dans le tableau
	* @return le nouveau tableau qui a le meme contenu que le tableau initial mais melange
	*/
	int[] melange(int[] leTab, int nbElem)
	{
		if (verifieParamTab(leTab, nbElem))
		{
			int incideAlea1;
			int incideAlea2;
			for (int i = 0; i < nbElem; i++)
			{
				incideAlea1 = tirerAleatoire(0, nbElem-1);
				incideAlea2 = tirerAleatoire(0, nbElem-1);
				echange(leTab, nbElem, incideAlea1, incideAlea2);
			}
		}
		return leTab;
	}

	/**
	* Test de la methode melange
	*/
	void testMelange()
	{
		System.out.println("\n=== Test de la methode melange ===");

		System.out.println("\nTest avec ce tableau :");
		int [] tab1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		afficherTab(tab1, 11);

		System.out.println("\nOn melange le tableau et on l'affiche :");
		melange(tab1, 11);
		afficherTab(tab1, 11);

		System.out.println("\n\n=== Fin du test de la methode melange ===\n");
	}

	/**
	* Supprime du tableau la premiere case rencontree dont le contenu est egale a "valeur".
	* La case du tableau est supprimee par decalage a gauche des cases du tableau.
	* A l'issue de la suppression (si elle existe) le nombre d'elements
	* dans le tableau est decremente et retourne.
	* @param leTab le tableau
	* @param nbElem le nombre d'entiers presents dans le tableau
	* @param valeur le contenu de la premiere case a supprimer
	* @return le nombre d'elements dans le tableau (eventuellement inchange)
	*/
	int supprimerUneValeur(int[] leTab, int nbElem, int valeur)
	{
		if (verifieParamTab(leTab, nbElem))
		{
			int i = 0;
			boolean chercher = true;
			while (i < nbElem && chercher)
			{
				if (leTab[i] == valeur)
				{
					decalerGche(leTab, nbElem, i);
					nbElem--;
					chercher = false;
				}
				else
				{
					i++;
				}
			}
		}
		return nbElem;
	}

	/**
	* Test de la methode supprimerUneValeur
	*/
	void testSupprimerUneValeur()
	{
		System.out.println("\n=== Test de la methode supprimerUneValeur ===");

		System.out.println("\nTest avec ce tableau :");
		int [] tab1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int taille = 11;
		afficherTab(tab1, taille);

		System.out.println("\nOn supprime la valeur 7 et on l'affiche :");
		taille = supprimerUneValeur(tab1, taille, 7);
		afficherTab(tab1, taille);

		System.out.println("\nOn supprime la valeur 0 et on l'affiche :");
		taille = supprimerUneValeur(tab1, taille, 0);
		afficherTab(tab1, taille);

		System.out.println("\nOn supprime la valeur 10 et on l'affiche :");
		taille = supprimerUneValeur(tab1, taille, 10);
		afficherTab(tab1, taille);

		System.out.println("\n\n=== Fin du test de la methode supprimerUneValeur ===\n");
	}

	/**
	* Renvoie vrai ssi le tableau tab1 est inclus dans tab2. Autrement dit, si tous les
	* elements de tab1 se retrouvent integralement dans tab2 (y compris les doublons)
	* mais pas necessairement dans le meme ordre.
	* @param tab1 le premier tableau
	* @param tab2 le deuxieme tableau
	* @param nbElem1 - le nombre d'entiers presents dans le tableau1
	* @param nbElem2 - le nombre d'entiers presents dans le tableau2
	* @return vrai ssi tableau1 est inclus dans tableau2
	*/
	boolean inclusion(int[] tab1, int[] tab2, int nbElem1, int nbElem2)
	{
		boolean inclus;
		if (verifieParamTab(tab1, nbElem1) && verifieParamTab(tab2, nbElem2))
		{
			inclus = true;
			boolean trouve = false;
			for (int i = 0; i < nbElem1 && inclus; i++)
			{
				trouve = false;
				for (int j = 0; j < nbElem2; j++)
				{
					if (tab1[i] == tab2[j])
					{
						trouve = true;
					}
				}
				if (trouve == false)
				{
					inclus = false;
				}
			}
		}
		else
		{
			inclus = false;
		}
		return inclus;
	}

	/**
	* Test de la methode inclusion
	*/
	void testInclusion()
	{
		System.out.println("\n=== Test de la methode inclusion ===");

		System.out.println("\nTest avec ces tableaux :");
		System.out.println("Tab1 :");
		int [] tab1 = {0, 1, 2, 3, 4, 5};
		afficherTab(tab1, 6);

		System.out.println("\nTab2 :");
		int [] tab2 = {1, 2, 3};
		afficherTab(tab2, 3);

		System.out.println("\nTab3 :");
		int [] tab3 = {1, 1, 2, 3, 3, 3};
		afficherTab(tab3, 6);
		
		System.out.println("\n\nLe deuxieme tableau est il inclus dans le premier ?");
		System.out.println("Inclus : " + inclusion(tab2, tab1, 3, 6));

		System.out.println("Le premier tableau est il inclus dans lui-meme ?");
		System.out.println("Inclus : " + inclusion(tab1, tab1, 6, 6));

		System.out.println("Le premier tableau est il inclus dans le deuxieme ?");
		System.out.println("Inclus : " + inclusion(tab1, tab2, 6, 3));

		System.out.println("Le troisieme tableau est il inclus dans le deuxieme ?");
		System.out.println("Inclus : " + inclusion(tab3, tab2, 6, 3));

		System.out.println("\n=== Fin du test de la methode inclusion ===\n");
	}

}
