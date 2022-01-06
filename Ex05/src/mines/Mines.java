package mines;

import java.util.Random;

public class Mines {
	private int height;
	private int width;
	private static Random rand = new Random();// create only once
	private Cell tDArray[][];// 2D array of Cells
	private boolean showAll;

	private class Cell {// inner class for creating an 2D array to hold the hole board game
		private boolean isMine;
		private boolean isFlag;
		private boolean isOpen;
		private int minesAroundMe;

		public Cell() {
			isMine = false;
			isFlag = false;
			isOpen = false;
			minesAroundMe = 0;
		}
	}

	public Mines(int height, int width, int numMines) {
		this.height = height;
		this.width = width;
		showAll = false;
		tDArray = new Cell[height][width];// create the 2D array
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tDArray[i][j] = new Cell();
			}
		}
		int countNumOfMinesAdded = 0;
		while (countNumOfMinesAdded < numMines) {// while we didn't finish adding all the mines to the board continue
													// adding them
			if (addMine(rand.nextInt(height-1), rand.nextInt(width-1)) == true) {// that means that there is no mine over
																				// there
				countNumOfMinesAdded++;
			}
		}
	}

	public boolean addMine(int i, int j) {
		if (tDArray[i][j].isMine == false) {// if that cell in the 2D array is not a mine
			tDArray[i][j].isMine = true;// its a mine now and we need to update how many mines around me
			updateMinesAroundMe();
			return true;
		}
		return false;// cannot add a mine because its already a mine
	}

	private void updateMinesAroundMe() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tDArray[i][j].minesAroundMe = checkHowManyMinesAroundMe(i, j);
			}
		}
	}

	private int checkHowManyMinesAroundMe(int i, int j) {// update how many mines are around this cell
		int countAmountOfMines = 0;
		if (i > 0) {
			if (tDArray[i - 1][j].isMine)
				countAmountOfMines++;
		}
		if (i < height - 1) {
			if (tDArray[i + 1][j].isMine)
				countAmountOfMines++;
		}
		if (j > 0) {
			if (tDArray[i][j - 1].isMine)
				countAmountOfMines++;
		}
		if (j < width - 1) {
			if (tDArray[i][j + 1].isMine)
				countAmountOfMines++;
		}
		if (i > 0 && j > 0) {
			if (tDArray[i - 1][j - 1].isMine)
				countAmountOfMines++;
		}
		if (i < height - 1 && j > 0) {
			if (tDArray[i + 1][j - 1].isMine)
				countAmountOfMines++;
		}
		if (i > 0 && j < width - 1) {
			if (tDArray[i - 1][j + 1].isMine)
				countAmountOfMines++;
		}
		if (i < height - 1 && j < width - 1) {
			if (tDArray[i + 1][j + 1].isMine)
				countAmountOfMines++;
		}
		return countAmountOfMines;
	}

	public boolean open(int i, int j) {
		if (!tDArray[i][j].isOpen) {
			if (tDArray[i][j].isMine == true) {// if thats a mine return false
				return false;
			} else {// its not a mine
				tDArray[i][j].isOpen = true;
				if (tDArray[i][j].minesAroundMe == 0) {// no mines around me open recursively all around
					if (i > 0)
						open(i - 1, j);
					if (i < height - 1)
						open(i + 1, j);
					if (j > 0)
						open(i, j - 1);
					if (j < width - 1)
						open(i, j + 1);
					if (i > 0 && j > 0) {
						open(i - 1, j - 1);
					}
					if (i < height - 1 && j > 0) {
						open(i + 1, j - 1);
					}
					if (i > 0 && j < width - 1) {
						open(i - 1, j + 1);
					}
					if (i < height - 1 && j < width - 1) {
						open(i + 1, j + 1);
					}
					return true;
				}
			}
		}
		return true;
	}

	public void toggleFlag(int x, int y) {
		if (!tDArray[x][y].isOpen) {// check if not open first
			if (!tDArray[x][y].isFlag) {// if there is no flag put a new flag
				tDArray[x][y].isFlag = true;
			} else {
				tDArray[x][y].isFlag = false;
			}
		}
	}

	public boolean isDone() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (!tDArray[i][j].isMine && !tDArray[i][j].isOpen) {// if its not a mine and its close
					return false;
				}
			}
		}
		return true;
	}

	public String get(int i, int j) {
		if (tDArray[i][j].isFlag && !tDArray[i][j].isOpen)// if its a flag and not opened yet
			return "F";
		if (tDArray[i][j].isOpen || showAll) {
			if (tDArray[i][j].isMine)// if its a mine
				return "X";
			if (tDArray[i][j].minesAroundMe > 0)
				return "" + tDArray[i][j].minesAroundMe;
			return " ";// if its open and not a mine and there are no mines around me
		}

		return ".";
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}

	public String toString() {
		StringBuilder sB = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				sB.append(get(i, j));
			}
			sB.append("\n");
		}
		return sB.toString();
	}
}
