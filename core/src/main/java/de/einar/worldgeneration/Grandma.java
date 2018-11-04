package de.einar.worldgeneration;

import com.badlogic.gdx.math.Vector2;

public class Grandma extends Obstacle {

	public Grandma(Vector2 leftBottom) {
		super(leftBottom, getRightUpper(leftBottom));
	}

	public static Vector2 getRightUpper(Vector2 leftBottom) {
		Vector2 rightUpper = new Vector2();
		rightUpper.x = leftBottom.x + Obstacle.REFERENCE;
		rightUpper.y = leftBottom.y + Obstacle.REFERENCE;
		return rightUpper;
	}

}
