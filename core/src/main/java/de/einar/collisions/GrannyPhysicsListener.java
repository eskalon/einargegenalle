package de.einar.collisions;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.google.common.eventbus.EventBus;

import de.einar.ecs.components.PlayerComponent;
import de.einar.events.GrannyContatcEvent;

public class GrannyPhysicsListener implements CollisionListener {

	private EventBus bus;

	public GrannyPhysicsListener(EventBus bus) {
		this.bus = bus;
	}

	@Override
	public void beginContact(Contact contact, Entity granny, Entity collidingEntity) {
		if (collidingEntity != null) {
			GrannyContatcEvent ev = new GrannyContatcEvent();
			ev.byPlayer = collidingEntity.getComponent(PlayerComponent.class) != null;
			ev.granny = granny;
			bus.post(ev);
		}		
	}

}
