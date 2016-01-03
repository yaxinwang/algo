package yaxin.backtrack;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatrixSearch {

	int[][] matrix;

	@Before
	public void createMatrix() {
		matrix = new int[3][3];
		matrix[0][1] = 1;
		matrix[1][1] = 1;
		matrix[2][1] = 1;
	}

	@Test
	public void searchWithBestLocalSolution() {
		int max = SearchWithBestLocalSolution.findMax(matrix);
		System.out.println(max);
	}
	
	@Test
	public void searchWithBacktracking() {
		Backtracking.search(matrix);
	}

	static class SearchWithBestLocalSolution {

		static int findMax(int[][] m) {
			int sum = m[0][0];
			int findDown = findMax(m, 1, 0);
			int findRight = findMax(m, 0, 1);
			return sum + (findDown > findRight ? findDown : findRight);
		}

		static int findMax(int[][] m, int x, int y) {
			int v = m[x][y];
			int findDown = x < m.length - 1 ? findMax(m, x + 1, y) : 0;
			int findRight = y < m[x].length - 1 ? findMax(m, x, y + 1) : 0;
			return v + (findDown > findRight ? findDown : findRight);
		}
	}

	static class Backtracking {

		static List<String> steps = new ArrayList<String>();

		static int max = 0;

		static int x = -1;

		static int y = -1;

		static int total = 0;

		static void search(int[][] m) {
			if (isSolution(m)) {
				if (total > max) {
					max = total;
					System.out.println("Max: " + max);
				}
				print();
			}

			List<String> candidates = generateCandidates(m);

			for (String candidate : candidates) {
				apply(m, candidate);
				search(m);
				reverse(m, candidate);
			}
		}

		private static void reverse(int[][] m, String candidate) {
			steps.remove(steps.size() - 1);
			total = total - m[x][y];
			
			if(candidate == "*") {
				x = -1;
				y = -1;
			} else if(candidate == "v") {
				x = x - 1;
			} else if(candidate == ">") {
				y = y - 1;
			}
		}

		private static void apply(int[][] m, String candidate) {
			steps.add(candidate);
			if(candidate == "*") {
				x = 0;
				y = 0;
			} else if(candidate == "v") {
				x = x+1;
			} else if(candidate == ">") {
				y = y+1;
			}
			total = total + m[x][y];
		}

		private static List<String> generateCandidates(int[][] m) {
			List<String> candidates = new ArrayList<String>();

			if (x == -1) {
				candidates.add("*");
			} else {
				if (x < m.length - 1) {
					candidates.add("v");
				}

				if (y < m[0].length - 1) {
					candidates.add(">");
				}
			}

			return candidates;
		}

		private static void print() {
			StringBuffer buf = new StringBuffer();
			for (String step : steps) {
				buf.append(step).append(",");
			}

			buf.append(" total=" + total);
			System.out.println(buf);
		}

		private static boolean isSolution(int[][] m) {
			return x == m.length - 1 && y == m[0].length - 1;
		}
	}

}
