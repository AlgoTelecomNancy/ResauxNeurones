/**
* Neural network MLP example
* Romaric Mollard
* romaric.mollard@gmail.com
*/

public final class config {
	static final double learningCoef = 2;
	
	static double activationFormula(double combinaison){
		return 1./(1.+Math.exp(-combinaison));
	}
	
	static double activationFormulaPrime(double combinaison){
		double ex = Math.exp(-combinaison);
		return ex/Math.pow(1.+ex,2);
	}
	
}
