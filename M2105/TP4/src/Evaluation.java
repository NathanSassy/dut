package model;

import java.time.*;
import java.time.format.*;

public class Evaluation
{
	private double note;
	private String matiere;
	private LocalDate date;

	public Evaluation(double note, String matiere, LocalDate date) throws EvaluationException
	{
		try
		{
			setNote(note);
			setMatiere(matiere);
			setDate(date);
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

	public void setDate(LocalDate date) throws EvaluationException
	{
		if(date != null && date.compareTo(LocalDate.now()) < 0)
			this.date = date;
		else
			throw new EvaluationException("Date d'evaluation invalide");
	}

	public double getNote()
	{
		return this.note;
	}

	public String getMatiere()
	{
		return this.matiere;
	}

	public LocalDate getDate()
	{
		return this.date;
	}
}