import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Bruno Pereria Ribeiro 2017138 First Year Information Technology -
 *         Group A College of Computing and Technology - CCTDublin Final
 *         Assignment - BattleShip Due date 08/04/2018
 */
public class Game {
	// Game class is the main class of the battleship
	// Its functions are to get information from the user and build up a grid.
	// Create players and set their name, age and email (within Player class)
	// Manage the game, rules and scores for each player.
	static GameBoard board = new GameBoard();
	// Four players create within the Player class, but some of them may not be used
	// in the game
	Player playerOne = new Player();
	Player playerTwo = new Player();
	Player playerThree = new Player();
	Player playerFour = new Player();
	// Global variables so they can be used everywhere within the program.
	String playersQtd;
	int turnCounter = 1;

	public void gameInformation() {
		playersQtd = numberOfPlayers();
		System.out.print("Enter the number of rows! (10-20)");
		// gridSize() method returns a String, so it has to be parsed to an int.
		int nRows = Integer.parseInt(gridSize());
		System.out.print("Enter the number of columns! (10-20)");
		// gridSize() method returns a String, so it has to be parsed to an int.
		int nColumns = Integer.parseInt(gridSize());
		// With nRows and nColumns have been typed by the user.
		// They can be sent to the buildGrid() method inside the GameBoard class.
		board.setNumberOfRows(nRows);
		board.setNumberOfColumns(nColumns);
		// And them call the buildGrid() itself.
		board.buildGrid();
		// Calling the createPlayers with the playersQtd, which will set the numbers of
		// players playing the game.
		createPlayers(playersQtd);
		// callint the currentTurn, which will start the battleship game.
		currentTurn();
	}

	/**
	 * @param playerQtd
	 *            the numbers of players playing the game.
	 */
	private void createPlayers(String playerQtd) {
		// createPlayers() will set information about each player.
		// It will only set information for the players that are actually playing the
		// game, even though they already exist as objects
		int playersQtd = Integer.valueOf(playerQtd);
		// For each player, there is one object create within the Player.java class
		// The game requires at least one player, so there will be ALWAYS one player.
		System.out.println("Player One: ");
		playerOne.ageValidation();
		playerOne.nameValidation();
		playerOne.emailValidation();
		// If there are two players, player number two has also to be set.
		if (playersQtd >= 2) {
			System.out.println("Player Two: ");
			playerTwo.ageValidation();
			playerTwo.nameValidation();
			playerTwo.emailValidation();
		}
		// If there are three players, player number three has also to be set.
		if (playersQtd >= 3) {
			System.out.println("Player Three: ");
			playerThree.ageValidation();
			playerThree.nameValidation();
			playerThree.emailValidation();
		}
		// If there are four players, player number four has also to be set.
		if (playersQtd == 4) {
			System.out.println("Player Four: ");
			playerFour.ageValidation();
			playerFour.nameValidation();
			playerFour.emailValidation();
		}
	}

