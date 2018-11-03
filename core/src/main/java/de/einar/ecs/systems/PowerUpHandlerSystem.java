package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.google.common.eventbus.EventBus;
import de.einar.collisions.PowerUpCollisionListener;
import de.einar.ecs.components.PlayerComponent;

public class PowerUpHandlerSystem extends IteratingSystem {

	@Wire
	ComponentMapper<PlayerComponent> playerComponentComponentMapper;

	private PowerUpCollisionListener powerUpCollisionListener;
	private EventBus bus;


	public PowerUpHandlerSystem(PowerUpCollisionListener powerUpCollisionListener, EventBus bus) {
		super(Aspect.all(PlayerComponent.class));
		this.powerUpCollisionListener = powerUpCollisionListener;
		this.bus = bus;
	}

	@Override
	protected void process(int entityId) {



	}
}
