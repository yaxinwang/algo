package yaxin.dp;

public class WordDistanceRecursive {
	
	public static int distance(String left, String right) {
		
		//termination
		
		if(left.length() == 0) {
			return right.length();
		}
		
		if(right.length() == 0) {
			return left.length();
		}
		
		if(left.charAt(0) == right.charAt(0)) {
			return distance(left.substring(1), right.substring(1));
		}
		
		Pair p1 = delete(left.charAt(0), right);
		int totalSteps1 = p1.steps + distance(left.substring(1), p1.right);
		
		int totalSteps2 = 1 + distance(left.substring(1), right.substring(1));
		
		int totalSteps3 = 1 + distance(left.substring(1), right);
		
		return min(totalSteps1, totalSteps2, totalSteps3);
	}
	
	private static Pair delete(char c, String right) {
		for(int i=0; i<right.length(); i++) {
			if(right.charAt(i) == c) {
				return new Pair(i, right.substring(i+1));
			}
		}
		
		return new Pair(right.length(), "");
	}

	private static int min(int a, int b, int c) {
		return min(a, min(b, c));
	}

	private static int min(int b, int c) {
		return b < c ? b : c;
	}

	static class Pair {
		int steps;
		String right;
		public Pair(int steps, String right) {
			this.steps = steps;
			this.right = right;
		}
	}

}
