package com.leecode.excellentsolution;

import org.junit.Test;


public class MinesweeperSolutionByDFS {
	public char[][] updateBoard(char[][] board, int[] click) {
		int m = board.length, n = board[0].length;
		int row = click[0], col = click[1];

		if (board[row][col] == 'M') { // Mine
			board[row][col] = 'X';
		} else { // Empty
					// Get number of mines first.
			int count = 0;
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (i == 0 && j == 0)
						continue;
					int r = row + i, c = col + j;
					if (r < 0 || r >= m || c < 0 || c < 0 || c >= n)
						continue;
					if (board[r][c] == 'M' || board[r][c] == 'X')
						count++;
				}
			}

			if (count > 0) { // If it is not a 'B', stop further DFS.
				board[row][col] = (char) (count + '0');
			} else { // Continue DFS to adjacent cells.
				board[row][col] = 'B';
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (i == 0 && j == 0)
							continue;
						int r = row + i, c = col + j;
						if (r < 0 || r >= m || c < 0 || c < 0 || c >= n)
							continue;
						if (board[r][c] == 'E')
							updateBoard(board, new int[] { r, c });
					}
				}
			}
		}

		return board;
	}
	@Test
	public void test() {
		char[][] board = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
				{ 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' } };
		int[] click = { 3, 0 };
		MinesweeperSolutionByDFS minesweeperSolution = new MinesweeperSolutionByDFS();
		char[][] updateBoard = minesweeperSolution.updateBoard(board, click);
		for (int i = 0; i < updateBoard.length; i++) {
			for (int j = 0; j < updateBoard[i].length; j++) {
				if (updateBoard[i][j] == 0) {
					System.out.print('0');
				} else {
					System.out.print(updateBoard[i][j]);
				}
			}
			System.out.println();
		}
	}
}
