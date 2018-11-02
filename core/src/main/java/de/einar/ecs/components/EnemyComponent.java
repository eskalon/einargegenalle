package de.einar.ecs.components;

import com.artemis.Component;

public class EnemyComponent extends Component {

	private int speed;
	private boolean started = false;

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

}
