package hr.fer.zemris.java.fractals;

import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

public class FractalProducer implements IFractalProducer {

	ComplexRootedPolynomial rootedPolynom;
	ComplexPolynomial polynom;
	ComplexPolynomial derived;
	
	public FractalProducer(Complex[] roots) {
		rootedPolynom = new ComplexRootedPolynomial(roots);
		polynom = rootedPolynom.toComplexPolynom();
		derived = polynom.derive();
	}

	@Override
	public void produce(double reMin, double reMax, double imMin, double imMax, int width, int height, long requestNo,
			IFractalResultObserver observer) {
		System.out.println("Zapocinjem izracun...");
		int offset = 0;
		short[] data = new short[width * height];
		
		for(double y = 0; y < height; y++) {
			for(double x = 0; x < width; x++) {
				double cre = x / (width-1.0) * (reMax - reMin) + reMin;
				double cim = (height-1.0-y) / (height-1) * (imMax - imMin) + imMin;
				Complex zn = new Complex(cre, cim);
				int iter = 0;
				double module;
				Complex zn1;
				do {
					Complex numerator = polynom.apply(zn);
					Complex denominator = derived.apply(zn);
					Complex fraction = numerator.divide(denominator);
					zn1 = zn.sub(fraction);
					module = zn1.sub(zn).module();
					zn = zn1;
					iter++;			
				} while(module > 0.001 && iter < 16);
			
				int index = rootedPolynom.indexOfClosestRootFor(zn1, 0.002);
			
				if(index==-1) { 
					data[offset++]=0;
				} 
				else { 
					data[offset++]=(short) (index+1); 
				}
			}
		}	
		System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
		observer.acceptResult(data, (short)(polynom.order()+1), requestNo);	
	}

}
