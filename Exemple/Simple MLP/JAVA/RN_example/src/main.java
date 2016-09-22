/**
* Neural network MLP example
* Romaric Mollard
* romaric.mollard@gmail.com
*/
import java.text.*;
import java.util.Scanner;

public class main {
	
	public static void setOctet(int n, layer l){
		for(int i=0; i<24; i++){
			l.get(i).setValue(n%2);
			n = (int)Math.floor(n/2);
		}
	}

	public static void main(String[] args) {
		

		//Simple XOR model
		layer l1 = new layer(2);
		layer l2 = new layer(3);
		layer l3 = new layer(1);
		l1.setNext(l2);
		l2.setNext(l3);

		l1.connectTo(l2);
		l2.connectTo(l3);
		l1.connectTo(l3);

		
		int i, iterations, n, a,b;
		iterations = 1000;
		for(i=0;i<iterations; i++){
			
			a = (int)(Math.random()*2); //Sort 1 ou 0
			b = (int)(Math.random()*2); //Sort 1 ou 0
			
			l1.get(0).setValue((double)a);
			l1.get(1).setValue((double)b);
			
			l3.get(0).setExpected(Math.abs(a-b)); // |a-b| correspond à un XOR
			
			l1.runRecursive();
			l3.calculErrors();
		}
	
		Scanner reader = new Scanner(System.in);


		while(true){
			
			System.out.println("Input A et B : ");
			
			if(reader.hasNextInt()){
				
				a = reader.nextInt();
				b = reader.nextInt();
				
				l1.get(0).setValue((double)a);
				l1.get(1).setValue((double)b);
				
				l1.runRecursive();

				System.out.println(l3.get(0).value);
				
			}else{
				reader.next();
			}
			
		}
		
		
	}

}
