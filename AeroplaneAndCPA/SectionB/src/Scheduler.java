import java.util.Hashtable;
import java.util.Map;

public class Scheduler {

	public static void main(String[] args) throws Exception {
		GraphInterface graph = new Graph(args[0]);
		System.out.println("Earliest completion time for this project is "
				+ graph.getEarliestCompletionTime());
	}
}
