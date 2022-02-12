public class GameOfLife {
	
	private int rows;
	private int columns;
	private int[][] cells;
	
	public GameOfLife(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		cells = new int[rows][columns];
	}

	public void printCells() {
		for (int i = 0; i < rows; i++) {
			String str = "||";
			for (int j = 0; j < columns; j++) {
				if (cells[i][j] == 0) str+=".";
				if (cells[i][j] == 1) str+="X";
			}
			str+="||";
		}
	}
	public int getCellState(int x, int y) {
		if (x < 0 || x >= columns) return 0;
		if (y < 0 || y >= rows) return 0;
		return cells[x][y];
	}
	
	public int countAdjacent(int x, int y) {
		int count = 0;
		count += getCellState(x - 1, y - 1);
		count += getCellState(x, y -1);
		count += getCellState(x + 1, y - 1);
		count += getCellState(x - 1, y);
		count += getCellState(x + 1, y);
		count += getCellState(x - 1, y + 1);
		count += getCellState(x, y + 1);
		count += getCellState(x + 1, y + 1);
		return count;
	}
	
	public void setAlive(int x, int y) {
		cells[x][y] = 1;
	}
	public void setDead(int x, int y) {
		cells[x][y] = 0;
	}
	
	public void next() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int adjacent = countAdjacent(i, j);
				if (cells[i][j] == 1) {
					if (adjacent < 2) cells[i][j] = 0;
					if (adjacent == 3 || adjacent == 4) cells[i][j] = 1;
					if (adjacent > 4) cells[i][j] = 0;
				}
				if (cells[i][j] == 0) {
					if (adjacent == 3) cells[i][j] = 1;
					else cells[i][j] = 0;
				}
			}
		}
	}
}
