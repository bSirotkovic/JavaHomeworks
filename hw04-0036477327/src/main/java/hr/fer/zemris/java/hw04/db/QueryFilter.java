package hr.fer.zemris.java.hw04.db;

import java.util.List;

/**
 * Razred koji implementia sučelje IFilter. Razred je namijenjen da provjeri je li primljeni StudentRecord u metodi
 * accepts prihvatljiv po uvjetima u listi ConditionalExpressiona.
 * @author Branko Sirotković
 *
 */
public class QueryFilter implements IFilter{

	/**
	 * Lista uvjeta nad kojima će se StudentRecord provjeravati.
	 */
	private List<ConditionalExpression> conditions;
	
	/**
	 * Konstruktor koji prima listu uvjeta i inicijalizira svoju varijablu tom listom.
	 * @param list Lista ConditionalExpressiona.
	 */
	public QueryFilter(List<ConditionalExpression> list){
		conditions = list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accepts(StudentRecord record) {
		
		for (ConditionalExpression con : conditions) {
			if(! con.getComparisonOperator().satisfied(con.getFieldGetter().get(record), con.getStringLiteral()))
				return false;
		}
		
		return true;
	}

}
