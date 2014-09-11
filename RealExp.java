import java.io.Serializable;

public class RealExp implements Serializable{

private static final long serialVersionUID = 1L;
	
	private int indeks = 0;
	private String exDate;
	private String uType;
	private String uFreq;
	private double uCost;
	private String uComment = "";
	
	public RealExp(String d, String uT, double uCo, String uCm)
	{
		/*
		if(indeks == 0xfffffff);
		{	indeks = 0;}
		*/
		exDate = d;
		uType = uT;
		//uFreq = uFr;
		uCost = uCo;
		uComment = uCm;
		System.out.println(indeks);
	}
	//get metode uNr
	//public String getUNr()
	//{
//		return uNr;
	//}
	public int getIndeks(){
		return indeks;
	}
	public void setIndeks(int i){
		indeks = i;
	}
	public String getExDate(){
		return exDate;
	}
	public String getDate(){
		return exDate;
	}
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
		String s = " \n\t\t|Indeks: " + indeks + "|Date: " + exDate + "\t|Utgiftstype: " + uType + 
				"\t|Kostnad: " + uCost + "\n\t\t|Kommentar :" + uComment;
		return s;
	}
	
	public String[] toArray()
	{
		String[] tableData = new String[5];
		tableData[0] = "" + indeks;
		tableData[1] = exDate;
		tableData[2] = uType;
		//tableData[3] = uFreq;
		tableData[3] = ""+uCost;
		tableData[4] = uComment;
		
		return tableData;
	}
	
	public String[] toArrayPrint()
	{
		String[] tableData = new String[4];
		tableData[0] = exDate;
		tableData[1] = uType;
		//tableData[3] = uFreq;
		tableData[2] = ""+uCost;
		tableData[3] = uComment;
		
		return tableData;
	}
}	
