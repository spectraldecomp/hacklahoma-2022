package application;
public class GameOfLife {
	
	//Data
	public int width;
	public int height;
	public int[][] cells;
	private int upperBound = 3;
	private int lowerBound = 2;
	int cellGenerate = 3;
	
	//Constructors
	public GameOfLife(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new int[width][height];
	}
	//Creates copy of cells
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
	
	//LOGIC
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
	
	public int getInformation(){
		int information = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int adjacent = countAdjacent(x, y);
				if (getCellState(x, y) == 1) {
					if (adjacent < lowerBound) information--;
					if (adjacent > upperBound) information++;
				}
			}
		}
		System.out.println(information);
		return information;
	}
	public void adjustBounds(int information) {
		if (information > 0) {
			upperBound+=2;
			lowerBound++;
		}

		if (information < 0) {
			lowerBound-=2;
			upperBound--;
		}
		System.out.println("Lower: " + lowerBound);
		System.out.println("Upper: " + upperBound);
		System.out.println("Generate: " + cellGenerate);
	}
	public void infoNext() {
		int[][] newcells = new int[width][height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int adjacent = countAdjacent(j, i);
				if (getCellState(j, i) == 1) {
					if (adjacent < lowerBound) newcells[j][i] = 0;
					if (adjacent >= lowerBound && adjacent <= upperBound) newcells[j][i] = 1;
					if (adjacent > upperBound) newcells[j][i] = 0;
				}
				if (getCellState(j, i) == 0) {
					if (adjacent == cellGenerate) newcells[j][i] = 1;
					else newcells[j][i] = 0;
				}
			}
		}
		cells = newcells;
	}
}
	
	
	
