package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.google.common.eventbus.EventBus;

import de.einar.events.PlayerWinEvent;
import de.einar.util.PositionConverter;

/**
 * Processes the physics.
 */
public class WinSystem extends IntervalSystem {

	private Body bounds;
	private EventBus bus;

	public WinSystem(EventBus bus) {
		super(Aspect.exclude(), 1 / 60f);
		this.bus = bus;
	}

	@Override
	protected void processSystem() {
		if (PositionConverter.toPixels(bounds.getPosition()).x < (-1280 * 31.99F))
			bus.post(new PlayerWinEvent());
	}

	public void setBounds(Body bounds) {
		this.bounds = bounds;
	}

}
