package yaxin

class Vertex {
		
		String name;
	
		ProcessingState state;
	
		List<Edge> edges = new ArrayList<Edge>();
	
		public Vertex(String name) {
			super();
			this.name = name;
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
