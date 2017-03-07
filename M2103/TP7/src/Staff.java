package staff;
import java.util.ArrayList;
import java.util.Iterator;

/**
* Staff class allows you to manipulate StaffMember list
*/
public class Staff
{
	ArrayList<StaffMember> staffList;

	/**
	* Construcot - Init the ArrayList
	*/
	public Staff()
	{
		staffList = new ArrayList<StaffMember>();
	}

	/**
	* Add a new member to the staff list
	* @param member the member you want to add
	*/
	public void addNewMember(StaffMember member)
	{
		if(member != null)
		{
			this.staffList.add(member);
		}
	}

	/**
	* Get a Staff Member from its index
	* @param index the index
	* @return the staff member
	*/
	public StaffMember getMember(int index)
	{
		StaffMember sm = null;
		if(index <= this.staffList.size() && index >= 0)
		{
			sm = this.staffList.get(index);
		}
		return sm;
	}

	/**
	* Print the payment for every
	* staff member in the staff list
	*/
	public void payday()
	{
		double paye = 0;
		for(StaffMember sm : this.staffList)
		{
			System.out.println(sm);
			paye = sm.pay();
			if(paye == 0)
			{
				System.out.println("\nThanks !");
			}
			else
			{
				System.out.println("\nPaid : " + paye);
			}
			System.out.println("\n-----------------------------------");
		}
	}
}