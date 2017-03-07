package staff;

/**
* StaffMember is the super class for other jobs
*/
public abstract class StaffMember
{
	protected String name, address, phone;

	/**
	* Constructor of StaffMember
	* @param eName the name
	* @param eAddress the address
	* @param ePhone the phone number
	*/
	public StaffMember(String eName, String eAddress, String ePhone)
	{
		if(testStringNN(eName, eAddress, ePhone))
		{
			this.name = eName;
			this.address = eAddress;
			this.phone = ePhone;
		}
		else
		{
			System.out.println("Les parametres doivent être non null");
		}
	}

	/**
	* Check if String in parameter are not null
	* @param test testing Strings
	* @return true if it's ok
	*/
	public static boolean testStringNN(String ... test)
	{
		boolean verif = true;
		
		for(int i = 0; i < test.length && verif; i++)
		{
			if(test[i] == null)
				verif = false;
		}

		return verif;
	}

	/**
	* Signature of pay method {abstract}
	* @return the payment
	*/
	public abstract double pay();

	/**
	* Give some infos on the worker
	* @return String containing infos
	*/
	public String toString()
	{
		String ret = "\nNom : " + this.name;
		ret += "\nAdresse : " + this.address;
		ret += "\nTel : " + this.phone;
		return ret;
	}
}