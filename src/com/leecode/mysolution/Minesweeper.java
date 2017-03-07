package com.leecode.mysolution;

import org.junit.Test;

//扫雷
public class Minesweeper {

	public char[][] updateBoard(char[][] board, int[] click) {
		char[][] sboard = getBoard(board);
		// 确定二维数组大小
		int higth = board.length;
		char[] temp = board[0];
		int weigth = temp.length;
		// 得到对照表开始对click处理
		// 判断click是否合法
		if ((click[0] >= 0) && (click[0] < higth) && (click[1] >= 0) && (click[1] < weigth)) {
			// 判断click的点
			// 点中炸弹游戏结束
			if (board[click[0]][click[1]] == 'M') {
				board[click[0]][click[1]] = 'X';
				return board;
				// 点中已经打开的地方无效
			} else if (board[click[0]][click[1]] == 'B') {
				return board;
				// 点中未打开的地方
			} else {
				// 如果未打开的地方是空白 则将3x3空格全部打开
				if (sboard[click[0]][click[1]] == 0) {
					// int[] flag = new int[2];
					// flag[0] = -1;
					// flag[1] = -1;
					return blank(board, click, sboard);
					// for (int i = -1; i < 2; i++)
					// for (int j = -1; j < 2; j++) {
					// if ((i != -1) && (j != -1)) {
					// flag[0] = i;
					// flag[1] = j;
					// blank = blank(blank, click, sboard, flag);
					// }
					// }

				} else {
					board[click[0]][click[1]] = sboard[click[0]][click[1]];
					return board;
				}

			}
		} else {
			System.out.println("Click 出界");
		}
		return board;
	}

	// 点击到了空白递归打开
	public char[][] blank(char[][] broad, int[] click, char[][] sboard) {
		int n = click[0];
		int m = click[1];
		// 合法扩张
		if (broad[n][m] == 'E') {
			broad[n][m] = sboard[n][m];
			if (broad[click[0]][click[1]] == 0) {
				for (int i = n - 1; n < i + 2; i++) {
					for (int j = m - 1; j < m + 2; j++) {
						if ((i >= 0) && (i < broad.length) && (j >= 0) && (j < broad[0].length)) {
							click[0] = i;
							click[1] = j;
							blank(broad, click, sboard);
						}
					}
				}
			}
		}
		return broad;
	}

	public char[][] getBoard(char[][] board) {
		// 确定二维数组大小
		int higth = board.length;
		char[] temp = board[0];
		int weigth = temp.length;
		char[][] sboard = new char[higth][weigth];
		// 遍历二维数组找出地雷并利用地雷推出对照表
		for (int i = 0; i < higth; i++) {
			for (int j = 0; j < weigth; j++) {
				// 找到地雷
				if (board[i][j] == 'M') {
					sboard[i][j] = 'M';
					// 以地雷为中心3X3布数
					for (int n = i - 1; n < i + 2; n++) {
						for (int m = j - 1; m < j + 2; m++) {
							// 除去自己
							if (n != i || m != j)
								if ((m >= 0) && (m < weigth) && (n >= 0) && (n < higth)) {
									// 除去3X3中包含的地雷
									if (sboard[n][m] != 'M')
										// 布数
										if (sboard[n][m] == 0) {
											sboard[n][m] = '1';
										} else {
											sboard[n][m] = (char) (sboard[n][m] + 1);
										}
								}
						}
					}
				}
			}
		}
		return sboard;
	}

	@Test
	public void test() {
		char[][] board = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
				{ 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' } };
		int[] click = { 3, 0 };
		Minesweeper minesweeper = new Minesweeper();
		char[][] updateBoard = minesweeper.updateBoard(board, click);
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
