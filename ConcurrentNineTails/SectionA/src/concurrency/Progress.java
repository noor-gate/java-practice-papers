package concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import concurrency.expressions.*;
import concurrency.schedulers.*;
import concurrency.statements.*;

public class Progress {

	private static int mismatches = 0;

	public static void main(String[] args) {

		try {
			testQuestion1();
			testQuestion2();
			testQuestion3();
			testQuestion4();
			// No test method is provided for question 5
		} catch (Exception e) {
			System.out.println("Progress program terminated with exception: "
					+ e);
			System.exit(1);
		}

		System.out.println();

		if (mismatches == 0) {
			System.out.println("All enabled tests passed");
		} else {
			System.out.println("There " + (mismatches == 1 ? "was" : "were")
					+ " " + mismatches + " mismatch"
					+ (mismatches == 1 ? "" : "es"));
			System.exit(1);
		}

	}

	private static void testQuestion1() {

        System.out.println("================");
        System.out.println("Question 1 tests");
        System.out.println("================");

        Set<String> variables = new HashSet<>();
        variables.add("a");
        variables.add("b");
        Store store = new Store(variables);

        Stmt wait1 = new WaitStmt(new IdentifierExpr("a"), new
                IdentifierExpr("b"));
        Stmt wait2 = new WaitStmt(new IdentifierExpr("a"), new
                LiteralExpr(1));

        checkExpectedVersusActual(true, wait1.isEnabled(store));
        checkExpectedVersusActual(false, wait2.isEnabled(store));

        new AssignStmt("a", new LiteralExpr(1)).execute(store);

        checkExpectedVersusActual(false, wait1.isEnabled(store));
        checkExpectedVersusActual(true, wait2.isEnabled(store));

	}

	private static void testQuestion2() throws DeadlockException {

        System.out.println("================");
        System.out.println("Question 2 tests");
        System.out.println("================");

        {
            ConcurrentProgram program = makeExampleProgram();
            Scheduler roundRobin = new RoundRobinScheduler();
            checkExpectedVersusActual(0, roundRobin.chooseThread(program));
            program.step(0);
            checkExpectedVersusActual(1, roundRobin.chooseThread(program));
            program.step(1);
            checkExpectedVersusActual(2, roundRobin.chooseThread(program));
            program.step(2);
            checkExpectedVersusActual(0, roundRobin.chooseThread(program));
            program.step(0);
            checkExpectedVersusActual(1, roundRobin.chooseThread(program));
            program.step(1);
            checkExpectedVersusActual(2, roundRobin.chooseThread(program));
            program.step(2);
        }

        {
            ConcurrentProgram program = makeExampleProgram2();
            Scheduler roundRobin = new RoundRobinScheduler();
            checkExpectedVersusActual(3, roundRobin.chooseThread(program));
            program.step(3);
            checkExpectedVersusActual(2, roundRobin.chooseThread(program));
            program.step(2);
            checkExpectedVersusActual(2, roundRobin.chooseThread(program));
            program.step(2);
            checkExpectedVersusActual(1, roundRobin.chooseThread(program));
            program.step(1);
            checkExpectedVersusActual(1, roundRobin.chooseThread(program));
            program.step(1);
            checkExpectedVersusActual(0, roundRobin.chooseThread(program));
            program.step(0);
        }

        {
            try {
                ConcurrentProgram program = makeExampleProgram3();
                Scheduler roundRobin = new RoundRobinScheduler();
                roundRobin.chooseThread(program);
                System.out.println("MISMATCH: DeadlockException should have been thrown");
                mismatches++;
            } catch (DeadlockException e) {
                System.out.println("MATCH: DeadlockException was thrown as expected");
            }
        }

	}

