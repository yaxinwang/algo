package yaxin.backtrack;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AllPaths {

	@Before
	public void createGraph() {
		Graph.newGraph();
		
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		Vertex f = new Vertex("f");
		
		new Edge(a, b);
		new Edge(a, c);
		new Edge(a, d);
		new Edge(c, e);
		new Edge(d, e);
		new Edge(d, f);
	}
	
	@Test
	public void findOnePath() {
		List<Vertex> path = findOnePath(Graph.g.getVertex("a"), Graph.g.getVertex("e"));
		printPath(path);
	}
	
	@Test
	public void findAllPaths() {
		findAllPaths(Graph.g.getVertex("a"), Graph.g.getVertex("e"));
	}

	List<Vertex> path = new ArrayList<Vertex>();
	
	private void findAllPaths(Vertex s, Vertex e) {
		if(isSolution(s, e)) {
			System.out.println(">>> Found solution");
			printPath(path);
			return;
		}
		
		List<Vertex> candidates = findCandidates(s);
		if(candidates.size() == 0) {
			System.out.println(">>> Reached deadend");
			printPath(path);
			return;
		}
		
		for(Vertex candidate : candidates) {
			addToSolution(candidate);
			findAllPaths(candidate, e);
			removeFromSolution(candidate);
		}
	}
	
	private List<Vertex> findCandidates(Vertex s) {
		List<Vertex> candidates = new ArrayList<Vertex>();
		for(Edge edge : s.edges) {
			candidates.add(edge.getOther(s));
		}
		return candidates;
	}
	
	private void addToSolution(Vertex v) {
		path.add(v);
	}
	
	private void removeFromSolution(Vertex v) {
		path.remove(path.size() - 1);
	}

	private boolean isSolution(Vertex s, Vertex e) {
		return s == e;
	}
	
	

	private List<Vertex> findOnePath(Vertex s, Vertex e ) {
		System.out.println(s.name + ">" + e.name);
		
		for(Edge edge : s.edges) {
			if(edge.getOther(s) == e) {
				List<Vertex> path = new ArrayList<Vertex>();
				path.add(e);
				path.add(s);
				return path;
			}
		}
		
		for (Edge edge : s.edges) {
			List<Vertex> path = findOnePath(edge.getOther(s), e);
			if(path != null) {
				path.add(s);
				return path;
			}
		}
		
		return null;
	}

	private void printPath(List<Vertex> path) {
		if(path == null) {
			System.out.println("no path");
		}
		
		StringBuffer buf = new StringBuffer();
		for(Vertex v : path) {
			buf.append(v.name).append(",");
		}
		System.out.println(buf);
	}
	
}
