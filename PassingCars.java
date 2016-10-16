package core;

public class PassingCars {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int solution(int[] A) {
       
		int total = 0;
		for (int i = 0; i < A.length; i++) {
			total += A[i];
		}
		
		int[] pref = prefix_sums(A);
		// for each given i s.t. a[i], no. of passing pairs starting with i (ie [i,j], j after i) 
		// is the suffix sum of suf[i]
		int total_count = 0;
		int[] suf = new int[A.length];
		
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) {
				suf[i] = total - pref[i] - A[i];
				total_count += suf[i];
			}
		}
		
		// When total count exceeds Integer.MAX_VALUE or  Math.pow(10, 9)
		if ((total_count < 0) || (total_count > Math.pow(10, 9))) {
			return -1;
		} else {
			return total_count;
		}
    }

	private int[] prefix_sums(int[] arr) {

		int[] tmp_pref = new int[arr.length + 1];
		for (int i = 1; i <= arr.length ; i++) {
			tmp_pref[i] = tmp_pref[i-1] + arr[i-1];
		}
		
		int[] pref = new int[arr.length];
		for (int i = 0; i < pref.length; i++) {
			pref[i] = tmp_pref[i+1];
		}
		
		return pref;
	}
}
