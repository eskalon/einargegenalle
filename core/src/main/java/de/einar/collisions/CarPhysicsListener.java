package de.einar.collisions;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.google.common.eventbus.EventBus;

import de.einar.ecs.components.PlayerComponent;
import de.einar.events.PlayerDeathEvent;

public class CarPhysicsListener implements CollisionListener {

	private EventBus bus;

	public CarPhysicsListener(EventBus bus) {
		this.bus = bus;
	}

	@Override
	public void beginContact(Contact contact, Entity car, Entity player) {
		if (player != null
				&& player.getComponent(PlayerComponent.class) != null)
			bus.post(new PlayerDeathEvent());
	}

}
