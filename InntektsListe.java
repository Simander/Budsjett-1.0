import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

public class InntektsListe implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Inntekter> inntektsliste;
	
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
    
    //Method that delete a list-object from the list
    protected void deleteObject(int n)
    {
       inntektsliste.remove(n);		//removes the index n from the list.
    }
    //metode som lagrer klassedata fra tabell i listeobjektene
    protected void saveDate(String[][] s)
    {
    	
    	for(int i = 0; i < inntektsliste.size(); i++)
    	{
    		{
    		//	inntektsliste.get(i).setINr(s[i][0]);
    			inntektsliste.get(i).setNavn(s[i][0]);
    			inntektsliste.get(i).setType(s[i][1]);
    			if(s[i][2].equals(""))
    				inntektsliste.get(i).setInntekt("0.00");
    			else
    				inntektsliste.get(i).setInntekt(s[i][2]);
    			inntektsliste.get(i).setKommentar(s[i][3]);
    		}
    	}
    }
}

