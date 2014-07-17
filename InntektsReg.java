import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class InntektsReg extends JPanel
{
	private static final long serialVersionUID = 1L;
	private LyttR lyttR;
	private JTextField aName;
	private JTextField kommentar;
	private JComboBox<String> iType;
	private JTextField inntekt;
	private String[] typer = {"Jobb", "Studielån", "annen inntekt"};  
	private JButton reg;
	protected InntektsListe iList;
	JPanel top;
	JPanel bottom;
	private String [] modellcolumn = {"InntektID", "Navn", "Type", "Inntekt", "Kommentar"};
	protected JScrollPane scrollIncomeTable;
    protected DefaultTableModel modell = new DefaultTableModel(); 
    protected JTable table = new JTable(modell);  
    KeyListener listener;
	
	InntektsReg(InntektsListe il) 
	{
		
		listener = new MyKeyListener();
		table.addKeyListener(listener);
		for(int i = 0; i < modellcolumn.length; i++)
		{
			modell.addColumn(modellcolumn[i]);
		}
		iList = il;
		top = new JPanel();
		bottom = new JPanel();
		lyttR = new LyttR();
		aName = new JTextField(10);
		kommentar= new JTextField(10);
		iType = new JComboBox<>(typer);
		inntekt = new JTextField(10);
		reg = new JButton("Registrer");
		reg.addActionListener(lyttR);
		top.add(new JLabel("Navn: "));
		top.add(aName);
		top.add(new JLabel("Type: "));
		top.add(iType);
		top.add(new JLabel("Inntekt: "));
		top.add(inntekt);
		top.add(new JLabel("Kommentar: "));
		top.add(kommentar);
		top.add(reg);
		scrollIncomeTable = new JScrollPane(table);
		scrollIncomeTable.setPreferredSize(new Dimension(1024,600));
		bottom.add(scrollIncomeTable);
		setLayout(new BorderLayout());
		
		add(top, BorderLayout.PAGE_START);
		add(bottom, BorderLayout.LINE_START);
	}
	
	public void uRegistre()
	{
		if((aName.getText().equals("")) || (kommentar.getText().equals("")) || (inntekt.getText().equals("")))
			JOptionPane.showMessageDialog(null, "Vennligst fyll ut alle felter!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
		else
		{
			try{
					String itype = (String)iType.getSelectedItem();
					String tempincome = commaCheck(inntekt.getText());
					double inCome = Double.parseDouble(tempincome);
					String icomment = kommentar.getText();
			
					Inntekter i = new Inntekter(aName.getText(), itype, inCome, icomment);
					iList.settInnInntekt(i);
					String[] temp= new String[5];
					temp[0] = i.getINr();
					temp[1] = aName.getText();
					temp[2] = itype;
					temp[3] = tempincome;
					temp[4] = icomment;
					modell.addRow(temp);
			
		
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "Ugyldig inntekt, prøv igjen!", "Brukerfeil", JOptionPane.ERROR_MESSAGE);
			}
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

			iList.saveDate(temp);
		
	}
	public void remove()
	{
		
		int col = table.getSelectedRow();
		iList.fjernObjekt(col);
		//System.out.println(col);
		modell.setRowCount(0);
		addToTable();
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
