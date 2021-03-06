package Battleship;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

/**
 * The GameMasterclass handles the game loop. 
 * It initiates the game, starting the back and fort 
 * between the player and the computer. First the player 
 * places its ships, then the computer  randomly  places 
 *  his.  Finally,  the  player  and  computer  take  
 *  turns  attacking  each otheruntilall the ships of one 
 *  entity have been hit and the game won
 * @author Roger
 *
 */

public class GameMaster {

	private boolean gameOver = false;
	private Board board = new Board();
	private Scanner scan = new Scanner(System.in);
	private Player player = new Player();
	private Computer computer = new Computer();
	public GameMaster() {}
	
	@Ignore
	public static void wait(int seconds)
	{
		int ms = seconds*1000;
	    try
	    {
	        Thread.sleep(ms);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	}
	public void ScannerLineTester(InputStream in)
	{
		scan = new Scanner(in);
	}
	public void printMainMenu() {
		
		String input = "";
		while(!input.equals("0"))
		{
			System.out.println("\t\t\t\tBattleship Game!\n");
			System.out.println("\t\tGame options:\n");
			System.out.println("\t\t\t1: Play\n");
			System.out.println("\t\t\t0: Exit\n");
			
			System.out.println("\tEnter an option: ");
			input = scan.nextLine();
			
			switch(input)
			{
			case "1":
				this.Play();
				break;
			case "0":
				System.out.println("\tExiting the game...\n");
				break;
			default:
				System.out.println("\tError!: Enter a valid option\n");
				break;
			}
		}
		scan.close();
	}
	@Test
	public void Play() {
		
		player.iniciateShipsLocation();
		player.setShipsLocation(board);
		computer.updateCompShips(board);
		board.drawCompShips();
		computer.populateMapList();
		while (!this.gameOver)
		{
			board.drawBoards();
			if(!computer.hasWon() && !gameOver)
			{
				player.attackShip(board);
			}else if(!gameOver){
				gameOver = true;
				board.drawBoards();
				wait(2);
				System.out.println("\nGame Over: Computer won!\n\n\n");
				wait(3);
			}
			if(!player.hasWon() && !gameOver)
			{
				wait(2);
				computer.attackShip(board);
				wait(2);
			}else if(!gameOver){
				gameOver = true;
				board.drawBoards();
				wait(2);
				System.out.println("\nGame Over: Player won!\n\n\n");
				wait(3);
			}
		}
		player = new Player();
		computer = new Computer();
		board = new Board();
		gameOver = false;
	}
}
