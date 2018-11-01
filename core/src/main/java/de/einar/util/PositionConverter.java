package de.einar.util;

import com.badlogic.gdx.math.Vector2;

/**
 * This class converts the physics units to be usable by the renderer and vice
 * versa.
 */
public class PositionConverter {

	private PositionConverter() {
	}

	public static int toPhysicUnits(int pixels) {
		// TODO
		return pixels;
	}

	public static Vector2 toPhysicUnits(Vector2 pixels) {
		// TODO
		return pixels;
	}

	public static int toPixels(float physicsUnit) {
		// TODO
		return (int) physicsUnit;
	}

	public static Vector2 toPixels(Vector2 physicsUnit) {
		// TODO
		return physicsUnit;
	}

}
