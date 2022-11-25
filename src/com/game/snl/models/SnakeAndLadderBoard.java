package com.game.snl.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderBoard {

	private int size;
	private List<Snake> snakes;
	private List<Ladder> ladders;
	private Map<String, Integer> playerPieces;
	
	public SnakeAndLadderBoard(int size) {
		this.size = size;
		this.snakes = new ArrayList<Snake>();
		this.ladders = new ArrayList<Ladder>();
		this.playerPieces = new HashMap<>();
	}

	public int getSize() {
		return size;
	}

	public List<Snake> getSnakes() {
		return snakes;
	}

	public List<Ladder> getLadders() {
		return ladders;
	}

	public Map<String, Integer> getPlayerPieces() {
		return playerPieces;
	}

	public void setSnakes(List<Snake> snakes) {
		this.snakes = snakes;
	}

	public void setLadders(List<Ladder> ladders) {
		this.ladders = ladders;
	}

	public void setPlayerPieces(Map<String, Integer> playerPieces) {
		this.playerPieces = playerPieces;
	}
	
	
	
	
	
}
