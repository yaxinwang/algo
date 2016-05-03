package yaxin.dp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class WordDistanceTest {
		
	@Test
	public void testDistanceWithSteps() {
		List<WordEditStep> steps = WordDistanceSteps.distance("aaa", "aaa");
		assertEquals(0, steps.size());
	}
	
	@Test
	public void testDistanceWithSteps2() {		
		List<WordEditStep> steps = WordDistanceSteps.distance("baa", "aaa");
		assertEquals(1, steps.size());
		WordEditStep step = steps.get(0);
		
		assertEquals('U', step.action);
		assertEquals('a', step.c);
		assertEquals(0, step.index);
	}
	
	@Test
	public void testDistanceWithSteps3() {		
		List<WordEditStep> steps = WordDistanceSteps.distance("aab", "aaa");
		assertEquals(1, steps.size());
		WordEditStep step = steps.get(0);
		
		assertEquals('U', step.action);
		assertEquals('a', step.c);
		assertEquals(2, step.index);
	}
	
	@Test
	public void testDistanceWithSteps4() {		
		List<WordEditStep> steps = WordDistanceSteps.distance("aa", "aaa");
		assertEquals(1, steps.size());
		WordEditStep step = steps.get(0);
		
		assertEquals('A', step.action);
		assertEquals('a', step.c);
		assertEquals(2, step.index);
	}
	
	@Test
	public void testDistanceWithSteps5() {		
		List<WordEditStep> steps = WordDistanceSteps.distance("aaaa", "aaa");
		assertEquals(1, steps.size());
		WordEditStep step = steps.get(0);
		
		assertEquals('D', step.action);
		assertEquals('a', step.c);
		assertEquals(3, step.index);
	}
	
	@Test
	public void testDistanceWithSteps6() {		
		List<WordEditStep> steps = WordDistanceSteps.distance("bab", "aaa");
		assertEquals(2, steps.size());
		
		WordEditStep step = steps.get(0);		
		assertEquals('U', step.action);
		assertEquals('a', step.c);
		assertEquals(0, step.index);
		
		step = steps.get(1);		
		assertEquals('U', step.action);
		assertEquals('a', step.c);
		assertEquals(2, step.index);
	}
	
	@Test
	public void testDistanceWithSteps7() {		
		List<WordEditStep> steps = WordDistanceSteps.distance("abab", "aaa");
		assertEquals(2, steps.size());
		
		WordEditStep step = steps.get(0);		
		assertEquals('U', step.action);
		assertEquals('a', step.c);
		assertEquals(1, step.index);
		
		step = steps.get(1);		
		assertEquals('D', step.action);
		assertEquals('b', step.c);
		assertEquals(3, step.index);
	}

}
