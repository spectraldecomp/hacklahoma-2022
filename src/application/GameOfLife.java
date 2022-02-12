package application;
public class GameOfLife {
	//Data
	public int width;
	public int height;
	public int[][] cells;
	//Constructors
	public GameOfLife(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new int[width][height];
	}
	public static GameOfLife copy(GameOfLife game) {
		GameOfLife copy = new GameOfLife(game.width, game.height);
		
		for (int i = 0; i < game.height; i++) {
			for (int j = 0; j < game.width; j++) {
				copy.setCellState(j, i, game.getCellState(j,i));
			}
			
		}
		
		return copy;	
	}
	//returns state of cell
	public int getCellState(int x, int y) {
		if (x < 0 || x >= width) return 0;
		if (y < 0 || y >= height) return 0;
		return cells[x][y];
	}
	//returns number of alive adjacent cells
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
	//Setters
	public void setAlive(int x, int y) {
		setCellState(x, y, 1);
	}
	public void setDead(int x, int y) {
		setCellState(x, y, 0);
	}
	public void setCellState(int x, int y, int cellState ) {
		if (x < 0 || x >= width) return;
		if (y < 0 || y >= height) return;
		cells[x][y] = cellState;
	}
	//Checks each cell using Game of Life logic; creates new cells
	public void next() {
		int[][] newcells = new int[width][height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int adjacent = countAdjacent(j, i);
				if (getCellState(j, i) == 1) {
					if (adjacent < 2) newcells[j][i] = 0;
					if (adjacent == 2 || adjacent == 3) newcells[j][i] = 1;
					if (adjacent > 3) newcells[j][i] = 0;
				}
				if (getCellState(j, i) == 0) {
					if (adjacent == 3) newcells[j][i] = 1;
					else newcells[j][i] = 0;
				}
			}
		}
		cells = newcells;
	}
}
	
	
	
