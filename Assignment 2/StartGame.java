package game;
import java.util.Random;
import java.util.Scanner;
public class StartGame {
	public static void gameWin (int announceWin) { // declare winner
		
		if (announceWin == 1) {
			System.out.println("Congratulations! You won!");
		}
		else if (announceWin == 0) {
			System.out.println("Better luck next time!");
		}
		System.exit(0); // program exits
	}	
	public static int matchStart(int player, int cpu, char[][] board) {	// player 1, player 2
		int i;
		int j;
		int k = 0;
		int checkForWin = 0;
		for (i = 0; i < 3; ++i) { // FIX: Player or CPU losing turns && Choosing a move			
			for (j = 0; j < 3; ++j){
				k++;
			if (k == player) {
				if (board[i][j] != 'O') {
					System.out.println("Illegal move.");
					k = 10;
				break;												
				}
				else if ((k == player) && (board[i][j] != 'O')) {
					board[i][j] = 'X';
					k = 10;
				break;
				}
			}
			if (k == cpu) {
				if (board[i][j] != 'X') {
					System.out.println("Illegal move.");
					k = 10;
				break;												
				}
				else if ((k == cpu) && (board[i][j] != 'X')) {
					board[i][j] = 'O';
					k = 10;
				break;
				}
			}

			}
			
			if (k == 10) {
				break;
			}			
		}
		// fix moves - certain moves are taken by the other player

		System.out.println("\t|\t|\t");
		System.out.println("\t|\t|\t");
		System.out.println("____" + board[0][0] + "_______" + board[0][1] + "_______" + board[0][2] + "_____");
		System.out.println("\t|\t|\t");
		System.out.println("\t|\t|\t");
		System.out.println("____" + board[1][0] + "_______" + board[1][1] + "_______" + board[1][2] + "_____");
		System.out.println("\t|\t|\t");
		System.out.println("\t|\t|\t");
		System.out.println("    " + board[2][0] + "	    " + board[2][1] + "	    " + board[2][2]);
		if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') {
			checkForWin = 1;
			gameWin(checkForWin);
			
		}
		else if (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') {
			checkForWin = 1;
			gameWin(checkForWin);
			
		}
		else if (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') {
			checkForWin = 1;
			gameWin(checkForWin);
			
		}
		else if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') {
			checkForWin = 1;
			gameWin(checkForWin);
			
		}
		else if (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') {
			checkForWin = 1;
			gameWin(checkForWin);
			
		}
		else if (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') {
			checkForWin = 1;
			gameWin(checkForWin);
			
		}
		else if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
			checkForWin = 1;
			gameWin(checkForWin);
			
		}
		else if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
			checkForWin = 1;
			gameWin(checkForWin);
			
		}
		else if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') {
			checkForWin = 0;
			gameWin(checkForWin);
			
		}
		else if (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') {
			checkForWin = 0;
			gameWin(checkForWin);
			
		}
		else if (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') {
			checkForWin = 0;
			gameWin(checkForWin);
			
		}
		else if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') {
			checkForWin = 0;
			gameWin(checkForWin);
			
		}
		else if (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') {
			checkForWin = 0;
			gameWin(checkForWin);
			
		}
		else if (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') {
			checkForWin = 0;
			gameWin(checkForWin);
			
		}
		else if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
			checkForWin = 0;
			gameWin(checkForWin);
			
		}
		else if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
			checkForWin = 0;
			gameWin(checkForWin);			
		}		
		return -1;
	}
	
	public static void displayBoard(int player) {	// display board and introduction to game
		char[] board = new char[9];
		int i;
		if (player == 1) {
			for (i = 0; i < board.length; ++i) {
				board[i] = 'X';
			}
		}
		
		System.out.println("\t|\t|\t");
		System.out.println("\t|\t|\t");
		System.out.println("____" + board[0] + "_______" + board[1] + "_______" + board[2] + "_____");
		System.out.println("\t|\t|\t");
		System.out.println("\t|\t|\t");
		System.out.println("____" + board[3] + "_______" + board[4] + "_______" + board[5] + "_____");
		System.out.println("\t|\t|\t");
		System.out.println("\t|\t|\t");
		System.out.println("    " + board[6] + "	    " + board[7] + "	    " + board[8]);
	}
	public static void main(String[] args) {
	final int ROWS = 3; // Array size for rows
	final int COLUMNS = 3;// Array size for columns
	char[][] board = new char[ROWS][COLUMNS]; // array initialized
	Scanner console = new Scanner(System.in);
	Random randGen = new Random();
	int game;
	int cpu;
	int i;
	int j;
	System.out.println("Welcome to Tic-Tac Toe!");
	System.out.println("Are you ready to start? Press 1 to start, you will play as X.");
	game = console.nextInt(); // user input for game start - if not 1, program exits
	if (game == 1) { 
		displayBoard(game);
		for (i = 1; i < 10; ++i) { 			
			System.out.println("Your move on the board from left to right, 1-3 (top), 4-6 (middle), 7-9 (bottom)");
			game = console.nextInt(); 
			cpu = randGen.nextInt(10) + 1; 			
			if (game == cpu) {
					cpu = randGen.nextInt(10) + 1;
			}
			if (matchStart(game,cpu,board) == -1) {
				i = i - 1;
			}
		}
	}		
	}
}
