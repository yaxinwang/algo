package yaxin

class Vertex {
		
		private String name;
	
		private ProcessingState state;
	
		private List<Edge> edges = new ArrayList<Edge>();
	
		public Vertex(String name) {
			super();
			this.name = name;
		}
	
		public String getName() {
			return name;
		}
	
		public List<Edge> getEdges() {
			return edges;
		}
	
		public ProcessingState getState() {
			return state;
		}
	
		public void setState(ProcessingState state) {
			this.state = state;
		}
	
		public void connect(Vertex... vertices) {
			for (Vertex v : vertices) {
				Edge e = new Edge();
				e.setFrom(this);
				e.setTo(v);
				edges.add(e);
			}
		}
		
		public String toString() {
			StringBuffer buf = new StringBuffer();
			buf.append(name);
			if(edges.size() > 0) {
				buf.append('->');
			}
			edges.each(){
				buf.append(it.to.name).append(',');
			};
			return buf.toString();
		}
	
}