	private static void testQuestion3() {

        System.out.println("================");
        System.out.println("Question 3 tests");
        System.out.println("================");

        {
            String result = new Executor(makeExampleProgram(), new
                    RoundRobinScheduler()).execute();
            checkExpectedVersusActual("Final state: [x -> 2, y -> 4]\nHistory: [0, 1, 2, 0, 1, 2]\nTermination status: graceful\n",
                    result);
        }

        {
            String result = new Executor(makeExampleProgram2(), new
                    RoundRobinScheduler()).execute();
            checkExpectedVersusActual("Final state: [x -> 1, y -> 1, z -> 1]\nHistory: [3, 2, 2, 1, 1, 0]\nTermination status: graceful\n",
                    result);
        }

        {
            String result = new Executor(makeExampleProgram3(), new
                    RoundRobinScheduler()).execute();
            checkExpectedVersusActual("Final state: [x -> 0]\nHistory: []\nTermination status: deadlock\n",
                    result);
        }

	}

	private static void testQuestion4() {

        System.out.println("================");
        System.out.println("Question 4 tests");
        System.out.println("================");

        {
            String result = new Executor(makeExampleProgram2(), new
                    FewestWaitsScheduler()).execute();
            checkExpectedVersusActual("Final state: [x -> 1, y -> 1, z -> 1]\nHistory: [3, 2, 2, 1, 1, 0]\nTermination status: graceful\n",
                    result);
        }

        {
            String result = new Executor(makeExampleProgram3(), new
                    FewestWaitsScheduler()).execute();
            checkExpectedVersusActual("Final state: [x -> 0]\nHistory: []\nTermination status: deadlock\n",
                    result);
        }

        {
            String result = new Executor(makeExampleProgram4(), new
                    FewestWaitsScheduler()).execute();
            checkExpectedVersusActual("Final state: [x -> 0, y -> 3]\nHistory: [0, 0, 0, 3, 3, 3, 1, 1, 1, 1, 2, 2, 2, 2]\nTermination status: graceful\n",
                    result);
        }

	}

