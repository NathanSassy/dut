public class Scenario
{
	public static void main(String[] args)
	{
		Eleve e1 = new Eleve("A", "B");
		Eleve e2 = new Eleve("C", "D");
		Eleve e3 = new Eleve("E", "F");
		
		Evaluation eva1 = new Evaluation(12, 2);
		Evaluation eva2 = new Evaluation(15, 6);
		Evaluation eva3 = new Evaluation(9, 3);
		Evaluation eva4 = new Evaluation(11, 1.4f);
		Evaluation eva5 = new Evaluation(6, 0.5f);
		
		// Notes en memoire
		e1.ajouterNote(eva1);
		e1.ajouterNote(eva2);
		e1.ajouterNote(eva3);
		e1.setMoyenne();
		System.out.println(e1);
		
		// Notes dans un fichier
		eva1.sauvegarderNotesFichier("notesE2");
		eva2.sauvegarderNotesFichier("notesE2");
		eva3.sauvegarderNotesFichier("notesE2");
		e2.lectureNoteFichier("notesE2");
		e2.setMoyenne();
		e2.sauvegarderElevesFichier();
		System.out.println(e2);
		
		// Notes dans un fichier binaire
		eva1.sauvegarderNotesFichierBin("notesE3-bin");
		eva2.sauvegarderNotesFichierBin("notesE3-bin");
		eva3.sauvegarderNotesFichierBin("notesE3-bin");
		eva4.sauvegarderNotesFichierBin("notesE3-bin");
		eva5.sauvegarderNotesFichierBin("notesE3-bin");
		e3.lectureNoteFichierBin("notesE3-bin");
		e3.setMoyenne();
		System.out.println(e3);
		e2.sauvegarderElevesFichierBin();
		
		// Serialisation d'un Eleve
		Eleve sEleve = new Eleve("X","Y");
		sEleve.ajouterNote(eva1);
		sEleve.ajouterNote(eva2);
		sEleve.setMoyenne();
		System.out.println(sEleve);
		Eleve.serialisationEleve("serial-eleve", sEleve);
		// Déserialisation
		sEleve = null;
		sEleve = Eleve.deserialisationEleve("serial-eleve");
		System.out.println(sEleve);

		// Serialisation d'une Evaluation
		Evaluation sEva = new Evaluation(10.5f, 3.5f);
		System.out.println("\nAvant Serialisation : " + sEva);
		Evaluation.serialisationEvaluation("serial-eva", sEva);
		sEva = null;
		sEva = Evaluation.deserialisationEvaluation("serial-eva");
		System.out.println("Apres Serialisation : " + sEva);
		
	}
}
