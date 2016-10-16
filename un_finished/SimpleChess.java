package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import core.SimpleChess.Cell;
import core.SimpleChess.Piece;
import core.SimpleChess.State;

class SimpleChess {
	
	/* Problem
	 * 4x4 board, 
	 * Each piece's possible moves are the same as in classical chess, 
	 * each move made by any player counts as a single move
	 * Each player has exactly one Queen, at most two Rooks, and at most two minor pieces (i.e., a Bishop and/or Knight)
	 * Objective: capture the opponent’s Queen W/O LOSING your own.
	 */

//	Note that m <= 6.
	/*
	 * Strategy for B: if BQ is being defended and no pieces of W except WQ can capture BQ then B just keep defending his queen
	 */
	
	/*
	 * m = 1 (simplest)
	 * 		White has a single chance to capture Black's queen but not have to worry of its queen being recaptured, 
	 * 		thus just need to check if any White's piece can capture BQ in this single move
	 */
	
	/*
	 * m = 2: W moves 1 and B moves 1. 
	 * 		B can recapture WQ if the only way for W to capture BQ is to use his own queen & BQ is being defended
	 */
	
	/*
	 * m = 3: W move, B move, W move.
	 * 		If 1st move, W can capture BQ w/o using his own queen, done
	 * 		else: 
	 * 			if WQ can capture BQ 
	 * 				if safely (BQ is not defended), win
	 * 				else: 	ie not safely
	 * 						W need to run his queen out of the fire range of BQ, 
	 * 						then no piece of W are ready to capture BQ in W's next move -> return NO 
	 * 			
	 * 			else: 	WQ cannot capture BQ, 
	 * 				 	thus W need to find moves where other piece(s) can go to a position ready for capture BQ. 
	 * 					If no such move, return NO
	 * 					else: for each such move, 
	 * 							if B cannot rm the threat (by rm the threatening piece or run his queen to a safe place):
	 * 		    					return true as this is the winning move
	 * 							else: pass as the move is useless
	 * 						  if all such moves are useless, return NO
	 */
	
	final int LEFT = 1;
	final int RIGHT = 4;
	final int TOP = 4;
	final int BOTTOM = 1;
	
	class Cell {
		int row;
		int col;
		
		public Cell(int r, int c) {
			this.row = r;
			this.col = c;
		}
		
	}

	abstract class Piece {
		
		boolean ofWhite;
		Cell cell;
		
		public Piece(boolean ofWhite, Cell cell) {
			this.ofWhite = ofWhite;
			this.cell = cell;
		}

		abstract Set<Cell> reachableCells(State stat);

		// for captures, need to provide the whole state to see if there is any piece shielding the threatened piece
		abstract boolean canCapture(Piece other, State stat);	

		abstract boolean isDefended(State s);	// Given a state, check if a piece is defended by its allies 
	}

	
	class Rook extends Piece {

		public Rook(boolean ofWhite, Cell pos) {
			super(ofWhite, pos);
		}

		@Override
		Set<Cell> reachableCells(State stat) {
			// cells on the same row and col with the rook and not blocked by any other piece
			Set<Cell> rowCells = unBlockedCellsOnRow(this, stat);
			Set<Cell> colCells = unBlockedCellsOnCol(this, stat);
			Set<Cell> reachables = union(rowCells, colCells);
			reachables.remove(this.cell);
			return reachables;
		}
		
		
		

		@Override
		boolean canCapture(Piece other, State stat) {
			return reachableCells(stat).contains(other.cell);
		}

		@Override
		boolean isDefended(State s) {
			// TODO Auto-generated method stub
			return false;
		}

	}
	
	class Knight extends Piece {

		public Knight(boolean ofWhite, Cell pos) {
			super(ofWhite, pos);
			// TODO Auto-generated constructor stub
		}

		@Override
		boolean isDefended(State s) {
			// TODO Auto-generated method stub
			return false;
		}

		
		@Override
		boolean canCapture(Piece other, State stat) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		List<Cell> reachableCells(State stat) {
			// TODO Auto-generated method stub
			return null;
		}

	}
	
	class Bishop extends Piece {

		public Bishop(boolean ofWhite, Cell pos) {
			super(ofWhite, pos);
		}

		@Override
		boolean isDefended(State s) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		boolean canCapture(Piece other, State stat) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		List<Cell> reachableCells(State stat) {
			// TODO Auto-generated method stub
			return null;
		}

	}
	
	class Queen extends Piece {

		public Queen(boolean ofWhite, Cell pos) {
			super(ofWhite, pos);
		}

		@Override
		boolean isDefended(State s) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		boolean canCapture(Piece other, State stat) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		List<Cell> reachableCells(State stat) {
			// TODO Auto-generated method stub
			return null;
		}

	}
	
	class State {
		List<Piece> white_minor_pieces;
		List<Rook> white_rooks;
		Queen white_queen;
		
		List<Piece> black_minor_pieces;
		List<Rook> black_rooks;
		Queen black_queen;
		
		int nMove;
		
		public State(List<Piece> white_minor_pieces, List<Rook> white_rooks, Queen white_queen, 
					List<Piece> black_minor_pieces, List<Rook> black_rooks, Queen black_queen,
					int m) {
			
			this.white_minor_pieces = white_minor_pieces;
			this.white_rooks = white_rooks;
			this.white_queen = white_queen;
			this.black_minor_pieces = black_minor_pieces;
			this.black_rooks = black_rooks;
			this.black_queen = black_queen;
			
			nMove = m;
		}

