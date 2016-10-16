package core;

public class Equi {
	
	public int solution(int[] A) {
		
        // write your code in Java SE 8
		long sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		
		long sum_left = 0;
		//		sum_left + a[p] + sum_right = sum
		for (int p = 0; p < A.length; p++) {
			long sum_right = sum - sum_left - A[p];
			if (sum_left == sum_right) {
				return p;
			}
			sum_left += A[p];
		}
		
		return -1;
    }
}
