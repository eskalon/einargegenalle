package de.einar.collisions;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.google.common.eventbus.EventBus;

public class GrannyPhysicsListener implements CollisionListener {

	private EventBus bus;

	public GrannyPhysicsListener(EventBus bus) {
		this.bus = bus;
	}

	@Override
	public void beginContact(Contact contact, Entity car, Entity player) {
		// bus.post(new PlayerDeathEvent());
	}

}
