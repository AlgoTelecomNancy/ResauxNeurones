/**
* Neural network MLP example
* Romaric Mollard
* romaric.mollard@gmail.com
*/
import java.util.*;

public class neurone {
	
	private static int currentId = 0;

	public int id;
	public double combinaison = 0; //Résultat fonction de combinaison
	public double result = 0; //Résultat après 
	public double error = 0;
	public double value = 0;
	public double expected = 0;
	
	public LinkedList<link> connexionsOut = new LinkedList<link>();
	public LinkedList<link> connexionsIn = new LinkedList<link>();

	public neurone(){
		this.id = currentId;
		currentId++;
		System.out.println("neurone created id="+id);
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public void connectTo(neurone nToAdd){
		for(link l : connexionsOut){
			if(l.getOutput().id==nToAdd.id){
				return;
			}
		}
		link lk = new link(this,nToAdd);
		connexionsOut.add(lk);
		nToAdd.connexionsIn.add(lk);
	}
	
	public void calculValue(){

		double res = 0;
		for(link l: connexionsIn){
			res += l.getInput().value * l.getCoef();
		}
		this.combinaison = res;
		this.value = config.activationFormula(res);
	}
	
	public void calculError(){
		
		if(connexionsOut.size()==0){
			this.error = config.activationFormulaPrime(this.combinaison)*(this.expected-this.value);
		}else{
			double res = 0;
			for(link l: connexionsOut){
				res += l.getOutput().error * l.getCoef();
			}
			this.error = config.activationFormulaPrime(this.combinaison)*(res);
		}
		
		for(link l: connexionsOut){
			l.updateCoef();
		}
		
	}
	
	public void setExpected(double e){
		this.expected = e;
	}
	
}
