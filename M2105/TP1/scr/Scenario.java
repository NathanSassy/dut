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
		Evaluation eva4 = new Evaluation(11, 0);
		Evaluation eva5 = new Evaluation(6, 0);
		
		// Notes en memoire
		e1.ajouterNote(eva1);
		e1.ajouterNote(eva2);
		e1.ajouterNote(eva3);
		e1.setMoyenne();
		System.out.println(e1);
		
		// Notes dans un fichier
		eva1.sauvegarderNotesFichier("noteEva2");
		eva2.sauvegarderNotesFichier("noteEva2");
		eva3.sauvegarderNotesFichier("noteEva2");
		e2.lectureNoteFichier("noteEva2");
		e2.setMoyenne();
		e2.sauvegarderElevesFichier();
		System.out.println(e2);
		
		// Coef nul
		e3.ajouterNote(eva4);
		e3.ajouterNote(eva5);
		e3.setMoyenne();
		System.out.println(e3);
		
		
	}
}
