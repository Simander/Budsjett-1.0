import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Budsjett 
{
	public static void main(String[] args)
	{
		InntektsListe il = new InntektsListe();
		UtgiftsListe ul = new UtgiftsListe();
		final GUI gui = new GUI(il, ul);
		gui.setVisible(true);
		gui.setSize(1024, 600);
	
		gui.addWindowListener( new WindowAdapter()
        {
            public void windowClosing ( WindowEvent e)
            { 
               // Vinduet skriver til fil
                Object[] valg = {"Ja", "Nei"};
                int check = JOptionPane.showOptionDialog(null, "Du er i ferd med å avslutte programmet, vil du lagre?", "Avslutning!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, valg, valg[1]);
               
                if(check == 0)
                {
                    gui.writeToFile();
                    System.exit(0);
                }
                else if(check == 1)
                {
                    System.exit(0);
                }
            }
        });
	}
}
