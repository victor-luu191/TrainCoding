package core;

import java.util.Arrays;
import java.util.Scanner;

class GridlandMetro {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		
		int[][] trackInfo = markOccupiedCells(in, n, m, k);
		System.out.println(nFreeCell(trackInfo));
		in.close();
	}

	/**
	 * @param track: 2d array recording track index of each cell, cells with index 0 are NOT occupied by any track
	 * @return no. of free cells
	 */
	private static int nFreeCell(int[][] track) {
		
		int nOccupied = 0;
		int nrow = track.length;
		int ncol = track[0].length;
		for (int i = 0; i < nrow; i++) {
			for (int j = 0; j < ncol; j++) {
				if (track[i][j] > 0) {
					nOccupied ++;
				}
			}
		}
		
		int nFree = nrow * ncol - nOccupied;
		return nFree;
	}

	private static int[][] markOccupiedCells(Scanner in, int n, int m, int k) {
		
		int[][] trackInfo = new int[n][m];
		for (int i = 0; i < k; i++) {// find cells occupied by each track and mark them
			
			int trackIndex = i + 1;
			
			int r = in.nextInt();
			int c1 = in.nextInt();
			int c2 = in.nextInt();
			// On row (r-1), cells from c1-1 to c2-1 are occupied, 
			// thus cells in [0,c1-1) and [c2,m) are free
			for (int j = c1-1; j < c2; j++) {
				trackInfo[r-1][j] = trackIndex;
			}
//			System.out.println(Arrays.toString(trackInfo[r-1]));
		}
		return trackInfo;
	}

	private static int nTrue(boolean[][] logic) {
		int c = 0;
		int nrow = logic.length;
		int ncol = logic[0].length;
		for (int r = 0; r < nrow; r++) {
			for (int col = 0; col < ncol; col++) {
				if (logic[r][col]) {
					c++;
				}
			}
		}
		return c;
	}
}
