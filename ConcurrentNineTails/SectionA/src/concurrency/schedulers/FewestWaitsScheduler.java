package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import concurrency.statements.*;

import java.util.Set;

public class FewestWaitsScheduler implements Scheduler {
    @Override
    public int chooseThread(ConcurrentProgram program) throws DeadlockException {
        Set<Integer> threads = program.getEnabledThreadIds();
        if (threads.isEmpty()) {
            throw new DeadlockException();
        }
        int minWait = Integer.MAX_VALUE;
        int minId = Integer.MAX_VALUE;
        for (int t : threads) {
            int wait = 0;
            for (Stmt s : program.remainingStatements(t)) {
                if (s instanceof WaitStmt) {
                    wait ++;
                }
            }
            if (wait == minWait && t < minId) {
                minId = t;
            } else if (wait < minWait) {
                minWait = wait;
                minId = t;
            }
        }
        return minId;
    }
}
