import java.util.*;
import java.io.*;

public class Graph implements GraphInterface{

	private GraphNode start;
	private GraphNode finish;
	
	public Graph(String projectFile) throws Exception {
		parseGraph(projectFile);
	}
	
	private void setIncomingDegree() {
		setIncomingDegreeRecursively(start);
	}
	
	
	//YOU ARE ASKED TO IMPLEMENT THIS METHOD
	//description: It proceeds forward and recursively sets the degree of each
	//             node to be the number of its incoming edges. 
	//post: It sets the degree of each node to be equal to the number of incoming 
	//      edges of that node. 
	private void setIncomingDegreeRecursively(GraphNode node) {
		
	}

	//YOU ARE ASKED TO IMPLEMENT THIS METHOD
	//description: It returns a queue of GraphNodes whose FIFO ordering reflects the 
	//             order in which the earliest completion time for the nodes has to be computed.
	//		       See the description of the algorithm given in the questions.
	//post: It returns a queue of GraphNode objects such that a node added in the queue before 
	//      another node indicates that there is a path from the former node to the latter 
	//      node in the event-node graph.   
	private QueueInterface<GraphNode> topologicalSort( ) {
		
	}
	
	
	//YOU ARE ASKED TO COMPLETE THE IMPLEMENTATION OF THIS METHOD
	//description: It computes the earliest completion time for each node using the algorithm 
	//             described in the question, in the order given by the FIFO queue generated 
	//             by the topological sort.
	//post: Sets the earliest completion time for the start node to be zero. Sets the
	//      earliest completion time for each node, different from the start node, to be the 
	//      maximum of the sum of the earliest completion time for its precedent node and the 
	//      duration of the connecting edge, over all its precedent nodes. 
	private void computeEarliestCompletionTime( ) {
	
		QueueInterface<GraphNode> sortedNodes = topologicalSort();
		GraphNode start = sortedNodes.dequeue();
		start.setEarliestCompletionTime(0);
		
		//YOU ARE ASKED TO IMPLEMENT THIS WHILE LOOP 
		while (!sortedNodes.isEmpty()) {
		
		
		
		}
	
	}
	
	public int getEarliestCompletionTime() {
		computeEarliestCompletionTime();
		return finish.EarliestCompletionTime();
	}


	private void parseGraph(String inputFile)
			throws FileNotFoundException {
		// each project input file has the following format:
		// 1. first line is an integer N, followed by N lines:
		// a. each of these N lines has the format "task_name task_duration"
		// 2. (N+1)th line is an integer M, followed by M lines:
		// a. each of these M lines has the format
		// "task_name dependent_task_name1 dependent_task_name2 ..."
		// b. "Finish" denotes the end of the project; "Start" denotes the start
		// of the project

		Scanner in = new Scanner(new FileReader(inputFile));
		// 1. read the task information
		int numOfTasks = Integer.parseInt(in.nextLine());
		Map<String, GraphEdge> tasks = new Hashtable<String, GraphEdge>();

		for (int i = 0; i < numOfTasks; i++) {
			String[] line = in.nextLine().trim().split(" ");
			// line[0] is task name, line[1] is task duration
			Task task = new Task(line[0], Integer.parseInt(line[1]));
			tasks.put(line[0], new GraphEdge(task));
		}
		// 2. read the task dependence information and construct the dependence
		// graph
		int numOfDependencies = Integer.parseInt(in.nextLine());
		//Graph graph = new Graph();
		start = new GraphNode();

		for (int i = 0; i < numOfDependencies; i++) {
			String[] line = in.nextLine().trim().split(" ");
			// line[0] is the task, line[1]... are the pre-tasks of line[0]
			if (line[0].equals("Finish")) {
				finish = new GraphNode();
				if (line.length == 2) {
					// there is only one dependence of the finish point
					GraphEdge task = tasks.get(line[1]);
					task.setEndPoint(finish);
					finish.addIncomingEdge(task);
				} else { // line.length > 2,
					// i.e., there are multiple dependences for the finish
					// point, so need to
					// create dummy node and dummy edge for each dependence
					for (int j = 1; j < line.length; j++) {
						GraphEdge task = tasks.get(line[j]);
						GraphNode dummyNode = new GraphNode();
						task.setEndPoint(dummyNode);
						dummyNode.addIncomingEdge(task);

						GraphEdge dummyTask = new GraphEdge(Task.DUMMY_TASK);
						dummyTask.setStartPoint(dummyNode);
						dummyNode.addOutgoingEdge(dummyTask);

						dummyTask.setEndPoint(finish);
						finish.addIncomingEdge(dummyTask);
					}
				}
			} else {
				GraphEdge task = tasks.get(line[0]);

				if (line.length == 2) {
					// there is only one dependence
					if (line[1].equals("Start")) {
						// this task does not have any dependent task
						task.setStartPoint(start);
						start.addOutgoingEdge(task);
					} else {
						// this task has exactly one dependent task
						GraphEdge dependentTask = tasks.get(line[1]);
						if (dependentTask.getEndPoint() != null) {
							// join the two tasks
							task.setStartPoint(dependentTask.getEndPoint());
							dependentTask.getEndPoint().addOutgoingEdge(task);
						} else {
							GraphNode intermediateNode = new GraphNode();
							task.setStartPoint(intermediateNode);
							intermediateNode.addOutgoingEdge(task);

							dependentTask.setEndPoint(intermediateNode);
							intermediateNode.addIncomingEdge(dependentTask);
						}
					}
				} else {
					// there are multiple dependence
					// i.e, need to create dummy node and dummy edge for each
					// dependence
					GraphNode dummyNode = new GraphNode();
					task.setStartPoint(dummyNode);
					dummyNode.addOutgoingEdge(task);

					for (int j = 1; j < line.length; j++) {
						GraphEdge dependentTask = tasks.get(line[j]);

						GraphEdge dummyTask = new GraphEdge(Task.DUMMY_TASK);
						dummyTask.setEndPoint(dummyNode);
						dummyNode.addIncomingEdge(dummyTask);

						if (dependentTask.getEndPoint() != null) {
							dummyTask
									.setStartPoint(dependentTask.getEndPoint());
							dependentTask.getEndPoint().addOutgoingEdge(
									dummyTask);
						} else {
							GraphNode intermediateNode = new GraphNode();
							dependentTask.setEndPoint(intermediateNode);
							intermediateNode.addIncomingEdge(dependentTask);

							dummyTask.setStartPoint(intermediateNode);
							intermediateNode.addOutgoingEdge(dummyTask);
						}
					}
				}
			}
		}
	}
}



