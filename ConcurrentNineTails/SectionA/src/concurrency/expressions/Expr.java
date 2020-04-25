package concurrency.expressions;

import concurrency.Store;

public interface Expr {

	/**
	 * Returns the value obtained by evaluating the
	 * expression in the context of the given store
	 *
	 * @param store
	 * 			variable store representing the program state
	 * @return the result obtained by evaulating the expression
	 */
	public int eval(Store store);

}
