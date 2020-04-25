package concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import concurrency.statements.Stmt;

public class ConcurrentProgram {

	private List<Thread> threads;
	private Store store;

	public ConcurrentProgram(Set<String> variables,
			List<List<Stmt>> threadBodies) {
		threads = new ArrayList<>();
		for (List<Stmt> threadBody : threadBodies) {
			threads.add(new Thread(threadBody));
		}
		store = new Store(variables);
	}

	/**
	 * Causes a thread to take an execution step
	 *
	 * @param threadId
	 *            the id of the thread that should make a step
	 */
	public void step(int threadId) {
		threads.get(threadId).step(store);
	}

	/**
	 * Identifies those threads that are enabled
	 *
	 * @return the set of ids of all enabled threads
	 */
	public Set<Integer> getEnabledThreadIds() {
		Set<Integer> result = new HashSet<>();
		for (int i = 0; i < threads.size(); i++) {
			if (threads.get(i).isEnabled(store)) {
				result.add(i);
			}
		}
		return result;
	}

	/**
	 * Identifies whether the program has terminated
	 *
	 * @return true if and only if the program has terminated
	 */
	public boolean isTerminated() {
		for (Thread t : threads) {
			if (!t.isTerminated()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the number of threads executing the program
	 *
	 * @return the number of threads executing the program
	 */
	public int getNumThreads() {
		return threads.size();
	}

	/**
	 * Returns the statements that remain to be executed by a given thread
	 *
	 * @param threadId
	 *            the id of the thread to be queried
	 * @return the statements remaining to be executed by this thread
	 */
	public Collection<? extends Stmt> remainingStatements(int threadId) {
		return threads.get(threadId).remainingStatements();
	}

	@Override
	public String toString() {
		return store.toString();
	}

}
