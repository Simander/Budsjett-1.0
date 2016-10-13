import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph3print extends JPanel {
	//Graph location
	private int x = 400-150-150;
	private int y = 20;
	
	private int dotx = 310 - 150-150;
	private int textx = 340 -150-150;
	//Graph size
	private int width = 300-50;
	private int height = 300-50;
	
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
		g2d.fillOval(dotx, 10, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Husleie", textx, 20);
		//Strøm
		g2d.setColor(Color.YELLOW);		//farge
		g2d.fillArc(x, y, width, height, stromStart, stromArc);		//Kakestykke
		g2d.fillOval(dotx, 30, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Strøm", textx, 40);
		//Internet
		g2d.setColor(Color.BLUE);		//farge
		g2d.fillArc(x, y, width, height, itStart, itArc);			//Kakestykke
		g2d.fillOval(dotx, 50, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Internet", textx, 60);
		//Telefon
		g2d.setColor(Color.CYAN);				//farge
		g2d.fillArc(x, y, width, height, teleStart, teleArc);		//Kakestykke
		g2d.fillOval(dotx, 70, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Telefon", textx, 80);
		//mat
		g2d.setColor(Color.ORANGE);		//farge
		g2d.fillArc(x, y, width, height, matStart, matArc);			//Kakestykke
		g2d.fillOval(dotx, 90, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Mat", textx, 100);
		//klær
		g2d.setColor(Color.PINK);		//farge
		g2d.fillArc(x, y, width, height, kledeStart, kledeArc);		//Kakestykke
		g2d.fillOval(dotx, 110, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Klær", textx, 120);
		//dyr
		g2d.setColor(Color.MAGENTA);	//farge
		g2d.fillArc(x, y, width, height, dyrStart, dyrArc);			//Kakestykke
		g2d.fillOval(dotx, 130, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Dyr", textx, 140);
		//reise
		g2d.setColor(Color.getHSBColor(225, 300, 150));
		g2d.fillArc(x, y, width, height, reiseStart, reiseArc);		//Kakestykke
		g2d.fillOval(dotx, 150, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Reise", textx, 160);
		//personlig
		g2d.setColor(Color.LIGHT_GRAY);	//farge
		g2d.fillArc(x, y, width, height, persStart, persArc);		//Kakestykke
		g2d.fillOval(dotx, 170, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Personlig", textx, 180);
		//Sparing
		g2d.setColor(Color.GREEN);	//farge
		g2d.fillArc(x, y, width, height, spareStart, spareArc);		//Kakestykke
		g2d.fillOval(dotx, 190, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Sparing", textx, 200);
		//Forsikring
		g2d.setColor(Color.getHSBColor(150, 350, 100));	//farge
		g2d.fillArc(x, y, width, height, sikkerStart, sikkerArc);		//Kakestykke
		g2d.fillOval(dotx, 210, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Forsikring", textx, 220);
		//Lån
		g2d.setColor(Color.RED);	//farge
		g2d.fillArc(x, y, width, height, lanStart, lanArc);		//Kakestykke
		g2d.fillOval(dotx, 230, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Lån", textx, 240);
		//Annet
		g2d.setColor(Color.getHSBColor(150, 300, 100));
		g2d.fillArc(x, y, width, height, annetStart, annetArc);		//Kakestykke
		g2d.fillOval(dotx, 250, 15, 15);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Annet", textx, 260);
		//g2d.scale(0.5, 0.5);
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
