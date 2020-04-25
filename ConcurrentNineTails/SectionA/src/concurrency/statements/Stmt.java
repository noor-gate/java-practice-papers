package concurrency.statements;

import concurrency.Store;

public interface Stmt {

	public boolean isEnabled(Store store);

	/**
	 * Executes the statement in the context of the
	 * given store, possibly updating the store
	 * in the process
	 *
	 * @param store
	 * 			variable store representing the program state
	 */
	public void execute(Store store);

}
