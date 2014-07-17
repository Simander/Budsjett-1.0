import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

public class InntektsListe implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	LinkedList<Inntekter> inntektsliste;
	
	InntektsListe()
	{
		inntektsliste = new LinkedList<>();
	}
		
	public LinkedList<Inntekter> getUtgiftsliste()
	{
		return inntektsliste;
	}
		
	public void  settInnInntekt(Inntekter i)
	{
		inntektsliste.add(i);
	}
	//list-size
	public int listSize()
	{
		return inntektsliste.size();
	}
    //metode som lister opp alle inntekter
    protected String listInntekter()
    {
        String s ="";
        ListIterator<Inntekter> iIter = inntektsliste.listIterator();
        while (iIter.hasNext())
        {
            s += iIter.next().toString() +"\n\n";
        }        
        return s;
    }
    //metode som lister opp alle inntekter i tabell
    protected LinkedList<String[]> listTable()
    {
        LinkedList<String[]>  s = new LinkedList<>();
        ListIterator<Inntekter> iIter = inntektsliste.listIterator();
        while (iIter.hasNext())
        {
           s.add(iIter.next().toArray());
        }        
        return s;
    }
    
    
    //Sum inntekter
    public double sumIncome()
    {;
       double d = 0.0;
        ListIterator<Inntekter> iIter = inntektsliste.listIterator();
        while (iIter.hasNext())
        {
            d += iIter.next().getInntekt();
        }        
        return d;
    }
    protected void fjernObjekt(int n)
    {
       inntektsliste.remove(n);
      // System.out.println("Linje nr: " + n + ", er fjernet!");
    }
    //metode som lagrer klassedata fra tabell i listeobjektene
    protected void saveDate(String[][] s)
    {
    	
    	for(int i = 0; i < inntektsliste.size(); i++)
    	{
    		{
    			inntektsliste.get(i).setINr(s[i][0]);
    			inntektsliste.get(i).setNavn(s[i][1]);
    			inntektsliste.get(i).setType(s[i][2]);
    			if(s[i][3].equals(""))
    				inntektsliste.get(i).setInntekt("0.00");
    			else
    				inntektsliste.get(i).setInntekt(s[i][3]);
    			inntektsliste.get(i).setKommentar(s[i][4]);
    		}
    	}
    }
    //Metode som returnerer et utgifts objekt med riktig nr
    public Inntekter finnInntekt(String n)
    {    
        ListIterator<Inntekter> iIter = inntektsliste.listIterator();
        while (iIter.hasNext())
        {
            if(iIter.next().getINr().equalsIgnoreCase(n))
                return iIter.previous();
         }
        return null;
        
    }
}

