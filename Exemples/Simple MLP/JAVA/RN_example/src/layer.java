/**
* Neural network MLP example
* Romaric Mollard
* romaric.mollard@gmail.com
*/
import java.util.LinkedList;

public class layer {
	
	public LinkedList<neurone> neurones = new LinkedList<neurone>();
	public layer next = null;
	public layer previous = null;
	
	public layer(int n){
		int i;
		for(i=0; i<n; i++){
			neurones.add(new neurone());
		}
	}
	
	public neurone get(int i){
		return neurones.get(i);
	}
	
	public void connectTo(layer output){
		for(neurone n1 : this.neurones){
			for(neurone n2 : output.neurones){
				n1.connectTo(n2);
			}
		}
	}
	
	public void setNext(layer l){
		this.next = l;
		l.previous = this;
	}
	
	public void runRecursive(){
		if(previous!=null){//The first one have been defined
			for(neurone n : neurones){
				n.calculValue();
			}
		}
		if(next!=null){
			this.next.runRecursive();
		}
	}
	
	public void calculErrors(){
		for(neurone n : neurones){
			n.calculError();
		}
		if(previous!=null){
			this.previous.calculErrors();
		}
	}
	
}