		public Piece pieceAt(Cell cell) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int g = in.nextInt();
		for (int i = 0; i < g; i++) {
			int w = in.nextInt();
			int b = in.nextInt();
			int m = in.nextInt();
			
			State initState = readInitState(in, w, b);
			if (canWinIfWhite2Move(initState, m)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			
//			List<State> nextStates = buildNextStates(initState);
//			if (existWin(nextStates)) {
//				System.out.println("YES");
//			} else {
//				System.out.println("NO");
//			}
		}
		
		
	}

	private static boolean canWinIfWhite2Move(State stat, int m) {
		// TODO Auto-generated method stub
		if (m == 1) {
			Queen white_queen = stat.white_queen;
			Queen black_queen = stat.black_queen;
			return white_queen.canCapture(black_queen, stat) || canCaptureByWhiteMinors(stat, black_queen) || canCaptureByWhiteRooks(stat, black_queen);
		}
		
		
//		int nWhiteMoves = (m % 2 == 0)? m/2 : (m/2 + 1);
//		int nBlackMoves = m - nWhiteMoves;
		
		
		return false;
	}

	private static boolean canCaptureByWhiteRooks(State stat, Piece piece) {
		for (Rook rook : stat.white_rooks) {
			if (rook.canCapture(stat.black_queen, stat)) {
				return true;
			}
		}
		
		return false;
	}

	private static boolean canCaptureByWhiteMinors(State stat, Piece piece) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean existWin(List<State> states) {
		for (State stat : states) {
			if (isWin(stat)) {
				return true;
			} 
		}
		
		return false;
	}

	private static boolean isWin(State stat) {
		if (canCaptureNow(stat)) {
			return true;
		} else {
			return canWinInNextStates(stat);
		}
	}

	private static boolean canWinInNextStates(State stat) {
		List<State> nextStates = buildNextStates(stat);
		return existWin(nextStates);
	}

	private static boolean canCaptureNow(State stat) {
		Queen white_queen = stat.white_queen;
		Queen black_queen = stat.black_queen;
		
		// check if capture by rooks is possible
		List<Rook> white_rooks = stat.white_rooks;
		for (Rook rook : white_rooks) {
			if (rook.canCapture(black_queen, stat)) {
				return true;
			}
		}
		// check if capture by minors is possible
		for (Piece minor : stat.white_minor_pieces) {
			if (minor instanceof Bishop) {
				Bishop bishop = (Bishop) minor;
				if ( bishop.canCapture(black_queen, stat)) {
					return true;
				}
			}
			else {
				// is a knight
				Knight knight = (Knight) minor;
				if (knight.canCapture(black_queen, stat)) {
					return true;
				}
			}
		}
		// check if capture by white queen is possible without being recaptured given no. of remaining moves
		if (white_queen.canCapture(black_queen, stat) && ! black_queen.isDefended(stat)) {
			return true;
		}
		
		return false;
	}

	private static List<State> buildNextStates(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	private static State readInitState(Scanner in, int w, int b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
		Set<T> tmp = new TreeSet<T>(set1);
		tmp.addAll(set2);
		return tmp;
	}
	
	private Set<Cell> unBlockedCellsOnCol(Piece piece, State stat) {
		// TODO Auto-generated method stub
		return null;
	}

	private Set<Cell> unBlockedCellsOnRow(Piece piece, State stat) {
		
		Set<Cell> leftCells = leftCells(piece);
		Piece leftBlock = closestPiece2Left(piece, stat);
		if (leftBlock != null) {
			leftCells.removeAll(leftCells(leftBlock));
		}
		
		Set<Cell> rightCells = rightCells(piece);
		Piece rightBlock = closestPiece2Right(piece, stat);
		if (rightBlock != null) {
			rightCells.removeAll(rightCells(rightBlock));
		}
		
		return union(leftCells, rightCells);
	}
	
	private Piece closestPiece2Right(Piece piece, State stat) {
		// TODO Auto-generated method stub
		return null;
	}

	private Piece closestPiece2Left(Piece piece, State stat) {
		
		int r = piece.cell.row;
		int c = piece.cell.col;
		for (int i = c; i >= LEFT; i--) {
			Cell cell = new Cell(r, i);
			Piece p = stat.pieceAt(cell);
			if (p != null) {
				return p;
			}
		}
		return null;
	}

	private Set<Cell> rightCells(Piece piece) {
		// TODO Auto-generated method stub
		return null;
	}

	private Set<Cell> leftCells(Piece piece) {
		Set<Cell> leftCells = new HashSet<>();
		int r = piece.cell.row;
		int c = piece.cell.col;
		for (int i = LEFT; i < c; i++) {
			leftCells.add(new Cell(r, i));
		}
		return leftCells;
	}
	
	/*
	 * Recursive
	 * Given a state where it is White's turn to move, search thru next possible states to see if there is any win state  
	 * win state = (White can capture Black's queen w/o losing its own) OR lead to a win state later
	 * Stop condition = (White can capture Black's queen w/o losing its own) OR no way to win
	 */
}
