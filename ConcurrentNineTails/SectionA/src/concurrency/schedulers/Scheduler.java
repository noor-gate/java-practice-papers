package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

public interface Scheduler {

	/**
	 * Returns the next thread to be executed for the
	 * given program
	 *
	 * @param program
	 * 			the program for which a scheduling decision is required
	 * @return the thread to be scheduled next
	 * @throws an exception if deadlock occurs
	 */
	public int chooseThread(ConcurrentProgram program) throws DeadlockException;

}
