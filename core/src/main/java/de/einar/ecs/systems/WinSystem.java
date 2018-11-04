package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.google.common.eventbus.EventBus;

import de.einar.core.GameSession;
import de.einar.events.PlayerWinEvent;
import de.einar.util.PositionConverter;

/**
 * Processes the physics.
 */
public class WinSystem extends IntervalSystem {

	private boolean notified = false;
	private Body bounds;
	private EventBus bus;

	public WinSystem(EventBus bus) {
		super(Aspect.exclude(), 1 / 60f);
		this.bus = bus;
	}

	@Override
	protected void processSystem() {
		if (!notified
				&& PositionConverter.toPixels(bounds.getPosition()).x < (-1280
						* (GameSession.worldLenght - 0.01))) {
			notified = true;
			bus.post(new PlayerWinEvent());
		}
	}

	public void setBounds(Body bounds) {
		this.bounds = bounds;
	}

}
