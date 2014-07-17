import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.*;
public class GUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	LyttR lyttR = new LyttR();
	JButton [] buttons;
	String[] buttonTexts = {"Oversikt", "Inntekter", "Utgifter"};
	JPanel top;
	JPanel bottom;
	UtgiftsReg uReg;
	InntektsReg iReg;
	Oversikt oversikt;
	InntektsListe iList;
	UtgiftsListe uList;
	JPanel backdrop;
	GUI(InntektsListe il, UtgiftsListe ul)
	{
		super("Budsjett 1.0");
		buttons = new JButton[3];
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		top = new JPanel();
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton(buttonTexts[i]);
			buttons[i].setPreferredSize(new Dimension(132,20));
			buttons[i].addActionListener(lyttR);
			top.add(buttons[i]); 
		}
		iList = il;
		uList = ul;
		readFile();
		bottom = new  JPanel();
		bottom.setPreferredSize(new Dimension(1024,600));
		bottom.setVisible(true);
		uReg = new UtgiftsReg(uList);
		uReg.setVisible(false);
		iReg = new InntektsReg(iList);
		iReg.setVisible(false);
		backdrop = new JPanel();
		JLabel front = new JLabel("BUDSJETT 1.0");
		front.setFont(new Font("Sans-Serif", Font.PLAIN, 100));
		backdrop.add(front);
		JLabel copyright = new JLabel("Av Anders S. Simonsen.");
		copyright.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		copyright.setLocation(100, 400);
		backdrop.add(copyright);
		backdrop.setVisible(true);
		oversikt = new Oversikt(iList, uList);
		oversikt.setVisible(false);

		c.add(top, BorderLayout.PAGE_START);
		c.add(bottom, BorderLayout.LINE_START);
		bottom.add(uReg);
		bottom.add(iReg);
		bottom.add(oversikt);
		bottom.add(backdrop);
		
	}
	public void hideAll()
	{
		uReg.setVisible(false);
		iReg.setVisible(false);
		oversikt.setVisible(false);
		backdrop.setVisible(false);
	}
	public void visUreg()
	{
		if(uReg.isVisible() == false)
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
			backdrop.setVisible(true);
		}
	}
	
	public void visIreg()
	{
		if(iReg.isVisible() == false)
		{	
			hideAll();
			if(iList.listSize() > 0)
			{

				iReg.modell.setRowCount(0);
				iReg.addToTable();
			}
				iReg.setVisible(true);
			
		}
		else
		{
			iReg.storeToClass();
			hideAll();
			backdrop.setVisible(true);
		}
	}
	public void visOversikt()
	{
		if(oversikt.isVisible() == false)
		{
			if(iReg.isVisible()== true)
				iReg.storeToClass();
			if(uReg.isVisible()==true)
				uReg.storeToClass();
			hideAll();
			oversikt.repaint();
			oversikt.dypdykk.repaint();
			
			oversikt.stats();
			oversikt.budgetReport();
			oversikt.setVisible(true);
		}
		else
		{
			hideAll();
			backdrop.setVisible(true);
		}
		
	}
	private void readFile()//Metode for å lese fra fil
	{
		try (ObjectInputStream inFile = new ObjectInputStream(
	            new FileInputStream( "src/Budsjett.data" )))
	    {
		
              iList = (InntektsListe) inFile.readObject();
              uList = (UtgiftsListe) inFile.readObject();
           
	    }
	    catch(ClassNotFoundException cnfe)
	    {
	    	System.out.println("cnfe");
                iList = new InntektsListe();
                uList = new UtgiftsListe();
             

	    }
	    catch(FileNotFoundException fne)
	    {
	    	System.out.println("fne");
	    }
	    catch(IOException ioe)
	    {
	    	System.out.println("ioe");
	    	iList = new InntektsListe();
            uList = new UtgiftsListe();
	    }
	  }
	public void writeToFile()//metode som skriver serialiserte data til fil
	{
		try (ObjectOutputStream outFile = new ObjectOutputStream(
				
				new FileOutputStream("src/Budsjett.data")))
		{
					 outFile.writeObject(iList);
                     outFile.writeObject(uList);
		}
		catch( NotSerializableException nse )
		{
                    JOptionPane.showMessageDialog(null, "Objektet er ikke serialisert!");
		}
		catch( IOException ioe )
		{
                    JOptionPane.showMessageDialog(null, "problem med utskrift til fil");
		}
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
    
        }
    }
}
