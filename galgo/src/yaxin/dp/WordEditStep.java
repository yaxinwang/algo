package yaxin.dp;

public class WordEditStep {

	// Delete, Update, Add
	char action;
	int index;
	char c;

	public WordEditStep(int index, char action, char c) {
		super();
		this.index = index;
		this.action = action;
		this.c = c;
	}

}
