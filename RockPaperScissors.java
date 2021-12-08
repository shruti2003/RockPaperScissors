import java.util.Scanner;

/**
 * CS312 Assignment 4.
 *
 * On my honor, Shruti Patel, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Rock Paper Scissors
 *
 * email address: shruti.patel@utexas.edu
 *  UTEID: sp44725
 *  Section 5 digit ID: 52550
 *  Grader name: Nathan
 *  Number of slip days used on this assignment: 0
 */

public class RockPaperScissors {

	/*
	 * A program to allow a human player to play rock - paper - scissors against the
	 * computer. If args.length != 0 then we assume the first element of args can be
	 * converted to an int
	 */
	public static final int ROCK_NUM = 1;
	public static final int PAPER_NUM = 2;
	public static final int SCISSORS_NUM = 3;

	public static void main(String[] args) {
		// CS312 Students. Do not change the following line.
		RandomPlayer computerPlayer = buildRandomPlayer(args);

		// CS312 students do no change the following line.
		// Do not create any other Scanners.
		Scanner keyboard = new Scanner(System.in);

		String name = getName(keyboard);
		int rounds = getRounds(keyboard);
		int playerWin = 0;
		int compWin = 0;
		int draw = 0;

		// for loop that calls methods for each round
		for (int roundNum = 1; roundNum <= rounds; roundNum++) {
			int choice = computerPlayer.getComputerChoice();
			String compChoice = numToChoice(choice);
			String playerChoice = getUserInput(keyboard, compChoice, roundNum, name, rounds, playerWin, compWin, draw);

			draw = draw(draw, compChoice, playerChoice);
			compWin = compWin(compWin, compChoice, playerChoice);
			playerWin = playerWin(playerWin, compChoice, playerChoice);

		}
		// calculates the total score of ALL the rounds
		totalScore(rounds, draw, playerWin, compWin, name);
		keyboard.close();
	}

	// returns the users name
	public static String getName(Scanner keyboard) {
		System.out.print("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.\n"
				+ "Please type in your name and press return: ");
		String name = keyboard.nextLine();
		System.out.println();
		System.out.println("Welcome " + name + ".");
		System.out.println();
		System.out.println("All right " + name + ". How many rounds would you like to play?");

		return name;
	}

	// returns the number of rounds the user wants to play
	public static int getRounds(Scanner keyboard) {
		System.out.print("Enter the number of rounds you want to play and press return: ");
		int numRounds = keyboard.nextInt();
		System.out.println();

		return numRounds;
	}

	// gets the users input on their choice of rock, paper, or scissors
	public static String getUserInput(Scanner keyboard, String compChoice, int roundNum, String name, int totalPlayed,
			int playerWin, int compWin, int draw) {
		System.out.println("Round " + roundNum + ".");
		System.out.println(name + ", please enter your choice for this round.");
		System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
		int choiceNum = keyboard.nextInt();

		String playerChoice = numToChoice(choiceNum);

		System.out.println("Computer picked " + compChoice + ", " + name + " picked " + playerChoice + ".");
		System.out.println();

		return playerChoice;
	}

	// takes the integer choice value and converts them into strings
	public static String numToChoice(int choiceNum) {
		String choice = "";
		if (choiceNum == ROCK_NUM) {
			choice = "ROCK";
		} else if (choiceNum == PAPER_NUM) {
			choice = "PAPER";

		} else if (choiceNum == SCISSORS_NUM) {
			choice = "SCISSORS";
		}
		return choice;
	}

	// calculates the number of times the player and comp tied and keeps track for each round
	public static int draw(int drawCount, String compChoice, String playerChoice) {
		if (compChoice.equals(playerChoice)) {
			System.out.println("We picked the same thing! This round is a draw.");
			System.out.println();
			drawCount++;
		}
		return drawCount;
	}

	// calculates the number of times the player has won and keeps track for each round
	public static int playerWin(int playerCount, String compChoice, String playerChoice) {
		if (compChoice == "ROCK" && playerChoice == "PAPER") {
			System.out.println("PAPER covers ROCK. You win.");
			System.out.println();
			playerCount++;
		} else if (compChoice == "PAPER" && playerChoice == "SCISSORS") {
			System.out.println("SCISSORS cut PAPER. You win.");
			System.out.println();
			playerCount++;
		} else if (compChoice == "SCISSORS" && playerChoice == "ROCK") {
			System.out.println("ROCK breaks SCISSORS. You win.");
			System.out.println();
			playerCount++;
		}
		return playerCount;
	}

	// calculates the number of times the computer has won and keeps track for each round
	public static int compWin(int compCount, String compChoice, String playerChoice) {
		if (compChoice == "ROCK" && playerChoice == "SCISSORS") {
			System.out.println("ROCK breaks SCISSORS. I win.");
			System.out.println();
			compCount++;
		} else if (compChoice == "PAPER" && playerChoice == "ROCK") {
			System.out.println("PAPER covers ROCK. I win.");
			System.out.println();
			compCount++;
		} else if (compChoice == "SCISSORS" && playerChoice == "PAPER") {
			System.out.println("SCISSORS cut PAPER. I win.");
			System.out.println();
			compCount++;
		}
		return compCount;
	}

	// calculates the total score of the rounds
	public static void totalScore(int totalRounds, int draw, int playerWin, int compWin, String name) {
		System.out.println();

		System.out.println("Number of games of ROCK PAPER SCISSORS: " + totalRounds);
		System.out.println("Number of times Computer won: " + compWin);
		System.out.println("Number of times " + name + " won: " + playerWin);
		System.out.println("Number of draws: " + draw);

		if (playerWin > compWin) {
			System.out.print("You, " + name + ", are a master at ROCK, PAPER, SCISSORS.");
		} else if (playerWin == compWin) {
			System.out.print("We are evenly matched.");
		} else if (compWin > playerWin) {
			System.out.print("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
		}
	}

	/*
	 * Build the random player. If args is length 0 then the default RandomPlayer is
	 * built that follows a predictable sequence. If args.length > 0 then we assume
	 * we can convert the first element to an int and build the RandomPlayer with
	 * that initial value.
	 */
	public static RandomPlayer buildRandomPlayer(String[] args) {
		if (args.length == 0) {
			return new RandomPlayer();
		} else {
			int seed = Integer.parseInt(args[0]);
			return new RandomPlayer(seed);
		}

	}
}