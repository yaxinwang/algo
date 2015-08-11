package yaxin

class TopSort {
	
	static Node topSort(DirectedGraph g) {
		Node head = null;
		List<Vertex> roots = g.findRoots();
		
		roots.each { r->
			if(head == null) {
				head = new Node(r.name);
			} else {
				Node newHead = new Node(r.name);
				newHead.name = head;
				head = newHead;
			}
			topSort(r, head);			
		}
		
		return head;
	}
	
	static void topSort(Vertex v, Node n) {
		v.state = ProcessingState.DISCOVERED;
		
		v.edges.each { e ->
			if(e.to.state == ProcessingState.DISCOVERED) {
				throw new RuntimeException("Found cycle at ${v.name}");
			} else if(e.to.state == ProcessingState.PROCESSED) {
				//stop
			} else {
				Node nextNode = new Node(e.to.name);
				n.insertAfter(nextNode);
				topSort(e.to, nextNode);
			}
		}
		
		v.state = ProcessingState.PROCESSED;
	}
}