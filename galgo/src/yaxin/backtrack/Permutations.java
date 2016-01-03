package yaxin.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Permutations {

	@Test
	public void findPermutations() {		
		findPermutations(new ArrayList<Integer>(), Arrays.asList(1,2,3));
	}

	private void findPermutations(ArrayList<Integer> solution, List<Integer> input) {
		//solution is the current state
		
		if(isCompleteSolution(solution, input)) {
			processSolution(solution);
		} else {
			List<Integer> candidates = constructCandidates(solution, input);
			for(Integer candidate : candidates) {				
				/*
				//update solution with the candidate
				addToFront(candidate, solution, input);
				findPermutations(solution, input);
				//backout the candidate from the solution
				removeFromFront(candidate, solution, input);
				*/
				
				//update solution with the candidate
				addToEnd(candidate, solution, input);
				findPermutations(solution, input);
				//backout the candidate from the solution
				removeFromEnd(candidate, solution, input);
			}
		}
		
	}

	private void removeFromEnd(Integer candidate, ArrayList<Integer> solution, List<Integer> input) {
		solution.remove(solution.size() - 1);
	}

	private void addToEnd(Integer candidate, ArrayList<Integer> solution, List<Integer> input) {
		solution.add(candidate);
	}

	private void removeFromFront(Integer candidate, ArrayList<Integer> solution, List<Integer> input) {
		solution.remove(0);
	}

	private void addToFront(Integer candidate, ArrayList<Integer> solution, List<Integer> input) {
		solution.add(0, candidate);		
	}

	private List<Integer> constructCandidates(ArrayList<Integer> solution, List<Integer> input) {
		List<Integer> candidates = new ArrayList<Integer>();
		for(Integer i : input) {
			if( !solution.contains(i) ) {
				candidates.add(i);
			}
		}
		return candidates;
	}

	private void processSolution(ArrayList<Integer> solution) {
		String s = "";
		for(Integer i : solution) {
			s = s + " " + i;
		}
		System.out.println(s);		
	}

	private boolean isCompleteSolution(ArrayList<Integer> solution, List<Integer> input) {
		return solution.size() == input.size();
	}
}
