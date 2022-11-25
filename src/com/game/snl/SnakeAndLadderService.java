package com.game.snl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.game.snl.models.Ladder;
import com.game.snl.models.Player;
import com.game.snl.models.Snake;
import com.game.snl.models.SnakeAndLadderBoard;

public class SnakeAndLadderService {

	private SnakeAndLadderBoard snakeAndLadderBoard;
	
	private int numberOfPlayers;
	
	private Queue<Player> players;
	
	private boolean isGameCompleted;
	
	private int noOfDices;
	
	private boolean shouldGameContinueTillLastPlayer;
	
	private boolean shouldAllowMultipleDiceRollOnSix;
	
	private static final int DEFAULT_BOARD_SIZE = 100;
	private static final int DEFAULT_NO_OF_DICES = 1;
	
	public SnakeAndLadderService(int boardsize) {
		this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardsize);
//		this.players = new LinkedList<>();
		this.noOfDices = SnakeAndLadderService.DEFAULT_NO_OF_DICES;
	}
	
	public SnakeAndLadderService() {
		this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
	}
	
	/**
     * ====Setters for making the game more extensible====
     */

    public void setNoOfDices(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
        this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
    }
    
    /**
     * ==================Initialize board==================
     */

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<Player>();
        this.numberOfPlayers = players.size();
        Map<String, Integer> playerPieces = new HashMap<String, Integer>();
        for (Player player : players) {
            this.players.add(player);
            playerPieces.put(player.getId(), 0); //Each player has a piece which is initially kept outside the board (i.e., at position 0).
        }
        snakeAndLadderBoard.setPlayerPieces(playerPieces); //  Add pieces to board
    }
    
    public void setSnakes(List<Snake> snakes) {
    	snakeAndLadderBoard.setSnakes(snakes);
    }
    
    public void setLadders(List<Ladder> ladders) {
    	snakeAndLadderBoard.setLadders(ladders);
    }
    
    /**
     * ==========Core business logic for the game==========
     */
    
    private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPos) {
    	int prevPos;
    	
    	do {
    		prevPos = newPos;
    		for(Snake snake : snakeAndLadderBoard.getSnakes())
    		{
    			if(snake.getStart() == newPos) {
    				newPos = snake.getEnd();
    			}
    		}
    		for(Ladder ladder : snakeAndLadderBoard.getLadders())
    		{
    			if(ladder.getStart() == newPos)
    				newPos = ladder.getEnd();
    		}
    	}
    	while(newPos != prevPos);
    	
    	return newPos;
    }
	
	private void movePlayer(Player player, int positions) {
		int oldPos = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
		int newPos = oldPos + positions;
		
		int boardSize = snakeAndLadderBoard.getSize();
		
		if(newPos>boardSize) {
			newPos = oldPos;
		}
		else
		{
			newPos = getNewPositionAfterGoingThroughSnakesAndLadders(newPos);
		}
		
		snakeAndLadderBoard.getPlayerPieces().put(player.getId(), newPos);
		System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPos +" to " + newPos);
	}
	
	private int getTotalValueAfterDiceRolls() {
		return DiceService.roll();
	}
	
	private boolean hasPlayerWon(Player player)
	{
		return snakeAndLadderBoard.getSize() == snakeAndLadderBoard.getPlayerPieces().get(player.getId());
	}
	
	private boolean isGameCompleted() {
		int currentNumberOfPlayers = players.size();
		return currentNumberOfPlayers < numberOfPlayers;
	}
	
	public void startGame() {
		while(!isGameCompleted()) {
			int totalDiceValue = getTotalValueAfterDiceRolls();
			Player currentPlayer = players.poll();
		
			movePlayer(currentPlayer, totalDiceValue);
			
			if(hasPlayerWon(currentPlayer))
			{
				System.out.println(currentPlayer.getName()+" wins the game!!");
				snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer);
			}
			else
			{
				players.add(currentPlayer);
			}
		}
	}
	
	/**
     * =======================================================
     */
}
