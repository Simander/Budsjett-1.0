import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Utdyp extends JPanel {
	//Graf lokasjon
	int x = 400;
	int y = 20;
	//Gra
	int width = 300;
	int height = 300;
	
	private double income = 0.0;
	private double expenses = 0.0;
	
	//husleieStykket
	int husleieStart = 0;
	int husleieArc = 0;
	//StrømStykket
	int stromStart = 0;
	int stromArc = 0;
	//InternetStykket
	int itStart = 0;
	int itArc = 0;
	//InternetStykket
	int teleStart = 0;
	int teleArc = 0;
	//MatStykket
	int matStart = 0;
	int matArc = 0;
	//KlærStykket
	int kledeStart = 0;
	int kledeArc = 0;
	//DyrStykket
	int dyrStart = 0;
	int dyrArc = 0;
	//ReiseStykket
	int reiseStart = 0;
	int reiseArc = 0;
	//PersonligStykket
	int persStart = 0;
	int persArc = 0;
	//SparingStykket
	int spareStart = 0;
	int spareArc = 0;
	//ForsikringStykket
	int sikkerStart = 0;
	int sikkerArc = 0;
	//LånStykket
	int lanStart = 0;
	int lanArc = 0;
	//RestStykket
	int restStart = 0;
	int restArc = 0;
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//UTGIFTER
		//Husleie
		g2d.setColor(Color.DARK_GRAY);	//farge
		g2d.fillArc(x, y, width, height, husleieStart, husleieArc);	//Kakestykke
		g2d.fillOval(310, 10, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Husleie", 340, 25);
		//Strøm
		g2d.setColor(Color.YELLOW);		//farge
		g2d.fillArc(x, y, width, height, stromStart, stromArc);		//Kakestykke
		g2d.fillOval(310, 40, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Strøm", 340, 55);
		//Internet
		g2d.setColor(Color.BLUE);		//farge
		g2d.fillArc(x, y, width, height, itStart, itArc);			//Kakestykke
		g2d.fillOval(310, 70, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Internet", 340, 85);
		//Telefon
		g2d.setColor(Color.CYAN);				//farge
		g2d.fillArc(x, y, width, height, teleStart, teleArc);		//Kakestykke
		g2d.fillOval(310, 100, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Telefon", 340, 115);
		//mat
		g2d.setColor(Color.ORANGE);		//farge
		g2d.fillArc(x, y, width, height, matStart, matArc);			//Kakestykke
		g2d.fillOval(310, 130, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Mat", 340, 145);
		//klær
		g2d.setColor(Color.PINK);		//farge
		g2d.fillArc(x, y, width, height, kledeStart, kledeArc);		//Kakestykke
		g2d.fillOval(310, 160, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Klær", 340, 175);
		//dyr
		g2d.setColor(Color.MAGENTA);	//farge
		g2d.fillArc(x, y, width, height, dyrStart, dyrArc);			//Kakestykke
		g2d.fillOval(310, 190, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Dyr", 340, 205);
		//reise
		g2d.setColor(Color.getHSBColor(225, 300, 150));
		g2d.fillArc(x, y, width, height, reiseStart, reiseArc);		//Kakestykke
		g2d.fillOval(310, 220, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Reise", 340, 235);
		//personlig
		g2d.setColor(Color.WHITE);	//farge
		g2d.fillArc(x, y, width, height, persStart, persArc);		//Kakestykke
		g2d.fillOval(310, 250, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Personlig", 340, 265);
		//Sparing
		g2d.setColor(Color.getHSBColor(150, 300, 100));	//farge
		g2d.fillArc(x, y, width, height, spareStart, spareArc);		//Kakestykke
		g2d.fillOval(310, 280, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Sparing", 340, 295);
		//Forsikring
		g2d.setColor(Color.getHSBColor(150, 350, 100));	//farge
		g2d.fillArc(x, y, width, height, sikkerStart, sikkerArc);		//Kakestykke
		g2d.fillOval(310, 310, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Forsikring", 340, 325);
		//Lån
		g2d.setColor(Color.getHSBColor(150, 250, 100));	//farge
		g2d.fillArc(x, y, width, height, lanStart, lanArc);		//Kakestykke
		g2d.fillOval(310, 340, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Lån", 340, 355);
		//rest
		if(income > expenses)
			g2d.setColor(Color.GREEN);	//farge
		else
			g2d.setColor(Color.RED);
		g2d.fillArc(x, y, width, height, restStart, restArc);		//Kakestykke
		g2d.fillOval(310, 370, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Rest", 340, 385);
		
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
	public void setRestStart(int r){
		restStart = r;
	}
	public void setRestArc(int r){
		restArc = r;
	}
	public void setIncome(double i){
		income = i;
	}
	public void setExpenses(double i){
		expenses = i;
	}
}
