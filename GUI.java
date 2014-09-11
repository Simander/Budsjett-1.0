import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.*;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class GUI extends JFrame implements Serializable
{
	private static final long serialVersionUID = 1L;
	LyttR lyttR = new LyttR();
	JButton [] buttons;
	String[] buttonTexts = {"Oversikt", "Inntekter", "Utgifter", "Logg"};
	JPanel top;
	JPanel bottom;
	ExpenseLog exLog;
	UtgiftsReg uReg;
	InntektsReg iReg;
	Oversikt oversikt;
	InntektsListe iList;
	UtgiftsListe uList;
	ExpLogList expLogList;
	JPanel page;
	JMenuBar bar = new JMenuBar();
	JMenu menu1;
	JMenuItem print;
	JMenuItem exit;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	String dataLoc = "budsjett.data";
	
	URL dataURL = getClass().getResource(dataLoc);
	//URL hey = ClassLoader.getSystemResource(dataLoc);
	//File file = new File(hey.toURI());
	//File file = new File(hey.getPath());
	
	//String is = Class.class.getResourceAsStream(dataLoc);
	//InputStream s = 
	//String loc = is.toString();
	
    

	
	//String getIT = (String) dataURL;
	//temp
	//PrintSheetBudget tempPanel = new PrintSheetBudget(oversikt.getLeft(), oversikt.getDypdy);
	
	private JProgressBar progressBar;
	
	
	GUI(InntektsListe il, UtgiftsListe ul, ExpLogList el)
	{
		
		super("Budsjett 1.0");
		
		
		
		//progressBar.setIndeterminate(true);
	//	progressBar.
		
		menu1 = new JMenu("Fil");
		bar = new JMenuBar();
		bar.add(menu1);
		page = new JPanel();
		print = new JMenuItem("Skriv ut utgiftsoversikt");
		exit = new JMenuItem("Avslutt program");
		//print.addActionListener(lyttR);
		print.addActionListener(lyttR);
		exit.addActionListener(lyttR);
		menu1.add(print);
		menu1.add(exit);
		buttons = new JButton[4];
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		top = new JPanel();
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton(buttonTexts[i]);
			buttons[i].setPreferredSize(new Dimension(132,40));
			buttons[i].addActionListener(lyttR);
			top.add(buttons[i]); 
		}
		iList = il;
		uList = ul;
		expLogList = el;
		
		readFile();
		delOld();
		bottom = new  JPanel();
		bottom.setPreferredSize(new Dimension(1024,600));
		bottom.setVisible(true);
		uReg = new UtgiftsReg(uList);
		uReg.setVisible(false);
		iReg = new InntektsReg(iList);
		iReg.setVisible(false);
		//new code
		exLog = new ExpenseLog(expLogList);
		exLog.setVisible(false);
		//backdrop = new JPanel();
		JLabel front = new JLabel("BUDSJETT 1.0");
		front.setFont(new Font("Sans-Serif", Font.PLAIN, 100));
		oversikt = new Oversikt(expLogList, iList, uList);
		oversikt.setVisible(false);
		page.setLayout(new BorderLayout());
		page.add(top, BorderLayout.PAGE_START);
		page.add(bottom, BorderLayout.LINE_START);
		
		bottom.add(uReg);
		bottom.add(iReg);
		bottom.add(exLog);
		bottom.add(oversikt);
		oversikt.setVisible(true);
		
		//c.add(comp)
		c.add(bar, BorderLayout.PAGE_START);
		c.add(page, BorderLayout.LINE_START);
		//c.add(progressBar, BorderLayout.PAGE_END);
	//	readFile();
		oversikt.updateView1();
		//oversikt.graf1.repaint();
		//c.remove(progressBar);
		
		
	}
	public void removePBar(){
		remove(progressBar);
	}
	public void hideAll()
	{
		uReg.setVisible(false);
		iReg.setVisible(false);
		exLog.setVisible(false);
		oversikt.setVisible(false);
	
		//backdrop.setVisible(false);
	}
	public void visUreg()
	{
		//oversikt.graf1.sleepyThread();
		oversikt.graf1.setState(0);
		try{
		
			if(uReg.isVisible() == false)
			{
				if(iReg.isVisible() == true)
					iReg.storeToClass();
				hideAll();
				uReg.modell.setRowCount(0);
				uReg.addToTable();
				uReg.setVisible(true);
			}
			/*if(uReg.isVisible() == false)
			{
				hideAll();
				uReg.modell.setRowCount(0);
				uReg.addToTable();
				uReg.setVisible(true);
			}
			else
			{
				uReg.storeToClass();
				hideAll();
				
			}*/
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "Ugyldig inntekt er fyllt inn, prøv igjen!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void visIreg()
	{
		oversikt.graf1.setState(0);
		//oversikt.graf1.sleepyThread();
		try{
			/*if(iReg.isVisible() == false)
			{	
				if(uReg.isVisible() == true)
					uReg.storeToClass();
				
				if(iList.listSize() > 0)
				{

					iReg.modell.setRowCount(0);
					iReg.addToTable();
				}
					iReg.setVisible(true);
			
			}*/
			if(iReg.isVisible() == false)
			{
				if(uReg.isVisible() == true)
					uReg.storeToClass();
				hideAll();
				iReg.modell.setRowCount(0);
				iReg.addToTable();
				iReg.setVisible(true);
			}
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "Ugyldig inntekt er fyllt inn, prøv igjen!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		}

	}
	public void visExLog()
	{
		oversikt.graf1.setState(0);
		//oversikt.graf1.sleepyThread();
		try{
			if(exLog.isVisible() == false)
			{	
				if(uReg.isVisible() == true)
					uReg.storeToClass();
				if(iReg.isVisible() == true)
					iReg.storeToClass();
				hideAll();
				if(iList.listSize() > 0)
				{

					exLog.modell.setRowCount(0);
					//exLog.addToTable();
				}
					exLog.setVisible(true);
					
			
			}
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "Ugyldig inntekt er fyllt inn, prøv igjen!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		}

	}
	public void visOversikt()
	{
		
		if(oversikt.isVisible()==false){
			oversikt.graf1.nullOut();
		
		//checks to see if the lists are empty or not. If not the animationThread of graph1 is launched
		if(oversikt.graf1.getState()== 0 && iList.listSize() >0 || expLogList.listSize() > 0) //&& iList.listSize()>0 && uList.utgiftsliste.size()>0)
		{		oversikt.graf1.setState(1);
		
//				oversikt.graf1.setDrawBool(true);
		}
	//	else
	//		oversikt.graf1.setDrawBool(false);
		
		try{
			if(oversikt.isVisible() == false)
			{
				if(iReg.isVisible()== true){
					iReg.storeToClass();
				}
				if(uReg.isVisible()==true)
					uReg.storeToClass();
				hideAll();
				//oversikt.graf1.setExecute(true);
				//if(oversikt.graf1.isVisible())
			//	if(iList.listSize() > 0 && expLogList.listSize() > 0){
				oversikt.updateView1();
				//if(oversikt.graf2.isVisible())
				oversikt.updateView2();}
				oversikt.setVisible(true);
			//}
		}
		catch(Exception e)//NumberFormatException nfe)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ugyldig inntekt er fyllt inn, prøv igjen!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		}
		}
		else{
		}
		
	}
	public void awake(){
		try{
			synchronized(this){
		notifyAll();
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
	private void delOld(){
		String temp = dateFormatter.format(Calendar.getInstance().getTime());
		int yearNow = Integer.parseInt(temp.substring(6));
		System.out.println(expLogList.deleteOld(yearNow));
	}
	private void readFile()//Metode for å lese fra fil
	{
		try (ObjectInputStream inFile = new ObjectInputStream(
	            new FileInputStream(dataLoc)))
	    {
		
              iList = (InntektsListe) inFile.readObject();
              uList = (UtgiftsListe) inFile.readObject();
              expLogList = (ExpLogList) inFile.readObject();
           
	    }
	    catch(ClassNotFoundException cnfe)
	    {
	    	System.out.println("cnfe");
	    	 iList = new InntektsListe();
             uList = new UtgiftsListe();
             expLogList = new ExpLogList();
             

	    }
	    catch(FileNotFoundException fne)
	    {
	    	System.out.println("fne");
	    	 iList = new InntektsListe();
             uList = new UtgiftsListe();
             expLogList = new ExpLogList();
	    }
	    catch(IOException ioe)
	    {
	    	System.out.println("ioe");
	    	 iList = new InntektsListe();
             uList = new UtgiftsListe();
             expLogList = new ExpLogList();
	    }
	  }
	public void writeToFile()//metode som skriver serialiserte data til fil
	{
		
		if(iReg.isVisible()==true)
			iReg.storeToClass();
		if(uReg.isVisible()==true)
			uReg.storeToClass();
	//	if(exLog.isVisible()==true)
	//		exLog.storeToClass();
		try (ObjectOutputStream outFile = new ObjectOutputStream(
				
				new FileOutputStream(dataLoc)))
				
		{
					 outFile.writeObject(iList);
                     outFile.writeObject(uList);
                     outFile.writeObject(expLogList);
		}
		catch( NotSerializableException nse )
		{
                    JOptionPane.showMessageDialog(null, "Objektet er ikke serialisert!");
		}
		catch( IOException ioe )
		{
                    JOptionPane.showMessageDialog(null, "problem med å skrive til fil");
		}
	}
	
	/*public void printComponenet(){
		   JPanel printpane = oversikt.createPrintable();
		  PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName(" Print Component ");

		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum, int pageIndex i){
		      if (pageNum > 0){
		      return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) pg;
		      g2.translate(pf.getImageableX(), pf.getImageableY());
		      pageIndex i = pageBreaks - pageNum;
		   
		     // componenet_name.paint(g2);
		      printpane.paint(g2);
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if (pj.printDialog() == false)
		  return;

		  try {
		        pj.print();
		  } catch (PrinterException ex) {
		        // handle exception
		  }
		}
	*/
	public void printComponent(){
		double[] hey = oversikt.calcExpReal();
		System.out.println(hey.length);
		final Printsheet printsheet1 = new Printsheet(hey, uList, expLogList);
		
		printsheet1.setVisible(true);
		printsheet1.setSize(700,1200); //595, 842);//700, 800);
		printsheet1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	//	final Graphics hell = printsheet1.getGraphics();
			//printsheet1.setLocation(dim.width/2-gui.getWidth()/2, dim.height/2-gui.getHeight()/2);
		 PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName(" Print Component ");
		  
		// this.set 
		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum){
		    	pf.setOrientation(PageFormat.PORTRAIT);
		    	//java.awt.print.Paper paper = new Paper("a4");
		    	//pf.setPaper(paper);
		    	//java.awt.print.Paper pap = pf.getPaper();
		    	//pap.setSize(width, height);;
		    	//Paper paper = new Paper();
		    	//paper.setSize(595, 842);
		    	//paper.setImageableArea(43, 43, 509, 756);
		    	//pf.setPaper(paper);
		      if (pageNum > 0){
		      return Printable.NO_SUCH_PAGE;
		      }
		      //System.out.println(pf.getImageableX() + "+" + pf.getImageableY());
		      //Graphics2D g2 = (Graphics2D) pg;
		  
		     // printsheet1.paint(hell);
		    //  printsheet1.setSize(printsheet1.getWidth()/2, printsheet1.getHeight()/2);
		      //*double sx = pf.getImageableWidth()/printsheet1.getWidth();
		     //* double sy = pf.getImageableHeight()/printsheet1.getHeight();
		      
		      //sx = printsheet1.getWidth()/pf.getImageableWidth();
		     // sy = printsheet1.getHeight()/pf.getImageableHeight();
		     
		  
		      //*double h = sx;
		      //*double w = sy;
		      //*printsheet1.paint(g2);
		      
		      //*g2.translate(pf.getImageableX(), pf.getImageableY());
		     //* g2.scale(.05, .05); 
		      
		     // g2.scale(width, height);
		   //   g2.scale(0.5, 0.5);
		      
		      //get the bounds of the component
		      Dimension dim = printsheet1.getSize();
		    //  double cHeight = dim.getHeight();
		      double cWidth = dim.getWidth();
		      
		      //get the bounds of the printable area
		    //  double pHeight = pf.getImageableHeight();
		      double pWidth = pf.getImageableWidth();
		      
		      double pXstart = pf.getImageableX();
		      double pYstart = pf.getImageableY();
		      
		      double xRatio = pWidth/cWidth;
		    //  double yRatio = pHeight/cHeight;
		      
		      Graphics2D g2d = (Graphics2D) pg;
		      g2d.translate(pXstart, pYstart);
		      g2d.scale(xRatio, xRatio);
		      printsheet1.paint(g2d);
		     
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if (pj.printDialog() == false){ printsheet1.dispose();
		  return;}

		  try {
		        pj.print();
		        printsheet1.dispose();
		  } catch (PrinterException ex) {
			//  printsheet1.dispose();
		        // handle exception
		  }
		  
		}
	 public void printSok()  //Metode som printer søkeResultat
     {
        /* boolean complete = false;

		 	
         try {
        	 if(uReg.isVisible())
        		 uReg.table.print();
        	 else if(iReg.isVisible())
        		 iReg.table.print();
        	 else if(exLog.isVisible())
        		 exLog.table.print();
        	// Printer print = new Printer();
        //	 else
        //	 {
        //		 Graphics pG = oversikt.getGraphics();
        //		 oversikt.print(pG);
        //	 }
 		 		//print();
		     if(complete)
		     {
		         JOptionPane.showMessageDialog(null, "Done printing", "Information", JOptionPane.INFORMATION_MESSAGE);
		     }
		     else
		         JOptionPane.showMessageDialog(null, "Printing", "Printer", JOptionPane.ERROR_MESSAGE);
 		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();*/
		//}
	
     }
    //privat lytteklasse
    private class LyttR implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
        	 if (e.getSource() == buttons[0])
        		 visOversikt();
             if (e.getSource() == buttons[2])
                 visUreg();
             if (e.getSource() == buttons[1])
            	visIreg();
             if	(e.getSource() == buttons[3])
            	 visExLog();
             //{
            	// String temp = dateFormatter.format(Calendar.getInstance().getTime()).substring(3);
            	// System.out.println(temp);
            	 
            // }
             if (e.getSource() == exit)
             {
            	 writeToFile();
            	 System.exit(0);
             }
            if(e.getSource() == print)
            	 //printSok();
            printComponent();
             
			
			//printsheet1.setDefaultCloseOperation(0);}
            	//System.out.println("knappen funker!");
    
        }
    }
}
