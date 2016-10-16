package core;

import java.util.Stack;

class NestedParen {
	
	public static void main(String[] args) {
		int m = 100000;
		int nLeft = 5000;
		
		String t1 = testCase(m, nLeft);
	}
	
	private static String testCase(int total, int nLeft) {
		// TODO Auto-generated method stub
		int nRight = total - nLeft;
		
		return null;
	}

	public int solution(String S) {
        
		/*
		 * Remark: based on the form (U), the FIRST left parentheses should be handled LAST, 
		 * after we have checked all the left parentheses in U
		 * So need a STACK to store left parentheses in the string
		 */
		
		/*
		 * Pass thru the string, if meet a left '(', push it into the stack
		 * Each time meet a right ')', match it with the last left ')' popped from stack (and remove this left '(' from stack)
		 * If there is no left ')' available for matching, ie stack is empty, return false
		 * If in stack there are still left '(' but no more right ')' in the string (ie end of the string), return false
		 * If we finish the pass and the stack is empty then all left '(' are matched properly with right ')', return true  
		 */
		
		Stack<Character> stack = new Stack<>(); // for storing left parentheses
		for (int i = 0; i < S.length(); i++) {
			
			char ith_char = S.charAt(i);
			if (ith_char == '(') {
				stack.push(ith_char);
			} else {// a right ')'
				if (stack.isEmpty()) {
					return 0;
				} else {
					stack.pop();
				}
			}
		}
		
		if (!stack.isEmpty()) {
			return 0;
		} else {
			return 1;
		}
    }
}
