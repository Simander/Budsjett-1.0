import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
public class ExpLogList implements Serializable 
{
	private static final long serialVersionUID = 1L;
	LinkedList<RealExp> expLogList;
	String dateFormater = "dd.MM.yyyy";
	
	private int indeks = 0;
	//SimpleDateFormat formatterOfDate = new SimpleDateFormat(dateFormater);
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	private double[] expCategories = new double[13];
	ExpLogList()
	{
	
		expLogList = new LinkedList<>();
		for(int i = 0; i < expCategories.length; i++)
		{
			expCategories[i] = 0;
		}
	//	calculateMonthlyExpenses();
		
	}
	
	public LinkedList<RealExp> getUtgiftsliste()
	{
		return expLogList;
	}
	
	public String  addExpenseToLog(RealExp u)
	{
		u.setIndeks(indeks);
		expLogList.add(u);
		if(indeks == 0xfffffff){
			indeks = 0;
		}
		else{
			indeks ++;
		}
		return u.toString();
	}
    //metode som lister opp alle lærere
    protected String ListExpLogList()
    {
        String s ="";
        ListIterator<RealExp> uIter = expLogList.listIterator();
        while (uIter.hasNext())
        {
            s += uIter.next().toString() +"\n\n";
        }        
        return s;
    
    }
    
    
    protected double[] getCalcCosts(){
    	
    	return expCategories;
    }
    //metode som lister opp alle utgifter i tabell
    protected LinkedList<String[]> listTable()
    {
        LinkedList<String[]>  s = new LinkedList<>();
        ListIterator<RealExp> uIter = expLogList.listIterator();
        while (uIter.hasNext())
        {
           s.add(uIter.next().toArray());
        }        
        return s;
    }
	//method that compares a string date to the date now
	public boolean compareDate(String d){
		Date thisDate = Calendar.getInstance().getTime();
		String now = dateFormatter.format(thisDate);
		System.out.println("now");
		String sub = now.substring(3);
		if(sub.matches(d.substring(3)))
			return true;
		else
			return false;
	}
	public int exlogSize(){
		return expLogList.size();
	}
    //sum utgifter
    public double sumExpenses()
    {
        double temp = 0.0;
      
        for(int i= 0; i < expLogList.size(); i++)
        {
        	if(compareDate(expLogList.get(i).getExDate().substring(3))==true){
        		if(expLogList.get(i).getUFREQ().equals("Årlig"))
        		{
        			temp += (expLogList.get(i).getUCOST())/12;
        		}	
        		else
        			temp += expLogList.get(i).getUCOST();
        	}
    	}
    	return temp;
        
    }
    
    public int sumMonthlyExpenses()
    {
    	int temp = 0;
    	//try{
    	String thisDate = dateFormatter.format(Calendar.getInstance().getTime());
    	for(int i = 0; i <expLogList.size(); i++)
    	{
    		if((thisDate.substring(3)).equalsIgnoreCase(expLogList.get(i).getDate().substring(3)))
    			temp += (int) expLogList.get(i).getUCOST();
    	}
    	return temp;
    }
    
   
    //Metode for å slette en logg med riktig indeks, gir en string med tilbakemelding til bruker
    protected String deleteEntry(int n)
    {
    	String returnValue = "Feil!";
    	for(int i = 0; i < expLogList.size(); i++)
    	{
    		if(expLogList.get(i).getIndeks() == n){
    			returnValue = expLogList.get(i).toString();
    			expLogList.remove(i);
    			return returnValue;
    		}
    	}
    	return returnValue;
    }
    public int listSize(){
    	return expLogList.size();
    }
    //Method for deleting logged expenses older than 1 year
    protected String deleteOld(int n)
    {
    	int i = 0;
    	int logYear = 0;
    	
    	boolean bool = false;
    	//løkke som løper gjennom hele lista
    	while( i< expLogList.size())
    	{
    		//Stores the logged year of the listobject in the variable logYear as int
    		logYear = Integer.parseInt(expLogList.get(i).getDate().substring(6));
    		//Checks if the time since the expense was logged is greater than 1 year
    		if((n - logYear) >= 1){
    				expLogList.remove(i);	//removes the list object
    				bool = true;	//changes returnbool
    		}
    		else
    			i++;	//if the loggedExpense-Object is under a year old, we skip to the next entry
    	}
    	if(bool == false)	//Message returned if list-elements was not deleted
    		return "ingenting å fjerne";
    	else	//Message returned if list-elements was deleted
    		return "Slettet gamle!";
    }
}
