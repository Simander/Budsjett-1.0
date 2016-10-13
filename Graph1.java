import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph1 extends JPanel implements Runnable, Printable{
	private int expenseStart = 0;
	private int expenseArc = 0;
	private int expenseArc2 = 0;
	private int incomeStart2 = 0;
	private int incomeArc2 = 0;
	private int incomeArc = 0;
	private double income = 0.0;
	private double expenses = 0.0;
	private Thread animationThread;
	protected Thread a;
	private String line1 = "";
	private String line2 = "";
	private String line3 = "";
	
	//private String line4 ="";
	private boolean drawBool = false;

	private boolean execute = true;
	private int state = 0; //0 = not running 1 = running, 2 = done running
	Graph1()
	{
		animationThread = new Thread(this);
		animationThread.start();
		state = 1;
	}
	//lokasjon
	private int x = 400;
	private int y = 60;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.fillRect(0, 5, 320, 320);
		
		g2d.setColor(Color.DARK_GRAY);
		if(drawBool == true){
			g2d.fillOval(330, 15, 30, 30);
			g2d.drawString("Utgifter", 366, 32);
		}
		g2d.fillArc(x, y, 260, 260, expenseStart, expenseArc2);
		if(income > expenses)
			g2d.setColor(Color.GREEN);
		else
			g2d.setColor(Color.RED);
		
			g2d.fillArc(x, y, 260, 260, incomeStart2, incomeArc2);
			if(drawBool == true){
				g2d.fillOval(330, 65, 30, 30);
				g2d.setColor(Color.BLACK);
				g2d.drawString("Rest", 366, 88);
			}
			//Strings:
			g2d.setColor(Color.BLACK);
			g2d.drawString(line1, 600, 30);
			g2d.drawString(line2, 600, 45);
			g2d.drawString(line3, 600, 60);
			
			//g2d.drawString(line4,350,350);
		
	}
	public void setDrawBool(boolean b){
		drawBool = b;
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
	}
	
	public void setIncArc(int a)
	{
		incomeArc = a;
	}
	public void setIncStart2(int a)
	{
		incomeStart2 = a;
	}
	public void setX(int i){
		x = i;
	}
	public void setY(int i){
		y = i;
	}
	public void setLine1(String l){
		line1 = l;
	}
	public void setLine2(String l){
		line2 = l;
	}
	public void setLine3(String l){
		line3 = l;
	}
	//public void setLine4(String l){
	//	line4 = l;
	//}
	//starts stops the run() methods thread
	public void setExecute(boolean b){
		execute = b;
	}
	public boolean killExecution(){
		return execute;
	}
	//nulls out the graph for new animation
	public void nullOut(){
		expenseStart = 0;
		expenseArc = 0;
		expenseArc2 = 0;
		incomeStart2 = 0;
		incomeArc = 0;
		incomeArc2 = 0;
	}
	//Method that returns an int representing the state of the animation thread
	public int getState(){
		return state;
	}
	//Method for changing the state of the thread, to prevent animating what´s already there
	public void setState(int i){
		{
			state = i;
			if(state == 0)
			{
				//animation thread waiting ready to be run;
			}
			else if(state == 1)	//restart
			{
				synchronized(Graph1.this){
					notify();
					state = 1;
					System.out.println("loading animation");
				}
			}
			else if(state ==2)
			{
				
				//animation thread waiting, not ready to run
			}
		}
	}
	/*
	 * run()-method that draws the piechart-pieces growing from zero to their real percentage
	 */
	
	@Override
	public void run()
	{
		synchronized(Graph1.this)
		{

			while(execute == true){
					while(expenseArc2 < expenseArc)
					{	
						expenseArc2 += 1;
						try {
							Thread.sleep(2);
							repaint();
						} 
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					while((expenseArc2 == expenseArc && incomeArc2 < incomeArc) )//|| (expenseArc == 0 && incomeArc == 0))
					{
						repaint();
						incomeArc2 += 1;
						try{
							Thread.sleep(2);
							repaint();
							//System.out.println(incomeArc + "\n" + incomeArc2);
							if(incomeArc2 == incomeArc)
							{	
								System.out.println("done animating");
								//System.out.println("+1");
								state = 2;	
								
								wait();
								
							}
						}
						catch(InterruptedException e){
							
						}

					}
			}
			
		}	
	}
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		return 0;
	}

		
}

