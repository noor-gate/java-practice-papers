package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RoundRobinScheduler implements Scheduler {

    List<Integer> scheduler;

    public RoundRobinScheduler() {
        this.scheduler = new ArrayList<>();
    }

    @Override
    public int chooseThread(ConcurrentProgram program) throws DeadlockException {
        Set<Integer> threads = program.getEnabledThreadIds();
        if (threads.isEmpty()) {
            throw new DeadlockException();
        }
        if (!scheduler.isEmpty()) {
            int min = Integer.MAX_VALUE;
            for (int t : threads) {
                if (t > scheduler.get(scheduler.size() - 1)) {
                    min = Math.min(min, t);
                }
            }
            if (min < Integer.MAX_VALUE) {
                scheduler.add(min);
                return min;
            }
        }
        scheduler.add(Collections.min(threads));
        return Collections.min(threads);
    }
}
