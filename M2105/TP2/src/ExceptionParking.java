public class ExceptionParking extends Throwable
{
	public ExceptionParking (String messageErreur)
	{
		super(messageErreur);
		/* On procede ainsi car le constructeur de Throwable
		nous permet de faire ce que l'on souhaite */
	}
}