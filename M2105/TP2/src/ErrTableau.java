public class ErrTableau extends Exception
{
	public ErrTableau(int err)
	{
		super("Taille de tableau invalide : " + err);
		printStackTrace();
	}

	public ErrTableau(String err)
	{
		super(err);
		printStackTrace();
	}
}
