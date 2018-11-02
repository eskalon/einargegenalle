package de.einar.worldgeneration;

import com.badlogic.gdx.math.Vector2;

public class ObstacleBlock {

	public static int number = 0;
	private Obstacle obstacle;
	private float startX;
	private float endX;
	private int freespace = Math.round(Obstacle.REFERENCE * 3.5F);

	public ObstacleBlock(Obstacle obstacle) {
		this.obstacle = obstacle;
		float safeJumpDistance = this.obstacle.calculateSafeJumpDistance();
		this.startX = this.obstacle.getLeftBottom().x - safeJumpDistance;
		this.endX = this.obstacle.getRightUpper().x + safeJumpDistance;
		ObstacleBlock.number++;
	}

	public Obstacle getObstacle() {
		return this.obstacle;
	}

	public float getStart() {
		return this.startX;
	}

	public float getEnd() {
		return this.endX;
	}

	public ObstacleBlock gerenateNextObstacleBlock() {
		float safeDistance = this.obstacle.calculateSafeJumpDistance();
		double random = Math.random();
		double bikeLength = Obstacle.REFERENCE + 40;
		float distance = (float) (bikeLength * 2 + random * this.freespace + safeDistance);
		Vector2 newLeftBottom = new Vector2(this.endX + distance, this.obstacle.leftBottom.y);
		ObstacleBlock next = new ObstacleBlock(new Grandma(newLeftBottom));
		return next;
	}

}
