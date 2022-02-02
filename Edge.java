
public class Edge {
	int endNode;
	int flow;
	public Edge( int eNode, int flow) {
		this.endNode = eNode;
		this.flow = flow;
	}
	public void updateEdgepositively(int flow) {
		this.flow += flow;
	}
	public void updateEdgenegatively(int flow) {
		this.flow -= flow;
	}


}