	public void currentTurn() {
		// currentTurn() will run the game throught ever turn.
		// It will also manage the row and column that the user choose (with the help of
		// other methods, such as selectRow and selectCol.
		// and also manage scores and winners.
		String sRow; // In that case, "selected" will be shorten to "s"
		String sCol;
		int player = Integer.valueOf(playersQtd);
		// usedSquares is a coordinate keeper, to store all the squares (row x column)
		// that have been used
		// it will be used to avoid repeted squares.
		String usedSquares = "";
		while (board.hitsLeft > 0) {
			System.out.println("Turn number " + turnCounter + ".");
			// The game will stop when there are no hits left.
			System.out.println("Left: " + board.hitsLeft);
			// Player one turn
			if (player >= 1) {
				System.out.println("It's " + playerOne.getName() + "'s turn.");
				sRow = selectRow();
				sCol = selectCol();
				// The following while will check if the square chosen has already been picked
				// The "|" will work as a breakpoint for coordinates, it avoids "1010" be the
				// same as "10 1"
				// So the .contains method will know that 101| is different from 1010|
				while (usedSquares.contains(sRow + sCol + "|")) {
					System.out.println("This grid has already been chosen!\nTry again!");
					sRow = selectRow();
					sCol = selectCol();
				}
				usedSquares += sRow + sCol + "| ";
				board.setSelectedCol(sCol);
				board.setSelectedRow(sRow);
				// checkGrid is a method within the GameBoard class
				// It check if the square picked has a ship and returns a boolean called hit.
				// If the boolean is true is a hit, so hit for the respective player will be
				// incremented
				if (board.checkGrid()) {
					playerOne.hit++;
				}
				// otherwise, misses will be incremented
				else {
					playerOne.misses++;
				}
				// The score is calculated as | Score = hit - (miss * 2)
				playerOne.setScore(playerOne.hit - (playerOne.misses * 2));
				// When the player's turn is finished, the grid will be upgraded with M or H in
				// the square chosen
				board.upgradeGrid();
			}
			// The same will happen with playerTwo, playerThree and playerFour. (Of course,
			// if they are playing)
			if (player >= 2) {
				System.out.println("It's " + playerTwo.getName() + "'s turn.");
				sRow = selectRow();
				sCol = selectCol();
				// The following while will check if the square chosen has already been picked
				// The "|" will work as a breakpoint for coordinates, it avoids "1010" be the
				// same as "10 1"
				// So the .contains method will know that 101| is different from 1010|
				while (usedSquares.contains(sRow + sCol + "|")) {
					System.out.println("This grid has already been chosen!\nTry again!");
					sRow = selectRow();
					sCol = selectCol();
				}
				usedSquares += sRow + sCol + "| ";
				board.setSelectedCol(sCol);
				board.setSelectedRow(sRow);
				// checkGrid is a method within the GameBoard class
				// It check if the square picked has a ship and returns a boolean called hit.
				// If the boolean is true is a hit, so hit for the respective player will be
				// incremented
				if (board.checkGrid()) {
					playerTwo.hit++;
				} // otherwise, misses will be incremented
				else {
					playerTwo.misses++;
				}
				// The score is calculated as | Score = hit - (miss * 2)
				playerTwo.setScore(playerTwo.hit - (playerTwo.misses * 2));
				// When the player's turn is finished, the grid will be upgraded with M or H in
				// the square chosen
				board.upgradeGrid();
			}
			if (player >= 3) {
				System.out.println("It's " + playerThree.getName() + "'s turn.");
				sRow = selectRow();
				sCol = selectCol();
				// The following while will check if the square chosen has already been picked
				// The "|" will work as a breakpoint for coordinates, it avoids "1010" be the
				// same as "10 1"
				// So the .contains method will know that 101| is different from 1010|
				while (usedSquares.contains(sRow + sCol + "|")) {
					System.out.println("This grid has already been chosen!\nTry again!");
					sRow = selectRow();
					sCol = selectCol();
				}
				usedSquares += sRow + sCol + "| ";
				board.setSelectedCol(sCol);
				board.setSelectedRow(sRow);
				// checkGrid is a method within the GameBoard class
				// It check if the square picked has a ship and returns a boolean called hit.
				// If the boolean is true is a hit, so hit for the respective player will be
				// incremented
				if (board.checkGrid()) {
					playerThree.hit++;
				} // otherwise, misses will be incremented
				else {
					playerThree.misses++;
				}
				// The score is calculated as | Score = hit - (miss * 2)
				playerThree.setScore(playerThree.hit - (playerThree.misses * 2));
				// When the player's turn is finished, the grid will be upgraded with M or H in
				// the square chosen
				board.upgradeGrid();
			}
			if (player >= 4) {
				System.out.println("It's " + playerFour.getName() + "'s turn.");
				sRow = selectRow();
				sCol = selectCol();
				// The following while will check if the square chosen has already been picked
				// The "|" will work as a breakpoint for coordinates, it avoids "1010" be the
				// same as "10 1"
				// So the .contains method will know that 101| is different from 1010|
				while (usedSquares.contains(sRow + sCol + "|")) {
					System.out.println("This grid has already been chosen!\nTry again!");
					sRow = selectRow();
					sCol = selectCol();
				}
				usedSquares += sRow + sCol + "| ";
				board.setSelectedCol(sCol);
				board.setSelectedRow(sRow);
				// checkGrid is a method within the GameBoard class
				// It check if the square picked has a ship and returns a boolean called hit.
				// If the boolean is true is a hit, so hit for the respective player will be
				// incremented
				if (board.checkGrid()) {
					playerFour.hit++;
				} else {
					playerFour.misses++;
				}
				// The score is calculated as | Score = hit - (miss * 2)
				playerFour.setScore(playerFour.hit - (playerFour.misses * 2));
				// When the player's turn is finished, the grid will be upgraded with M or H in
				// the square chosen
				board.upgradeGrid();
			}
			turnCounter++;
		}
		// If there is only one player, just their score has to be printed
		if (player == 1)
			System.out.println(playerOne.getName() + " scored " + playerOne.getScore() + " points.");
		// If there are more than 1 player, the winner's name will be printed
		else {
			// s1, s2, s3 and s4 are the scores of the players.
			int s1 = playerOne.getScore();
			int s2 = playerTwo.getScore();
			int s3 = playerThree.getScore();
			int s4 = playerFour.getScore();
			// The next if's statements will determinate who the winner is.
			// This is pretty primitive way to sort number, but it works. If there were more
			// than 5 players, it would be inviable.
			if (s1 > s2) {
				if (s1 > s3) {
					if (s1 > s4) {
						// In that case, the playerOne's score is greater than the other's
						System.out.println(playerOne.getName() + " won the game!");
					} else {
						// In that case, the playerFour's score is greater than the other's
						System.out.println(playerFour.getName() + " won the game!");
					}
				} else {
					if (s3 > s4) {
						// In that case, the playerThree's score is greater than the other's
						System.out.println(playerThree.getName() + " won the game!");
					} else {
						// In that case, the playerFour's score is greater than the other's
						System.out.println(playerFour.getName() + " won the game!");
					}
				}
			} else {
				if (s2 > s3) {
					if (s2 > s4) {
						// In that case, the playerTwo's score is greater than the other's
						System.out.println(playerTwo.getName() + " won the game!");
					}
				} else {
					if (s3 > s4) {
						// In that case, the playerThree's score is greater than the other's
						System.out.println(playerThree.getName() + " won the game!");
					} else {
						// In that case, the playerFour's score is greater than the other's
						System.out.println(playerFour.getName() + " won the game!");
					}
				}
			}
		}
	}

