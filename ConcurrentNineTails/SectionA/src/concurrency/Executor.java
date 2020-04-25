package concurrency;

import java.util.LinkedList;
import java.util.List;

import concurrency.schedulers.Scheduler;

public class Executor {

	private ConcurrentProgram program;
	private Scheduler scheduler;

	public Executor(ConcurrentProgram program, Scheduler scheduler) {
		this.program = program;
		this.scheduler = scheduler;
	}

	/**
	 * Executes program with respect to scheduler
	 *
	 * @return the final state and history of execution
	 */
	public String execute() {
		List<Integer> history = new LinkedList<Integer>();
		boolean deadlockOccurred = false;

		while (!program.isTerminated() && !deadlockOccurred) {
			try {
				int threadId = scheduler.chooseThread(program);
				history.add(threadId);
				program.step(threadId);
			} catch (Exception e) {
				deadlockOccurred = true;
			}
		}

		StringBuilder result = new StringBuilder();
		result.append("Final state: " + program + "\n");
		result.append("History: " + history + "\n");
		result.append("Termination status: "
				+ (deadlockOccurred ? "deadlock" : "graceful") + "\n");
		return result.toString();
	}

	// An incorrect attempt at overriding the equals method
	// of Object
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Executor)) {
			return false;
		}
		Executor that = (Executor) obj;
		return that.program.toString().equals(program.toString());
	}

	@Override
	public int hashCode() {
		return program.toString().hashCode();
	}
}


