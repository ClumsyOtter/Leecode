package com.leecode.excellentsolution;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class MinesweeperSolutionByBFS {
	public char[][] updateBoard(char[][] board, int[] click) {
		int m = board.length, n = board[0].length;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(click);

		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			int row = cell[0], col = cell[1];

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
				} else { // Continue BFS to adjacent cells.
					board[row][col] = 'B';
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if (i == 0 && j == 0)
								continue;
							int r = row + i, c = col + j;
							if (r < 0 || r >= m || c < 0 || c < 0 || c >= n)
								continue;
							if (board[r][c] == 'E') {
								queue.add(new int[] { r, c });
								board[r][c] = 'B'; // Avoid to be added
													// again.
							}
						}
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
		MinesweeperSolutionByBFS minesweeperSolution = new MinesweeperSolutionByBFS();
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
