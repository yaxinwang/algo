package yaxin.dp;

import java.util.LinkedList;
import java.util.List;

public class WordDistanceSteps {

	static List[][] matrix;
	
	public static List<WordEditStep> distance(String left, String right) {
		 matrix = new List[left.length()+1][right.length()+1];
		
		 
		return distance(left, 0, right, 0);
	}

	private static List<WordEditStep> distance(String left, int leftIndex, String right, int rightIndex) {
		if(matrix[leftIndex][rightIndex] != null) {
			return matrix[leftIndex][rightIndex];
		}
		
		if(left.length() == leftIndex && right.length() == rightIndex) {
			return new LinkedList();
		}
		
		if(left.length() == leftIndex && right.length() > rightIndex) {
			//Add
			List<WordEditStep> steps = new LinkedList<WordEditStep>();
			int j=0;
			for(int i=rightIndex; i<right.length(); i++) {
				steps.add(new WordEditStep(leftIndex+j, 'A', right.charAt(i)));
				j++;
			}
			matrix[leftIndex][rightIndex] = steps;
			return steps;
		}
		
		if(leftIndex < left.length() && rightIndex == right.length()) {
			//Delete
			List<WordEditStep> steps = new LinkedList<WordEditStep>();
			for(int i=leftIndex; i<left.length(); i++) {
				steps.add(new WordEditStep(i, 'D', left.charAt(i)));
			}
			matrix[leftIndex][rightIndex] = steps;
			return steps;
		}
		
		if(left.charAt(leftIndex) == right.charAt(rightIndex)) {
			return distance(left, leftIndex+1, right, rightIndex+1);
		}

		List<WordEditStep> stepsAfterUpdate = distance(left, leftIndex+1, right, rightIndex+1);
		
		List<WordEditStep> stepsAfterDelete = distance(left, leftIndex+1, right, rightIndex);
		
		List<WordEditStep> stepsAfterAdd = distance(left, leftIndex, right, rightIndex+1);
		
		if(stepsAfterUpdate.size() <= stepsAfterDelete.size() && stepsAfterUpdate.size() <= stepsAfterAdd.size()) {
			List<WordEditStep> steps = new LinkedList<WordEditStep>();
			steps.add(new WordEditStep(leftIndex, 'U', right.charAt(rightIndex)));
			steps.addAll(stepsAfterUpdate);
			matrix[leftIndex][rightIndex] = steps;
			return steps;
		}
		
		if(stepsAfterDelete.size() <= stepsAfterUpdate.size() && stepsAfterDelete.size() <= stepsAfterAdd.size()) {
			List<WordEditStep> steps = new LinkedList<WordEditStep>();
			steps.add(new WordEditStep(leftIndex, 'D', left.charAt(leftIndex)));
			steps.addAll(stepsAfterDelete);
			matrix[leftIndex][rightIndex] = steps;
			return steps;
		}
		
		List<WordEditStep> steps = new LinkedList<WordEditStep>();
		steps.add(new WordEditStep(leftIndex, 'A', right.charAt(rightIndex)));
		steps.addAll(stepsAfterAdd);
		matrix[leftIndex][rightIndex] = steps;
		return steps;
	}
}
