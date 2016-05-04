package yaxin.str;

import org.junit.Test;

public class SuffixTree {

	public static Node build(String s) {
		Node root = new Node("");
		for (int i = s.length(); i > 0; i--) {
			add(s.substring(i - 1), root);
		}
		return root;
	}

	private static void add(String ss, Node root) {
		for (Node child : root.children) {
			if (child.text.charAt(0) == ss.charAt(0)) {
				append(ss, child);
				return;
			}
		}

		root.children.add(new Node(ss));
	}

	private static void append(String ss, Node child) {
		if (ss.startsWith(child.text)) {
			String remaining = ss.substring(child.text.length());
			add(remaining, child);
		} else {
			String common = common(ss, child.text);
			String remainingNew = ss.substring(common.length());
			String remainingOld = child.text.substring(common.length());
			child.text = common;
			child.children.add(new Node(remainingOld));
			child.children.add(new Node(remainingNew));
		}
	}

	private static String common(String ss, String text) {
		int len = ss.length() < text.length() ? ss.length() : text.length();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (ss.charAt(i) == text.charAt(i)) {
				buf.append(ss.charAt(i));
			} else {
				break;
			}
		}
		return buf.toString();
	}
	
	@Test
	public void banana() {
		System.out.println("banana");
		
		Node n = build("banana");
		System.out.println(n);
	}
}
