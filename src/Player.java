import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * @author Bruno Pereria Ribeiro 
 * 2017138
 * First Year Information Technology - Group A
 * College of Computing and Technology - CCTDublin
 * Final Assignment - BattleShip
 * Due date 08/04/2018
 */
public class Player {
	String name;
	String email;
	String age;
	int score = 0;
	int hit = 0;
	int misses = 0;

	public void ageValidation() {
		//ageValidatio() will check the input of the user... validate it.
		//It will be called from the createPlayers() method inside the Game class.
		System.out.println("Enter your age: ");
		age = input();
		//Age can only be represented with numbers, so the user is not allowed to enter any input other than numbers
		//The game also requires that players must be older than 12 and younger than 100.
		while (!age.matches("[0-9]+") || Integer.valueOf(age) < 12 || Integer.valueOf(age) > 100) {
			//Double checking the input type, so the error can be easily indentified 
			while (!age.matches("[0-9]+")) {
				System.out.println("This is not a valid number! [0-9]");
				age = input();
			}
			//Only players between 12 and 100 years old can play the game
			if (Integer.valueOf(age) < 12 || Integer.valueOf(age) > 100) {
				System.out.println("You can't play this game. You must be must be aged over 12 and under 100");
				System.out.println("Please enter the age of the next player.");
				age = input();
			}
		}
	}

	public void nameValidation() {
		//nameValidation() will check the input of the user.
		//It will be called from the createPlayers() method inside the Game class.
		//the integer attempt will be used to display an example when the user failed more than 2 times
		int attempt = 0;
		System.out.println("Enter your full name: ");
		name = input();
		//Names can only contains letters so the .matches method will do
		//and because the user is required to enter a full name, there has to be more than two word with a blank space between them.
		while (!name.contains(" ") || name.matches("[0-9]+")) {
			attempt++;
			System.out.println(
					"This is not a valid full name.\nPlease enter your first name followed by your last name!");
			//If the user try less than 3 times, they be only asked to enter the name again
			if (attempt < 3) {
				name = input();
			}//after the second time, an example of a full name will be printed 
			else {
				System.out.println("Example: Bruno Ribeiro");
				name = input();
			}
		}
	}

	public void emailValidation() {
		//emailValidation() will check the input of the user.
		//It will be called from the createPlayers() method inside the Game class.
		System.out.println("Enter your e-mail: ");
		email = input();
		//atCounter and dotCounter will count how many " @ " and " . " the input has.
		int atCounter = 0;
		int dotCounter = 0;
		//atPosition and dotPosition will be used to show position of the  " @ " and the last " . ".
		int atPosition = 0;		
		int dotPosition = 0;
		//Loop throught the email input to count how many "@" and "." is has, and their position
		for (int i = 0; i <= email.length() - 1; i++) {
			//It the loop find an "@", the variable atCounter will be incremented by one and its position will be saved using atPosition.
			if (email.charAt(i) == '@') {
				atCounter++;
				atPosition = i;
			}
			//It the loop find an ".", the variable atCounter will be incremented by one and its position will be saved using atPosition.
			if (email.charAt(i) == '.') {
				dotCounter++;
				dotPosition = i;
			}
		}
		//A valid email contains only one @, at least one "." and no black space
		//Any input with any of those requirements have to be entered again
		if (atCounter != 1 || dotCounter < 1 || atPosition + 2 > dotPosition  || email.contains(" ")) {
			System.out.println(
					"This is not a valid email.\nYour e-mail must contain only one @ and at least one dot.\nEg. bruno@cct.ie");
			emailValidation();
		}
	}

	private static String input() {
		//input() method is the only method in the whole class that contains a BufferedReader.
		//Therefore is the only method that will allow the user to enter information
		//It can be called from anywhere within the Player class, so it makes easier to request inputs from the users
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = br.readLine();
		} catch (Exception e) {
			System.out.println(e + " " + "Error trying to get input");
		}
		return input;
	}

	//Only getters and setters below this line
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAge() {
		return age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
