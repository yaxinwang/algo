package yaxin.graph

class Triangle {

	static List<Vertex> findTriangle(Graph g) {
		for(Vertex v : g.vertices) {
			List<Vertex> triangle = findShape(v, 3);
			if(triangle != null) {
				return triangle;
			}
		}
		return null;
	}
	
	static List<Vertex> findRectangle(Graph g) {
		for(Vertex v : g.vertices) {
			List<Vertex> triangle = findShape(v, 4);
			if(triangle != null) {
				return triangle;
			}
		}
		return null;
	}

	static List<Vertex> findShape(Vertex v, int count) {
		for(Vertex n : v.neighbors) {
			//reduce count by 1 because v is included
			List<Vertex> points = findPoints(n, v, v, count - 1);
			if(points != null) {
				points << v;
				return points;
			}
		}
		return null;
	}

	static List<Vertex> findPoints(Vertex v, Vertex fromFrom, Vertex origin, int count) {
		if(count == 1) {
			//last point should be origin
			for(Vertex n : v.neighbors) {
				if(n == origin) {
					return [v];
				}
			}
		} else {
			for(Vertex n : v.neighbors) {
				if(n == fromFrom) {
				} else {
					def points = findPoints(n, v, origin, count-1);
					if(points != null) {
						points << v;
						return points;
					}
				}
			}
		}
	}
}
