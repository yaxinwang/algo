package yaxin.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	
	String name;
	
	Vertex(String name) {
		this.name = name;
		Graph.g.vertices.add(this);
	}
	
	List<Edge> edges = new ArrayList<Edge>();
}
