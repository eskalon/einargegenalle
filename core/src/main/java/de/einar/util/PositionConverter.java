package de.einar.util;

import com.badlogic.gdx.math.Vector2;

/**
 * This class converts the physics units to be usable by the renderer and vice
 * versa.
 */
public class PositionConverter {

	public static float PIX_TO_PHY_FACTOR = 150;

	private PositionConverter() {
		// not used
	}

	public static float toPhysicUnits(int pixels) {
		return pixels / PIX_TO_PHY_FACTOR;
	}

	public static Vector2 toPhysicUnits(Vector2 pixels) {
		return pixels.scl(1 / PIX_TO_PHY_FACTOR);
	}

	public static int toPixels(float physicsUnit) {
		return Math.round(physicsUnit * PIX_TO_PHY_FACTOR);
	}

	public static Vector2 toPixels(Vector2 physicsUnit) {
		return physicsUnit.scl(PIX_TO_PHY_FACTOR);
	}

}
