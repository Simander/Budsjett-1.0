import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.UIManager.*;


public class Budsjett implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int max = 1000;
	private static int temp = 0;
	private static int temp2 = 0;
	private static boolean exec = true;
	
	static GUI gui = null;
	
	public static void main(String[] args)
	{
		//Setting nimbus as the look and feel
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		final LoadingThreads ls = new LoadingThreads();
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		ls.setSize(300,410);
		ls.setLocation(dim.width/2-ls.getWidth()/2, dim.height/2-ls.getHeight()/2);
		ls.setVisible(true);
		ls.setResizable(false);
		ls.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		

		//GUI gra = null;
		
		//Runnable for thread t1 viser splash
		Runnable r1 = new Runnable(){public void run(){
		
			while(ls.loadingBar.getValue()<max && temp2 <= max){
				try {
					//System.out.println("galt");
					if(exec==true){
						Thread.sleep(50);
						temp = ls.loadingBar.getValue() + 5;
						ls.loadingBar.setValue(temp);
						if(ls.loadingBar.getValue() == max)
						 {	 //kode for å skjule splashscreen
							 //control = true;
						
							 ls.dispose();
							 break;
						 }
					}
					 if(exec == false && ls.loadingBar.getValue() < max){
						 Thread.sleep(5);
						 
						 temp = ls.loadingBar.getValue() + 5;
							ls.loadingBar.setValue(temp);
						 if(ls.loadingBar.getValue() == max)
						 {	 //kode for å skjule splashscreen
							 //control = true;
						
							 ls.dispose();
							 gui.setVisible(true);
							 break;
						 }
					 }
					 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		//Thread for creating the gui
		Runnable r2 = new Runnable(){public void run(){
			while(exec == true){
				//Creating lists
						InntektsListe il = new InntektsListe();
						UtgiftsListe ul = new UtgiftsListe();
						ExpLogList el = new ExpLogList();
						//Creating the GUI
						gui = new GUI(il, ul, el);
						gui.setVisible(false);
						gui.setSize(1024, 768);
						gui.setLocation(dim.width/2-gui.getWidth()/2, dim.height/2-gui.getHeight()/2);
						gui.setResizable(false);
						gui.setAlwaysOnTop(false);
						//Custom windowListener for the GUI(System.exit(0) with writeToFile())
						gui.addWindowListener( new WindowAdapter()
				        {
				            public void windowClosing ( WindowEvent e)
				            { 
				               // Vinduet skriver til fil
				                Object[] valg = {"Ja", "Nei"};
				                int check = JOptionPane.showOptionDialog(null,"Du er i ferd med å avslutte programmet, vil du lagre?", "Avslutning!",
				                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, valg, valg[1]);
				               
				                if(check == 0)
				                {
				                	gui.oversikt.graf1.setExecute(false);
				                    gui.writeToFile();
				                    System.exit(0);
				                }
				                else if(check == 1)
				                {
				                	gui.oversikt.graf1.setExecute(false);
				                    System.exit(0);
				                }
				            }
				          
				        });
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						exec = false;
				}
						
			}
		};
		Thread tCount = new Thread(r1);
		Thread createGUI = new Thread(r2);
		tCount.start();
		createGUI.start();
	}
}
