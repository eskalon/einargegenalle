package de.einar.worldGeneration;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class ObstacleList {

	private final int OBSTACLE_COUNT = 200;
	private ArrayList<ObstacleBlock> obstacleList;

	public ObstacleList() {
		ObstacleBlock first = new ObstacleBlock(new Grandma(new Vector2(0, 0)));
		this.obstacleList.add(first);
		ObstacleBlock iterator = first;
		for (int i = 0; i < OBSTACLE_COUNT; i++) {
			ObstacleBlock next = iterator.gerenateNextObstacleBlock();
			this.obstacleList.add(next);
			iterator = next;
		}
	}

}
