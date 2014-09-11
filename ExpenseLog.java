import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ExpenseLog extends JPanel{
private static final long serialVersionUID = 1L;	//version ID for serializing
	
	private KeyListener listener;				//private keyListener
	private CheckListener checklistener;
	private LyttR lyttR;						//private listener
	private JTextField kommentar;				//JTextField for input of comments
	private JComboBox<String> uType;			//JComboBox for selecting types of expenses
//	private JComboBox<String> uFrequency;		//JComboBox for selecting frequency of expenses
	private JTextField uPrice;					//JTextField for input of amount of expenses
	private String[] typer = {"Husleie", "Strøm", "Internett", "Telefon", "Sparing", "Forsikring", "Lån", "Mat", "Klær", "Dyr", "Reise", "Personlig", "Annet"};  
//	private String[] frekvens = {"Månedlig", "Årlig"};
	private JTextField rDate;
	private JButton reg;
	private JButton search;
	private ExpLogList exList;
	private JPanel searchPanel;
	private JPanel inputPanel;
	private JTextArea info;
	private JTextField sDate;
	private JPanel topSplitter;
	private Thread searchThread;
	Runnable runSearch;
	private int max = 1000;
	private JProgressBar searchProg = new JProgressBar(0,max);
	private int temp = 0;
	int state = 0;
	//String dateFormater = "dd.MM.yyyy";
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	
	//protected RealExpList expList;				//List of expenses
	private JPanel top;									//Top-panel
	private JPanel bottom;								//Bottom-panel
	private JPanel displayContainer;
	private String [] modellcolumn = {"IndeksNr", "Dato", "Type", "Utgift", "Kommentar"};	//String array holding column-names for the table model
	protected JScrollPane scrollExpenseTable;							//JScrollPane for holding the expense-table
    protected DefaultTableModel modell = new DefaultTableModel(); 		//Default table model
    @SuppressWarnings("serial")
	protected JTable table = new JTable(modell){
        public boolean isCellEditable(int row, int column) {                
            return false;               
    };//Creating a new JTable from the table model
    };

	private Runnable animateProgressBar;
    

	ExpenseLog(ExpLogList rl) 
	{
		//System.out.println(dateFormatter.format(Calendar.getInstance().getTime()).substring(3));
		lyttR = new LyttR();							//initiates the listener
		listener = new MyKeyListener();					//initiates the keyListener
		checklistener = new CheckListener();
		table.addKeyListener(listener);					//adds the keyListener to the table
		exList = rl;										//initiates the expenses-list
		//runs through the array of collumn-names and adds them to the table
		for(int i = 0; i < modellcolumn.length; i++)
		{
			modell.addColumn(modellcolumn[i]);
		}
		/*
		modell.addColumn("Indeks:", new String[]{""});
		modell.addColumn("Date", new String[] {""});
		modell.addColumn("Type", new String[] {""});
		modell.addColumn("Frekvens", new String[] {""});
		modell.addColumn("Utgift", new String[]{""});//new Object[]{new Double(0.0)});
		modell.addColumn("Kommentar", new String[]{""});
		modell.setRowCount(0);
		*/
		

		table.addKeyListener(listener);
		

		top = new JPanel();								//initiates top-panel
		bottom = new JPanel();							//initiates bottom-panel
		kommentar = new JTextField(10);					//initiates the TextField for input of comments
		kommentar.setToolTipText("Valgfri kommentar");
		uType = new JComboBox<>(typer);					//initiates the JComboBox for selecting expense types
		uType.setToolTipText("Klikk for å velge utgiftstype");
	//	uFrequency = new JComboBox<>(frekvens);			//initiates the JComboBox for selecting frequency of the expenses
	//	uFrequency.setToolTipText("Klikk for å velge hyppighet");
		uPrice = new JTextField(10);					//initiates the JTextField for input of expenses
		uPrice.addKeyListener(checklistener);
		uPrice.setToolTipText("Utgift i kr: bruk punktum for komma");
		reg = new JButton("Registrer");					//initiates the JButton for triggering the registration event
		reg.setToolTipText("Klikk for å loggføre ny reell utgift");
		reg.addActionListener(lyttR);					//adds the listener to the button
		rDate = new JTextField(10);
		rDate.setToolTipText("Skriv inn dato på formen: dag.måned.år");
		rDate.addKeyListener(checklistener);
		top.add(new JLabel("Date: "));
		top.add(rDate);
		top.add(new JLabel("Type: "));					//adds a JLabel to the top-panel
		top.add(uType);									//adds the JComboBox for selecting expense types
	//	top.add(new JLabel("Frekvens: "));				//adds a JLabel to the top-panel
	//	top.add(uFrequency);							//adds the JComboBox for selecting frequency of the expenses
		top.add(new JLabel("Kostnad: "));				//adds a JLabel to the top-panel
		top.add(uPrice);								//adds the JTextField for input of expenses to the top-panel
		top.add(new JLabel("Kommentar: "));				//adds a JLabel to the top-panel
		top.add(kommentar);								//adds the JTextField for input of comments to the top-panel
		top.add(reg);									//adds the JButton for triggering registration events to the top-panel
		
		scrollExpenseTable = new JScrollPane(table);	//initiates the JScrollPane containing the expense-table
		scrollExpenseTable.setPreferredSize(new Dimension(1024,400));	//sets the size of the ScrollPane
					//adds the JScrollPane to the bottom-panel
		setLayout(new BorderLayout(5,5));					//sets the layout of this panel to BorderLayout
		table.setToolTipText("Hvis linje er valgt, trykk delete for å slette linje");
			info = new JTextArea(3
					,80);
			displayContainer = new JPanel();
			displayContainer.add(info);
			topSplitter = new JPanel();
			topSplitter.setLayout(new BorderLayout());
			topSplitter.add(top, BorderLayout.PAGE_START);
			topSplitter.add(displayContainer, BorderLayout.CENTER);
		
			searchPanel = new JPanel();
			inputPanel = new JPanel();
				sDate = new JTextField(10);
				sDate.setToolTipText("Skriv en dato på formen dag.måned.år");
				sDate.addKeyListener(checklistener);
				search = new JButton("Søk");
				search.setToolTipText("Klikk for å søke etter oppføringer i samme måned");
				search.addActionListener(lyttR);
				inputPanel.add(new JLabel("Dato"));
				inputPanel.add(sDate);
				inputPanel.add(search);
				searchPanel.setLayout(new BorderLayout());
				searchPanel.add(inputPanel, BorderLayout.PAGE_START);
				searchPanel.add(scrollExpenseTable, BorderLayout.CENTER);
				searchPanel.add(searchProg, BorderLayout.PAGE_END);
				bottom.add(searchPanel);		
		add(topSplitter, BorderLayout.PAGE_START);				//adds the top-panel to the top of this panel
		inputPanel.setBackground(Color.LIGHT_GRAY);
		//info.setBackground(Color.GREEN);
		info.setPreferredSize(new Dimension(50,100));
		info.setEditable(false);
		add(bottom, BorderLayout.LINE_START);			//adds the bottom panel to the bottom of this panel
		runSearch = new Runnable(){
			public void run(){
				synchronized(this){
					while(true){
						searchProg.setStringPainted(true);
						searchProg.setString("Laster..");
						searchProg.setIndeterminate(true);
						findByDate(sDate.getText());
						searchProg.setString("Ferdig lastet!");
						searchProg.setIndeterminate(false);
						break;
					}
		}
					}};
		animateProgressBar = new Runnable(){
			public void run(){
				synchronized(this){
					while(true){
					//	if(running = true){
							//searchProg.setValue(0);
							while(temp < max){
								temp += 5;
								try {
									Thread.sleep(10);
								}
								catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} searchProg.setValue(temp);
								if(searchProg.getValue() == max)
									break;
					
							}
							break;
						
						}
					}
				}
			
		};
	}
	//method that checks and corrects input date to the right format
	public void check(){
		//registrering av dato
		String inputDate = rDate.getText();
		if(inputDate.length() == 2)
		{
			rDate.setText(inputDate + ".");
		}
		if(inputDate.length() == 5)
		{
			rDate.setText(inputDate + ".");
		}
		if(inputDate.length() == 10)
		{
			rDate.getText();
		//	rDate.
			rDate.setEditable(false);
		}
		else if(inputDate.length()< 10)
		{
			rDate.setEditable(true);
		}
		
		//søkedato
		String searchDate = sDate.getText();
		if(searchDate.length() == 2)
		{
			sDate.setText(searchDate + ".");
		}
		if(searchDate.length() == 5)
		{
			sDate.setText(searchDate + ".");
		}
		if(searchDate.length() == 10)
		{
			sDate.getText();
		//	rDate.
			sDate.setEditable(false);
		}
		else if(searchDate.length()< 10)
		{
			sDate.setEditable(true);
		}
		
		
		//moneyCheck
		if(uPrice.hasFocus())
			uPrice.setEditable(true);
		
		//rDate.getInputVerifier()
		
	
		//if(inputDate.l)
		
	}
	public void setState(int s){
		state = s;
		if(state == 0){
			synchronized(ExpenseLog.this){
			try {
				
				searchThread.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		else if (state == 1){
			synchronized(ExpenseLog.this){
				notifyAll();
			}
		}
			
		//	notifyAll();
		//}
	}
	//method for registering new expenses
	public void register()
	{
		
		if(uPrice.getText().isEmpty() || rDate.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "Vennligst fyll ut alle felter!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		else if(rDate.getText().length()<10)
			JOptionPane.showMessageDialog(null, "Feil innfylt dato!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		else
		{	
			try{
				String exDate = rDate.getText();
				String utype = (String)uType.getSelectedItem();
			//	String ufreq = (String)uFrequency.getSelectedItem();
				String tempincome = commaCheck(uPrice.getText());
				double inCome = Double.parseDouble(tempincome);
				String icomment = kommentar.getText();
				RealExp u = new RealExp(exDate, utype, inCome, icomment);
			//	info.setText("\tUtgift lagt til i listen:\n " +u.toString());
				info.setText("\tUtgift lagt til i listen:\n " + exList.addExpenseToLog(u));
				//info.setText(iList.listInntekter());
			//	Object[] temp= new Object[5];
			/*	temp[0] = exDate;
				temp[1] = utype;
				temp[2] = ufreq;
				temp[3] = tempincome;
				temp[4] = icomment;
				modell.addRow(temp);*/
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "Ugyldig tallverdi i utgift!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
			} 
		}
	}
	//method that returns all logged entries that matches the incoming parameter
	public void findByDate(String d){
				String temp = d;
				modell.setRowCount(0);
				String incoming = temp.substring(3);
				System.out.println(incoming);
				for(int i = 0; i < exList.expLogList.size(); i++)
				{
					temp = exList.expLogList.get(i).getDate().substring(3);
					if(temp.matches(incoming))
						modell.addRow(exList.expLogList.get(i).toArray());
			
				}
	}
	
    	public void calcDate(){
        	String temp = dateFormatter.format(Calendar.getInstance().getTime());
        	double cache = 0.0;
        	String incoming = temp.substring(3);
        	System.out.println(incoming);
        	for(int i = 0; i < exList.expLogList.size(); i++)
        	{
        		temp = exList.expLogList.get(i).getDate().substring(3);
        		if(temp.matches(incoming))
        			//if(exList.expLogList.get(i).getUTYPE().equalsIgnoreCase("Husleie")
        				cache += exList.expLogList.get(i).getUCOST();
        			
        	}
        	System.out.println(cache);
    	
    }
	public ExpLogList getList()
	{
		return exList;
	}
	public void addToTable()
	{
		LinkedList<String[]> lista = exList.listTable();
		for(int i = 0; i < lista.size(); i++)
		{
			modell.addRow(lista.get(i));
		}
	}
	//method that calls the deleteEntry() method from the ExpLogList on the selected table-entry
	public void remove()
	{
		try{
			if(table.getSelectedRow()!= -1){
		int col = table.getSelectedRow();
		
		int indexOfCell = Integer.parseInt((String)table.getValueAt(col, 0));
		//int indexOfCell = table.get
		String deleteMsg = exList.deleteEntry(indexOfCell);
		
		modell.removeRow(col);

		info.setText("\tUtgiftslogg fjernet:\n" + deleteMsg);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public String commaCheck(String b)
	{
		String temp ="";
		if(b.contains(","))
		{
			temp = b.replace(",",".");
		}
		else temp = b;
		return temp;
	}
	

	//method that compares a string date to the date now
	public boolean compareDate(String d){
		Date thisDate = Calendar.getInstance().getTime();
		String now = dateFormatter.format(thisDate);
		String sub = now.substring(3);
		if(sub.matches(d))
			return true;
		else
			return false;
	}
    //privat lytteklasse
    private class LyttR implements ActionListener
    {
    	
    	
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == reg)
                 register();
             if(e.getSource() == search){
            	// findByDate(sDate.getText());
            //	 searchThread = new Thread(runSearch);
            //	 searchThread.start();
            	// notify();
            	// notifyAll();
            	// setState(1);
            	 searchProg.setValue(0);
            		searchThread = new Thread(runSearch);
            	 new Thread(animateProgressBar);
         	
         	//	progressThread.start();
         		searchThread.start();
            	
            	 
            
            	
             }
    
        }
    }
    private class CheckListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			switch(e.getKeyCode()){
			
			case 49:	//0
				check();
				break;
			case 50:	//1
				check();
				break;
			case 51:	//2
				check();
				break;
			case 52:	//3
				check();
				break;
			case 53:	//4
				check();
				break;
			case 54:	//5
				check();
				break;
			case 55:	//6
				check();
				break;
			case 56:	//7
				check();
				break;
			case 57:	//8
				check();
				break;
			case 48:	//9
				check();
				break;
			case 8:	//backspace
				rDate.setEditable(true);
				sDate.setEditable(true);
				uPrice.setEditable(true);
			//	exList.calculateMonthlyExpenses();
			//	exList.calculateMonthlyExpenses();
				break;
			case KeyEvent.VK_DELETE:
				rDate.setEditable(true);
				sDate.setEditable(true);
				uPrice.setEditable(true);
				break;
			case 46:
				if(rDate.getText().length()==2 || rDate.getText().length()==5)
					break;
				if(sDate.getText().length()==2 || sDate.getText().length()==5)
					break;
				if(uPrice.getText().length() > 0)
					break;
			default:
				rDate.setEditable(false);
				sDate.setEditable(false);
				uPrice.setEditable(false);
				break;
		
				}
			}
			//if(e.getKeyCode()==KeyEvent.)
			
		

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
	private class MyKeyListener implements KeyListener {
		boolean cmd = false;
		boolean d = false;
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_DELETE)
				remove();
			if(e.getKeyCode() == 157)// || e.getKeyCode() == 68)
			{
				cmd = true;
			//	if(check == 2)
				//	remove();
				/*en += 1;
				if(en >1 && check == 0)
				{
					remove();
					check+=1;
				}
				*/
			}
			if(e.getKeyCode() == 68)
			{
				
				d = true;
				
				/*
			
				en+=1;
				if (en > 1 && check == 0)
					remove();
					check +=1;
				*/
			}
			if(cmd == true && d == true)
			{	remove();
			
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == 157)// || e.getKeyCode() == 68)
			{
				cmd = false;
				/*en -= 1;
				if(check > 0)
					check = 0;*/
			}
			if(e.getKeyCode() == 68)
			{
				d = false;
				/*
				en-=1;
				if(check > 0)
					check = 0;
					*/
			}
			
		}
	}

}
