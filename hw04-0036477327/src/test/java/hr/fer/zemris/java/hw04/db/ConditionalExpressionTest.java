package hr.fer.zemris.java.hw04.db;

import org.junit.Test;

public class ConditionalExpressionTest {

	@Test (expected=IllegalArgumentException.class)
	public void testFail() {
		new ConditionalExpression(null, null, null);
	}
	
	//Ne znam što još testirati u ovako jednostavnom razredu.

}
