import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Oversikt extends JPanel implements ActionListener, Printable
{
	private static final long serialVersionUID = 1L;	//serialiserings ID
	
	private LyttR lyttR = new LyttR();	//privat lytteklasse
	//Data-lists
	private InntektsListe iList;		//income-list
	private UtgiftsListe uList;			//expenses-list
	private ExpLogList exList;
	
	Graph3print graph3print; 
	//view1
	private JLabel header = new JLabel("Budsjettrapport");	//Header that gives the user an idea of what is displayed
	//JTextArea info1;								//info JTextArea for printing basic budget info	
	protected Graph1 graf1;									//PieChart for displaying the basic relationship between income and expenses
	//view2
	private JLabel header2 = new JLabel("Månedlige utgifter");	//Header that gives the user an idea of what is displayed
	protected JTextArea info2;									//info2 JTextArea for printing more advanced budget info
	protected JTextArea info3;
	protected Graph2 graf2;										//PieChart for displaying the different types of expenses
	//toggle
	JButton toggleSwitch;		//JButton for toggling between view one and view two
						//Where view one consists of info1 and graf1, and view two consists of info2 and graf2.
	//panels
	private JPanel topPanel;		//JPanel for holding the headers
	private JLayeredPane midPanel;	//JPanel for holding the different views
	private JPanel bottomPanel;		//JPanel for holding the toggleSwitch for switching views
	
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	private double [] expCategories = new double[13];
	
	
	//DesimalFormat
	private String decimalPattern = "#0.00";							//String format for formatting doubles.
	DecimalFormat dFormat = new DecimalFormat(decimalPattern);	//Desimalformat
	
	//Constructor
	Oversikt(ExpLogList ex, InntektsListe il, UtgiftsListe ul)
	{
		//this.setBackground(Color.WHITE);
		
		//listeklasser
		iList = il;
		uList = ul;
		exList = ex;
		
		//ToggleSwitch init
		toggleSwitch = new JButton("Detaljert");
		toggleSwitch.addActionListener(lyttR);
		toggleSwitch.setPreferredSize(new Dimension(300, 40));
		
		//view1 init
		header.setFont(new Font("Sans-Serif", Font.PLAIN, 40));
		graf1 = new Graph1();
		
		graf1.setVisible(true);
		graf1.setPreferredSize(new Dimension(400,400));
		graf1.setBounds(0,0, 1024,400);
		graf1.setLayout(new FlowLayout());
		//info1 = new JTextArea(20,30);
		//info1.setEditable(false);
		//info1.setOpaque(false);
		//info1.setVisible(false);	//should be true
		//info1.setPreferredSize(new Dimension(40, 40));
		//info1.setBounds(600, 0, 300,70);
		
		
		//view2 init
		header2.setFont(new Font("Sans-Serif", Font.PLAIN, 40));
		header2.setVisible(false);
		graf2 = new Graph2();
		graf2.setPreferredSize(new Dimension(1024, 400));
		graf2.setBounds(0,0,1024,400);
		graf2.setVisible(false);
		info2 = new JTextArea(10, 40);
		info2.setEditable(false);
		info2.setOpaque(true);
		info2.setVisible(false);
		info2.setPreferredSize(new Dimension(20, 40));
		info2.setBounds(710-150, 30, 150, 300);
		info3 = new JTextArea(20,40);
		info3.setOpaque(true);
		info3.setVisible(false);
		info3.setPreferredSize(new Dimension(20, 40));
		info3.setBounds(865-150, 30, 180, 300);
		//JPanels init
		topPanel = new JPanel();
		midPanel = new JLayeredPane();
		bottomPanel = new JPanel();
		midPanel.setVisible(true);
		midPanel.setBounds(0,0,1024,400);
		midPanel.setPreferredSize(new Dimension(1024,400));
		
		//adding components to JPanels
		topPanel.add(header);
		topPanel.add(header2);
		//midPanel.add(info1, new Integer(3));
		midPanel.add(info3, new Integer(3));
		midPanel.add(info2, new Integer(2));
		
		midPanel.add(graf1, new Integer(0));
		midPanel.add(graf2, new Integer(1));
		bottomPanel.add(toggleSwitch);
		
		//setting this JPanels layout to BorderLayout
		setLayout(new BorderLayout());
		
		//adding sections to this JPanel
		add(topPanel, BorderLayout.PAGE_START);
		add(midPanel, BorderLayout.LINE_START);
		add(bottomPanel, BorderLayout.PAGE_END);	
		//toggleSwitch.addActionListener(this);
		graph3print = new Graph3print();
	}
	//Get-method for graf1
	public Graph1 getGraf1()
	{
		Graph1 chart = graf1;
		return chart;
	}
	//Get-method for graf2
	public Graph2 getGraf2()
	{
		return graf2;
	}
	public synchronized int wakywaky(){
		notifyAll();
		return 1;
	}
	public double[] calcExpReal()
	{	for(int i=0; i < expCategories.length; i++){
		expCategories[i] = 0;
		}
	
		String temp = dateFormatter.format(Calendar.getInstance().getTime());
		for(int i = 0; i < exList.expLogList.size(); i++)
		{
		if(exList.expLogList.get(i).getExDate().substring(3).equalsIgnoreCase(temp.substring(3)))
		{

			switch(exList.expLogList.get(i).getUTYPE())
			{
				case "Husleie":
					expCategories[0]+=exList.expLogList.get(i).getUCOST();
						break;
				case "Strøm":
					expCategories[1] += exList.expLogList.get(i).getUCOST();
					break;
				case "Internett":
					expCategories[2] += exList.expLogList.get(i).getUCOST();
					break;
				case "Telefon":
					expCategories[3] += exList.expLogList.get(i).getUCOST();
					break;
				case "Sparing":
					expCategories[4] += exList.expLogList.get(i).getUCOST();
					break;
				case "Forsikring":
					expCategories[5] += exList.expLogList.get(i).getUCOST();
					break;
				case "Lån":
					expCategories[6] += exList.expLogList.get(i).getUCOST();
					break;
				case "Mat":
					expCategories[7] += exList.expLogList.get(i).getUCOST();
					break;
				case "Klær":
					expCategories[8] += exList.expLogList.get(i).getUCOST();
					break;
				case "Dyr":
					expCategories[9] += exList.expLogList.get(i).getUCOST();
					break;
				case "Reise":
					expCategories[10] += exList.expLogList.get(i).getUCOST();
					break;
				case "Personlig":
					expCategories[11] += exList.expLogList.get(i).getUCOST();
					break;
				case "Annet":
					expCategories[12] += exList.expLogList.get(i).getUCOST();
					break;
				}	
			}
		}
		return expCategories;
	}
	public double calcRealTotEx(){
    	String temp = dateFormatter.format(Calendar.getInstance().getTime());
    	//modell.setRowCount(0);
    	double cache = 0.0;
    	String incoming = temp.substring(3);
    	System.out.println(incoming);
    	for(int i = 0; i < exList.expLogList.size(); i++)
    	{
    	//	if(exList.expLogList.get(i).getDate().substring(3)!= null)
    		temp = exList.expLogList.get(i).getDate().substring(3);
    		if(temp.matches(incoming))
    			//if(exList.expLogList.get(i).getUTYPE().equalsIgnoreCase("Husleie")
    				cache += exList.expLogList.get(i).getUCOST();
    			
    	}
    	return cache;
	
}
	
	
	//Method that updates the piechart in Graf1
	public void updateView1()
	{
		if(expCategories.length > 0)
			graf1.setDrawBool(true);
		else
			graf1.setDrawBool(false);
		
		//budgetReport();
		graf1.nullOut();
	
		//graf1.setExecute(false);
		//notifyAll();
		//wakywaky();
		double hundreProsent;
		double totalExpenses;
		//if(exList.exlogSize()>0)
		totalExpenses = calcRealTotEx();//uList.sumExpenses();
	//	else
		
		//	totalExpenses = 0.0;
	//	if(totalExpenses == null)
		//	totalExpenses = 0;
		double totalIncome = iList.sumIncome();
		double leftoverMoney = totalIncome - totalExpenses;
	//	double budsjettTotal = uList.sumMonthlyExpenses();
		
	//	double tmp = (budsjettTotal - totalExpenses);
	
		
		graf1.setLine1("Total inntekt: " + dFormat.format(totalIncome));
		graf1.setLine2("Totale utgifter: " + dFormat.format(totalExpenses));
		graf1.setLine3("Rest: " + dFormat.format(leftoverMoney));
		//graf1.setLine4(hejda);
		//info1.setText("Total inntekt: " + dFormat.format(totalIncome) + "\nTotale utgifter: " + dFormat.format(totalExpenses) +
		//		"\nRest: " + dFormat.format(leftoverMoney));
		if(totalIncome > totalExpenses)
			hundreProsent = totalIncome;
		else
			hundreProsent = totalExpenses;
	
		double percentageOfExpense = ((leftoverMoney/hundreProsent) * 360);
		double percentageOfIncome = ((totalIncome/hundreProsent) * 360);
		int pOI = 0;
		if(hundreProsent == totalIncome)
			pOI = (int) percentageOfExpense;
		else if(hundreProsent == totalExpenses)
			pOI = (int) percentageOfIncome; //used to be Income;
		graf1.setTotInc(totalIncome);
		graf1.setTotExp(totalExpenses);
		if(hundreProsent == totalIncome)
		{
			/*
			graf1.setIncStart(0);
			//graf1.setIncArc(pOI);
			graf1.setExpArc(360-pOI);
			graf1.setExpStart(pOI);
			*/
			graf1.setExpStart(0);
			graf1.setExpArc(360-pOI);
			graf1.setIncStart2(360-pOI);
			graf1.setIncArc(pOI);
		}
		else
		{
			/*
			graf1.setIncStart(0);
			graf1.setExpStart(pOI);
			graf1.setIncArc(pOI);
			graf1.setExpArc(360-pOI);
			*/
			graf1.setIncStart(0);
			graf1.setExpStart(0);
			graf1.setIncArc(360-pOI);
			graf1.setExpArc(360);
			graf1.repaint();
		}
		//if(graf1.a.getState() ==)
	//		System.out.println(graf1.a.getState());
			
	}
	
	//Method that updates the pie-chart in Graf2
	public void updateView2()
	{
		//double hundreProsent;
		double totalExpenses = exList.sumMonthlyExpenses();
		double totalIncome = iList.sumIncome();
		//JOptionPane.showMessageDialog(null, "" + leftoverMoney);
		graf2.setIncome(totalIncome);
		graf2.setExpenses(totalExpenses);
		//exList.calculateMonthlyExpenses(temp);
		double[]utgift = calcExpReal();
		
		double[]utgiftbud = new double[14];
		utgiftbud[0] = uList.sumAlleHusleie();
		utgiftbud[1] = uList.sumAlleStrom();
		utgiftbud[2] = uList.sumAlleInternet();
		utgiftbud[3] = uList.sumAlleTelefon();
		utgiftbud[4] = uList.sumAlleSparing();
		utgiftbud[5] = uList.sumAlleForsikring();
		utgiftbud[6] = uList.sumAlleLån();
		utgiftbud[7] = uList.sumAlleMat();
		utgiftbud[8] = uList.sumAlleKlede();
		utgiftbud[9] = uList.sumAlleDyr();
		utgiftbud[10]= uList.sumAlleReiser();
		utgiftbud[11]= uList.sumAllePersonlig();
		utgiftbud[12]= uList.sumAlleAnnet();
		
		double [] diff = new double[14];
		for(int i = 0; i < diff.length-1; i++){
			diff[i] = utgift[i]-utgiftbud[i];
		}
		diff[13] = totalExpenses -uList.sumMonthlyExpenses();
		graf2.setDiff(diff);
		info3.setText("BUDSJETTERTE UTGIFTER:\n——————————————\nHusleie: " + dFormat.format(utgiftbud[0]) + "\nStrøm: " + dFormat.format(utgiftbud[1]) + 
				"\nInternett: " + dFormat.format(utgiftbud[2]) + "\nTelefon: " + dFormat.format(utgiftbud[3]) +
				"\nSparing: " + dFormat.format(utgiftbud[4]) + "\nForsikring: " + dFormat.format(utgiftbud[5]) +
				"\nLån: " + dFormat.format(utgiftbud[6]) + "\nMat: " + dFormat.format(utgiftbud[7]) + 
				"\nKlær: " + dFormat.format(utgiftbud[8]) + "\nDyr: " + dFormat.format(utgiftbud[9]) +
				"\nReise: " + dFormat.format(utgiftbud[10]) + "\nPersonlig: " + dFormat.format(utgiftbud[11]) +
				"\nAnnet: " + dFormat.format(utgiftbud[12]) +
				"\n_____________________\nSum:\n" + dFormat.format(uList.sumMonthlyExpenses()));
		//Sender data inn i tekstfeltet info2
		info2.setText("REELLE UTGIFTER:\n———————————\nHusleie: " + dFormat.format(utgift[0]) + "\nStrøm: " + dFormat.format(utgift[1]) + 
				"\nInternett: " + dFormat.format(utgift[2]) + "\nTelefon: " + dFormat.format(utgift[3]) +
				"\nSparing: " + dFormat.format(utgift[4]) + "\nForsikring: " + dFormat.format(utgift[5]) +
				"\nLån: " + dFormat.format(utgift[6]) + "\nMat: " + dFormat.format(utgift[7]) + 
				"\nKlær: " + dFormat.format(utgift[8]) + "\nDyr: " + dFormat.format(utgift[9]) +
				"\nReise: " + dFormat.format(utgift[10]) + "\nPersonlig: " + dFormat.format(utgift[11]) +
				"\nAnnet: " + dFormat.format(utgift[12]) +
				"\n_____________________\nSum:\n" + dFormat.format(totalExpenses));
		
		double sumInt = 0;
		for(int i = 0; i < utgift.length; i++)
		{
			//utgiftI[i] = (int)utgift[i];
			sumInt += utgift[i];
		}
		
		
		//Kalkulerer prosent av kaka(Graf2)
		int husleie2 = (int) ((utgift[0]/sumInt)*360);
		int strom2 = (int) ((utgift[1]/sumInt)*360);
		int internett2 = (int) ((utgift[2]/sumInt)*360);
		int tele2 = (int) ((utgift[3]/sumInt)*360);
		int sparing2 = (int) ((utgift[4]/sumInt)*360);
		int forsikring2 = (int) ((utgift[5]/sumInt)*360);
		int lan2 = (int) ((utgift[6]/sumInt)*360);
		int mat2 = (int) ((utgift[7]/sumInt)*360);
		int klede2 = (int) ((utgift[8]/sumInt)*360);
		int dyr2 = (int) ((utgift[9]/sumInt)*360);
		int reise2 = (int) ((utgift[10]/sumInt)*360);
		int personlig2 = (int) ((utgift[11]/sumInt)*360);
		int annet2 = (int) ((utgift[12]/sumInt)*360);
		//Sender data om prosent av kaka til Graf2
		graf2.setHusleieArc(husleie2);
		graf2.setStromStart(husleie2);
		graf2.setStromArc(strom2);
		graf2.setItStart(strom2 + husleie2);
		graf2.setItArc(internett2);
		graf2.setTeleStart(internett2 + strom2 + husleie2);
		graf2.setTeleArc(tele2);
		graf2.setMatStart(tele2 + internett2 + strom2 + husleie2);
		graf2.setMatArc(mat2);
		graf2.setKledeStart(mat2 + tele2 + internett2 + strom2 + husleie2);
		graf2.setKledeArc(klede2);
		graf2.setDyrStart(klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		graf2.setDyrArc(dyr2);
		graf2.setReiseStart(dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		graf2.setReiseArc(reise2);
		graf2.setPersStart(reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		graf2.setPersArc(personlig2);
		graf2.setSpareStart(personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		graf2.setSpareArc(sparing2);
		graf2.setSikkerStart(sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		graf2.setSikkerArc(forsikring2);
		graf2.setLanStart(forsikring2  + sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		graf2.setLanArc(lan2);
		graf2.setAnnetStart(lan2 + forsikring2  + sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		graf2.setAnnetArc(annet2);
		
	}

	public void percentofTotal(double n, double t)
	{
		double val = n/t;
		val*=360;
		JOptionPane.showMessageDialog(null, val);
	}
		//method that hides all components of the panel
		/*private void hideAll()
		{
			graf2.setVisible(false);
			graf1.setVisible(false);
			//info1.setVisible(false);
			header.setVisible(false);
			header2.setVisible(false);
		}
		*/
		//Method for switching views
		private void toggleSwitch()
		{
			//graf1.repaint();
			if(graf2.isVisible())
			{
				try{
				graf1.setVisible(true);
			//	updateView1();
			//	if(graf1.getState()==0){
				//	graf1.setState(1);
					header2.setVisible(false);
					header.setVisible(true);
					graf2.setVisible(false);
					toggleSwitch.setText("Detaljert");
					info2.setVisible(false);
					info3.setVisible(false);
					graf1.setVisible(true);
				}
			//	}
				catch(Exception e){
					e.printStackTrace();
			}
			//	info1.setVisible(true);	
			}
			else if(graf1.isVisible())
			{
				try{
				updateView2();
				header2.setVisible(true);
				header.setVisible(false);
				//graf2.repaint();
				//graf1.setState(0);
				toggleSwitch.setText("Enkel");
				graf2.setVisible(true);
				info2.setVisible(true);
				info3.setVisible(true);
				graf1.setVisible(false);
				//info1.setVisible(false);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	
	//public JPanel createPrintable()
	//{
		//PrintSheetBudget printbud = new PrintSheetBudget();

		
	//	return printbud;
		
	//}
	/*public void print()
	{
		PrintSheetBudget printbud = new PrintSheetBudget(graf1, graf2, info1, info2);
		
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(printbud);
		
		boolean doPrint = job.printDialog();
		if (doPrint) {
		    try {
		        job.print();
		    } catch (PrinterException e) {
		    	System.out.println(e);
		        // The job did not successfully
		        // complete
		    }
		}
	}*/
	public int print(Graphics g, PageFormat pf, int page){
		pf.setOrientation(PageFormat.LANDSCAPE);
		 if (page > 0) { /* We have only one page, and 'page' is zero-based */
		      return NO_SUCH_PAGE;
		    }

		    /*
		     * User (0,0) is typically outside the imageable area, so we must translate
		     * by the X and Y values in the PageFormat to avoid clipping
		     */
		    Graphics2D g2d = (Graphics2D) g;
		    g2d.translate(pf.getImageableX(), pf.getImageableY());
		    g2d.translate( 0f, 0f );
		    /* Now we perform our rendering */
		  	//g2d.copyArea(0, 0, this.getWidth(), this.getHeight(), 1, 1);
		    this.paint(g2d);
		    /* tell the caller that this page is part of the printed document */
		    return PAGE_EXISTS;
		  
	
		
	}
	public void actionPerformed(ActionEvent e) {
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(this);
	    boolean ok = job.printDialog();
	    if (ok) {
	      try {
	        job.print();
	      } catch (PrinterException ex) {
	        /* The job did not successfully complete */
	      }
	    }
	  }

	//private listener
    private class LyttR implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
        	 if (e.getSource() == toggleSwitch)
        		 toggleSwitch();
        }
    }
    
    
	
	
	
}
