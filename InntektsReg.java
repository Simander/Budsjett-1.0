import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;



public class InntektsReg extends JPanel
{
	private static final long serialVersionUID = 1L;	//version ID for serializing
	
	
	private LyttR lyttR;				//private listener
    private KeyListener listener;		//private KeyListener
    
	protected JTextField aName;			//TextField for name
	private JComboBox<String> iType;	//JComboBox for selecting income types
	private String[] typer = {"Jobb", "Studielån", "Pensjon", "Annen inntekt"};  //String array with data for the JComboBox
	private JTextField inntekt;			//TextfField for input of income
	private JTextField kommentar;		//TextField for comments
	private JButton reg;				//JButton which trigger a registration event.
	protected InntektsListe iList;		//incomeList for storing and retrieving income data
	private JPanel top;							//Top-panel
	private JPanel bottom;						//Bottom-panel
	private String [] modellcolumn = {"Navn", "Type", "Inntekt", "Kommentar"};	//String array with column-names for the TableModel
	protected JScrollPane scrollIncomeTable;	//JScrollPane for holding the income table
    protected DefaultTableModel modell = new DefaultTableModel(); 	//A table-model for the income table 
    protected JTable table = new JTable(modell);  //creates a new JTable from the table-model

	
	InntektsReg(InntektsListe il) 
	{
		
		NumberFormat integerFormat = NumberFormat.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(integerFormat);
		formatter.setAllowsInvalid(false);
		lyttR = new LyttR();				//initiates the listener
		listener = new MyKeyListener(); //initiates the keyListener
		table.addKeyListener(listener);	//adds the KeyListener to the table
		
		//runs through the column-name array and adds columns to the table-model
		for(int i = 0; i < modellcolumn.length; i++)
		{
			modell.addColumn(modellcolumn[i]);
		}
		
		iList = il;							//initiates the income-list
		top = new JPanel();					//initiates the top-panel
		bottom = new JPanel();				//initiates the bottom-panel
		
		aName = new JTextField(10);			//initiates JTextField
		aName.setToolTipText("Navn på personen hvis inntekt det er");
		kommentar= new JTextField(10);		//initiates JTextField
		kommentar.setToolTipText("Valgfri kommentar");
		iType = new JComboBox<>(typer);		//initiates the JComboBox
		iType.setToolTipText("Klikk for å velge inntektstype");
		inntekt = new JTextField(10);		//initiates JTextField
		inntekt.setToolTipText("Utgift i kr: bruk punktum for komma");
		inntekt.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c!=KeyEvent.VK_PERIOD)) {
			         e.consume();  // ignore event
			      }
			   }
			});
		reg = new JButton("Registrer");		//initiates the JButton
		reg.setToolTipText("Klikk for å registrere ny inntekt!");
		reg.addActionListener(lyttR);		//adds the listener to the JButton
		top.add(new JLabel("Navn: "));		//adds a JLabel to top-panel
		top.add(aName);						//adds the JTextField for name input to the top-panel
		top.add(new JLabel("Type: "));		//adds a JLabel to the top-panel
		top.add(iType);						//adds the JComboBox for selecting income types
		top.add(new JLabel("Inntekt: "));	//adds a JLabel to the top-panel
		top.add(inntekt);					//adds the JTextfield for input of income to the top-panel
		top.add(new JLabel("Kommentar: "));	//adds a JLabel to the top-panel
		top.add(kommentar);					//adds the JTextField for input of comments to the top-panel
		top.add(reg);						//adds the JButton for triggering registration events to the top-panel
		
		scrollIncomeTable = new JScrollPane(table);						//adds the table to scrollPane
		scrollIncomeTable.setPreferredSize(new Dimension(1024,600));	//sets the size of the scrollPane
		bottom.add(scrollIncomeTable);									//adds the scrollPane to the bottom-panel
		setLayout(new BorderLayout());									//sets the layout of this panel to BorderLayout
		table.setToolTipText("Hvis linje er valgt, trykk delete for å slette linje");
		add(top, BorderLayout.PAGE_START);								//adds top-panel to the top of this panel
		add(bottom, BorderLayout.LINE_START);							//adds bottom-panel to the bottom of this panel
		
	}
	
	public void register()
	{
		if( (aName.getText().equals("") || inntekt.getText().equals("")))
			JOptionPane.showMessageDialog(null, "Vennligst fyll ut alle felter!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		else
		{
			
					String itype = (String)iType.getSelectedItem();
					String tempincome = commaCheck(inntekt.getText());
					double inCome = Double.parseDouble(tempincome);
					String icomment = kommentar.getText();
			
					Inntekter i = new Inntekter(aName.getText(), itype, inCome, icomment);
					iList.settInnInntekt(i);
					String[] temp= new String[4];
					temp[0] = aName.getText();
					temp[1] = itype;
					temp[2] = tempincome;
					temp[3] = icomment;
					modell.addRow(temp);
		}
		
	}

	public void addToTable()
	{
		LinkedList<String[]> lista = iList.listTable();
		for(int i = 0; i < lista.size(); i++)
		{
			modell.addRow(lista.get(i));
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
	//Method that returns a double string array of data from the table
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
	//Method which reads the income table and overwrites the list-objects with changes
	public void storeToClass()
	{
		String[][] temp = saveTableData();

			iList.saveDate(temp);
		
	}
	//Method for deleting a table entry
	public void deleteEntry()
	{
		
		int col = table.getSelectedRow();	//gets the selected row number
		table.selectAll();
		iList.deleteObject(col);				//goes through the list of income entries and removes the list objekt.
		modell.setRowCount(0);				//sets the table row count to zero
		addToTable();						//reloads tabledata
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
		int check = 0;
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_DELETE)
				deleteEntry();
			else if(e.getKeyCode() == 157)// || e.getKeyCode() == 68)
			{
				check+=1;
				if(check == 2)
					deleteEntry();
			}
			else if(e.getKeyCode() == 68)
			{
				
				check+=1;
				if(check == 2)
					deleteEntry();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == 157)// || e.getKeyCode() == 68)
			{
				check -=1;
			}
			else if(e.getKeyCode() == 68)
			{
				check -=1;
			}
			
		}
}
}
