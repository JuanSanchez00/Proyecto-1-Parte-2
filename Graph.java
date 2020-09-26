package Logging;
import java.util.logging.*;
import java.util.List;
import java.util.LinkedList;

public class Graph  {
	private List<Node> nodes;
	private List<Edge> edges;
	private static Logger logger;
	
	public Graph() {
		nodes = new LinkedList<Node>();
		edges = new LinkedList<Edge>();
		logger = Logger.getLogger(Graph.class.getName());
		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);
		logger.setLevel(Level.WARNING);//solo muestra los mensajes con nivel WARNING o superior
		Logger raizLogger = logger.getParent();
		for (Handler h : raizLogger.getHandlers()) {
			h.setLevel(Level.OFF);
		}
	}
	
	public void addNode(int node) {
		boolean perteneceNodes=false;
		for (Node n:nodes) {
			if (n.getRotulo()==node) {
				perteneceNodes=true;
				break;
			}
		}
		if (!perteneceNodes) {
			nodes.add(new Node(node));
			logger.info("Se insertó el nodo "+node+" en el grafo.");
		}
		else {
			logger.warning("No se insertó el nodo "+node+", debido a que ya pertenece al grafo.");
		}
	}
	
	public void addEdge(int node1, int node2) {
		boolean node1PerteneceNodes=false,node2PerteneceNodes=false,perteneceEdges=false;
		for (Node n:nodes) {
			if (n.getRotulo()==node1) {
				node1PerteneceNodes=true;
			}
			if (n.getRotulo()==node2) {
				node2PerteneceNodes=true;
			}
			if (node1PerteneceNodes && node2PerteneceNodes) {
				break;
			}
		}
		if (node1PerteneceNodes && node2PerteneceNodes) {
			for(Edge e:edges) {
				if (e.getNode1()==node1 && e.getNode2()==node2) {
					perteneceEdges=true;
					break;
				}
			}
			if (!perteneceEdges) {
				edges.add(new Edge(node1,node2));
				logger.info("Se insertó el arco ("+node1+","+node2+") en el grafo.");
			}
			else {
				logger.warning("No se insertó el arco ("+node1+","+node2+"), debido a que ya pertenece al grafo.");
			}
		}
		else {
			if (!node1PerteneceNodes && !node2PerteneceNodes) {
				logger.warning("No se insertó el arco ("+node1+","+node2+"), ya que los nodos "+node1+" y "+node2+" no pertenecen al grafo.");
			}
			else {
				if (!node1PerteneceNodes) {
					logger.warning("No se insertó el arco ("+node1+","+node2+"), ya que el nodo "+node1+" no pertenece al grafo.");
				}
				else {
					logger.warning("No se insertó el arco ("+node1+","+node2+"), ya que el nodo "+node2+" no pertenece al grafo.");
				}
			}
		}
	}

	public void removeNode(int node)  {
		boolean perteneceNodes=false;
		List<Edge> edgesAntigua=new LinkedList<Edge>();//guarda la lista de edges, para que al eliminar los arcos la lista del for each no se modifique
		for (Edge e:edges) {
			edgesAntigua.add(e);
		}
		for (Node n:nodes) {
			if (n.getRotulo()==node) {
				perteneceNodes=true;
				nodes.remove(n);
				logger.info("Se eliminó el nodo "+node+" del grafo.");
				for (Edge e:edgesAntigua) {
					if (e.getNode1()==node || e.getNode2()==node) {//elimino los arcos donde estaba node
						removeEdge(e.getNode1(),e.getNode2());
					}
				}
				break;
			}
		}
		if (!perteneceNodes) {
			logger.warning("No se eliminó el nodo "+node+", ya que no pertenece al grafo.");
		}
	}		
	
	public void removeEdge(int node1, int node2) {
		boolean perteneceEdges=false;
		for (Edge e:edges) {
			if (e.getNode1()==node1 && e.getNode2()==node2) {
				edges.remove(e);
				logger.info("Se eliminó el arco ("+node1+","+node2+") del grafo.");
				perteneceEdges=true;
				break;
			}
		}
		if (!perteneceEdges) {
			logger.warning("No se eliminó el arco ("+node1+","+node2+"), ya que no pertenece al grafo.");
		}
	}
}
	