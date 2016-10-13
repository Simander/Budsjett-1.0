import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;
public class UtgiftsListe implements Serializable 
{
	private static final long serialVersionUID = 1L;
	LinkedList<Utgifter> utgiftsliste;

	UtgiftsListe()
	{
		utgiftsliste = new LinkedList<>();
	}
	
	public LinkedList<Utgifter> getUtgiftsliste()
	{
		return utgiftsliste;
	}
	
	public void  settInnUtgift(Utgifter u)
	{
		utgiftsliste.add(u);
	}
    //metode som lister opp alle lærere
    protected String listUtgifter()
    {
        String s ="";
        ListIterator<Utgifter> uIter = utgiftsliste.listIterator();
        while (uIter.hasNext())
        {
            s += uIter.next().toString() +"\n\n";
        }        
        return s;
    }
    
    //metode som lister opp alle utgifter i tabell
    protected LinkedList<String[]> listTable()
    {
        LinkedList<String[]>  s = new LinkedList<>();
        ListIterator<Utgifter> uIter = utgiftsliste.listIterator();
        while (uIter.hasNext())
        {
           s.add(uIter.next().toArray());
        }        
        return s;
    }
    //sum utgifter
    public double sumExpenses()
    {
        double temp = 0.0;
       /* ListIterator<Utgifter> uIter = utgiftsliste.listIterator();
        while (uIter.hasNext())
        {
            d += uIter.next().getUCOST();
        }        
        return d;
        */
        for(int i= 0; i < utgiftsliste.size(); i++)
        {
        	if(utgiftsliste.get(i).getUFREQ().equals("Årlig"))
    		{
    			temp += (utgiftsliste.get(i).getUCOST())/12;
    		}
    		else
    			temp += utgiftsliste.get(i).getUCOST();
    	}
    	return temp;
        
    }
    
    public int sumMonthlyExpenses()
    {
    	int temp = 0;
    	for(int i = 0; i <utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUFREQ().equals("Årlig"))
    		{
    			temp += ((int) utgiftsliste.get(i).getUCOST())/12;
    		}
    		else
    			temp += (int) utgiftsliste.get(i).getUCOST();
    	}
    	return temp;
    }
    
    //metode som lagrer klassedata fra tabell i listeobjektene
    protected void saveDate(String[][] s)
    {
    	
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{

    			//utgiftsliste.get(i).setUID(s[i][0]);
    			utgiftsliste.get(i).setUType(s[i][0]);
    			utgiftsliste.get(i).setUFrequency(s[i][1]);
    			if(s[i][2].equals(""))
    				utgiftsliste.get(i).setUCOST(0.00);
    			else
    				utgiftsliste.get(i).setUCOST(Double.parseDouble(s[i][2]));
    			utgiftsliste.get(i).setUComment(s[i][3]);
    		

    	}
    }
    public double sumAlleHusleie()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("husleie"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    					temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleStrom()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("strøm"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleInternet()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("internett"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    	public double sumAlleTelefon()
        {
        	double temp = 0.0;
        	for(int i = 0; i < utgiftsliste.size(); i++)
        	{
        		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("telefon"))
        		{
        			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
        				temp += (utgiftsliste.get(i).getUCOST()/12);
        			else
        				temp += utgiftsliste.get(i).getUCOST();
        		}
        	}
        	return temp;
    }
    public double sumAlleMat()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("mat"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleKlede()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("klær"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleDyr()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("dyr"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleReiser()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("reise"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAllePersonlig()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("personlig"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleSparing()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("sparing"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleForsikring()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("forsikring"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleLån()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("lån"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public double sumAlleAnnet()
    {
    	double temp = 0.0;
    	for(int i = 0; i < utgiftsliste.size(); i++)
    	{
    		if(utgiftsliste.get(i).getUTYPE().equalsIgnoreCase("Annet"))
    		{
    			if(utgiftsliste.get(i).getUFREQ().equalsIgnoreCase("Årlig"))
    				temp += (utgiftsliste.get(i).getUCOST()/12);
    			else
    				temp += utgiftsliste.get(i).getUCOST();
    		}
    	}
    	return temp;
    }
    public void listIndexes()
    {
    	for(int i = 0; i < utgiftsliste.size(); i++)
    		System.out.println(i+"\n");
    }
    protected void fjernObjekt(int n)
    {
       utgiftsliste.remove(n);
      // System.out.println("Linje nr: " + n + ", er fjernet!");
    }
    
	 //Metode som returnerer et utgifts objekt med riktig nr
    /*public Utgifter finnUtgift(String n)
    {    
        ListIterator<Utgifter> uIter = utgiftsliste.listIterator();
        while (uIter.hasNext())
        {
            if(uIter.next().getUNr().equalsIgnoreCase(n))
                return uIter.previous();
         }
        return null;
        
    }*/
}
