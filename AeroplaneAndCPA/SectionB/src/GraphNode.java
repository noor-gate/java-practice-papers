public class GraphNode {
	
	private static int count = 0;
	
	private String label;
	private GenericListInterface<GraphEdge> incomingEdges;
	private GenericListInterface<GraphEdge> outgoingEdges;
	private int ec;
	private int degree;
	
	public GraphNode() {
		label = String.valueOf(count++);	            // generate unique id for each node instance
		incomingEdges = new GenericList<GraphEdge>();
		outgoingEdges = new GenericList<GraphEdge>();
	}
	
	public String getLabel() {
		return label;
	}
	
	public void addIncomingEdge(GraphEdge in) {
		incomingEdges.add(1, in);
	}
	
	public GenericListInterface<GraphEdge> getIncomingEdges() {
		return incomingEdges;
	}
	
	public void addOutgoingEdge(GraphEdge out) {
		outgoingEdges.add(1, out);
	}
	
	public GenericListInterface<GraphEdge> getOutgoingEdges() {
		return outgoingEdges;
	}
	
	public void setEarliestCompletionTime(int ec) {
		this.ec = ec;
	}
	
	public int EarliestCompletionTime() {
		return ec;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	
}
