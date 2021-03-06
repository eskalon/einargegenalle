package de.einar.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Takes care of translating the key strokes/mouse clicks into game values.
 */
public class GameInputProcessor implements InputProcessor {

	private boolean left, right, up, down;
	private boolean escape, space;
	private boolean leftMouse, rightMouse;
	private int scrollAmount;

	public void reset() {
		left = false;
		right = false;
		up = false;
		down = false;
		escape = false;
		space = false;
		leftMouse = false;
		rightMouse = false;
		scrollAmount = 0;
	}

	// KEYS
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
			left = true;
		} else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
			right = true;
		} else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
			up = true;
		} else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
			down = true;
		} else if (keycode == Input.Keys.ESCAPE) {
			escape = true;
		} else if (keycode == Input.Keys.SPACE) {
			space = true;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
			left = false;
		} else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
			right = false;
		} else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
			up = false;
		} else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
			down = false;
		} else if (keycode == Input.Keys.ESCAPE) {
			escape = false;
		} else if (keycode == Input.Keys.SPACE) {
			space = false;
		}

		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	public boolean isLeftPressed() {
		return left;
	}

	public boolean isRightPressed() {
		return right;
	}

	public boolean isUpPressed() {
		return up;
	}

	public boolean isDownPressed() {
		return down;
	}

	public boolean isSpacePressed() {
		return space;
	}

	public boolean isSpaceJustPressed() {
		if (space) {
			space = false;
			return true;
		}
		return false;
	}

	public boolean isEscapePressed() {
		return escape;
	}

	public boolean isEscapeJustPressed() {
		if (escape) {
			escape = false;
			return true;
		}
		return false;
	}

	// MOUSE
	public boolean isLeftMouseButtonPressed() {
		return leftMouse;
	}

	public boolean isLeftMouseButtonJustPressed() {
		if (leftMouse) {
			leftMouse = false;
			return true;
		}
		return false;
	}

	public boolean isRightMouseButtonPressed() {
		return rightMouse;
	}

	public boolean isRightMouseButtonJustPressed() {
		if (rightMouse) {
			rightMouse = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			leftMouse = true;
		} else if (button == Input.Buttons.RIGHT) {
			rightMouse = true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			leftMouse = false;
		} else if (button == Input.Buttons.RIGHT) {
			rightMouse = false;
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return mouseMoved(screenX, screenY);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// not used
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		scrollAmount = amount;
		return false;
	}

	public int getAmountScrolled() {
		return scrollAmount;
	}

	public int getAmountJustScrolled() {
		int tmp = scrollAmount;
		scrollAmount = 0;
		return tmp;
	}

}
