package yaxin.tree

class TreeIndependentSet {

	static List<Node> maxSet(Node n) {
		List<Node> nodes1 = [];
		withN(n, nodes1);

		List<Node> nodes2 = [];
		withoutN(n, nodes2);

		return nodes1.size() > nodes2.size() ? nodes1 : nodes2;
	}

	static void withN(Node n, List<Node> nodes) {
		nodes << n;
		n.children.each { c ->
			c.children.each { gc ->
				nodes << maxSet(gc);
			}
		}
	}

	static void withoutN(Node n, List<Node> nodes) {
		Map<Node, List<Node>> with = [:];
		Map<Node, List<Node>> without = [:];

		n.children.each { c ->
			List<Node> subNodes = [];
			withN(c, subNodes);
			with[c] = subNodes;
		}

		n.children.each { c ->
			List<Node> subNodes = [];
			withoutN(c, subNodes);
			without[c] = subNodes;
		}

		//need to include at least one child

		List<Node> maxNodes = [];
		n.children.each { c ->
			List<Node> _totalNodes = countWith(c, with, without);
			if(_totalNodes.size() > maxNodes.size()) {
				maxNodes = _totalNodes;
			}
		}

		nodes.addAll(maxNodes);
	}

	static List<Node> countWith(Node n, Map<Node, List<Node>> with, Map<Node, List<Node>> without) {
		List<Node> nodes = [];
		nodes.addAll(with[n]);

		with.keySet().each {
			if(it != n) {
				List<Node> withIt = with[it];
				List<Node> withoutIt = without[it];
				nodes.addAll(withIt.size() > withoutIt.size() ? withIt : withoutIt);
			}
		}

		return nodes;
	}

}
