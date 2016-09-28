/**
* Neural network MLP example
* Romaric Mollard
* romaric.mollard@gmail.com
*/
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.*;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class main_image {
	
	public static void setOctet(int n, layer l){
		for(int i=0; i<24; i++){
			l.get(i).setValue(n%2);
			n = (int)Math.floor(n/2);
		}
	}

	public static void main(String[] args) {
		

		//Image reconstruction
		layer l1 = new layer(16*16);
		layer l2 = new layer(16*16);
		layer l3 = new layer(8*8);
		layer l4 = new layer(4*4);
		layer l5 = new layer(10);
		l1.setNext(l2);
		l2.setNext(l3);
		l3.setNext(l4);
		l4.setNext(l5);

		l1.connectTo(l2);System.out.println("1 connected to 2");
		l2.connectTo(l3);System.out.println("2 connected to 3");
		l3.connectTo(l4);System.out.println("3 connected to 4");
		l4.connectTo(l5);System.out.println("4 connected to 5");

		String imgs[] = {"imgs/1.png","imgs/2.png","imgs/3.png"};
		int i, iterations, n, im;
		iterations = 1000;
		for(i=0;i<iterations; i++){
			
			//Open image
			
			im = (int)(Math.random()*imgs.length);
			BufferedImage image = null;
	        try
	        {
	          image = ImageIO.read(new File(imgs[im]));
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	          System.exit(1);
	        }
	        //Définir l'entrée et la sortie
			for(int x=0;x<16;x++){
				for(int y=0;y<16;y++){
					int rgb = image.getRGB(x, y);
					int r = (rgb >> 16) & 0xFF;
					int g = (rgb >> 8) & 0xFF;
					int b = (rgb & 0xFF);
					int gray = (r + g + b) / 3;
					l1.get(x+16*y).setValue((int)(gray/128));
				}
			}

			for(n=0;n<l5.neurones.size();n++){
				l5.get(n).setExpected((im == n)?1:0);
			}
			
			l1.runRecursive();
			l5.calculErrors();
			
			if(i%10==0){
				double error = 0;
				//System.out.println("img : "+imgs[im]);
				for(n=0;n<l5.neurones.size();n++){
					error = Math.max(error, Math.abs(l5.get(n).value-l5.get(n).expected));
					//System.out.println(n+" : "+l5.get(n).value);
				}
				System.out.println(i+" max e="+error+" "+new String(new char[(int) (error*50)]).replace('\0', '#'));
			}

		}
	
		Scanner reader = new Scanner(System.in);
		
		
		String img = "";
		
		while(true){
			
			System.out.println("Adresse de l'image corrompue : ");
			
			if(reader.hasNext()){
				
				img = reader.next();
				
				BufferedImage image = null;
		        try
		        {
		          image = ImageIO.read(new File(img));
		          
		          //Définir l'entrée et la sortie
					for(int x=0;x<16;x++){
						for(int y=0;y<16;y++){
							int rgb = image.getRGB(x, y);
							int r = (rgb >> 16) & 0xFF;
							int g = (rgb >> 8) & 0xFF;
							int b = (rgb & 0xFF);
							int gray = (r + g + b) / 3;
							l1.get(x+16*y).setValue((int)(gray/128));
						}
					}
					
					l1.runRecursive();
					

					for(n=0;n<l5.neurones.size();n++){
						if((double)Math.round(l5.get(n).value*10)/10>=0.1){
							System.out.println(new String(new char[(int)Math.round(l5.get(n).value*10)]).replace('\0', '#')+" "+n+" = "+(double)Math.round(l5.get(n).value*10)/10);
						}
					}
		          
		        }
		        catch (Exception e)
		        {
		          e.printStackTrace();
		        }
		        
				
			}else{
				reader.next();
			}
			
		}
		
		
	}

}