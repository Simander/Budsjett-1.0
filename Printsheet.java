import java.awt.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class Printsheet extends JFrame{ //implements Printable{
	private JLabel header2;	//Header that gives the user an idea of what is displayed
	
	private ExpLogList exList;
	private JTextArea info2;									//info2 JTextArea for printing more advanced budget info
	private JTextArea info3;
	protected Graph2 graf2;			
	protected Graph1 graf1;
	protected JPanel cunt;
	protected JPanel graphicPanel;
	protected JPanel contentPanel;
	protected Graph3print graph3print;
	protected JScrollPane scrollTab;							//JScrollPane for holding the expense-table
    protected DefaultTableModel modell = new DefaultTableModel(); 		//Default table model
  //  @SuppressWarnings("serial")
	protected JTable table = new JTable(modell){
        public boolean isCellEditable(int row, int column) {                
            return false;               
        }
        
	};
	protected double[] expCalculated;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	DecimalFormat dFormat = new DecimalFormat("#0.00");	//Desimalformat
	
	Printsheet(double[] a, UtgiftsListe u, ExpLogList exLog){
		super("Forhåndsvisning- print");
		//expCalculated = a;
		exList = exLog;
		graph3print = new Graph3print();
		
	
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String time = dateFormatter.format(date);
		JPanel headerPanel;
		contentPanel = new JPanel();
		header2 = new JLabel("Månedlige utgifter: " + time);
		//setPreferredSize(new Dimension(1024,768));
		info2 = new JTextArea();
		info3 = new JTextArea();
		info2.setPreferredSize(new Dimension(140,300));
		//info2.setLocation(20,600);
	//	info2.setText(s1);
		info2.setVisible(true);
		info2.setEditable(false);
		info3.setEditable(false);
		//info3.setText(s2);
		info3.setVisible(true);
		//info3.setLocation(20, 780);
		info3.setPreferredSize(new Dimension(180,300));
	//	graf1 = g1;
	//	graf2 = g2;
		//graphicPanel = new JPanel();
		//arse.setBackground(Color.WHITE);
		printCalcBud(u);
		printCalc(a);
		headerPanel = new JPanel();
		headerPanel.add(header2);
		//not importante
		//Graphics head = graf2.
		
	//	graphicPanel.paint(head);
		//graphicPanel.add(graf1);
		graph3print.setVisible(true);
		graph3print.setPreferredSize(new Dimension(360,280));
		graph3print.setBackground(Color.WHITE);
		graph3print.setLocation(0,0);
		headerPanel.setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		header2.setFont(new Font("Serif", Font.PLAIN, 40));
		//header2.setLocation(this.getWidth()/2-header2.getWidth(), 20);
		setLayout(new BorderLayout());
		add(headerPanel, BorderLayout.PAGE_START);
		add(contentPanel, BorderLayout.LINE_START);
		contentPanel.setPreferredSize(new Dimension(700, 1200));
		contentPanel.setBackground(Color.WHITE);
		//graf2.setBackground(Color.WHITE);
	//	graf2.setVisible(true);
		cunt = new JPanel();
		cunt.setBackground(Color.WHITE);
		cunt.setPreferredSize(new Dimension(400,400));
		//Container c = getContentPane();
		//c.setLayout(new BorderLayout());
		//c.setLayout(new BorderLayout(5,5));
	//	c.add(header2, BorderLayout.PAGE_START);
		//add(graf1);
	//	modell.addColumn("Indeks:", new String[]{""});
		modell.addColumn("Dato", new String[] {""});
		modell.addColumn("Type", new String[] {""});
		modell.addColumn("Frekvens", new String[] {""});
		modell.addColumn("Utgift", new String[]{""});//new Object[]{new Double(0.0)});
		modell.addColumn("Kommentar", new String[]{""});
		modell.setRowCount(0);
		table.setBackground(Color.WHITE);
		
		scrollTab = new JScrollPane(table);
		scrollTab.setBackground(Color.WHITE);
		scrollTab.setPreferredSize(new Dimension(700,400));
	//	graphicPanel.setPreferredSize(new Dimension(800,400));
		contentPanel.add(graph3print);
		contentPanel.add(info2);
		contentPanel.add(info3);
		contentPanel.add(scrollTab);
		contentPanel.add(new JLabel("Utgiftsoversikt: " + time + ". Genert av SimpleBudget 1.0"));
		findByDate(time);
		table.setEnabled(false);
		//scrollTab.setEnabled(false);
		//graphicPanel.add(graf1, BorderLayout.PAGE_START);
		//c.add(cunt, BorderLayout.LINE_START);
		//cunt.add(info2);
		//cunt.add(info3);
	//	cunt.add(arse);
		//c.setBackground(Color.WHITE);
		//c.add(cunt);
	}

//	@Override
	/*public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}*/
public void printCalcBud(UtgiftsListe u){
	UtgiftsListe uList = u;
	double[]utgiftbud = new double[13];
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
	
	
	info3.setText("BUDSJETTERTE UTGIFTER:\n——————————————\nHusleie: " + dFormat.format(utgiftbud[0]) + "\nStrøm: " + dFormat.format(utgiftbud[1]) + 
			"\nInternett: " + dFormat.format(utgiftbud[2]) + "\nTelefon: " + dFormat.format(utgiftbud[3]) +
			"\nSparing: " + dFormat.format(utgiftbud[4]) + "\nForsikring: " + dFormat.format(utgiftbud[5]) +
			"\nLån: " + dFormat.format(utgiftbud[6]) + "\nMat: " + dFormat.format(utgiftbud[7]) + 
			"\nKlær: " + dFormat.format(utgiftbud[8]) + "\nDyr: " + dFormat.format(utgiftbud[9]) +
			"\nReise: " + dFormat.format(utgiftbud[10]) + "\nPersonlig: " + dFormat.format(utgiftbud[11]) +
			"\nAnnet: " + dFormat.format(utgiftbud[12]) +
			"\n____________________________\nSum:\n" + dFormat.format(uList.sumMonthlyExpenses()));
}
public void findByDate(String d){
	String temp = d;
	modell.setRowCount(0);
	String incoming = temp.substring(3);
	System.out.println(incoming);
	for(int i = 0; i < exList.expLogList.size(); i++)
	{
		temp = exList.expLogList.get(i).getDate().substring(3);
		if(temp.matches(incoming))
			modell.addRow(exList.expLogList.get(i).toArrayPrint());

	}
}
public void printCalc(double[] s){
		
		double[]utgift = s;
		double sumInt = 0;
		for(int i = 0; i < utgift.length; i++)
		{
			//utgiftI[i] = (int)utgift[i];
			sumInt += utgift[i];
		}
		double totalExpenses = sumInt;
		//Sender data inn i tekstfeltet info2
				info2.setText("REELLE UTGIFTER:\n———————————\nHusleie: " + dFormat.format(utgift[0]) + "\nStrøm: " + dFormat.format(utgift[1]) + 
						"\nInternett: " + dFormat.format(utgift[2]) + "\nTelefon: " + dFormat.format(utgift[3]) +
						"\nSparing: " + dFormat.format(utgift[4]) + "\nForsikring: " + dFormat.format(utgift[5]) +
						"\nLån: " + dFormat.format(utgift[6]) + "\nMat: " + dFormat.format(utgift[7]) + 
						"\nKlær: " + dFormat.format(utgift[8]) + "\nDyr: " + dFormat.format(utgift[9]) +
						"\nReise: " + dFormat.format(utgift[10]) + "\nPersonlig: " + dFormat.format(utgift[11]) +
						"\nAnnet: " + dFormat.format(utgift[12]) +
						"\n_____________________\nSum:\n" + dFormat.format(totalExpenses));
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
				//
				graph3print.setHusleieArc(husleie2);
				graph3print.setHusleieArc(husleie2);
				graph3print.setStromStart(husleie2);
				graph3print.setStromArc(strom2);
				graph3print.setItStart(strom2 + husleie2);
				graph3print.setItArc(internett2);
				graph3print.setTeleStart(internett2 + strom2 + husleie2);
				graph3print.setTeleArc(tele2);
				graph3print.setMatStart(tele2 + internett2 + strom2 + husleie2);
				graph3print.setMatArc(mat2);
				graph3print.setKledeStart(mat2 + tele2 + internett2 + strom2 + husleie2);
				graph3print.setKledeArc(klede2);
				graph3print.setDyrStart(klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
				graph3print.setDyrArc(dyr2);
				graph3print.setReiseStart(dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
				graph3print.setReiseArc(reise2);
				graph3print.setPersStart(reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
				graph3print.setPersArc(personlig2);
				graph3print.setSpareStart(personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
				graph3print.setSpareArc(sparing2);
				graph3print.setSikkerStart(sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
				graph3print.setSikkerArc(forsikring2);
				graph3print.setLanStart(forsikring2  + sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
				graph3print.setLanArc(lan2);
				graph3print.setAnnetStart(lan2 + forsikring2  + sparing2 + personlig2 + reise2  + dyr2 + klede2 + mat2 + tele2 + internett2 + strom2 + husleie2);
				graph3print.setAnnetArc(annet2);
	}

}
