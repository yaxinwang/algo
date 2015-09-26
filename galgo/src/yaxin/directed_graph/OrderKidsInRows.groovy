package yaxin.directed_graph

import javax.swing.text.DefaultEditorKit.PreviousWordAction;

class OrderKidsInRows {


	public static void main(String[] args) {
		Row headRow = order(getFirstGroup());

		while(headRow != null) {
			println headRow.kids.iterator().join(',');
			headRow = headRow.next;
		}
		
		println '=============';
		
		headRow = order(getSecondGroup());
		while(headRow != null) {
			println headRow.kids.iterator().join(',');
			headRow = headRow.next;
		}
	}

	static Row order(List<Kid> kids) {
		Row headRow = new Row();

		kids.each { kid ->
			if( !kid.inLine ) {
				order(kid, headRow);
				headRow = headRow.head();
			}
		}

		return headRow;
	}

	static void order(Kid kid, Row candidateRow) {
		kid.pending = true;

		if(candidateRow.hasEnemyOf(kid)) {
			//need to add to a row before the candidateRow
			Row r = new Row();
			candidateRow.insertBefore(r);
			candidateRow = r;
		}

		candidateRow.kids << kid;

		kid.enemies.each { e ->
			if(!e.inLine) {
				if(e.pending) {
					throw new RuntimeException("Found loop!");
				}

				if(candidateRow.next == null) {
					candidateRow.next = new Row();
				}

				order(e, candidateRow.next);
			}
		}

		kid.pending = false;
		kid.inLine = true;
	}

	static List<Kid> getFirstGroup() {
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

	static List<Kid> getSecondGroup() {
		List<Kid> kids = [];

		for(int i=1; i<=6; i++) {
			kids << new Kid(i);
		}

		kids[0].enemies << kids[1];
		kids[1].enemies << kids[2];

		kids[3].enemies << kids[4];
		kids[4].enemies << kids[5];

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

	static class Row {
		List<Kid> kids = [];
		Row previous;
		Row next;

		boolean hasEnemyOf(Kid kid) {
			for(Kid e : kid.enemies) {
				if(kids.contains(e)) {
					return true;
				}
			}

			return false;
		}

		void insertBefore(Row r) {
			r.next = this;
			r.previous = previous;

			if(previous != null) {
				previous.next = r;
			}

			this.previous = r;
		}

		Row head() {
			Row r = this;
			while(true) {
				if(r.previous == null) {
					return r;
				}
				r = r.previous;
			}
		}
	}
}
