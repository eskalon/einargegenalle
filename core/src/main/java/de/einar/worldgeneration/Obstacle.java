package de.einar.worldgeneration;

import com.badlogic.gdx.math.Vector2;

public class Obstacle {

	public static final int REFERENCE = 128;
	protected Vector2 leftBottom;
	protected Vector2 rightUpper;

	public Obstacle(Vector2 leftBottom, Vector2 rightUpper) {
		this.leftBottom = leftBottom;
		this.rightUpper = rightUpper;
	}

	public Vector2 getLeftBottom() {
		return this.leftBottom;
	}

	public Vector2 getRightUpper() {
		return this.rightUpper;
	}

	public int calculateSafeJumpDistance() {
		return Math.round(Obstacle.REFERENCE * 2.6F);
	}
}
