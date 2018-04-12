/**
 * @author Bruno Pereria Ribeiro 2017138 First Year Information Technology -
 *         Group A College of Computing and Technology - CCTDublin Final
 *         Assignment - BattleShip Due date 08/04/2018
 */
public class GameBoard {
	int numberOfRows = 22;
	int numberOfColumns = 22;
	// The string topLine will be printed above the grid showing the number of the
	// columns and implementing the design of the grid.
	String topLine = "  ";
	// The invisibleGrid is where the battleships are added, it will be only used to
	// check if the chosen row and column has ship.
	// It has a self-explanatory name, the user will not be able to see it, even if
	// the game is over
	String[][] invisibleGrid = new String[this.getNumberOfRows()][this.getNumberOfColumns()];
	// The visibleGrid will be upgraded throughout the game, "H" or "M" will be
	// added depeding on the users hits or misses.
	// This is the grid that the users will be able to see, after every turn it will
	// be upgraded.
	String[][] visibleGrid = new String[this.getNumberOfRows()][this.getNumberOfColumns()];
	String selectedRow;
	String selectedCol;
	// Hits lefts will be implemented for each of the squares containing a ship and
	// decremented for each hit.
	int hitsLeft = 0;

	public void buildGrid() {
		// Create two visual grids, one that will be shown to users and other two use as
		// a reference
		// Determine the quantity and lenght of the battleships.
		// Place them on squares randomly chosen making sure that any ship won't be
		// placed out of the grid
		// Print the visible grid before the game starts.
		// From 0 to the number of rows chosen by the user, r == rows.
		for (int r = 0; r < numberOfRows; r++) {
			// From 0 to to the number of columns chosen by the user, c == columns.
			for (int c = 0; c < numberOfColumns; c++) {
				// Both for functions are going to build up the grid using 2D arrays.
				visibleGrid[r][c] = "|___";
				invisibleGrid[r][c] = "|MMM";
			}
		}
		// This for loop is going to add the column number on the top of the grid.
		for (int c = 1; c <= numberOfColumns; c++) {
			// If and else to make the top line tidier, it will handle one and two digits
			// numbers.
			if (c < 10) {
				topLine += ".." + c + ".";
			} else {
				topLine += "." + c + ".";
			}
		}
		// Number of ships will be half of the number of columns on the grid. eg number
		// of colums: 10 == 5 battleships
		int numberOfShips = numberOfColumns / 2;
		// Number of ships will be one thid of the number of columns on the grid. eg
		// number of colums: 10 == battleships placed over 3 squares (rounding to the
		// nearest Integer)
		float lenghtOfShips = (Math.round(numberOfColumns / 3));
		// Loop to place each battleships.
		for (int i = 1; i <= numberOfShips; i++) {
			// Randomly choosing the column to place the first square used by the
			// battleship.
			int shipColumn = (int) (Math.random() * (numberOfColumns - lenghtOfShips));
			// Randomly choosing the row to place the first square used by the battleship.
			int shipRow = (int) (Math.random() * (numberOfRows - lenghtOfShips));
			// Randomly choosing if the ship will be built will be placed horizontally or
			// vertically
			int direction = (int) (Math.random() * 100);
			// If direction is less or equal to 50, the ships will be placed horizontally
			if (direction <= 50) {
				// This first loop prevents that the new ship will be place over another ship.
				for (int x = 0; x <= lenghtOfShips; x++) {
					while (invisibleGrid[shipRow + x][shipColumn].contains("H")) {
						shipColumn = (int) (Math.random() * (numberOfColumns - lenghtOfShips));
						shipRow = (int) (Math.random() * (numberOfRows - lenghtOfShips));
					}
				}
				// After checking the availability of the squares, the new ship will be built
				for (int x = 0; x < lenghtOfShips; x++) {
					invisibleGrid[shipRow + x][shipColumn] = "|_H_";
					hitsLeft++;
					// System.out.println(shipRow + x + " " +shipColumn);
				}
			}
			// The random number(direction) is greater than 50.
			// The ship will be placed vertically.
			else {
				// This first loop prevents that the new ship will be place over another ship.
				for (int x = 0; x <= lenghtOfShips; x++) {
					while (invisibleGrid[shipRow][shipColumn + x].contains("H")) {
						shipColumn = (int) (Math.random() * (numberOfColumns - lenghtOfShips));
						shipRow = (int) (Math.random() * (numberOfRows - lenghtOfShips));
					}
				}
				// After checking the availability of the squares, the new ship will be built
				for (int x = 0; x < lenghtOfShips; x++) {
					invisibleGrid[shipRow][shipColumn + x] = "|_H_";
					hitsLeft++;
					// System.out.println(shipRow + " " +shipColumn + x);
				}
			}
		}
		// Print a visual grid to the user.
		System.out.println(topLine);
		for (int r = 1; r <= numberOfRows; r++) {
			// If and else to handle numbers of one or two digits.
			if (r < 10) {
				// Numbers from 0 to 9 have only one digit, so it will be added an extra space
				// to compensate for it
				System.out.print(" " + r);
			} else {
				System.out.print(r);
			}
			for (int c = 0; c < numberOfColumns; c++) {
				System.out.print(visibleGrid[r - 1][c] + "");
			}
			System.out.print("|");
			System.out.print("\n");
		}
	}

	public boolean checkGrid() {
		/*
		 * checkGrid will return a boolean variable named hit checkGrid will be called
		 * from the game.java class after the player choose row and number column If the
		 * row and column chosen contains a "H", it will a hit and the boolean hit will
		 * be set as true.
		 */
		boolean hit = false;
		// Checking whether the square chosen has a H. It is row - 1 and column - 1,
		// because users will enter from number 1 and the grid starts at 0.
		if (invisibleGrid[Integer.valueOf(selectedRow) - 1][Integer.valueOf(selectedCol) - 1].contains("H")) {
			System.out.println("This is a hit!");
			hit = true;
			hitsLeft--;
			// If so, the visibleGrid will be incremented with a " H ". Giving the user a
			// visual of the place hit
			visibleGrid[Integer.valueOf(selectedRow) - 1][Integer.valueOf(selectedCol) - 1] = "|_H_";
		} else {
			// If not, "MMM" will be added to the grid. |MMM is visually easy to
			// diferentiate from
			// |_H_ than just |_M_
			System.out.println("This is not a hit!");
			visibleGrid[Integer.valueOf(selectedRow) - 1][Integer.valueOf(selectedCol) - 1] = "|MMM";
		}
		// returning the hit, as a boolean it can only be true or false. NOTHING ELSE.
		return hit;
	}

	public void upgradeGrid() {
		// upgradeGrid will upgrade the grid after the end of each turn.
		// It will be called from the Game class.
		// Basically, it will print a new grid, showing the hit or miss of the last
		// turn.
		System.out.println(topLine);
		for (int r = 1; r <= numberOfRows; r++) {
			if (r < 10) {
				System.out.print(" " + r);
			} else {
				System.out.print(r);
			}
			for (int c = 0; c < numberOfColumns; c++) {
				System.out.print(visibleGrid[r - 1][c] + "");
			}
			System.out.print("|");
			System.out.print("\n");
		}
	}
	// Only getters and setters below this line

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public String getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String getSelectedCol() {
		return selectedCol;
	}

	public void setSelectedCol(String selectedCol) {
		this.selectedCol = selectedCol;
	}

}
