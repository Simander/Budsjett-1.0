import javax.swing.*;

import java.awt.*;
public class LoadingThreads extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int max = 1000;
	JFrame loadingScreen = new JFrame("Laster inn Budsjett 1.0");

	
	protected JPanel c = new JPanel();
	protected JPanel bildepanel = new JPanel();
	protected JLabel message = new JLabel("Laster...");
	protected JProgressBar loadingBar = new JProgressBar(0,max);
	protected ImageIcon bilde = new ImageIcon("src/splash.jpg");
	PaintablePanel pp;
	//TimerThread tim = new TimerThread();
	/*int temp = 0;
	int temp2 = 0;
	double tempTid = 0;
	boolean exec = true;
	Runnable r1;
	Thread tCount;
	boolean check = false;
	boolean control = false;
	//Runnable r2;*/
	LoadingThreads(){
		
		super("Laster inn Budsjett 1.0");
		//final Thread a = new Thread(this);
		this.setAlwaysOnTop(true);
	
		pp = new PaintablePanel();
	
		this.add(c);
		c.add(pp);
		pp.setPreferredSize(new Dimension(300,300));
		
		if(bilde == null)System.out.println("Fail");
		//bilde
		bildepanel.setLocation(300, 300);
		c.setPreferredSize(new Dimension(300,420));
		//c.setLayout(new BorderLayout());
		c.add(bildepanel);
		message.setLocation(130,330);
		loadingBar.setLocation(40, 340);
		loadingBar.setStringPainted(true);
		c.add(message);
		c.add(loadingBar);
		loadingBar.setPreferredSize(new Dimension(300,50));
	
	}
	protected JProgressBar getJP(){
		return loadingBar;
	}
		//wait();
	};
		
	
	



	

