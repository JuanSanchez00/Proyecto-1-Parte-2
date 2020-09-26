package Logging;

public class Tester {

	public static void main(String[] args) {
		Graph g=new Graph();
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(1);
		g.addEdge(1, 2);
		g.addEdge(2, 1);
		g.addEdge(1, 2);
		g.removeNode(2);
		g.removeNode(2);
		g.addEdge(2, 1);
		g.addNode(2);
		g.addEdge(2, 1);
		g.addEdge(5, 6);
		g.removeEdge(1, 3);
		
	}

}

