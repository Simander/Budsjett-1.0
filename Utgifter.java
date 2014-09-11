import java.io.Serializable;


public class Utgifter implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//private static int i = 0;
	//private String uNr = "";
	private String uType = "";
	private String uFreq = "";
	private double uCost = 0.00;
	private String uComment = "";
	
	public Utgifter(String uT, String uFr, double uCo, String uCm)
	{
	//	uNr = "U"+i;
		//i++;
		uType = uT;
		uFreq = uFr;
		uCost = uCo;
		uComment = uCm;
	}
	//get metode uNr
	//public String getUNr()
	//{
//		return uNr;
	//}
	//get-metode for utgiftstype
	public String getUTYPE()
	{
		return uType;
	}
	//get-metode for utgiftsfrekvens
	public String getUFREQ()
	{
		return uFreq;
	}
	//get-metode for utgiftskostnad
	public double getUCOST()
	{
		return uCost;
	}
	//get-metode for utgiftskommetar
	public String getUCOMMENT()
	{
		return uComment;
	}
	/*//set-metode UNr
	public void setUID(String s)
	{
		uNr = s;
	}*/
	//set-metode for utgiftstype
	public void setUType(String s)
	{
		uType = s;
	}
	//set-metode for utgiftsfrekvens
	public void setUFrequency(String s)
	{
		uFreq = s;
	}
	//set-metode for utgiftskostnad
	public void setUCOST(double d)
	{
		uCost = d;
	}
	//set-metode for utgiftskommentar
	public void setUComment(String s)
	{
		uComment = s;
	}
	
	public String toString()
	{	
		String s = "Utgiftstype: " + uType + "\nFrekvens: " + uFreq + 
				"\nKostnad: " + uCost + "\nKommentar :" + uComment;
		return s;
	}
	
	public String[] toArray()
	{
		String[] tableData = new String[4];
		tableData[0] = uType;
		tableData[1] = uFreq;
		tableData[2] = ""+uCost;
		tableData[3] = uComment;
		
		return tableData;
	}
}
