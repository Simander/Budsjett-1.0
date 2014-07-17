import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Oversikt extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	LyttR lyttR = new LyttR();
	InntektsListe iList;
	UtgiftsListe uList;
	JTextArea info;
	Chart left;
	Utdyp dypdykk;
	JTextArea info2;
	JButton deeper;
	JPanel right;
	JLayeredPane bottom;
	JPanel buttox;
	JPanel top;
	Oversikt(InntektsListe il, UtgiftsListe ul)
	{
		//listeklasser
		iList = il;
		uList = ul;
		buttox = new JPanel();
		deeper = new JButton("Detaljert");
		deeper.addActionListener(lyttR);
		deeper.setPreferredSize(new Dimension(300, 40));
		dypdykk = new Utdyp();
		//Panel for tegning av graf
		left = new Chart();
		left.setVisible(true);
		//Panel for visning av info
		right = new JPanel();
		right.setVisible(true);
		//layeredpane
		bottom = new JLayeredPane();
		//top panel
		top = new JPanel();
		//hovedpanelets layout
		setLayout(new BorderLayout());
		//legger til top og bottom
		add(top, BorderLayout.PAGE_START);
		add(bottom, BorderLayout.LINE_START);
		add(buttox, BorderLayout.PAGE_END);
		buttox.add(deeper);
		
		bottom.setVisible(true);
		bottom.setBounds(0,0,1024,400);
		//bottom.setLayout(new FlowLayout());
		bottom.setPreferredSize(new Dimension(1024,400));
		info = new JTextArea(20,30);
		info.setEditable(false);
		info.setOpaque(false);

		info.setVisible(true);
		info.setPreferredSize(new Dimension(400,400));
		info2 = new JTextArea(20, 40);
		info2.setEditable(false);
		info2.setOpaque(false);
		info2.setVisible(false);
		info2.setPreferredSize(new Dimension(1024, 400));
		JLabel header = new JLabel("Budsjettrapport");
		header.setFont(new Font("Sans-Serif", Font.PLAIN, 40));
		top.add(header);
		bottom.add(info, new Integer(3));
		right.setPreferredSize(new Dimension(400,400));
		right.setLocation(800,0);
		left.setPreferredSize(new Dimension(400,400));
		left.setBounds(0,0, 1024,400);
		info.setBounds(600, 0, 1024,400);
		info2.setBounds(710, 50, 1024, 400);
		dypdykk.setPreferredSize(new Dimension(1024, 400));
		dypdykk.setBounds(0,0,1024,400);
		bottom.add(info2, new Integer(2));
	
		left.setLayout(new FlowLayout());
		right.setLayout(new FlowLayout());
		bottom.add(left, new Integer(0));
		bottom.add(dypdykk, new Integer(1));
		dypdykk.setVisible(false);


		
		
		
	}
	
	public void budgetReport()
	{
		double totalExpenses = uList.sumExpenses();
		double totalIncome = iList.sumIncome();
		double leftoverMoney = totalIncome - totalExpenses;
		
	//	JOptionPane.showMessageDialog(null, "" + percentageOfExpense);
		
		info.setText("Total inntekt: " + totalIncome + "\nTotale utgifter: " + totalExpenses +
				"\nRest: " + leftoverMoney);
	}
	public void stats()
	{
		double hundreProsent;
		double totalExpenses = uList.sumExpenses();
		double totalIncome = iList.sumIncome();
		double leftoverMoney = totalIncome - totalExpenses;
		if(totalIncome > totalExpenses)
			hundreProsent = totalIncome;
		else
			hundreProsent = totalExpenses;
	
		double percentageOfExpense = ((leftoverMoney/hundreProsent) * 100*3.6);
		double percentageOfIncome = ((totalIncome/hundreProsent) * 100*3.6);
		int pOI = 0;
		if(hundreProsent == totalIncome)
			pOI = (int) percentageOfExpense;
		else if(hundreProsent == totalExpenses)
			pOI = (int) percentageOfIncome;
		left.setTotInc(totalIncome);
		left.setTotExp(totalExpenses);
		if(hundreProsent == totalIncome)
		{
			left.setIncStart(0);
			left.setIncArc(pOI);
			left.setExpArc(360-pOI);
			left.setExpStart(pOI);

			
		
		}
		else
		{
			left.setIncStart(0);
			left.setExpStart(pOI);
			left.setIncArc(pOI);
			left.setExpArc(360-pOI);
		}
		
			
		}
	public void stats2()
	{
		double hundreProsent;
		double totalExpenses = uList.sumExpenses();
		double totalIncome = iList.sumIncome();
		double leftoverMoney = totalIncome - totalExpenses;
		//JOptionPane.showMessageDialog(null, "" + leftoverMoney);
		dypdykk.setIncome(totalIncome);
		dypdykk.setExpenses(totalExpenses);
		double husleie = uList.sumAlleHusleie();
		double strom = uList.sumAlleStrom();
		double internett = uList.sumAlleInternet();
		double tele = uList.sumAlleTelefon();
		double mat = uList.sumAlleMat();
		double klede = uList.sumAlleKlede();
		double dyr = uList.sumAlleDyr();
		double reise = uList.sumAlleReiser();
		double personlig = uList.sumAllePersonlig();
		double sparing = uList.sumAlleSparing();
		double forsikring = uList.sumAlleForsikring();
		double lan = uList.sumAlleLån();
		info2.setText("UTGIFTER:\nHusleie: " + husleie + "\nStrøm: " + strom + "\nInternett: " + internett +
				"\nTelefon: " + tele + "\nMat: " + mat + "\nKlær: " + klede + "\nDyr: " + dyr +
				"\nReise: " + reise + "\nPersonlig: " + personlig + "\nSparing: " + sparing +
				"\nForsikring: " + forsikring + "\nLån: " + lan +
				"\n\n\nREST:\n" + leftoverMoney);
		if(leftoverMoney < 0)
			leftoverMoney = leftoverMoney*(-1);
		
		
		
		double sumUt = (husleie + strom + internett + tele + mat + klede + dyr + reise +
				personlig + sparing + forsikring + lan + leftoverMoney);
		
		int husleie2 = (int)((husleie/sumUt)*100*3.6);
		int strom2 = (int)((strom/sumUt)*100*3.6);	
		int internett2 = (int)((internett/sumUt)*100*3.6);
		int tele2 = (int)((internett/sumUt)*100*3.6);
		int mat2 = (int)((mat/sumUt)*100*3.6);
		int klede2 = (int)((klede/sumUt)*100*3.6);
		int dyr2 = (int)((dyr/sumUt)*100*3.6);
		int reise2 = (int)((reise/sumUt)*100*3.6);
		int personlig2 = (int)((personlig/sumUt)*100*3.6);
		int sparing2 = (int)((sparing/sumUt)*100*3.6);
		int forsikring2 = (int)((forsikring/sumUt)*100*3.6);
		int lan2 = (int)((lan/sumUt)*100*3.6);
		int rest2 = 0;
		if(leftoverMoney < 0)
			rest2 = (int)((leftoverMoney/sumUt)*(-100)*3.6);
		else
			rest2 = (int)((leftoverMoney/sumUt)*100*3.6);
		//JOptionPane.showMessageDialog(null, ""+strom2);
		int sum = (husleie2 + strom2 + internett2 + tele2 + mat2 + klede2 + dyr2 + reise2 + personlig2 +sparing2);
		//double resta = sumUt-summarum;
		//System.out.println(resta);
		dypdykk.setHusleieArc(husleie2);
		dypdykk.setStromStart(husleie2);
		dypdykk.setStromArc(strom2);
		dypdykk.setItStart(strom2 + husleie2);
		dypdykk.setItArc(internett2);
		dypdykk.setTeleStart(internett2 + strom2 + husleie2);
		dypdykk.setTeleArc(tele2);
		dypdykk.setMatStart(tele2 + internett2 + strom2 + husleie2);
		dypdykk.setMatArc(mat2);
		dypdykk.setKledeStart(mat2 + tele2 + internett2 + strom2 + husleie2);
		dypdykk.setKledeArc(klede2);
		dypdykk.setDyrStart(klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		dypdykk.setDyrArc(dyr2);
		dypdykk.setReiseStart(dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		dypdykk.setReiseArc(reise2);
		dypdykk.setPersStart(reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		dypdykk.setPersArc(personlig2);
		dypdykk.setSpareStart(personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		dypdykk.setSpareArc(sparing2);
		dypdykk.setSikkerStart(sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		dypdykk.setSikkerArc(forsikring2);
		dypdykk.setLanStart(forsikring2  + sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		dypdykk.setLanArc(lan2);
		dypdykk.setRestStart(lan2 + forsikring2  + sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
		dypdykk.setRestArc(360-sum);
		
		}
		private void hideAll()
		{
			dypdykk.setVisible(false);
			left.setVisible(false);
			info.setVisible(false);
		}
		private void toggle()
		{
			left.repaint();
			if(dypdykk.isVisible())
			{
				dypdykk.setVisible(false);
				deeper.setText("Detaljert");
				info2.setVisible(false);
				left.setVisible(true);
				info.setVisible(true);
			
			}
			else
			{
				stats2();
				dypdykk.repaint();
				deeper.setText("Enkel");
				dypdykk.setVisible(true);
				info2.setVisible(true);
				left.setVisible(false);
				info.setVisible(false);
			}
		}
	
		
	  //privat lytteklasse
    private class LyttR implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
        	 if (e.getSource() == deeper)
        		 toggle();
        }
    }
	
	
	
}
