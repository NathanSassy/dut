package staff;

public abstract class StaffMember
{
	protected String name, address, phone;

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

	public abstract double pay();

	public String toString()
	{
		String ret = "\nNom : " + this.name;
		ret += "\nAdresse : " + this.address;
		ret += "\nTel : " + this.phone;
		return ret;
	}
}