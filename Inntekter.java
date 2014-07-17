import java.io.Serializable;
public class Inntekter implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private static int i = 0;
	String arbeiderNavn = "";
	String iType = "";
	double månedliginntekt;
	String iID = "";
	String kommentar = "";
	
	Inntekter(String n, String t, double m, String k)
	{
		iID = "I" + i;
		i++;
		arbeiderNavn = n;
		iType = t;
		månedliginntekt = m;
		kommentar = k;
	}
	public String getINr()
	{
		return iID;
	}
	public String getNavn()
	{
		return arbeiderNavn;
	}
	public String getType()
	{
		return iType;
	}
	public double getInntekt()
	{
		return månedliginntekt;
	}
	public String getKommentar()
	{
		return kommentar;
	}
	public void setINr(String i)
	{
		iID = i;
	}
	public void setNavn(String n)
	{
		arbeiderNavn = n;
	}
	public void setType(String t)
	{
		iType = t;
	}
	public void setInntekt(String in)
	{
		månedliginntekt = Double.parseDouble(in);
	}
	public void setKommentar(String k)
	{
		kommentar = k;
	}
	public String toString()
	{
		String s = "InntektsID: " + iID + "\nNavn: " + arbeiderNavn + "\nType: " + iType +
				"\nInntekt: " + månedliginntekt  + "\nKommentar: " + kommentar;
		return s;
	}
	public String[] toArray()
	{
		String[] tableData = new String[5];
		tableData[0] = iID;
		tableData[1] = arbeiderNavn;
		tableData[2] = iType;
		tableData[3] = ""+månedliginntekt;
		tableData[4] = kommentar;
		
		return tableData;
	}
}
