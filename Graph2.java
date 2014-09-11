import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph2 extends JPanel {
	//Graph location
	private int x = 400-150;
	private int y = 20;
	
	private int dotx = 310 - 150;
	private int textx = 340 -150;
	//Graph size
	private int width = 300;
	private int height = 300;
	int sumdiff = 0;
	//husleieStykket
	private int husleieStart = 0;
	private int husleieArc = 0;
	//StrømStykket
	private int stromStart = 0;
	private int stromArc = 0;
	//InternetStykket
	private int itStart = 0;
	private int itArc = 0;
	//InternetStykket
	private int teleStart = 0;
	private int teleArc = 0;
	//MatStykket
	private int matStart = 0;
	private int matArc = 0;
	//KlærStykket
	private int kledeStart = 0;
	private int kledeArc = 0;
	//DyrStykket
	private int dyrStart = 0;
	private int dyrArc = 0;
	//ReiseStykket
	private int reiseStart = 0;
	private int reiseArc = 0;
	//PersonligStykket
	private int persStart = 0;
	private int persArc = 0;
	//SparingStykket
	private int spareStart = 0;
	private int spareArc = 0;
	//ForsikringStykket
	private int sikkerStart = 0;
	int sikkerArc = 0;
	//LånStykket
	private int lanStart = 0;
	private int lanArc = 0;
	//Annet
	private int annetStart = 0;
	private int annetArc = 0;
	double[] diff = new double[14];
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//UTGIFTER
		//Husleie
		g2d.setColor(Color.DARK_GRAY);	//farge
		g2d.fillArc(x, y, width, height, husleieStart, husleieArc);	//Kakestykke
		g2d.fillOval(dotx, 10, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Husleie", textx, 25);
		//Strøm
		g2d.setColor(Color.YELLOW);		//farge
		g2d.fillArc(x, y, width, height, stromStart, stromArc);		//Kakestykke
		g2d.fillOval(dotx, 40, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Strøm", textx, 55);
		//Internet
		g2d.setColor(Color.BLUE);		//farge
		g2d.fillArc(x, y, width, height, itStart, itArc);			//Kakestykke
		g2d.fillOval(dotx, 70, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Internet", textx, 85);
		//Telefon
		g2d.setColor(Color.CYAN);				//farge
		g2d.fillArc(x, y, width, height, teleStart, teleArc);		//Kakestykke
		g2d.fillOval(dotx, 100, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Telefon", textx, 115);
		//mat
		g2d.setColor(Color.ORANGE);		//farge
		g2d.fillArc(x, y, width, height, matStart, matArc);			//Kakestykke
		g2d.fillOval(dotx, 130, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Mat", textx, 145);
		//klær
		g2d.setColor(Color.PINK);		//farge
		g2d.fillArc(x, y, width, height, kledeStart, kledeArc);		//Kakestykke
		g2d.fillOval(dotx, 160, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Klær", textx, 175);
		//dyr
		g2d.setColor(Color.MAGENTA);	//farge
		g2d.fillArc(x, y, width, height, dyrStart, dyrArc);			//Kakestykke
		g2d.fillOval(dotx, 190, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Dyr", textx, 205);
		//reise
		g2d.setColor(Color.getHSBColor(225, 300, 150));
		g2d.fillArc(x, y, width, height, reiseStart, reiseArc);		//Kakestykke
		g2d.fillOval(dotx, 220, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Reise", textx, 235);
		//personlig
		g2d.setColor(Color.WHITE);	//farge
		g2d.fillArc(x, y, width, height, persStart, persArc);		//Kakestykke
		g2d.fillOval(dotx, 250, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Personlig", textx, 265);
		//Sparing
		g2d.setColor(Color.GREEN);	//farge
		g2d.fillArc(x, y, width, height, spareStart, spareArc);		//Kakestykke
		g2d.fillOval(dotx, 280, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Sparing", textx, 295);
		//Forsikring
		g2d.setColor(Color.getHSBColor(150, 350, 100));	//farge
		g2d.fillArc(x, y, width, height, sikkerStart, sikkerArc);		//Kakestykke
		g2d.fillOval(dotx, 310, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Forsikring", textx, 325);
		//Lån
		g2d.setColor(Color.RED);	//farge
		g2d.fillArc(x, y, width, height, lanStart, lanArc);		//Kakestykke
		g2d.fillOval(dotx, 340, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Lån", textx, 355);
		//Annet
		g2d.setColor(Color.getHSBColor(150, 300, 100));
		g2d.fillArc(x, y, width, height, annetStart, annetArc);		//Kakestykke
		g2d.fillOval(dotx, 370, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Annet", textx, 385);
		
		g2d.drawString("Differanse:", 900, 50);
		//diff
		//utgift1
		if(diff[0]< 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + diff[0], 900, 75);
		}
		else if(diff[0] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-"+diff[0], 900, 75);
		}
		//utgift2
		if(diff[1]< 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[1], 900, 90);
		}
		else if(diff[1] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[1], 900, 90);
		}
		if(diff[2] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[2], 900, 105);
		}
		else if(diff[2] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-"+ diff[2], 900, 105);
		}
		if(diff[3] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[3], 900, 120);
		}
		else if(diff[3] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[3], 900, 120);
		}
		if(diff[4] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[4], 900, 135);
		}
		else if(diff[4] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[4], 900, 135);
		}
		if(diff[5] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[5], 900, 150);
		}
		else if(diff[5] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[5], 900, 150);
		}
		if(diff[6] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[6], 900, 165);
		}
		else if(diff[6] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[6], 900, 165);
		}
		if(diff[7] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[7], 900, 180);
		}
		else if(diff[7] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[7], 900, 180);
		}
		if(diff[8] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[8], 900, 195);
		}
		else if(diff[8] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[8], 900, 195);
		}
		if(diff[9] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[9], 900, 210);
		}
		else if(diff[9] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[9], 900, 210);
		}
		if(diff[10] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[10], 900, 225);
		}
		else if(diff[10] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[10], 900, 225);
		}
		if(diff[11] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[11], 900, 240);
		}
		else if(diff[11] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[11], 900, 240);
		}
		if(diff[12] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[12], 900, 255);
		}
		else if(diff[12] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[12], 900, 255);
		}
		if(diff[13] < 0){
			g2d.setColor(Color.GREEN);
			g2d.drawString("+" + -diff[13], 900, 300);
		}
		else if(diff[13] > 0){
			g2d.setColor(Color.RED);
			g2d.drawString("-" + diff[13], 900, 300);
		}

		
		//rest
		/*if(income > expenses)
			g2d.setColor(Color.GREEN);	//farge
		else
			g2d.setColor(Color.RED);
		g2d.fillArc(x, y, width, height, restStart, restArc);		//Kakestykke
		g2d.fillOval(310, 370, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Rest", 340, 385);
		*/
		
	}
	public void setDiff(double[] d){
		diff = d;
	}
	public void setHusleieStart(int h){
		husleieStart = h;
	}
	public void setHusleieArc(int h){
		husleieArc = h;	
	}
	public void setStromStart(int s){
		stromStart = s;
	}
	public void setStromArc(int s){
		stromArc = s;
	}
	public void setItStart(int i){
		itStart = i;
	}
	public void setItArc(int i){
		itArc = i;
	}
	public void setTeleStart(int i){
		teleStart = i;
	}
	public void setTeleArc(int i){
		teleArc = i;
	}
	public void setMatStart(int m){
		matStart = m;
	}
	public void setMatArc(int m){
		matArc = m;
	}
	public void setKledeStart(int k){
		kledeStart = k;
	}
	public void setKledeArc(int k){
		kledeArc = k;
	}
	public void setDyrStart(int d){
		dyrStart = d;
	}
	public void setDyrArc(int d){
		dyrArc = d;
	}
	public void setReiseStart(int r){
		reiseStart = r;
	}
	public void setReiseArc(int r){
		reiseArc = r;
	}
	public void setPersStart(int p){
		persStart = p;
	}
	public void setPersArc(int p){
		persArc = p;
	}
	public void setSpareStart(int s){
		spareStart = s;
	}
	public void setSpareArc(int s){
		spareArc = s;
	}
	public void setSikkerStart(int s){
		sikkerStart = s;
	}
	public void setSikkerArc(int s){
		sikkerArc = s;
	}
	public void setLanStart(int l){
		lanStart = l;
	}
	public void setLanArc(int l){
		lanArc = l;
	}
	public void setAnnetStart(int s){
		annetStart = s;
	}
	public void setAnnetArc(int s){
		annetArc = s;
	}
	public void setRestStart(int r){
	}
	public void setRestArc(int r){
	}
	public void setIncome(double i){
	}
	public void setExpenses(double i){
	}
	public void setX(int i){
		x = i;
	}
	public void setY(int i){
		y = i;
	}
}
