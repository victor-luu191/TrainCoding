package core;

import java.util.Scanner;

public class AppleOrange {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int t = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int m = in.nextInt();
        int n = in.nextInt();
        int[] apple = new int[m];
        int nApple = 0;
        for(int apple_i=0; apple_i < m; apple_i++){
            apple[apple_i] = in.nextInt();
            if ((s<= a + apple[apple_i]) && (a + apple[apple_i] <= t)) {
            	nApple ++;
            }
        }
        System.out.println(nApple);
        
        int[] orange = new int[n];
        int nOrange = 0;
        for(int orange_i=0; orange_i < n; orange_i++){
            orange[orange_i] = in.nextInt();
            if (s <= b - orange[orange_i] && orange[orange_i] <= t) {
				nOrange ++;
			}
        }
        System.out.println(nOrange);
        in.close();
	}

}
