import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UtgiftsReg extends JPanel
{
	private static final long serialVersionUID = 1L;
	private LyttR lyttR;
	private JTextField kommentar;
	private JComboBox<String> uType;
	private JComboBox<String> uFrequency;
	private JTextField uPrice;
	private String[] typer = {"Husleie", "Strøm", "Internett", "Telefon", "Sparing", "Forsikring", "Lån", "Mat", "Klær", "Dyr", "Reise", "Personlig"};  
	private String[] frekvens = {"Månedlig", "Årlig"};
	private JButton reg;
	protected UtgiftsListe uList;
	JPanel top;
	JPanel bottom;

	private String [] modellcolumn = {"UtgiftsID", "Type", "Frekvens", "Utgift", "Kommentar"};
	protected JScrollPane scrollExpenseTable;
    protected DefaultTableModel modell = new DefaultTableModel(); 
    protected JTable table = new JTable(modell);  
    KeyListener listener;

	UtgiftsReg(UtgiftsListe ul) 
	{
		listener = new MyKeyListener();
		
		table.addKeyListener(listener);
		uList = ul;
		for(int i = 0; i < modellcolumn.length; i++)
		{
			modell.addColumn(modellcolumn[i]);
		}
		//super("UtgiftReg");
		top = new JPanel();
		bottom = new JPanel();
		lyttR = new LyttR();
		kommentar = new JTextField(10);
		uType = new JComboBox<>(typer);
		uFrequency = new JComboBox<>(frekvens);
		uPrice = new JTextField(10);
		reg = new JButton("Registrer");
		reg.addActionListener(lyttR);	
		top.add(new JLabel("Type: "));
		top.add(uType);
		top.add(new JLabel("Frekvens: "));
		top.add(uFrequency);
		top.add(new JLabel("Kostnad: "));
		top.add(uPrice);
		top.add(new JLabel("Kommentar: "));
		top.add(kommentar);
		top.add(reg);
		scrollExpenseTable = new JScrollPane(table);
		scrollExpenseTable.setPreferredSize(new Dimension(1024,600));
		bottom.add(scrollExpenseTable);
		setLayout(new BorderLayout());
		//top.setPreferredSize(new Dimension(1024, 100));
		//bottom.setPreferredSize(new Dimension(1024, 4000));
		
		add(top, BorderLayout.PAGE_START);
		add(bottom, BorderLayout.LINE_START);
	}
	
	public void uRegistre()
	{
		
		if(uPrice.getText().isEmpty() || kommentar.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "Vennligst fyll ut alle felter!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		else
		{	
			try{
				String utype = (String)uType.getSelectedItem();
				String ufreq = (String)uFrequency.getSelectedItem();
				String tempincome = commaCheck(uPrice.getText());
				double inCome = Double.parseDouble(tempincome);
				String icomment = kommentar.getText();
				Utgifter u = new Utgifter(utype, ufreq, inCome, icomment);
				uList.settInnUtgift(u);
				//info.setText(iList.listInntekter());
				String[] temp= new String[5];
				temp[0] = u.getUNr();
				temp[1] = utype;
				temp[2] = ufreq;
				temp[3] = tempincome;
				temp[4] = icomment;
				modell.addRow(temp);
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "Ugyldig tallverdi i utgift!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public UtgiftsListe getList()
	{
		return uList;
	}
	public void addToTable()
	{
		LinkedList<String[]> lista = uList.listTable();
		for(int i = 0; i < lista.size(); i++)
		{
			modell.addRow(lista.get(i));
		}
	}
	public void remove()
	{
		
		int col = table.getSelectedRow();
		uList.fjernObjekt(col);
		//System.out.println(col);
		modell.setRowCount(0);
		addToTable();
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
	
	public String[][] saveTableData()
	{
		
		int temp = modell.getRowCount();
		
		String[][] temp1 = new String[temp][5]; 
		for(int i = 0; i < temp; i++)
		{
			for(int u = 0; u < 5; u++)
			{
				temp1[i][u] = (String) modell.getValueAt(i, u);
			}
		}
		return temp1;
	}
	public void storeToClass()
	{
		String[][] temp = saveTableData();

			uList.saveDate(temp);
		
	}
	
    //privat lytteklasse
    private class LyttR implements ActionListener
    {
    	
    	
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == reg)
                 uRegistre();
    
        }
    }
	public class MyKeyListener implements KeyListener {
		int en = 0;
		int check = 0;
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_DELETE)
				remove();
			else if(e.getKeyCode() == 157)// || e.getKeyCode() == 68)
			{
				en += 1;
				if(en >1 && check == 0)
				{
					remove();
					check+=1;
				}
				
			}
			else if(e.getKeyCode() == 68)
			{	
				en+=1;
				if (en > 1 && check == 0)
					remove();
					check +=1;
			}
			else if(e.getKeyCode() == 16)
				uList.listIndexes();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == 157)// || e.getKeyCode() == 68)
			{
				en -= 1;
				if(check > 0)
					check = 0;
			}
			else if(e.getKeyCode() == 68)
			{
				en-=1;
				if(check > 0)
					check = 0;
			}
			
		}
	}
}
