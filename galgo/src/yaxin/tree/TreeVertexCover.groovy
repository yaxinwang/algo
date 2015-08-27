package yaxin.tree

class TreeVertexCover {

	//each node has one or two edges, at least one edge must connect to a vertex cover

	static List<Node> findMinCover(Node n) {
		def with = findMinCoverWithN(n);
		println "with ${n} -> ${nodesToString(with)}";

		def without = findMinCoverWithoutN(n, false);
		println "without ${n} -> ${nodesToString(without)}";

		return with.size() <= without.size() ? with : without;
	}

	static List<Node> findMinCoverWithN(Node n) {
		List<Node> nodes = [];
		nodes.add(n);

		n.children.each { c ->
			if(c.isLeaf()) {
				//n is in cover, no need to include child if it is leaf
			} else {
				def with = findMinCoverWithN(c);
				def without = findMinCoverWithoutN(c, true);
				if(with.size() <= without.size()) {
					nodes.addAll(with);
				} else {
					nodes.addAll(without);
				}
			}
		}



		return nodes;
	}

	static List<Node> findMinCoverWithoutN(Node n, boolean isParentInCover) {
		List<Node> nodes = [];

		boolean hasWith = false;
		List<WithWithout> minCovers = [];
		n.children.each { c ->
			if(c.isLeaf()) {
				//must include leaf
				hasWith = true;
				nodes.add(c);
			} else {
				def with = findMinCoverWithN(c);
				def without = findMinCoverWithoutN(c, false);
				WithWithout ww = new WithWithout();
				ww.n = c;
				ww.with = with;
				ww.without = without;
				minCovers.add(ww);
				if(with.size() <= without.size()) {
					hasWith = true;
					nodes.addAll(with);
				} else {
					nodes.addAll(without);
				}
			}
		}

		if(isParentInCover) {
			return nodes;
		}

		//n is not linked to parent, thus it has to link to at least one of its children

		if(hasWith) {
			//at least one of the children is included in cover, thus n does not need to be in cover
			return nodes;
		}

		//no child is leaf 
		
		//every child without has less nodes than with, find the child which has min(with - without)

		WithWithout minDiff = null;
		int min = Integer.MAX_VALUE;
		minCovers.each { ww ->
			int diff = ww.with.size() - ww.without.size();
			if(diff < min) {
				minDiff = ww;
			}
		}

		nodes = [];
		minCovers.each { ww ->
			if(ww == minDiff) {
				nodes.addAll(ww.with);
			} else {
				nodes.addAll(ww.without);
			}
		}

		return nodes;
	}

	static class WithWithout {
		Node n;
		List<Node> with;
		List<Node> without;
	}

	static String nodesToString(List<Node> nodes) {
		def buf = new StringBuffer();
		nodes.each { n ->
			buf.append(n).append(',');
		}
		return buf.toString();
	}
}
