import java.awt.Component;
import java.awt.Graphics;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


public class PaintablePanel extends Component{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		BufferedImage img;
		String imgLoc = "/splash.jpg";
	
	
		PaintablePanel(){
			URL imgURL = getClass().getResource(imgLoc);
		//	 java.net.URL imgURL = getClass().getResource("./src/splash.jpg");
			try{
				//img = ImageIO.read(new File("splash.jpg"));
				img = ImageIO.read(imgURL);
			//	   img =  Toolkit.getDefaultToolkit().getImage(imgURL);
				
		}
			catch(IOException e){
			
			if(img != null)
				repaint();
			
		}
		}
			public void paint(Graphics g){
			g.drawImage(img.getScaledInstance(300, 300, 1), 0, 0, null);
		}
		
		

	}
