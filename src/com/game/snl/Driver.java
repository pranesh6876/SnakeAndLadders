package com.game.snl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.game.snl.models.Ladder;
import com.game.snl.models.Player;
import com.game.snl.models.Snake;

public class Driver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int noOfSnakes = sc.nextInt();
		List<Snake> snakes = new ArrayList<Snake>();
		
		for(int i=0; i<noOfSnakes; ++i)
		{
			snakes.add(new Snake(sc.nextInt(),sc.nextInt()));
		}
		
		int noOfLadders = sc.nextInt();
		List<Ladder> ladders = new ArrayList<Ladder>();
		
		for(int i=0; i<noOfLadders; ++i)
		{
			ladders.add(new Ladder(sc.nextInt(),sc.nextInt()));
		}
		
		int noOfPlayers = sc.nextInt();
		List<Player> players = new ArrayList<Player>();

		for(int i=0 ; i<noOfPlayers; i++)
		{
			players.add(new Player(sc.next()));
		}
		
		SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
		snakeAndLadderService.setPlayers(players);
		snakeAndLadderService.setLadders(ladders);
		snakeAndLadderService.setSnakes(snakes);
		
		snakeAndLadderService.startGame();
	}

}
