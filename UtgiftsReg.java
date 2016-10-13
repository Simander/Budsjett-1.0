import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UtgiftsReg extends JPanel implements Serializable
{
	private static final long serialVersionUID = 1L;	//version ID for serializing
	
	private KeyListener listener;				//private keyListener
	private LyttR lyttR;						//private listener
	private JTextField kommentar;				//JTextField for input of comments
	private JComboBox<String> uType;			//JComboBox for selecting types of expenses
	private JComboBox<String> uFrequency;		//JComboBox for selecting frequency of expenses
	private JTextField uPrice;					//JTextField for input of amount of expenses
	private String[] typer = {"Husleie", "Strøm", "Internett", "Telefon", "Sparing", "Forsikring", "Lån", "Mat", "Klær", "Dyr", "Reise", "Personlig", "Annet"};  
	private String[] frekvens = {"Månedlig", "Årlig"};
	private JButton reg;						//JButton for triggering a registration event
	
	protected UtgiftsListe uList;				//List of expenses
	JPanel top;									//Top-panel
	JPanel bottom;								//Bottom-panel

	private String [] modellcolumn = {"Type", "Frekvens", "Utgift", "Kommentar"};	//String array holding column-names for the table model
	protected JScrollPane scrollExpenseTable;							//JScrollPane for holding the expense-table
    protected DefaultTableModel modell = new DefaultTableModel(); 		//Default table model
    protected JTable table = new JTable(modell);  						//Creating a new JTable from the table model
    

	UtgiftsReg(UtgiftsListe ul) 
	{
		lyttR = new LyttR();							//initiates the listener
		listener = new MyKeyListener();					//initiates the keyListener
		table.addKeyListener(listener);					//adds the keyListener to the table
		uList = ul;										//initiates the expenses-list
		//runs through the array of collumn-names and adds them to the table
		for(int i = 0; i < modellcolumn.length; i++)
		{
			modell.addColumn(modellcolumn[i]);
		}
		/*
		modell.addColumn("Type", new String[] {""});
		modell.addColumn("Frekvens", new String[] {""});
		modell.addColumn("Utgift", new Object[]{new Double(0.0)});
		modell.addColumn("Kommentar", new String[]{""});*/
		
		top = new JPanel();								//initiates top-panel
		bottom = new JPanel();							//initiates bottom-panel
		kommentar = new JTextField(10);					//initiates the TextField for input of comments
		kommentar.setToolTipText("Valgfri kommentar");
		uType = new JComboBox<>(typer);					//initiates the JComboBox for selecting expense types
		uType.setToolTipText("Klikk for å velge utgiftstype");
		uFrequency = new JComboBox<>(frekvens);			//initiates the JComboBox for selecting frequency of the expenses
		uFrequency.setToolTipText("Klikk for å velge hyppighet");
		uPrice = new JTextField(10);					//initiates the JTextField for input of expenses
		uPrice.setToolTipText("Utgift i kr: bruk punktum for komma");
		uPrice.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c!=KeyEvent.VK_PERIOD)) {
			         e.consume();  // ignore event
			      }
			   }
			});
		reg = new JButton("Registrer");					//initiates the JButton for triggering the registration event
		reg.addActionListener(lyttR);					//adds the listener to the button
		reg.setToolTipText("Klikk for å registrere ny utgift!");
		top.add(new JLabel("Type: "));					//adds a JLabel to the top-panel
		top.add(uType);									//adds the JComboBox for selecting expense types
		top.add(new JLabel("Frekvens: "));				//adds a JLabel to the top-panel
		top.add(uFrequency);							//adds the JComboBox for selecting frequency of the expenses
		top.add(new JLabel("Kostnad: "));				//adds a JLabel to the top-panel
		top.add(uPrice);								//adds the JTextField for input of expenses to the top-panel
		top.add(new JLabel("Kommentar: "));				//adds a JLabel to the top-panel
		top.add(kommentar);								//adds the JTextField for input of comments to the top-panel
		top.add(reg);									//adds the JButton for triggering registration events to the top-panel
		table.setToolTipText("Hvis linje er valgt, trykk delete for å slette linje");
		scrollExpenseTable = new JScrollPane(table);	//initiates the JScrollPane containing the expense-table
		scrollExpenseTable.setPreferredSize(new Dimension(1024,600));	//sets the size of the ScrollPane
		bottom.add(scrollExpenseTable);					//adds the JScrollPane to the bottom-panel
		setLayout(new BorderLayout());					//sets the layout of this panel to BorderLayout
		//top.setPreferredSize(new Dimension(1024, 100));
		//bottom.setPreferredSize(new Dimension(1024, 4000));
		
		add(top, BorderLayout.PAGE_START);				//adds the top-panel to the top of this panel
		add(bottom, BorderLayout.LINE_START);			//adds the bottom panel to the bottom of this panel
	}
	
	//method for registering new expenses
	public void register()
	{
		
		if(uPrice.getText().isEmpty())
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
				String[] temp= new String[4];
				temp[0] = utype;
				temp[1] = ufreq;
				temp[2] = tempincome;
				temp[3] = icomment;
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
		table.selectAll();
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
		
		String[][] temp1 = new String[temp][4]; 
		for(int i = 0; i < temp; i++)
		{
			for(int u = 0; u < 4; u++)
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
                 register();
    
        }
    }
	private class MyKeyListener implements KeyListener {
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