	public static String selectRow() {
		// This method will request which row of the grid the user wants to select
		// and return its value as a String of name "row".
		String row;
		System.out.println("Enter the row number:");
		row = input();
		// While the input entered by the user is not a number
		// Or the row selected is greater than the grid (it would be a null section of
		// the array.)
		while (!row.matches("[0-9]+") || Integer.valueOf(row) < 1
				|| Integer.valueOf(row) > Integer.valueOf(board.getNumberOfRows())) {
			System.out.println("Please enter a number between 1 and " + board.getNumberOfRows());
			row = input();
		}
		return row;
	}

	public static String selectCol() {
		// This method will request which row of the grid the user wants to select
		// and return its value as a String of name "col".
		String col;
		System.out.println("Enter the column number:");
		col = input();
		// While the input entered by the user is not a number
		// Or the column selected is greater than the grid (it would be a null section
		// of the array.)
		while (!col.matches("[0-9]+") || Integer.valueOf(col) < 1
				|| Integer.valueOf(col) > Integer.valueOf(board.getNumberOfColumns())) {
			System.out.println("Please enter a number between 1 and " + board.getNumberOfColumns());
			col = input();
		}
		return col;
	}

	private String gridSize() {
		// gridSize() will work as a validation of the input that will determinate the
		// height and the width of the grid
		// and it will return to the gameInformation() method.
		String gridSize = null;
		gridSize = input();
		// While the gridSize is not a number, the user will be requested to enter a
		// input again
		while (!gridSize.matches("[0-9]+")) {
			System.out.println("This is not a valid number.\n" + "Please, type only numbers.");
			gridSize = input();
		}
		// The user can only choose a grid of lenght and width between 10 and 20.
		while (Integer.parseInt(gridSize) < 10 || Integer.parseInt(gridSize) > 20) {
			System.out.println("The number of rows has to be more than 10 and less than 20.");
			System.out.print("Reenter the number desired. [10-20]");
			gridSize = input();
		}
		return gridSize;
	}

	private String numberOfPlayers() {
		// numberOfPlayer will work as a validation of the number of players.
		String input = null;
		// Requesting the user to enter a valid number(0-9) between 2 and 4.
		// 2, 3 or 4 are the only numbers of players allowed.
		System.out.print("Enter the number of players. [2-4]");
		input = input();
		// Validating if the user entered a number instead of a letter or a blank space
		while (!input.matches("[0-9]+")) {
			System.out.println("This is not a valid number.\n" + "Please, type only numbers.");
			input = input();
		}
		// If not, a message will be printed and the users will be required to enter the
		// number again
		// While the input is less than one OR greater than 4.
		while (Integer.valueOf(input) < 1 || Integer.valueOf(input) > 4) {
			System.out.println(
					"This is not a valid number.\n" + "The game requires at least one and no more than four players!");
			input = input();
		}
		System.out.println(input);
		return input;
	}

	private static String input() {
		// input() method is the only method in the whole class that contains a
		// BufferedReader.
		// Therefore is the only method that will allow the user to enter information
		// It can be called from anywhere within the main class, so it makes easier to
		// request inputs from the users
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = br.readLine();
		} catch (Exception e) {
			System.out.println(e + " " + "Error trying to get input");
		}
		return input;
	}

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		gameInformation();
	}

}
