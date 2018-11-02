package de.einar.collisions;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.google.common.eventbus.EventBus;

import de.einar.events.PlayerDeathEvent;

public class CarPhysicsListener implements CollisionListener {

	private EventBus bus;

	public CarPhysicsListener(EventBus bus) {
		this.bus = bus;
	}

	@Override
	public void beginContact(Contact contact, Entity car, Entity target) {
		bus.post(new PlayerDeathEvent());
	}

}
