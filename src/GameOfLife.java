public class GameOfLife {
	//Data
	private int width;
	private int height;
	private int[][] cells;
	//Constructors
	public GameOfLife(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new int[width][height];
	}
	//Prints cells to console
	public void printCells() {
		for (int i = 0; i < height; i++) {
			String str = "||";
			for (int j = 0; j < width; j++) {
				if (cells[j][i] == 0) str+=".";
				if (cells[j][i] == 1) str+="X";
			}
			str+="||";
			System.out.println(str);
			
		}
		System.out.println();
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
		cells[x][y] = 1;
	}
	public void setDead(int x, int y) {
		cells[x][y] = 0;
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
	
	//Main method
	public static void main(String[] args) {
		GameOfLife game = new GameOfLife(3, 5);
		game.setAlive(0, 3);
		game.setAlive(1, 3);
		game.setAlive(2, 3);
		game.printCells();
		game.next();
		game.printCells();
		game.next();
		game.printCells();

		
	}
	
	
	
}
