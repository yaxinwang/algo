package yaxin.directed_graph

class OrderKids {


	public static void main(String[] args) {
		order();
	}

	static void order() {
		List<Kid> kids = getKids();

		List<Kid> line = [];

		kids.each { kid ->
			if( !kid.inLine ) {
				order(kid, line);
			}
		}

		println line.reverse().iterator().join(',');
	}

	static void order(Kid kid, List<Kid> line) {
		kid.pending = true;

		kid.enemies.each { e ->
			if(!e.inLine) {
				if(e.pending) {
					throw new RuntimeException("Found loop!");
				}
				order(e, line);
			}
		}

		line << kid;

		kid.pending = false;
		kid.inLine = true;
	}

	static List<Kid> getKids() {
		List<Kid> kids = [];

		for(int i=1; i<=6; i++) {
			kids << new Kid(i);
		}

		kids[2].enemies << kids[1];
		kids[2].enemies << kids[3];

		kids[1].enemies << kids[0];
		kids[1].enemies << kids[4];

		kids[4].enemies << kids[5];

		kids[5].enemies << kids[3];
		
		//cause loop
		//kids[3].enemies << kids[1];

		return kids;
	}


	static class Kid {
		int id;
		List<Kid> enemies = [];
		boolean inLine = false;
		boolean pending = false;

		Kid(int id) {
			this.id = id;
		}

		String toString() {
			return "" + id;
		}
	}
}
