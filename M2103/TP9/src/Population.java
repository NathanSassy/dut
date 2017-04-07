package pays;

import utilitaire.RWFile;
import java.util.*;
import java.io.*;

public class Population
{
	private ArrayList <Pays> listePays;
	private HashMap <String, Double> popMap;
	private HashMap <String, Double> areaMap;
	
	public Population(String popFileName, String areaFileName)
	{
		
		if((popFileName != null) && (areaFileName != null))
		{	
			this.initialyseListePays();
			this.initialyseAreaMap( areaFileName );
			this.initialysePopMap( popFileName );	
		}	
	}
	
	private String extractCountry(String line)
	{
		int i = 0;
		if(line != null)
		{
			
			while ( (!Character.isDigit(line.charAt(i))) && ( i < line.length()))
				i++;
		}	
		return line.substring(i).trim();
	}

	private double extractValue(String line)
	{
		int i = 0;
		if( line != null )
		{
			
			while ( !Character.isDigit(line.charAt(i)) && ( i < line.length()))
				i++;
		}
		return Double.parseDouble( line.substring(i).trim());
	}
	
	private void initialysePopMap(String popFile)
	{	
		ArrayList<String>popArray = RWFile.readFile(popFile);
		this.popMap = this.asMap(popArray);
	}
	
	private void initialyseAreaMap(String areaFile)
	{
		ArrayList<String>areaArray = RWFile.readFile(areaFile);
		this.areaMap = this.asMap(areaArray);		
	}
	
	private void initialyseListePays()
	{
		Set<String> nomPays = popMap.keySet();
		if(popMap.size() == areaMap.size())
		{
			
			for (String nom : nomPays)
			{
				listePays.add(new Pays(nom, popMap.get(nom), areaMap.get(nom)));
			}
		}
	}
	
	private HashMap<String, Double> asMap(ArrayList<String> liste)
	{
		HashMap<String, Double> map = new HashMap<String, Double>();
		if (liste != null)
		{
			
			for (String line : liste)
			{	
				map.put( this.extractCountry(line), this.extractValue(line) );	
			}
		}
		
		return map;
	}
	
	public HashMap<String,Double> getPopMap()
	{	
		return this.popMap;	
	}
	
	public HashMap<String,Double> getAreaMap()
	{	
		return this.areaMap;	
	}
	
	public ArrayList<Pays> getListePays()
	{	
		return this.listePays;	
	}
}