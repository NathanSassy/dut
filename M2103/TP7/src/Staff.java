package staff;
import java.util.ArrayList;
import java.util.Iterator;

public class Staff
{
	ArrayList<StaffMember> staffList;

	public Staff()
	{
		staffList = new ArrayList<StaffMember>();
	}

	public void addNewMember(StaffMember member)
	{
		if(member != null)
		{
			this.staffList.add(member);
		}
	}

	public StaffMember getMember(int index)
	{
		StaffMember sm = null;
		if(index <= this.staffList.size() && index >= 0)
		{
			sm = this.staffList.get(index);
		}
		return sm;
	}

	public void payday()
	{
		double paye = 0;
		for(StaffMember sm : this.staffList)
		{
			System.out.println(sm);
			paye = sm.pay();
			if(paye == 0)
			{
				System.out.println("Thanks !");
			}
			else
			{
				System.out.println("Paid : " + paye);
			}
			System.out.println("-----------------------------------");
		}
	}
}