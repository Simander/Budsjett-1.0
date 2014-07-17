import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Chart extends JPanel {
	int expenseStart = 0;
	int expenseArc = 0;
	int incomeArc = 0;
	int incomeStart = 0;
	private double income = 0.0;
	private double expenses = 0.0;
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.fillRect(0, 5, 320, 320);
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillOval(330, 65, 30, 30);
		g2d.drawString("Utgifter", 366, 88);
		g2d.fillArc(400, 60, 260, 260, expenseStart, expenseArc);
		if(income > expenses)
			g2d.setColor(Color.GREEN);
		else
			g2d.setColor(Color.RED);
		g2d.fillArc(400, 60, 260, 260, incomeStart, incomeArc);
		g2d.fillOval(330, 15, 30, 30);
		g2d.drawString("Rest", 366, 32);
	}
	public void setTotExp(double tex)
	{
		expenses = tex;
	}
	public void setTotInc(double tin)
	{
		income = tin;
	}
	public void setExpStart(int a)
	{
		expenseStart = a;
	}
	
	public void setExpArc(int a)
	{
		expenseArc = a;
	}
	public void setIncStart(int a)
	{
		incomeStart = a;
	}
	
	public void setIncArc(int a)
	{
		incomeArc = a;
	}
}
