/**
* Neural network MLP example
* Romaric Mollard
* romaric.mollard@gmail.com
*/
public class link {
	
	private double coef = 0;
	private neurone input = null;
	private neurone output = null;
	
	public link(neurone in, neurone out){
		this.input = in;
		this.output = out;
		this.setRandCoef();
	}

	
	public double getCoef() {
		return coef;
	}
	public void setCoef(double coef) {
		this.coef = coef;
	}
	public void setRandCoef() {
		this.coef = Math.random()*2 - 1;
	}
	public void updateCoef(){
		this.coef = config.learningCoef*input.value*output.error + this.coef ;
	}
	
	public neurone getInput() {
		return input;
	}
	public void setInput(neurone input) {
		this.input = input;
	}
	public neurone getOutput() {
		return output;
	}
	public void setOutput(neurone output) {
		this.output = output;
	}
	
}