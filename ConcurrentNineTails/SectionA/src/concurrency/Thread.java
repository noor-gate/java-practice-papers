package concurrency;

import java.util.Collection;
import java.util.List;

import concurrency.statements.Stmt;

public class Thread {

	private List<Stmt> statements;
	private int programCounter;

	public Thread(List<Stmt> statements) {
		this.statements = statements;
		this.programCounter = 0;
	}

	/**
	 * Determines whether the thread has terminated
	 *
	 * @return true if and only if the thread has terminated
	 */
	public boolean isTerminated() {
		return programCounter == statements.size();
	}

	/**
	 * Determines whether the thread is enabled in the
	 * context of a store
	 *
	 * @param store
	 * 			variable store representing the program state
	 * @return true if and only if the thread is enabled
	 */
	public boolean isEnabled(Store store) {
		if (isTerminated()) {
			return false;
		}
		return statements.get(programCounter).isEnabled(store);
	}

	/**
	 * Causes the thread to make an execution step in
	 * the context of the given store
	 *
	 * @param store
	 * 			variable store representing the program state
	 */
	public void step(Store store) {
		if (isTerminated()) {
			throw new UnsupportedOperationException(
					"Terminated thread cannot execute");
		}
		statements.get(programCounter).execute(store);
		programCounter++;
	}

	/**
	 * Retrieves the statements that remain to be executed by the thread
	 *
	 * @return the statements that remain to be executed
	 */
	public Collection<? extends Stmt> remainingStatements() {
		return statements;
	}

}
