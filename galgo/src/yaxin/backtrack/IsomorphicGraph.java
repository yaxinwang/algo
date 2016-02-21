package yaxin.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IsomorphicGraph {
	
	Graph g1;
	Graph g2;
	List<VertexPair> processedPairs = new ArrayList<VertexPair>();
	
	boolean isIsomorphic = false;
	
	@Before
	public void buildGraphs() {
		Graph.newGraph();
		g1 = Graph.g;
		
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		
		new Edge(a, b);
		new Edge(a, c);
		
		Graph.newGraph();
		g2 = Graph.g;
		
		Vertex x = new Vertex("x");
		Vertex y = new Vertex("y");
		Vertex z = new Vertex("z");
		
		new Edge(x, z);
		new Edge(y, z);
	}

	@Test
	public void run() {
		System.out.println("run...");
		
		isomorphic();
		
		System.out.println("isIsomorphic? " + isIsomorphic);
	}
	
	void isomorphic() {
		if(isIsomorphic) {
			return;
		}
		
		List<VertexPair> pairs = nextPairs();
		for(VertexPair pair : pairs) {
			boolean partialIsomorphic = processPair(pair);
			if(partialIsomorphic) {
				if(processedPairs.size() == g1.vertices.size()) {
					isIsomorphic = true;
					break;
				} else {
					isomorphic();
				}
			}
			reversePair();
		}
	}
	
	

	private void reversePair() {
		VertexPair lastPair = processedPairs.remove(processedPairs.size() - 1);
		lastPair.left.alias = null;
		lastPair.right.alias = null;
	}

	private boolean processPair(VertexPair pair) {
		System.out.println("processPair " + pair);
		
		processedPairs.add(pair);
		
		List<Vertex> leftNeighbors = pair.left.getNeighbors();
		List<Vertex> leftProcessedNeighbors = getProcessedNeighbors(leftNeighbors);
		List<Vertex> leftUnprocessedNeighbors = getUnprocessedNeighbors(leftNeighbors);
		
		List<Vertex> rightNeighbors = pair.right.getNeighbors();
		List<Vertex> rightProcessedNeighbors = getProcessedNeighbors(rightNeighbors);
		List<Vertex> rightUnprocessedNeighbors = getUnprocessedNeighbors(rightNeighbors);
		
		if(equals(leftProcessedNeighbors, rightProcessedNeighbors) && leftUnprocessedNeighbors.size() == rightUnprocessedNeighbors.size()) {
			pair.left.alias = pair.left.name;
			pair.right.alias = pair.left.name;			
			return true;
		}
		
		return false;
	}

	private boolean equals(List<Vertex> leftVertices, List<Vertex> rightVertices) {
		if(leftVertices.size() != rightVertices.size()) {
			return false;
		}
		
		String leftJointedAlias = joinAlias(leftVertices);
		String rightJointedAlias = joinAlias(rightVertices);
		return leftJointedAlias.equals(rightJointedAlias);
		
	}

	private String joinAlias(List<Vertex> vertices) {
		List<String> aliasList = new ArrayList<String>();
		for(Vertex v : vertices) {
			aliasList.add(v.alias);
		}
		Collections.sort(aliasList);
		
		StringBuffer sb = new StringBuffer();
		for(String s : aliasList) {
			sb.append(s);
		}
		return sb.toString();
	}

	private List<Vertex> getUnprocessedNeighbors(List<Vertex> vertices) {
		List<Vertex> resultVertices = new ArrayList<Vertex>();
		for(Vertex v : vertices) {
			if(v.alias == null) {
				resultVertices.add(v);
			}
		}
		
		return resultVertices;
	}

	private List<Vertex> getProcessedNeighbors(List<Vertex> vertices) {
		List<Vertex> resultVertices = new ArrayList<Vertex>();
		for(Vertex v : vertices) {
			if(v.alias != null) {
				resultVertices.add(v);
			}
		}
		
		return resultVertices;
	}

	private List<VertexPair> nextPairs() {
		List<VertexPair> pairs = new ArrayList<VertexPair>();
		
		Vertex leftVertex = nextUnprocessedVertex(g1.vertices);
		if(leftVertex != null) {
			for(Vertex rightVertex : g2.vertices) {
				if(rightVertex.alias == null) {
					pairs.add(new VertexPair(leftVertex, rightVertex));
				}
			}
		}
		
		return pairs;
	}

	private Vertex nextUnprocessedVertex(List<Vertex> vertices) {
		for(Vertex v : vertices) {
			if(v.alias == null) {
				return v;
			}
		}
		return null;
	}

	static class VertexPair {
		Vertex left;
		Vertex right;
		public VertexPair(Vertex left, Vertex right) {
			super();
			this.left = left;
			this.right = right;
		}
		
		public String toString() {
			return left.name + "," + right.name;
		}
	}
}
