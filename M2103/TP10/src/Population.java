package pays;

import utilitaire.*;
import java.io.*;
import java.util.*;

/**
 * * This class allows you to manipulate population object
 */
 
public class Population
{
	private ArrayList<Pays> listePays;
	private HashMap<String,Double> popMap;
	private HashMap<String,Double> areaMap;
	
	/**
	* Constructor of Population
	* @param popFileName the file containing the names and the populations of the countries
	* @param areaFileName the file containing the names and the areas of the countries
	*/
	public Population(String popFileName, String areaFileName)
	{
		
		if(popFileName != null && areaFileName != null)
		{
			this.initialysePopMap( popFileName );
			this.initialyseAreaMap( areaFileName );
			this.listePays = new ArrayList<Pays>();
			this.initialyseListePays();
			
		}
	}

	/**
	* @return the density per country
	*/
	public HashMap<String,Double> calculateDensity()
	{
		HashMap<String,Double> densityMap = new HashMap<String,Double>();
		Double density = 0.0;

		for(String key : this.popMap.keySet())
		{
			density = popMap.get(key) / areaMap.get(key);
			densityMap.put(key, density);
		}

		return densityMap;
	}

	/**
	* @return the country with the biggest density
	*/
	public String getMaxDensity()
	{
		String biggest = null;
		double maxDensity = 0.0;
		HashMap<String,Double> densityMap =  calculateDensity();

		for (Map.Entry<String, Double> m : densityMap.entrySet())
		{
		    if(maxDensity >= m.getValue())
		    {
		    	maxDensity = m.getValue();
		    	biggest =  m.getKey();
		    }
		}

		return biggest;
	}
	
	/**
	* Extract the name of the country into a String
	* @param line the String containing the name of the country
	* @return the name of the country
	*/	
	private String extractCountry(String line)
	{
		
		int i = 0;
		
		if(line != null)
		{
			while(!(Character.isDigit(line.charAt(i))) && i < line.length())
			{	
				i++;
				
			}
		}	
		return line.substring(0,i).trim();
	}

	/**
	* Extract the number into a String
	* @param line the String containing the numer
	* @return the value extracted
	*/
	private double extractValue(String line)
	{
		int i = 0;
		if(line != null)
		{
			
			while(!Character.isDigit(line.charAt(i)) && i < line.length())
			{
				i++;
				
			}
		}
		return Double.parseDouble(line.substring(i).trim());
	}

	/**
	* Initialize the popMap attribute
	* @param popFile the file containing the names and the populations of the countries
	*/
	private void initialysePopMap(String popFile)
	{
		ArrayList<String>popArray = RWFile.readFile(popFile);
		this.popMap = this.asMap(popArray);
	}
	
   /**
	* Initialize the areaMap attribute
	* @param areaFile the file containing the names and the areas of the countries
	*/	
	private void initialyseAreaMap(String areaFile)
	{
		ArrayList<String>areaArray = RWFile.readFile(areaFile);
		this.areaMap = this.asMap(areaArray);
	}
	
	/**
	* Initialize the attribute listePays, use the attributes popMap and areaMap
	*/
	private void initialyseListePays()
	{
		
		Set<String> nomPays = popMap.keySet();
		if(popMap.size() == areaMap.size())
		{
			for(String nom : nomPays)
			{
				listePays.add(new Pays(nom, areaMap.get(nom), popMap.get(nom)));
			}
		}
	}
	
	/**
	* Create HashMap whith the names and the values from an ArrayList of String.
	* @param liste ArrayList of String
	* @return the new HashMap
	*/
	private HashMap<String,Double> asMap(ArrayList<String> liste)
	{
		HashMap<String,Double> map = new HashMap<String,Double>();
		
		if(liste != null)
		{
			for(String line : liste)
			{
				map.put(this.extractCountry(line), this.extractValue(line));
			}
		}
		return map;
	}
	
	/**
	* @return the HashMap of popMap
	*/
	public HashMap<String,Double> getPopMap()
	{
		return this.popMap;
	}
	
	/**
	* @return the HashMap of areaMap
	*/
	public HashMap<String,Double> getAreaMap()
	{
		return this.areaMap;	
	}
	
	/**
	* @return the ArrayList of Pays
	*/
	public ArrayList<Pays> getListePays()
	{
		return listePays;
	}

}
