package staff;

/**
* Volunteer class allows you to manipulate Volunteer
*/
public class Volunteer extends StaffMember
{

	/**
	* Constructor of Volunteer
	* @param eName the name
	* @param eAddress the address
	* @param ePhone the phone number
	*/
	public Volunteer(String eName, String eAddress, String ePhone)
	{
		super(eName, eAddress, ePhone);
	}

	/**
	* Give the pay
	* @return return 0 because the statut of volunteer
	*/
	public double pay()
	{
		return 0;
	}
}