	/**
	 * <strong>Creates the example concurrent program used in the spec:</strong>
	 *
	 * Store: { x == 0, y == 0 } Thread 0: x = 1; y = 2; Thread 1: x = 2; y = 3;
	 * Thread 2: wait(x, 2); y = 4;
	 * 
	 * Uncomment this method as needed when you uncomment the test methods
	 * 
	 * @return the example concurrent program used in the spec
	 */
    private static ConcurrentProgram makeExampleProgram() {

        Set<String> variables = new HashSet<>();
        variables.add("x");
        variables.add("y");

        List<Stmt> statements0 = new ArrayList<Stmt>();
        statements0.add(new AssignStmt("x", new LiteralExpr(1)));
        statements0.add(new AssignStmt("y", new LiteralExpr(2)));

        List<Stmt> statements1 = new ArrayList<Stmt>();
        statements1.add(new AssignStmt("x", new LiteralExpr(2)));
        statements1.add(new AssignStmt("y", new LiteralExpr(3)));

        List<Stmt> statements2 = new ArrayList<Stmt>();
        statements2.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(2)));
        statements2.add(new AssignStmt("y", new LiteralExpr(4)));

        List<List<Stmt>> threads = new ArrayList<>();
        threads.add(statements0);
        threads.add(statements1);
        threads.add(statements2);

        return new ConcurrentProgram(variables, threads);

    }

	/**
	 * <strong>Creates the following concurrent program:</strong>
	 *
	 * Store: { x == 0, y == 0, z == 0 } Thread 0: wait(z, 1); Thread 1: wait(y,
	 * 1); z = 1; Thread 2: wait(x, 1); y = 1; Thread 3: x = 1;
	 *
	 * Uncomment this method as needed when you uncomment the test methods
	 * 
	 * @return an example concurrent program
	 */
    private static ConcurrentProgram makeExampleProgram2() {

        Set<String> variables = new HashSet<>();
        variables.add("x");
        variables.add("y");
        variables.add("z");

        List<Stmt> statements0 = new ArrayList<Stmt>();
        statements0.add(new WaitStmt(new IdentifierExpr("z"), new
                LiteralExpr(1)));

        List<Stmt> statements1 = new ArrayList<Stmt>();
        statements1.add(new WaitStmt(new IdentifierExpr("y"), new
                LiteralExpr(1)));
        statements1.add(new AssignStmt("z", new LiteralExpr(1)));

        List<Stmt> statements2 = new ArrayList<Stmt>();
        statements2.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(1)));
        statements2.add(new AssignStmt("y", new LiteralExpr(1)));

        List<Stmt> statements3 = new ArrayList<Stmt>();
        statements3.add(new AssignStmt("x", new LiteralExpr(1)));

        List<List<Stmt>> threads = new ArrayList<>();
        threads.add(statements0);
        threads.add(statements1);
        threads.add(statements2);
        threads.add(statements3);

        return new ConcurrentProgram(variables, threads);

    }

	/**
	 * <strong>Creates the following deadlocked concurrent program:</strong>
	 *
	 * Store: { x == 0 } Thread 0: wait(x, 1);
	 *
	 * Uncomment this method as needed when you uncomment the test methods
	 * 
	 * @return an example concurrent program
	 */
    private static ConcurrentProgram makeExampleProgram3() {

        Set<String> variables = new HashSet<>();
        variables.add("x");

        List<Stmt> statements0 = new ArrayList<Stmt>();
        statements0.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(1)));

        List<List<Stmt>> threads = new ArrayList<>();
        threads.add(statements0);

        return new ConcurrentProgram(variables, threads);

    }

	/**
	 * <strong>Creates the following concurrent program:</strong>
	 *
	 * Store: { x == 0, y == 0 } Thread 0: wait(x, 0); wait(x, 0); y = 1; Thread
	 * 1: wait(x, 0); wait(x, 0); wait(x, 0); y = 2; Thread 2: wait(x, 0);
	 * wait(x, 0); wait(x, 0); y = 3; Thread 3: wait(x, 0); wait(x, 0); y = 4;
	 *
	 * Uncomment this method as needed when you uncomment the test methods
	 * 
	 * @return an example concurrent program
	 */
    private static ConcurrentProgram makeExampleProgram4() {

        Set<String> variables = new HashSet<>();
        variables.add("x");
        variables.add("y");

        List<Stmt> statements0 = new ArrayList<Stmt>();
        statements0.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements0.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements0.add(new AssignStmt("y", new LiteralExpr(1)));

        List<Stmt> statements1 = new ArrayList<Stmt>();
        statements1.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements1.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements1.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements1.add(new AssignStmt("y", new LiteralExpr(2)));

        List<Stmt> statements2 = new ArrayList<Stmt>();
        statements2.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements2.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements2.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements2.add(new AssignStmt("y", new LiteralExpr(3)));

        List<Stmt> statements3 = new ArrayList<Stmt>();
        statements3.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements3.add(new WaitStmt(new IdentifierExpr("x"), new
                LiteralExpr(0)));
        statements3.add(new AssignStmt("y", new LiteralExpr(4)));

        List<List<Stmt>> threads = new ArrayList<>();
        threads.add(statements0);
        threads.add(statements1);
        threads.add(statements2);
        threads.add(statements3);

        return new ConcurrentProgram(variables, threads);

    }

	/**
	 * Compares String output of your code with output known to be correct
	 *
	 * @param expected
	 *            String produced by your code
	 * @param actual
	 *            String produced by correct code
	 */
	private static void checkExpectedVersusActual(String expected, String actual) {
		if (!expected.equals(actual)) {
			System.out.println("MISMATCH: expected:\n" + expected
					+ "\nbut got:\n" + actual);
			mismatches++;
		} else {
			System.out.println("MATCH: expected and got:\n" + expected);
		}
	}

	/**
	 * Compares int output of your code with output known to be correct
	 *
	 * @param expected
	 *            int or Integer produced by your code
	 * @param actual
	 *            int or Integer produced by correct code
	 */
	private static void checkExpectedVersusActual(Integer expected,
			Integer actual) {
		checkExpectedVersusActual(expected.toString(), actual.toString());
	}

	/**
	 * Compares boolean output of your code with output known to be correct
	 *
	 * @param expected
	 *            boolean or Boolean produced by your code
	 * @param actual
	 *            boolean or Boolean produced by correct code
	 */
	private static void checkExpectedVersusActual(Boolean expected,
			Boolean actual) {
		checkExpectedVersusActual(expected.toString(), actual.toString());
	}

}
