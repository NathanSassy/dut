package model;

public class Evaluation
{
	private double note;
	private String matiere;

	public Evaluation(double note, String matiere) throws EvaluationException
	{
		try
		{
			setNote(note);
			setMatiere(matiere);
		}
		catch(EvaluationException e)
		{
			throw e;
		}
	}

	public void setNote(double note)
	{
		this.note = note;
	}

	public void setMatiere(String matiere) throws EvaluationException
	{
		if(matiere != null)
			this.matiere = matiere;
		else
			throw new EvaluationException("Matiere invalide");
	}

	public double getNote()
	{
		return this.note;
	}

	public String getMatiere()
	{
		return this.matiere;
	}
}