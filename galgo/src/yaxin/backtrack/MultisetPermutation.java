package yaxin.backtrack;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MultisetPermutation {
	
	int[] ms = {1,1,2,2};
	
	List<List<Integer>> solutions = new ArrayList<List<Integer>>();
	
	List<Integer> state = new ArrayList<Integer>();

	@Test
	public void run() {
		System.out.println("run....");
		
		permutate();
	}
	
	void permutate() {
		if(isSolution()) {
			processSolution();
			return;
		}
		
		List<Integer> candidates = nextCandidates();
		
		for(int c : candidates) {
			addToSolution(c);
			permutate();
			removeFromSolution();
		}
	}

	private void removeFromSolution() {
		state.remove(state.size() - 1);
	}

	private void addToSolution(int c) {
		state.add(c);
	}

	private List<Integer> nextCandidates() {
		List<Integer> candidates = new ArrayList<Integer>();
		for(int i=0; i<ms.length; i++) {
			if(!state.contains(i)) {
				candidates.add(i);
			}
		}
		return candidates;
	}

	private void processSolution() {
		List<Integer> solution = toSolution(state);
		
		if(contains(solutions, solution)) {
			return;
		}
		
		solutions.add(solution);
		
		StringBuffer sb = new StringBuffer();
		for(int e : solution) {
			sb.append(e).append(",");
		}
		System.out.println(sb);
	}

	private List<Integer> toSolution(List<Integer> state) {
		List<Integer> solution = new ArrayList<Integer>();
		for(int i : state) {
			solution.add(ms[i]);
		}
		return solution;
	}

	private boolean isSolution() {
		return state.size() == ms.length;
	}

	private boolean contains(List<List<Integer>> solutions, List<Integer> state) {
		for(List<Integer> solution : solutions) {
			if(equals(solution, state)) {
				return true;
			}
		}
		
		return false;
	}

	private boolean equals(List<Integer> solution, List<Integer> s2) {
		for(int i=0; i<solution.size(); i++) {
			if(solution.get(i) != s2.get(i)) {
				return false;
			}
		}
		
		return true;
	}
}
