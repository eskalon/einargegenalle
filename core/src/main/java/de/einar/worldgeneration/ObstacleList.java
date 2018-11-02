package de.einar.worldgeneration;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class ObstacleList {

	private List<ObstacleBlock> obstacleList = new ArrayList<>();

	public ObstacleList(int count) {
		ObstacleBlock first = new ObstacleBlock(new Grandma(new Vector2(0, 0)));
		this.obstacleList.add(first);
		ObstacleBlock iterator = first;
		for (int i = 0; i < count; i++) {
			ObstacleBlock next = iterator.gerenateNextObstacleBlock();
			this.obstacleList.add(next);
			iterator = next;
		}
	}

	public List<ObstacleBlock> getObstacleList() {
		return obstacleList;
	}

}
