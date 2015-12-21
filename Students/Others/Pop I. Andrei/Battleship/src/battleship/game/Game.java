package battleship.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import battleship.game.io.BoardFrame;
import battleship.models.Ship;

public class Game implements ActionListener{
	
	private final BoardConfiguration boardConfiguration;
	private final BoardFrame boardFrame;
	private final String ME = "me";
	private final String COMPUTER = "computer";
	private String activePlayer = ME;
	private String otherPlayer = COMPUTER;
	private boolean endOfGame = false;
	private Timer timer;
	private int timeInterval;
	
	public Game() {
		boardConfiguration = new BoardConfiguration(this);
		boardFrame = boardConfiguration.getTheBoardFrame();
		boardFrame.setGameInfo("Placing the ships");
		timer = new Timer(1000, this);
	}
	
	public void startTheGame() {
		//myBoard.enableMyBoard();
		//myBoard.clearComputerBoard();
		boardFrame.setGameInfo("Starting the game");
		activePlayer = COMPUTER;
		otherPlayer = ME;
		nextRound();
		
	}
	
	public void nextRound() {
		
		checkEndOfGame();
		timeInterval = 2;
		if(activePlayer.equals(COMPUTER))
			boardConfiguration.activateComputer();
		else
			boardConfiguration.enableComputerBoard();
		timer.start();
	
	}
	
	private void checkEndOfGame() {
		if(boardConfiguration.activePlayerDestroyedTheShips(otherPlayer)) {
			JOptionPane.showMessageDialog(null, "You Won");
			System.exit(0);
		}
		
	}
	
	private void switchPlayers() {
		if(activePlayer.equals(COMPUTER)) {
			activePlayer = ME;
			otherPlayer = COMPUTER;
		}
		else {
			activePlayer = COMPUTER;
			otherPlayer = ME;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timeInterval -=1;
		if(timeInterval == 0) {
			boardFrame.setNextPlayer();
			switchPlayers();
			timer.stop();
		}
	}
}
