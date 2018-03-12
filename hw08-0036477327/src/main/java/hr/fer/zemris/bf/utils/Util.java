package hr.fer.zemris.bf.utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import hr.fer.zemris.bf.model.Node;

/**
 * Razred koji korisniku pruža statičke metode koje olakšavaju rad sa poljima booleana
 * koji predstavljaju redak u tablici istinitosti.
 * @author Branko
 *
 */
public class Util {

	/**
	 * Metoda koja za ulaznu listu varijabli stvara tablicu istinitosti, te za svaki redak
	 * tablice nad njime poziva metodu consumera.
	 * @param variables Lista varijabli.
	 * @param consumer Consumer čija se metoda izvršava nad retkom tablice istinitosti.
	 */
	public static void forEach(List<String> variables, Consumer<boolean[]> consumer){
		if(variables == null || consumer == null){
			throw new IllegalArgumentException("Null argumenti poslani metodi forEach.");
		}
		
		boolean[] values = new boolean[variables.size()];
		
		for(int i = 0, n = (int) Math.pow(2, variables.size()); i < n; i++){
			consumer.accept(values);
			values = getNext(values, values.length - 1);
		}
	}

	/**
	 * Pomoćna metoda koja za trenutni redak u tablici istinitosti vraća sljedeći.
	 * @param values Trenutni redak u tablici istinitosti.
	 * @param pos Pomoćna varijabla koja govori za koju varijablu mijenjamo boolean vrijednost.
	 * @return Sljedeći redak u tablici istinitosti.
	 */
	private static boolean[] getNext(boolean[] values, int pos) {
		if(values == null || pos < 0 || pos >= values.length){
			throw new IllegalArgumentException("Neispravni argumenti poslani metodi getNext.");
		}
		if(!values[pos]){//na poziciji je false.
			values[pos] = true;
			return values;
		}
		if(pos == 0){//Zadnji slučaj, ne treba više uvećavati, ako je jedinica na prvoj poziciji.
			return values;
		}
		else{//na poziciji je true.
			values[pos] = false;
			return getNext(values, pos - 1);
		}
	}
	
	/**
	 * Metoda koja filtrira retke u tablici istinitosti, odnosno vraća set onih redaka koji za primljeni
	 * expression daju rezultat jednak kao i primljena varijabla expressionValue.
	 * @param variables Lista varijabli u expressionu.
	 * @param expression Stablo odnosno izraz koji se evaluira.
	 * @param expressionValue True ili false s kojim uspoređujemo rezultate svakog retka u tablici istinitosti.
	 * @return
	 */
	public static Set<boolean[]> filterAssignments(List<String> variables, Node expression, boolean expressionValue){
		if(variables == null || expression == null){
			throw new IllegalArgumentException("Null argumenti poslani metodi filterAssignments.");
		}
		Set<boolean[]> filteredSet = new LinkedHashSet<>();
		ExpressionEvaluator eval = new ExpressionEvaluator(variables);
		
		forEach(variables, values -> {
			eval.setValues(values);
			expression.accept(eval);
			if(eval.getResult() == expressionValue){
				filteredSet.add(values.clone()); //Treba se pozvati .clone jer je values adresa od values(?).
			}
		});
		
		return filteredSet;
	}
	
	/**
	 * Pomoćna metoda koja za neki redak vraća redni broj koi predstavlja njegovu poziciju u tablici istinitosti.
	 * @param values Redak u tablici istinitosti.
	 * @return Redni broj retka.
	 */
	public static int booleanArrayToInt(boolean[] values){
		if(values == null){
			throw new IllegalArgumentException("Null polje booleana je poslano metodi booleanArrayToInt.");
		}
		int number = 0;
		for(int i = 0; i < values.length; i++){
			number = (number << 1) | (values[i] ? 1 : 0);
		}
		
		return number;
	}
	
	/**
	 * Metoda koja za određeni izraz vraća sve one retke iz tablice istinitosti kojima je 
	 * rezultat true.
	 * @param variables Varijable u izrazu.
	 * @param expression Izraz tj. stablo koje se evaluira.
	 * @return Set rednih brojeva redaka za koje je izraz istinit.
	 */
	public static Set<Integer> toSumOfMinterms(List<String> variables, Node expression){
		return mintermsAndMaxterms(variables, expression, true);
	}
	
	/**
	 * Metoda koja za određeni izraz vraća sve one retke iz tablice istinitosti kojima je 
	 * rezultat false.
	 * @param variables Varijable u izrazu.
	 * @param expression Izraz tj. stablo koje se evaluira.
	 * @return Set rednih brojeva redaka za koje je izraz neistinit.
	 */
	public static Set<Integer> toProductOfMaxterms(List<String> variables, Node expression){
		return mintermsAndMaxterms(variables, expression, false);
	}
	
	/**
	 * Metoda koja za određeni izraz vraća sve one retke iz tablice istinitosti kojima je 
	 * rezultat jednak predanom argumentu minOrMax.
	 * @param variables Varijable u izrazu.
	 * @param expression Izraz tj. stablo koje se evaluira.
	 * @param minOrMax boolean s kojim se uspoređuje rezultat pojedinog retka tablice istinitosti.
	 * @return Set rednih brojeva redaka tablice istinitosti čija je vrijednost jednaka minOrMax.
	 */
	private static Set<Integer> mintermsAndMaxterms(List<String> variables, Node expression, boolean minOrMax){
		if(variables == null || expression == null){
			throw new IllegalArgumentException("Null argumenti poslani metodi toSumOfMinterms ili toProductOfMaxterms.");
		}
		Set<Integer> mintermsInt = new LinkedHashSet<>();
		Set<boolean[]> minterms = filterAssignments(variables, expression, minOrMax);
		for (boolean[] bs : minterms) {
			mintermsInt.add(booleanArrayToInt(bs));
		}
		
		return mintermsInt;
	}
	
	public static byte[] indexToByteArray(int x, int n){
		if(n <= 0){
			throw new IllegalArgumentException("Ovoj metodi se kao drugi argument treba predati prirodan broj.");
		}
		
		byte[] bytes = new byte[n];
		int pos = n-1;
		long num = Integer.toUnsignedLong(x);
		
		while(pos > -1){
			bytes[pos] = (num % 2 == 1) ? (byte)1 : (byte)0;
			num = num >> 1;
			pos--;
		}
		
		if(x < 0 && n > 32){//Korekcija za negativne brojeve.
			for(int i = 0; i < n - 32; i++){
				bytes[i] = 1;
			}
		}
		
		return bytes;
	}
}
